package com.cuterwrite.dbfinal.elasticsearch.dao;


import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cuterwrite.dbfinal.entity.Book;

/**  
 * @author Pang S.Z.
 * @create 2020-10-12 14:33:31 
 */
public interface BookRepository extends ElasticsearchRepository<Book, Long>{
	List<Book>findByName(String name);
}
