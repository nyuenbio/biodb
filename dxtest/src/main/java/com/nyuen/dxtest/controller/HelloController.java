package com.nyuen.dxtest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class HelloController {

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    @ResponseBody
    public String say(){
        return "hello springboot";
    }
}
