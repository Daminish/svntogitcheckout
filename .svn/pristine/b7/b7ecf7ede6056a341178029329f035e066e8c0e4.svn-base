package com.capco.travel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.RequestHistoryLogBO;
import com.capco.travel.service.ApproverService;
import com.capco.travel.service.MainRequestService;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.MainRequestBaseVO;
import com.capco.travel.vo.RequestListDTO;

@RestController
public class ApproverActionController {

	private final Logger logger = Logger.getLogger(ApproverActionController.class);

	@Autowired
	private MainRequestService mainService;

	@Autowired
	ApproverService approverService;


	/**
	 * This method will get all requests for approver id
	 * 	  
	 * @author e5544344
	 * @methodName getAllRequestByApproverId
	 * @param Integer
	 * @return ResponseEntity<RequestListDTO>
	 * @throws TravelServiceException 
	 * 	 */		
	@RequestMapping(value = "/getAllRequest/{approverId}", method = RequestMethod.GET, headers = "Accept=application/json", produces ="application/json")
	public ResponseEntity<RequestListDTO> getAllRequestByApproverId(@PathVariable("approverId") Integer approverId, HttpServletRequest request) throws TravelServiceException{
		logger.info("ApproverActionController : getAllRequestByApproverId: Started");
		HttpSession session = request.getSession();
		Integer tokenId = (Integer) session.getAttribute("tokenId");
		String userId = (String) session.getAttribute("userId");
		if((tokenId != null && tokenId != 0) && mainService.tokenValidation(tokenId, userId)) {
			RequestListDTO requestListDTO=new RequestListDTO();
			if(approverId == 0 || approverId==null) {
				logger.info("travel request for approver id " + approverId + " not found");			
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}		
			try{
				requestListDTO.setRequestListVO(approverService.getAllRequestByApproverId(approverId));
			}catch (TravelServiceException ex) {
				logger.error("ApproverActionController : getAllRequestByApproverId: TravelServiceException Caught : " + ex);
			}
			return new ResponseEntity<>(requestListDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity("token not found for requested user", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method will change current status of a request
	 * 	  
	 * @author e5544344
	 * @methodName updateCurrentStatusByapproverID
	 * @param MainRequestBaseVO (Updated body)
	 * @return ResponseEntity<MainRequestTableVO>
	 * @throws TravelServiceException 
	 * 	 */	
	@RequestMapping(value = "/approveRequest", method = RequestMethod.POST, headers = "Accept=application/json", produces ="application/json")
	public ResponseEntity<MainRequestBaseVO> updateCurrentStatusByapproverID(@RequestBody MainRequestBaseVO updateRequest,HttpServletRequest request) throws TravelServiceException{
		logger.info("ApproverActionController : updateCurrentStatusByapproverID: Started");
		if(updateRequest== null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		HttpSession session = request.getSession();	
		Integer tokenId = (Integer) session.getAttribute("tokenId");
		String userId = (String) session.getAttribute("userId");
		int approverId=0;
		MainRequestBaseVO newVo=null;
		Integer requestId=updateRequest.getRequestId();
		MainRequestBO oldBo;
		if((tokenId != null && tokenId != 0) && mainService.tokenValidation(tokenId, userId)) {
			if(userId == null){
				logger.info("ApproverActionController : updateCurrentStatusByapproverID: No user ID");
				return new ResponseEntity("no user id",HttpStatus.BAD_REQUEST);
			}		
			try {
				approverId = approverService.getEmployeeIdByUserID(userId);
				oldBo = approverService.getRequestById(requestId);	
				newVo = approverService.updateMainRequestTable(oldBo,updateRequest);
				//inserting requestHistoryLog
				RequestHistoryLogBO requestHistoryLogBO = mainService.createApproverRequestHistoryLog(oldBo, newVo,approverId);
				mainService.insertRequestHistoryLog(requestHistoryLogBO);
				
				//sending email notification
				if(newVo.getCurrentStatus().equals(TravelConstants.L2_PENDING)) {
					mainService.sendEmailNotification(requestId, TravelConstants.EMAIL_NEW);
				}
				else {
					mainService.sendEmailNotification(requestId, newVo.getCurrentStatus());
				}
				
				
				
				logger.info("ApproverActionController : updateCurrentStatusByapproverID: Ended");
			} catch (TravelServiceException e1) {
				logger.error("ApproverActionController : updateCurrentStatusByapproverID: TravelServiceException Caught : " + e1);	
				
			}	
			return new ResponseEntity<>(newVo,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("token not found for requested user", HttpStatus.BAD_REQUEST);
		}
	}


}
