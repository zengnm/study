package com.share.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zengnianmei
 * @version 1.0, 2015/11/21.
 */
@Controller
@RequestMapping(value = "/")
public class LoginCotroller {
   // private static final Logger log = Logger.getLogger(LoginCotroller.class);

    @ResponseBody
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request){
        //todo
        return "success";
    }
}
