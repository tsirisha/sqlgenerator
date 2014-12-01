package com.sqlgen.descriptor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sqlgen.util.AnnotationsUtil;

/*
 * This class used to store entity information of given DAO class.
 */
public class DbEntityDescriptor {
	private final String dbEntityName;
	private final List<String> dbColumnNames;
	private final Map<String, String> dbJoins;

	public DbEntityDescriptor(Class<?> type) {
		super();
		dbEntityName = AnnotationsUtil.resolveEntityNames(type);
		dbColumnNames = AnnotationsUtil.resolveFieldNames(type);
		dbJoins = AnnotationsUtil.resolveJoins(type);
	}

	public String getDbEntityName() {
		return dbEntityName;
	}

	public List<String> getDbColumnNames() {
		return dbColumnNames;
	}

	public Map<String, String> getDbJoins() {
		return dbJoins;
	}

	public Set<String> getDbFieldSet() {
		return new HashSet<String>(this.dbColumnNames);
	}
	public Set<String> getDbEntityFieldSet() {
		Set<String> dbEntityFieldSet=new HashSet<String>();
		Iterator it=this.dbColumnNames.iterator();
		while(it.hasNext()){
			dbEntityFieldSet.add(this.dbEntityName+"."+it.next());
		}
		return dbEntityFieldSet;
	}

}
