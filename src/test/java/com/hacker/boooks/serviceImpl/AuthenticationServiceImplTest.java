package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.ResponseEntity;
import com.hacker.boooks.bean.Token;
import com.hacker.boooks.entity.LoginEntity;
import com.hacker.boooks.repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl underTest;
    @Mock
    private EncryptionServiceImpl encryptionService;
    @Mock
    private TokenServiceImpl tokenService;

    @Mock
    private LoginRepository loginRepository;

    @Test
    void loginSuccess() throws NoSuchAlgorithmException {

        // given
        String username = "admin";
        String password = "Admin@123";
        String encryptedPassword = "e86f78a8a3caf0b60d8e74e5942aa6d86dc150cd3c03338aef25b7d2d7e3acc7";

        LoginEntity dummyUser = new LoginEntity(username, encryptedPassword, null, null);

        when(loginRepository.getLoginByUsername(anyString())).thenReturn(dummyUser);
        when(encryptionService.encrypt(anyString())).thenReturn(encryptedPassword);
        when(tokenService.generateToken()).thenReturn(anyString());

        // when
        ResponseEntity<Token> response = underTest.login(username, password);

        // then
        assertThat(response.getHttpStatusCode()).isEqualTo(200);
        assertThat(response.getResponse()).isNotNull();

        verify(loginRepository).getLoginByUsername(username);

    }

    @Test
    void loginFail() throws NoSuchAlgorithmException {

        // given
        String username = "admin";
        String password = "Admin@123";
        String encryptedPassword = "e86f78a8a3caf0b60d8e74e5942aa6d86dc150cd3c03338aef25b7d2d7e3acc7";

        LoginEntity dummyUser = new LoginEntity(username, password, null, null);

        when(loginRepository.getLoginByUsername(anyString())).thenReturn(dummyUser);
        when(encryptionService.encrypt(anyString())).thenReturn(encryptedPassword);
        when(tokenService.generateToken()).thenReturn(anyString());

        // when
        ResponseEntity<Token> response = underTest.login(username, password);

        // then
        assertThat(response.getHttpStatusCode()).isEqualTo(401);

    }

    @Test
    void loginUserNotFound() throws NoSuchAlgorithmException {

        // when
        ResponseEntity<Token> response = underTest.login("Admin", "");

        // then
        assertThat(response.getHttpStatusCode()).isEqualTo(404);

    }

    @Test
    void logout() {

        // given
        String username = "admin";
        String token = "Token12345";

        LoginEntity dummyUser = new LoginEntity(username, null, token, null);

        when(loginRepository.getLoginByUsernameAndToken(anyString(), anyString())).thenReturn(dummyUser);

        //when
        Map<String, Boolean> response = underTest.logout(username, token);

        //then
        assertThat(response.get("result")).isEqualTo(true);

    }
}