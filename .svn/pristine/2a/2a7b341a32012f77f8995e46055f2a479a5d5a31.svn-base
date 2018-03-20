package com.capco.hcm.view.fragments;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import com.capco.hcm.model.StaticTableObject;
import com.capco.hcm.sql.StaticTableDao;
import com.capco.hcm.utils.StringUtils;
/**
 * @author Mohit Gangil
 *
 */

@SessionScoped
@ManagedBean(name="staticValuesView")
public class StaticTableView implements Serializable {
	private static final long serialVersionUID = 2679252052104772392L;
	
	//TODO add select category needs to be updated if there is not values for the field category
	
	private List<StaticTableObject> staticFieldList;
	private String fieldValue;
	private String fieldKey;
	private String fieldCategory;
	private List<StaticTableObject> staticsearchhistory;
	
	
	private String selectedCategory;
	private Map<String, String> filedCategoryInDb;
	
	@ManagedProperty (value="#{staticDao}")
	private StaticTableDao staticDao;
	
	//METHOD STARTS HERE
	@PostConstruct	
	public void init()
	{
		try{
		List<String> catList = staticDao.getAllCategories();
		
		if(filedCategoryInDb == null)
			filedCategoryInDb = new HashMap<String, String>();
		
		for(String cat : catList){
			filedCategoryInDb.put(cat, cat);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	/**
	 * 
	 */
	public void loadAllStaticFieldsForCategory(){
		if(StringUtils.isNullOrEmpty(selectedCategory))
			return;
		staticFieldList = staticDao.getValuesForCategory(selectedCategory);
	}
	
	/**
	 * 
	 * @param sto
	 */
	public void updateExistingField(StaticTableObject sto){
		if(sto == null)
			return;
		
		staticDao.updateFieldValue(sto.getFieldName(), sto.getFieldCategory(), sto.getFieldKey());
	}
	
	/**
	 * 
	 */
	public void addNewFieldForCategory(){
		if(StringUtils.isNullOrEmpty(selectedCategory))
			return;
		
		staticDao.addFieldForCategory(fieldValue, selectedCategory, fieldKey);

		if(!filedCategoryInDb.containsKey(selectedCategory.trim()))
			filedCategoryInDb.put(selectedCategory, selectedCategory);
	}
	
	/**
	 * 
	 * @param sto
	 */
	public void deleteRecord(StaticTableObject sto){
		if(sto == null)
			return;
		
		staticDao.deleteFieldForCategory(sto.getFieldCategory(), sto.getFieldKey());
		loadAllStaticFieldsForCategory();
	}

	//GETTER AND SETTER 
	public List<StaticTableObject> getStaticFieldList() {
		return staticFieldList;
	}
	public void setStaticFieldList(List<StaticTableObject> staticFieldList) {
		this.staticFieldList = staticFieldList;
	}
	public String getFieldCategory() {
		return fieldCategory;
	}
	public void setFieldCategory(String fieldCategory) {
		this.fieldCategory = fieldCategory;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public StaticTableDao getStaticDao() {
		return staticDao;
	}
	public void setStaticDao(StaticTableDao staticDao) {
		this.staticDao = staticDao;
	}
	public String getFieldKey() {
		return fieldKey;
	}
	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}
	public String getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	public Map<String, String> getFiledCategoryInDb() {
		if(filedCategoryInDb == null)
			filedCategoryInDb = new HashMap<String, String>();
		return filedCategoryInDb;
	}
	public void setFiledCategoryInDb(Map<String, String> filedCategoryInDb) {
		this.filedCategoryInDb = filedCategoryInDb;
	}

	public List<StaticTableObject> getStaticsearchhistory() {
		return staticsearchhistory;
	}

	public void setStaticsearchhistory(List<StaticTableObject> staticsearchhistory) {
		this.staticsearchhistory = staticsearchhistory;
	}
	
}