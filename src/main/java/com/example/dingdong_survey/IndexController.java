package com.example.dingdong_survey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String IndexController()  {
        return "ok";
    }
}
