package com.cjl.wxdc.base.exception;

import com.cjl.wxdc.base.constant.ErrorCode;
import com.cjl.wxdc.base.constant.ProductEnum;

public class ProductException extends RuntimeException
{
    private int code;
    private String msg;

    public ProductException(ErrorCode.ERRORCODE errorcode)
    {
        super(errorcode.getMsg());
        this.code=errorcode.getCode();
    }
}
