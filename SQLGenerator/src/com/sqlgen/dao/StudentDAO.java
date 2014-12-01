package com.sqlgen.dao;

import com.sqlgen.annotations.DbEntity;
import com.sqlgen.annotations.DbField;
import com.sqlgen.annotations.DbJoinTo;
/*
 * Entity class for Student table.
 */
@DbEntity (value="Student")
public class StudentDAO {
@DbField(value="id")
@DbJoinTo(joinName="StudentIDJoin")
int id;
@DbField(value="name")
String name;
@DbField(value="gender")
String gender;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}

}
