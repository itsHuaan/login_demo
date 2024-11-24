package org.example.login_demo.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SignUpRequest {
    private String name;
    private String username;
    private String password;
    private String email;
    private Long roleId;
}
