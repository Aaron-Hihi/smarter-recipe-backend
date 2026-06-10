package com.smarterrecipe.domain.validator;

import com.smarterrecipe.data.entity.Notification;
import com.smarterrecipe.data.repository.NotificationRepository;
import com.smarterrecipe.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationValidator {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User tidak ditemukan");
        }
    }

    public Notification validateNotificationBelongsToUser(Long notificationId, Long userId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notifikasi tidak ditemukan"));

        if (!notification.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Notifikasi tidak dimiliki oleh user ini");
        }

        return notification;
    }
}
