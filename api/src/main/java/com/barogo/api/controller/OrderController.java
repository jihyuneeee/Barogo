package com.barogo.api.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.barogo.api.model.OrderInfo;
import com.barogo.api.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/order/list")
    @ResponseBody
    public List<OrderInfo> orderList(HttpServletRequest request, @RequestBody HashMap<String, Object> params) {
        System.out.println("###################");
        System.out.println("map :" + params.get("startDate"));

        if (params.get("startDate") == null || params.get("endDate") == null) {
            System.out.println("날짜 다시 입력");
        }

        return orderService.orderList(params);
    }

    @PostMapping(value = "/order/change")
    @ResponseBody
    public void orderChange(HttpServletRequest request, @RequestBody HashMap<String, Object> params) {
        System.out.println("###################");

        // orderService.orderList(params);
        // order_id / address null check

        orderService.orderChange(params);
    }
}
