package com.capco.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="visarequest")
public class VisaRequest implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "sequence_visarequest_id", strategy = "com.capco.ids.VisaRequestIDGenerator")
	@GeneratedValue(generator = "sequence_visarequest_id") 
	@Column(name="visarequestid")
	private String visarequestid;
	
	@Column(name="enteredby")
	private int enteredby;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="entereddate")
	private Date entereddate;
	
	@Column(name="selforothers")
	private boolean selforothers;
	
	@Column(name="travelersempid")
	private int travelersempid;
	
	@Column(name="travelersname")
	private String travelersname;
	
	@Column(name="travelersmlevel")
	private String travelersmlevel;
	
	@Column(name="travelersdesignation")
	private String travelersdesignation;
	
	@Column(name="travelersgender")
	private String travelersgender;
	
	@Column(name="travelersmailid")
	private String travelersmailid;
	
	@Column(name="approvingmanager")
	private int approvingmanager;
	
	@Column(name="traveldestination")
	private String traveldestination;
	
	@Column(name="visatype")
	private String visatype;
	
	@Column(name="typeofvisit")
	private String typeofvisit;
	
	@Column(name="projectcode")
	private String projectcode;
	
	@Column(name="activitycode")
	private String activitycode;
	
	@Column(name="vrStatus")
	private String status;
	
	@Column(name="vrInQueue")
	private String inqueue;
	
	@Column(name="managerapprovalcomments")
	private String managerapprovalcomments;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="managerapprovaldate")
	private Date managerapprovaldate;
	
	@Column(name="isbillable")
	private boolean isbillable;
	
	@Column(name="financeteamcomments")
	private String financeteamcomments;
	
	@Column(name="financeteamupdatedby")
	private int financeteamupdatedby;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="financeteamupdateddate")
	private Date financeteamupdateddate;
	
	@Column(name="partnercomments")
	private String partnercomments;
	
	@Column(name="partnerupdatedby")
	private int partnerupdatedby;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="partnerupdateddate")
	private Date partnerupdateddate;

	public String getVisarequestid() {
		return visarequestid;
	}

	public void setVisarequestid(String visarequestid) {
		this.visarequestid = visarequestid;
	}

	public int getEnteredby() {
		return enteredby;
	}

	public void setEnteredby(int enteredby) {
		this.enteredby = enteredby;
	}

	public Date getEntereddate() {
		return entereddate;
	}

	public void setEntereddate(Date entereddate) {
		this.entereddate = entereddate;
	}

	public boolean isSelforothers() {
		return selforothers;
	}

	public void setSelforothers(boolean selforothers) {
		this.selforothers = selforothers;
	}

	public int getTravelersempid() {
		return travelersempid;
	}

	public void setTravelersempid(int travelersempid) {
		this.travelersempid = travelersempid;
	}

	public String getTravelersname() {
		return travelersname;
	}

	public void setTravelersname(String travelersname) {
		this.travelersname = travelersname;
	}

	public String getTravelersmlevel() {
		return travelersmlevel;
	}

	public void setTravelersmlevel(String travelersmlevel) {
		this.travelersmlevel = travelersmlevel;
	}

	public String getTravelersdesignation() {
		return travelersdesignation;
	}

	public void setTravelersdesignation(String travelersdesignation) {
		this.travelersdesignation = travelersdesignation;
	}

	public String getTravelersgender() {
		return travelersgender;
	}

	public void setTravelersgender(String travelersgender) {
		this.travelersgender = travelersgender;
	}

	public String getTravelersmailid() {
		return travelersmailid;
	}

	public void setTravelersmailid(String travelersmailid) {
		this.travelersmailid = travelersmailid;
	}

	public int getApprovingmanager() {
		return approvingmanager;
	}

	public void setApprovingmanager(int approvingmanager) {
		this.approvingmanager = approvingmanager;
	}

	public String getTraveldestination() {
		return traveldestination;
	}

	public void setTraveldestination(String traveldestination) {
		this.traveldestination = traveldestination;
	}

	public String getVisatype() {
		return visatype;
	}

	public void setVisatype(String visatype) {
		this.visatype = visatype;
	}

	public String getTypeofvisit() {
		return typeofvisit;
	}

	public void setTypeofvisit(String typeofvisit) {
		this.typeofvisit = typeofvisit;
	}

	public String getProjectcode() {
		return projectcode;
	}

	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	public String getActivitycode() {
		return activitycode;
	}

	public void setActivitycode(String activitycode) {
		this.activitycode = activitycode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInqueue() {
		return inqueue;
	}

	public void setInqueue(String inqueue) {
		this.inqueue = inqueue;
	}

	public String getManagerapprovalcomments() {
		return managerapprovalcomments;
	}

	public void setManagerapprovalcomments(String managerapprovalcomments) {
		this.managerapprovalcomments = managerapprovalcomments;
	}

	public Date getManagerapprovaldate() {
		return managerapprovaldate;
	}

	public void setManagerapprovaldate(Date managerapprovaldate) {
		this.managerapprovaldate = managerapprovaldate;
	}

	public boolean isIsbillable() {
		return isbillable;
	}

	public void setIsbillable(boolean isbillable) {
		this.isbillable = isbillable;
	}

	public String getFinanceteamcomments() {
		return financeteamcomments;
	}

	public void setFinanceteamcomments(String financeteamcomments) {
		this.financeteamcomments = financeteamcomments;
	}

	public int getFinanceteamupdatedby() {
		return financeteamupdatedby;
	}

	public void setFinanceteamupdatedby(int financeteamupdatedby) {
		this.financeteamupdatedby = financeteamupdatedby;
	}

	public Date getFinanceteamupdateddate() {
		return financeteamupdateddate;
	}

	public void setFinanceteamupdateddate(Date financeteamupdateddate) {
		this.financeteamupdateddate = financeteamupdateddate;
	}

	public String getPartnercomments() {
		return partnercomments;
	}

	public void setPartnercomments(String partnercomments) {
		this.partnercomments = partnercomments;
	}

	public int getPartnerupdatedby() {
		return partnerupdatedby;
	}

	public void setPartnerupdatedby(int partnerupdatedby) {
		this.partnerupdatedby = partnerupdatedby;
	}

	public Date getPartnerupdateddate() {
		return partnerupdateddate;
	}

	public void setPartnerupdateddate(Date partnerupdateddate) {
		this.partnerupdateddate = partnerupdateddate;
	}
}
