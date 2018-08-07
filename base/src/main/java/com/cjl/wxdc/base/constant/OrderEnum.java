package com.cjl.wxdc.base.constant;

public interface OrderEnum
{
    public enum  PAYSTATUS
    {
        WAIT_PAY(0,"未支付"),
        SUCCESS_PAY(1,"支付成功")
        ;
        private int status;
        private String msg;
        PAYSTATUS(int status, String msg)
        {
            this.status = status;
            this.msg = msg;
        }

        public int getStatus()
        {
            return status;
        }

        public String getMsg()
        {
            return msg;
        }
    }

    public enum ORDERSTATUS
    {
        NEW(0,"新创建"),
        SUCCESS(1,"创建成功")
        ;
        //
        private int code;
        private String msg;

        ORDERSTATUS(int code, String msg)
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
