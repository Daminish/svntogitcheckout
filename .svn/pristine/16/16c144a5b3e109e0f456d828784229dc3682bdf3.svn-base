package com.capco.hcm.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.capco.hcm.controller.DatabaseController;
import com.capco.hcm.model.DocAndBgvObj;
import com.capco.hcm.model.InductionTrackerObj;

/**
 * @author Mohit Gangil
 *
 */

public class InductionTrackerDao implements Serializable {
	private static final long serialVersionUID = 342942165198785084L;

	private DatabaseController dbCtrl;
	
	public DatabaseController getDbCtrl() {
		return dbCtrl;
	}
	public void setDbCtrl(DatabaseController dbCtrl) {
		this.dbCtrl = dbCtrl;
	}

	public void updateEmpInductiondetails(List<InductionTrackerObj> induction)
	{
		for(InductionTrackerObj obj:induction)
		{
			if(null != obj.getEmployeeId() ||  !"0".equals(obj.getEmployeeId()))
			{
				
				System.out.println("Object main kya hain bai "+ obj);
				
				InductionTrackerObj indu =  getInductionStatus(obj.getEmployeeId());
				
				System.out.println("induction ke object"+indu);
				
				if(indu !=null && indu.getEmployeeId()!=null && Integer.parseInt(indu.getEmployeeId())>0)
				{
					System.out.println("Comes under update"+obj);
					updateInduction(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertInductionStatus(obj);
				}
			}
		}
	}
	
	
	
	public InductionTrackerObj getInductionStatus(String employeeId){
		InductionTrackerObj indTrkrObj = new InductionTrackerObj();
		String query = "SELECT Emp_Id,First_Email_Date, Second_Email_Date, Third_Email_Date, Fourth_Email_Date, Fifth_Email_Date, Attended_On, "
						+ " Insertion_Date, Inserted_By "
						+ " FROM INDUCTION_TRACKER  WHERE Emp_Id = ? AND "
						+ " Insertion_Date = (SELECT MAX(Insertion_Date) FROM INDUCTION_TRACKER WHERE Emp_Id = ?)";
		
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
				indTrkrObj.setEmployeeId("" + rs.getInt("Emp_Id"));
				indTrkrObj.setFirstEmailSentDate(new java.util.Date(rs.getDate("First_Email_Date").getTime()));
				indTrkrObj.setSecondEmailSentDate(new java.util.Date(rs.getDate("Second_Email_Date").getTime()));
				indTrkrObj.setThirdEmailSentDate(new java.util.Date(rs.getDate("Third_Email_Date").getTime()));
				indTrkrObj.setFourthEmailSentDate(new java.util.Date(rs.getDate("Fourth_Email_Date").getTime()));
				indTrkrObj.setFifthEmailSentDate(new java.util.Date(rs.getDate("Fifth_Email_Date").getTime()));
				indTrkrObj.setAttendedOnDate(new java.util.Date(rs.getDate("Attended_On").getTime()));
				indTrkrObj.setInsertedBy(rs.getString("Inserted_By"));
				indTrkrObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return indTrkrObj;
	}
	
	/**
	 * 
	 * @param indTrkrObj
	 * @return
	 */
	public boolean insertInductionStatus(InductionTrackerObj indTrkrObj){ //, String employeeId, String insertedBy){
		boolean success = true;
		String query = "INSERT INTO INDUCTION_TRACKER (Emp_Id, First_Email_Date, Second_Email_Date, Third_Email_Date, Fourth_Email_Date, "
				+ " Fifth_Email_Date, Attended_On,Deleted,  Inserted_By) VALUES (?,?,?,?,?,?,?,false,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(indTrkrObj.getEmployeeId()));
			
			if(indTrkrObj.getFirstEmailSentDate() != null)
			{
				ps.setDate(2, new Date(indTrkrObj.getFirstEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(2, null);
				
			}
			
			if(indTrkrObj.getSecondEmailSentDate() != null)
			{
				ps.setDate(3, new Date(indTrkrObj.getSecondEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(3, null);
				
			}
			
			
			if(indTrkrObj.getThirdEmailSentDate() != null)
			{
				ps.setDate(4, new Date(indTrkrObj.getThirdEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(4, null);
				
			}
			
			if(indTrkrObj.getFourthEmailSentDate() != null)
			{
				ps.setDate(5, new Date(indTrkrObj.getFourthEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(5, null);
				
			}
			
			if(indTrkrObj.getFifthEmailSentDate() != null)
			{
				ps.setDate(6, new Date(indTrkrObj.getFifthEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(6, null);
				
			}
			
			if(indTrkrObj.getAttendedOnDate() != null)
			{
				
				ps.setDate(7, new Date(indTrkrObj.getAttendedOnDate().getTime()));
			
			}
			else
			{
				
				ps.setDate(7, null);
			
			}
			ps.setString(8, indTrkrObj.getInsertedBy());
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
	 * @param employeeId
	 * @return
	 */
	public List<InductionTrackerObj> getInductionTrkrRevHist(){
		List<InductionTrackerObj> indTrkrRevHist = new ArrayList<InductionTrackerObj>();
		
		String query = "SELECT Emp_Id, First_Email_Date, Second_Email_Date, Third_Email_Date, Fourth_Email_Date, Fifth_Email_Date, Attended_On, "
						+ " Insertion_Date, Inserted_By FROM INDUCTION_TRACKER ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				InductionTrackerObj indTrkrObj = new InductionTrackerObj();
				
				indTrkrObj.setEmployeeId("" + rs.getInt("Emp_Id"));
				
				if(rs.getDate("First_Email_Date") != null)
				{
					
					indTrkrObj.setFirstEmailSentDate(rs.getDate("First_Email_Date"));
				}
				else
				{
					
					indTrkrObj.setFirstEmailSentDate(null);
				
				}
				
				if(rs.getDate("Second_Email_Date") != null)
				{
					indTrkrObj.setSecondEmailSentDate(rs.getDate("Second_Email_Date"));
				}
				else
				{
					indTrkrObj.setSecondEmailSentDate(null);
				}
			
				if(rs.getDate("Third_Email_Date") != null)
				{
					indTrkrObj.setThirdEmailSentDate(rs.getDate("Third_Email_Date"));
					
				}
				else
				{
					indTrkrObj.setThirdEmailSentDate(null);
					
				}
				
				if(rs.getDate("Fourth_Email_Date") != null)
				{
					indTrkrObj.setFourthEmailSentDate(rs.getDate("Fourth_Email_Date"));
					
				}
				else
				{
					indTrkrObj.setFourthEmailSentDate(null);
					
				}
				
				if(rs.getDate("Fifth_Email_Date") != null)
				{
					indTrkrObj.setFifthEmailSentDate(rs.getDate("Fifth_Email_Date"));
					
				}
				else
				{
					indTrkrObj.setFifthEmailSentDate(null);
					
				}
				

				if(rs.getDate("Attended_On") != null)
				{
					indTrkrObj.setAttendedOnDate(rs.getDate("Attended_On"));
					
				}
				else
				{
					indTrkrObj.setAttendedOnDate(null);
					
				}
				
				indTrkrObj.setInsertedBy(rs.getString("Inserted_By"));
				indTrkrObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
				
				indTrkrRevHist.add(indTrkrObj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return indTrkrRevHist;
	}
	
	

	public List<InductionTrackerObj> getInductionDetails(String empid) {
		
		List<InductionTrackerObj> finance = new ArrayList<InductionTrackerObj>();
		
		String query = "select Emp_Id,First_Email_Date, Second_Email_Date, Third_Email_Date, Fourth_Email_Date, "
				+ "Fifth_Email_Date,Deleted, Attended_On from INDUCTION_TRACKER where Emp_Id=?  ";
		
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
				InductionTrackerObj induction = new InductionTrackerObj();
				induction.setEmployeeId(rs.getString("Emp_Id"));
				
				if(rs.getDate("First_Email_Date") != null)
				{
					
					induction.setFirstEmailSentDate(rs.getDate("First_Email_Date"));
				}
				else
				{
					
					induction.setFirstEmailSentDate(null);
				
				}
				
				if(rs.getDate("Second_Email_Date") != null)
				{
					induction.setSecondEmailSentDate(rs.getDate("Second_Email_Date"));
				}
				else
				{
					induction.setSecondEmailSentDate(null);
				}
			
				if(rs.getDate("Third_Email_Date") != null)
				{
					induction.setThirdEmailSentDate(rs.getDate("Third_Email_Date"));
					
				}
				else
				{
					induction.setThirdEmailSentDate(null);
					
				}
				
				if(rs.getDate("Fourth_Email_Date") != null)
				{
					induction.setFourthEmailSentDate(rs.getDate("Fourth_Email_Date"));
					
				}
				else
				{
					induction.setFourthEmailSentDate(null);
					
				}
				
				if(rs.getDate("Fifth_Email_Date") != null)
				{
					induction.setFifthEmailSentDate(rs.getDate("Fifth_Email_Date"));
					
				}
				else
				{
					induction.setFifthEmailSentDate(null);
					
				}
				
				induction.setDeletedB(rs.getBoolean("Deleted"));
				
				if(rs.getDate("Attended_On") != null)
				{
					induction.setAttendedOnDate(rs.getDate("Attended_On"));
					
				}
				else
				{
					induction.setAttendedOnDate(null);
					
				}
				
				finance.add(induction);
				
				
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  finance;
	
	}

	public boolean updateInduction(InductionTrackerObj induction) {
		
		System.out.println(induction);
		boolean success = true;
		
		String query = "UPDATE INDUCTION_TRACKER SET First_Email_Date=?, Second_Email_Date=?, Third_Email_Date=?,"
				+ " Fourth_Email_Date=?, Fifth_Email_Date=?, Attended_On=?  where Emp_Id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			if(induction.getFirstEmailSentDate() != null)
			{
				ps.setDate(1, new Date(induction.getFirstEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(1, null);
				
			}
			
			if(induction.getSecondEmailSentDate() != null)
			{
				ps.setDate(2, new Date(induction.getSecondEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(2, null);
				
			}
			
			
			if(induction.getThirdEmailSentDate() != null)
			{
				ps.setDate(3, new Date(induction.getThirdEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(3, null);
				
			}
			
			if(induction.getFourthEmailSentDate() != null)
			{
				ps.setDate(4, new Date(induction.getFourthEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(4, null);
				
			}
			
			if(induction.getFifthEmailSentDate() != null)
			{
				ps.setDate(5, new Date(induction.getFifthEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(5, null);
				
			}
			
			if(induction.getAttendedOnDate() != null)
			{
				
				ps.setDate(6, new Date(induction.getAttendedOnDate().getTime()));
			
			}
			else
			{
				
				ps.setDate(6, null);
			
			}
			
			ps.setInt(7, Integer.parseInt(induction.getEmployeeId()));

			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
		
	}

	public boolean deleteInductionHistory(InductionTrackerObj induction) {
		// TODO Auto-generated method stub
		boolean success = true;
		
		String query = "UPDATE INDUCTION_TRACKER SET Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1,  Integer.parseInt(induction.getEmployeeId()));
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
