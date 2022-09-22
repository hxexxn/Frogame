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
    UserService userService;


//    @GetMapping (value = "/test")
//    public String test(Model model) {
//        model.addAttribute("data", "타임리프 예제입니다.");
//        return "user/userTest";
//    }

    // <회원 가입> 페이지 이동
    @GetMapping (value = "/join")
    public String join() {
        return "user/user_join";
    }

    // <회원 가입>
    @PostMapping (value = "/join")
    public String user_join(UserDTO userDTO) {
        System.out.printf("회원 가입 시도하는 중");
        userService.user_join(userDTO);
        System.out.printf("회원 가입 완료");
        return "redirect:/";
    }

    // <회원 가입> - 유저 아이디 중복 확인 (ajax)
    @ResponseBody
    @PostMapping (value = "/userIdCheck")
    public int user_id_check(@RequestParam ("user_id") String user_id) {

        int id_count = userService.user_id_check(user_id);

        return id_count;
    }

    // <회원 가입> - 유저 닉네임 중복 확인 (ajax)
    @ResponseBody
    @PostMapping (value = "/userNickCheck")
    public int user_nick_check(@RequestParam ("user_nick") String user_nick) {

        int nick_count = userService.user_nick_check(user_nick);

        return nick_count;
    }

    // <회원 가입> - 유저 닉네임 중복 확인 (ajax)
    @ResponseBody
    @PostMapping (value = "/userEmailCheck")
    public int user_email_check(@RequestParam ("user_email") String user_email) {

        int email_count = userService.user_email_check(user_email);

        return email_count;
    }

    // <로그인> 페이지 이동
    @GetMapping (value = "/login")
    public String login() {
        return "user/user_login";
    }

    // <로그인>
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
            session.setAttribute("user_regdate", result_user.getUser_regdate());

            System.out.printf(result_user.getUser_nick());

            System.out.printf("로그인 성공 및 세션 등록 성공");

            return "redirect:/";

        } else {

            rttr.addAttribute("msg", false);
            System.out.printf("로그인 실패");

            return "user/login";
        }
    }

    // <로그아웃>
    @GetMapping (value = "/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();

        session.invalidate();

        System.out.println("로그아웃 완료");

        return "redirect:/";
    }

    // <회원 정보> 페이지 이동
    @GetMapping (value = "/account")
    public String user_account() {
        return "user/user_account";
    }

    // <회원 정보> 수정을 위한 비밀번호 입력 페이지 이동
    @GetMapping (value = "/account/password_security")
    public String user_modify() {
        return "user/user_password_security";
    }

    // <회원 정보> - 기존 비밀번호 DB 매칭 후 비밀번호 수정 페이지로 이동
    @PostMapping (value = "/account/password_update_page")
    public String user_input_password(UserDTO userDTO) {

        System.out.println("테스트 컨트롤러 시작");

        UserDTO user_input_pw = userService.userPasswordSecurity(userDTO);

        System.out.println("테스트 컨트롤러 끝");

        if (user_input_pw != null) {

            System.out.println("테스트 컨트롤러 성공해서 페이지 이동");
            return "user/user_account_update";

        } else {

            System.out.println("테스트 컨트롤러 실패");

        }

        return null;
    }

    // <회원 정보> - 새로운 비밀번호 입력 후 세션 새롭게 만들어서 회원 정보 페이지로 리다이렉트
    @PostMapping (value = "/account/password_update")
    public String user_update(@RequestParam("new_user_pw") String new_user_pw,
                              HttpServletRequest request,
                              Model model) {

        // 새로운 객체와 세션을 만들어서 비밀번호를 대입시킨 후 기존 유저의 아이디로 비밀번호를 찾은 후 기존 유저의 정보에 대입해주고, 세션도 새롭게 만든다.

        UserDTO user = new UserDTO();
        HttpSession session = request.getSession();

        String user_id = (String) session.getAttribute("user_id");

        if (user_id == null) {
            model.addAttribute("errorMsg", "비밀번호 변경 실패");
        } else {
            System.out.println("잘 실행됩니다그려.");
        }

        user.setUser_pw(new_user_pw);

        userService.user_password_update(new_user_pw, user_id);

        return "redirect:/";
    }
}
