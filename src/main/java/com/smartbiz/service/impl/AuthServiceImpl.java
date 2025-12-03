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
               .username(signUpRequestDto.getUsername())
               .fullName(signUpRequestDto.getFullName())
               //.role(Role.valueOf(signUpRequestDto.getRole().toUpperCase()))
               .role(Role.EDITOR)
               .password(encodePassword)
               .build();

       User createdUser =userRepo.save(user);
       return UserResponseDto.fromEntity(createdUser);
   }

   //login
   @Override
   public SignInResultDto signIn(SignInRequestDto signInRequestDto) {
       // authenticate credentials
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       signInRequestDto.getUsername(),
                       signInRequestDto.getPassword()
               )
       );
       //load user full details
       UserDetails userDetails = userService.loadUserByUsername(signInRequestDto.getUsername());
      //load user response dto
       UserResponseDto user = userService.getUserByUsername(signInRequestDto.getUsername());
       // generate token
       String token = jwtUtil.generateToken(userDetails);
       // create http-only cookie
       ResponseCookie cookie = ResponseCookie.from("jwt", token)
               .httpOnly(true)
               .secure(false)  // change to true when using HTTPS
               .path("/")
               .maxAge(24 * 60 * 60)
               .sameSite("Lax")
               .build();
       return SignInResultDto.builder()
               .cookie(cookie)        // this sets Set-Cookie header
               .token(token)          // easy access for frontend
               .userResponseDto(user) // user info
               .build();
   }


    //changePassword
   public UserResponseDto changePassword(String username, String newPassword) {
       // Find the user by username
       User user = userRepo.findByUsername(username)
               .orElseThrow(() -> new RuntimeException("User not found"));

       // Check old password
//       if (!passwordEncoder.matches(newPassword, user.getPassword())) {
//           throw new RuntimeException("Old password is incorrect");
//       }

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
