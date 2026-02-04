package com.ryan.module.identity.application.services;

import com.ryan.common.utils.MapperUtil;
import com.ryan.module.identity.application.interfaces.IAuth;
import com.ryan.module.identity.domain.model.RefreshTokens;
import com.ryan.module.identity.domain.model.Roles;
import com.ryan.module.identity.domain.model.UserRoles;
import com.ryan.module.identity.domain.model.Users;
import com.ryan.module.identity.domain.repositories.RefreshTokenRepository;
import com.ryan.module.identity.domain.repositories.RoleRepository;
import com.ryan.module.identity.domain.repositories.UserRepository;
import com.ryan.module.identity.domain.repositories.UserRoleRepository;
import com.ryan.module.identity.dtos.request.*;
import com.ryan.module.identity.dtos.response.AuthResponse;
import com.ryan.module.identity.dtos.response.RefreshTokenResponse;
import com.ryan.module.identity.dtos.response.UserResponse;
import com.ryan.module.identity.shared.enums.RoleCodeEnum;
import com.ryan.module.identity.utils.JwtTokenUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService implements IAuth {
    private final UserRepository _repository;
    private final MapperUtil _mapper;
    private final AuthenticationManager _authenticationManager;
    private final JwtTokenUtil _jwtToken;
    private final RefreshTokenRepository _refreshTokenRepository;
    private final UserRoleRepository _userRoleRepository;
    private final RoleRepository _roleRepository;

    private final PasswordEncoder _encoder;

    public AuthService(UserRepository repository, PasswordEncoder encoder,
                       MapperUtil mapper, AuthenticationManager authenticationManager,
                       JwtTokenUtil jwtToken, RefreshTokenRepository refreshTokenRepository,
                        UserRoleRepository userRoleRepository,
                       RoleRepository roleRepository) {
        _repository = repository;
        _mapper = mapper;
        _authenticationManager = authenticationManager;
        _jwtToken = jwtToken;
        _refreshTokenRepository = refreshTokenRepository;
        _encoder = encoder;
        _userRoleRepository = userRoleRepository;
        _roleRepository = roleRepository;
    }
    @Override
    public AuthResponse login(AuthRequest request) {

        _authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Users user = _repository.findByUsernameWithRoles(request.getUsername()).orElseThrow();
        String accessToken = _jwtToken.generateAccessToken(user);
        String refreshToken = _jwtToken.generateRefreshToken(user);

        RefreshTokens rt = new RefreshTokens();
        rt.setRefreshToken(refreshToken);
        rt.setExpiresAt(Instant.now().plusSeconds(604800)); //7days
        rt.setCreatedAt(Instant.now());
        rt.setUser(user);
        rt.setRevoked(false);

        _refreshTokenRepository.save(rt);
        return new AuthResponse(accessToken, refreshToken, _mapper.convertToDto(user, UserResponse.class));
    }

    @Override
    @Transactional
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
            user.setPasswordHash(_encoder.encode(request.getPassword()));
        }else{
            throw new RuntimeException("Confirm password incorrect");
        }
        user.setEmailVerify(false);
        user.setPhoneVerify(false);
        Roles role = _roleRepository.findRolesByCode(RoleCodeEnum.USER).orElseThrow(() -> new RuntimeException("Role USER not found"));
        UserRoles userRoles = new UserRoles();
        userRoles.setRole(role);
        userRoles.setUser(user);
        user.getUserRoles().add(userRoles);
        _repository.save(user);
        return _mapper.convertToDto(user, UserResponse.class);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {

    }

    @Override
    public RefreshTokenResponse refresh(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String username = _jwtToken.extractUsernameFromRefreshToken(refreshToken);
        Users user = _repository.findUsersByUsername(username).orElseThrow();

        if(!_jwtToken.validateRefreshToken(refreshToken, user)){
            throw new IllegalArgumentException("Invalid refreshToken");
        }
        RefreshTokens currentToken = _refreshTokenRepository.findRefreshTokensByUserAndRefreshToken(user, refreshToken)
                .orElseThrow(() ->
                        new IllegalArgumentException("Refresh token not recognized"));

        if (Boolean.TRUE.equals(currentToken.getRevoked()) || currentToken.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Refresh token is revoked/expired");
        }
        currentToken.setRevoked(true);
        currentToken.setRevokedAt(Instant.now());
        _refreshTokenRepository.save(currentToken);

        String newAccessToken = _jwtToken.generateAccessToken(user);
        String newRefreshToken = _jwtToken.generateRefreshToken(user);

        RefreshTokens rt = new RefreshTokens();
        rt.setUser(user);
        rt.setRefreshToken(newRefreshToken);
        rt.setExpiresAt(Instant.now().plusSeconds(604800));
        rt.setCreatedAt(Instant.now());
        rt.setRevoked(false);
        _refreshTokenRepository.save(rt);
        return new RefreshTokenResponse(newAccessToken, newRefreshToken);
    }


}
