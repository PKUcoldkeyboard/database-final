package com.cuterwrite.dbfinal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.neo4j.dao.DiseaseRepository;
import com.cuterwrite.dbfinal.neo4j.dao.LoveRepository;
import com.cuterwrite.dbfinal.neo4j.dao.PersonRepository;
import com.cuterwrite.dbfinal.neo4j.entity.Disease;
import com.cuterwrite.dbfinal.neo4j.entity.Person;
import com.cuterwrite.dbfinal.neo4j.relationship.Love;
import com.cuterwrite.dbfinal.service.Neo4jService;

/**  
 * @author Pang S.Z.
 * @create 2020-10-27 20:02:22 
 */
@Service
public class Neo4jServiceImpl implements Neo4jService{
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	LoveRepository loveRepository;
	
	@Autowired
	DiseaseRepository diseaseRepository;

	@Override
	public Person addPerson(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Person findOnePerson(Long id) {
		return personRepository.findById(id).get();
	}

	@Override
	public Love loves(Love love) {
		return loveRepository.save(love);
	}

	@Override
	public void clear() {
		diseaseRepository.deleteAll();
	}

	@Override
	public Disease addDisease(Disease disease) {
		return diseaseRepository.save(disease);
	}

}
