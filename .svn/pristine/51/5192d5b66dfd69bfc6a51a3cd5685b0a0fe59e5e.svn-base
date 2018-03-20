package com.capco.travel.dao;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.RequestHistoryLogBO;

/**
 * @author e5542274
 *
 */
public interface MainRequestDAO {
	public Integer insertMainReuqest(MainRequestBO mainRequestBO) throws DAOException;
	public MainRequestBO getRequestDetailsByRequestId(int requestId) throws DAOException;
	public List<MainRequestBO> getRequestDetailsByRequestedId(int requestedBy) throws DAOException;
	public boolean cancelRequestDetailsByRequestId(MainRequestBO mainRequest) throws DAOException;
	public MainRequestBO updateMainReuqest(MainRequestBO mainRequestBO) throws DAOException;
	public Integer insertRequestHistoryLog(RequestHistoryLogBO requestHistoryLogBO) throws DAOException;
	/*public List<RequestHistoryLogBO> getAllRequestHistoryLogs() throws DAOException;
	public List<RequestHistoryLogBO> getRequestHistoryLogsByRequestId(int requestId) throws DAOException;
	public List<RequestHistoryLogBO> getRequestHistoryLogsByUpdatedBy(int updatedBy) throws DAOException;*/
	public List<Object[]> getRequestHistoryCommentsByRequestId(int requestId) throws DAOException;
	public List<MainRequestBO> getLatestApprovedRequest(int requestedBy) throws DAOException;
	public boolean isRequestIdValid(int requestId) throws DAOException;
	
}
