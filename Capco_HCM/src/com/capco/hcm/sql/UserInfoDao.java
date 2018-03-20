package com.capco.hcm.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.capco.hcm.controller.DatabaseController;
import com.capco.hcm.model.ProbationTrackerObj;
import com.capco.hcm.model.UserInfoObj;

/**
 * @author Mohit Gangil
 *
 */


public class UserInfoDao implements Serializable {

	private static final long serialVersionUID = -1883199104862494983L;
	private DatabaseController dbCtrl;
	
	//Getters and Setters
	public DatabaseController getDbCtrl() {
		return dbCtrl;
	}
	public void setDbCtrl(DatabaseController dbCtrl) {
		this.dbCtrl = dbCtrl;
	}

	
	public List<UserInfoObj> getUserList(String capcoId){
		List<UserInfoObj> userList = new ArrayList<UserInfoObj>();
		String query = "SELECT Emp_Id, Capco_ID, Fis_Id, User_Name, Admin_Rights, Hcm_Rights, Normal_User "
				+ " User_Deleted,  Insertion_Date, Inserted_By "
				+ " FROM USER_INFO WHERE Capco_ID = ?";

			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
			
				ps.setInt(1, Integer.parseInt(capcoId));
				rs = ps.executeQuery();
			
				while(rs.next()){
					UserInfoObj userInfoObj = new UserInfoObj();
					userInfoObj.setEmployeeId(rs.getString("Emp_Id"));
					userInfoObj.setCapcoId(rs.getString("Capco_ID"));
					userInfoObj.setFisId(rs.getString("Fis_Id"));
					userInfoObj.setUserName(rs.getString("User_Name"));
					userInfoObj.setAdminRights(rs.getBoolean("Admin_Rights"));
					userInfoObj.setHcmRights(rs.getBoolean("Hcm_Rights"));
					userInfoObj.setNormalUser(rs.getBoolean("Normal_User"));
					userInfoObj.setUserDeleted(rs.getBoolean("User_Deleted"));
					userInfoObj.setInsertedBy(rs.getString("Inserted_By"));
					userInfoObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
					
					userList.add(userInfoObj);
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}
					return userList;
				}
	
	
	
	public List<UserInfoObj> getUserHist(){
		List<UserInfoObj> userList = new ArrayList<UserInfoObj>();
		String query = "SELECT Emp_Id, Capco_ID, Fis_Id, User_Name, Admin_Rights, Hcm_Rights,  "
				+ " User_Deleted,  Insertion_Date, Inserted_By "
				+ " FROM USER_INFO";

			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
			
				
				rs = ps.executeQuery();
			
				while(rs.next()){
					UserInfoObj userInfoObj = new UserInfoObj();
					userInfoObj.setEmployeeId(rs.getString("Emp_Id"));
					userInfoObj.setCapcoId(rs.getString("Capco_ID"));
					userInfoObj.setFisId(rs.getString("Fis_Id"));
					userInfoObj.setUserName(rs.getString("User_Name"));
					userInfoObj.setAdminRights(rs.getBoolean("Admin_Rights"));
					userInfoObj.setHcmRights(rs.getBoolean("Hcm_Rights"));
					userInfoObj.setUserDeleted(rs.getBoolean("User_Deleted"));
					userInfoObj.setInsertedBy(rs.getString("Inserted_By"));
					userInfoObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
					
					userList.add(userInfoObj);
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}
					return userList;
				}
	
	
	
	public void updateEmpUserdetails(List<UserInfoObj> empobj)
	{
		for(UserInfoObj obj:empobj)
		{
			if(null != obj.getEmployeeId() ||  !"0".equals(obj.getEmployeeId()))
			{
				
				
				System.out.println("kya hain id yeh bata"+obj.getEmployeeId());
				System.out.println("Object main kya hain bai "+ obj);
				
				UserInfoObj user = getUserInfo(obj.getEmployeeId());
				
				
				if(user !=null && user.getEmployeeId()!=null && Integer.parseInt(user.getEmployeeId())>0)
				{
					System.out.println("Comes under update"+obj);
					updateUser(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertUserInfoObj(obj);
				}
			}
		}
	}
	
	
	
	public UserInfoObj getUserInfo(String employeeId){
		UserInfoObj userInfoObj = new UserInfoObj();
		String query = "SELECT Emp_Id, Capco_ID, Fis_Id, User_Name, Admin_Rights, Hcm_Rights, Normal_User, "
				+ " User_Deleted, Inserted_By, Insertion_Date FROM USER_INFO WHERE Emp_Id = ? AND "
				+ " Insertion_Date = (SELECT MAX(Insertion_Date) FROM USER_INFO WHERE Emp_Id = ?)";
	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(employeeId));
			ps.setInt(2, Integer.parseInt(employeeId));
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				userInfoObj.setEmployeeId("" +rs.getInt("Emp_Id"));
				userInfoObj.setCapcoId(rs.getString("Capco_ID"));
				userInfoObj.setFisId(rs.getString("Fis_Id"));
				userInfoObj.setUserName(rs.getString("User_Name"));
				userInfoObj.setAdminRights(rs.getBoolean("Admin_Rights"));
				userInfoObj.setHcmRights(rs.getBoolean("Hcm_Rights"));
				userInfoObj.setNormalUser(rs.getBoolean("Normal_User"));
				userInfoObj.setUserDeleted(rs.getBoolean("User_Deleted"));
				userInfoObj.setInsertedBy(rs.getString("Inserted_By"));
				userInfoObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
					
				}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		
		return userInfoObj;
	}
	
	public boolean insertUserInfoObj(UserInfoObj userInfoObj){
		boolean success = true;
		
		String query = "INSERT INTO USER_INFO (Emp_Id, Capco_ID, Fis_Id, User_Name, Admin_Rights, Hcm_Rights, Normal_User, "
				+ " User_Deleted, Inserted_By) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			Connection conn = null;
			PreparedStatement ps = null;
			try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
				
				ps.setInt(1, Integer.parseInt(userInfoObj.getEmployeeId()));
				ps.setString(2, userInfoObj.getCapcoId());
				ps.setString(3, userInfoObj.getFisId());
				ps.setString(4, userInfoObj.getUserName());
				ps.setBoolean(5, userInfoObj.getAdminRights());
				ps.setBoolean(6, userInfoObj.getHcmRights());
				ps.setBoolean(7, userInfoObj.getNormalUser());
				ps.setBoolean(8, userInfoObj.getUserDeleted());
				ps.setString(9, userInfoObj.getInsertedBy());
				
				
				ps.execute();
			}catch(SQLException e){
				e.printStackTrace();
				success = false;
			}finally{
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}
		
		return success;
	}
	

	public boolean updateUser(UserInfoObj user) {
		
		System.out.println(user);
		boolean success = true;
		
		String query = "UPDATE USER_INFO SET Admin_Rights=?, Hcm_Rights=?,Capco_ID=?,User_Name=?,Fis_Id=? where Emp_Id=?";
			
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, user.getAdminRights());
			ps.setBoolean(2, user.getHcmRights());
			ps.setString(3, user.getCapcoId());
			ps.setString(4, user.getUserName());
			ps.setString(5, user.getFisId());
			ps.setInt(6, Integer.parseInt(user.getEmployeeId()));
			
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}

	public List<UserInfoObj> getUserDetails(String empid) {
	
		List<UserInfoObj> userlist = new ArrayList<UserInfoObj>();
		String query = "select Emp_Id, Admin_Rights, Hcm_Rights,User_Name, Capco_ID, Fis_Id, User_Deleted "
				+ "from USER_INFO where Emp_Id=? ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(empid));
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				UserInfoObj user= new UserInfoObj();
				user.setEmployeeId(rs.getString("Emp_Id"));
				user.setAdminRights(rs.getBoolean("Admin_Rights"));
				user.setHcmRights(rs.getBoolean("Hcm_Rights"));
				user.setAdminRights(rs.getBoolean("Admin_Rights"));
				user.setCapcoId(rs.getString("Capco_ID"));
				user.setFisId(rs.getString("Fis_Id"));
				user.setUserName(rs.getString("User_Name"));
				user.setUserDeleted(rs.getBoolean("User_Deleted"));
				
			
				userlist.add(user);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			try
			{
				conn.close();
				ps.close();
			}catch (Exception e) {
				e.printStackTrace();;
			}
			
		}
		return userlist;
		
	}

	public boolean deleteUsertHistory(UserInfoObj user) {
		
		boolean success = true;
		
		String query = "UPDATE USER_INFO SET User_Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(user.getEmployeeId()));
			
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return success;
		
		}
	}
		