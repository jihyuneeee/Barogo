package com.barogo.api.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.barogo.api.domain.ResponseInfo;
import com.barogo.api.domain.TokenInfo;
import com.barogo.api.domain.UserInfo;
import com.barogo.api.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/auth/signup")
    @ResponseBody
    public ResponseEntity<ResponseInfo> singup(@RequestBody @Validated UserInfo userInfo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {

                ResponseInfo response = ResponseInfo.builder()
                        .status("fail")
                        .message(objectError.getDefaultMessage())
                        .build();
                return ResponseEntity.internalServerError().body(response);

            }
        }

        if (userInfo.getName().isEmpty()) {

            ResponseInfo response = ResponseInfo.builder()
                    .status("fail")
                    .message("name is a required value.")
                    .build();

            return ResponseEntity.internalServerError().body(response);
        }

        return ResponseEntity.ok().body(userService.singup(userInfo));
    }

    @PostMapping(value = "/auth/login")
    @ResponseBody
    public ResponseEntity<TokenInfo> login(@RequestBody @Validated UserInfo userInfo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {

                TokenInfo response = TokenInfo.builder()
                        .status("fail")
                        .message(objectError.getDefaultMessage())
                        .build();

                return ResponseEntity.internalServerError().body(response);
            }
        }

        return ResponseEntity.ok().body(userService.login(userInfo));
    }

}
