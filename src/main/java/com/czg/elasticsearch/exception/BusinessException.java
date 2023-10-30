package com.czg.elasticsearch.exception;

import com.czg.elasticsearch.common.ApiResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author czg
 * @date 2023/10/30 15:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private String errMsg;

    private int errCode;

    public BusinessException(String message, String errMsg, int errCode) {
        super(message);
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public BusinessException(String message, String errMsg) {
        super(message);
        this.errMsg = errMsg;
        this.errCode = ApiResultCode.FAILED.getCode();
    }

    public BusinessException(String message) {
        super(message);
        this.errMsg = ApiResultCode.FAILED.getMsg();
        this.errCode = ApiResultCode.FAILED.getCode();
    }
}
