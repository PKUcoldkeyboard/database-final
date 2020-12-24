package com.cuterwrite.dbfinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@Document(indexName = "book-sales",shards = 1,replicas = 0,indexStoreType = "fs")
public class Book implements Serializable {
    /**
     * 自增id
     */
	@Id
    private Long id;

    /**
     * 图书名称
     */
	@Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录修改时间
     */
    private Date modifyTime;

    /**
     * 图书分类号
     */
    private Long categoryId;

    /**
     * 图书描述
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String description;

    /**
     * 图书图片url
     */
    private String picture;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 图书销量
     */
    private Integer sale;

    /**
     * 图书库存
     */
    private Integer stock;

    /**
     * 画册图片，以逗号分隔，包括picture最多5张
     */
    private String albumPicture;

    /**
     * 书号
     */
    @Field(type = FieldType.Keyword)
    private String isbn;

    /**
     * 作者名，如果有多个作者，以逗号分隔
     */
    @Field(type = FieldType.Keyword)
    private String author;

    /**
     * 出版社名
     */
    @Field(type = FieldType.Keyword)
    private String press;

    /**
     * 图书语言
     */
    private String language;

    /**
     * 图书页数
     */
    private Integer pageCount;

    /**
     * 出版日期
     */
    private Date publishDate;

    private static final long serialVersionUID = 1L;
}