package org.example.login_demo.service.impl;

import org.example.login_demo.configuration.UserDetailsImpl;
import org.example.login_demo.dto.UserDto;
import org.example.login_demo.mapper.UserMapper;
import org.example.login_demo.model.UserModel;
import org.example.login_demo.repository.IUserRepository;
import org.example.login_demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService, UserDetailsService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(IUserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(Objects.requireNonNull(userRepository.findById(id).orElse(null)));
    }

    @Override
    public int save(UserModel userModel) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(userRepository.findByUsername(username));
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username));
    }
}
