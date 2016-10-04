package com.evozon.domain.dtos;

/**
 * Created by florinani on 26/07/2016.
 */
public class OrderRestDTO {

    private Integer ordersId;
    private String status;

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
