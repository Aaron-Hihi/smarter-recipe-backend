package com.smarterrecipe.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppStatusEntity {
    @Id
    private String id;
    private String status;
}