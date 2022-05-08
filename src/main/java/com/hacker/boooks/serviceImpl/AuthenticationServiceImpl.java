package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.ResponseEntity;
import com.hacker.boooks.bean.Token;
import com.hacker.boooks.constant.HTTPResponseCode;
import com.hacker.boooks.constant.Response;
import com.hacker.boooks.entity.LoginEntity;
import com.hacker.boooks.repository.LoginRepository;
import com.hacker.boooks.service.AuthenticationService;
import com.hacker.boooks.service.EncryptionService;
import com.hacker.boooks.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Service
@SuppressWarnings("unused")
public class AuthenticationServiceImpl implements AuthenticationService {

    Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository loginRepository;

    /**
     * @param username User ID of the user
     * @param password Password of the user
     * @return A unique access token if successful and a http status code
     * @throws NoSuchAlgorithmException Exception is the encryption algorithm doesn't exist
     * @apiNote Generates and stores access token in the DB
     * @author [@thehackermonk]
     * @see EncryptionService For encrypting the password
     * @see TokenService To generate access token
     * @since 1.0
     */
    @Override
    public ResponseEntity<Token> login(String username, String password) throws NoSuchAlgorithmException {

        LoginEntity loginEntity = loginRepository.getLoginByUsername(username);
        ResponseEntity<Token> responseEntity = new ResponseEntity<>();
        Token accessToken = new Token();

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime incorrectPasswordTime;

        if (loginEntity == null) {

            log.info(Response.USER_NOT_FOUND);
            responseEntity.setHttpStatusCode(HTTPResponseCode.USER_NOT_FOUND);

        } else {

            if (encryptionService.encrypt(password).equals(loginEntity.getPassword())) {

                log.info(username + " logged in successfully.");

                accessToken.setToken(tokenService.generateToken());
                accessToken.setGenerationTime(currentDateTime);

                loginEntity.setToken(accessToken.getToken());
                loginEntity.setTokenCreationTime(java.sql.Timestamp.valueOf(currentDateTime));

                loginRepository.save(loginEntity);

                responseEntity.setResponse(accessToken);
                responseEntity.setHttpStatusCode(HTTPResponseCode.SUCCESS);

            } else {

                log.info("Login failed for " + username);

                responseEntity.setHttpStatusCode(HTTPResponseCode.UNAUTHORIZED_ACCESS);

            }

        }

        return responseEntity;
    }

    /**
     * @param username    User ID of the user
     * @param accessToken Access token provided to the user
     * @return True is logout succeeds and false otherwise
     * @apiNote Deletes the access token from DB
     * @author [@thehackermonk]
     * @since 1.0
     */
    public Map<String, Boolean> logout(String username, String accessToken) {

        LoginEntity loginEntity = loginRepository.getLoginByUsernameAndToken(username, accessToken);

        loginEntity.setToken(null);
        loginEntity.setTokenCreationTime(null);

        loginRepository.save(loginEntity);

        log.info(username + " logged out successfully!");

        return Collections.singletonMap("result", true);
    }

}
