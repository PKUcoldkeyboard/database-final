package com.cuterwrite.dbfinal.neo4j.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.cuterwrite.dbfinal.neo4j.entity.Disease;

/**  
 * @author Pang S.Z.
 * @create 2020-10-27 22:18:44 
 */
public interface DiseaseRepository extends Neo4jRepository<Disease, Long>{

}
