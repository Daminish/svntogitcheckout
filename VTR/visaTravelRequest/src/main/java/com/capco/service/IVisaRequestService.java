package com.capco.service;

import java.util.List;

import com.capco.entity.Employee;
import com.capco.entity.TravelDestinations;
import com.capco.entity.VisaRequest;
import com.capco.exception.VTRException;

public interface IVisaRequestService {

	List<VisaRequest> getAllVisaRequestEnteredByEmployee(int empID);
	List<VisaRequest> getAllVisaActionForEmployee(int empID);
	List<Employee> getAllEmployee(int empID);
	List<Employee> getM6AndM7(int empID);
	Employee getEmployee(int empID);
	Employee getEmployee(String capcoLoginID);
	List<TravelDestinations> getTravelDestinations();
	void createOrUpdateVisaRequest(int empID, String requestType, VisaRequest vrObj) throws VTRException;
	void deleteVisaRequest(int empID, String visaRequestID);
	
	boolean validateUser(String userID, String pwd);
	void sendTestMail();

}
