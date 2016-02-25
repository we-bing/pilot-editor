package com.github.webing.pilot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by KD4 on 16. 2. 25..
 */

@Controller
@RequestMapping("/editors")
public class EditorController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showEditor(HttpServletRequest request) {
        return new ModelAndView("editors");
    }
}
