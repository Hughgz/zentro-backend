package com.ryan.module.identity.apis;

import com.ryan.module.identity.application.interfaces.IAuth;
import com.ryan.module.identity.dtos.request.AuthRequest;
import com.ryan.module.identity.dtos.request.RefreshTokenRequest;
import com.ryan.module.identity.dtos.request.UserRegisterRequest;
import com.ryan.module.identity.dtos.response.ApiResponse;
import com.ryan.module.identity.dtos.response.AuthResponse;
import com.ryan.module.identity.dtos.response.RefreshTokenResponse;
import com.ryan.module.identity.dtos.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {
    private final IAuth _auth;

    public AuthApi(IAuth auth) {
        _auth = auth;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody UserRegisterRequest request){
        UserResponse response = _auth.register(request);
        return ResponseEntity.status(201).body(ApiResponse.created(response));
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request){
        AuthResponse response = _auth.login(request);
        return ResponseEntity.status(200).body(ApiResponse.ok(response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<?>> refresh(@RequestBody RefreshTokenRequest request){
        RefreshTokenResponse response = _auth.refresh(request);
        return ResponseEntity.status(200).body(ApiResponse.ok(response));
    }
}
