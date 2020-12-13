package com.cuterwrite.dbfinal.service;
/**  
 * @author Pang S.Z.
 * @create 2020-10-27 20:01:23 
 */

import com.cuterwrite.dbfinal.neo4j.entity.Disease;
import com.cuterwrite.dbfinal.neo4j.entity.Person;
import com.cuterwrite.dbfinal.neo4j.relationship.Love;

public interface Neo4jService {
	Person addPerson(Person person);
	Person findOnePerson(Long id);
	Disease addDisease(Disease disease);
	Love loves(Love love);
	void clear();
}
