package com.capco.hcm.model;

import java.util.List;

public class ReportGenerateObj {

	private String selectreport;	
	private String uploadedfile;
	
	private List<EmployeeBasicInfoObj> basicInfoList;
	
	
	public List<EmployeeBasicInfoObj> getBasicInfoList() {
		return basicInfoList;
	}
	public void setBasicInfoList(List<EmployeeBasicInfoObj> basicInfoList) {
		this.basicInfoList = basicInfoList;
	}
	public String getSelectreport() {
		return selectreport;
	}
	public void setSelectreport(String selectreport) {
		this.selectreport = selectreport;
	}
	public String getUploadedfile() {
		return uploadedfile;
	}
	public void setUploadedfile(String uploadedfile) {
		this.uploadedfile = uploadedfile;
	}
	
	
	
}
