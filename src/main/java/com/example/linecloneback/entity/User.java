package com.example.linecloneback.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    public User(String username, String password, String nickname) {
        this.username = username;       //this.username: (위에서 선언된) 필드, username: 매개변수
        this.password = password;
        this.nickname = nickname;
    }
}
