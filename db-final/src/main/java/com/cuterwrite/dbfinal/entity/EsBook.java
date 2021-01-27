package com.cuterwrite.dbfinal.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**  
 * @author Pang S.Z.
 * @create 2021-01-22 15:21:17 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Document(indexName = "books",shards = 1,replicas = 0,indexStoreType = "fs")
public class EsBook {
	@Id
	@Field(type = FieldType.Long,store = true)
	private Long id;
	@Field(analyzer = "ik_max_word",type = FieldType.Text,store=true)
	private String description;
	@Field(analyzer = "ik_max_word",type = FieldType.Text,store=true)
	private String bookName;
	@Field(analyzer = "ik_max_word",type = FieldType.Text,store=true)
	private String author;
	@Field(type = FieldType.Keyword,store=true)
	private String categoryName;
	@Field(type = FieldType.Keyword,store=true)
	private String isbn;
	@Field(type = FieldType.Keyword,store=true)
	private String press;
	@Field(type = FieldType.Text,store=true,index=false)
	private String pic;
	@Field(type = FieldType.Auto,store=true)
	private BigDecimal price;
	private Date publishDate;
	@Field(type = FieldType.Integer,store=true,index=false)
	private Integer sale;
}
