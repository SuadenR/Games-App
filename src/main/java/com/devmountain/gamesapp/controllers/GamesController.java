package com.devmountain.gamesapp.controllers;

import com.devmountain.gamesapp.dtos.GamesDto;
import com.devmountain.gamesapp.services.GamesService;
import com.devmountain.gamesapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GamesController {

    @Autowired
    private UserService userService;


}
