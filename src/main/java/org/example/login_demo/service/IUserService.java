package org.example.login_demo.service;

import org.example.login_demo.dto.UserDto;
import org.example.login_demo.model.UserModel;

public interface IUserService extends IBaseService<UserDto, UserModel, Long> {
    UserDto findByUsername(String username);
    boolean isExistingByUsernameOrEmail(String username, String email);
}
