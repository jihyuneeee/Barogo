package com.barogo.api.domain;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderCheckInfo {

    @NotBlank(message = "id is a required value.")
    private String id;

    @NotBlank(message = "start date is a required value.")
    private String startDate;

    @NotBlank(message = "end date is a required value.")
    private String endDate;
}
