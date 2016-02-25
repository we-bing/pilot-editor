package com.github.webing.pilot.service;

import com.github.webing.pilot.model.User;

/**
 * Created by KD4 on 16. 2. 25..
 */
public interface WebingCoreService {
    User getUserByIdentity(String identity);

    User getUserByName(String name);

    void addUser(User user);

    User existUserByIdentity(String identity);

    void securityLogin(User user);
}
