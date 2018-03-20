package com.capco.living.service;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capco.living.custom.exception.LivingException.LivingDAOException;
import com.capco.living.custom.exception.LivingException.LivingServiceException;
import com.capco.living.dao.LoginDAO;
import com.capco.living.model.Login;
import com.capco.living.model.User;

/**
 * This service is created to authenticate user
 * @author e5544700
 */

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;
	
	@Autowired
	private EmailNotificationService emailNotificationService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger LOG = Logger.getLogger(LoginServiceImpl.class);
	
	@Override
	public User validateUser(Login login) throws LivingServiceException{
		
		try {
			LOG.info("LoginService: login : started");
			
			User user = loginDAO.findUser(login.getEmail());
			
			String hashed = passwordEncoder.encode(login.getPassword());
			
			if(user==null || !user.getPassword().equals(hashed))
				throw new Exception("Invalid credentials");
			return user;
			
			
		} catch (Exception e) {
			
			LOG.error("LoginServiceImpl: login : Exception caught:"+e);
			throw new LivingServiceException(e.getMessage());
		}
	}
	
	public void register(User user) throws LivingServiceException{
		
		try {
			
			LOG.info("LoginService: registration : started");
			String sub = "Welcome to Living!";
			
			String msg = "We are glad to have you aboard! Hope you enjoy our services.\n"
					+ "Should you have any queries, feel free to reach out to our toll free customer care numbers.\n"
					+ "Thanks again! :)";
			
			emailNotificationService.sendEmail(user.getUserId(), sub, msg);
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			loginDAO.register(user);
			
			
		} catch (Exception e) {
		
			LOG.error("LoginServiceImpl: registration : Exception caught:"+e);
			throw new LivingServiceException(e);
			
		} 
		LOG.info("LoginService: registration : ended");
	}
	
	public void updatePassword(Login login) throws LivingServiceException{
		
		try {
			
			LOG.info("LoginService: update : started");
			login.setPassword(passwordEncoder.encode(login.getPassword()));
			loginDAO.updatePassword(login);

		} catch (Exception e) {
		
			LOG.error("LoginServiceImpl: update : Exception caught:"+e);
			throw new LivingServiceException(e);
		}
		LOG.info("LoginService: update : ended");
	}

	@Override
	public User findUser(String email) throws LivingServiceException {
		
		try {
			
			LOG.info("LoginService: findUser : started");
			return loginDAO.findUser(email);

			
			
		} catch (LivingDAOException e) {
		
			LOG.error("LoginServiceImpl: findUser : Exception caught:"+e);
			throw new LivingServiceException(e);
		}
		
	}

}
