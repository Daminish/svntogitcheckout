package com.capco.hcm.model;

import java.util.Date;


/**
 * @author Mohit Gangil
 *
 */

public class DocAndBgvObj {

	private String employeeId;
	private boolean preOnboarding;
	private String owner;
	private Date   welcomeEmailSentDate;
	private Date   docReceivedOnDate;
	private Date   firstChaserDate;
	private Date   secondChaserDate;
	private Date   thirdChaserDate;
	private Date   escalationDate;
	private Date   newHireProvisionCreationDate;
	private boolean newHireProvision;
	private Date   bgvInitiatedDate;
	private String bgvRefNo;
	private Date   bgvInterimReportDate;
	private Date   bgvFinalReportDate;
	private String bgvReportColorCode;
	private String bgvSignOffBy;
	private Date   bgvSignOffDate;
	private Date   bgvClosureDate;
	private String packageInitiated;
	private String employment;
	private String courtCheck;
	private String identityCheck;
	private String countryCheck;
	private String referenceCheck;
	private String cvCheck;
	private boolean signedOfferLetterReceived;
	private boolean cvReceived;
	private boolean personalDetailsFormReceived;
	private boolean medicliamNominationReceived;
	private boolean pfNominationReceived;
	private boolean passportSizePhotoReceived;
	private boolean ndaReceived;
	private boolean signedCodeOfConductReceived;
	private boolean releivingLetterReceived;
	private boolean experienceLetterReceived;
	private boolean lastPayslipsReceived;
	private boolean marksheetsReceived;
	private String panCardReceived;
	private String passportNumberReceived;
	private Date passportExpiry;
	private String visaType;
	private String country;
	private boolean deletedB;
	private String insertedBy;
	private Date   insertedDate;
	
	// All Getter and Setter
	
	public boolean getDeletedB() {
		return deletedB;
	}
	public void setDeletedB(boolean deletedB) {
		this.deletedB = deletedB;
	}
	

	public String getPackageInitiated() {
		return packageInitiated;
	}
	public void setPackageInitiated(String packageInitiated) {
		this.packageInitiated = packageInitiated;
	}

	public String getEmployment() {
		return employment;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}
	public String getCourtCheck() {
		return courtCheck;
	}
	public void setCourtCheck(String courtCheck) {
		this.courtCheck = courtCheck;
	}
	public String getIdentityCheck() {
		return identityCheck;
	}
	public void setIdentityCheck(String identityCheck) {
		this.identityCheck = identityCheck;
	}
	public String getCountryCheck() {
		return countryCheck;
	}
	public void setCountryCheck(String countryCheck) {
		this.countryCheck = countryCheck;
	}
	public String getReferenceCheck() {
		return referenceCheck;
	}
	public void setReferenceCheck(String referenceCheck) {
		this.referenceCheck = referenceCheck;
	}
	public String getCvCheck() {
		return cvCheck;
	}
	public void setCvCheck(String cvCheck) {
		this.cvCheck = cvCheck;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getPassportExpiry() {
		return passportExpiry;
	}
	public void setPassportExpiry(Date passportExpiry) {
		this.passportExpiry = passportExpiry;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getWelcomeEmailSentDate() {
		return welcomeEmailSentDate;
	}
	public void setWelcomeEmailSentDate(Date welcomeEmailSentDate) {
		this.welcomeEmailSentDate = welcomeEmailSentDate;
	}
	public Date getDocReceivedOnDate() {
		return docReceivedOnDate;
	}
	public void setDocReceivedOnDate(Date docReceivedOnDate) {
		this.docReceivedOnDate = docReceivedOnDate;
	}
	public Date getFirstChaserDate() {
		return firstChaserDate;
	}
	public void setFirstChaserDate(Date firstChaserDate) {
		this.firstChaserDate = firstChaserDate;
	}
	public Date getSecondChaserDate() {
		return secondChaserDate;
	}
	public void setSecondChaserDate(Date secondChaserDate) {
		this.secondChaserDate = secondChaserDate;
	}
	public Date getThirdChaserDate() {
		return thirdChaserDate;
	}
	public void setThirdChaserDate(Date thirdChaserDate) {
		this.thirdChaserDate = thirdChaserDate;
	}
	public Date getEscalationDate() {
		return escalationDate;
	}
	public void setEscalationDate(Date escalationDate) {
		this.escalationDate = escalationDate;
	}
	public Date getNewHireProvisionCreationDate() {
		return newHireProvisionCreationDate;
	}
	public void setNewHireProvisionCreationDate(Date newHireProvisionCreationDate) {
		this.newHireProvisionCreationDate = newHireProvisionCreationDate;
	}
	public Date getBgvInitiatedDate() {
		return bgvInitiatedDate;
	}
	public void setBgvInitiatedDate(Date bgvInitiatedDate) {
		this.bgvInitiatedDate = bgvInitiatedDate;
	}
	public String getBgvRefNo() {
		return bgvRefNo;
	}
	public void setBgvRefNo(String bgvRefNo) {
		this.bgvRefNo = bgvRefNo;
	}
	public Date getBgvInterimReportDate() {
		return bgvInterimReportDate;
	}
	public void setBgvInterimReportDate(Date bgvInterimReportDate) {
		this.bgvInterimReportDate = bgvInterimReportDate;
	}
	public Date getBgvFinalReportDate() {
		return bgvFinalReportDate;
	}
	public void setBgvFinalReportDate(Date bgvFinalReportDate) {
		this.bgvFinalReportDate = bgvFinalReportDate;
	}
	public String getBgvReportColorCode() {
		return bgvReportColorCode;
	}
	public void setBgvReportColorCode(String bgvReportColorCode) {
		this.bgvReportColorCode = bgvReportColorCode;
	}
	public String getBgvSignOffBy() {
		return bgvSignOffBy;
	}
	public void setBgvSignOffBy(String bgvSignOffBy) {
		this.bgvSignOffBy = bgvSignOffBy;
	}
	public Date getBgvSignOffDate() {
		return bgvSignOffDate;
	}
	public void setBgvSignOffDate(Date bgvSignOffDate) {
		this.bgvSignOffDate = bgvSignOffDate;
	}
	public Date getBgvClosureDate() {
		return bgvClosureDate;
	}
	public void setBgvClosureDate(Date bgvClosureDate) {
		this.bgvClosureDate = bgvClosureDate;
	}

	public boolean getPreOnboarding() {
		return preOnboarding;
	}
	public void setPreOnboarding(boolean preOnboarding) {
		this.preOnboarding = preOnboarding;
	}
	public boolean getSignedOfferLetterReceived() {
		return signedOfferLetterReceived;
	}
	public void setSignedOfferLetterReceived(boolean signedOfferLetterReceived) {
		this.signedOfferLetterReceived = signedOfferLetterReceived;
	}
	public boolean getCvReceived() {
		return cvReceived;
	}
	public void setCvReceived(boolean cvReceived) {
		this.cvReceived = cvReceived;
	}
	public boolean getPersonalDetailsFormReceived() {
		return personalDetailsFormReceived;
	}
	public void setPersonalDetailsFormReceived(boolean personalDetailsFormReceived) {
		this.personalDetailsFormReceived = personalDetailsFormReceived;
	}
	public boolean getMedicliamNominationReceived() {
		return medicliamNominationReceived;
	}
	public void setMedicliamNominationReceived(boolean medicliamNominationReceived) {
		this.medicliamNominationReceived = medicliamNominationReceived;
	}
	public boolean getPfNominationReceived() {
		return pfNominationReceived;
	}
	public void setPfNominationReceived(boolean pfNominationReceived) {
		this.pfNominationReceived = pfNominationReceived;
	}
	public boolean getPassportSizePhotoReceived() {
		return passportSizePhotoReceived;
	}
	public void setPassportSizePhotoReceived(boolean passportSizePhotoReceived) {
		this.passportSizePhotoReceived = passportSizePhotoReceived;
	}
	public boolean getNdaReceived() {
		return ndaReceived;
	}
	public void setNdaReceived(boolean ndaReceived) {
		this.ndaReceived = ndaReceived;
	}
	public boolean getSignedCodeOfConductReceived() {
		return signedCodeOfConductReceived;
	}
	public void setSignedCodeOfConductReceived(boolean signedCodeOfConductReceived) {
		this.signedCodeOfConductReceived = signedCodeOfConductReceived;
	}
	public boolean getReleivingLetterReceived() {
		return releivingLetterReceived;
	}
	public void setReleivingLetterReceived(boolean releivingLetterReceived) {
		this.releivingLetterReceived = releivingLetterReceived;
	}
	public boolean getExperienceLetterReceived() {
		return experienceLetterReceived;
	}
	public void setExperienceLetterReceived(boolean experienceLetterReceived) {
		this.experienceLetterReceived = experienceLetterReceived;
	}
	public boolean getLastPayslipsReceived() {
		return lastPayslipsReceived;
	}
	public void setLastPayslipsReceived(boolean lastPayslipsReceived) {
		this.lastPayslipsReceived = lastPayslipsReceived;
	}
	public boolean getMarksheetsReceived() {
		return marksheetsReceived;
	}
	public void setMarksheetsReceived(boolean marksheetsReceived) {
		this.marksheetsReceived = marksheetsReceived;
	}
	public String getPanCardReceived() {
		return panCardReceived;
	}
	public void setPanCardReceived(String panCardReceived) {
		this.panCardReceived = panCardReceived;
	}
	public String getPassportNumberReceived() {
		return passportNumberReceived;
	}
	public void setPassportNumberReceived(String passportNumberReceived) {
		this.passportNumberReceived = passportNumberReceived;
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
	public boolean getNewHireProvision() {
		return newHireProvision;
	}
	public void setNewHireProvision(boolean newHireProvision) {
		this.newHireProvision = newHireProvision;
	}
	
}