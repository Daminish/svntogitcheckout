package com.capco.hcm.view.fragments;


import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.capco.hcm.model.FinancialDetailsObj;
import com.capco.hcm.sql.FinancialDetailsDao;


/**
 * @author Mohit Gangil
 *
 */
@ViewScoped
@ManagedBean (name = "finDetTrkrView")
public class FinancialDetailsTrackerView implements Serializable {
	private static final long serialVersionUID = 2161676159196038248L;

	private FinancialDetailsObj finDetTrkrObj;
	@ManagedProperty (value = "#{finDetailDao}")
	private FinancialDetailsDao finDetDao;
	private List<FinancialDetailsObj> financesearchhistory;
	


	private List<FinancialDetailsObj> financeHistory;
	private String forLoadFinanceId;
		 
	 
	// GETTER AND SETTER
	public List<FinancialDetailsObj> getFinanceHistory() {
		return financeHistory;
	}

	public void setFinanceHistory(List<FinancialDetailsObj> financeHistory) {
		this.financeHistory = financeHistory;
	}

	public List<FinancialDetailsObj> getFinancesearchhistory() {
		return financesearchhistory;
	}

	public void setFinancesearchhistory(List<FinancialDetailsObj> financesearchhistory) {
		this.financesearchhistory = financesearchhistory;
	}

	
	public FinancialDetailsDao getFinDetDao() {
			return finDetDao;
		}
	public void setFinDetDao(FinancialDetailsDao finDetDao) {
			this.finDetDao = finDetDao;
		}
	public FinancialDetailsObj getFinDetTrkrObj() {
			if(finDetTrkrObj == null)
				finDetTrkrObj = new FinancialDetailsObj();
			return finDetTrkrObj;
		}
	public void setFinDetTrkrObj(FinancialDetailsObj finDetTrkrObj) {
			this.finDetTrkrObj = finDetTrkrObj;
		}

	public String getForLoadFinanceId() {
		return forLoadFinanceId;
	}

	public void setForLoadFinanceId(String forLoadFinanceId) {
		this.forLoadFinanceId = forLoadFinanceId;
	}

	
	//METHOD STARTS HERE
	public  void loadFinanceHistory() throws IOException{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for FInance history is"+empid);
		if(empid !=null)
		{
		financeHistory = finDetDao.getFinanceDetails(empid);
		}
	}
	
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      
    }
	
	
	public void getFinDetailsForFinance(){
		finDetTrkrObj.setEmployeeId(finDetTrkrObj.getEmployeeId());//TODO add employee id from session
		finDetDao.getFinancialDetailsTrackerStatus(finDetTrkrObj.getEmployeeId());
	}
	
	public void insertFinancialDetailsInfo() throws IOException{
	
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the insert travel Bond session" +hello);
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		finDetTrkrObj.setEmployeeId(empid);//TODO add employee id from session
		finDetTrkrObj.setInsertedBy(hello);//TODO add user id from session
		boolean success = finDetDao.insertFinancialDetailsTrackerStatus(finDetTrkrObj);
		if(success)
		{
			loadFinanceHistory();
			//TODO call summary view to load values 
			System.out.println("insert the financial values");
		}
	}
	
	
	
	public void updateFinanceHistory(FinancialDetailsObj finance){
		System.out.println(finance);
		
	boolean success = finDetDao.updateFinance(finance);
	
	if(success)
	{
		try {
			loadFinanceHistory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello update successfully done");
	}
	}
	
	

	/**
	 * 
	 * @param empToLoad
	 * @return
	 */
	public void setSelectedFinanceHistoryForLoad(FinancialDetailsObj finance){
			System.out.println("hello");
			forLoadFinanceId = finance.getEmployeeId();
	}
	
	/**
	 * 
	 * @return
	 */

	public String loadSelectedProjectInfo(){
		System.out.println("Loading data for project: " + forLoadFinanceId);
		finDetTrkrObj.setEmployeeId(forLoadFinanceId);
		finDetTrkrObj = finDetDao.getFinancialDetailsTrackerStatus(finDetTrkrObj.getEmployeeId());
		
		return "Framework";
	}
	
	
	
	public void deleteFinanceHistoryRecord(FinancialDetailsObj finance){
		
		
		boolean success = finDetDao.deleteFinanceHistory(finance);
		if(success)
		{
			try {
				loadFinanceHistory();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}

	
	@PostConstruct	
	public void init()
	{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for project history is"+empid);
		if(empid !=null)
		{
			try {
				loadFinanceHistory();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}	
	
}