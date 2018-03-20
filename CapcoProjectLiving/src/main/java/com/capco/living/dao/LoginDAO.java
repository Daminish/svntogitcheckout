package com.capco.living.dao;


/**
 * @author e5544700
 */


import com.capco.living.custom.exception.LivingException.LivingDAOException;
import com.capco.living.model.PasswordResetToken;
import com.capco.living.model.User;
import com.capco.living.model.Login;

public interface LoginDAO {

	public User findUser(String email) throws LivingDAOException;
	public void register(User user) throws LivingDAOException ;
	public void updatePassword(Login login) throws LivingDAOException;
	public void saveToken(PasswordResetToken myToken) throws LivingDAOException;
	public PasswordResetToken getToken(String email) throws LivingDAOException;
	void destroyToken(String email) throws LivingDAOException;
	
}
