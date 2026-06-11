package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.Notification;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.notification.NotificationJpaRepository;
import com.smarterrecipe.domain.model.UserModel;
import com.smarterrecipe.domain.repository.UserRepository;
import com.smarterrecipe.domain.service.NotificationService;
import com.smarterrecipe.presentation.dto.notification.NotificationResponse;
import com.smarterrecipe.presentation.dto.notification.UnreadCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationHandler {

    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final NotificationJpaRepository notificationJpaRepository;

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
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getId();
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> getInbox(String username) {
        return notificationJpaRepository.findByUserUsernameOrderByCreatedAtDesc(username)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void notifyUser(User user, String title, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setIsRead(false);
        notificationJpaRepository.save(notification);
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