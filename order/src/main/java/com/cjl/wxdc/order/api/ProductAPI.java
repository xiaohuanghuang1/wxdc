package com.cjl.wxdc.order.api;

import com.cjl.wxdc.base.CartDTO;
import com.cjl.wxdc.base.entity.ProductInfo;
import com.cjl.wxdc.base.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
public interface ProductAPI
{
    /**
     * 查询商品列表
     */
    @PostMapping("/product/fids")
    public List<ProductInfo> findlist(@RequestBody List<String> productIds);

    /**
     * 减库存
     */
    @PostMapping("/product/dstock")
    Result<String> decreaseStock(@RequestBody List<CartDTO> cartDTOS);

}
