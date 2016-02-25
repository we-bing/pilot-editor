package com.github.webing.pilot.controller;

import com.github.webing.pilot.exception.InvalidUserException;
import com.github.webing.pilot.model.User;
import com.github.webing.pilot.service.WebingCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    @Autowired
    WebingCoreService webingCoreService;

    @ResponseBody
    @RequestMapping(
            headers = "Accept=application/json",
            produces = "application/json;charset=utf8",
            method = RequestMethod.POST)
    public List<String> signUp(User user) {

        List<String> results = new ArrayList<String>();
        //검색된 결과가 없어서 예외가 발생하는 것이 정상.
        try {
            if (webingCoreService.getUserByIdentity(user.getIdentity()) != null) {
                results.add("이미 등록된 이메일입니다. 다른 이메일을 사용해주세요.");
            }
            return results;
        } catch (InvalidUserException e) {
        }

        try {
            if (webingCoreService.getUserByName(user.getName()) != null) {
                results.add("이미 등록된 이름입니다. 다른 이름을 사용해주세요.");
            }
            return results;
        } catch (InvalidUserException e) {
        }

        webingCoreService.addUser(user);

        //results 첫번째 인덱스는 작업결과 (success or error msg) 두번째 인덱스는 등록에 성공한 유저 아이디.
        //results 값으로 프론트에서 Tenth2 업로드 요청을 함. - img 업로드 API 와 회원등록 API 분리.
        results.add("success");
        results.add(String.valueOf(user.getId()));


        logger.debug(user.getId() + " sign up ! hello " + user.getName());

        return results;
    }

}
