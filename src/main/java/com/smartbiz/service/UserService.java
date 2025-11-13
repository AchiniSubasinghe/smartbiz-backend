package com.smartbiz.service;

import com.smartbiz.dto.response.UserResponseDto;
import com.smartbiz.entity.User;
import com.smartbiz.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("user not found ");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public UserResponseDto getUserByUsername (String username ){
        User user =userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not found by username"));
        return UserResponseDto.fromEntity(user);
    }

    public List<UserResponseDto > getAllUsers(){
        List<User> allUsers =  userRepo.findAll();
        return allUsers.stream().map(UserResponseDto::fromEntity).toList();
    }

    public UserResponseDto getUserById(UUID id){
        User user =userRepo.findById(id).orElseThrow(()->new UsernameNotFoundException("user not found by Id"));
        return UserResponseDto.fromEntity(user);
    }

    public void deleteUserById(UUID userId){
        User user =userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        userRepo.delete(user);
    }

    public List<User> searchUsersByUserName(String q){
        return userRepo.findByUsernameStartingWithIgnoreCase(q);
    }

}