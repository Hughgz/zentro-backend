package com.ryan.module.identity.apis;

import com.ryan.module.identity.application.interfaces.IRole;
import com.ryan.module.identity.dtos.request.RoleRequest;
import com.ryan.module.identity.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
public class RoleApi {
    private final IRole _roleService;

    public RoleApi(IRole roleService) {
        _roleService = roleService;
    }

    @PostMapping("/roles")
    public ResponseEntity<?> insert(@RequestBody RoleRequest request){
        _roleService.insertRole(request);
        return ResponseEntity.status(201).body("Created role successfully");
    }
}
