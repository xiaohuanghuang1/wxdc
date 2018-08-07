package com.cjl.wxdc.product.controller;

import com.cjl.wxdc.base.CartDTO;
import com.cjl.wxdc.base.vo.CategoryVo;
import com.cjl.wxdc.base.vo.ProductVo;
import com.cjl.wxdc.base.vo.Result;
import com.cjl.wxdc.base.entity.ProductCategory;
import com.cjl.wxdc.base.entity.ProductCategoryExample;
import com.cjl.wxdc.base.entity.ProductInfo;
import com.cjl.wxdc.product.service.CategoryService;
import com.cjl.wxdc.product.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController
{
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private CategoryService categoryServiceImpl;


    @RequestMapping("/list")
    public Object listUpProduct()
    {
        List<ProductInfo> productInfos = productServiceImpl.findUpAll();
        List<Integer> types = productInfos.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        ProductCategoryExample example = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryTypeIn(types);
        List<ProductCategory> categories = categoryServiceImpl.findBy(example);

        List<CategoryVo> categoryVos = new LinkedList<>();
        for (ProductCategory category : categories)
        {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category,categoryVo);

            List<ProductVo> productVos = new LinkedList<>();
            for (ProductInfo productInfo : productInfos)
            {
                if (productInfo.getCategoryType().equals(category.getCategoryType()))
                {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(productInfo,productVo);
                    productVos.add(productVo);
                }
            }
            categoryVo.setFoods(productVos);
            categoryVos.add(categoryVo);
        }

        Result<List<CategoryVo>> result = new Result<>();
        result.setCode(0);
        result.setMsg("success");
        result.setDate(categoryVos);
        return result;
    }

    @PostMapping("/fids")
    public Object findlist(@RequestBody List<String> productIds)
    {
//        Result<List<ProductInfo>> result = new Result<>();
//        result.setCode(0);
//        result.setMsg("success");
//        result.setDate();
        return productServiceImpl.findByIds(productIds);
    }

    @PostMapping("/dstock")
    public Object decreaseStock(@RequestBody List<CartDTO> cartDTOS)
    {
        Result<List<ProductInfo>> result = new Result<>();
        try
        {
            productServiceImpl.decreaseStock(cartDTOS);
        }catch (Exception e)
        {
            result.setCode(1);
            result.setMsg("fail");
            return result;
        }
        result.setCode(0);
        result.setMsg("success");
        return result;
    }
}
