package com.capco.living.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author e5544847,e5544698
 */
 
@Entity
@Table(name = "broadband_locality", schema = "capco_living_portal")
public class BroadBandLocality {
	

	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "service_id")
	private int service_id;
   
    
    @Column(name = "locality_id")
	
    private int locality_id;
    
    @Column(name = "service_rating")
    private String service_rating;

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getLocality_id() {
		return locality_id;
	}

	public void setLocality_id(int locality_id) {
		this.locality_id = locality_id;
	}

	public String getService_rating() {
		return service_rating;
	}

	public void setService_rating(String service_rating) {
		this.service_rating = service_rating;
	}
    
    

}
