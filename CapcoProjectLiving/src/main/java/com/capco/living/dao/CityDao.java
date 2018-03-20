package com.capco.living.dao;

import java.util.List;


import com.capco.living.model.City;

/**
 * @author e5544847,e5544698
 */
 

public interface CityDao {
	public List<City> getAllCities();

	City getCityByCityName(String city_name);
}
