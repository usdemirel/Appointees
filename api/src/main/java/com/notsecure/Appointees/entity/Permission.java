package com.notsecure.Appointees.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Permission implements Serializable {
@Id
private String shortTitle;
private LocalDateTime timeStamp;
private String title;
private String description;
private boolean active;
}