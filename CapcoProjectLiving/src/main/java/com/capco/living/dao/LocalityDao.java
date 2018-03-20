package com.capco.living.dao;

import java.util.List;


import com.capco.living.model.Locality;
import com.capco.living.vo.LocalityDTO;
/**
 * @author e5544847,e5544698
 */
 
public interface LocalityDao {
	public List<Locality> getLocalityByCityName(int city_id);

	LocalityDTO getLocalityByLocalityId(int localityId);

}
