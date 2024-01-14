package com.devmountain.gamesapp.services;

import com.devmountain.gamesapp.dtos.UserDto;
import com.devmountain.gamesapp.entities.Favorites;
import com.devmountain.gamesapp.entities.User;
import com.devmountain.gamesapp.repositories.FavoritesRepository;
import com.devmountain.gamesapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("http://localhost:8080/login.html");

        Optional<User> userOptional = userRepository.findById(user.getId());
        Favorites favorites = new Favorites();
        userOptional.ifPresent(favorites::setUser);
        favoritesRepository.saveAndFlush(favorites);

        return response;
    }

    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if (userOptional.isPresent()){
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getId()));
                response.add(userOptional.get().getUsername());
            } else {
                response.add("Username or password incorrect");
            }
        } else {
            response.add("Username or password incorrect");
        }
        return response;
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
    }
}
