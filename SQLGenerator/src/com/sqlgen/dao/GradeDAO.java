package com.sqlgen.dao;

import com.sqlgen.annotations.DbEntity;
import com.sqlgen.annotations.DbField;
import com.sqlgen.annotations.DbJoinTo;


/*
 * Entity class for Grade table.
 */
@DbEntity (value="Grade")
public class GradeDAO {
	@DbField(value="id")
	@DbJoinTo(joinName="GradeIDJoin")
	int id;
	@DbField(value="name")
	String name;
}
