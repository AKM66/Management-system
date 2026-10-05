package com.mc.link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
public class UserServiceImpl {

    public UserServiceImpl(UserMapper) {
    }

    @Service
    public class UserServiceImpl implements UserService {
        private final UserMapper userMapper;

        @Autowired
        public UserServiceImpl(UserMapper userMapper) {
            this.userMapper = userMapper;
        }

        @Override
        public void register(User user) {
            userMapper.register(user);
        }

        @Override
        public User login(String username, String password) {
            User user = userMapper.findByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            return null;
        }

        @Override
        public void updatePassword(String email, String newPassword) {
            userMapper.updatePassword(email, newPassword);
        }

        @Override
        public void deleteUser(String username) {
            userMapper.deleteByUsername(username);
        }
    }
}
