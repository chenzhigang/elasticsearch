package com.czg.elasticsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author czg
 * @date 2023/10/27 16:57
 */
@Data
@Document(indexName = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @Field(type = FieldType.Keyword, index = false)
    private String id;

    @Field(type = FieldType.Integer, index = false)
    private Integer age;

    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String name;

    @Field(type = FieldType.Keyword, index = false)
    private String gender;

    @Field(type = FieldType.Keyword, index = false)
    private String phone;

    @Field(type = FieldType.Keyword, index = false)
    private String email;

    @Field(type = FieldType.Keyword, index = false)
    private String headerPic;

}
