package com.capco.living.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capco.living.custom.exception.LivingException.LivingServiceException;
import com.capco.living.model.PasswordResetToken;
import com.capco.living.service.ForgotPasswordService;

/**
 * This service is created to handle forgot password functionality for user
 * @author e5544700
 *
 */

@RestController
@RequestMapping("/living")
public class ForgotPasswordController {
	
	private static final Logger LOG = Logger.getLogger(ForgotPasswordController.class);
	
	@Autowired
	ForgotPasswordService forgotPasswordservice;
	
	/**
     *This method is for forgot Password
     * @methodName forgotPassword
     * @param userId
     * @return ResponseEntity<JSONObject>
     * @throws LivingServiceException
     * 
      */
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json") 
	public ResponseEntity<JSONObject> forgotPassword(@RequestParam("userId") String email){	
		
		LOG.info("ForgotPasswordController: forgot Password : started");
		
		JSONObject json = new JSONObject();
		try {
			forgotPasswordservice.forgotPassword(email);
			json.put("Status Code", 200);
			return new ResponseEntity<JSONObject>(json, HttpStatus.OK);
			
		} catch (LivingServiceException e) {
		
			json.put("Status Code", 500);
			return new ResponseEntity<JSONObject>(json, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	/**
     *This method is for validating reset token
     * @methodName validateToken
     * @param PasswordResetToken model
     * @return ResponseEntity<JSONObject>
     * @throws LivingServiceException
     * 
      */
	
	@RequestMapping(value = "/validatetoken", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json") 
	public ResponseEntity<JSONObject> validateToken(@RequestBody PasswordResetToken token){	
		
		
		LOG.info("ForgotPasswordController: validateToken : started");
		
		JSONObject json = new JSONObject();
		HttpStatus statusCode =null;
		String email = token.getEmail();
		try {
			
			PasswordResetToken p = forgotPasswordservice.getToken(email); 
			if(p==null)
				throw new LivingServiceException("No Token");
			if(p.getToken()==token.getToken() && p.getEmail().equals(token.getEmail())) {
				
				if(Calendar.getInstance().getTime().getTime() - p.getExpiryDate().getTime() > 600000) {
					
					json.put("Status Code", 401);
					json.put("message", "Expired");
					statusCode = HttpStatus.UNAUTHORIZED;
					
				}else {
					
					json.put("Status Code", 200);
					statusCode = HttpStatus.OK;
				}
					
			}else {
				
				json.put("Status Code", 401);
				json.put("message", "invalid id or token");
				statusCode = HttpStatus.UNAUTHORIZED;
				
			}
			
			forgotPasswordservice.destroyToken(email);
			
		} catch (LivingServiceException e) {
		
			if(e.getMessage().equals("No Token"))
			{
				json.put("Status Code", 404);
				json.put("message", "Sorry but this token has already been used once. Try again.");
				statusCode = HttpStatus.NOT_FOUND;
			}
			else {
				
				json.put("Status Code", 500);
				statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		
		LOG.info("ForgotPasswordController: validateToken : ended");
		return new ResponseEntity<JSONObject>(json, statusCode);
		
	}
}
