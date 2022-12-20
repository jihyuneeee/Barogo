package com.barogo.api.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String singup(HashMap<String, Object> params) {

        // id / passwrord / name 유효성 체크
        // 1. 비번은 대문자, 소문자, 숫자 특수문자 중 3종류 이상
        // 2. 12자리 이상 문자열로 생성

        return null;
    }

}
