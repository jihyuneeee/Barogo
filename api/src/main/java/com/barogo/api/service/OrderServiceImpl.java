package com.barogo.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barogo.api.domain.OrderCheckInfo;
import com.barogo.api.domain.OrderInfo;
import com.barogo.api.domain.OrderRepository;
import com.barogo.api.domain.ResponseInfo;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public HashMap<String, Object> orderList(OrderCheckInfo orderCheck) {

        HashMap<String, Object> jsonObject = new HashMap<>();
        ResponseInfo response;

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date startDate = formatter.parse(orderCheck.getStartDate());
            Date endDate = formatter.parse(orderCheck.getEndDate());

            long diffSec = (endDate.getTime() - startDate.getTime()) / 1000;
            long diffDay = diffSec / (24 * 60 * 60);

            if (diffDay > 3) { // 3일 초과

                response = ResponseInfo.builder()
                        .status("fail")
                        .message("search criteria has been exceeded by 3 days.")
                        .build();

                jsonObject.put("response", response);
                return jsonObject;

            }

            List<OrderInfo> list = orderRepository.findByOrderdateBetweenAndId(startDate, endDate,
                    orderCheck.getId());

            if (!list.isEmpty()) {

                response = ResponseInfo.builder()
                        .status("success")
                        .message("success")
                        .build();

                jsonObject.put("data", list);

            } else {

                response = ResponseInfo.builder()
                        .status("success")
                        .message("there is no data")
                        .build();
            }

            jsonObject.put("response", response);

        } catch (ParseException e) {

            response = ResponseInfo.builder()
                    .status("fail")
                    .message("ParseException Error")
                    .build();

        }

        return jsonObject;
    }

    @Transactional
    @Override
    public ResponseInfo orderChange(OrderInfo orderInfo) {

        ResponseInfo response;
        int order_no = orderInfo.getOrderno();
        String address = orderInfo.getAddress();
        int status = 0; // 0 : 배달 전 1 : 배달중 2: 배달취소

        OrderInfo order_data = orderRepository.findByOrdernoAndStatus(order_no, status);

        if (order_data != null) {

            order_data.setAddress(address);

            response = ResponseInfo.builder().status("success").message("success").build();

        } else {
            response = ResponseInfo.builder().status("fail").message("Can't change delievery. ").build();

        }

        return response;
    }

}
