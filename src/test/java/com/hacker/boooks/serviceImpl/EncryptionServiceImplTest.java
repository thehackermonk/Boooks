package com.hacker.boooks.serviceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EncryptionServiceImplTest {

    @InjectMocks
    private EncryptionServiceImpl underTest;

    @Test
    void encrypt() throws NoSuchAlgorithmException {

        // when
        String encryption = underTest.encrypt("password");

        // then
        assertThat(encryption).isNotBlank();

    }
}