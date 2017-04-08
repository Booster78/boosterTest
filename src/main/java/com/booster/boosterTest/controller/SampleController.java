package com.booster.boosterTest.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @GetMapping("/test")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void mainback(String[] args) throws Exception {
        SpringApplication.run(new Object[]{SampleController.class, SwaggerConfig.class}, args);
    }
}