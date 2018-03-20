package com.capco.hcm.model;

import java.util.Date;


/**
 * @author Mohit Gangil
 *
 */
public class SkillSetTrainingInfoObj {

	private String empId;
	private String primarySkills;
	private String currentSkill;
	private String capcoCertification;
	private String otherTrainings;
	private String priorExp;
	private String totalExp;
	private String capcoExp;
	private boolean deletedB;
	private String insertedBy;
	private Date insertedDate;
	
	// All Getter and Setter
	public boolean getDeletedB() {
		return deletedB;
	}
	public void setDeletedB(boolean deletedB) {
		this.deletedB = deletedB;
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPrimarySkills() {
		return primarySkills;
	}
	public void setPrimarySkills(String primarySkills) {
		this.primarySkills = primarySkills;
	}
	public String getCurrentSkill() {
		return currentSkill;
	}
	public void setCurrentSkill(String currentSkill) {
		this.currentSkill = currentSkill;
	}
	public String getCapcoCertification() {
		return capcoCertification;
	}
	public void setCapcoCertification(String capcoCertification) {
		this.capcoCertification = capcoCertification;
	}
	public String getOtherTrainings() {
		return otherTrainings;
	}
	public void setOtherTrainings(String otherTrainings) {
		this.otherTrainings = otherTrainings;
	}
	public String getPriorExp() {
		return priorExp;
	}
	public void setPriorExp(String priorExp) {
		this.priorExp = priorExp;
	}
	public String getTotalExp() {
		return totalExp;
	}
	public void setTotalExp(String totalExp) {
		this.totalExp = totalExp;
	}
	public String getCapcoExp() {
		return capcoExp;
	}
	public void setCapcoExp(String capcoExp) {
		this.capcoExp = capcoExp;
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