package com.capco.living.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capco.living.custom.exception.LivingException.LivingServiceException;
import com.capco.living.model.Locality;
import com.capco.living.service.LocalityGuideService;
import com.capco.living.vo.LocalityDTO;

/**
 * This service is created to search locality in that city
 * @author e5544847,e5544698
 */


@RestController
@RequestMapping("/search")
public class LocalityGuideController {
	@Autowired
	private LocalityGuideService localityGuideService;
	
	/**
     *This method is to search locality in that city
     * @methodName getAllLocalitiesByCitId,getLocalityByLocalityId
     * @pathVariable city_id,LocalityId
     * @return ResponseEntity<JSONObject>
     * 
      */



	@RequestMapping(value="/city/{city_id}/locality",method=RequestMethod.GET,headers = "Accept=application/json" )	
	public ResponseEntity<List<Locality>> getAllLocalitiesByCitId(@PathVariable("city_id") int cityId)
	{
		 List<Locality> localities=localityGuideService.getLocalityByCityName(cityId);
		return new ResponseEntity<>(localities,HttpStatus.OK);
	}
	
	@RequestMapping(value="/city/{city_id}/locality/{LocalityId}",method=RequestMethod.GET,headers = "Accept=application/json" )	
	public ResponseEntity<LocalityDTO> getLocalityByLocalityId(@PathVariable("LocalityId") int localityId)
	{
		LocalityDTO locality=localityGuideService.getLocalityByLocalityId(localityId);
		return new ResponseEntity<>(locality,HttpStatus.OK);
		
	}

}
