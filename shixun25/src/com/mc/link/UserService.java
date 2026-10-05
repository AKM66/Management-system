package com.mc.link;

public class UserService {

    public void login(String username, String password) {
        return null;
    }

    public interface UserService {
        void register(User user);
        User login(String username, String password);
        void updatePassword(String email, String newPassword);
        void deleteUser(String username);
    }
}
