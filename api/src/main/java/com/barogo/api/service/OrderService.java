package com.barogo.api.service;

import java.util.HashMap;

import com.barogo.api.domain.OrderCheckInfo;
import com.barogo.api.domain.OrderInfo;
import com.barogo.api.domain.ResponseInfo;

public interface OrderService {

    public HashMap<String, Object> orderList(OrderCheckInfo orderCheck);

    public ResponseInfo orderChange(OrderInfo orderInfo);

}
