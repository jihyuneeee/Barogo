package com.barogo.api.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.barogo.api.domain.OrderCheckInfo;
import com.barogo.api.domain.OrderInfo;
import com.barogo.api.domain.ResponseInfo;
import com.barogo.api.service.OrderService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/order/list")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> orderList(@RequestBody @Validated OrderCheckInfo orderCheck,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {

                HashMap<String, Object> result = new HashMap<>();

                ResponseInfo response = ResponseInfo.builder()
                        .status("fail")
                        .message(objectError.getDefaultMessage())
                        .build();

                result.put("response", response);

                return ResponseEntity.internalServerError().body(result);

            }
        }

        return ResponseEntity.ok().body(orderService.orderList(orderCheck));
    }

    @PostMapping(value = "/order/change")
    @ResponseBody
    public ResponseEntity<ResponseInfo> orderChange(@RequestBody @Validated OrderInfo orderInfo,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("에러있음음");
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {

                ResponseInfo response = ResponseInfo.builder()
                        .status("fail")
                        .message(objectError.getDefaultMessage())
                        .build();

                return ResponseEntity.internalServerError().body(response);

            }
        }

        return ResponseEntity.ok().body(orderService.orderChange(orderInfo));

    }
}
