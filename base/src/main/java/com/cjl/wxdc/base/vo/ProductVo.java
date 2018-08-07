package com.cjl.wxdc.base.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductVo
{
    @JsonProperty("Id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public BigDecimal getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice)
    {
        this.productPrice = productPrice;
    }

    public String getProductDescription()
    {
        return productDescription;
    }

    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }

    public String getProductIcon()
    {
        return productIcon;
    }

    public void setProductIcon(String productIcon)
    {
        this.productIcon = productIcon;
    }

    @Override
    public String toString()
    {
        return "ProductVo{" + "productId='" + productId + '\'' + ", productName='" + productName + '\'' + ", productPrice=" + productPrice + ", productDescription='" + productDescription + '\'' + ", productIcon='" + productIcon + '\'' + '}';
    }
}
