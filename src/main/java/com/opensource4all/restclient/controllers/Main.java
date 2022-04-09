package com.opensource4all.restclient.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {

    @RequestMapping("/getJoke")
    public String getJoke(String firstName, String lastName) {
        return "na";
    }

    @RequestMapping("/")
    public String helloWorld() {
        return "hello World";
    }
}
