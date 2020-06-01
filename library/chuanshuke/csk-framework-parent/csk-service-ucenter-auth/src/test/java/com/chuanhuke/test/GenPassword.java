package com.chuanhuke.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenPassword {

	public static void main(String[] args) {
		String password = "CskWebApp";
		String encode = new BCryptPasswordEncoder().encode(password);
		System.out.println(encode);

	}

}
