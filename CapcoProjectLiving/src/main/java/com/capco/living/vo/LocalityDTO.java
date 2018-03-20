package com.capco.living.vo;

import org.mockito.internal.util.DefaultMockingDetails;

import com.capco.living.model.Locality;
import com.capco.living.model.SchoolLocality;


/**
 * @author e5544847,e5544698
 */
 
public class LocalityDTO {
	
	Locality locality;
	
	DetailsVo Details;
	

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	public DetailsVo getDetails() {
		return Details;
	}

	public void setDetails(DetailsVo details) {
		Details = details;
	}

}
