package com.czg.elasticsearch.document.user.controller;

import com.czg.elasticsearch.common.ApiResult;
import com.czg.elasticsearch.document.user.service.UserService;
import com.czg.elasticsearch.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author czg
 * @date 2023/10/30 10:14
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/doc/user")
public class UserController {

    private final UserService userService;

    /**
     * 创建文档
     *
     * @param user 文档对象
     * @return 文档信息
     */
    @PostMapping("/create")
    public ApiResult<User> createDocument(@RequestBody User user) {
        userService.createDocument(user);
        return new ApiResult<>(user);
    }

    /**
     * 删除文档
     *
     * @param id 用户信息
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public ApiResult<Boolean> deleteDocument(@PathVariable(value = "id") String id) {
        userService.deleteDocument(id);
        return new ApiResult<>(true);
    }
}
