package com.capco.hcm.view.fragments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import com.capco.hcm.model.EmployeeBasicInfoObj;
import com.capco.hcm.sql.EmployeeBasicInfoDao;
/**
 * @author Mohit Gangil
 *
 */


@SessionScoped
@ManagedBean(name = "empSearchView")
public class EmpSearchView implements Serializable{
	private static final long serialVersionUID = 189096390734352117L;

	//private EmployeeBasicInfoObj searchParams;
	private String empId;
	private String empName;
	private String secondaryEmpId;
	private String employeeStatus;
	private String designation;
	private String project;
	private List<EmployeeBasicInfoObj> empListFoundBySearchParam;
	
    @ManagedProperty("#{empBasicInfoDao}")
    private EmployeeBasicInfoDao searchDao;
    private List<EmployeeBasicInfoObj> empsearchhistory;
    
  

	// GETTER AND SETTER
    
    public List<EmployeeBasicInfoObj> getEmpsearchhistory() {
  		return empsearchhistory;
  	}

  	public void setEmpsearchhistory(List<EmployeeBasicInfoObj> empsearchhistory) {
  		this.empsearchhistory = empsearchhistory;
  	}
    public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

    public List<EmployeeBasicInfoObj> getEmpListFoundBySearchParam() {
		if(empListFoundBySearchParam == null)
			empListFoundBySearchParam = new ArrayList<EmployeeBasicInfoObj>();
		return empListFoundBySearchParam;
	}
	public void setEmpListFoundBySearchParam(List<EmployeeBasicInfoObj> empListFoundBySearchParam) {
		this.empListFoundBySearchParam = empListFoundBySearchParam;
	}
	public EmployeeBasicInfoDao getSearchDao() {
		return searchDao;
	}
	public void setSearchDao(EmployeeBasicInfoDao searchDao) {
		this.searchDao = searchDao;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getSecondaryEmpId() {
		return secondaryEmpId;
	}

	public void setSecondaryEmpId(String secondaryEmpId) {
		this.secondaryEmpId = secondaryEmpId;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	//METHOD STARTS HERE
	/**
     * 
     * @param actionEvent
     */
	public void loadEmpBasedOnSearchParams(ActionEvent actionEvent){
		empListFoundBySearchParam = new ArrayList<EmployeeBasicInfoObj>();
		empListFoundBySearchParam = searchDao.searchForEmployeeBasicInfo(empId, empName, secondaryEmpId, employeeStatus, designation, project);
	}
  
	
    
}
