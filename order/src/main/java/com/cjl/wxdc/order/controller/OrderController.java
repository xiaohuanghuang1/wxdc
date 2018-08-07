package com.cjl.wxdc.order.controller;

import com.cjl.wxdc.base.constant.ErrorCode;
import com.cjl.wxdc.base.constant.ProductEnum;
import com.cjl.wxdc.base.exception.OrderException;
import com.cjl.wxdc.base.vo.Result;
import com.cjl.wxdc.order.api.ProductAPI;
import com.cjl.wxdc.order.service.OrderService;
import com.cjl.wxdc.order.util.ConvertUtil;
import com.cjl.wxdc.order.vo.OrderDTO;
import com.cjl.wxdc.order.vo.OrderForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController
{
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

//    @Autowired
//    private ProductAPI productAPI;

    @Autowired
    private OrderService orderServiceImpl;

    @PostMapping("/create")
    public Object create(@Valid OrderForm orderForm, BindingResult result)
    {
        if (result.hasErrors())
        {
            logger.info("Error: {}",result.getFieldError().getDefaultMessage());
            throw new OrderException(ErrorCode.ERRORCODE.PARAM_ERROR);
        }
        //orderForm -> orderDTO
        OrderDTO orderDTO = ConvertUtil.OrderFormConvertOrderDTO(orderForm);
        String res = orderServiceImpl.create(orderDTO);
        Result<String> r = new Result<>();
        r.setCode(0);
        r.setMsg("success");
        r.setDate("openid"+res);
        return r;
    }

//    @RequestMapping("/findps")
//    private Object findps()
//    {
//        List<String> ids = new ArrayList<>();
//        ids.add("157875196366160022");
//        ids.add("157875227953464068");
//        return productAPI.findlist(ids);
//    }
}
