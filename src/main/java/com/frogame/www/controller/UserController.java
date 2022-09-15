package com.frogame.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (value = "/user")
public class UserController {

    @GetMapping (value = "/test")
    public String test(Model model) {
        model.addAttribute("data", "타임리프 예제입니다.");
        return "user/userTest";
    }

    @GetMapping (value = "login")
    public String user_login() {
        return "user/user_login";
    }

    @GetMapping (value = "join")
    public String user_join() {
        return "user/user_join";
    }
}
