package com.smartbiz.dto.response;

import com.smartbiz.entity.Business;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Getter @Builder
public class BusinessResponseDto {
    private UUID id;
    private String businessName;
    private String  businessCategory;

    public static BusinessResponseDto fromEntity(Business business) {
        return BusinessResponseDto.builder()
                .id(business.getId())
                .businessName(business.getBusinessName())
                .businessCategory((business.getBusinessCategory()))
                .build();
    }
}
