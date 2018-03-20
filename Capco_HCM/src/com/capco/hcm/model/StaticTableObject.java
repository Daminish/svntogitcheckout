package com.capco.hcm.model;


/**
 * @author Mohit Gangil
 *
 */

public class StaticTableObject {

	private String fieldName;
	private String fieldKey;
	private String fieldCategory;
	private boolean deletedB;

	// All Getter and Setter
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldCategory() {
		return fieldCategory;
	}
	public void setFieldCategory(String fieldCategory) {
		this.fieldCategory = fieldCategory;
	}
	public boolean getDeletedB() {
		return deletedB;
	}
	public void setDeletedB(boolean deletedB) {
		this.deletedB = deletedB;
	}
	public String getFieldKey() {
		return fieldKey;
	}
	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}
}