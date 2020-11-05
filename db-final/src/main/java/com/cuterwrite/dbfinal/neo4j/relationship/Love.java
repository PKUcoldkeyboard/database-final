package com.cuterwrite.dbfinal.neo4j.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.cuterwrite.dbfinal.neo4j.entity.Person;

import lombok.Data;
import lombok.NoArgsConstructor;

/**  
 * @author Pang S.Z.
 * @create 2020-10-27 19:57:03 
 */
@RelationshipEntity(type = "LOVES")
@Data
@NoArgsConstructor
public class Love {
	@Id
	@GeneratedValue
	private Long id;
	@StartNode
	private Person startNode;
	@EndNode
	private Person endNode;
}
