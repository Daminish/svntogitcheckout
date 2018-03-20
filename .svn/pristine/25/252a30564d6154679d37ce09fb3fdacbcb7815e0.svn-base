package com.capco.living.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * This class is placeholder for Locality table
 * @author e5544847,e5544698
 */
 
@Entity
@Table(name = "locality", schema = "capco_living_portal")
public class Locality {
	private static final long serialVersionUID = 1L;

	@Column(name = "city_id")
	private int city_id;

	@Id	
	@Column(name = "locality_id")
	private int locality_id;
	
	@Column(name = "locality_name")
	private String locality_name;
	
	@Column(name="population")
	private int population;
	
	@Column(name = "lattitude")
	private double lattitude;

	@Column(name = "longitude")
	private double longitude;


	@Column(name = "crime_rating")
	private String crime_rating;

	@Column(name = "zip_code")
	private int zip_code;

	
	public String getLocality_name() {
		return locality_name;
	}

	public void setLocality_name(String locality_name) {
		this.locality_name = locality_name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public int getLocality_id() {
		return locality_id;
	}

	public void setLocality_id(int locality_id) {
		this.locality_id = locality_id;
	}

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCrime_rating() {
		return crime_rating;
	}

	public void setCrime_rating(String crime_rating) {
		this.crime_rating = crime_rating;
	}

	public int getZip_code() {
		return zip_code;
	}

	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}

	
}
