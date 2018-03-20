package com.capco.hcm.view.fragments;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import com.capco.hcm.model.EmployeeBasicInfoObj;

/**
 * @author Mohit Gangil
 *
 */

@SessionScoped
@ManagedBean (name = "empSummaryView")
public class EmployeeSummaryView implements Serializable {
	private static final long serialVersionUID = -3311544477812131497L;

	//Emp summary view
	private String bioPrimaryEmpId;
	private String bioSecondaryEmpId;
	private String bioEmployeeName;
	private String bioEmpDesignation;
	private Date   bioJoiningDate;
	private String bioStatus;
	/* Check the current userID*/
	private String currentUserId;
	
	//GETTER AND SETTER
	public String getBioPrimaryEmpId() {
		return bioPrimaryEmpId;
	}
	public void setBioPrimaryEmpId(String bioPrimaryEmpId) {
		this.bioPrimaryEmpId = bioPrimaryEmpId;
	}
	public String getBioSecondaryEmpId() {
		return bioSecondaryEmpId;
	}
	public void setBioSecondaryEmpId(String bioSecondaryEmpId) {
		this.bioSecondaryEmpId = bioSecondaryEmpId;
	}
	public String getBioEmployeeName() {
		return bioEmployeeName;
	}
	public void setBioEmployeeName(String bioEmployeeName) {
		this.bioEmployeeName = bioEmployeeName;
	}
	public String getBioEmpDesignation() {
		return bioEmpDesignation;
	}
	public void setBioEmpDesignation(String bioEmpDesignation) {
		this.bioEmpDesignation = bioEmpDesignation;
	}
	public Date getBioJoiningDate() {
		return bioJoiningDate;
	}
	public void setBioJoiningDate(Date bioJoiningDate) {
		this.bioJoiningDate = bioJoiningDate;
	}
	public String getBioStatus() {
		return bioStatus;
	}
	public void setBioStatus(String bioStatus) {
		this.bioStatus = bioStatus;
	}
	public String getCurrentUserId() throws IOException {
		currentUserId = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
		if(currentUserId == null)
		{
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
			System.out.println("failed");
		}
		return currentUserId;
	}
	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
	
	@PostConstruct
	public void onLoadOrSaveOfEmployeeInfo(){
		EmployeeBasicInfoObj basicInfoObj = new EmployeeBasicInfoObj();
	
		bioPrimaryEmpId = basicInfoObj.getEmpId();
		bioSecondaryEmpId = basicInfoObj.getSecondaryEmpId();
		bioEmpDesignation = basicInfoObj.getDesignation();
		bioJoiningDate = basicInfoObj.getJoiningDate();
		bioStatus = basicInfoObj.getEmployeeStatus();
		bioEmployeeName = basicInfoObj.getEmpName();
		
		
	}

	
}