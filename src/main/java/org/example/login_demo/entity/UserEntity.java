package org.example.login_demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tbl_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String username;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
