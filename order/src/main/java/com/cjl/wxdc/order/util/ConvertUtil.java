package com.cjl.wxdc.order.util;

import com.cjl.wxdc.base.constant.ErrorCode;
import com.cjl.wxdc.base.exception.OrderException;
import com.cjl.wxdc.base.entity.OrderDetail;
import com.cjl.wxdc.order.vo.OrderDTO;
import com.cjl.wxdc.order.vo.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil
{
    private static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

    public static OrderDTO OrderFormConvertOrderDTO(OrderForm orderForm)
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        Gson gson = new Gson();
        List<OrderDetail> orderDetails = new ArrayList<>();

        try
        {
            orderDetails = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e)
        {
            logger.error("json转换错误:{}",orderForm.getItems());
            throw new OrderException(ErrorCode.ERRORCODE.PARAM_ERROR);
        }
        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
    }
}
