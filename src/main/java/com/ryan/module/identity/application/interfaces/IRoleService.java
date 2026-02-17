package com.ryan.module.identity.application.interfaces;

import com.ryan.module.identity.dtos.request.RoleRequest;

public interface IRoleService {
    void insertRole(RoleRequest request);
}
