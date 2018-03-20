package com.capco.hcm.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
/**
 * @author Mohit Gangil
 *
 */

@FacesConverter("Char[]Converter")
public class StringToCharConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String inputVal) {
		if(StringUtils.isNullOrEmpty(inputVal))
			return null;
		
		char[] outputVal = null;
		try{
			outputVal = inputVal.toCharArray();
		}catch(ClassCastException e){
			FacesMessage errMsg = new FacesMessage("CANNOT CONVERT TO A CHAR ARRAY");
	        FacesContext.getCurrentInstance().addMessage(null, errMsg);
	        throw new ConverterException(errMsg.getSummary());
		}
		return outputVal;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		char[] inputVal = null;
		if ( value == null ) {
	        return null;
	    }
	    // value must be of a type that can be cast to a String.
	    try {
	        inputVal = (char[])value;
	    } catch (ClassCastException ce) {
	        FacesMessage errMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"CANNOT CONVERT TO A STRING",null);
	        //FacesContext.getCurrentInstance().addMessage(null, errMsg);
	        throw new ConverterException(errMsg);
	    }
	    
	    return new String(inputVal);
	}

}
