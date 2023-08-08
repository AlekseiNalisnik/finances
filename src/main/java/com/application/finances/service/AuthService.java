package com.application.finances.service;

import com.application.finances.dto.JwtRequestDto;
import com.application.finances.dto.JwtResponseDto;
import com.application.finances.dto.RegistrationUserDto;
import com.application.finances.exceptions.AppError;
import com.application.finances.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final TokenService tokenService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(JwtRequestDto jwtRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequestDto.getUsername(), jwtRequestDto.getPassword()
            ));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), "Неверный логин или пароль"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        UserDetails userDetails = userService.loadUserByUsername(jwtRequestDto.getUsername());
        String jwtToken = jwtTokenUtils.generateToken(userDetails);

        var user = userService
            .findByUsername(jwtRequestDto.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        tokenService.revokeUserToken(user);
        tokenService.saveUserToken(user, jwtToken);

        return ResponseEntity.ok(new JwtResponseDto(jwtToken));
    }

    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto) {
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь существует"),
                    HttpStatus.BAD_REQUEST
            );
        }
        var savedUser = userService.createNewUser(registrationUserDto);

        UserDetails userDetails = userService.loadUserByUsername(registrationUserDto.getUsername());
        String jwtToken = jwtTokenUtils.generateToken(userDetails);

        tokenService.saveUserToken(savedUser, jwtToken);

        return ResponseEntity.ok(new JwtResponseDto(jwtToken));
    }
}
