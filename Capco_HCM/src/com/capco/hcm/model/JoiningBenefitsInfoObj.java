package com.capco.hcm.model;

import java.util.Date;


/**
 * @author Mohit Gangil
 *
 */
public class JoiningBenefitsInfoObj {

	private String empId;
	private boolean relocAssistance;
	private String relocAssistAmt;
	private boolean joiningBonus;
	private String joiningBonusAmt;
	private boolean referralBonus;
	private String referralBonusAmt;
	private String referrealCandidateName;
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
	public boolean getRelocAssistance() {
		return relocAssistance;
	}
	public void setRelocAssistance(boolean relocAssistance) {
		this.relocAssistance = relocAssistance;
	}
	public String getRelocAssistAmt() {
		return relocAssistAmt;
	}
	public void setRelocAssistAmt(String relocAssistAmt) {
		this.relocAssistAmt = relocAssistAmt;
	}
	public boolean getJoiningBonus() {
		return joiningBonus;
	}
	public void setJoiningBonus(boolean joiningBonus) {
		this.joiningBonus = joiningBonus;
	}
	public String getJoiningBonusAmt() {
		return joiningBonusAmt;
	}
	public void setJoiningBonusAmt(String joiningBonusAmt) {
		this.joiningBonusAmt = joiningBonusAmt;
	}
	public boolean getReferralBonus() {
		return referralBonus;
	}
	public void setReferralBonus(boolean referralBonus) {
		this.referralBonus = referralBonus;
	}
	public String getReferralBonusAmt() {
		return referralBonusAmt;
	}
	public void setReferralBonusAmt(String referralBonusAmt) {
		this.referralBonusAmt = referralBonusAmt;
	}
	public String getReferrealCandidateName() {
		return referrealCandidateName;
	}
	public void setReferrealCandidateName(String referrealCandidateName) {
		this.referrealCandidateName = referrealCandidateName;
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