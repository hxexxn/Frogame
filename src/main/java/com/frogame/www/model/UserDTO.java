package com.frogame.www.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {

    private int user_no;

    private String user_id;

    private String user_pw;

    private String user_email;

    private String user_nick;

    private Timestamp user_regdate;

    private boolean user_level;
    
}
