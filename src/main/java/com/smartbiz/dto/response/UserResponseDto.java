package com.smartbiz.dto.response;

import com.smartbiz.entity.User;
import com.smartbiz.enums.Role;
import lombok.*;

import java.util.UUID;
@Builder
@Getter
public class UserResponseDto {
    private UUID id;
    private String userName;
    private String fullName;
    private Role role;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .userName(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
