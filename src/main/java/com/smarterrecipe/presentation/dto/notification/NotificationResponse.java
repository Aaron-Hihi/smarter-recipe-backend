package com.smarterrecipe.presentation.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private String title;
    private String message;
    private Boolean isRead;
    private LocalDateTime createdAt;
}
