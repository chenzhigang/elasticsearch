package com.czg.elasticsearch.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * es操作工具类
 *
 * @author czg
 * @date 2023/10/27 17:13
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class IndexCommonUtils {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 获取对象属性值
     *
     * @param attrName 属性名称
     * @param cls 对象
     * @return 属性值
     */
    public <T> Object getObjectValue(String attrName, T cls) {
        try {
            Field field = cls.getClass().getDeclaredField(attrName);
            field.setAccessible(true);
            return field.get(cls);
        } catch (Exception e) {
            log.error("获取对象{}属性值{}失败", cls.getClass().getName(), attrName, e);
        }
        return null;
    }

    /**
     * 创建索引库
     *
     * @param className 实体类名称
     * @return 创建结果
     */
    public <T> Boolean createIndexAndMapping(String className) {
        try {
            Class<?> cls = Class.forName(className);
            return createIndexAndMapping(cls);
        } catch (ClassNotFoundException e) {
            log.error("创建索引失败", e);
        }
        return false;
    }


    /**
     * 创建索引库和映射（如果存在先删除后添加）
     *
     * @param cls 实体类
     * @param <T> 泛型
     * @return 创建结果
     */
    public <T> Boolean createIndexAndMapping(Class<T> cls) {
        try {
            IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(cls);
            if (indexOperations.exists()) {
                // 创建索引库，存在就先删除后添加（等同更新）
                indexOperations.delete();
            }
            boolean result = indexOperations.create();
            if (result) {
                // 生成映射
                Document mapping = indexOperations.createMapping();
                // 推送映射
                return indexOperations.putMapping(mapping);
            }
        } catch (Exception e) {
            log.error("创建索引失败", e);
        }
        return false;
    }

    /**
     * 删除索引库
     *
     * @param entityName 实体类名称
     * @return 删除结果
     */
    public Boolean deleteIndex(String entityName) {
        try {
            Class<?> cls = Class.forName(entityName);
            return deleteIndex(cls);
        } catch (ClassNotFoundException e) {
            log.error("删除索引失败", e);
        }
        return false;
    }

    /**
     * 删除索引库
     *
     * @param cls 实体类
     * @param <T> 泛型
     * @return 删除结果
     */
    public <T> Boolean deleteIndex(Class<T> cls) {
        try {
            IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(cls);
            if (!indexOperations.exists()) {
                log.info("删除索引成功");
                return true;
            }
            boolean result = indexOperations.delete();
            if (result) {
                log.info("删除索引成功");
                return true;
            } else {
                log.info("删除索引失败");
                return false;
            }
        } catch (Exception e) {
            log.error("删除索引失败", e);
        }
        return false;
    }

}
