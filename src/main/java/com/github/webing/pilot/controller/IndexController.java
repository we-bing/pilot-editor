package com.github.webing.pilot.controller;

import com.sun.javafx.sg.prism.NGShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by KD4 on 16. 2. 25..
 */

@Controller
@RequestMapping("/")
public class IndexController {


    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String root(HttpServletRequest request) {
        logger.debug("a user access the server : " + request.getRemoteHost());
        return "redirect:/editors";
    }


    @RequestMapping(
            value = "signup",
            method = RequestMethod.GET)
    public String viewSignUp() {
        //todo:세션 로그인 확인
        return "signUp";
    }


    @RequestMapping(
            value = "signin",
            method = RequestMethod.GET)
    public ModelAndView showSignInPage(Model model, @RequestParam(value = "error", required = false, defaultValue = "") String error) {
        ModelAndView mov = new ModelAndView("signin");
        mov.addObject("error", error);
        return mov;
    }


    @RequestMapping(value = "signOut", method = RequestMethod.GET)
    public String processSignOut(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.debug(auth.getName() + " sign out.");
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/signin";
    }
}
