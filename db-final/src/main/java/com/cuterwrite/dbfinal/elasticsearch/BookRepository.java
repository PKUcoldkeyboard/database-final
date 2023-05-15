package com.cuterwrite.dbfinal.elasticsearch;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cuterwrite.dbfinal.entity.EsBook;

/**
 * @author Pang S.Z.
 * @create 2020-10-12 14:33:31
 */
public interface BookRepository extends ElasticsearchRepository<EsBook, Long> {

}
