package com.cjl.wxdc.product.service.impl;

import com.cjl.wxdc.product.dao.ProductCategoryMapper;
import com.cjl.wxdc.base.entity.ProductCategory;
import com.cjl.wxdc.base.entity.ProductCategoryExample;
import com.cjl.wxdc.product.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> findAll()
    {
        return productCategoryMapper.selectByExample(new ProductCategoryExample());
    }

    @Override
    public List<ProductCategory> findBy(ProductCategoryExample example)
    {
        return productCategoryMapper.selectByExample(example);
    }
}
