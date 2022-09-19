package com.frogame.www.mapper;

import com.frogame.www.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void user_join(UserDTO userDTO);

    // 유저 비밀번호 찾기
    String user_find_pw(String user_id);

    // 유저 로그인
    UserDTO user_login(String user_id);

    // 회원 가입 - 유저 아이디 중복 확인
    int user_id_check(String user_id);

    // 회원 가입 - 유저 닉네임 중복 확인
    int user_nick_check(String user_nick);

    // 회원 가입 - 유저 이메일 중복 확인
    int user_email_check(String user_email);
}
