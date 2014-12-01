package com.sqlgen.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sqlgen.exceptions.DbSelectBuilderException;
import com.sqlgen.sql.SelectBuilder;

public class SqlBuilderTest {

	@Test
	public void validTest(){
		try{
			System.out.println("validTest()");
    		List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("Student");
        	tables.add("StudentToGrade");
        	columns.add("id");
        	columns.add("name");
        	columns.add("gradeID");
        	joins.add("id");
        	joins.add("studentID");
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	String sql=sb.selectQuery();
        	assertNotNull(sql);
        	System.out.println("Resulting Query: "+sql);
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		ds.printStackTrace();
    	}
	}
	@Test
	public void invalidColumnSeperatorTest() {
		try{
			System.out.println("invalidColumnSeperatorTest()");
			List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("Student");
        	tables.add("StudentToGrade");
        	tables.add("Grade");
        	columns.add("studentID");
        	columns.add("gradeID");
        	columns.add("Student.xxyz");
        	columns.add("Grade.name");
        	joins.add("Student.id");
        	joins.add("studentID");
        	joins.add("Grade.id");
        	joins.add("gradeID");
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	System.out.println("Resulting Query: "+sb.selectQuery());
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		//ds.printStackTrace();
    	}
	}
	@Test
	public void validColumnSeperatorTest() {
		try{
			System.out.println("validColumnSeperatorTest()");
			List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("Student");
        	tables.add("StudentToGrade");
        	tables.add("Grade");
        	columns.add("studentID");
        	columns.add("gradeID");
        	columns.add("Student.name");
        	columns.add("Grade.name");
        	joins.add("Student.id");
        	joins.add("studentID");
        	joins.add("Grade.id");
        	joins.add("gradeID");
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	System.out.println("Resulting Query: "+sb.selectQuery());
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		//ds.printStackTrace();
    	}
	}
	@Test
	public void validMultipleJoinTest() {
		try{
			System.out.println("validMultipleJoinTest()");
    		List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("Student");
        	tables.add("StudentToGrade");
        	tables.add("Grade");
        	columns.add("studentID");
        	columns.add("gradeID");
        	//columns.add("Student.id");
        	joins.add("Student.id");
        	joins.add("studentID");
        	joins.add("Grade.id");
        	joins.add("gradeID");
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	System.out.println("Resulting Query: "+sb.selectQuery());
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		ds.printStackTrace();
    	}
	}
	
	@Test
	public void invalidColumnTest() {
		try{
			System.out.println("invalidColumnTest()");
    		List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("Student");
        	tables.add("StudentToGrade");
        	columns.add("id");
        	columns.add("name");
        	columns.add("gradeid");
        	joins.add("id");
        	joins.add("studentID");
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	System.out.println("Resulting Query: "+sb.selectQuery());
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		//ds.printStackTrace();
    	}
	}
	
	@Test
	public void invalidJoinTest() {
		try{
			System.out.println("invalidJoinTest()");
    		List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("Student");
        	tables.add("StudentToGrade");
        	columns.add("id");
        	columns.add("name");
        	columns.add("gradeID");
        	joins.add("name");
        	joins.add("studentID");
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	System.out.println("Resulting Query: "+sb.selectQuery());
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		//ds.printStackTrace();
    	}
	}
	
	@Test
	public void invalidJoinSeperatorTest() {
		try{
			System.out.println("invalidJoinSeperatorTest()");
    		List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("Student");
        	tables.add("StudentToGrade");
        	columns.add("id");
        	columns.add("name");
        	columns.add("gradeID");
        	joins.add("Student.name");
        	joins.add("studentID");
        	joins.add("Student.name.test");
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	System.out.println("Resulting Query: "+sb.selectQuery());
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		//ds.printStackTrace();
    	}
	}
	
	@Test
	public void invalidJoinColumnsTest() {
		try{
			System.out.println("invalidJoinColumnsTest()");
    		List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("Student");
        	tables.add("Grade");
        	columns.add("gender");
        	//columns.add("name");
        	joins.add("Student.id");
        	joins.add("Grade.id");
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	System.out.println("Resulting Query: "+sb.selectQuery());
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		//ds.printStackTrace();
    	}
	}
	
	@Test
	public void ambiguousColumnTest() {
		try{
			System.out.println("ambiguousColumn()");
    		List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("Student");
        	tables.add("Grade");
        	columns.add("id");
        	
        	columns.add("name");
     
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	System.out.println("Resulting Query: "+sb.selectQuery());
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		//ds.printStackTrace();
    	}
	}
	
	@Test
	public void invalidTableTest() {
		try{
			System.out.println("invalidTableTest()");
    		List<String> tables=new ArrayList<String>();
        	List<String> columns=new ArrayList<String>();
        	List<String> joins=new ArrayList<String>();
        	tables.add("StudentTest");
        	tables.add("StudentToGrade");
        	columns.add("id");
        	
        	columns.add("name");
        	columns.add("gradeID");
        	
        	joins.add("id");
        	joins.add("studentID");
        	SelectBuilder sb=new SelectBuilder(tables,columns,joins);
        	System.out.println("Resulting Query: "+sb.selectQuery());
    	}catch(DbSelectBuilderException ds){
    		System.out.println("Error Message: "+ds.getMessage());
    		//ds.printStackTrace();
    	}
	}

}
