package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bo.ChangePasswordBO;
import com.hacker.boooks.entity.LoginEntity;
import com.hacker.boooks.repository.LoginRepository;
import com.hacker.boooks.service.EncryptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SettingsServiceImplTest {

    @InjectMocks
    private SettingsServiceImpl underTest;

    @Mock
    private LoginRepository loginRepository;
    @Mock
    private EncryptionService encryptionService;

    @Test
    void changePassword() throws NoSuchAlgorithmException {

        // given
        LocalDate localDate = LocalDate.now();
        Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
        LoginEntity loginEntity = new LoginEntity("username", "password", "token", timestamp);
        ChangePasswordBO changePasswordBO = new ChangePasswordBO("username", "oldpassword", "newpassword");

        when(loginRepository.getById(anyString())).thenReturn(loginEntity);
        when(encryptionService.encrypt(anyString())).thenReturn("password");

        // when
        int response = underTest.changePassword(changePasswordBO);

        // then
        assertThat(response).isEqualTo(200);

    }
}