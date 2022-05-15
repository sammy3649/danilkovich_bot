package prosky.java.course6.danilkovich_bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import prosky.java.course6.danilkovich_bot.model.NotificationTask;
import prosky.java.course6.danilkovich_bot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private static final String BOT_MESSAGE = "([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private final NotificationRepository repository;


    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void schedule(NotificationTask task, Long chatId) {
        logger.info(String.valueOf(chatId), task);
        task.setChatId(chatId);
        NotificationTask storedTask = repository.save(task);
        logger.info("1Напоминание добавлено : " + storedTask);
    }

    @Override
    public NotificationTask parse(String notificationBotMessage) {
        Pattern pattern = Pattern.compile(BOT_MESSAGE);
        Matcher matcher = pattern.matcher(notificationBotMessage);
        NotificationTask result = null;
        try {
            if (matcher.find()) {
                LocalDateTime notificationDateTime = LocalDateTime.parse(matcher.group(1), DATE_TIME_FORMATTER);
                logger.info(String.valueOf(notificationDateTime));
                String notification = matcher.group(3);
                logger.info(String.valueOf(notification));
                result = new NotificationTask(notification, notificationDateTime);
            }
        } catch (Exception exception) {
            logger.error("Ошибка добавления : " + notificationBotMessage, exception);
        }
        logger.info(String.valueOf(Optional.ofNullable(result)));
        return result;

    }
}
