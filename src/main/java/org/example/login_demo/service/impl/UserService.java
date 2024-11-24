package org.example.login_demo.service.impl;

import org.example.login_demo.configuration.UserDetailsImpl;
import org.example.login_demo.dto.UserDto;
import org.example.login_demo.entity.RoleEntity;
import org.example.login_demo.entity.UserEntity;
import org.example.login_demo.mapper.UserMapper;
import org.example.login_demo.model.UserModel;
import org.example.login_demo.repository.IRoleRepository;
import org.example.login_demo.repository.IUserRepository;
import org.example.login_demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.example.login_demo.util.specification.UserSpecification.hasEmail;
import static org.example.login_demo.util.specification.UserSpecification.hasUsername;

@Service
public class UserService implements IUserService, UserDetailsService {
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IRoleRepository roleRepository, IUserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(userRepository.getReferenceById(id));
    }

    @Override
    public int save(UserModel userModel) {
        if (isExistingByUsernameOrEmail(userModel.getUsername(), userModel.getEmail())) {
            return 0;
        }

        RoleEntity role = roleRepository.findById(userModel.getRoleId()).orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        UserEntity userEntity = userMapper.toEntity(userModel);
        userEntity.setRole(role);  // Assign the correct role to the user

        userRepository.save(userEntity);
        return 1;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(userRepository.findOne(Specification.where(hasUsername(username)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(Objects.requireNonNull(userRepository.findOne(Specification.where(hasUsername(username))).orElse(null)));
    }

    @Override
    public boolean isExistingByUsernameOrEmail(String username, String email) {
        UserEntity existingUser = userRepository.findOne(Specification.where(hasUsername(username).or(hasEmail(email)))).orElse(null);
        return existingUser != null;
    }
}
