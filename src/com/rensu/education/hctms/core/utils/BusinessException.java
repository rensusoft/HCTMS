package com.rensu.education.hctms.core.utils;

public class BusinessException extends RuntimeException {

	
    /**
     */
    private static final long serialVersionUID = -7638041501183925225L;

    /***
     * 10 -- 用户会话过期
     * 20 -- 
     */
    private Integer code;

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}