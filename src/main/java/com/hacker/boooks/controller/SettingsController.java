package com.hacker.boooks.controller;

import com.hacker.boooks.bo.ChangePasswordBO;
import com.hacker.boooks.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for settings
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks/settings")
@SuppressWarnings("unused")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    /**
     * @param userID      username of the application
     * @param oldPassword existing password
     * @param newPassword new password
     * @return http response
     * @apiNote change password
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/changepassword")
    @ResponseBody
    public int changePassword(@RequestHeader String userID, @RequestHeader String oldPassword, @RequestHeader String newPassword) throws NoSuchAlgorithmException {

        ChangePasswordBO changePasswordBO = new ChangePasswordBO(userID, oldPassword, newPassword);

        return settingsService.changePassword(changePasswordBO);

    }

}
