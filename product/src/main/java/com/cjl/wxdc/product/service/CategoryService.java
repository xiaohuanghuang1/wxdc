package com.cjl.wxdc.product.service;

import com.cjl.wxdc.base.entity.ProductCategory;
import com.cjl.wxdc.base.entity.ProductCategoryExample;

import java.util.List;

public interface CategoryService
{
    List<ProductCategory> findAll();

    List<ProductCategory> findBy(ProductCategoryExample example);
}
