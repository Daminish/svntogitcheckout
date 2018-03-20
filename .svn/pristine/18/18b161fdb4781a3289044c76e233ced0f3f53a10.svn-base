package com.capco.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.capco.entity.Employee;
import com.capco.entity.EmployeeGroup;
import com.capco.entity.TravelDestinations;
import com.capco.entity.VisaRequest;
import com.capco.exception.VTRException;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class VisaRequestDAO implements IVisaRequestDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeSelectQuery(String sqlQuery) {

		List<Object[]> cleanedResults = new ArrayList<Object[]>();

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sqlQuery);
		List<Object[]> hibernateResults = query.list();

		// Hibernate does not return always a List<Object[]>, but a list of
		// strings or integers, so it is necessary to check the returned values

		if (!hibernateResults.isEmpty()) {
			if (hibernateResults.get(0) instanceof Object[]) {
				cleanedResults = hibernateResults;
			} else {
				Object[] row;
				// Use a 'for' because 'foreach' sometimes has casting
				// exceptions converting to object
				for (int i = 0; i < hibernateResults.size(); i++) {
					row = new Object[1];
					row[0] = hibernateResults.get(i);
					cleanedResults.add(row);
				}
			}
		}

		return cleanedResults;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<VisaRequest> getAllVisaRequestEnteredByEmployee(int empID) {
		String hql = "FROM VisaRequest as vr where vr.enteredby = " + empID + " ORDER BY vr.visarequestid";
		return (List<VisaRequest>) hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VisaRequest> getVisaActionForManager(int empID) {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM VisaRequest as vr where vr.approvingmanager = ");
		hql.append(empID);
		hql.append(" and vr.inqueue = 'MANAGER' ORDER BY vr.visarequestid");
		return (List<VisaRequest>) hibernateTemplate.find(hql.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VisaRequest> getVisaActionForFinance() {
		String hql = "FROM VisaRequest as vr where vr.inqueue = 'FINANCE' ORDER BY vr.visarequestid";
		return (List<VisaRequest>) hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VisaRequest> getVisaActionForPartner() {
		String hql = "FROM VisaRequest as vr where vr.inqueue = 'PARTNER' ORDER BY vr.visarequestid";
		return (List<VisaRequest>) hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployee(int empID) {

		String hql = "FROM Employee as emp where emp.empid != " + empID + "  ORDER BY emp.empname";
		return (List<Employee>) hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getM6AndM7(int empID) {

		String hql = "FROM Employee as emp where emp.mlevel in ('M6','M7') ORDER BY emp.empname";
		return (List<Employee>) hibernateTemplate.find(hql);
	}
	
	@Override
	public Employee getEmployee(int empID) {
		return hibernateTemplate.get(Employee.class, empID);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Employee getEmployee(String capcoLoginID) throws VTRException {
		String hql = "FROM Employee as emp where emp.capcoLoginID = '" + capcoLoginID + "'";
		List<Employee> empList = (List<Employee>) hibernateTemplate.find(hql);
		if(empList == null || empList.size() <= 0) {
			throw new VTRException("Employee: "+capcoLoginID+" - not found!");
		}
		return (Employee) empList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TravelDestinations> getTravelDestinations() {

		String hql = "FROM TravelDestinations as tdest ORDER BY tdest.country";
		return (List<TravelDestinations>) hibernateTemplate.find(hql);
	}

	@Override
	public VisaRequest getVisaRequestById(String visaRequestID) {
		return hibernateTemplate.get(VisaRequest.class, visaRequestID);
	}

	@Override
	public void createVisaRequest(VisaRequest vrObj) {
		hibernateTemplate.save(vrObj);
	}

	@Override
	public void updateVisaRequest(VisaRequest vrObj) {
		hibernateTemplate.update(vrObj);
	}

	@Override
	public void deleteVisaRequest(VisaRequest vrObj) {
		hibernateTemplate.delete(vrObj);
	}
	
	@Override
	public EmployeeGroup getEmployeeGroup(int empID) {
		return hibernateTemplate.get(EmployeeGroup.class, empID);
	}
}
