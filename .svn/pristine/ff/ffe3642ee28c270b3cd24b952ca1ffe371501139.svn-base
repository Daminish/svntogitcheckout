package com.capco.living.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capco.living.dao.CityDao;
import com.capco.living.model.City;

/**
 * This service is created to search city
 * @author e5544847,e5544698
 */

@Service
public class CityGuidServiceImpl implements CityGuideService {

	@Autowired
	private CityDao citydao;
	
	@Override
	public List<City> getAllCities() {
		List<City> cities=null;
		cities=citydao.getAllCities();
		return cities;
	}
	
	@Override
	public City getCityByCityName(String city_name) {
		City city=citydao.getCityByCityName(city_name);
		return city;
	}

	
}
