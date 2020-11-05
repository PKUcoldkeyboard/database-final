package com.cuterwrite.dbfinal.elasticsearch.dao;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cuterwrite.dbfinal.entity.User;

/**  
 * @author Pang S.Z.
 * @create 2020-10-12 14:33:31 
 */
public interface BookRepository extends ElasticsearchRepository<User, Long>{
	

}
