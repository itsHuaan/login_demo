package org.example.login_demo.mapper;

import org.example.login_demo.dto.UserDto;
import org.example.login_demo.entity.RoleEntity;
import org.example.login_demo.entity.UserEntity;
import org.example.login_demo.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roleId(userEntity.getRole().getRoleId())
                .build();
    }
    public UserEntity toEntity(UserModel userModel) {
        RoleEntity role = new RoleEntity();
        role.setRoleId(userModel.getRoleId());
        return UserEntity.builder()
                .userId(userModel.getUserId())
                .name(userModel.getName())
                .username(userModel.getUsername())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .role(role)
                .build();
    }
}
