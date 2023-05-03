package com.hacker.boooks.controller;

import com.hacker.boooks.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for user authentication
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * @param username User ID of the user
     * @param password Password of the user
     * @return Unique token and http status code (200) if authentication succeeds, and only http status code otherwise
     * @throws NoSuchAlgorithmException Exception is the encryption algorithm doesn't exist
     * @apiNote Login
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PostMapping("/login")
//    public ResponseEntity<Token> login(@RequestHeader String username, @RequestHeader String password) throws NoSuchAlgorithmException {
//        return authenticationService.login(username, password);
//    }

    /**
     * @param username    User ID of the user
     * @param accesstoken Access token provided to the user
     * @return true if logout succeeds and false otherwise
     * @apiNote Controller for logout
     * @author [@thehackermonk]
     */
//    @PostMapping("/logout")
//    public Map<String, Boolean> logout(@RequestHeader String username, @RequestHeader String accesstoken) {
//        return authenticationService.logout(username, accesstoken);
//    }

    /**
     * @param userID      username of the application
     * @param oldPassword existing password
     * @param newPassword new password
     * @return http response
     * @apiNote change password
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PutMapping("/change-password")
//    public int changePassword(@RequestHeader String userID, @RequestHeader String oldPassword, @RequestHeader String newPassword) throws NoSuchAlgorithmException {
//
//        ChangePasswordBO changePasswordBO = new ChangePasswordBO(userID, oldPassword, newPassword);
//        return authenticationService.changePassword(changePasswordBO);
//
//    }

}
