package com.barogo.api.domain;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;

// import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

    @Query("select order from tbl_order order where order.orderdate between :startDate and :endDate and order.id=:id")
    List<OrderInfo> findByOrderdateBetweenAndId(@Param("startDate") Date startDate,
            @Param("endDate") Date endDate, @Param("id") String id);

    @Query(value = "select * from tbl_order t where t.order_no=:order_no and t.status=:status", nativeQuery = true)
    OrderInfo findByOrdernoAndStatus(@Param("order_no") int order_no, @Param("status") int status);

}
