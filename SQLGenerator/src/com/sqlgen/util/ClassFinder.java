package com.sqlgen.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class ClassFinder {
	/**
	 * Returns the fully qualified class names of
	 * all the classes in the classpath.  Checks
	 * directories and zip files.  The FilenameFilter
	 * will be applied only to files that are in the
	 * zip files and the directories.  In other words,
	 * the filter will not be used to sort directories.
	 */
	public static String[] getClasspathFileNames() throws ZipException, IOException {
		StringTokenizer tokenizer = new StringTokenizer(System.getProperty("java.class.path"), File.pathSeparator, false);
		Set<String> filenames = new LinkedHashSet();

		while ( tokenizer.hasMoreTokens() ) {
			String classpathElement = tokenizer.nextToken();
			File classpathFile = new File(classpathElement);

			if ( classpathFile.exists() && classpathFile.canRead() ) {
				if ( classpathElement.toLowerCase().endsWith(".jar") ) {
					ZipFile zip = new ZipFile(classpathFile);
					Enumeration entries = zip.entries();

					while( entries.hasMoreElements() ) {
						ZipEntry entry = (ZipEntry) entries.nextElement();
						if ( ! entry.isDirectory() ) {
							filenames.add(entry.getName());
						}
					}

				} else if ( classpathFile.isDirectory() ) {
					// lets go through and find all of the subfolders
					Set<File> directoriesToSearch = new HashSet();
					Set<File> newDirectories = new HashSet();
					directoriesToSearch.add(classpathFile);
					String basePath = classpathFile.getAbsolutePath();

					while ( directoriesToSearch.size() > 0 ) {
						for ( File searchDirectory : directoriesToSearch ) {

							File[] directoryFiles = searchDirectory.listFiles();
							for ( File directoryFile : directoryFiles ) {
								if ( directoryFile.isDirectory()) {
									newDirectories.add(directoryFile);
								} else {
									filenames.add(directoryFile.getAbsolutePath().substring(basePath.length() + 1));
								}
							}

						}
						directoriesToSearch.clear();                   
						directoriesToSearch.addAll(newDirectories);
						newDirectories.clear();
					}
				}
			}
		}

		String[] uniqueNames = new String[filenames.size()];
		int index = 0;

		for ( String name : filenames ) {
			uniqueNames[index++] = name.replace("\\", "/");
		}

		return uniqueNames;
	}

	public static void main (String[] args) throws Exception {
		String[] names = ClassFinder.getClasspathFileNames();
		for ( String name : names ) {
			System.out.println(name);
		}
	}
}
