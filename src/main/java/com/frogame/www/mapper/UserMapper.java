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
}
