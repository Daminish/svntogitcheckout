package com.capco.dao;

import java.util.List;

import com.capco.entity.Employee;
import com.capco.entity.EmployeeGroup;
import com.capco.entity.TravelDestinations;
import com.capco.entity.VisaRequest;

public interface IVisaRequestDAO {

	List<Object[]> executeSelectQuery(String sqlQuery);
	
	List<VisaRequest> getAllVisaRequestEnteredByEmployee(int empID);
	List<VisaRequest> getVisaActionForManager(int empID);
	List<VisaRequest> getVisaActionForFinance();
	List<VisaRequest> getVisaActionForPartner();
	List<Employee> getAllEmployee(int empID);
	List<Employee> getM6AndM7(int empID);
	Employee getEmployee(int empID);
	Employee getEmployee(String capcoLoginID);
	List<TravelDestinations> getTravelDestinations();
	VisaRequest getVisaRequestById(String visaRequestID);
	void createVisaRequest(VisaRequest vrObj);
	void updateVisaRequest(VisaRequest vrObj);
	void deleteVisaRequest(VisaRequest vrObj);
	EmployeeGroup getEmployeeGroup(int empID);
}
