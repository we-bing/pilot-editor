package com.github.webing.pilot.service;

import com.github.webing.pilot.exception.InvalidUserException;
import com.github.webing.pilot.model.User;
import com.github.webing.pilot.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KD4 on 16. 2. 25..
 */

@Service
public class WebingCoreServiceImpl implements WebingCoreService {

    @Autowired
    UserService userService;

    @Override
    public User getUserByIdentity(String identity) {
        User selectedUser = userService.getUserByIdentity(identity);
        if (selectedUser == null) {
            throw new InvalidUserException("등록된 유저 정보가 없거나 탈퇴한 회원입니다.");
        }
        return selectedUser;
    }

    @Override
    public User getUserByName(String name) {
        User selectedUser = userService.getUserByName(name);
        if (selectedUser == null) {
            throw new InvalidUserException();
        }
        return selectedUser;
    }

    @Override
    public void addUser(User user) {
        if (user.getIsOAuth().equals("F")) {
            userService.insertNormalUser(user);
        } else {
            userService.insertOAuthUser(user);
        }
    }

    @Override
    public User existUserByIdentity(String identity) {
        return userService.getUserByIdentity(identity);
    }

    @Override
    public void securityLogin(User user) {
        SecurityUtil.logInUser(user);
    }
}
