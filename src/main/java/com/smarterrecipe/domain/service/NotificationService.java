package com.smarterrecipe.domain.service;

import com.smarterrecipe.data.entity.Notification;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.notification.NotificationJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.validator.NotificationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationJpaRepository notificationRepository;
    private final UserJpaRepository userRepository;
    private final NotificationValidator notificationValidator;

    @Transactional
    public Notification send(Long userId, String title, String message) {
        notificationValidator.validateUserExists(userId);

        User user = userRepository.findById(userId).orElseThrow();

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setIsRead(false);

        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications(Long userId) {
        notificationValidator.validateUserExists(userId);
        return notificationRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        notificationValidator.validateUserExists(userId);
        return notificationRepository.findAllByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId);
    }

    public long countUnread(Long userId) {
        notificationValidator.validateUserExists(userId);
        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

    @Transactional
    public Notification markAsRead(Long notificationId, Long userId) {
        Notification notification = notificationValidator.validateNotificationBelongsToUser(notificationId, userId);
        notification.setIsRead(true);
        return notificationRepository.save(notification);
    }

    @Transactional
    public void markAllAsRead(Long userId) {
        notificationValidator.validateUserExists(userId);
        List<Notification> unread = notificationRepository.findAllByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId);
        unread.forEach(notification -> notification.setIsRead(true));
        notificationRepository.saveAll(unread);
    }
}
