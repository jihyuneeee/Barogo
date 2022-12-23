package com.barogo.api.controller;

import java.util.List;
import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.barogo.api.domain.ResponseInfo;
import com.barogo.api.domain.TokeInfo;
import com.barogo.api.domain.UserInfo;
import com.barogo.api.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/auth/singup")
    @ResponseBody
    public ResponseEntity<ResponseInfo> singup(@RequestBody @Validated UserInfo userInfo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {

                ResponseInfo response = ResponseInfo.builder()
                        .response("fail").message(objectError.getDefaultMessage())
                        .build();
                // response.setResponse("fail");
                // response.setMessage(objectError.getDefaultMessage());
                return ResponseEntity.internalServerError().body(response);

            }
        }

        return ResponseEntity.ok().body(userService.singup(userInfo));
    }

    @PostMapping(value = "/auth/login")
    @ResponseBody
    public TokeInfo login(@RequestBody @Validated UserInfo userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("에러 있음");
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {
                System.out.println("error==" + objectError.getDefaultMessage());
            }

        }

        TokeInfo info = userService.login(userDTO);
        System.out.println("access : " + info.getGrantType());
        System.out.println("access : " + info.getAccessToken());
        System.out.println("access : " + info.getRefeshToken());
        return info;
    }

}
