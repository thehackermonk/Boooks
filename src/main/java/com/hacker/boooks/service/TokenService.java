package com.hacker.boooks.service;

import org.springframework.stereotype.Service;

/**
 * @apiNote Service class for token generation
 */
@Service
public interface TokenService {

    /**
     * @apiNote Generate unique token
     * @author [@thehackermonk]
     * @since 1.0
     */
    String generateToken();

}
