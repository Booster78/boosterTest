package com.booster.boosterTest.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

   /* @GetMapping("/test")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
*/
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
     //   SpringApplication.run(new Object[]{Main.class, SwaggerConfig.class}, args);
    }
}