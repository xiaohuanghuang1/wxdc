package com.cjl.wxdc.order.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm
{
    @NotEmpty(message = "买家名字不能为空")
    private String name;

    @NotEmpty(message = "买家电话不能为空")
    private String phone;

    @NotEmpty(message = "买家地址不能为空")
    private String address;

    @NotEmpty(message = "买家openid不能为空")
    private String openid;

    private String items;
}
