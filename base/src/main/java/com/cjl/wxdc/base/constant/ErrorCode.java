package com.cjl.wxdc.base.constant;

public interface ErrorCode
{
    public enum ERRORCODE
    {
        PARAM_ERROR(1,"参数错误"),
        CART_EMPTY(2,"购物车为空"),
        STOCK_IS_ERROR(3,"库存不足"),
        PRODUCT_NOT_EXIST(4,"商品不存在")
        ;
        private int code;
        private String msg;

        ERRORCODE(int code, String msg)
        {
            this.code = code;
            this.msg = msg;
        }

        public int getCode()
        {
            return code;
        }

        public String getMsg()
        {
            return msg;
        }
    }
}
