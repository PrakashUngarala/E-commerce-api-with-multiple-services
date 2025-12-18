package com.nit;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestingLogics {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd1 = encoder.encode("raja");
		String pwd2 = encoder.encode("prakash");
		System.out.println(pwd1);
		System.out.println(pwd2);
	}
}
