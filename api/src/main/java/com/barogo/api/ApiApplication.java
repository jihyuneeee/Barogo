package com.barogo.api;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.barogo.api.service.UserService;

@SpringBootApplication
public class ApiApplication {

	
	public static void main(String[] args) {
		
		 String COMPLEX_PASSWORD_REGEX =
            "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|" +
            "(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})" +
            "[A-Za-z0-9!~<>,;:_=?*+#.\"&§%°()\\|\\[\\]\\-\\$\\^\\@\\/]" +
            "{12,}$";
 
    Pattern PASSWORD_PATTERN = Pattern.compile(COMPLEX_PASSWORD_REGEX);

	String password = "StreamJava@!";
 
	// 비밀번호 확인
	if (PASSWORD_PATTERN.matcher(password).matches()) {
		System.out.print("The Password " + password + " is valid");
	}
	else {
		System.out.print("The Password " + password + " isn't valid");
	}

		SpringApplication.run(ApiApplication.class, args);
		
	}

}
