package com.capco.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import com.capco.dao.IVisaRequestDAO;
import com.capco.entity.Employee;
import com.capco.entity.TravelDestinations;
import com.capco.entity.VisaRequest;
import com.capco.exception.VTRException;
import static com.capco.utility.VTRUtility.isNull;

@SuppressWarnings("deprecation")
@Service
public class VisaRequestService implements IVisaRequestService {

	private Map<String, String> currentNextQueue = new HashMap<String, String>();

	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	private IVisaRequestDAO vrDAO;

	VisaRequestService(){
		currentNextQueue.put("EMPLOYEE", "MANAGER");
		currentNextQueue.put("MANAGER", "FINANCE");
		currentNextQueue.put("FINANCE", "PARTNER");
		currentNextQueue.put("PARTNERtoMANAGER", "MANAGER");
		currentNextQueue.put("PARTNERtoFINANCE", "FINANCE");
		currentNextQueue.put("PARTNER", "COMPLETED");
	}
	
	@Override
	public List<VisaRequest> getAllVisaRequestEnteredByEmployee(int empID) {
		return vrDAO.getAllVisaRequestEnteredByEmployee(empID);
	}
	
	@Override
	public List<VisaRequest> getAllVisaActionForEmployee(int empID) {
		List<VisaRequest> vrActionList = new ArrayList<VisaRequest>();
		
		List<VisaRequest> vrActionListForManager = null;
		List<VisaRequest> vrActionListForGroup = null;
		
		
		if(isEmployeeM6orM7(empID)) {
			vrActionListForManager = vrDAO.getVisaActionForManager(empID);
		}
		//else {
		
		String groupName = "";
				
		try {
			groupName = getEmployeeGroup(empID);
		} catch (Exception e) {

		}
		if("FINANCE".equalsIgnoreCase(groupName)) {
			vrActionListForGroup = vrDAO.getVisaActionForFinance();
		}
		else if("PARTNER".equalsIgnoreCase(groupName)) {
			vrActionListForGroup = vrDAO.getVisaActionForPartner();
		}
		
		
		if(vrActionListForManager != null) {
			for(VisaRequest vrequest : vrActionListForManager) {
				vrActionList.add(vrequest);
			}
		}
		
		if(vrActionListForGroup != null) {
			for(VisaRequest vrequest : vrActionListForGroup) {
				vrActionList.add(vrequest);
			}
		}
		
		//}
		return vrActionList;
	}
	
	private boolean isEmployeeM6orM7(int empID) {
		Employee emp = vrDAO.getEmployee(empID);
		if("M6".equalsIgnoreCase(emp.getMlevel()) || "M7".equalsIgnoreCase(emp.getMlevel())) {
			return true;
		}
		return false;
	}
	
	private String getEmployeeGroup(int empID) {
		return vrDAO.getEmployeeGroup(empID).getGroupname();
	}
	
	@Override
	public List<Employee> getAllEmployee(int empID) {
		return vrDAO.getAllEmployee(empID);
	}
	
	@Override
	public List<Employee> getM6AndM7(int empID) {
		return vrDAO.getM6AndM7(empID);
	}
	
	@Override
	public Employee getEmployee(int empID) {
		return vrDAO.getEmployee(empID);
	}
	
	@Override
	public Employee getEmployee(String capcoLoginID) {
		return vrDAO.getEmployee(capcoLoginID);
	}
	
	@Override
	public List<TravelDestinations> getTravelDestinations() {
		return vrDAO.getTravelDestinations();
	}

	private boolean validateEmployeeDetails(int empID, String requestType, VisaRequest vrObj) throws VTRException {
		
		int tEmpID = vrObj.getTravelersempid();
		String tName = vrObj.getTravelersname();
		String tMlevel = vrObj.getTravelersmlevel();
		String tDesignation = vrObj.getTravelersdesignation();
		String tGender = vrObj.getTravelersgender();
		String tMailID = vrObj.getTravelersmailid();
		int aManagerID = vrObj.getApprovingmanager();
		String tDest = vrObj.getTraveldestination();
		String visaType = vrObj.getVisatype();
		String typeOfVisit = vrObj.getTypeofvisit();
		String projectCode = vrObj.getProjectcode();
		String activityCode = vrObj.getActivitycode();
		
		Employee tEmp = null;
		
		if( isNull(tEmpID) ) {
			throw new VTRException("Invalid Travelers Employee ID: "+tEmpID+"!");
		}
		
		try{
			tEmp = getEmployee(tEmpID);
		}
		catch(Exception e){
			throw new VTRException("Travelers Employee ID: "+tEmpID+" not found!");
		}
		
		if( isNull(tName) ) {
			throw new VTRException("Travelers Employee Name: "+tName+" cannot be null or blank!");
		}
		
		if( !tName.equals(tEmp.getEmpname()) ) {
			throw new VTRException("Invalid Travelers Employee Name: "+tName+" :: "+tEmp.getEmpname());
		}
		
		if( isNull(tMlevel) ) {
			throw new VTRException("Travelers M Level: "+tName+" cannot be null or blank!");
		}
		
		if( !tMlevel.equals(tEmp.getMlevel()) ) {
			throw new VTRException("Invalid Travelers M Level: "+tMlevel+" :: "+tEmp.getMlevel());
		}
		
		if( isNull(tDesignation) ) {
			throw new VTRException("Travelers Designation: "+tDesignation+" cannot be null or blank!");
		}
		
		if( !tDesignation.equals(tEmp.getDesignation()) ) {
			throw new VTRException("Invalid Travelers Designation: "+tDesignation+" :: "+tEmp.getDesignation());
		}
		
		if( isNull(tGender) ) {
			throw new VTRException("Travelers Gender: "+tGender+" cannot be null or blank!");
		}
		
		if( !tGender.equals(tEmp.getGender()) ) {
			throw new VTRException("Invalid Travelers Gender: "+tGender+" :: "+tEmp.getGender());
		}
		
		if( isNull(tMailID) ) {
			throw new VTRException("Travelers Mail ID: "+tMailID+" cannot be null or blank!");
		}
		
		if( !tMailID.equals(tEmp.getMailid()) ) {
			throw new VTRException("Invalid Travelers Mail ID: "+tMailID+" :: "+tEmp.getMailid());
		}
		
		if(isNull(vrObj.getInqueue()) || "EMPLOYEE".equalsIgnoreCase(vrObj.getInqueue())) {
			if(vrObj.isSelforothers()) {
				if(empID != tEmpID) {
					throw new VTRException("Logged in User Emp. ID: " + empID + " and Travelers Emp. ID: " + tEmpID
							+ " should be same when Visa Request enetering for Self!");
				}
			}
			else {
				if(empID == tEmpID) {
					throw new VTRException("Logged in User Emp. ID: " + empID + " and Travelers Emp. ID: " + tEmpID
							+ " should not be same when Visa Request enetering for Others!");
				}
			}
		}
		
		if( isNull(aManagerID) ) {
			throw new VTRException("Invalid Approving Manager ID: "+aManagerID+"!");
		}
		
		try{
			getEmployee(aManagerID);
		}
		catch(Exception e){
			throw new VTRException("Approving Manager ID: "+aManagerID+" not found!");
		}
		
		if(tEmpID == aManagerID) {
			throw new VTRException("Traveller and Approving Manager cannot be same!");
		}
		
		if( isNull(tDest) ) {
			throw new VTRException("Travelers Destination: "+tDest+" cannot be null or blank!");
		}
		
		// TO DO
		// Travelers Destination - to be validated with the predefined list
		
		if( isNull(visaType) ) {
			throw new VTRException("Travelers Visa Type: "+visaType+" cannot be null or blank!");
		}
		
		// TO DO
		// Travelers Visa Type - to be validated with the predefined list
		
		if( isNull(typeOfVisit) ) {
			throw new VTRException("Travelers Type of visit: "+typeOfVisit+" cannot be null or blank!");
		}
		
		// TO DO
		// Travelers Type of visit - to be validated with the predefined list
		
		if( isNull(projectCode)) {
			throw new VTRException("Invalid Project Code: "+projectCode+"!");
		}
		
		if(projectCode.length() != 7) {
			throw new VTRException("Invalid Project Code: "+projectCode+"!");
		}
		
		if( isNull(activityCode)) {
			throw new VTRException("Invalid Activity Code: "+activityCode+"!");
		}
		
		if(activityCode.length() != 2) {
			throw new VTRException("Invalid Activity Code: "+activityCode+"!");
		}
		
		return true;
	}
	
/*	private boolean validateQueue() {
		return true;
	}*/
	
	private boolean validAddorUpdateRequest(int empID, String requestType, VisaRequest vrObj, VisaRequest currentVRObj)
			throws VTRException {
		
		try{
			getEmployee(empID);
		}
		catch(Exception e){
			throw new VTRException("Employee: "+empID+" not found!");
		}
		
		if (!"CREATE".equalsIgnoreCase(requestType) && !"DRAFT".equalsIgnoreCase(requestType)
				&& !"CREATE_NEXT".equalsIgnoreCase(requestType) && !"NEXT".equalsIgnoreCase(requestType)
				&& !"PARTNERtoMANAGER".equalsIgnoreCase(requestType)
				&& !"PARTNERtoFinance".equalsIgnoreCase(requestType) && !"PARTNER".equalsIgnoreCase(requestType)) {
			
			throw new VTRException("Invalid Request Type: "+requestType+"!");
		}
		
		if("CREATE".equalsIgnoreCase(requestType) || "CREATE_NEXT".equalsIgnoreCase(requestType)) {
			validateEmployeeDetails(empID, requestType, vrObj);
		}
		else if("DRAFT".equalsIgnoreCase(requestType)) {
			validateEmployeeDetails(empID, requestType, vrObj);
			
			String vrInQueue = currentVRObj.getInqueue();
			
			if(isNull(vrInQueue)) {
				throw new VTRException("Current Queue of the Visa Request cannot be null or blank!");
			}
			if( !"EMPLOYEE".equalsIgnoreCase(vrInQueue) ) {
				throw new VTRException("Cannot perform this action! As the current Queue of the Visa Request is: "+vrInQueue+"!");
			}
		}
		else if("NEXT".equalsIgnoreCase(requestType)) {
			validateEmployeeDetails(empID, requestType, vrObj);
			
			String currentQ = vrObj.getInqueue();
			String inDBQ = currentVRObj.getInqueue();
			
			if(!currentQ.equalsIgnoreCase(inDBQ)) {
				throw new VTRException("Invalid Queue: "+currentQ+" received :: Actual Queue: "+inDBQ+" !");
			}
			
			if("MANAGER".equalsIgnoreCase(currentQ)) {
				String managerComments = vrObj.getManagerapprovalcomments();
				if(isNull(managerComments) || managerComments.length() > 300) {
					throw new VTRException("Comments should not be blank or length should not be greater than 300 characters!");
				}
			}
			else if("FINANCE".equalsIgnoreCase(currentQ)) {
				String financeComments = vrObj.getFinanceteamcomments();
				if(isNull(financeComments) || financeComments.length() > 300) {
					throw new VTRException("Comments should not be blank or length should not be greater than 300 characters!");
				}
			}
			else if("PARTNER".equalsIgnoreCase(currentQ)) {
				String partnerComments = vrObj.getPartnercomments();
				if(isNull(partnerComments) || partnerComments.length() > 300) {
					throw new VTRException("Comments should not be blank or length should not be greater than 300 characters!");
				}
			}
		}
		else if("PARTNERtoMANAGER".equalsIgnoreCase(requestType) || "PARTNERtoFINANCE".equalsIgnoreCase(requestType)) {
			String partnerComments = vrObj.getPartnercomments();
			if(isNull(partnerComments) || partnerComments.length() > 300) {
				throw new VTRException("Comments should not be blank or length should not be greater than 300 characters!");
			}
		}
		return true;
	}
	
	
	@Override
	public void createOrUpdateVisaRequest(int empID, String requestType, VisaRequest vrObj) throws VTRException {
		
		VisaRequest currentVRObj = null;
		
		if(!"CREATE".equalsIgnoreCase(requestType) && !"CREATE_NEXT".equalsIgnoreCase(requestType)) {
			String vrID = vrObj.getVisarequestid();
			
			if(isNull(vrID)) {
				throw new VTRException("Visa Request ID cannot be null or blank!");
			}
			try {
				currentVRObj = vrDAO.getVisaRequestById(vrID);
			}
			catch(Exception e){
				throw new VTRException("Visa Request For ID: "+vrID+" not found!");
			}
		}
		
		if(validAddorUpdateRequest(empID, requestType, vrObj, currentVRObj)) {
			
			String currentQ = vrObj.getInqueue();
			String nextQ = getNextQueue(vrObj.getInqueue());
			
			if("CREATE".equalsIgnoreCase(requestType)) {
				vrObj.setEnteredby(empID);
				vrObj.setStatus("DRAFT");
				vrObj.setInqueue("EMPLOYEE");
				vrDAO.createVisaRequest(vrObj);
			}
			else if("DRAFT".equalsIgnoreCase(requestType)) {
				vrObj.setStatus("DRAFT");
				vrObj.setInqueue("EMPLOYEE");
				vrDAO.updateVisaRequest(vrObj);
			}
			else if ("CREATE_NEXT".equalsIgnoreCase(requestType)) {
				vrObj.setEnteredby(empID);
				vrObj.setStatus("IN_PROGRESS");
				vrObj.setInqueue("MANAGER");
				vrDAO.createVisaRequest(vrObj);
			}
			else if ("NEXT".equalsIgnoreCase(requestType)) {
				if("EMPLOYEE".equalsIgnoreCase(currentQ)) {
					vrObj.setStatus("IN_PROGRESS");
					vrObj.setInqueue(nextQ);
					vrDAO.updateVisaRequest(vrObj);
				}
				else if("MANAGER".equalsIgnoreCase(currentQ)) {
					vrObj.setStatus("IN_PROGRESS");
					vrObj.setInqueue(nextQ);
					vrObj.setManagerapprovaldate(new Date());
					vrDAO.updateVisaRequest(vrObj);
				}
				else if("FINANCE".equalsIgnoreCase(currentQ)) {
					currentVRObj.setStatus("IN_PROGRESS");
					currentVRObj.setInqueue(nextQ);
					currentVRObj.setIsbillable(vrObj.isIsbillable());
					currentVRObj.setFinanceteamcomments(vrObj.getFinanceteamcomments());
					currentVRObj.setFinanceteamupdatedby(empID);
					currentVRObj.setFinanceteamupdateddate(new Date());
					vrDAO.updateVisaRequest(currentVRObj);
				}
				else if("PARTNER".equalsIgnoreCase(currentQ)) {
					currentVRObj.setStatus("APPROVED");
					currentVRObj.setInqueue(nextQ);
					currentVRObj.setPartnercomments(vrObj.getPartnercomments());
					currentVRObj.setPartnerupdatedby(empID);
					currentVRObj.setPartnerupdateddate(new Date());
					vrDAO.updateVisaRequest(currentVRObj);
				}
			}
			else if ("PARTNERtoMANAGER".equalsIgnoreCase(requestType) || "PARTNERtoFINANCE".equalsIgnoreCase(requestType)) {
				currentVRObj.setStatus("IN_PROGRESS");
				currentVRObj.setInqueue(getNextQueue(requestType));
				currentVRObj.setPartnercomments(vrObj.getPartnercomments());
				currentVRObj.setPartnerupdatedby(empID);
				currentVRObj.setPartnerupdateddate(new Date());
				vrDAO.updateVisaRequest(currentVRObj);
			}
		}
	}
	
	private String getNextQueue(String currentQ) {
		return currentNextQueue.get(currentQ);
	}

	@Override
	public void deleteVisaRequest(int empID, String visaRequestID) {
		
		VisaRequest vrObj = vrDAO.getVisaRequestById(visaRequestID);
		/*
		 * Should implement the validations
		 * 
		 * if (vrObj.getEnteredby() != empID) {
			System.out.println("Only entered user can delete the Visa Request!");
		}*/
		vrDAO.deleteVisaRequest(vrObj);
	}
	
	@Override
	public boolean validateUser(String userID, String pwd) {
		
		/*boolean isValidUser = false;
		
		AndFilter filter = new AndFilter();
		
		filter.and(new EqualsFilter("uid", userID));
		
		isValidUser = ldapTemplate.authenticate(DistinguishedName.EMPTY_PATH, filter.toString(), pwd);
		
		return isValidUser;*/
		
		return true;
	}
	
	@Override
	public void sendTestMail() {
		Properties props = new Properties();
		//props.put("mail.smtp.host", "10.132.204.112");
		props.put("mail.smtp.host", "smarthost.fisglobal.com");
		
		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(false);
		
		Message msg = new MimeMessage(mailSession);
		
		String myEmail = "santhosh.kumar@capco.com";
		InternetAddress addFrom = null;
		InternetAddress addTo = null;
		
		try {
			addFrom = new InternetAddress(myEmail);
			addTo = new InternetAddress(myEmail);

			msg.setFrom(addFrom);
			msg.setRecipient(Message.RecipientType.TO, addTo);
			msg.setSubject("Test Mail");
			msg.setText("This is a test mail...!");
			
			Transport.send(msg);
		}
		catch (AddressException addExcp) {
			throw new VTRException("Internal error! Contact your System Admin."+addExcp.getMessage());
		}
		catch (MessagingException msgExcp) {
			throw new VTRException("Internal error! Contact your System Admin."+msgExcp.getMessage());
		}
	}

}
