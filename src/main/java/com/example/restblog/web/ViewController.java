package com.example.restblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {

    @RequestMapping({"/", "/about", "/posts", "/login", "/home"})
    @ResponseBody
    public String showView() {
        return "forward:/index.html";
    }
}
