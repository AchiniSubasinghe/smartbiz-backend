package com.smartbiz.service.impl;

import com.smartbiz.dto.request.SignInRequestDto;
import com.smartbiz.dto.response.SignInResultDto;
import com.smartbiz.dto.request.SignUpRequestDto;
import com.smartbiz.dto.response.UserResponseDto;
import com.smartbiz.entity.ChangePasswordToken;
import com.smartbiz.entity.User;
import com.smartbiz.enums.Role;
import com.smartbiz.repo.ChangePasswordTokenRepo;
import com.smartbiz.repo.UserRepo;
import com.smartbiz.service.AuthService;
import com.smartbiz.service.EmailService;
import com.smartbiz.service.UserService;
import com.smartbiz.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService{
   private final UserRepo userRepo;
   private final PasswordEncoder passwordEncoder;
   private final JwtUtil jwtUtil;
   private final AuthenticationManager authenticationManager;
   private final ChangePasswordTokenRepo changePasswordTokenRepo;
   private final EmailService emailService;
   private final UserService userService;


   //constructor
    public AuthServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService,ChangePasswordTokenRepo changePasswordTokenRepo ,EmailService emailService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil=jwtUtil;
        this.authenticationManager=authenticationManager;
        this.emailService=emailService;
        this.changePasswordTokenRepo= changePasswordTokenRepo;
        this.userService=userService;

    }

    //AddUser
   public UserResponseDto addUser (SignUpRequestDto signUpRequestDto){
       String encodePassword = passwordEncoder.encode(signUpRequestDto.getPassword());
       User user =User.builder()
               .username(signUpRequestDto.getUsername())
               .fullName(signUpRequestDto.getFullName())
               .email(signUpRequestDto.getEmail())
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
                       signInRequestDto.getEmail(),
                       signInRequestDto.getPassword()
               )
       );
       //load user full details
       UserDetails userDetails = userService.loadUserByUsername(signInRequestDto.getEmail());
      //load user response dto
       UserResponseDto user = userService.getUserByEmail(signInRequestDto.getEmail());
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

//  Send reset Email
@Override
public String sendResetEmail(String email) {
    User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

    String token = UUID.randomUUID().toString();

      ChangePasswordToken resetToken = ChangePasswordToken.builder()
            .token(token)
            .email(email)
            .expiresAt(LocalDateTime.now().plusMinutes(30))
            .build();

    changePasswordTokenRepo.save(resetToken);

    String link = "http://localhost:3000/auth/reset-password?token=" + token;
    emailService.sendResetPasswordEmail(email,  link);

    return link;
}

//reset password with token
@Transactional
@Override
public void resetPasswordWithToken(String token, String newPassword) {


    ChangePasswordToken resetToken = changePasswordTokenRepo.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Invalid reset token"));

    if (resetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
        throw new RuntimeException("Reset token expired");
    }

    User user = userRepo.findByEmail(resetToken.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    user.setPassword(passwordEncoder.encode(newPassword));
    userRepo.save(user);

    changePasswordTokenRepo.deleteByToken(token);
}



    //auhentication
   public boolean authenticateUser(String token) {
       try {
           String username = jwtUtil.extractUsername(token);
           Optional<User> userOptional = userRepo.findByEmail(username);
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
