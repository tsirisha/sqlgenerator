package com.sqlgen.util;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

import com.sqlgen.descriptor.DbEntityDescriptor;

/*
 * EntityLook class is a singleton class to instantiate all entities.
 * Naming convention all entities should end with DAO example StudentDAO.java also 
 * preferably put all entity classes in dao package.
 */
public class EntityLookup {
	private static EntityLookup instance;
	Map<String, DbEntityDescriptor> entityNamesMap = new HashMap<String, DbEntityDescriptor>();

	public static EntityLookup getInstance() {
		if (instance == null) {
			synchronized (EntityLookup.class) {
				if (instance == null) {
					instance = new EntityLookup();
				}
			}
		}
		return instance;
	}

	private EntityLookup() {
		try {
			String[] classNames = ClassFinder.getClasspathFileNames();
			for (String className : classNames) {
				if (className.indexOf("DAO") >= 0) {
					String entityName = className.replace('/', '.');
					try {
						String entity = entityName.substring(0,
								entityName.indexOf(".class"));

						DbEntityDescriptor dbEntityDesc = new DbEntityDescriptor(
								Class.forName(entity));
						entityNamesMap.put(dbEntityDesc.getDbEntityName(),
								dbEntityDesc);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DbEntityDescriptor lookupEntityName(String entityName) {
		return (DbEntityDescriptor) this.entityNamesMap.get(entityName);
	}
}
