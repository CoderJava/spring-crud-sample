package com.ysn.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String home() {
        return "Hello world!. I'm learning Spring Framework for REST API";
    }

}
