package com.frogame.www.service;

import com.frogame.www.model.UserDTO;

public interface UserService {

    // 유저 회원 가입
    void user_join(UserDTO userDTO);

    // 유저 로그인 및 비밀번호 일치화
    UserDTO user_login(UserDTO userDTO);

    // 회원 가입 - 유저 아이디 중복 확인
    int user_id_check(String user_id);

    // 회원 가입 - 유저 닉네임 중복 확인
    int user_nick_check(String user_nick);

    // 회원 가입 - 유저 이메일 중복 확인
    int user_email_check(String user_email);

    // 회원 정보 수정 - 비밀번호 확인
    UserDTO userPasswordSecurity(UserDTO userDTO);

    // 회원 정보 수정 - 비밀번호 변경
    void user_password_update(String new_user_pw, String user_id);
}
