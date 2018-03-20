package com.capco.living.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author e5544847,e5544698
 */
 
@Entity
@Table(name = "locality_crime_relation", schema = "capco_living_portal")
public class Crimelocality {
	
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "crime_id")
	private int crime_id;
   
    
    @Column(name = "locality_id")	
    private int locality_id;
    
    @Column(name = "crime_rating")
    private String crime_rating;
    
    public int getCrime_id() {
		return crime_id;
	}

	public void setCrime_id(int crime_id) {
		this.crime_id = crime_id;
	}

	public int getLocality_id() {
		return locality_id;
	}

	public void setLocality_id(int locality_id) {
		this.locality_id = locality_id;
	}

	public String getCrime_rating() {
		return crime_rating;
	}

	public void setCrime_rating(String crime_rating) {
		this.crime_rating = crime_rating;
	}

	


}
