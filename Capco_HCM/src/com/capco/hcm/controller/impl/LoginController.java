package com.capco.hcm.controller.impl;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


/**
 * @author Mohit Gangil
 *
 */
@ViewScoped
@ManagedBean (name="userLoginView")
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private String currentID;
	
	public String getCurrentID() {
		return currentID;
	}

	public void setCurrentID(String currentID) {
		this.currentID = currentID;
	}

	public void validateUserLogin(ActionEvent event) throws IOException{
		//TODO code for LDAP authentication
		System.out.println("comes inside login");
		/*FacesContext context = FacesContext.getCurrentInstance();
		UserInfoObj uio = new UserInfoObj();
		HttpSession session = (HttpSession)context.getExternalContext().getSessionMap().put(HcmConstants.LOGIN_USER_NAME.toString(), uio);
		session.setAttribute("LOGIN_USER_NAME", uio);*/
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean loggedIn = false;
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("id", userName);
		sessionMap.put("password",password);
	
		
		String user = (String) sessionMap.get("id");
		System.out.println("the session value is "+user);
		
		String pass = (String) sessionMap.get("password");
		System.out.println("the password is "+pass);
		
		//valid check username and password 
		
		if(user.equals("mohit") && pass.equals("gangil") || user.equals("Preeti") && pass.equals("e10460696596") ||
		user.equals("Supriya") && pass.equals("e10540858323") || user.equals("Ahamed") && pass.equals("e109119113712") ||
		user.equals("Trisha") && pass.equals("e109249614062") || user.equals("Vidya") && pass.equals("e109249814064") ||
		user.equals("Manoranjan") && pass.equals("e109249914065") || user.equals("Dharani") && pass.equals("e10012989339") ||
		user.equals("Savita") && pass.equals("e537036615177")
				)
		{
			loggedIn = true;
			
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", userName);
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        ec.redirect(ec.getRequestContextPath() + "/Framework.xhtml");
			System.out.println("successfull");
		}
		else
		{
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid Credentails");
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
			System.out.println("failed");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
		
		}
		/* Destroy the Session*/
		public void logout() throws IOException{
		currentID = (String)FacesContext.getCurrentInstance().getExternalContext()
                   .getSessionMap().get("id");
		if(currentID != null)
		{
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
		
				System.out.println("the session destroy ");
			ec.invalidateSession();
		}
		
	}
	
	
	//Start of Getters and Setters
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	

}
