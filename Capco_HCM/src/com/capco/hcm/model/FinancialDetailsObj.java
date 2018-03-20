package com.capco.hcm.model;

import java.util.Date;


/**
 * @author Mohit Gangil
 *
 */

public class FinancialDetailsObj {

	private String employeeId;
	private Date   pfDocsReceivedDate;
	private Date   sentToPfOfficeDate;
	private String uanNumber;
	private String bankName;
	private String bankBranchName;
	private String bankIifscCode;
	private String bankAccountNumber;
	private String nameOfAccountHolder;
	private String insertedBy;
	private Date   insertedDate;
	private boolean deletedB;

	// All Getter and Setter
	public boolean getDeletedB() {
		return deletedB;
	}
	public void setDeletedB(boolean deletedB) {
		this.deletedB = deletedB;
	}
	public Date getPfDocsReceivedDate() {
		return pfDocsReceivedDate;
	}
	public void setPfDocsReceivedDate(Date pfDocsReceivedDate) {
		this.pfDocsReceivedDate = pfDocsReceivedDate;
	}
	public Date getSentToPfOfficeDate() {
		return sentToPfOfficeDate;
	}
	public void setSentToPfOfficeDate(Date sentToPfOfficeDate) {
		this.sentToPfOfficeDate = sentToPfOfficeDate;
	}
	public String getUanNumber() {
		return uanNumber;
	}
	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankIifscCode() {
		return bankIifscCode;
	}
	public void setBankIifscCode(String bankIifscCode) {
		this.bankIifscCode = bankIifscCode;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getNameOfAccountHolder() {
		return nameOfAccountHolder;
	}
	public void setNameOfAccountHolder(String nameOfAccountHolder) {
		this.nameOfAccountHolder = nameOfAccountHolder;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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