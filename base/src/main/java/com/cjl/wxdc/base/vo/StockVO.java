package com.cjl.wxdc.base.vo;

import java.io.Serializable;

public class StockVO implements Serializable
{
    private String productId;
    private int productQuantity;

    @Override
    public String toString()
    {
        return "StockVO{" + "productId='" + productId + '\'' + ", productQuantity=" + productQuantity + '}';
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
