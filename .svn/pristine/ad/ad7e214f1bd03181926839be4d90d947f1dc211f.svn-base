package com.capco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.capco.entity.Employee;
import com.capco.entity.TravelDestinations;
import com.capco.entity.VisaRequest;
import com.capco.exception.ErrorResponse;
import com.capco.exception.VTRException;
import com.capco.service.IVisaRequestService;

@RestController
@RequestMapping("/services")
public class VTRController {

	@Autowired
	private IVisaRequestService vrService;
	
	/*@Autowired
	private IVisaRequestDAO vrDAO;

	@RequestMapping(value = "/testservice", method = RequestMethod.GET)
	public void test() {
		List<Object[]> list = vrDAO.executeSelectQuery("select * from visarequest");
	}*/
	
	@RequestMapping(value = "/allVisaRequestByEmp/{empID}", method = RequestMethod.GET)
	public ResponseEntity<List<VisaRequest>> getAllVisaRequestEnteredByEmployee(@PathVariable("empID") Integer empID) {
		
		System.out.println("*** In VTRController.getAllVisaRequestEnteredByEmployee() ***");
		System.out.println("EmpID="+empID+"=");
		List<VisaRequest> list = vrService.getAllVisaRequestEnteredByEmployee(empID);
		return new ResponseEntity<List<VisaRequest>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/allVisaActionForEmp/{empID}", method = RequestMethod.GET)
	public ResponseEntity<List<VisaRequest>> getAllVisaActionForEmployee(@PathVariable("empID") Integer empID) {
		
		System.out.println("*** In VTRController.getAllVisaActionForEmployee() ***");
		System.out.println("EmpID="+empID+"=");
		
		List<VisaRequest> list = vrService.getAllVisaActionForEmployee(empID);
		return new ResponseEntity<List<VisaRequest>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllEmployee/{empID}", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployee(@PathVariable("empID") Integer empID) {
		
		System.out.println("*** In VTRController.getAllEmployee() ***");
		List<Employee> list = vrService.getAllEmployee(empID);
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getM6AndM7/{empID}", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getM6AndM7(@PathVariable("empID") Integer empID) {
		
		System.out.println("*** In VTRController.getM6AndM7() ***");
		List<Employee> list = vrService.getM6AndM7(empID);
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getEmployee/{empID}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable("empID") Integer empID) {
		
		System.out.println("*** In VTRController.getEmployee() ***");
		Employee employee = vrService.getEmployee(empID);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getTravelDestinations", method = RequestMethod.GET)
	public ResponseEntity<List<TravelDestinations>> getTravelDestinations() {
		
		System.out.println("*** In VTRController.getTravelDestinations() ***");
		List<TravelDestinations> list = vrService.getTravelDestinations();
		return new ResponseEntity<List<TravelDestinations>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/createOrUpdateVisaRequest/{empID}/{requestType}", method = RequestMethod.POST)
	public ResponseEntity<Void> createOrUpdateVisaRequest(@PathVariable("empID") Integer empID,
			@PathVariable("requestType") String requestType, @RequestBody VisaRequest visaRequest,
			UriComponentsBuilder builder) throws VTRException {

		System.out.println("*** In VTRController.createOrUpdateVisaRequest() ***");
		System.out.println("EmpID="+empID+"=");
		System.out.println("requestType="+requestType+"=");
		vrService.createOrUpdateVisaRequest(empID, requestType, visaRequest);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteVisaRequest/{empID}/{visaRequestID}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteVisaRequest(@PathVariable("empID") Integer empID,
			@PathVariable("visaRequestID") String visaRequestID) {
		
		System.out.println("*** In VTRController.deleteVisaRequest() ***");
		System.out.println("EmpID="+empID+"=");
		System.out.println("visaRequestID="+visaRequestID+"=");
		vrService.deleteVisaRequest(empID, visaRequestID);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/authenticateAndGetEmp/{capcoLoginID}/{pwd}", method = RequestMethod.GET)
	public ResponseEntity<Employee> authenticateAndGetEmp(@PathVariable("capcoLoginID") String capcoLoginID,
			@PathVariable("pwd") String pwd) {
		
		System.out.println("*** In VTRController.authenticateAndGetEmp() ***");
		System.out.println("capcoLoginID="+capcoLoginID+"=");
		
		try {
			if(!vrService.validateUser(capcoLoginID, pwd)) {
				throw new VTRException("Authentication failed!");
			}
			
			Employee employee = vrService.getEmployee(capcoLoginID);
			
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new VTRException("Internal error! Contact your System Admin."+e.getMessage());
		}
		
	}
	
	@RequestMapping(value="/sendTestMail", method = RequestMethod.GET)
	public ResponseEntity<Void> sendTestMail() {
		
		System.out.println("*** In VTRController.sendTestMail() ***");
		vrService.sendTestMail();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ExceptionHandler(VTRException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.PRECONDITION_FAILED);
	}

}
