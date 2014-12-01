package com.sqlgen.util;

import java.util.*;
import java.util.Map.Entry;

import com.sqlgen.descriptor.*;
import com.sqlgen.exceptions.*;

public class DbValidations {
public final static String AMBIGUOUS_FIELDS="Ambiguous Fields Passed";
public final static String ENTITY_DOESNT_EXIST="Entity name doesn't Exists";
public final static String ZERO_FIELDS="Zero Fields Passed";
public final static String FIELD_DOESNT_EXIST="Invalid or ambiguous Fields Passed";
public final static String JOIN_DOESNT_EXIST="Join doesn't exist between tables";
public final static String JOIN_COLUMNSNOS_INVALID="Number of join columns passed invalid";
public final static String JOIN_COLUMN_INVALID="Join column passed invalid";
/*
This method performs necessary validations on columns and joins
*/
public static void doColumnValidations(List<DbEntityDescriptor> dbEntityDescriptors,List<String> dbFields) throws DbSelectBuilderException{
	if(dbEntityDescriptors!=null && dbEntityDescriptors.size()>0){
		// Column validations
		if(dbFields==null || dbFields.size()==0){
			throw new DbSelectBuilderException(DbValidations.ZERO_FIELDS);
		}else{
			//check for fields with same by adding to hashset and verifying size
			Set<String> dbFieldSet=new HashSet<String>(dbFields);
			if(dbFieldSet.size()<dbFields.size()){
				throw new DbSelectBuilderException(DbValidations.AMBIGUOUS_FIELDS);
			}else{
				int columnCount=0;
				//Verify if fields exists in entities by doing set retain all of fields passed and making count check to make sure all fields passed exists in entities.
				Set<String> dbEntityFieldSet=new HashSet<String>();
				for(DbEntityDescriptor dbEntityDescriptor:dbEntityDescriptors){
					Set<String> tempdbFieldSet=dbEntityDescriptor.getDbFieldSet();
					dbEntityFieldSet.addAll(dbEntityDescriptor.getDbEntityFieldSet());
					tempdbFieldSet.retainAll(dbFieldSet);
					columnCount+=tempdbFieldSet.size();
				}
				if(columnCount!=dbFieldSet.size()){
					dbEntityFieldSet.retainAll(dbFieldSet);
					columnCount+=dbEntityFieldSet.size();
					if(columnCount!=dbFieldSet.size()){
					throw new DbSelectBuilderException(DbValidations.FIELD_DOESNT_EXIST);
					}
				}
			}
		}

	}
}
public static List<String> doJoinValidations(List<DbEntityDescriptor> dbEntityDescriptors,List<String> dbJoins)throws DbSelectBuilderException{
	//Join Validations
	Set<String> dbJoinNameSet=new HashSet<String>();
	List<String> dbJoinNames=new ArrayList<String>();
	boolean joinNameExists=false;
	String dbJoinName="";
	if(dbJoins!=null && dbJoins.size()>0){
		//check equal number of fields passed to perform join conditions ex: two tables should pass two fields similarly 3 tables 4 columns.
		if(dbJoins.size()%2!=0){
			throw new DbSelectBuilderException(DbValidations.JOIN_COLUMNSNOS_INVALID);
		}else{
			//check if join name exists for each join field passed
			for(String dbJoin:dbJoins){
				//Add check for "." exists in join field passed
				String dbJoinArray[]=dbJoin.split("\\.");
				if(dbJoinArray.length>2){
					throw new DbSelectBuilderException(DbValidations.JOIN_COLUMN_INVALID);
				}
				joinNameExists=false;
				for(DbEntityDescriptor dbEntityDescriptor:dbEntityDescriptors){
					//check for . in join field name
					if(dbJoinArray.length>1)
					{
						if(dbEntityDescriptor.getDbEntityName().equals(dbJoinArray[0])){
							dbJoinName=getKeysByValue(dbEntityDescriptor.getDbJoins(),dbJoinArray[1]);
						if(dbJoinName!=null && !("").equals(dbJoinName)){
							joinNameExists=true;
							dbJoinNames.add(dbJoinName);
						}
						}
					}else{
						dbJoinName=getKeysByValue(dbEntityDescriptor.getDbJoins(),dbJoin);
						
						if(dbJoinName!=null && !("").equals(dbJoinName)){
							joinNameExists=true;
							dbJoinNames.add(dbJoinName);
					}
					}
				}if(!joinNameExists){
					throw new DbSelectBuilderException(DbValidations.JOIN_COLUMN_INVALID);
				}
			}
			//check if join exists between two tables number of join names should be 1/2 the total join fields passed
			dbJoinNameSet.addAll(dbJoinNames);
			if((dbJoinNames.size()/2)!=(dbJoinNameSet.size())){
				throw new DbSelectBuilderException(DbValidations.JOIN_DOESNT_EXIST);
			}
		}
	}
	return dbJoinNames;
	
}
public static String getKeysByValue(Map<String, String> tempJoinsMap, String dbJoin) {
    String key = new String();
    for (Entry<String,String> entry : tempJoinsMap.entrySet()) {
        if (dbJoin.equals(entry.getValue())) {
        	key= entry.getKey();
        }
    }
    return key;
}
}
