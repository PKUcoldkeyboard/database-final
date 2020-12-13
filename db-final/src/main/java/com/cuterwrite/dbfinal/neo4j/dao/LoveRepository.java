package com.cuterwrite.dbfinal.neo4j.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.cuterwrite.dbfinal.neo4j.relationship.Love;

/**  
 * @author Pang S.Z.
 * @create 2020-10-27 19:58:56 
 */
public interface LoveRepository extends Neo4jRepository<Love, Long>{

}
