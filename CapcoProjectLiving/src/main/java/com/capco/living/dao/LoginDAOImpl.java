package com.capco.living.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capco.living.custom.exception.LivingException.LivingDAOException;
import com.capco.living.model.Login;
import com.capco.living.model.PasswordResetToken;
import com.capco.living.model.User;

/**
 * @author e5544700
 */

@Repository
public class LoginDAOImpl implements LoginDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOG = Logger.getLogger(LoginDAOImpl.class);
	
	@Override
	public User findUser(String email) throws LivingDAOException {
		
		LOG.info("LoginDAOImpl: findUser : started");
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			return session.get(User.class, email);
				
			
		} catch (HibernateException e) {
		
			
			LOG.error("LoginServiceImpl: findUser : Exception caught:"+e);
			throw new LivingDAOException(e);
		}
			
	}
	
	public void register(User user) throws LivingDAOException {
		
		LOG.info("LoginDAOImpl: register : started");
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			session.save(user);
			
		} catch (HibernateException e) {
		
			
			LOG.error("LoginServiceImpl: login : Exception caught:"+e);
			throw new LivingDAOException(e);
		}
			
		LOG.info("LoginDAOImpl: register : ended");
	}

	public void updatePassword(Login login) throws LivingDAOException {
		
		LOG.info("LoginDAOImpl: register : started");
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			User user = session.get(User.class, login.getEmail());
			user.setPassword(login.getPassword());
			session.persist(user);
			
		} catch (HibernateException e) {
		
			
			LOG.error("LoginServiceImpl: register : Exception caught:"+e);
			throw new LivingDAOException(e);
		}
			
		LOG.info("LoginDAOImpl: register : ended");
		
	}

	@Override
	public void saveToken(PasswordResetToken myToken) throws LivingDAOException {
		
		
		LOG.info("LoginDAOImpl: saveToken : started");
		Session session = sessionFactory.getCurrentSession();
		
		try {
			PasswordResetToken p = session.get(PasswordResetToken.class, myToken.getEmail());
			if(p!=null)
				destroyToken(myToken.getEmail());
			session.save(myToken);
				
			
		} catch (HibernateException e) {
		
			
			LOG.error("LoginServiceImpl: findUser : Exception caught:"+e);
			throw new LivingDAOException(e);
		}
		
	}

	@Override
	public PasswordResetToken getToken(String email) throws LivingDAOException {
		
		LOG.info("LoginDAOImpl: getToken : started");
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			return session.get(PasswordResetToken.class, email);	
			
		} catch (HibernateException e) {
		
			
			LOG.error("LoginServiceImpl: getToken : Exception caught:"+e);
			throw new LivingDAOException(e);
		}
	}

	@Override
	public void destroyToken(String email) throws LivingDAOException {
	
		LOG.info("LoginDAOImpl: destroyToken : started");
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
		PasswordResetToken token = session.get(PasswordResetToken.class, email);	
		session.delete(token);
			
		} catch (HibernateException e) {
		
			
			LOG.error("LoginServiceImpl: destroyToken : Exception caught:"+e);
			throw new LivingDAOException(e);
		}
		
	}

}
