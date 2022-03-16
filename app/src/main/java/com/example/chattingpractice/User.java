package com.example.chattingpractice;

public class User {
    private String name;
    private String email;
    private String userID;
    private String profileImageURL;
    private int avatarMockUpResource;

    public User() {
    }

    public User(String name, String email, String userID, String profileImageURL, int avatarMockUpResource) {
        this.name = name;
        this.email = email;
        this.userID = userID;
        this.profileImageURL = profileImageURL;
        this.avatarMockUpResource = avatarMockUpResource;
    }

    public String getName() {
        return name;
    }

    public int getAvatarMockUpResource() {
        return avatarMockUpResource;
    }

    public void setAvatarMockUpResource(int avatarMockUpResource) {
        this.avatarMockUpResource = avatarMockUpResource;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
