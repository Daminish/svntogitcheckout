package com.capco.hcm.model;

import java.util.Date;


/**
 * @author Mohit Gangil
 *
 */

public class ExitFormalityTrackerObj {

	private String employeeId;
	private Date   lastWorkingDate;
	private Date   resignationDate;
	private String reasonForResignation;
	private String noticePeriod;
	private String comments;
	private boolean medicalInsuranceNotified;
	private boolean idAndEmailTerminated;
	private Date idAndEmailTerminatedDate;
	private String libraryBooksCollected;
	private boolean exitInterviewCompleted;
	private boolean birthdayAppDeletion; 
	private boolean salaryHold;
	private Date fnfCompleted;
	private boolean timesheetSubmitted;
	private boolean releivingLetterIssued;
	private Date releivingLetterDate;
	private boolean serviceLetterIssued;
	private Date serviceLetterDate;
	private boolean buyoutLetterIssued;
	private Date buyoutLetterDate;
	private boolean buyoutAmountPaid;
	private String buyoutAmount;
	private String buyoutNoOfDays;
	private boolean buyoutWaived;
	private String buyoutWaivedDays;
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
	
	public boolean getBirthdayAppDeletion() {
		return birthdayAppDeletion;
	}
	public void setBirthdayAppDeletion(boolean birthdayAppDeletion) {
		this.birthdayAppDeletion = birthdayAppDeletion;
	}
	public Date getIdAndEmailTerminatedDate() {
		return idAndEmailTerminatedDate;
	}
	public void setIdAndEmailTerminatedDate(Date idAndEmailTerminatedDate) {
		this.idAndEmailTerminatedDate = idAndEmailTerminatedDate;
	}
	public Date getLastWorkingDate() {
		return lastWorkingDate;
	}
	public void setLastWorkingDate(Date lastWorkingDate) {
		this.lastWorkingDate = lastWorkingDate;
	}
	public Date getResignationDate() {
		return resignationDate;
	}
	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}
	public String getReasonForResignation() {
		return reasonForResignation;
	}
	public void setReasonForResignation(String reasonForResignation) {
		this.reasonForResignation = reasonForResignation;
	}
	public String getNoticePeriod() {
		return noticePeriod;
	}
	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean getMedicalInsuranceNotified() {
		return medicalInsuranceNotified;
	}
	public void setMedicalInsuranceNotified(boolean medicalInsuranceNotified) {
		this.medicalInsuranceNotified = medicalInsuranceNotified;
	}
	public boolean getIdAndEmailTerminated() {
		return idAndEmailTerminated;
	}
	public void setIdAndEmailTerminated(boolean idAndEmailTerminated) {
		this.idAndEmailTerminated = idAndEmailTerminated;
	}
	public String getLibraryBooksCollected() {
		return libraryBooksCollected;
	}
	public void setLibraryBooksCollected(String libraryBooksCollected) {
		this.libraryBooksCollected = libraryBooksCollected;
	}
	public boolean getExitInterviewCompleted() {
		return exitInterviewCompleted;
	}
	public void setExitInterviewCompleted(boolean exitInterviewCompleted) {
		this.exitInterviewCompleted = exitInterviewCompleted;
	}
	public boolean getSalaryHold() {
		return salaryHold;
	}
	public void setSalaryHold(boolean salaryHold) {
		this.salaryHold = salaryHold;
	}
	public Date getFnfCompleted() {
		return fnfCompleted;
	}
	public void setFnfCompleted(Date fnfCompleted) {
		this.fnfCompleted = fnfCompleted;
	}
	public boolean getTimesheetSubmitted() {
		return timesheetSubmitted;
	}
	public void setTimesheetSubmitted(boolean timesheetSubmitted) {
		this.timesheetSubmitted = timesheetSubmitted;
	}
	public boolean getReleivingLetterIssued() {
		return releivingLetterIssued;
	}
	public void setReleivingLetterIssued(boolean releivingLetterIssued) {
		this.releivingLetterIssued = releivingLetterIssued;
	}
	public Date getReleivingLetterDate() {
		return releivingLetterDate;
	}
	public void setReleivingLetterDate(Date releivingLetterDate) {
		this.releivingLetterDate = releivingLetterDate;
	}
	public boolean getServiceLetterIssued() {
		return serviceLetterIssued;
	}
	public void setServiceLetterIssued(boolean serviceLetterIssued) {
		this.serviceLetterIssued = serviceLetterIssued;
	}
	public Date getServiceLetterDate() {
		return serviceLetterDate;
	}
	public void setServiceLetterDate(Date serviceLetterDate) {
		this.serviceLetterDate = serviceLetterDate;
	}
	public boolean getBuyoutLetterIssued() {
		return buyoutLetterIssued;
	}
	public void setBuyoutLetterIssued(boolean buyoutLetterIssued) {
		this.buyoutLetterIssued = buyoutLetterIssued;
	}
	public Date getBuyoutLetterDate() {
		return buyoutLetterDate;
	}
	public void setBuyoutLetterDate(Date buyoutLetterDate) {
		this.buyoutLetterDate = buyoutLetterDate;
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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public boolean getBuyoutAmountPaid() {
		return buyoutAmountPaid;
	}
	public void setBuyoutAmountPaid(boolean buyoutAmountPaid) {
		this.buyoutAmountPaid = buyoutAmountPaid;
	}
	public String getBuyoutAmount() {
		return buyoutAmount;
	}
	public void setBuyoutAmount(String buyoutAmount) {
		this.buyoutAmount = buyoutAmount;
	}
	public boolean getBuyoutWaived() {
		return buyoutWaived;
	}
	public void setBuyoutWaived(boolean buyoutWaived) {
		this.buyoutWaived = buyoutWaived;
	}
	public String getBuyoutWaivedDays() {
		return buyoutWaivedDays;
	}
	public void setBuyoutWaivedDays(String buyoutWaivedDays) {
		this.buyoutWaivedDays = buyoutWaivedDays;
	}
	public String getBuyoutNoOfDays() {
		return buyoutNoOfDays;
	}
	public void setBuyoutNoOfDays(String buyoutNoOfDays) {
		this.buyoutNoOfDays = buyoutNoOfDays;
	}
}