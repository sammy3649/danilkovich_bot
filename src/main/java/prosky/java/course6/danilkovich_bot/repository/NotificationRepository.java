package prosky.java.course6.danilkovich_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prosky.java.course6.danilkovich_bot.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.Collection;

public interface NotificationRepository extends JpaRepository<NotificationTask, Long> {
    Collection<NotificationTask> getNotificationTaskByNotificationDataEquals(LocalDateTime time);
}
