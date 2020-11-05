package com.cuterwrite.dbfinal.neo4j.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.cuterwrite.dbfinal.neo4j.entity.Person;

/**  
 * @author Pang S.Z.
 * @create 2020-10-27 19:58:22 
 */
public interface PersonRepository extends Neo4jRepository<Person, Long>{
	
}
