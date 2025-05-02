package org.sopt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름은 비어 있을 수 없습니다.")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public List<Post> getPosts() { return posts; }
}
