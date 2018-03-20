package com.capco.hcm.view.fragments;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.capco.hcm.model.UserInfoObj;
import com.capco.hcm.sql.UserInfoDao;
/**
 * @author Mohit Gangil
 *
 */

@ViewScoped
@ManagedBean(name="userView")
public class UserInfoView {

	@ManagedProperty("#{userInfoDao}")
	UserInfoDao userInfoDao;
	
	private UserInfoObj userInfoObj; 
	private List<UserInfoObj> userList;
	private String forLoadUserInfoId;
	private List<UserInfoObj> usersearchhistory;
	

	//GETTER AND SETTER
	public List<UserInfoObj> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfoObj> userList) {
		this.userList = userList;
	}


	public List<UserInfoObj> getUsersearchhistory() {
		return usersearchhistory;
	}

	public void setUsersearchhistory(List<UserInfoObj> usersearchhistory) {
		this.usersearchhistory = usersearchhistory;
	}
	
	public String getForLoadUserInfoId() {
		return forLoadUserInfoId;
	}

	public void setForLoadUserInfoId(String forLoadUserInfoId) {
		this.forLoadUserInfoId = forLoadUserInfoId;
	}
	
	public UserInfoObj getUserInfoObj() {
			if(userInfoObj == null)
				userInfoObj = new UserInfoObj();
			return userInfoObj;
		}
	public void setUserInfoObj(UserInfoObj userInfoObj) {
			this.userInfoObj = userInfoObj;
		}
		

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	//METHOD STARTS HERE
	/**
	 * Save a new user info object
	 * @throws IOException 
	 */
	public void saveSelectedUserInfo() throws IOException{
		

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		String hello = (String) sessionMap.get("id");
		System.out.println("the Selected User Info session" +hello);
		
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		userInfoObj.setEmployeeId(empid);
		userInfoObj.setInsertedBy(hello);
		
		boolean success = userInfoDao.insertUserInfoObj(userInfoObj);
	 	if(success)
	 		{
	 			loadUserHistory();	
	 			System.out.println("insertion for user data ");
	 			//TODO
	 		}
		}
	
	

	public void getUserInfoTrkr(){
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id is"+empid);
		
		userInfoObj.setEmployeeId(empid); //TODO set the employee id from session
		userInfoDao.getUserInfo(userInfoObj.getEmployeeId());
		}
	
	

	public void setSelectedUserInfoForLoad(UserInfoObj userToLoad){
		forLoadUserInfoId = userToLoad.getEmployeeId();
		System.out.println("Select data to loead");
	}
	
	
	public String loadSelectedUserInfo(){
		System.out.println("Loading data for User: " + forLoadUserInfoId);
		userInfoObj.setEmployeeId(forLoadUserInfoId);
		userInfoObj = userInfoDao.getUserInfo(userInfoObj.getEmployeeId());
		return "Framework";
	}
	
	
	public  void loadUserHistory() throws IOException{
		ExternalContext externalContextid = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMapid = externalContextid.getSessionMap();
		String empid = (String) sessionMapid.get("empid");
		System.out.println("the session user id for load USer hostory is"+empid);
		if(empid !=null)
		{
		
		userList = userInfoDao.getUserDetails(empid);
		}
	}
	
	
	public void deleteUserHistoryRecord(UserInfoObj user){
		
		
		boolean success = userInfoDao.deleteUsertHistory(user);
		if(success)
		{
			try {
				loadUserHistory();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DELETED !"));
		}
	}
	

	public void updateUserInfo(UserInfoObj user) throws IOException{
		System.out.println(user);
		
	boolean success = userInfoDao.updateUser(user);
	
	if(success)
	{
		loadUserHistory();
		System.out.println("hello update successfully done");
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
			try {
				loadUserHistory();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}	
	

}