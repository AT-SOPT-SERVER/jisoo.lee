package org.sopt.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")  // ✅ H2에서 예약어 'user' 대신 안전한 테이블명 사용
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // 유효성 검사는 DTO에서, DB null 금지는 여기서!
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    // 기본 생성자
    public User() {}

    // 이름을 받아 생성하는 생성자
    public User(String name) {
        this.name = name;
    }

    // ID 조회용 getter
    public Long getId() {
        return id;
    }

    // 이름 조회용 getter
    public String getName() {
        return name;
    }

    // 내가 쓴 글들 조회용 getter
    public List<Post> getPosts() {
        return posts;
    }
}
