package com.frogame.www.controller;

import com.frogame.www.model.UserDTO;
import com.frogame.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping (value = "/test")
    public String test(Model model) {
        model.addAttribute("data", "타임리프 예제입니다.");
        return "user/userTest";
    }

    // 로그인 페이지 이동
    @GetMapping (value = "/login")
    public String login() {
        return "user/user_login";
    }

    // 로그인
    @PostMapping (value = "/login")
    public void user_login(UserDTO userDTO) {

    }

    // 회원가입 페이지 이동
    @GetMapping (value = "/join")
    public String join() {
        return "user/user_join";
    }

    // 회원가입
    @PostMapping (value = "/join")
    public String user_join(UserDTO userDTO) {
        System.out.printf("회원 가입 시도하는 중");
        userService.user_join(userDTO);
        System.out.printf("회원 가입 완료");
        return "redirect:/";
    }
}
