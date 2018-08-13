package com.cjl.wxdc.order.mq;

import com.cjl.wxdc.base.vo.StockVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Transactional
public class StockMessage
{
    private static final Logger logger = LoggerFactory.getLogger(StockMessage.class);

    public static final String PRODUCT_STOCK = "product_stock_%";

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("stock"))
    public void processDescStock(List<StockVO> stocks){
        if (CollectionUtils.isEmpty(stocks)){
            logger.info("没有收到消息...");
            return;
        }
        for (StockVO stockVO : stocks){
            String id = stockVO.getProductId();
            int n = stockVO.getProductQuantity();
            redisTemplate.opsForValue().set(String.format(PRODUCT_STOCK,stockVO.getProductId()),n);
        }
//        logger.error("value={}",redisTemplate.opsForValue().get("157875196366160022"));
    }
}
