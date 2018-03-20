package com.capco.hcm.view.fragments;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.capco.hcm.model.HcmLetterReqObj;
import com.capco.hcm.sql.HcmLetterReqSqlDao;

/**
 * @author Mohit Gangil
 *
 */

@ViewScoped
@ManagedBean( name="letterReqView")
public class HcmLetterRequestView implements Serializable {
	private static final long serialVersionUID = 8006672611832856891L;

	@ManagedProperty("#{letterReqSqlDao}")
	HcmLetterReqSqlDao letterReqDao;
	
	private String currEmployeeId;
	private HcmLetterReqObj letterReqObj;
	private List<HcmLetterReqObj> 	pastLetReqList;
	private String forLoadHcmId;
	private List<HcmLetterReqObj> hcmsearchhistory;
	

	//GETTER AND SETTER
	public String getForLoadHcmId() {
		return forLoadHcmId;
	}

	public void setForLoadHcmId(String forLoadHcmId) {
		this.forLoadHcmId = forLoadHcmId;
	}

	public List<HcmLetterReqObj> getHcmsearchhistory() {
		return hcmsearchhistory;
	}

	public void setHcmsearchhistory(List<HcmLetterReqObj> hcmsearchhistory) {
		this.hcmsearchhistory = hcmsearchhistory;
	}
	
	public HcmLetterReqSqlDao getLetterReqDao() {
		return letterReqDao;
	}
	public void setLetterReqDao(HcmLetterReqSqlDao letterReqDao) {
		this.letterReqDao = letterReqDao;
	}

	public HcmLetterReqObj getLetterReqObj() {
		if(letterReqObj == null)
			letterReqObj = new HcmLetterReqObj();
		return letterReqObj;
	}
	public void setLetterReqObj(HcmLetterReqObj letterReqObj) {
		this.letterReqObj = letterReqObj;
	}

	public List<HcmLetterReqObj> getPastLetReqList() {
		
		return pastLetReqList;
	}
	public void setPastLetReqList(List<HcmLetterReqObj> pastLetReqList) {
		this.pastLetReqList = pastLetReqList;
	}
	
	public String getCurrEmployeeId() {
		return currEmployeeId;
	}
	public void setCurrEmployeeId(String currEmployeeId) {
		this.currEmployeeId = currEmployeeId;
	}

	// METHOD STARTS HERE
	public boolean checkDuplicateRecord()
	{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String	empId = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empId);
		
		System.out.println("comes in the checkDuplicateRecord");
		return letterReqDao.getDuplicate(empId, letterReqObj);
		
			
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      
    }
	
	
	public  void loadHcmLetterHistory() throws IOException{
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for load project history is"+empid);
		if(empid !=null)
		{
		pastLetReqList = letterReqDao.getHcmDetails(empid);
		}
	}
	
	
	
	public void insertNewLetterReqForEmployee() throws IOException{		
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the insert exit formality session" +hello);
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);		
		
		letterReqObj.setEmpId(empid);
		letterReqObj.setInsertedBy(hello);
		//check for the duplicate
		if(checkDuplicateRecord()){
			System.out.println("Do the updation");
			boolean success =letterReqDao.updateLetterRequest(empid, letterReqObj);
			if(success)
			{
				loadHcmLetterHistory();
				System.out.println("successfully updated done");
			}
			
		
		}else{
			boolean success = letterReqDao.insertLetterRequest(letterReqObj);
			if(success)
			{
				loadHcmLetterHistory();
				/*viewPastRequestForEmployee(empid);*/
				System.out.println(letterReqObj.getEmpName());
				System.out.println("Insert the HCM LETTER REQUEST");
				System.out.println("done");
			}
		}
		//ends here bharath
	}
	
	/**
	 * 
	 * @param empToLoad
	 * @return
	 */
	public void setSelectedHcmLetterForLoad(HcmLetterReqObj letterReqObj){
			System.out.println("hello");
			forLoadHcmId = letterReqObj.getEmpId();
	}
	


	@PostConstruct	
	public void init()
	{
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for HCM Letter is"+empid);
		if(empid !=null)
		{
			try {
				loadHcmLetterHistory();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}	
	
	
	
}