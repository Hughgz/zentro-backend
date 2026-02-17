package com.ryan.module.identity.apis;

import com.ryan.module.identity.application.interfaces.IRoleService;
import com.ryan.module.identity.dtos.request.RoleRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.role}")
@AllArgsConstructor
public class RoleApi {
    private final IRoleService _service;

    @PostMapping("/roles")
    public ResponseEntity<?> insert(@RequestBody RoleRequest request){
        _service.insertRole(request);
        return ResponseEntity.status(201).body("Created role successfully");
    }
}
