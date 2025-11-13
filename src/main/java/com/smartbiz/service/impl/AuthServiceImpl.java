package com.smartbiz.service.impl;

import com.smartbiz.dto.request.SignInRequestDto;
import com.smartbiz.dto.response.SignInResultDto;
import com.smartbiz.dto.request.SignUpRequestDto;
import com.smartbiz.dto.response.UserResponseDto;
import com.smartbiz.entity.User;
import com.smartbiz.enums.Role;
import com.smartbiz.repo.UserRepo;
import com.smartbiz.service.AuthService;
import com.smartbiz.service.UserService;
import com.smartbiz.util.JwtUtil;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
   private final UserRepo userRepo;
   private final PasswordEncoder passwordEncoder;
   private final JwtUtil jwtUtil;
   private final AuthenticationManager authenticationManager;

   private final UserService userService;


   //constructor
    public AuthServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil=jwtUtil;
        this.authenticationManager=authenticationManager;

        this.userService=userService;

    }

    //addUser
   public UserResponseDto addUser (SignUpRequestDto signUpRequestDto){
       String encodePassword = passwordEncoder.encode(signUpRequestDto.getPassword());
       User user =User.builder()
               .username(signUpRequestDto.getUserName())
               .fullName(signUpRequestDto.getFullName())
               .role(Role.valueOf(signUpRequestDto.getRole().toUpperCase()))
               .password(encodePassword)
               .build();

       User createdUser =userRepo.save(user);
       return UserResponseDto.fromEntity(createdUser);
   }

   //login
    public SignInResultDto signIn (SignInRequestDto signInRequestDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getUsername(),signInRequestDto.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(signInRequestDto.getUsername());
        UserResponseDto user =userService.getUserByUsername(signInRequestDto.getUsername());
        String token=jwtUtil.generateToken(userDetails);

        ResponseCookie cookie =ResponseCookie.from("jwt",token).httpOnly(true).secure(false).
                path("/").maxAge(24*60*60).sameSite("Lax").build();
        return SignInResultDto.builder().cookie(cookie).userResponseDto(user).build();
    }

   //changePassword
   public UserResponseDto changePassword(String username, String oldPassword, String newPassword) {
       // Find the user by username
       User user = userRepo.findByUsername(username)
               .orElseThrow(() -> new RuntimeException("User not found"));

       // Check old password
       if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
           throw new RuntimeException("Old password is incorrect");
       }

       // Encode new password and save
       user.setPassword(passwordEncoder.encode(newPassword));
       userRepo.save(user);

       return UserResponseDto.fromEntity(user);
   }
   //auhentication
   public boolean authenticateUser(String token) {
       try {
           String username = jwtUtil.extractUsername(token);
           Optional<User> userOptional = userRepo.findByUsername(username);
           if (userOptional.isEmpty()) {
               return false;
           }

           UserDetails userDetails = userService.loadUserByUsername(username);
           return jwtUtil.validateToken(token, userDetails);
       } catch (Exception e) {
           return false;
       }
   }

}
