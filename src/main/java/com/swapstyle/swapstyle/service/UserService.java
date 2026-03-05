package com.swapstyle.swapstyle.service;

import com.swapstyle.swapstyle.dto.request.LoginRequestDTO;
import com.swapstyle.swapstyle.dto.request.RegisterRequestDTO;
import com.swapstyle.swapstyle.dto.request.UserUpdateDTO;
import com.swapstyle.swapstyle.dto.response.UserProfileResponseDTO;
import com.swapstyle.swapstyle.entity.User;

public interface UserService {

    public User createUser(RegisterRequestDTO dto);

    public UserProfileResponseDTO getUserProfile(Integer Id);

    public UserProfileResponseDTO login(LoginRequestDTO dto);

    public UserProfileResponseDTO updateUserName(Integer id, UserUpdateDTO dto);

    public UserProfileResponseDTO updateUserAvatar(Integer id, UserUpdateDTO dto);

}