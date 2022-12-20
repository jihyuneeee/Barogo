package com.barogo.api.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.barogo.api.model.UserDTO;
import com.barogo.api.service.UserService;

@Controller
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/user/singup")
    public String singup(
            @RequestBody @Validated UserDTO userDTO, BindingResult bindingResult,
            HttpServletRequest req, HashMap<String, Object> params) {
        System.out.println("singup");

        System.out.println("params : " + userDTO.getId());
        System.out.println("params : " + userDTO.getName());
        System.out.println("에러 존재? : " + bindingResult.hasErrors());

        if (bindingResult.hasErrors()) {

            System.out.println("에러 존재" + bindingResult);

            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {
                System.out.println("error==" + objectError.getDefaultMessage());
            }

        }

        return null;

        // return userService.singup(params);
    }
}
