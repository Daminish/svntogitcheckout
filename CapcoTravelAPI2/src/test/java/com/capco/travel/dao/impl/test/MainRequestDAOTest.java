package com.capco.travel.dao.impl.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.MainRequestDAO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.util.TravelConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/web.xml","file:src/main/webapp/WEB-INF/spring-servlet.xml" })
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,MainRequestDAOTest.class})
public class MainRequestDAOTest  extends AbstractTestExecutionListener{
	@Autowired
	MainRequestDAO mainRequestDAO;
	
	
	MainRequestBO mainRequestBO=new MainRequestBO();
	@Before
	public void setupData() {
	System.out.println("setting data");		
	mainRequestBO.setRequestId(863343);
	mainRequestBO.setRequestedBy(16294);
	mainRequestBO.setRequestType("Self");
	mainRequestBO.setCurrentStatus("Approved");
	mainRequestBO.setCreatedOn(new Date());
	mainRequestBO.setModifiedOn(new Date());
	mainRequestBO.setApproverId(16292);
	mainRequestBO.setBillable(true);
	mainRequestBO.setRequestedFor("shubham");
	mainRequestBO.setProjectCode("8888");
	mainRequestBO.setProjectName("capco_internal");
	mainRequestBO.setRemark("");
	try {
		System.out.println(mainRequestDAO.insertMainReuqest(this.mainRequestBO));
	} catch (DAOException e) {
		e.printStackTrace();
	}
	System.out.println("exit");
	}
	
	@After
	public void deleteRecord(){
		try {
			mainRequestDAO.deleteRequestByRequestIdTest(mainRequestBO);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void TestFindRequestById() {
		MainRequestBO mainRequestBO=null;
		try {
			mainRequestBO = mainRequestDAO.getRequestDetailsByRequestId(this.mainRequestBO.getRequestId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(this.mainRequestBO.getRequestId(), mainRequestBO.getRequestId());
		assertEquals(this.mainRequestBO.getApproverId(), mainRequestBO.getApproverId());
		assertEquals(this.mainRequestBO.getRequestedBy(), mainRequestBO.getRequestedBy());
		assertEquals(this.mainRequestBO.getRequestType(), mainRequestBO.getRequestType());
		assertEquals(this.mainRequestBO.getCurrentStatus(), mainRequestBO.getCurrentStatus());
		//assertEquals(this.mainRequestBO.getCreatedOn(), mainRequestBO.getCreatedOn());
		//assertEquals(this.mainRequestBO.getModifiedOn(), mainRequestBO.getModifiedOn());
		assertEquals(this.mainRequestBO.getBillable(), mainRequestBO.getBillable());
		assertEquals(this.mainRequestBO.getRequestedFor(), mainRequestBO.getRequestedFor());
		assertEquals(this.mainRequestBO.getProjectCode(), mainRequestBO.getProjectCode());
		assertEquals(this.mainRequestBO.getProjectName(), mainRequestBO.getProjectName());		
	}
	
	@Test
	public void TestUpdateRequest(){
		MainRequestBO updatedObject=new MainRequestBO();
		this.mainRequestBO.setRequestedFor("others");
		try {
			mainRequestDAO.updateMainRequest(mainRequestBO);
			updatedObject=mainRequestDAO.getRequestDetailsByRequestId(this.mainRequestBO.getRequestId());
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals("others", updatedObject.getRequestedFor());	
	}
	
	@Test
	public void DeleteRequestByRequestId(){
		MainRequestBO updatedObject=new MainRequestBO();
		this.mainRequestBO.setCurrentStatus(TravelConstants.CANCELLED);
		try {
			mainRequestDAO.updateMainRequest(mainRequestBO);
			updatedObject=mainRequestDAO.getRequestDetailsByRequestId(this.mainRequestBO.getRequestId());
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(TravelConstants.CANCELLED, updatedObject.getCurrentStatus());		
	}
}
