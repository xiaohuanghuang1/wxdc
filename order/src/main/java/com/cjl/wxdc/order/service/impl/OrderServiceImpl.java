package com.cjl.wxdc.order.service.impl;

import com.cjl.wxdc.base.CartDTO;
import com.cjl.wxdc.base.constant.OrderEnum;
import com.cjl.wxdc.base.entity.ProductInfo;
import com.cjl.wxdc.base.exception.OrderException;
import com.cjl.wxdc.base.exception.ProductException;
import com.cjl.wxdc.base.vo.Result;
import com.cjl.wxdc.order.api.ProductAPI;
import com.cjl.wxdc.base.entity.OrderDetail;
import com.cjl.wxdc.base.entity.OrderMaster;
import com.cjl.wxdc.order.mapper.OrderDetailMapper;
import com.cjl.wxdc.order.mapper.OrderMasterMapper;
import com.cjl.wxdc.order.service.OrderService;
import com.cjl.wxdc.order.vo.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("orderServiceImpl")
@Transactional
public class OrderServiceImpl implements OrderService
{
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private ProductAPI productAPI;

    @Override
    public String create(OrderDTO orderDTO)
    {

        String orderId = UUID.randomUUID().toString().toUpperCase();
        //查询商品
        List<String> ids = orderDTO.getOrderDetails().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        System.out.println(ids);
        List<ProductInfo> productInfos = productAPI.findlist(ids);
//        List<ProductInfo> productInfos = (List<ProductInfo>) result.getDate();

        //计算总价
        List<OrderDetail> orderDetails = orderDTO.getOrderDetails();
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDetails)
        {
            for (ProductInfo productInfo : productInfos)
            {
                if (productInfo.getProductId().equals(orderDetail.getProductId()))
                {
                    amount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(amount);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(UUID.randomUUID().toString().toUpperCase());
                    orderDetailMapper.insert(orderDetail);
                }
            }
        }

        //减库存
        List<CartDTO> cartDTOS = orderDTO.getOrderDetails().stream().
                map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        Result<String> result1 = productAPI.decreaseStock(cartDTOS);

        //保存订单
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderEnum.ORDERSTATUS.NEW.getCode());
        orderMaster.setPayStatus(OrderEnum.PAYSTATUS.WAIT_PAY.getStatus());
        orderMaster.setOrderAmount(amount);
        orderMasterMapper.insert(orderMaster);

        return orderMaster.getOrderId();
    }
}
