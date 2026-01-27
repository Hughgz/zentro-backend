package com.ryan.module.identity.application.services;

import com.ryan.common.utils.MapperUtil;
import com.ryan.config.SecurityConfig;
import com.ryan.module.identity.application.interfaces.IAuth;
import com.ryan.module.identity.domain.model.RefreshTokens;
import com.ryan.module.identity.domain.model.Users;
import com.ryan.module.identity.domain.repositories.RefreshTokenRepository;
import com.ryan.module.identity.domain.repositories.UserRepository;
import com.ryan.module.identity.dtos.request.AuthRequest;
import com.ryan.module.identity.dtos.request.ForgotPasswordRequest;
import com.ryan.module.identity.dtos.request.RefreshTokenRequest;
import com.ryan.module.identity.dtos.request.UserRegisterRequest;
import com.ryan.module.identity.dtos.response.AuthResponse;
import com.ryan.module.identity.dtos.response.UserResponse;
import com.ryan.module.identity.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class AuthService implements IAuth {
    private final UserRepository _repository;
    private final SecurityConfig _config;
    private final MapperUtil _mapper;
    private final AuthenticationManager _authenticationManager;
    private final JwtTokenUtil _jwtToken;
    private final RefreshTokenRepository _refreshTokenRepository;

    public AuthService(UserRepository repository, SecurityConfig config, MapperUtil mapper, AuthenticationManager authenticationManager, JwtTokenUtil util, RefreshTokenRepository refreshTokenRepository) {
        _repository = repository;
        _config = config;
        _mapper = mapper;
        _authenticationManager = authenticationManager;
        _jwtToken = util;
        _refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        _authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Users user = _repository.findUsersByUsername(request.getUsername()).orElseThrow();
        String accessToken = _jwtToken.generateAccessToken(user);
        String refreshToken = _jwtToken.generateRefreshToken(user);

        RefreshTokens rt = new RefreshTokens();
        rt.setTokenHash(refreshToken);
        rt.setExpiresAt(Instant.now().plusSeconds(604800)); //7days
        rt.setCreatedAt(Instant.now());
        rt.setUser(user);
        _refreshTokenRepository.save(rt);
        return new AuthResponse(accessToken, refreshToken, _mapper.convertToDto(user, UserResponse.class));
    }

    @Override
    public UserResponse register(UserRegisterRequest request) {
        if(_repository.existsUsersByEmail(request.getEmail())
            || _repository.existsUsersByUsername(request.getUsername())
                || _repository.existsUsersByPhone(request.getPhone())
        ){
            throw new RuntimeException("Account registered! Please use new email/phone/username");
        }
        Users user = new Users();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setSex(request.getSex());
        user.setDob(request.getDob());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        if(request.getConfirmPassword().equals(request.getPassword())){
            user.setPasswordHash(_config.passwordEncoder().encode(request.getPassword()));
        }else{
            throw new RuntimeException("Confirm password incorrect");
        }
        user.setEmailVerify(false);
        user.setPhoneVerify(false);
        _repository.save(user);
        return _mapper.convertToDto(user, UserResponse.class);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {

    }
}
