package com.library.authentication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sessions")
@Getter
@Setter
public class Session {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "valid_until", nullable = false)
    private Timestamp valid_until;

}
