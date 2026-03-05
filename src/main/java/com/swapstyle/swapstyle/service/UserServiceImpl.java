package com.swapstyle.swapstyle.service;

import org.springframework.stereotype.Service;

import com.swapstyle.swapstyle.dto.request.LoginRequestDTO;
import com.swapstyle.swapstyle.dto.request.RegisterRequestDTO;
import com.swapstyle.swapstyle.dto.request.UserUpdateDTO;
import com.swapstyle.swapstyle.dto.response.UserProfileResponseDTO;
import com.swapstyle.swapstyle.entity.User;

import com.swapstyle.swapstyle.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(RegisterRequestDTO dto) {
        User user = new User();
        user.setUserName(dto.userName());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setAvatar(dto.avatar());
        user.setRole(dto.role());

        return userRepository.save(user);
    }

    @Override
    public UserProfileResponseDTO getUserProfile(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid user"));
        return new UserProfileResponseDTO(
                user.getIdUser(),
                user.getUserName(),
                user.getEmail(),
                user.getAvatar(),
                user.getRole());

    }

    @Override
    public UserProfileResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + dto.email()));
        return new UserProfileResponseDTO(
                user.getIdUser(),
                user.getUserName(),
                user.getEmail(),
                user.getAvatar(),
                user.getRole());
    }

    @Override
    public UserProfileResponseDTO updateUserName(Integer id, UserUpdateDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(dto.userName());
        User updatedUser = userRepository.save(user);
        return new UserProfileResponseDTO(
            updatedUser.getIdUser(),
            updatedUser.getUserName(),
            updatedUser.getEmail(),
            updatedUser.getAvatar(),
            updatedUser.getRole()
        );
    }

    @Override
    public UserProfileResponseDTO updateUserAvatar(Integer id, UserUpdateDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setAvatar(dto.avatar());
        User updatedUser = userRepository.save(user);
        return new UserProfileResponseDTO(
            updatedUser.getIdUser(),
            updatedUser.getUserName(),
            updatedUser.getEmail(),
            updatedUser.getAvatar(),
            updatedUser.getRole()
        );
    }

}
