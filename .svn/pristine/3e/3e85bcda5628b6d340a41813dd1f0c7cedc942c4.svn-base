package com.capco.living.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author e5544847,e5544698
 */
 
@Entity
@Table(name = "Locality_population_relation", schema = "capco_living_portal")
public class PopulationLocality {
private static final long serialVersionUID = 1L;
	
   
	
   
    @Id
    @Column(name = "locality_id")	
    private int locality_id;
    
    @Column(name = "male_population")
    private double male_population;
    
    @Column(name = "female_population")
    private double female_population;

    @Column(name = "childrens_below_18")
    private double childrens_below_18;

	public int getLocality_id() {
		return locality_id;
	}

	public void setLocality_id(int locality_id) {
		this.locality_id = locality_id;
	}

	public double getMale_population() {
		return male_population;
	}

	public void setMale_population(double male_population) {
		this.male_population = male_population;
	}

	public double getFemale_population() {
		return female_population;
	}

	public void setFemale_population(double female_population) {
		this.female_population = female_population;
	}

	public double getChildrens_below_18() {
		return childrens_below_18;
	}

	public void setChildrens_below_18(double childrens_below_18) {
		this.childrens_below_18 = childrens_below_18;
	}

    
	
}
