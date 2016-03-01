package com.github.webing.pilot.security;


import com.github.webing.pilot.exception.InvalidUserException;
import com.github.webing.pilot.model.User;
import com.github.webing.pilot.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by KD4 on 16. 2. 25.
 */
public class DefaultJdbcDaoImpl implements UserDetailsService {

    private static final Logger logger =
            LoggerFactory.getLogger(DefaultJdbcDaoImpl.class);

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public UserDetails loadUserByUsername(String identity)
            throws UsernameNotFoundException {
        System.out.println(sqlSession);
        User user = sqlSession.getMapper(UserRepository.class).findUserByIdentity(identity);
        if (user == null) {
            throw new InvalidUserException("가입된 유저가 아닙니다. 회원가입을 해주세요.");
        }
        if (user.getIsOAuth().equals("T")) {
            throw new InvalidUserException("소셜 가입 유저입니다. 소셜 로그인을 이용해주세요.");
        }
        String password = user.getPassword();
        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(user.getRoles()));
        UserDetails userDetail = new org.springframework.security.core.userdetails.User(identity, password, roles);

        logger.debug(identity + " sign in...");
        return userDetail;
    }
}