package com.smartbiz.controller;

import com.smartbiz.dto.response.ApiResponseDto;
import com.smartbiz.dto.response.UserResponseDto;
import com.smartbiz.entity.User;
import com.smartbiz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

  @GetMapping
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>>getAllUsers(){
        List<UserResponseDto> allUsers =userService.getAllUsers();
        ApiResponseDto<List<UserResponseDto>> response =new ApiResponseDto<>(true, "User Retrieve Successfully",allUsers);
        
        return  new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping("{id}")
    public  ResponseEntity<ApiResponseDto<Object>>getUserById(@PathVariable UUID id){
        UserResponseDto user =userService.getUserById(id);
        ApiResponseDto<Object>response =new ApiResponseDto<>(true,"Success", user);
      return  new ResponseEntity<>(response, HttpStatus.OK);
  }

  //@GetMapping("/search")
  @GetMapping("/search")
  public ResponseEntity<ApiResponseDto<List<User>>> searchUsers(@RequestParam("q") String query) {
      List<User> users = userService.searchUsersByUserName(query);
      ApiResponseDto<List<User>> response =
              new ApiResponseDto<>(true, "Users matching query retrieved successfully", users);

      return new ResponseEntity<>(response, HttpStatus.OK);
  }


    // @DeleteMapping("{id}")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Object>> deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
        ApiResponseDto<Object> response =
                new ApiResponseDto<>(true, "User deleted successfully", null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
