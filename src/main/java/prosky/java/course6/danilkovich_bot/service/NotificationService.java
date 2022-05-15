package prosky.java.course6.danilkovich_bot.service;

import prosky.java.course6.danilkovich_bot.model.NotificationTask;

import java.util.Optional;
import java.util.function.Consumer;

public interface NotificationService {
    default void schedule(NotificationTask task, Long chatId) {
    }

    NotificationTask parse(String notificationBotMessage);
}
