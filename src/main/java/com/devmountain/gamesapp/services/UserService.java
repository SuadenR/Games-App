package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.UserDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {

    @Transactional
    List<String> addUser(UserDto userDto);

    List<String> userLogin(UserDto userDto);
}
