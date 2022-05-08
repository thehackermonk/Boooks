package com.hacker.boooks.bo;

public class ChangePasswordBO {

    private String userID;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordBO() {
    }

    public ChangePasswordBO(String userID, String oldPassword, String newPassword) {
        this.userID = userID;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
