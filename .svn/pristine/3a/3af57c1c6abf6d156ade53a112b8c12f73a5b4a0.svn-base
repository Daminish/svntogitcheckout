package com.capco.hcm.view.fragments;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.capco.hcm.model.ProjectHistoryObj;
import com.capco.hcm.sql.ProjectHistoryDao;
/**
 * @author Mohit Gangil
 *
 */

@ViewScoped
@ManagedBean (name = "projectHistoryInfo")
public class ProjectHistoryView implements Serializable {

	private static final long serialVersionUID = -840852783654068803L;

	@ManagedProperty (value="#{projecthistoryDao}")
	private ProjectHistoryDao prohistoryDao;
	
	private ProjectHistoryObj projectHistoryObj;
	private Map<String, String> projectHistoryCategory;
	private List<ProjectHistoryObj> projhistory;
	


	private String forLoadProjectId;
	private List<ProjectHistoryObj> projectsearchhistory;
	
	
	//GETTER AND SETTER 
	public Map<String, String> getProjectHistoryCategory() {
		return projectHistoryCategory;
	}

	public void setProjectHistoryCategory(Map<String, String> projectHistoryCategory) {
		this.projectHistoryCategory = projectHistoryCategory;
	}
	public List<ProjectHistoryObj> getProjectsearchhistory() {
		return projectsearchhistory;
	}

	public void setProjectsearchhistory(List<ProjectHistoryObj> projectsearchhistory) {
		this.projectsearchhistory = projectsearchhistory;
	}
	
	public List<ProjectHistoryObj> getProjhistory() {
		if(projhistory == null)
			projhistory = new ArrayList<ProjectHistoryObj>();
		return projhistory;
	}

	public void setProjhistory(List<ProjectHistoryObj> projhistory) {
		this.projhistory = projhistory;
	}
	
	public String getForLoadProjectId() {
		return forLoadProjectId;
	}

	public void setForLoadProjectId(String forLoadProjectId) {
		this.forLoadProjectId = forLoadProjectId;
	}

	public ProjectHistoryObj getProjectHistoryObj() {
		if(projectHistoryObj == null)
			projectHistoryObj = new ProjectHistoryObj();
		return projectHistoryObj;
	}

	public void setProjectHistoryObj(ProjectHistoryObj projectHistoryObj) {
		this.projectHistoryObj = projectHistoryObj;
	}
	
	public ProjectHistoryDao getProhistoryDao() {
		return prohistoryDao;
	}

	public void setProhistoryDao(ProjectHistoryDao prohistoryDao) {
		this.prohistoryDao = prohistoryDao;
	}

	//METHOD STARTS HERE
	public  void loadProjectHistory() {
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for load project history is"+empid);
		if(empid !=null)
		{
			projhistory = prohistoryDao.getProject(empid);
			
		}
		
		
	}


	public void updatePrjojectHistory(ProjectHistoryObj project){
		System.out.println(project);
		
	boolean success = prohistoryDao.updateProject(project);
	
	if(success)
	{
		loadProjectHistory();
		System.out.println("hello update successfully done");
	}
	}
	
	
	
	public void insertProjectHistoryStatus() {
		

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the Insert Project History session" +hello);
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		
		projectHistoryObj.setEmployeeId(empid);
		projectHistoryObj.setInsertedBy(hello);
		
		boolean success = prohistoryDao.insertProjectHistoryTrackerStatus(projectHistoryObj);
		if(success)
		{
			loadProjectHistory();
			System.out.println("insert the project history records");
			//TODO
		}
		
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      
    }
	
	public void getProjectHistoryTrkr(){
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		projectHistoryObj.setEmployeeId(empid);//TODO set the employee id from session
		prohistoryDao.getProjectDetails(projectHistoryObj.getEmployeeId());
	}
	
	/**
	 * 
	 * @param empToLoad
	 * @return
	 */
	public void setSelectedProjectHistoryForLoad(ProjectHistoryObj projecttoLoad){
			System.out.println("hello");
		forLoadProjectId = projecttoLoad.getEmployeeId();
	}
	
	/**
	 * 
	 * @return
	 */

	public String loadSelectedProjectInfo(){
		System.out.println("Loading data for project: " + forLoadProjectId);
		projectHistoryObj.setEmployeeId(forLoadProjectId);
		projectHistoryObj = prohistoryDao.getProjectDetails(projectHistoryObj.getEmployeeId());
		
		return "Framework";
	}
	
	
	public void deleteProjectHistoryRecord(ProjectHistoryObj project){
		
		
		boolean success = prohistoryDao.deleteFieldForProjectHistory(project);
		if(success)
		{
			
				loadProjectHistory();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	
	@PostConstruct	
	public void init()
	{
		try{
		List<String> catList = prohistoryDao.getAllProject();
		
		if(projectHistoryCategory == null)
			projectHistoryCategory = new HashMap<String, String>();
		
		for(String cat : catList){
			projectHistoryCategory.put(cat, cat);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for project history is"+empid);
		if(empid !=null)
		{
			loadProjectHistory();
			
		}
		
		
	}	

	
	
}