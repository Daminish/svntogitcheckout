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

import com.capco.hcm.model.DocAndBgvObj;
import com.capco.hcm.sql.DocAndBgvDao;

/**
 * @author Mohit Gangil
 *
 */

@ManagedBean (name = "empDocBgvBean")
@ViewScoped
public class DocAndBgvStatusView implements Serializable {

	
	private static final long serialVersionUID = 2331110286029066659L;

	private DocAndBgvObj docBgvObj;
	
	private List<DocAndBgvObj> backgroundHistory;
	private String forLoadBackgroundId;
	private List<DocAndBgvObj> backgroundsearchhistory;
	
	

	@ManagedProperty(value="#{bgvDocDao}")
	private DocAndBgvDao docBgvDao;
	
	// ALL GETTERS AND SETTER
	public List<DocAndBgvObj> getBackgroundHistory() {
		return backgroundHistory;
	}

	public void setBackgroundHistory(List<DocAndBgvObj> backgroundHistory) {
		this.backgroundHistory = backgroundHistory;
	}
	
	public String getForLoadBackgroundId() {
		return forLoadBackgroundId;
	}

	public void setForLoadBackgroundId(String forLoadBackgroundId) {
		this.forLoadBackgroundId = forLoadBackgroundId;
	}

	public DocAndBgvObj getDocBgvObj() {
		if(docBgvObj == null)
			docBgvObj = new DocAndBgvObj();
		return docBgvObj;
	}
	public void setDocBgvObj(DocAndBgvObj docBgvObj) {
		this.docBgvObj = docBgvObj;
	}
	public DocAndBgvDao getDocBgvDao() {
		return docBgvDao;
	}

	public void setDocBgvDao(DocAndBgvDao docBgvDao) {
		this.docBgvDao = docBgvDao;
	}	
	
	
	public List<DocAndBgvObj> getBackgroundsearchhistory() {
		return backgroundsearchhistory;
	}

	public void setBackgroundsearchhistory(List<DocAndBgvObj> backgroundsearchhistory) {
		this.backgroundsearchhistory = backgroundsearchhistory;
	}

	
	//METHODS START
	public void getEmpBgvAndDocStatus(){
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		docBgvObj.setEmployeeId(empid);  //TODO set the employee id from session
		backgroundHistory =	docBgvDao.getDocAndBgvStatus();
	}
	
	
	public void updateBackgroundHistory(DocAndBgvObj doc){
		System.out.println(doc);
		
	boolean success = docBgvDao.updateVerify(doc);
	
	if(success)
	{
		try {
			loadDocument();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello update successfully done");
	}
	}
	
	
	public void deleteBackgroundHistoryRecord(DocAndBgvObj document) throws IOException{
		
		
		boolean success = docBgvDao.deleteFieldForBackgroundHistory(document);
		if(success)
		{
			
				loadDocument();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	
	/**
	 * 
	 * @param empToLoad
	 * @return
	 */
/*	public void setSelectedBackgroundHistoryForLoad(DocAndBgvObj document){
			System.out.println("hello");
		forLoadBackgroundId = document.getEmployeeId();
	}*/
	


	/**
	 * 
	 * @return
	 */

	/*public String loadSelectedBackgroundInfo(){
		System.out.println("Loading data for project: " + forLoadBackgroundId);
		docBgvObj.setEmployeeId(forLoadBackgroundId);
		docBgvObj = docBgvDao.getDocAndBgvStatus(docBgvObj.getEmployeeId());
		
		return "Framework";
	}
	*/
	
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      
    }
	
	
	
	public  void loadDocument() throws IOException{
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for background tracker is"+empid);
		if(empid !=null)
		{
		backgroundHistory = docBgvDao.getVerify(empid);
		System.out.println("Hellosasasasa");
		}
		
	}
	
	public void insertEmpBgvAndDocStatus() throws IOException{
	
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the insert Emp Background session" +hello);

		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		
		docBgvObj.setInsertedBy(hello);//TODO add code for user id
		docBgvObj.setEmployeeId(empid);//TODO add code for emp id
		boolean success = docBgvDao.insertDocAndBgvStatus(docBgvObj);
		if(success)
		{
			loadDocument();
			System.out.println("Insert Background verify details");
			//TODO call summary view to load values
		}
	}

	
	@PostConstruct	
	public void init()
	{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for background tracker is"+empid);
		if(empid !=null)
		{
			try {
				loadDocument();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
