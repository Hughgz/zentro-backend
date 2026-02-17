package com.ryan.module.identity.apis;

import com.ryan.module.identity.application.interfaces.IAuthService;
import com.ryan.module.identity.dtos.request.AuthRequest;
import com.ryan.module.identity.dtos.request.RefreshTokenRequest;
import com.ryan.module.identity.dtos.request.UserRegisterRequest;
import com.ryan.common.response.ApiResponse;
import com.ryan.module.identity.dtos.response.AuthResponse;
import com.ryan.module.identity.dtos.response.RefreshTokenResponse;
import com.ryan.module.identity.dtos.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.auth}")
@AllArgsConstructor
public class AuthApi {
    private final IAuthService _service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody UserRegisterRequest request){
        UserResponse response = _service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(HttpStatus.CREATED.value(),"User is registered successfully", response));
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request){
        AuthResponse response = _service.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(HttpStatus.OK.value(),"Login successfully", response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<?>> refresh(@RequestBody RefreshTokenRequest request){
        RefreshTokenResponse response = _service.refresh(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(HttpStatus.OK.value(),"Created refresh token successfully", response));
    }
}
