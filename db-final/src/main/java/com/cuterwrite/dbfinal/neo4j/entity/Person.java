package com.cuterwrite.dbfinal.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;
import lombok.NoArgsConstructor;

/**  
 * @author Pang S.Z.
 * @create 2020-10-27 19:55:31 
 */
@NodeEntity(label = "Person")
@Data
@NoArgsConstructor
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	@Property(name = "name")
	private String name;
}
