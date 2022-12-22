package com.barogo.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbl_order")
public class OrderInfo {

    @Id
    @Column(name = "order_no")
    private int orderno;

    private String id;

    @Column(name = "address")
    private String address;

    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverydate;

    private int rider_id;

}
