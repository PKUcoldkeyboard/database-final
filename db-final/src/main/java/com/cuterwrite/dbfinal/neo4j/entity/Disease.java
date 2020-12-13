package com.cuterwrite.dbfinal.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;
import lombok.NoArgsConstructor;

/**  
 * @author Pang S.Z.
 * @create 2020-10-27 22:10:38 
 */
@NodeEntity(label = "Disease")
@Data
@NoArgsConstructor
public class Disease {
	@Id
	@GeneratedValue
	private Long id;
	@Property(name = "name")
	private String name;
}
