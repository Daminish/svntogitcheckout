package com.capco.hcm.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.capco.hcm.controller.DatabaseController;
import com.capco.hcm.model.EmployeeBasicInfoObj;
import com.capco.hcm.model.StaticTableObject;

/**
 * @author Mohit Gangil
 *
 */

public class StaticTableDao implements Serializable {
	private static final long serialVersionUID = 2034998340767829603L;

	private DatabaseController dbCtrl;
	
	//GETTERS AND SETTERS
		public DatabaseController getDbCtrl() {
			return dbCtrl;
		}
		public void setDbCtrl(DatabaseController dbCtrl) {
			this.dbCtrl = dbCtrl;
		}
	
		
		
		public void updateEmpStaticdetails(List<StaticTableObject> empobj)
		{
			for(StaticTableObject obj:empobj)
			{
				if(null != obj.getFieldCategory() ||  !"0".equals(obj.getFieldCategory()))
				{
					
					System.out.println("Object main kya hain bai "+ obj);
					
					
						System.out.println("Comes under insert"+obj);
						
						addFieldForCategory(obj.getFieldName(), obj.getFieldCategory(), obj.getFieldKey());
					
				}
			}
		}
		
	/**
	 * 
	 * @return
	 */
	public List<String> getAllCategories(){
		List<String> categories = new ArrayList<String>();
		String query = "SELECT DISTINCT Field_Name FROM HCM_STATIC_TABLE WHERE Field_Category = 'CATEGORY'";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try{
			conn = dbCtrl.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()){
				categories.add(rs.getString("Field_Name"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return categories;
	}
	
	/**
	 * 
	 * @param category
	 * @return
	 */
	public List<StaticTableObject> getValuesForCategory(String category){
		List<StaticTableObject> staticFieldList = new ArrayList<StaticTableObject>();
		
		String query = "SELECT Field_Name, Field_Category, Field_Key, Deleted FROM HCM_STATIC_TABLE "
						+ " WHERE Field_Category = '" + category.trim() + "' ORDER BY Field_Name ";
		
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try{
			conn = dbCtrl.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()){
				StaticTableObject sto = new StaticTableObject();
				sto.setFieldName(rs.getString("Field_Name"));
				sto.setFieldCategory(rs.getString("Field_Category"));
				sto.setDeletedB(rs.getBoolean("Deleted"));
				sto.setFieldKey(rs.getString("Field_Key"));
				staticFieldList.add(sto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return staticFieldList;
	}
	
	public StaticTableObject getValue(String category){
		StaticTableObject sto = new StaticTableObject();
		
		String query = "SELECT Field_Name, Field_Category, Field_Key, Deleted FROM HCM_STATIC_TABLE WHERE Field_Category = ?";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, category);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				sto.setFieldName(rs.getString("Field_Name"));
				sto.setFieldCategory(rs.getString("Field_Category"));
				sto.setDeletedB(rs.getBoolean("Deleted"));
				sto.setFieldKey(rs.getString("Field_Key"));
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return sto;
	}
	
	
	
	
	
	
	
	public List<StaticTableObject> getValuesForCategoryHistory(){
		List<StaticTableObject> staticFieldList = new ArrayList<StaticTableObject>();
		
		String query = "SELECT Field_Name, Field_Category, Field_Key, Deleted FROM HCM_STATIC_TABLE ";
		
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try{
			conn = dbCtrl.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()){
				StaticTableObject sto = new StaticTableObject();
				sto.setFieldName(rs.getString("Field_Name"));
				sto.setFieldCategory(rs.getString("Field_Category"));
				sto.setDeletedB(rs.getBoolean("Deleted"));
				sto.setFieldKey(rs.getString("Field_Key"));
				staticFieldList.add(sto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return staticFieldList;
	}
	
	
	/**
	 * 
	 * @param fieldName
	 * @param fieldCategory
	 * @param fieldKey
	 * @return
	 */
	public boolean updateFieldValue(String fieldName, String fieldCategory, String fieldKey){
		boolean success = true;
		
		String query = "UPDATE HCM_STATIC_TABLE SET Field_Name = ? WHERE Field_Category = ? AND Field_Key = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, fieldName);
			ps.setString(2, fieldCategory);
			ps.setString(3, fieldKey);
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}
	
	/**
	 * 
	 * @param fieldName
	 * @param fieldCategory
	 * @return
	 */
	public boolean deleteFieldForCategory(String fieldCategory, String fieldKey){
		boolean success = true;
		
		String query = "UPDATE HCM_STATIC_TABLE SET Deleted = TRUE WHERE Field_Category = ? AND Field_Key = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, fieldCategory);
			ps.setString(2, fieldKey);
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}
	
	/**
	 * 
	 * @param fieldName
	 * @param fieldCategory
	 * @param fieldKey
	 * @return
	 */
	public boolean addFieldForCategory(String fieldName, String fieldCategory, String fieldKey){
		boolean success = true;
		
		String query = "INSERT INTO HCM_STATIC_TABLE (Field_Name, Field_Key, Field_Category, Deleted) VALUES (?,?,?,FALSE)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, fieldName);
			ps.setString(2, fieldKey);
			ps.setString(3, fieldCategory);
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}

	
	
}