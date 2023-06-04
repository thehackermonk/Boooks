package com.hacker.boooks.service;

import com.hacker.boooks.bean.AccessTokenResponse;
import com.hacker.boooks.bean.AuthenticationResponse;
import com.hacker.boooks.bean.Librarian;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Authentication service.
 */
public interface AuthenticationService {

    ResponseEntity<AuthenticationResponse> register(String username, String password);

    ResponseEntity<AuthenticationResponse> login(String username, String password);
    ResponseEntity<AccessTokenResponse> generateAccessTokenFromRefreshToken(String username, String refreshToken);

    ResponseEntity<String> logout(String username);

    ResponseEntity<String> changePassword(String username, String oldPassword, String newPassword, String retypePassword);

    ResponseEntity<String> requestPasswordResetToken(String username);

    ResponseEntity<String> resetPassword(String username, String token, String newPassword);

    ResponseEntity<List<Librarian>> getAllLibrarians();

    ResponseEntity<List<String>> getInactiveLibrarians();

    ResponseEntity<String> activateLibrarian(String username, Boolean activate);

    ResponseEntity<String> deleteLibrarian(String username);

}
