package com.barogo.api.model;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

    @Query("select * from tbl_order `order` where`order`.order_date between?0 and?1 and id=?2")
    List<OrderInfo> findByOrderdateBetweenAndId(Date startDate, Date endDate, String id);

    @Query("select * from tbl_order `order` where `order`.order_no=?0 and `order`.status=?1")
    OrderInfo findByOrdernoAndStatus(int order_no, int status);

    // @Transactional
    // @Modifying(clearAutomatically = true)
    // @Query("update tbl_order `order` set `order`.address = :address where
    // `order`.order_no = :order_no")
    // int address(@Param("address") String address, @Param("order_no") int
    // order_no);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE tbl_order c SET c.address = :address WHERE c.order_no = :orderno")
    int updateAddress(@Param("address") String address, @Param("orderno") int orderno);
}
