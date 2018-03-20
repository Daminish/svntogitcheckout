package com.capco.travel.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.RequestHistoryLogBO;
import com.capco.travel.vo.MainRequestBaseVO;
import com.capco.travel.vo.MainRequestVO;
import com.capco.travel.vo.RequestHistoryLogVO;

/**
 * @author e5542274
 *
 */
public interface MainRequestService {
	public Integer insertMainRequest(MainRequestVO newRequest) throws TravelServiceException;
	public MainRequestVO getRequestDetailsByRequestId(Integer requestId) throws TravelServiceException;
	public List<MainRequestVO> getRequestDetailsByRequestedId(Integer requestedBy) throws TravelServiceException;
	public Boolean cancelRequestDetailsByRequestId(MainRequestBO request) throws TravelServiceException;
	public MainRequestVO updateMainRequest(MainRequestBO dbData, MainRequestVO updateRequest) throws TravelServiceException;
	public MainRequestBO getMainRequestBOByRequestId(Integer requestId) throws TravelServiceException;
	public Integer insertRequestHistoryLog(RequestHistoryLogBO requestHistoryLogBO) throws TravelServiceException;
	/*public List<RequestHistoryLogVO> getAllRequestHistoryLogs()throws TravelServiceException;
	public List<RequestHistoryLogVO> getRequestHistoryLogsByRequestId(int requestId) throws TravelServiceException;
	public List<RequestHistoryLogVO> getRequestHistoryLogsByUpdatedBy(int updatedBy) throws TravelServiceException;*/
	public ResponseEntity validateTravelRequestObject(MainRequestVO newRequest);
	public RequestHistoryLogBO createApproverRequestHistoryLog(MainRequestBO oldRequest, MainRequestBaseVO newRequest,int oldId);
	public boolean tokenValidation(int tokenId, String capcoUserId) throws TravelServiceException;
	public List<MainRequestVO> getLatestApprovedRequest(Integer requestedBy) throws TravelServiceException;
	public boolean isRequestIdValid(int requestId) throws TravelServiceException;
	public void sendEmailNotification(int requestId, String changeDone);
}
