package com.sqlgen.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqlgen.annotations.DbEntity;
import com.sqlgen.annotations.DbField;
import com.sqlgen.annotations.DbJoinTo;

public class AnnotationsUtil {
    /*
     This method get Entity Name of given DAO class
     */
	public static String resolveEntityNames(Class<?> type) {
		String tableName = null;
		DbEntity dbTable = (DbEntity) type.getAnnotation(DbEntity.class);
		if (dbTable != null) {
			tableName = dbTable.value().trim();
		}
		return tableName;
	}
   /*
    * This method get Joins associated with given entity. It returns HashMap of joinname,fieldname
    * associated to join
    */
	public static Map<String, String> resolveJoins(Class<?> type) {
		Map<String, String> joinNames = new HashMap<String, String>();
		try {

			for (java.lang.reflect.Field field : type.getDeclaredFields())
				if (field != null) {
					DbJoinTo dbJoinName = (DbJoinTo) field
							.getAnnotation(DbJoinTo.class);
					if (dbJoinName != null) {
						joinNames.put(dbJoinName.joinName(), field.getName());
					}
				}
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return joinNames;
	}
/*
 * This method gets Field names associated with given entity.
 */
	public static List<String> resolveFieldNames(Class<?> type) {
		List<String> columnNames = new ArrayList<String>();
		try {

			for (java.lang.reflect.Field field : type.getDeclaredFields())
				if (field != null) {
					DbField dbColumn = (DbField) field
							.getAnnotation(DbField.class);
					if (dbColumn != null) {
						columnNames.add(dbColumn.value());
					}
				}
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return columnNames;
	}
}
