package com.astradev_back.astradev_back.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "KeyWords", schema = "public")
public class KeyWords {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "keyWords_generator")
    @SequenceGenerator(name = "keyWords_generator", sequenceName = "keyWords_seq", allocationSize = 1)
    private Long id;

    private String word;

}