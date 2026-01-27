package com.ryan.module.identity.dtos.response;


import com.ryan.module.identity.domain.model.Addresses;
import com.ryan.module.identity.domain.model.RefreshTokens;
import com.ryan.module.identity.domain.model.Roles;
import com.ryan.module.identity.shared.enums.SexEnum;
import com.ryan.module.identity.shared.enums.StatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private SexEnum sex;
    private String dob;
    private String email;
    private String phone;
    private String avatarUrl;
    private Boolean emailVerify;
    private Boolean phoneVerify;
    private StatusEnum status = StatusEnum.ACTIVE;
}
