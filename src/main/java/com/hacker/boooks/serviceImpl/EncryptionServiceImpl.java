package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.constant.Response;
import com.hacker.boooks.service.EncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@SuppressWarnings("unused")
public class EncryptionServiceImpl implements EncryptionService {

    Logger log = LoggerFactory.getLogger(EncryptionServiceImpl.class);

    /**
     * @param input: String to encrypt
     * @return array of byte
     * @throws NoSuchAlgorithmException Exception is the encryption algorithm doesn't exist
     * @apiNote To get SHA-256 encryption
     * @author [@thehackermonk]
     * @since 1.0
     */
    private byte[] getSHA(String input) throws NoSuchAlgorithmException {

        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest method is called to calculate message digest of an input
        return md.digest(input.getBytes(StandardCharsets.UTF_8));

    }

    /**
     * @param hash byte array
     * @return hex value
     * @apiNote To convert byte array to hex value
     * @author [@thehackermonk]
     * @since 1.0
     */
    private String toHexString(byte[] hash) {

        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
            hexString.insert(0, '0');

        return hexString.toString();

    }

    /**
     * @param password Password to be encrypted
     * @return Encrypted password
     * @throws NoSuchAlgorithmException Exception is the encryption algorithm doesn't exist
     * @apiNote Get SHA-256 encryption of string
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public String encrypt(String password) throws NoSuchAlgorithmException {

        log.info(Response.PASSWORD_ENCRYPTED);

        return toHexString(getSHA(password));

    }

}
