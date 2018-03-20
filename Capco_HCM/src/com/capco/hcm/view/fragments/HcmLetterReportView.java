package com.capco.hcm.view.fragments;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.capco.hcm.model.HcmLetterReqObj;
import com.capco.hcm.sql.HcmLetterReqSqlDao;

@SessionScoped
@ManagedBean(name = "letterRequestView", eager = true)
public class HcmLetterReportView implements Serializable {

	@ManagedProperty("#{letterReqSqlDao}")
	HcmLetterReqSqlDao letterReqDao;
	private HcmLetterReqObj letterReqObj;
	
	public void getAllLetterReqByEmployees(){
		List<HcmLetterReqObj> getAllRequestsForLetters = letterReqDao.getAllRequestsForLetters();
	}
	
	public HcmLetterReqSqlDao getLetterReqDao() {
		return letterReqDao;
	}
	public void setLetterReqDao(HcmLetterReqSqlDao letterReqDao) {
		this.letterReqDao = letterReqDao;
	}
	public HcmLetterReqObj getLetterReqObj() {
		return letterReqObj;
	}
	public void setLetterReqObj(HcmLetterReqObj letterReqObj) {
		this.letterReqObj = letterReqObj;
	}
}