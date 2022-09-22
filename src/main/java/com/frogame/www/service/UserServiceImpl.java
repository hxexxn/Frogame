package com.frogame.www.service;

import com.frogame.www.mapper.UserMapper;
import com.frogame.www.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 회원 가입
    @Override
    public void user_join(UserDTO userDTO) {

        System.out.printf("비밀번호 암호화 중");

        userDTO.setUser_pw(passwordEncoder.encode(userDTO.getUser_pw()));
        userMapper.user_join(userDTO);

        System.out.printf("비밀번호 암호화 및 회원가입 완료");
    }

    // 로그인 - 아이디와 일치하는 비밀번호 찾기
    @Override
    public UserDTO user_login(UserDTO userDTO) {


        String pw_result = userMapper.user_find_pw(userDTO.getUser_id());
        boolean pw_filter = passwordEncoder.matches(userDTO.getUser_pw(), pw_result);

        System.out.printf(String.valueOf(pw_filter));
        System.out.printf("pw_result 값 : " + pw_result);

        if (pw_filter) {

            UserDTO result_user = userMapper.user_login(userDTO.getUser_id());

            return result_user;
        } else {
            return null;
        }

    }

    // 회원 가입 - 유저 아이디 중복 확인
    @Override
    public int user_id_check(String user_id) {
        int id_count = userMapper.user_id_check(user_id);
        return id_count;
    }

    // 회원 가입 - 유저 닉네임 중복 확인
    @Override
    public int user_nick_check(String user_nick) {
        int nick_count = userMapper.user_nick_check(user_nick);
        return nick_count;
    }

    // 회원 가입 - 유저 이메일 중복 확인
    @Override
    public int user_email_check(String user_email) {
        int email_count = userMapper.user_email_check(user_email);
        return email_count;
    }

    // 회원 정보 수정 - 개인 정보 보호를 위한 비밀번호 재입력
    @Override
    public UserDTO userPasswordSecurity(UserDTO userDTO) {
        System.out.println("테스트 Impl 이동");

        String pw_result = userMapper.user_find_pw(userDTO.getUser_id());

        System.out.printf("pw_result 값 : " + pw_result);
        System.out.println("테스트 Impl 이동 111111");


        boolean pw_filter = passwordEncoder.matches(userDTO.getUser_pw(), pw_result);

        System.out.println("테스트 Impl 이동 222222");

        if (pw_filter) {
            System.out.println("테스트 Impl 이동 333333");
            UserDTO user_input_pw = userMapper.user_login(userDTO.getUser_id());
            System.out.println("테스트 Impl 이동 444444 성공");
            return user_input_pw;
        } else {
            System.out.println("테스트 Impl 이동 실패");
            System.out.println("실패");
        }


        return null;
    }

    // 회원 정보 수정 - 비밀번호 변경
    @Override
    public void user_password_update(String new_user_pw, String user_id) {

    }
}
