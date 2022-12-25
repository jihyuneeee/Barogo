package com.barogo.api.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_order")
public class OrderInfo {

    @Id
    @Column(name = "order_no")
    @NotNull(message = "order no is a required value.")
    private int orderno;

    private String id;

    @NotBlank(message = "address is a required value.")
    private String address;

    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverydate;

    private int rider_no;

    private int store_no;

}
