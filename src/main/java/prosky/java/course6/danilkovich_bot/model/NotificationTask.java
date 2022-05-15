package prosky.java.course6.danilkovich_bot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {
    public enum NotificationStatus {SCHEDULED, SENT}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "notification_text")
    private String notificationMessage;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "notification_date")
    private LocalDateTime notificationData;
    // private LocalDateTime sentData;

    //@Enumerated(EnumType.STRING)
   // private NotificationStatus status = NotificationStatus.SCHEDULED;

    public NotificationTask() {
    }

    public NotificationTask(String notificationMessage, LocalDateTime notificationData) {
        this.notificationMessage = notificationMessage;
        this.notificationData = notificationData;

    }

    public Long getId() {
        return id;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public Long getChatId() {
        return chatId;
    }

    public LocalDateTime getNotificationData() {
        return notificationData;
    }

    // public LocalDateTime getSentData() {
    //     return sentData;
    //  }

  //  public NotificationStatus getStatus() {
    //    return status;
  //  }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

  //  public void markAsSent() {
       // this.status = NotificationStatus.SENT;
        //   this.sentData = LocalDateTime.now();
   // }

    public void setNotificationData(LocalDateTime notificationData) {
        this.notificationData = notificationData;
    }

    //  public void setSentData(LocalDateTime sentData) {
    //     this.sentData = sentData;
    // }

   // public void setStatus(NotificationStatus status) {
    //    this.status = status;
  //  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(id, that.id) && Objects.equals(notificationMessage, that.notificationMessage) && Objects.equals(chatId, that.chatId) && Objects.equals(notificationData, that.notificationData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationMessage, chatId, notificationData);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", notificationMessage='" + notificationMessage + '\'' +
                ", chatId=" + chatId +
                ", notificationData=" + notificationData +
                '}';
    }
}
