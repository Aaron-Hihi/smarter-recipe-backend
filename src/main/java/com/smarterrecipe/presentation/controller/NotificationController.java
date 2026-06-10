package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.NotificationHandler;
import com.smarterrecipe.presentation.dto.notification.NotificationResponse;
import com.smarterrecipe.presentation.dto.notification.UnreadCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationHandler notificationHandler;

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getMyNotifications() {
        return ResponseEntity.ok(notificationHandler.getMyNotifications());
    }

    @GetMapping("/unread")
    public ResponseEntity<List<NotificationResponse>> getMyUnreadNotifications() {
        return ResponseEntity.ok(notificationHandler.getMyUnreadNotifications());
    }

    @GetMapping("/unread/count")
    public ResponseEntity<UnreadCountResponse> getUnreadCount() {
        return ResponseEntity.ok(notificationHandler.getUnreadCount());
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<NotificationResponse> markAsRead(@PathVariable Long notificationId) {
        return ResponseEntity.ok(notificationHandler.markAsRead(notificationId));
    }

    @PatchMapping("/read-all")
    public ResponseEntity<Void> markAllAsRead() {
        notificationHandler.markAllAsRead();
        return ResponseEntity.noContent().build();
    }
}
