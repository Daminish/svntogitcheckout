package com.capco.living.dao;

import static org.mockito.Matchers.anyObject;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.capco.living.model.Locality;
import com.capco.living.model.PropertyBO;
import com.capco.living.model.SchoolLocality;
import com.capco.living.vo.BroadBandRatingVo;
import com.capco.living.vo.CrimeRatingVo;
import com.capco.living.vo.DetailsVo;
import com.capco.living.vo.LocalityDTO;
import com.capco.living.vo.PopulationVo;
import com.capco.living.vo.SchoolRatingVo;

/**
 * @author e5544847,e5544698
 */
 
@Repository
@Transactional

public class LocalityDaoImpl implements LocalityDao {
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Locality> getLocalityByCityName(int city_id) {
		Session session=getSessionFactory().getCurrentSession();
		Query query=session.createQuery("from Locality where city_id=:city_id");
		query.setParameter("city_id", city_id);
		List<Locality> localities=query.list();
		
		return localities;
	}

	@Override
	public LocalityDTO getLocalityByLocalityId(int localityId) {
	
		Session session=getSessionFactory().getCurrentSession();
		Query query=session.createQuery("from Locality where locality_id=:locality_id");
		query.setParameter("locality_id", localityId);
		Locality locality=(Locality) query.uniqueResult();
		
		//school
		 session=getSessionFactory().getCurrentSession();
	      query=session.createQuery("select round(avg(school_rating),2) from SchoolLocality where locality_id=:locality_id");
		query.setParameter("locality_id", localityId);
		Double schoolRating = Double.parseDouble(query.uniqueResult().toString());
		SchoolRatingVo ratingVo=new SchoolRatingVo();
		ratingVo.setAvg_school_rating(schoolRating);
		
		//broadband
		session=getSessionFactory().getCurrentSession();
	      query=session.createQuery("select round(avg(service_rating),2) from BroadBandLocality where locality_id=:locality_id");
		query.setParameter("locality_id", localityId);
		Double serviceRating = Double.parseDouble(query.uniqueResult().toString());
		BroadBandRatingVo ratingVo1=new BroadBandRatingVo();
		ratingVo1.setAvg_service_rating(serviceRating);
		
		//crime
		session=getSessionFactory().getCurrentSession();
	      query=session.createQuery("select round(avg(crime_rating),2) from Crimelocality where locality_id=:locality_id");
		query.setParameter("locality_id", localityId);
		Double crimeRating = Double.parseDouble(query.uniqueResult().toString());
		CrimeRatingVo ratingVo2=new CrimeRatingVo();
		ratingVo2.setAvg_crime_rating(crimeRating);
		

		//population
		session=getSessionFactory().getCurrentSession();
	      query=session.createQuery("select round(avg(p.male_population),2),avg(female_population),avg(childrens_below_18) from PopulationLocality p where locality_id=:locality_id");
		query.setParameter("locality_id", localityId);
		List<Object[]> data1=query.list();
		DetailsVo detailsVo=new DetailsVo();
		PopulationVo avgPopulation=new PopulationVo();
		for(Object[] row:data1) {
			
			
			if(row[0]!=null) {
				avgPopulation.setAvg_male_population(Double.parseDouble(row[0].toString()));
			}

			if(row[1]!=null) {
				avgPopulation.setAvg_female_population(Double.parseDouble(row[1].toString()));
			}
			

			if(row[2]!=null) {
				avgPopulation.setAvg_childrens_below_18(Double.parseDouble(row[2].toString()));
			}
			
		}
			
		
		LocalityDTO localityDTO=new LocalityDTO();
		
		
		localityDTO.setLocality(locality);
		session=getSessionFactory().getCurrentSession();
		String Sql="select min(p1.property_value), max(p1.property_value),min(p2.property_value),max(p2.property_value),min(p3.property_value), max(p3.property_value) " + 
				"from property p1,property p2,property p3 where (p1.locality_id=:locality_id and p1.property_type='Flat') " + 
				"or (p2.locality_id=:locality_id and p2.property_type='villa') " + 
				" or (p3.locality_id=:locality_id and p3.property_type='house');";
		SQLQuery query1=session.createSQLQuery(Sql);
		query1.setParameter("locality_id", locality.getLocality_id());
		List<Object[]> data=query1.list();
		//DetailsVo detailsVo=new DetailsVo();
		
		for(Object[] row:data) {
			PropertyBO flat=new PropertyBO();
			PropertyBO villa=new PropertyBO();
			PropertyBO house=new PropertyBO();
			if(row[0]!=null) {
				flat.setMin_value(Double.parseDouble(row[0].toString()));
				
			}
			if(row[1]!=null) {
				flat.setMax_value(Double.parseDouble(row[1].toString()));
				
			}
			if(row[2]!=null) {
				villa.setMin_value(Double.parseDouble(row[2].toString()));
				
			}
			if(row[3]!=null) {
				villa.setMax_value(Double.parseDouble(row[3].toString()));
				
			}
			if(row[4]!=null) {
				house.setMin_value(Double.parseDouble(row[4].toString()));
			
			}
			if(row[5]!=null) {
				house.setMax_value(Double.parseDouble(row[5].toString()));
				
			}
			detailsVo.setFlat(flat);
			detailsVo.setVilla(villa);
			detailsVo.setHouse(house);
		}
		detailsVo.setSchoolRating(ratingVo);
		localityDTO.setDetails(detailsVo);
		detailsVo.setBroadBandRating(ratingVo1);
		detailsVo.setCrimeRating(ratingVo2);
		detailsVo.setPopulation(avgPopulation);
		
		return localityDTO;
		
	}


	
}
