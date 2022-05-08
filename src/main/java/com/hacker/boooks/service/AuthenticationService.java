package com.hacker.boooks.service;

import com.hacker.boooks.bean.ResponseEntity;
import com.hacker.boooks.bean.Token;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @apiNote Service class for user authentication
 */
@Service
public interface AuthenticationService {

    /**
     * @apiNote Generates and stores access token in the DB
     * @author [@thehackermonk]
     * @since 1.0
     */
    ResponseEntity<Token> login(String username, String password) throws NoSuchAlgorithmException;

    /**
     * @apiNote Deletes the access token from DB
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> logout(String username, String accessToken);

}
