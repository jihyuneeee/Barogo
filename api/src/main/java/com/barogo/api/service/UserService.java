package com.barogo.api.service;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.barogo.api.domain.ResponseInfo;
import com.barogo.api.domain.TokeInfo;
import com.barogo.api.domain.UserInfo;

public interface UserService {

    ResponseInfo singup(UserInfo userDTO);

    TokeInfo login(UserInfo userDTO);

}
