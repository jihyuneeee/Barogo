package com.barogo.api.service;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.barogo.api.domain.ResponseInfo;
import com.barogo.api.domain.TokenInfo;
import com.barogo.api.domain.UserInfo;

public interface UserService {

    ResponseInfo singup(UserInfo userDTO);

    TokenInfo login(UserInfo userDTO);

}
