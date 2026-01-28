package com.ryan.module.identity.dtos.request;


import com.ryan.module.identity.domain.model.UserRoles;
import com.ryan.module.identity.shared.enums.RoleCodeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    private RoleCodeEnum code;
    private String name;
}
