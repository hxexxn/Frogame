package com.frogame.www.service;

import com.frogame.www.model.UserDTO;

public interface UserService {
    void user_join(UserDTO userDTO);

    UserDTO user_login(UserDTO userDTO);
}
