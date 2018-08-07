package com.cjl.wxdc.product.service;

import com.cjl.wxdc.base.CartDTO;
import com.cjl.wxdc.base.entity.ProductInfo;

import java.util.List;

public interface ProductService
{
    List<ProductInfo> findUpAll();

    List<ProductInfo> findByIds(List<String> ids);

    /**
     * 减库存
     * @param cartDTOs
     */
    void decreaseStock(List<CartDTO> cartDTOs);
}
