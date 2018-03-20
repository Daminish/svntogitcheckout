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
import com.capco.hcm.model.ExitFormalityTrackerObj;
import com.capco.hcm.model.ProbationTrackerObj;

/**
 * @author Mohit Gangil
 *
 */

public class ProbationTrackerDao implements Serializable {
	private static final long serialVersionUID = -108900208043423610L;

	private DatabaseController dbCtrl;
	
	//Getters and Setters
		public DatabaseController getDbCtrl() {
			return dbCtrl;
		}
		public void setDbCtrl(DatabaseController dbCtrl) {
			this.dbCtrl = dbCtrl;
		}

		public void updateEmpProbationdetails(List<ProbationTrackerObj> empobj)
		{
			for(ProbationTrackerObj obj:empobj)
			{
				if(null != obj.getEmployeeId() ||  !"0".equals(obj.getEmployeeId()))
				{
					
					
					System.out.println("kya hain id yeh bata"+obj.getEmployeeId());
					System.out.println("Object main kya hain bai "+ obj);
					
					ProbationTrackerObj pro = getProbationTrackerStatus(obj.getEmployeeId());
					
					
					if(pro !=null && pro.getEmployeeId()!=null && Integer.parseInt(pro.getEmployeeId())>0)
					{
						System.out.println("Comes under update"+obj);
						updateProbation(obj);
					}
					else
					{
						System.out.println("Comes under insert"+obj);
						
						insertProbationTrackerStatus(obj);
					}
				}
			}
		}
		
		
	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	public ProbationTrackerObj getProbationTrackerStatus(String employeeId){
		ProbationTrackerObj probTrkrObj = new ProbationTrackerObj();

		String query = "SELECT Emp_Id, Probation_Confirm_Date, First_Chaser_Date, Second_Chaser_Date, Third_Chaser_Date, Escalated_Hcbp_Date, "
						+ " Confirmed_Date, Feedback_Form_Received, Letter_Issue_Date, Status, Extended_Date, Extension_Reason, Inserted_By, Insertion_Date "
						+ " FROM PROBATION_TRACKER WHERE Emp_Id = ? "
						+ " AND Insertion_Date = (SELECT MAX(Insertion_Date) FROM PROBATION_TRACKER  WHERE Emp_Id = ?)";

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
				System.out.println("comes under while");
				
				probTrkrObj.setEmployeeId("" + rs.getInt("Emp_Id"));
				probTrkrObj.setProbationConfirmationDate(new java.util.Date(rs.getDate("Probation_Confirm_Date").getTime()));
				probTrkrObj.setFirstChaserDate(new java.util.Date(rs.getDate("First_Chaser_Date").getTime()));
				probTrkrObj.setSecondChaserDate(new java.util.Date(rs.getDate("Second_Chaser_Date").getTime()));
				probTrkrObj.setThirdChaserDate(new java.util.Date(rs.getDate("Third_Chaser_Date").getTime()));
				probTrkrObj.setEscalatedToHcbpOnDate(new java.util.Date(rs.getDate("Escalated_Hcbp_Date").getTime()));
				probTrkrObj.setConfirmationDate(new java.util.Date(rs.getDate("Confirmed_Date").getTime()));
				probTrkrObj.setFeedbackFormReceived(rs.getBoolean("Feedback_Form_Received"));
				probTrkrObj.setLetterSentDate(new java.util.Date(rs.getDate("Letter_Issue_Date").getTime()));
				probTrkrObj.setConfirmationStatus(rs.getBoolean("Status"));
				probTrkrObj.setExtendedToDate(new java.util.Date(rs.getDate("Extended_Date").getTime()));
				probTrkrObj.setExtensionReason(rs.getString("Extension_Reason"));
				probTrkrObj.setInsertedBy(rs.getString("Inserted_By"));
				probTrkrObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return probTrkrObj;
	}

	/**
	 * 
	 * @param extFormTrkrObj
	 * @param employeeId
	 * @return
	 */
	public boolean insertProbationTrackerStatus(ProbationTrackerObj probTrkrObj){//, String employeeId, String insertedBy){
		boolean success = true;

		String query = "INSERT INTO PROBATION_TRACKER (Emp_Id, Probation_Confirm_Date, First_Chaser_Date, Second_Chaser_Date, Third_Chaser_Date, "
						+ " Escalated_Hcbp_Date, Confirmed_Date, Feedback_Form_Received, Letter_Issue_Date, Status, Extension_Reason, Extended_Date,Deleted, Inserted_By) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false,?)";

		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(probTrkrObj.getEmployeeId()));
			/*if(!probTrkrObj.getConfirmationStatus())
			{
				ps.setDate(2, null);
			}*/
			
			if(probTrkrObj.getProbationConfirmationDate() != null)
			{
				ps.setDate(2, new Date(probTrkrObj.getProbationConfirmationDate().getTime()));
			}
			
			else
			{
				ps.setDate(2, null);
			}
			
			if(probTrkrObj.getFirstChaserDate() != null)
			{
				ps.setDate(3, new Date(probTrkrObj.getFirstChaserDate().getTime()));
				
			}
			else
			{
				ps.setDate(3, null);
				
			}
			
			if(probTrkrObj.getSecondChaserDate() != null)
			{
				ps.setDate(4, new Date(probTrkrObj.getSecondChaserDate().getTime()));
				
			}
			else
			{
				ps.setDate(4, null);
				
			}
			
			if(probTrkrObj.getThirdChaserDate() != null)
			{
				ps.setDate(5, new Date(probTrkrObj.getThirdChaserDate().getTime()));
				
			}
			else
			{
				ps.setDate(5, null);
				
			}
			
			if(probTrkrObj.getEscalatedToHcbpOnDate() != null)
			{
				ps.setDate(6, new Date(probTrkrObj.getEscalatedToHcbpOnDate().getTime()));
				
			}
			else
			{
				ps.setDate(6, null);
				
			}
			
			if(probTrkrObj.getConfirmationDate() != null)
			{
				ps.setDate(7, new Date(probTrkrObj.getConfirmationDate().getTime()));
				
			}
			else
			{
				ps.setDate(7, null);
				
			}
			
			ps.setBoolean(8, probTrkrObj.getFeedbackFormReceived());
		
			if(probTrkrObj.getLetterSentDate() != null)
			{
				ps.setDate(9, new Date(probTrkrObj.getLetterSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(9, null);
				
			}
			ps.setBoolean(10, probTrkrObj.getConfirmationStatus());
			ps.setString(11, probTrkrObj.getExtensionReason());
			
			if(probTrkrObj.getExtendedToDate() != null)
			{
				ps.setDate(12, new Date(probTrkrObj.getExtendedToDate().getTime()));
				
			}
			else
			{
				ps.setDate(12, null);
				
			}
			ps.setString(13, probTrkrObj.getInsertedBy());
			
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
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	public List<ProbationTrackerObj> getProbationRevHist(){
		List<ProbationTrackerObj> probationTrkrVerHist = new ArrayList<ProbationTrackerObj>();

		String query = "SELECT Emp_Id, Probation_Confirm_Date, First_Chaser_Date, Second_Chaser_Date, Third_Chaser_Date, Escalated_Hcbp_Date, "
						+ " Confirmed_Date, Feedback_Form_Received, Letter_Issue_Date, Status, Extended_Date,"
						+ " Extension_Reason, Deleted, Insertion_Date, Inserted_By "
						+ " FROM PROBATION_TRACKER ";

		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while(rs.next()){
				ProbationTrackerObj probTrkrObj = new ProbationTrackerObj();
				
				probTrkrObj.setEmployeeId(rs.getString("Emp_Id"));
				if(rs.getDate("Probation_Confirm_Date") != null)
				{
					probTrkrObj.setProbationConfirmationDate(new Date(rs.getDate("Probation_Confirm_Date").getTime()));
				}
				else
				{
					probTrkrObj.setProbationConfirmationDate(null);
				}
				
				
				
				if(rs.getDate("First_Chaser_Date") != null)
				{
					probTrkrObj.setFirstChaserDate(new Date(rs.getDate("First_Chaser_Date").getTime()));
					
				}
				else
				{
					probTrkrObj.setFirstChaserDate(null);
					
				}
				
				if(rs.getDate("Second_Chaser_Date") != null)
				{
					probTrkrObj.setSecondChaserDate(new Date(rs.getDate("Second_Chaser_Date").getTime()));
					
				}
				else
				{
					probTrkrObj.setSecondChaserDate(null);
					
				}
				
				if(rs.getDate("Third_Chaser_Date") != null)
				{
					probTrkrObj.setThirdChaserDate(new Date(rs.getDate("Third_Chaser_Date").getTime()));
					
				}
				else
				{
					probTrkrObj.setThirdChaserDate(null);
					
				}
				
				if(rs.getDate("Escalated_Hcbp_Date") != null)
				{
					probTrkrObj.setEscalatedToHcbpOnDate(new Date(rs.getDate("Escalated_Hcbp_Date").getTime()));
					
				}
				else
				{
					probTrkrObj.setEscalatedToHcbpOnDate(null);
					
				}
				
				if(rs.getDate("Confirmed_Date") != null)
				{
					probTrkrObj.setConfirmationDate(new Date(rs.getDate("Confirmed_Date").getTime()));
					
				}
				else
				{
					probTrkrObj.setConfirmationDate(null);
				}
				
				probTrkrObj.setFeedbackFormReceived(rs.getBoolean("Feedback_Form_Received"));
				
				if(rs.getDate("Letter_Issue_Date") != null)
				{
					probTrkrObj.setLetterSentDate(new Date(rs.getDate("Letter_Issue_Date").getTime()));
					
				}
				else
				{
					probTrkrObj.setLetterSentDate(null);
					
				}
				
				probTrkrObj.setConfirmationStatus(rs.getBoolean("Status"));
				
				if(rs.getDate("Extended_Date") != null)
				{
					probTrkrObj.setExtendedToDate(new Date(rs.getDate("Extended_Date").getTime()));
					
				}
				else
				{
					probTrkrObj.setExtendedToDate(null);
					
				}
				
				probTrkrObj.setExtensionReason(rs.getString("Extension_Reason"));
				probTrkrObj.setDeletedB(rs.getBoolean("Deleted"));
				probTrkrObj.setInsertedBy(rs.getString("Inserted_By"));
				probTrkrObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
				
				probationTrkrVerHist.add(probTrkrObj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return probationTrkrVerHist;
	}

	

	public List<ProbationTrackerObj> getProbationDetails(String empid) {

		List<ProbationTrackerObj>	probation = new ArrayList<ProbationTrackerObj>();
		
		String query = "select Emp_Id, Probation_Confirm_Date,First_Chaser_Date, Second_Chaser_Date, Third_Chaser_Date, Escalated_Hcbp_Date, Feedback_Form_Received,"
				+ " Confirmed_Date, Extended_Date, Deleted, Extension_Reason,Letter_Issue_Date  from PROBATION_TRACKER where Emp_Id=? ";
		
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
	
				ProbationTrackerObj pro = new ProbationTrackerObj();
				pro.setEmployeeId(rs.getString("Emp_Id"));
				
				if(rs.getDate("Probation_Confirm_Date") != null)
				{
					pro.setProbationConfirmationDate(new Date(rs.getDate("Probation_Confirm_Date").getTime()));
				}
				else
				{
					pro.setProbationConfirmationDate(null);
				}
				
				
				
				if(rs.getDate("First_Chaser_Date") != null)
				{
					pro.setFirstChaserDate(new Date(rs.getDate("First_Chaser_Date").getTime()));
					
				}
				else
				{
					pro.setFirstChaserDate(null);
					
				}
				
				if(rs.getDate("Second_Chaser_Date") != null)
				{
					pro.setSecondChaserDate(new Date(rs.getDate("Second_Chaser_Date").getTime()));
					
				}
				else
				{
					pro.setSecondChaserDate(null);
					
				}
				
				if(rs.getDate("Third_Chaser_Date") != null)
				{
					pro.setThirdChaserDate(new Date(rs.getDate("Third_Chaser_Date").getTime()));
					
				}
				else
				{
					pro.setThirdChaserDate(null);
					
				}
				
				if(rs.getDate("Escalated_Hcbp_Date") != null)
				{
					pro.setEscalatedToHcbpOnDate(new Date(rs.getDate("Escalated_Hcbp_Date").getTime()));
					
				}
				else
				{
					pro.setEscalatedToHcbpOnDate(null);
					
				}
				
				pro.setFeedbackFormReceived(rs.getBoolean("Feedback_Form_Received"));
				
				if(rs.getDate("Confirmed_Date") != null)
				{
					pro.setConfirmationDate(new Date(rs.getDate("Confirmed_Date").getTime()));
					
				}
				else
				{
					pro.setConfirmationDate(null);
				}
				
				if(rs.getDate("Extended_Date") != null)
				{
					pro.setExtendedToDate(new Date(rs.getDate("Extended_Date").getTime()));
					
				}
				else
				{
					pro.setExtendedToDate(null);
					
				}
				pro.setDeletedB(rs.getBoolean("Deleted"));
				pro.setExtensionReason(rs.getString("Extension_Reason"));
				
				if(rs.getDate("Letter_Issue_Date") != null)
				{
					pro.setLetterSentDate(new Date(rs.getDate("Letter_Issue_Date").getTime()));
					
				}
				else
				{
					pro.setLetterSentDate(null);
					
				}
				probation.add(pro);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  probation;
		
	}

	public boolean updateProbation(ProbationTrackerObj probation) {
		System.out.println(probation);
		boolean success = true;
		
		String query = "UPDATE PROBATION_TRACKER SET Probation_Confirm_Date =?,First_Chaser_Date = ?,Second_Chaser_Date=? ,Third_Chaser_Date=?, "
				+ " Escalated_Hcbp_Date=?, Confirmed_Date=?, Extended_Date=?,Extension_Reason=?, Letter_Issue_Date=? , Feedback_Form_Received=?  where Emp_Id=?";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			
			if(probation.getProbationConfirmationDate() != null)
			{
				ps.setDate(1, new Date(probation.getProbationConfirmationDate().getTime()));
			}
			
			else
			{
				ps.setDate(1, null);
			}
			
			
			
			if(probation.getFirstChaserDate() != null)
			{
				ps.setDate(2, new Date(probation.getFirstChaserDate().getTime()));
				
			}
			else
			{
				ps.setDate(2, null);
				
			}
			
			if(probation.getSecondChaserDate() != null)
			{
				ps.setDate(3, new Date(probation.getSecondChaserDate().getTime()));
				
			}
			else
			{
				ps.setDate(3, null);
				
			}
			
			if(probation.getThirdChaserDate() != null)
			{
				ps.setDate(4, new Date(probation.getThirdChaserDate().getTime()));
				
			}
			else
			{
				ps.setDate(4, null);
				
			}
			
			if(probation.getEscalatedToHcbpOnDate() != null)
			{
				ps.setDate(5, new Date(probation.getEscalatedToHcbpOnDate().getTime()));
				
			}
			else
			{
				ps.setDate(5, null);
				
			}
			
			if(probation.getConfirmationDate() != null)
			{
				ps.setDate(6, new Date(probation.getConfirmationDate().getTime()));
				
			}
			else
			{
				ps.setDate(6, null);
				
			}
		
			if(probation.getExtendedToDate() != null)
			{
				ps.setDate(7, new Date(probation.getExtendedToDate().getTime()));
				
			}
			else
			{
				ps.setDate(7, null);
				
			}
			ps.setString(8, probation.getExtensionReason());
			
			if(probation.getLetterSentDate() != null)
			{
				ps.setDate(9, new Date(probation.getLetterSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(9, null);
				
			}
			ps.setBoolean(10, probation.getFeedbackFormReceived());
			ps.setInt(11, Integer.parseInt(probation.getEmployeeId()));
			
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


		public boolean deleteFieldForProbation(ProbationTrackerObj probation) {
			boolean success = true;
			
			String query = "UPDATE PROBATION_TRACKER SET Deleted = TRUE WHERE Emp_Id = ?";
			
			Connection conn = null;
			PreparedStatement ps = null;
			
			try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(probation.getEmployeeId()));
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
	

