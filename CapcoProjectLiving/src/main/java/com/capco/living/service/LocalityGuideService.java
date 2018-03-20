package com.capco.living.service;

import java.util.List;

import com.capco.living.model.Locality;
import com.capco.living.vo.LocalityDTO;
/**
 * This service is created to search locality in that city
 * @author e5544700,e5544698
 */

public interface LocalityGuideService {
	public List<Locality> getLocalityByCityName(int city_id);

	LocalityDTO getLocalityByLocalityId(int localityId);


}
