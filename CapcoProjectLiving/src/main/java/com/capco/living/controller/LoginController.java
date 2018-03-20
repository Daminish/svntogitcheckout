package com.capco.living.controller;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capco.living.custom.exception.LivingException.LivingServiceException;
import com.capco.living.model.Login;
import com.capco.living.model.User;
import com.capco.living.service.LoginService;
/**
 * This service is created to handle all on boarding processes for user
 * @author e5544700
 *
 */

@RestController
@RequestMapping("/living")
public class LoginController {

	private static final Logger LOG = Logger.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	/**
     *This method is to validate user credentials
     * @methodName validateUser
     * @param email,password
     * @return ResponseEntity<JSONObject>
     * @throws LivingServiceException
     * 
      */

	
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json") 
	@ResponseBody
	public ResponseEntity<JSONObject> validateUser(@RequestBody Login login){	

		LOG.info("LoginController: login : started");
		JSONObject json = new JSONObject();

		try {
			
			User user = loginService.validateUser(login);
			json.put("Status Code", 200);
			json.put("User", user);
			return new ResponseEntity<JSONObject>(json, HttpStatus.OK);


		} catch (LivingServiceException e) {
			
			if(e.getMessage().equals("Invalid credentials")) {
				
				json.put("Status Code", 401);
				return new ResponseEntity<JSONObject>(json, HttpStatus.UNAUTHORIZED);
			}
			LOG.error("LoginController: login : validateUser caught: "+e);
			json.put("Status Code", 500);
			return new ResponseEntity<JSONObject>(json, HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json") 
	public ResponseEntity<JSONObject> register(@RequestBody User user){	
		
		JSONObject json = new JSONObject();
		try {
			
			User user1 = loginService.findUser(user.getUserId());
			if(user1==null)
				loginService.register(user);
			else
				throw new LivingServiceException("Email ID already in use");
			
			json.put("Status Code", 201);
			return new ResponseEntity<JSONObject>(json, HttpStatus.CREATED);
			
		} catch (LivingServiceException e) {
		
			if(e.getMessage().equals("Email ID already in use")) {
				
				json.put("Status Code", 202);
				json.put("message", "Email ID already in use. Please use other emailId to signup.");
				return new ResponseEntity<JSONObject>(json, HttpStatus.ALREADY_REPORTED);
				
			}
			json.put("Status Code", 500);
			return new ResponseEntity<JSONObject>(json, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} 
		
		
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.PUT, headers = "Accept=application/json", produces = "application/json") 
	public ResponseEntity<JSONObject> updatePassword(@RequestBody Login login){	
		
		JSONObject json = new JSONObject();
		try {
			
			loginService.updatePassword(login);
			
			json.put("Status Code", 200);
			return new ResponseEntity<JSONObject>(json, HttpStatus.OK);
			
		} catch (LivingServiceException e) {
		
			json.put("Status Code", 500);
			return new ResponseEntity<JSONObject>(json, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json") 
	public ResponseEntity<JSONObject> findUser(@RequestParam("userId") String userId){	
		
		JSONObject json = new JSONObject();
		try {
			
			User user = loginService.findUser(userId);
			if(user==null) {
				
				json.put("Status Code", 404);
				return new ResponseEntity<JSONObject>(json, HttpStatus.NOT_FOUND);
			}
			else {
				json.put("User", user);
				json.put("Status Code", 200);
				
				return new ResponseEntity<JSONObject>(json, HttpStatus.OK);
			}
			
		} catch (LivingServiceException e) {
		
			json.put("Status Code", 500);
			return new ResponseEntity<JSONObject>(json, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
}
