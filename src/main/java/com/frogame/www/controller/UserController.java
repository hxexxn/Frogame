package com.frogame.www.controller;

import com.frogame.www.model.UserDTO;
import com.frogame.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String user_login(UserDTO userDTO, HttpServletRequest request, RedirectAttributes rttr) {
        UserDTO result_user = userService.user_login(userDTO);

        System.out.printf("result_user 값 : ", result_user.toString());

        if (userDTO != null) {

            HttpSession session = request.getSession();

            session.setAttribute("user_no", result_user.getUser_no());
            session.setAttribute("user_id", result_user.getUser_id());
            session.setAttribute("user_nick", result_user.getUser_nick());
            session.setAttribute("user_email", result_user.getUser_email());

            System.out.printf(result_user.getUser_nick());

            System.out.printf("로그인 성공 및 세션 등록 성공");

            return "redirect:/";

        } else {

            rttr.addAttribute("msg", false);
            System.out.printf("로그인 실패");

            return "user/login";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();

        session.invalidate();

        System.out.println("로그아웃 완료");

        return "redirect:/";
    }

    // 회원 가입 페이지 이동
    @GetMapping (value = "/join")
    public String join() {
        return "user/user_join";
    }

    // 회원 가입
    @PostMapping (value = "/join")
    public String user_join(UserDTO userDTO) {
        System.out.printf("회원 가입 시도하는 중");
        userService.user_join(userDTO);
        System.out.printf("회원 가입 완료");
        return "redirect:/";
    }

    // 회원 가입 - 유저 아이디 중복 확인
    @ResponseBody
    @PostMapping (value = "/userIdCheck")
    public int user_id_check(@RequestParam ("user_id") String user_id) {

        int id_count = userService.user_id_check(user_id);

        return id_count;
    }

    // 회원 가입 - 유저 닉네임 중복 확인
    @ResponseBody
    @PostMapping (value = "/userNickCheck")
    public int user_nick_check(@RequestParam ("user_nick") String user_nick) {

        int nick_count = userService.user_nick_check(user_nick);

        return nick_count;
    }

    // 회원 가입 - 유저 닉네임 중복 확인
    @ResponseBody
    @PostMapping (value = "/userEmailCheck")
    public int user_email_check(@RequestParam ("user_email") String user_email) {

        int email_count = userService.user_email_check(user_email);

        return email_count;
    }
}
