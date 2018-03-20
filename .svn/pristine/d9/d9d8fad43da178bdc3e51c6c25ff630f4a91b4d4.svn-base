package com.capco.hcm.view.fragments;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.capco.hcm.controller.DatabaseController;
import com.capco.hcm.model.JoiningBenefitsInfoObj;
import com.capco.hcm.model.SkillSetTrainingInfoObj;
import com.capco.hcm.model.TravelBondInfoObj;
import com.capco.hcm.sql.BenefitsTravelTrainingDao;

/**
 * @author Mohit Gangil
 *
 */
@ManagedBean (name = "beneTrvlTrainView")
@ViewScoped
public class BenefitsTravelTrainingView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private TravelBondInfoObj trvlBondInfoObj;
	private JoiningBenefitsInfoObj joinBeneInfoObj;
	private SkillSetTrainingInfoObj skillTrainInfoObj;
	private List<JoiningBenefitsInfoObj> benefitList;
	private List<SkillSetTrainingInfoObj> skillList;
	private List<TravelBondInfoObj> travelBondList;
	private List<JoiningBenefitsInfoObj> benefitsearchhistory;
	private List<SkillSetTrainingInfoObj> skillsearchhistory;
	

	private List<TravelBondInfoObj> travelsearchhistory;
	private String forLoadTravelId;
	private String forLoadSkillId;
	private String forLoadBenefitId;
	private DatabaseController dbCtrl;
	
	
	@ManagedProperty(value="#{beneTrvlTrainDao}")
	BenefitsTravelTrainingDao beneTrvlTrainDao;

	//GETTER SETTER
	public List<JoiningBenefitsInfoObj> getBenefitsearchhistory() {
		return benefitsearchhistory;
	}
	public void setBenefitsearchhistory(List<JoiningBenefitsInfoObj> benefitsearchhistory) {
		this.benefitsearchhistory = benefitsearchhistory;
	}
	public List<SkillSetTrainingInfoObj> getSkillsearchhistory() {
		return skillsearchhistory;
	}
	public void setSkillsearchhistory(List<SkillSetTrainingInfoObj> skillsearchhistory) {
		this.skillsearchhistory = skillsearchhistory;
	}
	public List<TravelBondInfoObj> getTravelsearchhistory() {
		return travelsearchhistory;
	}
	public void setTravelsearchhistory(List<TravelBondInfoObj> travelsearchhistory) {
		this.travelsearchhistory = travelsearchhistory;
	}

	
	public DatabaseController getDbCtrl() {
		return dbCtrl;
	}
	public void setDbCtrl(DatabaseController dbCtrl) {
		this.dbCtrl = dbCtrl;
	}
	
	public List<SkillSetTrainingInfoObj> getSkillList() {
		return skillList;
	}
	public void setSkillList(List<SkillSetTrainingInfoObj> skillList) {
		this.skillList = skillList;
	}
	public List<JoiningBenefitsInfoObj> getBenefitList() {
		return benefitList;
	}
	public void setBenefitList(List<JoiningBenefitsInfoObj> benefitList) {
		this.benefitList = benefitList;
	}

	public SkillSetTrainingInfoObj getSkillTrainInfoObj() {
		if(skillTrainInfoObj == null)
			skillTrainInfoObj = new SkillSetTrainingInfoObj();
		return skillTrainInfoObj;
	}
	public List<TravelBondInfoObj> getTravelBondList() {
		
		return travelBondList;
	}
	public void setTravelBondList(List<TravelBondInfoObj> travelBondList) {
		this.travelBondList = travelBondList;
	}
	public void setSkillTrainInfoObj(SkillSetTrainingInfoObj skillTrainInfoObj) {
		this.skillTrainInfoObj = skillTrainInfoObj;
	}
	public String getForLoadTravelId() {
		return forLoadTravelId;
	}
	public void setForLoadTravelId(String forLoadTravelId) {
		this.forLoadTravelId = forLoadTravelId;
		System.out.println("SETTING THE TRAVEL LOAD ID");
	}

	public String getForLoadBenefitId() {
		return forLoadBenefitId;
	}
	public void setForLoadBenefitId(String forLoadBenefitId) {
		this.forLoadBenefitId = forLoadBenefitId;
	}

	public String getForLoadSkillId() {
		return forLoadSkillId;
	}
	public void setForLoadSkillId(String forLoadSkillId) {
		this.forLoadSkillId = forLoadSkillId;
	}
	
	public TravelBondInfoObj getTrvlBondInfoObj() {
		if(trvlBondInfoObj == null)
			trvlBondInfoObj = new TravelBondInfoObj();
		return trvlBondInfoObj;
	}
	public void setTrvlBondInfoObj(TravelBondInfoObj trvlBondInfoObj) {
		this.trvlBondInfoObj = trvlBondInfoObj;
	}
	public JoiningBenefitsInfoObj getJoinBeneInfoObj() {
		if(joinBeneInfoObj == null)
			joinBeneInfoObj = new JoiningBenefitsInfoObj();
		return joinBeneInfoObj;
	}
	public void setJoinBeneInfoObj(JoiningBenefitsInfoObj joinBeneInfoObj) {
		this.joinBeneInfoObj = joinBeneInfoObj;
	}
	public BenefitsTravelTrainingDao getBeneTrvlTrainDao() {
		return beneTrvlTrainDao;
	}
	public void setBeneTrvlTrainDao(BenefitsTravelTrainingDao beneTrvlTrainDao) {
		this.beneTrvlTrainDao = beneTrvlTrainDao;
	}	
	
	//METHODS TO LOAD DATA
	public  void loadTravel() throws IOException{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for travel is"+empid);
		if(empid !=null)
		{
		
		travelBondList = beneTrvlTrainDao.getTravel(empid);
		System.out.println("Hellosasasasa");
		}
	}
	
	public  void loadBenefit() throws IOException{
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for benefit is"+empid);
		if(empid !=null)
		{
		
		benefitList = beneTrvlTrainDao.getBenefit(empid);
		System.out.println("Hellosasasasa");
		}
	}
	
	public  void loadSkill() throws IOException{
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for Skill is"+empid);
		if(empid !=null)
		{
		
		skillList = beneTrvlTrainDao.getSkill(empid);
		System.out.println("Hellosasasasa");
		}
	}
	

	/**
	 * @throws IOException 
	 * 
	 */
	public void insertTravelBondDetailsInfo() throws IOException{
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the insert travel Bond session" +hello);
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		
		trvlBondInfoObj.setEmpId(empid);
		trvlBondInfoObj.setInsertedBy(hello);
		boolean success = beneTrvlTrainDao.insertTravelBondDetails(trvlBondInfoObj);
		
		if(success){
			loadTravel();
			//TODO call summary view to load values
			System.out.println("successfully inserted records for Travel Bond Details");
			
		}
		
		
	}
	
	public void insertBenefitsDetailsInfo() throws IOException{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the insert Benefits Detail session" +hello);
		

		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		
		joinBeneInfoObj.setEmpId(empid);
		joinBeneInfoObj.setInsertedBy(hello);
		
		boolean success = beneTrvlTrainDao.insertBenefitsDetails(joinBeneInfoObj);
		if(success){
			loadBenefit();
			//TODO call summary view to load values
			System.out.println("insert records for benefit details");
		}
	}
	
	
	public void insertSkillSetDetailsInfo() throws IOException{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the insert Skils Detail session" +hello);

		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		skillTrainInfoObj.setEmpId(empid);				//TODO get value from some context value
		skillTrainInfoObj.setInsertedBy(hello);
		
		boolean success = beneTrvlTrainDao.insertSkillsetDetails(skillTrainInfoObj);
		if(success){
			loadSkill();
			//TODO call summary view to load values
			System.out.println("Insert Skill Set");
		}
	}
	
	public void updateTravelField(TravelBondInfoObj travel){
		System.out.println(travel);
	boolean success = beneTrvlTrainDao.updatedValue(travel);
	
	if(success)
	{
		try {
			loadTravel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello update successfully done");
	}
	}
	
	public void updateSkillHistory(SkillSetTrainingInfoObj skill)
	{
		System.out.println(skill);
		boolean success = beneTrvlTrainDao.updatedSkillValue(skill);
		
		if(success)
		{
			try {
				loadSkill();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("hello update successfully done");
		}
	}
	
	
	public void updateBenefitHistory(JoiningBenefitsInfoObj benefit)
	{
		System.out.println("hello benefit"+benefit);
		boolean success = beneTrvlTrainDao.updatedBenefitValue(benefit);
		
		if(success)
		{
			try {
				loadBenefit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("hello update successfully done");
		}
	}
		
	
	
	
	public void deleteTravelRecord(TravelBondInfoObj travel){
		
		
		boolean success = beneTrvlTrainDao.deleteFieldForTravel(travel);
		if(success)
		{
			try {
				loadTravel();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	public void deleteBenefitHistoryRecord(JoiningBenefitsInfoObj benefit)
	{
		boolean success = beneTrvlTrainDao.deleteFieldForBenefit(benefit);
		if(success)
		{
			try {
				loadBenefit();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	public void deleteSkillHistoryRecord(SkillSetTrainingInfoObj skill)
	{
		
		boolean success = beneTrvlTrainDao.deleteFieldForSkill(skill);
		if(success)
		{
			try {
				loadSkill();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	
	
	public void setSelectedTravelInfoForLoad(TravelBondInfoObj travelToLoad){
		forLoadTravelId = travelToLoad.getEmpId();
		System.out.println("Select data to loead");
	}
	
	
	
	

	public void setSelectedSkillInfoForLoad(SkillSetTrainingInfoObj skillToLoad){
		forLoadSkillId = skillToLoad.getEmpId();
		System.out.println("Select data to loead");
	}
	
	
	
	
	public void setSelectedBenefitInfoForLoad(JoiningBenefitsInfoObj joininig){
		forLoadBenefitId = joininig.getEmpId();
		System.out.println("Select data to loead");
	}
	
	
	
	@PostConstruct	
	public void initSkillset()
	{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for Skillset is"+empid);
		if(empid !=null)
		{
			try {
				loadSkill();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(empid !=null)
		{
			try {
				loadTravel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(empid !=null)
		{
			try {
				loadBenefit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
}