package com.ryan.module.identity.utils;

import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;

import com.ryan.module.identity.domain.model.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${jwt.accessSecretKey}")
    private String accessSecretKey;
    @Value("${jwt.refreshSecretKey}")
    private String refreshSecretKey;
    @Value("${jwt.accessTokenExpiration}")
    private long accessTokenExpiration;
    @Value("${jwt.refreshTokenExpiration}")
    private long refreshTokenExpiration;


    // generate token
    public String generateAccessToken(Users user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role", user.getRoleCodes())
                .claim("token_type", "access")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getAccessKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String generateRefreshToken(Users user) {
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("token_type", "refresh")
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date(currentTime))
                .expiration(new Date(currentTime + refreshTokenExpiration))
                .signWith(getRefreshKey(), Jwts.SIG.HS256)
                .compact();
    }

    // secret key
    private SecretKey getAccessKey() {
        byte[] bytes = Decoders.BASE64.decode(accessSecretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private SecretKey getRefreshKey() {
        byte[] bytes = Decoders.BASE64.decode(refreshSecretKey);
        return Keys.hmacShaKeyFor(bytes);
    }


    // extract claims
    private Claims extractAccessClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getAccessKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new JwtException("Invalid or expired JWT token", e);
        }
    }

    private Claims extractRefreshClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getRefreshKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new JwtException("Invalid or expired JWT token", e);
        }
    }

    //validate token
    public boolean validateAccessToken(String accessToken, UserDetails userDetail) {
        try {
            Claims claims = extractAccessClaims(accessToken);
            String type = claims.get("token_type", String.class);
            String username = claims.getSubject();
            Date expAccess = claims.getExpiration();
            if(!type.equals("access")) {
                throw new JwtException("Token is not an access token");
            }
            return username.equals(userDetail.getUsername()) && expAccess.after(new Date());
        }catch(JwtException jwtException) {
            return false;
        }
    }

    public boolean validateRefreshToken(String refreshToken, UserDetails userDetail) {
        try {
            Claims claims = extractRefreshClaims(refreshToken);
            String type = claims.get("token_type", String.class);
            String username = claims.getSubject();
            Date expRefresh = claims.getExpiration();
            if(!"refresh".equals(type)) {
                throw new JwtException("Token is not a refresh token");
            }
            return username.equals(userDetail.getUsername()) && expRefresh.after(new Date());
        }catch(JwtException e) {
            return false;
        }
    }

    //extract token
    public String extractUsernameFromAccessToken(String accessToken) {
        Claims claims = extractAccessClaims(accessToken);
        String type = claims.get("token_type", String.class);
        if(!type.equals("access")) {
            throw new JwtException("This token isn't access");
        }
        return claims.getSubject();
    }

    public String extractUsernameFromRefreshToken(String refreshToken) {
        Claims claims = extractRefreshClaims(refreshToken);
        String type = claims.get("token_type", String.class);
        if(!type.equals("refresh")) {
            throw new JwtException("This token isn't refresh");
        }
        return claims.getSubject();
    }

    //extract role
    public String extractRole(String accessToken) {
        return extractAccessClaims(accessToken).get("role", String.class);
    }

    //extract token id
    public String extractTokenId(String refreshToken) {
        Claims claims = extractRefreshClaims(refreshToken);
        String type = claims.get("token_type", String.class);
        if(!type.equals("refresh")) {
            throw new JwtException("This toke isn't refresh");
        }
        return claims.getId();
    }
}
