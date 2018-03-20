package com.capco.living.vo;

import com.capco.living.model.PropertyBO;


/**
 * @author e5544847,e5544698
 */
 

public class DetailsVo {
	
	PropertyBO flat;
	PropertyBO villa;
	PropertyBO house;
	SchoolRatingVo schoolRating;
	CrimeRatingVo crimeRating;
	BroadBandRatingVo broadBandRating;
PopulationVo  population;

	public PropertyBO getFlat() {
		return flat;
	}
	public void setFlat(PropertyBO flat) {
		this.flat = flat;
	}
	public PropertyBO getVilla() {
		return villa;
	}
	public void setVilla(PropertyBO villa) {
		this.villa = villa;
	}
	public PropertyBO getHouse() {
		return house;
	}
	public void setHouse(PropertyBO house) {
		this.house = house;
	}
	
	
	public SchoolRatingVo getSchoolRating() {
		return schoolRating;
	}
	public void setSchoolRating(SchoolRatingVo schoolRating) {
		this.schoolRating = schoolRating;
	}
	
	
	
	
	
	public CrimeRatingVo getCrimeRating() {
		return crimeRating;
	}
	public void setCrimeRating(CrimeRatingVo crimeRating) {
		this.crimeRating = crimeRating;
	}
	public BroadBandRatingVo getBroadBandRating() {
		return broadBandRating;
	}
	public void setBroadBandRating(BroadBandRatingVo broadBandRating) {
		this.broadBandRating = broadBandRating;
	}
	public PopulationVo getPopulation() {
		return population;
	}
	public void setPopulation(PopulationVo population) {
		this.population = population;
	}
	
	
	
	

}
