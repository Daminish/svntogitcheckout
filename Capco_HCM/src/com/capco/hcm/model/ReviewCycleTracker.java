package com.capco.hcm.model;

import java.util.Date;

/**
 * @author Mohit Gangil
 *
 */

public class ReviewCycleTracker {

	private String reviewDate;
	private String reviewBand;
	private String empId;
	private String insertedBy;
	private Date   insertedDate;
	

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
	// All Getter and Setter
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewBand() {
		return reviewBand;
	}
	public void setReviewBand(String reviewBand) {
		this.reviewBand = reviewBand;
	}
}
