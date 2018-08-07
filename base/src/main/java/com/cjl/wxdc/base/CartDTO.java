package com.cjl.wxdc.base;

/**
 * 用于订单服务和商品服务之间传递购物车信息
 */
public class CartDTO
{
    private String productId;
    private int productQuantity;

    public CartDTO()
    {
    }

    public CartDTO(String productId, int productQuantity)
    {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public int getProductQuantity()
    {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity)
    {
        this.productQuantity = productQuantity;
    }
}
