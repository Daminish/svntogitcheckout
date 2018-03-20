package com.capco.travel.vo;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ErrorCodeHandlerVO {
	
	private HttpStatus errorCode;
	private String errorMessage;
	
	public ErrorCodeHandlerVO() {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ErrorCodeHandlerVO(HttpStatus errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the errorCode
	 */
	public HttpStatus getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
