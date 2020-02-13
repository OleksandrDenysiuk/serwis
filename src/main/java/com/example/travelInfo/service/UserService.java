package com.example.travelInfo.service;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;

import static com.example.travelInfo.domain.Role.ADMIN;
import static com.example.travelInfo.domain.Role.USER;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null){
            return false;
        }

        user.setActive(true);
        user.setLocked(false);
        user.setRoles(Collections.singleton(USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void updateProfile(User user,String username, String name, String surname, String password, String email) {

        boolean isUserNameChanged = isChanged(username,user.getUsername());
        boolean isNameChanged = isChanged(name,user.getName());
        boolean isSurnameChanged = isChanged(surname,user.getSurname());
        boolean isPasswordChanged = passwordEncoder.matches(password,user.getPassword());
        boolean isEmailChanged = isChanged(email,user.getEmail());

        if (isUserNameChanged) {
            user.setUsername(username);
        }
        if (isNameChanged) {
            user.setName(name);
        }
        if (isSurnameChanged) {
            user.setSurname(surname);
        }
        if (isPasswordChanged) {
            user.setPassword(password);
        }
        if (isEmailChanged) {
            user.setEmail(email);
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        userRepository.save(user);
    }

    private boolean isChanged(String value1 , String value2){
        return (value1 != null && !value1.equals(value2)) ||
                (value2 != null && !value2.equals(value1));
    }

    public void subscribe(Place place,User user){
        place.getUsers().add(user);
        placeRepository.save(place);
        user.getPlaces().add(place);
    }

    public void unSubscribe(Place place, User user){
        place.getUsers().remove(user);
        placeRepository.save(place);
        user.getPlaces().remove(place);
    }

    public User getAdmin(){
        return userRepository.findByRoles(ADMIN);
    }
}
