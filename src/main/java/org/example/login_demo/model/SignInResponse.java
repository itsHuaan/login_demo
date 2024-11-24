package org.example.login_demo.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private Long id;
    private String type;
    private String token;
    private String username;
    private String email;
    private String roleName;
}
