package com.capco.hcm.model;

import java.util.Date;


/**
 * @author Mohit Gangil
 *
 */

public class NomineeTrackerObj {


	private String empid;
	private Integer serialNo;
	private boolean deletedB;
	private String dependentsName;
	private String relation;
	private boolean gender;
	private Date   dateOfBirthOfNominee;
	private String ageOfNominee;
	private String emailId;
	private String insertedBy;
	private Date   insertedDate;
	
	// All Getter and Setter
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public boolean getDeletedB() {
		return deletedB;
	}
	public void setDeletedB(boolean deletedB) {
		this.deletedB = deletedB;
	}
	public String getDependentsName() {
		return dependentsName;
	}
	public void setDependentsName(String dependentsName) {
		this.dependentsName = dependentsName;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public boolean getGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getDateOfBirthOfNominee() {
		return dateOfBirthOfNominee;
	}
	public void setDateOfBirthOfNominee(Date dateOfBirthOfNominee) {
		this.dateOfBirthOfNominee = dateOfBirthOfNominee;
	}
	public String getAgeOfNominee() {
		return ageOfNominee;
	}
	public void setAgeOfNominee(String ageOfNominee) {
		this.ageOfNominee = ageOfNominee;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	
	
}
