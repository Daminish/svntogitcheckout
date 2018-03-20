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

import com.capco.hcm.model.InductionTrackerObj;
import com.capco.hcm.sql.InductionTrackerDao;
/**
 * @author Mohit Gangil
 *
 */


@ViewScoped
@ManagedBean (name = "empIndTrkrView")
public class EmployeeInductionTrackerView implements Serializable {
	private static final long serialVersionUID = 5516977271359113080L;

	@ManagedProperty (value="#{inductionTrkrDao}")
	private InductionTrackerDao empIndTrkrDao;
	
	private List<InductionTrackerObj> inductionHistory;
	private InductionTrackerObj empIndTrkrObj;
	private String forLoadInductionId;
	private List<InductionTrackerObj> inductionsearchhistory;
	
	
	// GETTER SETTER
	public List<InductionTrackerObj> getInductionHistory() {
		return inductionHistory;
	}

	public void setInductionHistory(List<InductionTrackerObj> inductionHistory) {
		this.inductionHistory = inductionHistory;
	}
	
	public String getForLoadInductionId() {
		return forLoadInductionId;
	}

	public void setForLoadInductionId(String forLoadInductionId) {
		this.forLoadInductionId = forLoadInductionId;
	}

	public InductionTrackerObj getEmpIndTrkrObj() {
			if(empIndTrkrObj == null)
				empIndTrkrObj = new InductionTrackerObj();
			return empIndTrkrObj;
		}
		public void setEmpIndTrkrObj(InductionTrackerObj empIndTrkrObj) {
			this.empIndTrkrObj = empIndTrkrObj;
		}
		public InductionTrackerDao getEmpIndTrkrDao() {
			return empIndTrkrDao;
		}
		public void setEmpIndTrkrDao(InductionTrackerDao empIndTrkrDao) {
			this.empIndTrkrDao = empIndTrkrDao;
		}
		
		public List<InductionTrackerObj> getInductionsearchhistory() {
			return inductionsearchhistory;
		}

		public void setInductionsearchhistory(List<InductionTrackerObj> inductionsearchhistory) {
			this.inductionsearchhistory = inductionsearchhistory;
		}
	
	
	//METHOD START
	public  void loadInductionHistory() throws IOException{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for induction is"+empid);
		if(empid !=null)
		{
		inductionHistory = empIndTrkrDao.getInductionDetails(empid);
		}
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      
    }
	
	public void insertEmpInductionTrkrStatus() throws IOException{
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the Employee Induction session" +hello);

		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		
		empIndTrkrObj.setEmployeeId(empid);//TODO set the employee id from session
		empIndTrkrObj.setInsertedBy(hello);//TODO set the user id from session
		
		boolean success = empIndTrkrDao.insertInductionStatus(empIndTrkrObj);
		if(success)
		{
			loadInductionHistory();
			System.out.println("Insert the employee induction values");
			
			System.out.println(empIndTrkrObj.getFirstEmailSentDate());
			//TODO call summary view to load values
		}
	
	}
	
	public void updateInductionHistory(InductionTrackerObj induction){
		System.out.println(induction);
		
	boolean success = empIndTrkrDao.updateInduction(induction);
	
	if(success)
	{
		try {
			loadInductionHistory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello update successfully done");
	}
	}
	
	
	public void deleteInductionHistoryRecord(InductionTrackerObj induction){
		
		
		boolean success = empIndTrkrDao.deleteInductionHistory(induction);
		if(success)
		{
			try {
				loadInductionHistory();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	

	/**
	 * 
	 * @param empToLoad
	 * @return
	 */
	public void setSelectedInductionHistoryForLoad(InductionTrackerObj induction){
			System.out.println("hello");
			forLoadInductionId = induction.getEmployeeId();
	}
	
	/**
	 * 
	 * @return
	 */

	public String loadSelectedProjectInfo(){
		System.out.println("Loading data for project: " + forLoadInductionId);
		empIndTrkrObj.setEmployeeId(forLoadInductionId);
		empIndTrkrObj = empIndTrkrDao.getInductionStatus(empIndTrkrObj.getEmployeeId());
		
		return "Framework";
	}
	
	
	
	public void getInductionStatusTrkr(){
	
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		
		empIndTrkrObj.setEmployeeId(empid);//TODO set the employee id from session
		empIndTrkrDao.getInductionStatus(empIndTrkrObj.getEmployeeId());
	}
	
	@PostConstruct	
	public void init()
	{
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for induction is"+empid);
		if(empid !=null)
		{
			try {
				loadInductionHistory();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}	

	
}