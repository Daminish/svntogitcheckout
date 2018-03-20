package com.capco.living.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capco.living.custom.exception.LivingException.LivingServiceException;
import com.capco.living.model.Login;
import com.capco.living.model.User;
import com.capco.living.service.LoginService;

/**
 * These test cases are created to authenticate login user
 * @author e5544700
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class, secure = false)
public class LoginControllerTest {
	
	/**
     *This method is to validate user credentials Successfully
     * @methodName test_ValidateUserSuccessfully
     * @throws LivingServiceException
     * 
      */
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoginService  loginService;
	
    @Test
    public void test_ValidateUserSuccessfully() throws Exception { 

    	User mockUser = new User(1,"admin@capco.com","ADMIN","abc","abc","Admin","6765456780","436578","chinchwad","kharadi");
    	
    	String exampleLoginJson = "{\"email\":\"admin@capco.com\",\"password\":\"ADMIN\"}";
    	
		Mockito.when(
				loginService.validateUser(Mockito.any(Login.class))).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/living/login")
				.accept(MediaType.APPLICATION_JSON).content(exampleLoginJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
       
    
    
    /**
     *This method is to validate user credentials with wrong username
     * @methodName test_ValidateUserWithWrongUname
     * @throws LivingServiceException
     * 
      */
    
	@Test
    public void test_ValidateUserWithWrongUserId() throws Exception {
    	
    	String exampleLoginJson = "{\"email\":\"jack@capco.com\",\"password\":\"ADMIN\"}";
    	
		Mockito.when(loginService.validateUser(Mockito.any(Login.class))).thenThrow(new LivingServiceException("Invalid credentials"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/living/login")
				.accept(MediaType.APPLICATION_JSON).content(exampleLoginJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());

    }
    
    /**
     *This method is to validate user credentials with wrong password
     * @methodName test_ValidateUserWithWrongPassword
     * @throws LivingServiceException
     * 
      */
    
    @Test
    public void test_ValidateUserWithWrongPassword() throws Exception {
    	
    	String exampleLoginJson = "{\"email\":\"admin@capco.com\",\"password\":\"bhgt61\"}";
    	
    	Mockito.when(
				loginService.validateUser(Mockito.any(Login.class))).thenThrow(new LivingServiceException("Invalid credentials"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/living/login")
				.accept(MediaType.APPLICATION_JSON).content(exampleLoginJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());

    }
    
    /**
     *This method is to validate user credentials with wrong URI
     * @methodName test_ValidateUserWithWrongURI
     * @throws LivingServiceException
     * 
      */
    
    
    
    @Test
    public void test_RegisterSuccessfully() throws Exception { 
    	
    	String exampleRegisterJson = "{\"userId\":\"noopur@capco.com\",\"password\":\"noopur\",\"firstName\":\"Noopur\","
    		   	+ "\"lastName\":\"Singh\",\"userTypeName\":\"user\",\"mobileNo\":\"6765456780\",\"thaiId\":\"436578\","
    		   	+ "\"homeAddress\":\"chinchwad\",\"workAddress\":\"kharadi\"}";
    	
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/living/register")
				.accept(MediaType.APPLICATION_JSON).content(exampleRegisterJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}
    
    
    /*@Test
    public void test_RegisterWithExistingUserId() throws Exception { 
    	
    	String exampleRegisterJson = "{\"userId\":\"noopur@capco.com\",\"password\":\"noopur\",\"firstName\":\"Noopur\","
    		   	+ "\"lastName\":\"Singh\",\"userTypeName\":\"user\",\"mobileNo\":\"6765456780\",\"thaiId\":\"436578\","
    		   	+ "\"homeAddress\":\"chinchwad\",\"workAddress\":\"kharadi\"}";
    	
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/living/register")
				.accept(MediaType.APPLICATION_JSON).content(exampleRegisterJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.ALREADY_REPORTED.value(), response.getStatus());

    }*/
    
}
