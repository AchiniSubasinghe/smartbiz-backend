package com.smartbiz.repo;

import com.smartbiz.entity.ChangePasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ChangePasswordTokenRepo extends JpaRepository<ChangePasswordToken, UUID>{
        Optional<ChangePasswordToken> findByToken(String token);
        void deleteByToken(String token);
;
}
