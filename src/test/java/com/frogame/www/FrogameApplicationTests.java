package com.frogame.www;

import com.frogame.www.model.UserDTO;
import com.frogame.www.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class FrogameApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

//	@Test
	@DisplayName("비밀번호 암호화 테스트")
	void passwordEncoderTest() {

		String original_pw = "khe20120438";
		String encode_pw = passwordEncoder.encode(original_pw);

		System.out.printf(encode_pw);
	}

//	@Test
	@DisplayName("회원가입 암호화 테스트")
	public void joinTest() {

		UserDTO user = new UserDTO();

		user.setUser_id("raccoon");
		user.setUser_pw("raccoon");
		user.setUser_nick("너구리");
		user.setUser_email("raccoon@naver.com");

		userService.user_join(user);
	}

//	@Test
//	@DisplayName("유저 로그인 테스트")
//	public void loginTest() {
//		return null;
//
//	}
}
