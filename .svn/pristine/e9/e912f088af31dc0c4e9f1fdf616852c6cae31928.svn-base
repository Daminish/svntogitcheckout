package com.capco.living.service;

import com.capco.living.custom.exception.LivingException.LivingServiceException;
import com.capco.living.model.PasswordResetToken;

/**
 * This service is created to provide forgot password related services
 * @author e5544700
 */

public interface ForgotPasswordService {

	public void forgotPassword(String email) throws LivingServiceException;
	public void createPasswordResetTokenForUser(String email, int token) throws LivingServiceException;
	public PasswordResetToken getToken(String email) throws LivingServiceException;
	public void destroyToken(String email) throws LivingServiceException;
}
