package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bo.ChangePasswordBO;
import com.hacker.boooks.constant.HTTPResponseCode;
import com.hacker.boooks.constant.Response;
import com.hacker.boooks.entity.LoginEntity;
import com.hacker.boooks.repository.LoginRepository;
import com.hacker.boooks.service.EncryptionService;
import com.hacker.boooks.service.SettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@SuppressWarnings("unused")
public class SettingsServiceImpl implements SettingsService {

    Logger log = LoggerFactory.getLogger(SettingsServiceImpl.class);

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private EncryptionService encryptionService;

    /**
     * @param changePasswordBO User ID, old password, new password
     * @return http response status
     * @apiNote To change password
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public int changePassword(ChangePasswordBO changePasswordBO) throws NoSuchAlgorithmException {

        LoginEntity loginEntity = loginRepository.getById(changePasswordBO.getUserID());

        String oldPassword = encryptionService.encrypt(changePasswordBO.getOldPassword());
        String newPassword = encryptionService.encrypt(changePasswordBO.getNewPassword());

        if (oldPassword.equals(loginEntity.getPassword())) {

            loginEntity.setPassword(newPassword);
            loginRepository.save(loginEntity);

            log.info(Response.PASSWORD_UPDATED_SUCCESSFULLY);

            return HTTPResponseCode.SUCCESS;

        } else {
            return HTTPResponseCode.UNAUTHORIZED_ACCESS;
        }

    }

}
