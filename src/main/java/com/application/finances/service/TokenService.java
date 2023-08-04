package com.application.finances.service;

import com.application.finances.entity.Token;
import com.application.finances.entity.User;
import com.application.finances.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public void revokeUserToken(User user) {
        var validToken = tokenRepository.findValidTokenByUser(user.getId());

        if (validToken != null) {
            validToken.setExpired(true);
            validToken.setRevoked(true);
            tokenRepository.save(validToken);
        }
    }

    public boolean isTokenValid(String jwt) {
        return jwt == null || tokenRepository.findByToken(jwt)
            .map(t -> !t.isExpired() && !t.isRevoked())
            .orElse(false);
    }
}
