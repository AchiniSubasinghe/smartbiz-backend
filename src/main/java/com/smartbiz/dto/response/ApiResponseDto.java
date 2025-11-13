package com.smartbiz.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T>{
    private boolean success;
    private String message;
    private T data;
}
