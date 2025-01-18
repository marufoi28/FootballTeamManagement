package model.field;

import model.address.Address;

public class Field {
	private int fieldId;
	private String fieldName;
	private Address address;
	
	public Field(int fieldId, String fieldName) {
		this.fieldId = fieldId;
		this.fieldName = fieldName;
	}
	
	public Field() {
	}

	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
