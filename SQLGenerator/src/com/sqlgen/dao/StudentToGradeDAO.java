package com.sqlgen.dao;

import com.sqlgen.annotations.DbEntity;
import com.sqlgen.annotations.DbField;
import com.sqlgen.annotations.DbJoinTo;
/*
 * Entity class for StudentToGrade table.
 */
@DbEntity (value="StudentToGrade")
public class StudentToGradeDAO {
	@DbField(value="studentID")
	@DbJoinTo(joinName="StudentIDJoin")
	int studentID;
	@DbField(value="gradeID")
	@DbJoinTo(joinName="GradeIDJoin")
	int gradeID;
}
