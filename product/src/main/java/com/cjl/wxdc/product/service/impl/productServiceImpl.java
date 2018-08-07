package com.cjl.wxdc.product.service.impl;

import com.cjl.wxdc.base.CartDTO;
import com.cjl.wxdc.base.constant.ErrorCode;
import com.cjl.wxdc.base.constant.ProductEnum;
import com.cjl.wxdc.base.exception.ProductException;
import com.cjl.wxdc.product.dao.ProductInfoMapper;
import com.cjl.wxdc.base.entity.ProductInfo;
import com.cjl.wxdc.base.entity.ProductInfoExample;
import com.cjl.wxdc.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional
public class productServiceImpl implements ProductService
{
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> findUpAll()
    {
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();

        criteria.andProductStatusNotEqualTo(ProductEnum.ProductStatus.PRODUCT_DOWN.getStatus());

        return productInfoMapper.selectByExample(example);
    }

    @Override
    public List<ProductInfo> findByIds(List<String> ids)
    {
        if (CollectionUtils.isEmpty(ids))
        {
            logger.error("ids不能为空:{}",ids);
            throw new ProductException(ErrorCode.ERRORCODE.PARAM_ERROR);
        }
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdIn(ids);
        return productInfoMapper.selectByExample(example);
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOs)
    {
        if (CollectionUtils.isEmpty(cartDTOs))
        {
            logger.error("cartDTOs 不能为空:{}",cartDTOs);
            throw new ProductException(ErrorCode.ERRORCODE.PARAM_ERROR);
        }
        for (CartDTO cartDTO : cartDTOs)
        {
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(cartDTO.getProductId());

            if (productInfo == null)
            {
                logger.error("商品【{}】不存在",productInfo.getProductName());
                throw new ProductException(ErrorCode.ERRORCODE.PRODUCT_NOT_EXIST);
            }
            int resultNum = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (resultNum < 0)
            {
                logger.error("库存不够,库存:{}",productInfo.getProductStock());
                throw new ProductException(ErrorCode.ERRORCODE.STOCK_IS_ERROR);
            }
            productInfo.setProductStock(resultNum);
            productInfoMapper.updateByPrimaryKey(productInfo);
        }
    }
}
