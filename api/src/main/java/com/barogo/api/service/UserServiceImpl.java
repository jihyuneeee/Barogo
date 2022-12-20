package com.barogo.api.service;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.barogo.api.model.UserDTO;

@Service
public class UserServiceImpl implements UserService {

    private static final String COMPLEX_PASSWORD_REGEX =
            "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|" +
            "(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})" +
            "[A-Za-z0-9!~<>,;:_=?*+#.\"&§%°()\\|\\[\\]\\-\\$\\^\\@\\/]" +
            "{8,32}$";
 
    private static final Pattern PASSWORD_PATTERN =
                                    Pattern.compile(COMPLEX_PASSWORD_REGEX);

    @Override
    public String singup() {

        // id / passwrord / name 유효성 체크
        // 1. 비번은 대문자, 소문자, 숫자 특수문자 중 3종류 이상
        // 2. 12자리 이상 문자열로 생성

         String password = "Stream@Java8";
 
        // 비밀번호 확인
        if (PASSWORD_PATTERN.matcher(password).matches()) {
            System.out.print("The Password " + password + " is valid");
        }
        else {
            System.out.print("The Password " + password + " isn't valid");
        }

        return null;
    }

}
