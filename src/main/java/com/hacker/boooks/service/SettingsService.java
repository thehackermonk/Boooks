package com.hacker.boooks.service;

import com.hacker.boooks.bo.ChangePasswordBO;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * @apiNote Service class for settings
 */
@Service
public interface SettingsService {

    /**
     * @apiNote To change password
     * @author [@thehackermonk]
     * @since 1.0
     */
    int changePassword(ChangePasswordBO changePasswordBO) throws NoSuchAlgorithmException;

}
