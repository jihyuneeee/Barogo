package com.barogo.api.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.barogo.api.model.TokeInfo;
import com.barogo.api.model.UserDTO;
import com.barogo.api.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/auth/singup")
    public void singup(@RequestBody @Validated UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("에러 있음");
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {
                System.out.println("error==" + objectError.getDefaultMessage());
            }

        }

        userService.singup(userDTO);

    }

    @PostMapping(value = "/auth/login")
    @ResponseBody
    public TokeInfo login(@RequestBody @Validated UserDTO userDTO, BindingResult bindingResult) {

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
