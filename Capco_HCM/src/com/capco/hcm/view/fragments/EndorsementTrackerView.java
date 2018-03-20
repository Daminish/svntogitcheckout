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

import com.capco.hcm.model.EndorsementTrackerObj;
import com.capco.hcm.model.NomineeTrackerObj;
import com.capco.hcm.sql.EndorsementTrackerDao;

/**
 * @author Mohit Gangil
 *
 */
@ViewScoped
@ManagedBean(name= "endorsementTrkrView")
public class EndorsementTrackerView implements Serializable {
	private static final long serialVersionUID = 8099382114805514725L;

	@ManagedProperty(value="#{endorseTrkrDao}")
	EndorsementTrackerDao endorsementDao;

	private EndorsementTrackerObj endorsementObj;
	private NomineeTrackerObj nomineetracker;
	private List<NomineeTrackerObj> nominee;
	private List<EndorsementTrackerObj> endorsementlist;
	private String forLoadEndorsentId;
	private List<EndorsementTrackerObj> endorsementsearchhistory;
	private List<NomineeTrackerObj> nomineesearchhistory;
	



	// GETTER AND SETTER
	public NomineeTrackerObj getNomineetracker() {
		if(nomineetracker == null)
			nomineetracker = new NomineeTrackerObj();
		return nomineetracker;
	}

	public void setNomineetracker(NomineeTrackerObj nomineetracker) {
		this.nomineetracker = nomineetracker;
	}

	public List<NomineeTrackerObj> getNominee() {
		return nominee;
	}

	public void setNominee(List<NomineeTrackerObj> nominee) {
		this.nominee = nominee;
	}
	
	
	public List<EndorsementTrackerObj> getEndorsementlist() {
		return endorsementlist;
	}

	public void setEndorsementlist(List<EndorsementTrackerObj> endorsementlist) {
		this.endorsementlist = endorsementlist;
	}
	public EndorsementTrackerObj getEndorsementObj() {
		if(endorsementObj == null)
			endorsementObj = new EndorsementTrackerObj();
		return endorsementObj;
	}

	public void setEndorsementObj(EndorsementTrackerObj endorsementObj) {
		this.endorsementObj = endorsementObj;
	}

	public EndorsementTrackerDao getEndorsementDao() {
		return endorsementDao;
	}
	public void setEndorsementDao(EndorsementTrackerDao endorsementDao) {
		this.endorsementDao = endorsementDao;
	}
	public String getForLoadEndorsentId() {
		return forLoadEndorsentId;
	}

	public void setForLoadEndorsentId(String forLoadEndorsentId) {
		this.forLoadEndorsentId = forLoadEndorsentId;
	}

	public List<EndorsementTrackerObj> getEndorsementsearchhistory() {
		return endorsementsearchhistory;
	}

	public void setEndorsementsearchhistory(List<EndorsementTrackerObj> endorsementsearchhistory) {
		this.endorsementsearchhistory = endorsementsearchhistory;
	}	

	public List<NomineeTrackerObj> getNomineesearchhistory() {
		return nomineesearchhistory;
	}

	public void setNomineesearchhistory(List<NomineeTrackerObj> nomineesearchhistory) {
		this.nomineesearchhistory = nomineesearchhistory;
	}
	
	
	//METHOD STARTS HERE
	public void insertNomineeStatus() throws IOException{
		

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the Insert Project History session" +hello);
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		nomineetracker.setEmpid(empid);
		nomineetracker.setInsertedBy(hello);
		
		boolean success = endorsementDao.insertNomineeTrackerStatus(nomineetracker);
		if(success)
		{
			loadNomineeHistory();
			System.out.println("insert the nominee details");
			//TODO
		}
		
	}

	public  void loadNomineeHistory() throws IOException{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for nomination is"+empid);
		if(empid !=null)
		{
		nominee = endorsementDao.getNomineeDetails(empid);
		}
	}
	
	public  void loadEndorsementHistory() throws IOException{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for endorsement history is"+empid);
		if(empid !=null)
		{
		endorsementlist = endorsementDao.getEndorsementDetails(empid);
		}
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      
    }


		
	public void saveInsuranceDetails() throws IOException{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the insert travel Bond session" +hello);
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		
		endorsementObj.setInsertedBy(hello);
		boolean success =  endorsementDao.insertEndorsementTrackerStatus(endorsementObj, empid);//TODO add employee
		if(success)
		{
			loadEndorsementHistory();
			System.out.println("insert Endorsment details");
			//TODO CODE
		}
	}
	
	public void deleteNomineeRecord(NomineeTrackerObj nominee){
		
		
		boolean success = endorsementDao.deleteNominee(nominee);
		if(success)
		{
			try {
				loadNomineeHistory();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	
	public void deleteEndorsementRecord(EndorsementTrackerObj endorse)
	{
		
		boolean success = endorsementDao.deleteEndorsement(endorse);
		if(success)
		{
			try {
				loadEndorsementHistory();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	
	public void updateNomineeHistory(NomineeTrackerObj nominee){
		System.out.println(nominee);
		
	boolean success = endorsementDao.updateNominee(nominee);
	
	if(success)
	{
		try {
			loadNomineeHistory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello update successfully done");
	}
	}
	
	
	public void updateEndorsementHistory(EndorsementTrackerObj endor)
	{
		boolean success = endorsementDao.updateEndorsement(endor);
		
		if(success)
		{
			try {
				loadEndorsementHistory();
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
	public void setSelectedEndorsementForLoad(EndorsementTrackerObj endorse){
			System.out.println("hello");
			forLoadEndorsentId = endorse.getEmpid();
	}
	
	/**
	 * 
	 * @return
	 */

	public String loadSelectedEndorsementInfo(){
		System.out.println("Loading data for project: " + forLoadEndorsentId);
		endorsementObj.setEmpid(forLoadEndorsentId);
		endorsementObj = endorsementDao.getEndorsementTrackerStatus(endorsementObj.getEmpid());
		
		return "Framework";
	}
	


	@PostConstruct	
	public void init()
	{
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for endorsement history is"+empid);
		if(empid !=null)
		{
			try {
				loadEndorsementHistory();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(empid !=null)
		{
			try {
				loadNomineeHistory();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}	
	


}
