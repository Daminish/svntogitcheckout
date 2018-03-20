package com.capco.living.service;

import com.capco.living.custom.exception.LivingException.LivingServiceException;

public interface EmailNotificationService {

	public void sendEmail(String to,String sub,String msg) throws LivingServiceException;
		
}
