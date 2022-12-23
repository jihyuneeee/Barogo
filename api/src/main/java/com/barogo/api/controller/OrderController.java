package com.barogo.api.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.barogo.api.domain.OrderCheck;
import com.barogo.api.domain.OrderInfo;
import com.barogo.api.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/order/list")
    @ResponseBody
    public ResponseEntity<List<OrderInfo>> orderList(@RequestBody @Validated OrderCheck orderCheck,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {

                // ResponseInfo response = ResponseInfo.builder()
                // .response("fail")
                // .message(objectError.getDefaultMessage())
                // .build();
                // return ResponseEntity.internalServerError().body(response);
                // return ResponseEntity.internalServerError();
            }
        }
        // if (params.get("startDate") == null || params.get("endDate") == null) {
        // System.out.println("날짜 다시 입력");
        // }

        return ResponseEntity.ok().body(orderService.orderList(orderCheck));
    }

    @PostMapping(value = "/order/change")
    @ResponseBody
    public void orderChange(HttpServletRequest request, @RequestBody HashMap<String, Object> params) {
        System.out.println("###################");
        System.out.println("params :" + params);

        // orderService.orderList(params);
        // order_id / address null check

        orderService.orderChange(params);
    }
}
