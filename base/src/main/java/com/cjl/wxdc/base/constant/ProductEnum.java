package com.cjl.wxdc.base.constant;

import jdk.nashorn.internal.objects.annotations.Getter;

public interface ProductEnum
{
    public enum ProductStatus
    {
        PRODUCT_UP(1,"上架"),
        PRODUCT_DOWN(0,"下架");
        private int status;
        private String desc;
        ProductStatus(int status, String desc)
        {
            this.status = status;
            this.desc = desc;
        }

        public int getStatus()
        {
            return status;
        }

        public void setStatus(int status)
        {
            this.status = status;
        }

        public String getDesc()
        {
            return desc;
        }

        public void setDesc(String desc)
        {
            this.desc = desc;
        }
    }





}
