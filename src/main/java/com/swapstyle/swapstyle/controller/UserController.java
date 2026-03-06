package com.swapstyle.swapstyle.controller;

import com.swapstyle.swapstyle.dto.request.LoginRequestDTO;
import com.swapstyle.swapstyle.dto.request.RegisterRequestDTO;
import com.swapstyle.swapstyle.dto.request.UserUpdateDTO;
import com.swapstyle.swapstyle.dto.response.UserProfileResponseDTO;
import com.swapstyle.swapstyle.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileResponseDTO> createUser(@RequestBody RegisterRequestDTO dto) {
        UserProfileResponseDTO response = userService.userRegister(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserProfileResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        UserProfileResponseDTO response = userService.login(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileResponseDTO> getProfile(@PathVariable Integer id) {
        UserProfileResponseDTO response = userService.getUserProfile(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/name/{id}")
    public ResponseEntity<UserProfileResponseDTO> updateUserName(@PathVariable Integer id,@RequestBody UserUpdateDTO dto) {
        UserProfileResponseDTO updated = userService.updateUserName(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/avatar/{id}")
    public ResponseEntity<UserProfileResponseDTO> updateUserAvatar(@PathVariable Integer id,@RequestBody UserUpdateDTO dto) {
        UserProfileResponseDTO updated = userService.updateUserAvatar(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}