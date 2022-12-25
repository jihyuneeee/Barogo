package com.barogo.api.service;

import com.barogo.api.domain.ResponseInfo;
import com.barogo.api.domain.TokenInfo;
import com.barogo.api.domain.UserInfo;

public interface UserService {

    ResponseInfo singup(UserInfo userDTO);

    TokenInfo login(UserInfo userDTO);

}
