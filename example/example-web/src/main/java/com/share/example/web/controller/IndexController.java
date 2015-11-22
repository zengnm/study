package com.share.example.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zengnianmei
 * @version 1.0, 2015/11/21.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
   private static final Logger log = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        Map<String, String> map = new HashMap();
        map.put("index", "index12345");
        model.addAllAttributes(map);
        log.info("====");
        return "index";
    }
}
