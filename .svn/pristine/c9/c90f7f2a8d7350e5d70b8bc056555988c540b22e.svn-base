package com.capco.living.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capco.living.custom.exception.LivingException.LivingServiceException;
import com.capco.living.model.City;
import com.capco.living.service.CityGuideService;
import com.capco.living.service.LoginService;

/**
 * This service is created to search for city
 * @author e5544847,e5544698
 */
 
 

@RestController
@RequestMapping("/search")
public class CityGuideController {
	

	
	/**
     *This method is to search city
     * @methodName getAllCities,getCityByCityName
     * @pathVariable cityName
     * @return ResponseEntity<JSONObject>
     * 
      */
    @Autowired
	private CityGuideService cityguideservice;
	
	@RequestMapping(value="/city",method=RequestMethod.GET,headers = "Accept=application/json" )	
	public ResponseEntity<List<City>> getAllCities()
	{
		 List<City> cities=cityguideservice.getAllCities();
		return new ResponseEntity<>(cities,HttpStatus.OK);
		
	}
	@RequestMapping(value="/city/{cityName}",method=RequestMethod.GET,headers = "Accept=application/json" )
	public ResponseEntity<City> getCityByCityName(@PathVariable("cityName") String cityName)
	{
		City city=cityguideservice.getCityByCityName(cityName);
		return new ResponseEntity<>(city,HttpStatus.OK);
		
	}
}
