package com.capco.hcm.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.capco.hcm.controller.DatabaseController;
import com.capco.hcm.model.ProbationTrackerObj;
import com.capco.hcm.model.ProjectHistoryObj;

/**
 * @author Mohit Gangil
 *
 */

public class ProjectHistoryDao implements Serializable {

	private static final long serialVersionUID = -8674863457828714137L;

	private DatabaseController dbCtrl;

	public DatabaseController getDbCtrl() {
		return dbCtrl;
	}

	public void setDbCtrl(DatabaseController dbCtrl) {
		this.dbCtrl = dbCtrl;
	}
	
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	
	
	public void updateEmpProjectHistorydetails(List<ProjectHistoryObj> empobj)
	{
		for(ProjectHistoryObj obj:empobj)
		{
			if(null != obj.getEmployeeId() ||  !"0".equals(obj.getEmployeeId()))
			{
				
				
				System.out.println("kya hain id yeh bata"+obj.getEmployeeId());
				System.out.println("Object main kya hain bai "+ obj);
				
				ProjectHistoryObj pro = getProjectDetails(obj.getEmployeeId());
				
				
				if(pro !=null && pro.getEmployeeId()!=null && Integer.parseInt(pro.getEmployeeId())>0)
				{
					System.out.println("Comes under update"+obj);
					updateProject(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertProjectHistoryTrackerStatus(obj);
				}
			}
		}
	}
	
	
	
	
	public ProjectHistoryObj getProjectDetails(String employeeId){
		ProjectHistoryObj projDetailsObj = new ProjectHistoryObj();
		String query = "SELECT Emp_Id, From_Date, To_Date, Project_List, Reporting_To, "
						+ " Managed_By, Insertion_Date, Inserted_By FROM PROJECT_HISTORY WHERE Emp_Id = ? AND "
						+ " Insertion_Date = (SELECT MAX(Insertion_Date) FROM PROJECT_HISTORY WHERE Emp_Id = ?) ";

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
				projDetailsObj.setEmployeeId("" + rs.getInt("Emp_Id"));
				projDetailsObj.setFromDate(new java.util.Date(rs.getDate("From_Date").getTime()));	
				projDetailsObj.setToDate(new java.util.Date(rs.getDate("To_Date").getTime()));
				projDetailsObj.setManagedBy(rs.getString("Managed_By"));
				projDetailsObj.setProjectList(rs.getString("Project_List"));
				projDetailsObj.setReportingTo(rs.getString("Reporting_To"));
				projDetailsObj.setInsertedBy(rs.getString("Inserted_By"));
				projDetailsObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
				
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return projDetailsObj;
	}

	
	/**
	 * 
	 * @param projectHistoryTrkrObj
	 * @param employeeId
	 * @return
	 */
	public boolean insertProjectHistoryTrackerStatus(ProjectHistoryObj projectHistoryTrkrObj){ //, String employeeId, String insertedBy){
		
		boolean success = true;

		String query = "INSERT INTO PROJECT_HISTORY (Emp_Id, From_Date, To_Date, Project_List, Reporting_To,"
				+ " Managed_By, Deleted, Inserted_By) VALUES "
				+ " (?, ?, ?, ?, ?,?, false, ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		try{
			
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(projectHistoryTrkrObj.getEmployeeId()));
			
			if(projectHistoryTrkrObj.getFromDate() != null)
			{
				
				ps.setDate(2,  new Date(projectHistoryTrkrObj.getFromDate().getTime()));
				
			}
			else
			{
				
				ps.setDate(2,  null);
				
			
			}
			
			if( projectHistoryTrkrObj.getToDate() != null)
			{
				ps.setDate(3, new Date(projectHistoryTrkrObj.getToDate().getTime()));
				
			}
			else
			{
				ps.setDate(3, null);
				
			}
			ps.setString(4, projectHistoryTrkrObj.getProjectList());
			ps.setString(5, projectHistoryTrkrObj.getReportingTo());
			ps.setString(6, projectHistoryTrkrObj.getManagedBy());
			ps.setString(7, projectHistoryTrkrObj.getInsertedBy());
			ps.execute();
		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
			
		
		return success;
	}
	
	
	

	public List<ProjectHistoryObj> getProject(String empid) {

		List<ProjectHistoryObj>	project = new ArrayList<ProjectHistoryObj>();
		
		String query = "select Emp_Id,From_Date,To_Date, Project_List, Reporting_To, Managed_By, Deleted from PROJECT_HISTORY where Emp_Id=?  ";
		System.out.println("comes inside get project");
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
	
				ProjectHistoryObj pro = new ProjectHistoryObj();
				pro.setEmployeeId(""+rs.getInt("Emp_Id"));
				
			if(rs.getDate("From_Date") != null)
			{
				pro.setFromDate(rs.getDate("From_Date"));
			}
			else
			{
				pro.setFromDate(null);
			}
			
			if(rs.getDate("To_Date") != null)
			{
				
				pro.setToDate(rs.getDate("To_Date"));
			}
			else
			{
				
				pro.setToDate(null);
			
			}
				pro.setProjectList(rs.getString("Project_List"));
				pro.setReportingTo(rs.getString("Reporting_To"));
				pro.setManagedBy(rs.getString("Managed_By"));
				//pro.setInsertedDate(rs.getDate("Insertion_Date"));
				
				pro.setDeletedB(rs.getBoolean("Deleted"));
				project.add(pro);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  project;
		
	}

	public List<ProjectHistoryObj> getProjectHistory() {

		List<ProjectHistoryObj>	project = new ArrayList<ProjectHistoryObj>();
		
		String query = "select Emp_Id,From_Date,To_Date, Project_List, Reporting_To, Managed_By, Deleted,"
				+ "Insertion_Date, Inserted_By from PROJECT_HISTORY  ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
	
				ProjectHistoryObj pro = new ProjectHistoryObj();
		
				pro.setEmployeeId(""+rs.getInt("Emp_Id"));
				
			if(rs.getDate("From_Date") != null)
			{
				pro.setFromDate(rs.getDate("From_Date"));
			}
			else
			{
				pro.setFromDate(null);
			}
			
			if(rs.getDate("To_Date") != null)
			{
				
				pro.setToDate(rs.getDate("To_Date"));
			}
			else
			{
				
				pro.setToDate(null);
			
			}
				pro.setProjectList(rs.getString("Project_List"));
				pro.setReportingTo(rs.getString("Reporting_To"));
				pro.setManagedBy(rs.getString("Managed_By"));
				
				pro.setDeletedB(rs.getBoolean("Deleted"));
			
				pro.setInsertedDate(rs.getDate("Insertion_Date"));
				pro.setInsertedBy(rs.getString("Inserted_By"));
				project.add(pro);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  project;
		
	}

	

	public boolean updateProject(ProjectHistoryObj project) {
		
		System.out.println(project);
		boolean success = true;
		
		String query = "UPDATE PROJECT_HISTORY SET From_Date=?, To_Date=?, Reporting_To=? ,Managed_By=?,Project_List=? "
				+ "where Emp_Id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
		
			if(project.getFromDate() != null)
			{
				
				ps.setDate(1,  new Date(project.getFromDate().getTime()));
			
			}
			else
			{
				
				ps.setDate(1,  null);
			
			}
			
			
			if(project.getToDate() != null)
			{
				ps.setDate(2, new Date(project.getToDate().getTime()));
				
			}
			else
			{
				ps.setDate(2, null);
				
			}
			ps.setString(3, project.getReportingTo());
			ps.setString(4, project.getManagedBy());
			ps.setString(5, project.getProjectList());
			ps.setInt(6, Integer.parseInt(project.getEmployeeId()));
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}
		// TODO Auto-generated method stub


	
	public boolean deleteFieldForProjectHistory(ProjectHistoryObj project) {
		boolean success = true;
		
		String query = "UPDATE PROJECT_HISTORY SET Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1,Integer.parseInt(project.getEmployeeId()));
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return success;
		
		}


	public List<String> getAllProject() {
		
		List<String> categories = new ArrayList<String>();
		String query = "SELECT DISTINCT Field_Name FROM HCM_STATIC_TABLE WHERE Field_Category = 'PROJECT'";
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


	
		
	}
		
	