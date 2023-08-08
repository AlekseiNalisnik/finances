package com.application.finances.service;

import com.application.finances.dto.JwtRequestDto;
import com.application.finances.dto.JwtResponseDto;
import com.application.finances.dto.RegistrationUserDto;
import com.application.finances.exceptions.AppError;
import com.application.finances.utils.JwtTokenUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthServiceTest {
    @Mock
    private AuthService authService;
    @Mock
    private UserService userService;
    @Mock
    private JwtTokenUtils jwtTokenUtils;

    @Test
    void testCreateAuthToken() {
        var jwtRequestDto = new JwtRequestDto();
        jwtRequestDto.setUsername("admin");
        jwtRequestDto.setPassword("100");

        UserDetails userDetails = userService.loadUserByUsername(jwtRequestDto.getUsername());
        String jwtToken = jwtTokenUtils.generateToken(userDetails);

        Mockito.doReturn(ResponseEntity.ok(new JwtResponseDto(jwtToken)))
            .when(authService).createAuthToken(jwtRequestDto);
    }

    @Test
    void testCreateAuthTokenIfBadCredentials() {
        var jwtRequestDto = new JwtRequestDto();
        jwtRequestDto.setUsername("noSuchUser");
        jwtRequestDto.setPassword("noSuchUser");

        Mockito.doReturn(new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(), "Неверный логин или пароль"),
                HttpStatus.UNAUTHORIZED
            ))
            .when(authService).createAuthToken(jwtRequestDto);
    }

    @Test
    void testCreateNewUser() {
        var registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setPassword("newPassword");
        registrationUserDto.setUsername("newUser");
        registrationUserDto.setFirstName("newFirstName");
        registrationUserDto.setLastName("newLastName");

        UserDetails userDetails = userService.loadUserByUsername(registrationUserDto.getUsername());
        String jwtToken = jwtTokenUtils.generateToken(userDetails);

        Mockito.doReturn(ResponseEntity.ok(new JwtResponseDto(jwtToken)))
            .when(authService).createNewUser(registrationUserDto);
    }

    @Test
    void testCreateNewUserIfUserExists() {
        var registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setPassword("100");
        registrationUserDto.setUsername("user");
        registrationUserDto.setFirstName("user");
        registrationUserDto.setLastName("user");

        Mockito.doReturn(new ResponseEntity<>(
                new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь существует"),
                HttpStatus.BAD_REQUEST
            ))
            .when(authService).createNewUser(registrationUserDto);
    }
}
