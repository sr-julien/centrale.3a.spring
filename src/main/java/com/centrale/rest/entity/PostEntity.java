package com.centrale.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date LastUpdate;

    @PrePersist
    private void onCreate() {
        LastUpdate = new Date();
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="userId", nullable = false)
    private UserEntity author;
}
