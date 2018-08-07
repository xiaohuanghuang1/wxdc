package com.cjl.wxdc.base.exception;

import com.cjl.wxdc.base.constant.ErrorCode;
import com.cjl.wxdc.base.constant.ProductEnum;

public class OrderException extends RuntimeException
{
    private int code;
    private String msg;
    public String getMsg()
    {
        return msg;
    }
    public OrderException(int code, String msg)
    {
        super(msg);
        this.code = code;
    }

    public OrderException(ErrorCode.ERRORCODE errorcode)
    {
        super(errorcode.getMsg());
        this.code = errorcode.getCode();
    }
}
