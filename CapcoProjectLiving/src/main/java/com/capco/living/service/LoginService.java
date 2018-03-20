package com.capco.living.service;

import com.capco.living.custom.exception.LivingException.LivingServiceException;
import com.capco.living.model.Login;
import com.capco.living.model.User;

/**
 * This service is created to authenticate user
 * @author e5544700
 */

public interface LoginService {

	public User validateUser(Login login) throws LivingServiceException;
	public void register(User user) throws LivingServiceException;
	public void updatePassword(Login login) throws LivingServiceException;
	public User findUser(String email) throws LivingServiceException;
	
}
