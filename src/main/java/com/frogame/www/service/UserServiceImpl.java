package com.frogame.www.service;

import com.frogame.www.mapper.UserMapper;
import com.frogame.www.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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

    @Override
    public UserDTO user_login(UserDTO userDTO) {

        // 비밀번호 찾기
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
}
