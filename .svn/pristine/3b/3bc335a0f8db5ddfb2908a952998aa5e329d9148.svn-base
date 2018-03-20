package com.capco.living.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capco.living.custom.exception.LivingException.LivingDAOException;
import com.capco.living.custom.exception.LivingException.LivingServiceException;
import com.capco.living.dao.LoginDAO;
import com.capco.living.model.PasswordResetToken;

import java.util.Calendar;
import java.util.Random;

/**
 * This service is provide Forgot Password functionality
 * @author e5544700
 */

@Service
@Transactional
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	
	@Autowired
	LoginDAO loginDAO;
	
	@Autowired
	EmailNotificationService emailNotificationService;
	
	@Override
	public void forgotPassword(String email) throws LivingServiceException {
		
		Random rnd = new Random();
		int token = 100000 + rnd.nextInt(900000);
	    createPasswordResetTokenForUser(email, token);
	    String subject = "Forgot Password";
	    String msg = "To verify, following is verification Code:\n"+token+"\n time: ";
	    msg+= Calendar.getInstance().getTime().toString();
	    				
	    emailNotificationService.sendEmail(email, subject, msg);
	    
	}
	
	public void createPasswordResetTokenForUser(String email, int token) throws LivingServiceException {
			
			PasswordResetToken myToken = new PasswordResetToken(token, email);
		    try {
		    	
				loginDAO.saveToken(myToken);
				
			} catch (LivingDAOException e) {
				
				throw new LivingServiceException(e);
			}
		    
	}

	@Override
	public PasswordResetToken getToken(String email) throws LivingServiceException {
		
	    try {
	    	
			return loginDAO.getToken(email);
			
		} catch (LivingDAOException e) {
			
			throw new LivingServiceException(e);
		}
	}

	@Override
	public void destroyToken(String email) throws LivingServiceException {
		
		try {
	    	
			loginDAO.destroyToken(email);
			
		} catch (LivingDAOException e) {
			
			throw new LivingServiceException(e);
		}
	}
	
	
}
