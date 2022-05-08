package com.hacker.boooks.controller;

import com.hacker.boooks.bean.ResponseEntity;
import com.hacker.boooks.bean.Token;
import com.hacker.boooks.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for user authentication
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks")
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
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Token> login(@RequestHeader String username, @RequestHeader String password) throws NoSuchAlgorithmException {
        return authenticationService.login(username, password);
    }

    /**
     * @param username    User ID of the user
     * @param accesstoken Access token provided to the user
     * @return true if logout succeeds and false otherwise
     * @apiNote Controller for logout
     * @author [@thehackermonk]
     */
    @PostMapping("/logout")
    @ResponseBody
    public Map<String, Boolean> logout(@RequestHeader String username, @RequestHeader String accesstoken) {
        return authenticationService.logout(username, accesstoken);
    }

}
