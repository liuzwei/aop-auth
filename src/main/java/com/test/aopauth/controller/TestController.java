package com.test.aopauth.controller;

import com.test.aopauth.aop.AuthChecker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @AuthChecker
    @RequestMapping("/test")
    public String test(){

        return "test ok";
    }
}
