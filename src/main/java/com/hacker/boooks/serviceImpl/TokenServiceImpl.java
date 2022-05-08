package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.constant.Response;
import com.hacker.boooks.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@SuppressWarnings("unused")
public class TokenServiceImpl implements TokenService {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);

    /**
     * @return Unique access token
     * @apiNote Generate unique token
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public String generateToken() {

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);

        log.info(Response.TOKEN_GENERATED);

        return base64Encoder.encodeToString(randomBytes);

    }
}
