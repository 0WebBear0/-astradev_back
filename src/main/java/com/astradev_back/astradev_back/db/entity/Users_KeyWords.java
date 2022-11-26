package com.astradev_back.astradev_back.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@CrossOrigin
@Table(name = "users_keywords")
public class Users_KeyWords {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_keywords_generator")
    @SequenceGenerator(name = "users_keywords_generator", sequenceName = "users_keywords_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "\"user\"")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "word")
    private KeyWords word;

}