package com.czg.elasticsearch.index.controller;

import com.czg.elasticsearch.common.ApiResult;
import com.czg.elasticsearch.common.IndexCommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * es索引库操作
 *
 * @author czg
 * @date 2023/10/27 16:31
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/index")
public class IndexController {

    private final IndexCommonUtils indexCommonUtils;

    /**
     * 创建或更新索引库
     *
     * @param entityName 索引库对应的对象信息
     * @return 创建或更新结果：true成功，false失败
     */
    @PostMapping("/create")
    public ApiResult<Boolean> createIndexAndMapping(@RequestParam(value = "entityName") String entityName) {
        return new ApiResult<>(indexCommonUtils.createIndexAndMapping(entityName));
    }

    /**
     * 删除索引库
     *
     * @param entityName 索引库对应的对象信息
     * @return 创建或更新结果：true成功，false失败
     */
    @DeleteMapping("/delete")
    public ApiResult<Boolean> deleteIndex(@RequestParam(value = "entityName") String entityName) {
        return new ApiResult<>(indexCommonUtils.deleteIndex(entityName));
    }

}
