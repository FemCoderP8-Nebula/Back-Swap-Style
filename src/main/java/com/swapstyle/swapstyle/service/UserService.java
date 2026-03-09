package com.swapstyle.swapstyle.service;
import com.swapstyle.swapstyle.dto.request.LoginRequestDTO;
import com.swapstyle.swapstyle.dto.request.RegisterRequestDTO;
import com.swapstyle.swapstyle.dto.request.UserAvatarUpdateDTO;
import com.swapstyle.swapstyle.dto.request.UserNameUpdateDTO;
//import com.swapstyle.swapstyle.dto.request.UserUpdateDTO;
import com.swapstyle.swapstyle.dto.response.UserProfileResponseDTO;
import com.swapstyle.swapstyle.entity.User;


public interface UserService {

    public UserProfileResponseDTO userRegister(RegisterRequestDTO dto);

    public UserProfileResponseDTO getUserProfile(Integer id);

    public UserProfileResponseDTO login(LoginRequestDTO dto);

    //public UserProfileResponseDTO updateUserName(Integer id, UserUpdateDTO dto);

    //public UserProfileResponseDTO updateUserAvatar(Integer id, UserUpdateDTO dto);

    public void deleteUser(Integer id);

    public User getUserById(Integer idUser);

    public UserProfileResponseDTO updateUserName(Integer id, UserNameUpdateDTO dto);

    public UserProfileResponseDTO updateUserAvatar(Integer id, UserAvatarUpdateDTO dto);

}