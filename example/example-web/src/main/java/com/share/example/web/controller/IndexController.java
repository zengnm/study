package com.share.example.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zengnianmei
 * @version 1.0, 2015/11/21.
 */
@Controller
public class IndexController {
   private static final Logger log = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "/")
    public String home(Model model){
        return "index";
    }
}
