package prosky.java.course6.danilkovich_bot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import prosky.java.course6.danilkovich_bot.model.NotificationTask;
import prosky.java.course6.danilkovich_bot.repository.NotificationRepository;
import prosky.java.course6.danilkovich_bot.service.NotificationService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private static final String START = "/start";
    private static final String TEXT = "Для использования бота по назначению, отправьте ему сообщение вида 01.01.2022 20:00 Сделать домашнюю работу ";
    private final TelegramBot telegramBot;
    private final NotificationService notificationService;
    private NotificationRepository repository;
    private static String botMessage;


    public TelegramBotUpdatesListener(TelegramBot telegramBot, NotificationService notificationService, NotificationRepository repository) {
        this.telegramBot = telegramBot;
        this.notificationService = notificationService;
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void notifyScheduledTask() {
        logger.info("2Напоминание добавлено ");
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        Collection<NotificationTask> notifications = repository.getNotificationTaskByNotificationDataEquals(time);
        logger.info("Всего {} напоминаний ", notifications.size());
        if (!notifications.isEmpty())
            logger.info("Notices to send " + notifications.size());
        for (NotificationTask notificationTask : notifications) {
            botMessage = "Добрый день! Вы просили напомнить о событии \"" + notificationTask.getNotificationMessage() + "\".";
            SendMessage request = new SendMessage(notificationTask.getChatId(), botMessage);
            telegramBot.execute(request);
            logger.info("Напоминание было отправлено");
        }
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            Message message = update.message();
            if (message.text().startsWith(START)) {
                logger.info(START + " command has been received");
                sendMessage(extractChatId(message), TEXT);
            } else {
                NotificationTask task = notificationService.parse(message.text());
                logger.info(String.valueOf(task));
                scheduleNotification(update.message().chat().id(), task);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    private void scheduleNotification(Long chatId, NotificationTask task) {
        logger.info("Перед сохранением");
        notificationService.schedule(task, chatId);
        sendMessage(chatId, "Напоминание добавлено ");
    }

    private void sendMessage(Long chatId, String messageText) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        telegramBot.execute(sendMessage);
    }

    private Long extractChatId(Message message) {
        return message.chat().id();
    }
}
