package com.dims.controller;

import com.dims.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final MyService myService;

    public TestController(MyService myService) {
        this.myService = myService;
    }

    //Testing Logs
    @GetMapping("/test")
    public String testLogging() {
        myService.processData();
        return "Check logs!";
    }
}
