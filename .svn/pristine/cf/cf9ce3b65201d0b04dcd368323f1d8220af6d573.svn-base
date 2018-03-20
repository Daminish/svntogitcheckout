package com.capco.hcm.view.fragments;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import com.capco.hcm.model.PromotionTrackerObj;
import com.capco.hcm.model.ReviewCycleTracker;
import com.capco.hcm.sql.PromotionTrackerDao;
/**
 * @author Mohit Gangil
 *
 */
@ViewScoped
@ManagedBean (name = "promotionTrackerView")
public class PromotionTrackerView implements Serializable {
	private static final long serialVersionUID = -8465333356339863868L;
	
	private PromotionTrackerObj promoTrkrObj;
	@ManagedProperty (value = "#{promotionTrkrDao}")
	private PromotionTrackerDao promoTrkrDao;
	private List<ReviewCycleTracker> promotionsearchhistory;
	

	private List<ReviewCycleTracker> bandTracker;
	private List<String> empBands;

	//GETTER AND SETTER
	public List<ReviewCycleTracker> getBandTracker() {
		return bandTracker;
	}

	public void setBandTracker(List<ReviewCycleTracker> bandTracker) {
		this.bandTracker = bandTracker;
	}

	public List<ReviewCycleTracker> getPromotionsearchhistory() {
		return promotionsearchhistory;
	}

	public void setPromotionsearchhistory(List<ReviewCycleTracker> promotionsearchhistory) {
		this.promotionsearchhistory = promotionsearchhistory;
	}

	public List<String> getEmpBands() {
		return empBands;
		
	}
	public void setEmpBands(List<String> empBands) {
		this.empBands = empBands;
	}
	public PromotionTrackerObj getPromoTrkrObj() {
		if(promoTrkrObj == null)
			promoTrkrObj = new PromotionTrackerObj();
		return promoTrkrObj;
	}
	public void setPromoTrkrObj(PromotionTrackerObj promoTrkrObj) {
		this.promoTrkrObj = promoTrkrObj;
	}
	public PromotionTrackerDao getPromoTrkrDao() {
		return promoTrkrDao;
	}
	public void setPromoTrkrDao(PromotionTrackerDao promoTrkrDao) {
		this.promoTrkrDao = promoTrkrDao;
	}	
	
	//METHOD STARTS HERE
	public void getEmployeeReviewCycleTracker(){
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
	}

	public void insertEmployeeReviewCycleStatus(){
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the Insert Project History session" +hello);
		
		promoTrkrObj.setInsertedBy(hello);
		
		boolean success = promoTrkrDao.insertEmployeeReviewTracker(promoTrkrObj, empid);//TODO add the employee id & Joining Date
		if(success)
		{
			getPromotionDetails();
			System.out.println("insert the promotion tracker values");
			
		}
	
	}

	
	public void updateReviewCycleStatus(String bd, String empid){
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
	//	String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		System.out.println("hello comes in update section");
		
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the Insert Project History session" +hello);
		
		
		
		boolean success  = promoTrkrDao.updateEmployeeReviewCycleHistory(bd,date,empid,hello);//TODO add the employee id
		if(success)
		{
			getPromotionDetails();
			System.out.println("update the promotion tracker values");
			//TODO LOGIC
			
		}
	}
	/**
	 * 
	 * @param event
	 */
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if(newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


	public void getPromotionDetails()
	{
		promoTrkrObj = new PromotionTrackerObj();
		System.out.println("hello comes in this section");
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		List<ReviewCycleTracker> list =promoTrkrDao.getPromotionTrackerValues(empid);
		
		setBandTracker(list);
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
			getPromotionDetails();
		}
		
		
	}	

	
	
	
}