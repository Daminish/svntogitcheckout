package com.capco.living.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capco.living.dao.LocalityDao;
import com.capco.living.model.City;
import com.capco.living.model.Locality;
import com.capco.living.vo.LocalityDTO;

/**
 * This service is created to search locality in that city
 * @author e5544847,e5544698
 */
@Service
public class LocalityGuideServiceImpl  implements LocalityGuideService{
	
	@Autowired
private LocalityDao localityDao;

	@Override
	public List<Locality> getLocalityByCityName(int city_id) {
		List<Locality> localities=null;
		localities=localityDao.getLocalityByCityName(city_id);
		return localities;
	}

	@Override
	public LocalityDTO getLocalityByLocalityId(int localityId) {

		LocalityDTO locality=localityDao.getLocalityByLocalityId(localityId);
		
		return locality;
	}
	
	

}
