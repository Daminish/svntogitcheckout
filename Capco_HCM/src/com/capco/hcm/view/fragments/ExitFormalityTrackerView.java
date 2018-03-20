package com.capco.hcm.view.fragments;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.capco.hcm.model.ExitFormalityTrackerObj;
import com.capco.hcm.sql.ExitFormalityTrackerDao;

/**
 * @author Mohit Gangil
 *
 */
@ViewScoped
@ManagedBean (name = "exitFormTrkr")
public class ExitFormalityTrackerView implements Serializable {
	private static final long serialVersionUID = -13132811025386211L;

	private ExitFormalityTrackerObj exitFormTrkrObj;
	@ManagedProperty (value="#{exitFormilityDao}")
	private ExitFormalityTrackerDao exitFormTrkrDao;
	private List<ExitFormalityTrackerObj> exitsearchhistory;
	
	private List<ExitFormalityTrackerObj> exithistory;
	private String forLoadExitId;
	
	//Getters and Setters
	public ExitFormalityTrackerObj getExitFormTrkrObj() {
		if(exitFormTrkrObj == null)
			exitFormTrkrObj = new ExitFormalityTrackerObj();
		return exitFormTrkrObj;
	}
	public void setExitFormTrkrObj(ExitFormalityTrackerObj exitFormTrkrObj) {
		this.exitFormTrkrObj = exitFormTrkrObj;
	}
	public String getForLoadExitId() {
		return forLoadExitId;
	}
	public void setForLoadExitId(String forLoadExitId) {
		this.forLoadExitId = forLoadExitId;
	}
	
	public List<ExitFormalityTrackerObj> getExitsearchhistory() {
		return exitsearchhistory;
	}
	public void setExitsearchhistory(List<ExitFormalityTrackerObj> exitsearchhistory) {
		this.exitsearchhistory = exitsearchhistory;
	}

	
	public ExitFormalityTrackerDao getExitFormTrkrDao() {
		return exitFormTrkrDao;
	}
	public void setExitFormTrkrDao(ExitFormalityTrackerDao exitFormTrkrDao) {
		this.exitFormTrkrDao = exitFormTrkrDao;
	}
	public List<ExitFormalityTrackerObj> getExithistory() {
		if(exithistory == null)
			exithistory = new ArrayList<ExitFormalityTrackerObj>();
		return exithistory;
	}

	public void setExithistory(List<ExitFormalityTrackerObj> exithistory) {
		this.exithistory = exithistory;
	}
	
	
	// METHOD STARTS HERE
	public void getExitFormalityTrackerDetails(){
		exitFormTrkrObj.setEmployeeId("");//TODO add employee id from session
		exitFormTrkrObj = exitFormTrkrDao.getExitFormailityTrackerStatus(exitFormTrkrObj.getEmployeeId());
	}
	
	public  void loadExitFormalitytHistory() {
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for Exit formaloty is"+empid);
		if(empid !=null)
		{
		exithistory = exitFormTrkrDao.getExitDetails(empid);
		}
	}

	public void updateProbationTracker(ExitFormalityTrackerObj form){
		
		
		boolean success = exitFormTrkrDao.updateExitFormality(form);
		
		if(success)
		{
			loadExitFormalitytHistory();
			System.out.println("hello update successfully done");
		}
	}
	
		public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	      
	    }
	
		/**
		 * 
		 * @param empToLoad
		 * @return
		 */
		public void setSelectedExitInfoForLoad(ExitFormalityTrackerObj exit){
			forLoadExitId = exit.getEmployeeId();
					
		}
		
		/**
		 * 
		 * @return
		 */
		public String loadSelectedExitInfo(){
			System.out.println("Loading data for Probation: " + forLoadExitId);
			
			exitFormTrkrObj.setEmployeeId(forLoadExitId);
			exitFormTrkrObj = exitFormTrkrDao.getExitFormailityTrackerStatus(exitFormTrkrObj.getEmployeeId());
			return "Framework";
		
		}
		
		
		
	public void deleteExitTracker(ExitFormalityTrackerObj exit)
	{
		System.out.println("lkya bhua bee");
		
		boolean success = exitFormTrkrDao.deleteFieldForExit(exit);
		if(success)
		{
			
				loadExitFormalitytHistory();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	public void insertExitFormalityInfo() throws IOException{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the insert exit formality session" +hello);
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		exitFormTrkrObj.setEmployeeId(empid);//TODO add employee id from session
		exitFormTrkrObj.setInsertedBy(hello);//TODO add user id from session

	
		boolean success = exitFormTrkrDao.insertExitFormalityTrackerStatus(exitFormTrkrObj);
		if(success){
			//TODO call summary view to load values
		
			loadExitFormalitytHistory();
			
			System.out.println("successfully inserted records for exit formality");
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
			loadExitFormalitytHistory();
			
		}
		
		
	}	
	
	

}