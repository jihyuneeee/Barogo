package com.barogo.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barogo.api.model.OrderInfo;
import com.barogo.api.model.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<OrderInfo> orderList(HashMap<String, Object> params) {

        // 1. Null

        // 2. 기간 3일
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate;
        Date endDate;
        List<OrderInfo> list = null;

        try {
            startDate = formatter.parse(params.get("startDate").toString());
            endDate = formatter.parse(params.get("endDate").toString());
            long t = (endDate.getTime() - startDate.getTime()) / 1000;
            long minus = t / (24 * 60 * 60);
            System.out.println("startDate : " + startDate.getTime());
            System.out.println("endDate : " + endDate.getTime());
            System.out.println("=  : " + minus);

            if (minus > 3) {
                System.out.println("할수없음.");

            } else {
                list = orderRepository.findByOrderdateBetweenAndId(startDate, endDate, "bbb");
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public void orderChange(HashMap<String, Object> params) {

        // status 조회해서 가능한지 확인
        OrderInfo test = orderRepository.findByOrdernoAndStatus(1, 0);
        System.out.println("test   :::: " + test);

        // 가능하면 변경
        if (test != null) {

        }

    }
}
