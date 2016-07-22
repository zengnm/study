package com.share.example.service.example.dubbo.impl;

import com.share.example.service.example.dubbo.DemoService;

/**
 * @author zengnianmei
 * @version 1.0, 2016/7/22.
 */
public class DemoServiceImpl implements DemoService {
    public String say(int i) {
        switch (i){
            case 0:
                return "hello world!";
            default:
                return i + "hello world!";
        }
    }
}
