package com.cjl.wxdc.base.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"name","type","foods"})
public class CategoryVo
{
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    private List<ProductVo> foods;

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType()
    {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType)
    {
        this.categoryType = categoryType;
    }

    public List<ProductVo> getFoods()
    {
        return foods;
    }

    public void setFoods(List<ProductVo> foods)
    {
        this.foods = foods;
    }

    @Override
    public String toString()
    {
        return "CategoryVo{" + "categoryName='" + categoryName + '\'' + ", categoryType=" + categoryType + ", foods=" + foods + '}';
    }
}
