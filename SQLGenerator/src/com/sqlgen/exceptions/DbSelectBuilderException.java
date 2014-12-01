package com.sqlgen.exceptions;
/*
 * User defined exception to throw exceptions related to Select Query.
 */
public class DbSelectBuilderException extends Exception {

	private static final long serialVersionUID = 1L;
	String message="";
	public DbSelectBuilderException(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.toString();
	}
	public String toString(){
		return message;
	}
}