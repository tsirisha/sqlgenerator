package com.sqlgen.sql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.sqlgen.descriptor.DbEntityDescriptor;
import com.sqlgen.exceptions.DbSelectBuilderException;
import com.sqlgen.util.*;

public class SelectBuilder {

	private List<String> theDbFields = new ArrayList<String>();
	private List<String> dbEntities = new ArrayList<String>();
	private List<String> dbJoins = new ArrayList<String>();
	private List<String> wheres = new ArrayList<String>();
	private List<String> dbJoinFields=new ArrayList<String>();
	private List<DbEntityDescriptor> dbEntityDescriptors=new ArrayList<DbEntityDescriptor>();
	
	public SelectBuilder() {
	}
	
	public SelectBuilder(List<String> dbEntities,List<String> dbFields,
			List<String> dbJoins) {

		this.dbEntities=dbEntities;
		this.theDbFields=dbFields;
		this.dbJoins=dbJoins;
	}
	//This method is to create select query
	private void appendQuery(StringBuilder aSql, List<String> list, String init,String sep) {
		boolean first = true;
		for (String s : list) {
			if (first) {
				aSql.append(init);
			} else {
				aSql.append(sep);
			}
			aSql.append(s);
			first = false;
		}
	}
	//This method is to add joins.
	private void appendQuery(StringBuilder sql, List<String> joinFields,List<String> joinFieldNames, String init,String sep) {
		
		boolean first = true;
		int i=0;
		Set<String> dbJoinNameSet=new HashSet<String>();
		dbJoinNameSet.addAll(joinFieldNames);
		for(String joinNameSet:dbJoinNameSet){
			List<String> fields=new ArrayList<String>();
			if (first) {
				sql.append(init);
			} else {
				
				sql.append(sep);
			}
			for(int idx=0;idx < joinFieldNames.size();idx++){
				if(joinNameSet.equals(joinFieldNames.get(idx))){
				fields.add(joinFields.get(idx));
			}
		}
		sql.append(fields.get(0));
		sql.append(" = ");
		sql.append(fields.get(1));
		first=false;
		
		
	
		
		}

		
	}
	/*
	 This method loads Entity data of entities passed on to this class
	 */
	public void dbLoadEntityDescriptors() throws DbSelectBuilderException{
		for(String dbEntity:dbEntities){
			EntityLookup elookup=EntityLookup.getInstance();
			DbEntityDescriptor dbEntityDescriptor=elookup.lookupEntityName(dbEntity);
			if(dbEntityDescriptor!=null){
				dbEntityDescriptors.add(dbEntityDescriptor);
			}else{
				throw new DbSelectBuilderException(DbValidations.ENTITY_DOESNT_EXIST+dbEntity);
			}
		}
	}
	
	
   /*
    This method build select query after doing necessary validations.
    */
	public String selectQuery() throws DbSelectBuilderException{
		dbLoadEntityDescriptors();
		//Call validation methods to validate columns and joins
		 DbValidations.doColumnValidations(dbEntityDescriptors, theDbFields);
		 dbJoinFields=DbValidations.doJoinValidations(dbEntityDescriptors,dbJoins);
		StringBuilder sql = new StringBuilder("select ");
		if (theDbFields.size() == 0) {
			sql.append("*");
		} else {
			appendQuery(sql, theDbFields, "", ", ");
		}
		appendQuery(sql, dbEntities, " from ", ", ");
		appendQuery(sql,dbJoins,dbJoinFields," where "," and ");
		return sql.toString();
	}

	public SelectBuilder where(String expr) {
		wheres.add(expr);
		return this;
	}
}