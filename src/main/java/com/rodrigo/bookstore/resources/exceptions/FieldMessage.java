package com.rodrigo.bookstore.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;

	//==================================================================
	//constructs
	public FieldMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	
	//==================================================================
	//getters e setters
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDefaultMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
