package prosky.java.course6.danilkovich_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import prosky.java.course6.danilkovich_bot.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.Collection;

public interface NotificationRepository extends JpaRepository<NotificationTask, Long> {
   // @Query(value = "SELECT * FROM notification_task WHERE notification_date = CURRENT_TIMESTAMP" , nativeQuery = true)
   // Collection<NotificationTask> getScheduledNotifications();

    Collection<NotificationTask> getNotificationTaskByNotificationDataEquals (LocalDateTime time);
}
