package com.frogame.www.mapper;

import com.frogame.www.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void user_join(UserDTO userDTO);

}
