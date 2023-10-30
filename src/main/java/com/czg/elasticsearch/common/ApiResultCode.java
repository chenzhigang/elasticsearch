package com.czg.elasticsearch.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author czg
 * @date 2023/10/30 15:59
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ApiResultCode {
    SUCCESS(200, "成功"),
    FAILED(500, "失败"),
    ;

    private int code;

    private String msg;

}
