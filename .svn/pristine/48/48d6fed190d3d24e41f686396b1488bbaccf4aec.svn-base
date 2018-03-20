package com.capco.living.dao;

import java.util.List;



import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capco.living.model.City;

/**
 * @author e5544847,e5544698
 */
 
@Repository
@Transactional
public class CityDaoImpl implements CityDao {
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<City> getAllCities() {		
		Session session = getSessionFactory().getCurrentSession();
		List<City> cities=session.createQuery("from City").list();		
		return cities;
	}
		
	@Override
	public City getCityByCityName(String city_name) {
		Session session=getSessionFactory().getCurrentSession();
		Query query=session.createQuery("from City where city_name=:name");
		query.setParameter("name", city_name);
		City city=(City) query.uniqueResult();
		return city;
	}
	
	
}
