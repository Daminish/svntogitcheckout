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

import com.capco.hcm.model.ProbationTrackerObj;
import com.capco.hcm.sql.ProbationTrackerDao;

/**
 * @author Mohit Gangil
 *
 */

@ViewScoped
@ManagedBean(name = "probationTrkrView")
public class ProbationTrackerView implements Serializable {
	private static final long serialVersionUID = -7519066912861674533L;
	
	private ProbationTrackerObj probationTrkrObj;
	private List<ProbationTrackerObj> probationTrkrRevCyc;
	private List<ProbationTrackerObj> probationsearchhistory;
	
	
	@ManagedProperty(value="#{probationTrkrDao}")
	private ProbationTrackerDao probationTrkrDao;

	private String forLoadProbationId;
	
	//GETTER ANS SETTER
	
	public List<ProbationTrackerObj> getProbationsearchhistory() {
		return probationsearchhistory;
	}

	public void setProbationsearchhistory(List<ProbationTrackerObj> probationsearchhistory) {
		this.probationsearchhistory = probationsearchhistory;
	}

	public List<ProbationTrackerObj> getProbationTrkrRevCyc() {
		return probationTrkrRevCyc;
	}

	public void setProbationTrkrRevCyc(List<ProbationTrackerObj> probationTrkrRevCyc) {
		this.probationTrkrRevCyc = probationTrkrRevCyc;
	}


	public String getForLoadProbationId() {
		return forLoadProbationId;
	}

	public void setForLoadProbationId(String forLoadProbationId) {
		this.forLoadProbationId = forLoadProbationId;
	}
	public ProbationTrackerObj getProbationTrkrObj() {
		if(probationTrkrObj == null)
			probationTrkrObj = new ProbationTrackerObj();
		return probationTrkrObj;
	}
	public void setProbationTrkrObj(ProbationTrackerObj probationTrkrObj) {
		this.probationTrkrObj = probationTrkrObj;
	}
	public ProbationTrackerDao getProbationTrkrDao() {
		return probationTrkrDao;
	}
	public void setProbationTrkrDao(ProbationTrackerDao probationTrkrDao) {
		this.probationTrkrDao = probationTrkrDao;
	}
	
	//METHOD STARTS HERE
	public  void loadProbation() throws IOException{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for Probation Tracker is"+empid);
		if(empid !=null)
		{
			probationTrkrRevCyc = probationTrkrDao.getProbationDetails(empid);
		}
	}
	
	public void deleteProbationRecord(ProbationTrackerObj probation){
		
		
		boolean success = probationTrkrDao.deleteFieldForProbation(probation);
		if(success)
		{
			try {
				loadProbation();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	

	public void getProbationStatus(){
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		probationTrkrObj.setEmployeeId(empid);//TODO set Employee id from session
		probationTrkrObj = probationTrkrDao.getProbationTrackerStatus(probationTrkrObj.getEmployeeId());
	}
	

	
	public void updateProbationTracker(ProbationTrackerObj probation){
		System.out.println(probation);
		
	boolean success = probationTrkrDao.updateProbation(probation);
	
	if(success)
	{
		try {
			loadProbation();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello update successfully done");
	}
	}
	
	
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      
    }
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void insertProbationTrackerInfo() throws IOException{

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the insert Probation Tracker session" +hello);
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
	
		probationTrkrObj.setInsertedBy(hello); //TODO add user id from session
		probationTrkrObj.setEmployeeId(empid); //TODO add employee id from session
		boolean success = probationTrkrDao.insertProbationTrackerStatus(probationTrkrObj);
		if(success){
			loadProbation();
			//TODO call summary view to load values
			System.out.println("insert the probation tracker");
		}
	}

	/**
	 * 
	 * @param empToLoad
	 * @return
	 */
	public void setSelectedProbationInfoForLoad(ProbationTrackerObj probationToLoad){
		forLoadProbationId = probationToLoad.getEmployeeId();
				
	}
	
	/**
	 * 
	 * @return
	 */
	public String loadSelectedProbationInfo(){
		System.out.println("Loading data for Probation: " + forLoadProbationId);
		
		probationTrkrObj.setEmployeeId(forLoadProbationId);
		probationTrkrObj = probationTrkrDao.getProbationTrackerStatus(probationTrkrObj.getEmployeeId());
		return "Framework";
	
	}
	
	
	@PostConstruct	
	public void init()
	{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for Probation Tracker is"+empid);
		if(empid !=null)
		{
			try {
				loadProbation();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}	
	
	
}
