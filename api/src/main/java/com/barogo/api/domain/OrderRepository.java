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

    @Query("select order from tbl_order order where order.orderdate between?0 and?1 and order.id=?2")
    List<OrderInfo> findByOrderdateBetweenAndId(Date startDate, Date endDate, String id);

    @Query(value = "select * from tbl_order t where t.order_no=:order_no and t.status=:status", nativeQuery = true)
    OrderInfo findByOrdernoAndStatus(@Param("order_no") int order_no, @Param("status") int status);

    // @Query(value = "update tbl_order set address =?0 where order_no = ?1 ",
    // nativeQuery = true)
    // @Modifying
    // void updateAddress(String address, int orderno);

    // @Transactional
    // @Modifying(clearAutomatically = true)
    // @Query("update tbl_order `order` set `order`.address = :address where
    // `order`.order_no = :order_no")
    // int address(@Param("address") String address, @Param("order_no") int
    // order_no);

    // @Modifying(clearAutomatically = true)
    // @Query("UPDATE tbl_order c SET c.address = :address WHERE c.order_no =
    // :orderno")
    // int updateAddress(@Param("address") String address, @Param("orderno") int
    // orderno);
}
