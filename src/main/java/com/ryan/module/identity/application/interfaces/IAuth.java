package com.ryan.module.identity.application.interfaces;

import com.ryan.module.identity.dtos.request.AuthRequest;
import com.ryan.module.identity.dtos.request.ForgotPasswordRequest;
import com.ryan.module.identity.dtos.request.RefreshTokenRequest;
import com.ryan.module.identity.dtos.request.UserRegisterRequest;
import com.ryan.module.identity.dtos.response.AuthResponse;
import com.ryan.module.identity.dtos.response.RefreshTokenResponse;
import com.ryan.module.identity.dtos.response.UserResponse;

public interface IAuth {
    AuthResponse login(AuthRequest request);
    UserResponse register(UserRegisterRequest request);
    void forgotPassword(ForgotPasswordRequest request);
    RefreshTokenResponse refresh(RefreshTokenRequest request);
}
