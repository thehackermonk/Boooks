package com.hacker.boooks.service;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * @apiNote Service class for password encryption
 */
@Service
public interface EncryptionService {

    /**
     * @apiNote Get SHA-256 encryption of string
     * @author [@thehackermonk]
     * @since 1.0
     */
    String encrypt(String password) throws NoSuchAlgorithmException;

}
