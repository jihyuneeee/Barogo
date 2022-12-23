package com.barogo.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barogo.api.domain.OrderInfo;
import com.barogo.api.domain.OrderRepository;

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

    @Transactional
    public void orderChange(HashMap<String, Object> params) {

        int order_no = (int) params.get("order_no");
        String address = params.get("address").toString();
        System.out.println("order_no :" + order_no);
        System.out.println("address :" + address);
        // status 조회해서 가능한지 확인
        OrderInfo test = orderRepository.findByOrdernoAndStatus(order_no, 0);
        System.out.println("test   :::: " + test);

        // 가능하면 변경
        if (test != null) {

            test.setAddress(address);

            // orderRepository.updateAddress(address, order_no);
        }

    }

}
