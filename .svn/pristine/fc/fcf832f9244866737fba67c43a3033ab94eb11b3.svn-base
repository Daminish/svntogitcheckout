package com.capco.hcm.view.fragments;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.capco.hcm.model.EmployeeBasicInfoObj;
import com.capco.hcm.sql.EmployeeBasicInfoDao;
/**
 * @author Mohit Gangil
 *
 */


@SessionScoped
@ManagedBean (name = "empBasicInfo")
public class EmpBasicInfoView implements Serializable {
	private static final long serialVersionUID = -3124855761108297184L;
	
	private List<EmployeeBasicInfoObj> basichistory;
	private Map<String, String> designationCategory;
	private Map<String, String> projectCategory;
	private EmployeeBasicInfoObj basicInfoObj;
	private String forLoadEmpId;
	private List<EmployeeBasicInfoObj> basicsearchhistory;
	







	@ManagedProperty(value="#{empBasicInfoDao}")
	private EmployeeBasicInfoDao basicInfoDao;
	
	//Start of getters and setters
	public List<EmployeeBasicInfoObj> getBasichistory() {
		return basichistory;
	}

	public void setBasichistory(List<EmployeeBasicInfoObj> basichistory) {
		this.basichistory = basichistory;
	}

	public Map<String, String> getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(Map<String, String> projectCategory) {
		this.projectCategory = projectCategory;
	}

	public Map<String, String> getDesignationCategory() {
		return designationCategory;
	}

	public void setDesignationCategory(Map<String, String> designationCategory) {
		this.designationCategory = designationCategory;
	}
	
	public EmployeeBasicInfoDao getBasicInfoDao() {
		return basicInfoDao;
	}
	public void setBasicInfoDao(EmployeeBasicInfoDao basicInfoDao) {
		this.basicInfoDao = basicInfoDao;
	}
	public String getForLoadEmpId() {
		return forLoadEmpId;
	}
	public void setForLoadEmpId(String forLoadEmpId) {
		this.forLoadEmpId = forLoadEmpId;
	}
	public EmployeeBasicInfoObj getBasicInfoObj() {
		if(basicInfoObj == null)
			basicInfoObj = new EmployeeBasicInfoObj();
		return basicInfoObj;
	}
	public void setBasicInfoObj(EmployeeBasicInfoObj basicInfoObj) {
		this.basicInfoObj = basicInfoObj;
	}
	

	public List<EmployeeBasicInfoObj> getBasicsearchhistory() {
		return basicsearchhistory;
	}

	public void setBasicsearchhistory(List<EmployeeBasicInfoObj> basicsearchhistory) {
		this.basicsearchhistory = basicsearchhistory;
	}

	

	//METHODS START
	public void deleteBasicHistoryRecord(EmployeeBasicInfoObj basic) throws IOException{
		
		System.out.println("comes in deleted section");
		
		boolean success = basicInfoDao.deleteFieldForBasicHistory(basic);
		
		if(success)
		{
			
				loadBasicHistory();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
		
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", ((EmployeeBasicInfoObj) event.getObject()).getEmpId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	
	@PostConstruct	
	public void init()
	{
		try{
		List<String> catList = basicInfoDao.getAllDesignation();
		
		if(designationCategory == null)
			designationCategory = new HashMap<String, String>();
		
		for(String cat : catList){
			designationCategory.put(cat, cat);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			
			List<String> catList = basicInfoDao.getAllProject();
			
			if(projectCategory == null)
				projectCategory = new HashMap<String, String>();
			
			for(String cat : catList){
				projectCategory.put(cat, cat);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public  void loadBasicHistory() throws IOException{
		
		basichistory = basicInfoDao.getBasicInfoDetails();
	}
	
	

	/**
	 * @throws IOException 
	 * 
	 */
	public void insertEmpBasicInfo() throws IOException{
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the session user id is"+hello);
		
		basicInfoObj.setInsertedBy(hello); //TODO add code for user id
	//	basicInfoObj.setEmpId(""); //TODO add code for emp id
		boolean success = basicInfoDao.insertBasicInfo(basicInfoObj);
		if(success){
			loadBasicHistory();
			System.out.println("data is sucessfully inserted");

			ExternalContext sessionempid = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap1 = sessionempid.getSessionMap();
			sessionMap.put("empid", basicInfoObj.getEmpId());
			
			String empid =  (String) sessionMap.get("empid");
				int emp =	Integer.parseInt(empid);
			System.out.println("the session value is "+emp);
			
			//TODO call summary view to load values
		}
	}
	 
	
	public void updateBasicHistory(EmployeeBasicInfoObj basic){
		System.out.println(basic);
		
	boolean success = basicInfoDao.updateBasic(basic);
	
	if(success)
	{
		System.out.println("hello update successfully done");
	}
	}
	
	/**
	 * 
	 * @param empToLoad
	 * @return
	 */
	public void setSelectedEmployeeBasicInfoForLoad(EmployeeBasicInfoObj empToLoad){
		ExternalContext sessionempid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap1 = sessionempid.getSessionMap();
		sessionMap1.put("empid", empToLoad.getEmpId());
		
		System.out.println("emp id comes");
		
		forLoadEmpId = empToLoad.getEmpId();
	}
	
	/**
	 * 
	 * @return
	 */
	public String loadSelectedEmpBasicInfo(){
		System.out.println("Loading data for employee: " + forLoadEmpId);
		basicInfoObj.setEmpId(forLoadEmpId);
		basicInfoObj = basicInfoDao.getBasicInfo(basicInfoObj.getEmpId());//TODO uncomment the code
		return "Framework";
	}
	


	public EmpBasicInfoView() {
		super();
		// TODO Auto-generated constructor stub
	}
}
