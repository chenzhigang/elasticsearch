package com.czg.elasticsearch.document.user.service;

import com.czg.elasticsearch.aspect.ExceptionHandle;
import com.czg.elasticsearch.aspect.ExceptionOptEnum;
import com.czg.elasticsearch.domain.User;
import com.czg.elasticsearch.index.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author czg
 * @date 2023/10/30 16:59
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * 创建文档
     *
     * @param user 用户文档信息
     */
    @ExceptionHandle(optType = ExceptionOptEnum.CREATE)
    public void createDocument(User user) {
        userRepository.save(user);
    }

    /**
     * 删除文档
     *
     * @param id 用户文档id
     */
    @ExceptionHandle(optType = ExceptionOptEnum.DELETE)
    public void deleteDocument(String id) {
        if (!userRepository.existsById(id)) {
            log.info("用户文档[{}]已删除", id);
            return;
        }
        userRepository.deleteById(id);
    }

}
