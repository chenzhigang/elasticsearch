package com.czg.elasticsearch.aspect;

import com.czg.elasticsearch.common.ApiResult;
import com.czg.elasticsearch.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义异常处理类
 *
 * @author czg
 * @date 2023/10/30 15:51
 */
@Slf4j
@Aspect
@Component
public class RepositoryAspect {

    @Around("@annotation(ExceptionHandle)")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            // 如果返回报文包含ok，则认为请求成功
            String message = e.getMessage();
            if (StringUtils.isNotBlank(message) && message.contains("OK")) {
                log.info("处理成功200");
                return new ApiResult<>(true);
            }
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            ExceptionHandle exceptionHandle = method.getAnnotation(ExceptionHandle.class);
            switch (exceptionHandle.optType()) {
                // 操作类型为创建，如果返回包含created则认为请求成功
                case CREATE:
                    if (StringUtils.isNotBlank(message) && message.toLowerCase().contains("created")) {
                        log.info("创建成功201", message);
                        return new ApiResult<>(true);
                    }
                    break;
                default:
            }
            throw new BusinessException(message);
        }
    }

}
