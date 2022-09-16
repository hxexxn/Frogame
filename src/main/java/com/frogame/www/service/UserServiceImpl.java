package com.frogame.www.service;

import com.frogame.www.mapper.UserMapper;
import com.frogame.www.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void user_join(UserDTO userDTO) {
        userMapper.user_join(userDTO);
    }
}
