package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.Notification;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.UserRepository;
import com.smarterrecipe.domain.service.NotificationService;
import com.smarterrecipe.presentation.dto.notification.NotificationResponse;
import com.smarterrecipe.presentation.dto.notification.UnreadCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationHandler {

    private final NotificationService notificationService;
    private final UserRepository userRepository;

    public List<NotificationResponse> getMyNotifications() {
        Long userId = getAuthenticatedUserId();
        return notificationService.getAllNotifications(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<NotificationResponse> getMyUnreadNotifications() {
        Long userId = getAuthenticatedUserId();
        return notificationService.getUnreadNotifications(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UnreadCountResponse getUnreadCount() {
        Long userId = getAuthenticatedUserId();
        long count = notificationService.countUnread(userId);
        return new UnreadCountResponse(count);
    }

    public NotificationResponse markAsRead(Long notificationId) {
        Long userId = getAuthenticatedUserId();
        Notification notification = notificationService.markAsRead(notificationId, userId);
        return mapToResponse(notification);
    }

    public void markAllAsRead() {
        Long userId = getAuthenticatedUserId();
        notificationService.markAllAsRead(userId);
    }

    private Long getAuthenticatedUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
        return user.getId();
    }

    private NotificationResponse mapToResponse(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getIsRead(),
                notification.getCreatedAt()
        );
    }
}
