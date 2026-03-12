package com.swapstyle.swapstyle.service;

import org.springframework.stereotype.Service;
import com.swapstyle.swapstyle.dto.request.LoginRequestDTO;
import com.swapstyle.swapstyle.dto.request.RegisterRequestDTO;
import com.swapstyle.swapstyle.dto.request.UserAvatarUpdateDTO;
import com.swapstyle.swapstyle.dto.request.UserNameUpdateDTO;
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
    public UserProfileResponseDTO userRegister(RegisterRequestDTO dto) {
        User user = new User();
        user.setUserName(dto.userName());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setAvatar(dto.avatar());
        user.setRole(dto.role());

        User savedUser = userRepository.save(user);

        return new UserProfileResponseDTO(
                savedUser.getIdUser(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getAvatar(),
                savedUser.getRole());
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

        if (!user.getPassword().equals(dto.password())) {
            throw new RuntimeException("Invalid password");
        }

        return new UserProfileResponseDTO(
                user.getIdUser(),
                user.getUserName(),
                user.getEmail(),
                user.getAvatar(),
                user.getRole());
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Integer idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User" + idUser + " not found "));
    }

    @Override
    public UserProfileResponseDTO updateUserName(Integer id, UserNameUpdateDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(dto.userName());
        User updatedUser = userRepository.save(user);
        return new UserProfileResponseDTO(
                updatedUser.getIdUser(),
                updatedUser.getUserName(),
                updatedUser.getEmail(),
                updatedUser.getAvatar(),
                updatedUser.getRole());
    }

    @Override
    public UserProfileResponseDTO updateUserAvatar(Integer id, UserAvatarUpdateDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setAvatar(dto.avatar());
        User updatedUser = userRepository.save(user);
        return new UserProfileResponseDTO(
                updatedUser.getIdUser(),
                updatedUser.getUserName(),
                updatedUser.getEmail(),
                updatedUser.getAvatar(),
                updatedUser.getRole());
    }
}