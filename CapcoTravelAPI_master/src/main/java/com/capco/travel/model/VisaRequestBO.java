package com.capco.travel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class is the VisaRequest Business class to map fields with DB columns
 * 
 */

@Entity
@Table(name = "VISA_REQUEST", schema = "CAPCO_TRAVEL_PORTAL")
public class VisaRequestBO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "uid")
	private int uid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_id")
	private MainRequestBO mainRequestBO;
	
	@Column(name = "travel_destination")
	private String travelDestination;
	
	@Column(name = "visa_type")
	private String visaType;
	
	@Column(name = "type_of_visit")
	private String typeOfVisit;
	
	@Column(name = "business_purpose")
	private String businessPurpose;
	
	@Column(name="collection_desk")
	private String collectionDesk;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "modified_on")
	private Date modifiedOn;
	/**
	 * @return the mainRequestBO
	 */
	public MainRequestBO getMainRequestBO() {
		return mainRequestBO;
	}

	/**
	 * @param mainRequestBO the mainRequestBO to set
	 */
	public void setMainRequestBO(MainRequestBO mainRequestBO) {
		this.mainRequestBO = mainRequestBO;
	}

	/**
	 * @return the travelDestination
	 */
	public String getTravelDestination() {
		return travelDestination;
	}

	/**
	 * @param travelDestination the travelDestination to set
	 */
	public void setTravelDestination(String travelDestination) {
		this.travelDestination = travelDestination;
	}

	/**
	 * @return the visaType
	 */
	public String getVisaType() {
		return visaType;
	}

	/**
	 * @param visaType the visaType to set
	 */
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	/**
	 * @return the typeOfVisit
	 */
	public String getTypeOfVisit() {
		return typeOfVisit;
	}

	/**
	 * @param typeOfVisit the typeOfVisit to set
	 */
	public void setTypeOfVisit(String typeOfVisit) {
		this.typeOfVisit = typeOfVisit;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the modifiedOn
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * @return the businessPurpose
	 */
	public String getBusinessPurpose() {
		return businessPurpose;
	}

	/**
	 * @param businessPurpose the businessPurpose to set
	 */
	public void setBusinessPurpose(String businessPurpose) {
		this.businessPurpose = businessPurpose;
	}

	/**
	 * @return the collectionDesk
	 */
	public String getCollectionDesk() {
		return collectionDesk;
	}

	/**
	 * @param collectionDesk the collectionDesk to set
	 */
	public void setCollectionDesk(String collectionDesk) {
		this.collectionDesk = collectionDesk;
	}
	
	
}
