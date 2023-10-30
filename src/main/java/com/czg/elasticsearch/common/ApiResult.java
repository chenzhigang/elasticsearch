package com.czg.elasticsearch.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

/**
 * @author czg
 * @date 2023/10/30 10:58
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResult<T> {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public ApiResult(T data) {
        this.code = HttpStatus.SC_OK;
        this.data = data;
    }

}
