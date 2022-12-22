package com.barogo.api.service;

import java.util.HashMap;

import com.barogo.api.model.TokeInfo;
import com.barogo.api.model.UserDTO;

public interface UserService {

    String singup(UserDTO userDTO);

    TokeInfo login(UserDTO userDTO);

}
