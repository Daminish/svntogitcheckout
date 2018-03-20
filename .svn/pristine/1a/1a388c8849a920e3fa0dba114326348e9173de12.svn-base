package com.capco.travel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.MainRequestDAO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.RequestHistoryLogBO;

/**
 * This class is the DAO implementation for the MainRequest
 * @author e5542274
 *
 */
@Repository
@Transactional
public class MainRequestDAOImpl implements MainRequestDAO {
	private final static Logger logger = Logger.getLogger(MainRequestDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 */
	public Integer insertMainReuqest(MainRequestBO mainRequestBO) throws DAOException {
		logger.info("MainRequestDAOImpl : insertMainReuqest: Started");
		Integer requestId = 0;
		try {
			Session session = getSessionFactory().getCurrentSession();
			requestId = (Integer)session.save(mainRequestBO);	
			logger.info("MainRequestDAOImpl : insertMainReuqest: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : insertMainReuqest: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestId;
	}

	/**
	 * 
	 */
	public MainRequestBO updateMainReuqest(MainRequestBO mainRequestBO) throws DAOException {
		logger.info("MainRequestDAOImpl : updateMainReuqest: Started");
		Integer requestId = 0;
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.update(mainRequestBO);			
			logger.info("MainRequestDAOImpl : updateMainReuqest: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : updateMainReuqest: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return mainRequestBO;
	}

	/**
	 * 
	 */
	public MainRequestBO getRequestDetailsByRequestId(int requestId) throws DAOException {
		logger.info("MainRequestDAOImpl : getRequestDetailsByRequestId: Started");
		MainRequestBO mainRequestBO  = null;
		try {
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestId: Request Id: " + requestId);
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from MainRequestBO where requestId =:requestId";
			Query  query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			mainRequestBO = (MainRequestBO) query.uniqueResult();
			if(mainRequestBO == null) {
				return null;
			}
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestDetailsByRequestId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return mainRequestBO;
	}

	public boolean deleteRequestDetailsByRequestId(MainRequestBO mainRequestBO) throws DAOException {
		logger.info("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Started");
		boolean requestDeleted  = false;
		try {
			logger.info("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Request Id: " + mainRequestBO.getRequestId());
			Session session = getSessionFactory().getCurrentSession();
			session.saveOrUpdate(mainRequestBO);
			requestDeleted = true;
			logger.info("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Exception Caught: " + e);
			requestDeleted = false;
			throw new DAOException(e);
		}
		return requestDeleted;
	}

	/**
	 * this method will add entry in request_history table
	 * @author e5544354
	 * @methodName insertRequestHistoryLog
	 * @param RequestHistoryLogBO
	 * @return Integer
	 * @throws DAOException
	 */
	@Override
	public Integer insertRequestHistoryLog(RequestHistoryLogBO requestHistoryLogBO) throws DAOException {
		logger.info("MainRequestDAOImpl : insertRequestHistoryLog: Started");
		Integer i =0;
		try {
			Session session = getSessionFactory().getCurrentSession();
			i =(Integer) session.save(requestHistoryLogBO);
			logger.info("MainRequestDAOImpl : insertRequestHistoryLog: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : insertRequestHistoryLog: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return i;
	}

/*	*//**
	 * this method will get all entries from request_history table
	 * @author e5544354
	 * @methodName getAllRequestHistoryLogs
	 * @return Integer
	 * @throws DAOException
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public List<RequestHistoryLogBO> getAllRequestHistoryLogs() throws DAOException {
		logger.info("MainRequestDAOImpl : getAllRequestHistoryLogs: Started");
		List<RequestHistoryLogBO> requestHistoryList = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			requestHistoryList = session.createQuery("from RequestHistoryLogBO").list();
			logger.info("MainRequestDAOImpl : getAllRequestHistoryLogs: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getAllRequestHistoryLogs: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestHistoryList;
	}

	*//**
	 * this method will get entries from request_history table by request_id
	 * @author e5544354
	 * @methodName getRequestHistoryLogsByRequestId
	 * @param int
	 * @return Integer
	 * @throws DAOException
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public List<RequestHistoryLogBO> getRequestHistoryLogsByRequestId(int requestId) throws DAOException {
		logger.info("MainRequestDAOImpl : getRequestHistoryLogsByRequestId: Started");
		List<RequestHistoryLogBO> requestHistoryList = null;
		try {
			logger.info("MainRequestDAOImpl : getRequestHistoryLogsByRequestId: Request Id: " + requestId);
			
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from RequestHistoryLogBO where requestId =:requestId";
			Query query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			requestHistoryList = query.list();
			
			logger.info("MainRequestDAOImpl : getRequestHistoryLogsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestHistoryLogsByRequestId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestHistoryList;
	}
	


	*//**
	 * this method will get entries from request_history table by updated_by
	 * @author e5544354
	 * @methodName getRequestHistoryLogsByUpdatedBy
	 * @param int
	 * @return Integer
	 * @throws DAOException
	 *//*
	@Override
	public List<RequestHistoryLogBO> getRequestHistoryLogsByUpdatedBy(int updatedBy) throws DAOException {
		logger.info("MainRequestDAOImpl : getRequestHistoryLogsByUpdatedBy: Started");
		List<RequestHistoryLogBO> requestHistoryList = null;
		try {
			logger.info("MainRequestDAOImpl : getRequestHistoryLogsByUpdatedBy: Updated By: " + updatedBy);
			
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from RequestHistoryLogBO where updatedBy =:updatedBy";
			Query query = session.createQuery(sql);  
			query.setParameter("updatedBy", updatedBy);  
			requestHistoryList = query.list();
			
			logger.info("MainRequestDAOImpl : getRequestHistoryLogsByUpdatedBy: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestHistoryLogsByUpdatedBy: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestHistoryList;
	}*/
	
	/**
	 * this method will give comments from the request_history table
	 * @author e5544354
	 * @methodName getRequestHistoryCommentsByRequestId
	 * @param int
	 * @return List<Object[]>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRequestHistoryCommentsByRequestId(int requestId) throws DAOException {
		logger.info("MainRequestDAOImpl : getRequestHistoryCommentsByRequestId: Started");
		List<Object[]> list = new ArrayList<>();
		try {
			logger.info("MainRequestDAOImpl : getRequestHistoryCommentsByRequestId: Request Id: " + requestId);
			
			Session session = getSessionFactory().getCurrentSession();
			String sql ="select r.requestId, r.createdOn, r.status, r.remarks,  r.updatedBy, e.employeeName from EmployeeDetailsBO e, RequestHistoryLogBO r where e.employeeId = r.updatedBy and r.requestId = :requestId order by r.createdOn desc";
			Query query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			list  = query.list();
			
			logger.info("MainRequestDAOImpl : getRequestHistoryCommentsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestHistoryCommentsByRequestId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return list;
	}
	
	/**
	 * This method will get user all requests by requested Id (user id)
	 * @author e5545730
	 * @methodName getRequestDetailsByRequestedId
	 * @param MainRequestBO
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<MainRequestBO> getRequestDetailsByRequestedId(int requestedBy) throws DAOException {
		
	    logger.info("MainRequestDAOImpl : getRequestDetailsByRequestedId: Started");
		List<MainRequestBO> requestDetailsList = new ArrayList<MainRequestBO>();
		try {
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestedId: Request Id: " + requestedBy);
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from MainRequestBO where requestedBy =:requestedBy order by createdOn desc";
			Query query = session.createQuery(sql);  
			query.setParameter("requestedBy", requestedBy);  
			requestDetailsList = query.list();
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestedId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestDetailsByRequestedId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestDetailsList;
	}

	/**
	 * This method will get five approved user requests by requestedBy Id (user id)
	 * @author e5545730
	 * @methodName getLatestApprovedRequest
	 * @param MainRequestBO
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<MainRequestBO> getLatestApprovedRequest(int requestedBy) throws DAOException {
		logger.info("MainRequestDAOImpl : getLatestApprovedRequest: Started");
		List<MainRequestBO> requestDetailsList = null;
		try {
			logger.info("MainRequestDAOImpl : getLatestApprovedRequest: Request Id: " + requestedBy);	
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from MainRequestBO where requestedBy =:requestedBy and currentStatus ='Approved' order by createdOn desc";
			Query query = session.createQuery(sql);  
			query.setParameter("requestedBy", requestedBy); 
			query.setMaxResults(7);
			requestDetailsList = query.list();
			logger.info("MainRequestDAOImpl : getLatestApprovedRequest: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getLatestApprovedRequest: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestDetailsList;
	}

	
}

