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
import com.capco.hcm.model.EmployeeBasicInfoObj;
import com.capco.hcm.model.ExitFormalityTrackerObj;

/**
 * @author Mohit Gangil
 *
 */

public class ExitFormalityTrackerDao implements Serializable {
	private static final long serialVersionUID = 5393976779578069371L;

	private DatabaseController dbCtrl;

	//Getters & Setters
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
	
	
	public void updateEmpExitFormalitydetails(List<ExitFormalityTrackerObj> empobj)
	{
		for(ExitFormalityTrackerObj obj:empobj)
		{
			if(null != obj.getEmployeeId() ||  !"0".equals(obj.getEmployeeId()))
			{
				
				
				System.out.println("kya hain id yeh bata"+obj.getEmployeeId());
				System.out.println("Object main kya hain bai "+ obj);
				
				ExitFormalityTrackerObj exittracker = getExitFormailityTrackerStatus(obj.getEmployeeId());
				
				
				if(exittracker !=null && exittracker.getEmployeeId()!=null && Integer.parseInt(exittracker.getEmployeeId())>0)
				{
					System.out.println("Comes under update"+obj);
					updateExitFormality(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertExitFormalityTrackerStatus(obj);
				}
			}
		}
	}
	
	
	public ExitFormalityTrackerObj getExitFormailityTrackerStatus(String employeeId){
		ExitFormalityTrackerObj exitFormailityTrkrObj = new ExitFormalityTrackerObj();

		System.out.println("comes in select exit");
		
		String query = "SELECT Emp_Id, Last_Working_Date, Resignation_Date, Resignation_Reason, Notice_Period, Comments, Login_Id_Terminated, Login_Id_Term_Date, "
				+ "	Medical_Insurance_Informed, Library_Book_Collected, Exit_Interview_Completed, Birthday_App_Deletion, Salary_Hold, Fnf_Completed, "
				+ " Ts_Submitted, Releiving_Letter_Issued, Releiving_Letter_Issue_Date, Service_Letter_Issued, Service_Letter_Issue_Date, Buyout_Letter_Issued, "
				+ " Buyout_Letter_Issue_Date, Buyout_Amount_Paid, Buyout_Amount, Buyout_Number_of_Days, Buyout_Waived, Buyout_Waived_Days, Inserted_By, "
				+ " Insertion_Date FROM EXIT_TRACKER where Emp_Id = ? AND "
				+ " Insertion_Date = (SELECT MAX(Insertion_Date) FROM EXIT_TRACKER WHERE Emp_Id = ?) ";

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
				exitFormailityTrkrObj.setEmployeeId("" + rs.getInt("Emp_Id"));
				exitFormailityTrkrObj.setLastWorkingDate(new java.util.Date(rs.getDate("Last_Working_Date").getTime()));
				exitFormailityTrkrObj.setResignationDate(new java.util.Date(rs.getDate("Resignation_Date").getTime()));
				exitFormailityTrkrObj.setReasonForResignation(rs.getString("Resignation_Reason"));
				exitFormailityTrkrObj.setNoticePeriod(rs.getString("Notice_Period"));
				exitFormailityTrkrObj.setComments(rs.getString("Comments"));
				exitFormailityTrkrObj.setIdAndEmailTerminated(rs.getBoolean("Login_Id_Terminated"));
				if (!exitFormailityTrkrObj.getIdAndEmailTerminated()) {
					exitFormailityTrkrObj.setIdAndEmailTerminatedDate(null);
				}
				else
				{
					exitFormailityTrkrObj.setIdAndEmailTerminatedDate(new java.util.Date(rs.getDate("Login_Id_Term_Date").getTime()));
						
				}
				exitFormailityTrkrObj.setMedicalInsuranceNotified(rs.getBoolean("Medical_Insurance_Informed"));
				exitFormailityTrkrObj.setLibraryBooksCollected(rs.getString("Library_Book_Collected"));
				exitFormailityTrkrObj.setExitInterviewCompleted(rs.getBoolean("Exit_Interview_Completed"));
				exitFormailityTrkrObj.setBirthdayAppDeletion(rs.getBoolean("Birthday_App_Deletion"));
				exitFormailityTrkrObj.setSalaryHold(rs.getBoolean("Salary_Hold"));
				exitFormailityTrkrObj.setFnfCompleted(new java.util.Date(rs.getDate("Fnf_Completed").getTime()));
				exitFormailityTrkrObj.setTimesheetSubmitted(rs.getBoolean("Ts_Submitted"));
				exitFormailityTrkrObj.setReleivingLetterIssued(rs.getBoolean("Releiving_Letter_Issued"));
				if (!exitFormailityTrkrObj.getReleivingLetterIssued()) {
					exitFormailityTrkrObj.setReleivingLetterDate(null);
				}else{
					exitFormailityTrkrObj.setReleivingLetterDate(new java.util.Date(rs.getDate("Releiving_Letter_Issue_Date").getTime()));
					
				}
				exitFormailityTrkrObj.setServiceLetterIssued(rs.getBoolean("Service_Letter_Issued"));
				if (!exitFormailityTrkrObj.getServiceLetterIssued()) {
					exitFormailityTrkrObj.setServiceLetterDate(null);
				}else{
					exitFormailityTrkrObj.setServiceLetterDate(new java.util.Date(rs.getDate("Service_Letter_Issue_Date").getTime()));
				}
				
				exitFormailityTrkrObj.setBuyoutLetterIssued(rs.getBoolean("Buyout_Letter_Issued"));
				if (!exitFormailityTrkrObj.getBuyoutLetterIssued()) {
						exitFormailityTrkrObj.setBuyoutLetterDate(null);
				}else
				{
					exitFormailityTrkrObj.setBuyoutLetterDate(new java.util.Date(rs.getDate("Buyout_Letter_Issue_Date").getTime()));
						
				}
				exitFormailityTrkrObj.setBuyoutAmountPaid(rs.getBoolean("Buyout_Amount_Paid"));
				if (!exitFormailityTrkrObj.getBuyoutAmountPaid()) {
					exitFormailityTrkrObj.setBuyoutAmount(null);
					exitFormailityTrkrObj.setBuyoutNoOfDays(""+ 0);
				}
				else
				{
					
					exitFormailityTrkrObj.setBuyoutAmount(rs.getString("Buyout_Amount"));
					exitFormailityTrkrObj.setBuyoutNoOfDays("" + rs.getInt("Buyout_Number_of_Days"));
			
				}
				exitFormailityTrkrObj.setBuyoutWaived(rs.getBoolean("Buyout_Waived"));
				exitFormailityTrkrObj.setBuyoutWaivedDays("" + rs.getInt("Buyout_Waived_Days"));
				exitFormailityTrkrObj.setInsertedBy(rs.getString("Inserted_By"));
				exitFormailityTrkrObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return exitFormailityTrkrObj;
	}

	/**
	 * 
	 * @param extFormTrkrObj
	 * @param employeeId
	 * @return
	 */
	public boolean insertExitFormalityTrackerStatus(ExitFormalityTrackerObj extFormTrkrObj){ //, String employeeId, String insertedBy){
		boolean success = true;

		String query = "INSERT INTO EXIT_TRACKER (Emp_Id, Last_Working_Date, Resignation_Date, Resignation_Reason, Notice_Period, Comments, Login_Id_Terminated, "
				+ " Login_Id_Term_Date,	Medical_Insurance_Informed, Library_Book_Collected, Exit_Interview_Completed, Birthday_App_Deletion, Salary_Hold, Fnf_Completed, "
				+ " Ts_Submitted, Releiving_Letter_Issued, Releiving_Letter_Issue_Date, Service_Letter_Issued, Service_Letter_Issue_Date, Buyout_Letter_Issued, "
				+ " Buyout_Letter_Issue_Date, Buyout_Amount_Paid, Buyout_Amount, Buyout_Number_of_Days, Buyout_Waived, Buyout_Waived_Days,Deleted, Inserted_By) VALUES "
				+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false,?)";

		/*System.out.println("Inside exit formality"+"\nThe boolean:"+extFormTrkrObj.getIdAndEmailTerminated()+
							"\nDate :"+extFormTrkrObj.getIdAndEmailTerminatedDate().getTime());
*/		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(extFormTrkrObj.getEmployeeId()));
			
			if(extFormTrkrObj.getLastWorkingDate() != null)
			{
				ps.setDate(2, new Date(extFormTrkrObj.getLastWorkingDate().getTime()));
				
			}
			else
			{
				ps.setDate(2, null);
				
			}
			
			if(extFormTrkrObj.getResignationDate() != null)
			{
				ps.setDate(3, new Date(extFormTrkrObj.getResignationDate().getTime()));
				
			}
			else
			{
				ps.setDate(3, null);
				
			}
			
			ps.setString(4, extFormTrkrObj.getReasonForResignation());
			ps.setString(5, extFormTrkrObj.getNoticePeriod());
			ps.setString(6, extFormTrkrObj.getComments());
			ps.setBoolean(7, extFormTrkrObj.getIdAndEmailTerminated());
		
			
			if (extFormTrkrObj.getIdAndEmailTerminatedDate() != null)
			{
				ps.setDate(8, new Date(extFormTrkrObj.getIdAndEmailTerminatedDate().getTime()));
				
			}
			else
			{
				ps.setDate(8, null);
			}
			
			ps.setBoolean(9, extFormTrkrObj.getMedicalInsuranceNotified());
			ps.setString(10, extFormTrkrObj.getLibraryBooksCollected());
			ps.setBoolean(11, extFormTrkrObj.getExitInterviewCompleted());
			ps.setBoolean(12, extFormTrkrObj.getBirthdayAppDeletion());
			ps.setBoolean(13, extFormTrkrObj.getSalaryHold());
			
			if(extFormTrkrObj.getFnfCompleted() != null)
			{
				ps.setDate(14, new Date(extFormTrkrObj.getFnfCompleted().getTime()));
				
			}
			else
			{
				ps.setDate(14, null);
				
			}
			
			ps.setBoolean(15, extFormTrkrObj.getTimesheetSubmitted());
			ps.setBoolean(16, extFormTrkrObj.getReleivingLetterIssued());
		
			
			if (extFormTrkrObj.getReleivingLetterDate() != null)
			{
				ps.setDate(17, new Date(extFormTrkrObj.getReleivingLetterDate().getTime()));
			}
			else
			{
				ps.setDate(17, null);

			}
			ps.setBoolean(18, extFormTrkrObj.getServiceLetterIssued());
			
			
			if (extFormTrkrObj.getServiceLetterDate() != null)
			{
				ps.setDate(19, new Date(extFormTrkrObj.getServiceLetterDate().getTime()));
			}
			else
			{
				ps.setDate(19, null);
			}	
			
			
			ps.setBoolean(20, extFormTrkrObj.getBuyoutLetterIssued());
			
			
			if (extFormTrkrObj.getBuyoutLetterDate() != null) 
			{
				ps.setDate(21, new Date(extFormTrkrObj.getBuyoutLetterDate().getTime()));
			}
			else
			{
				ps.setDate(21, null);
			}
			
			ps.setBoolean(22, extFormTrkrObj.getBuyoutAmountPaid());
			ps.setString(23, extFormTrkrObj.getBuyoutAmount());
			
			if (!extFormTrkrObj.getBuyoutAmountPaid())
			{
				ps.setInt(24, 0);
			}
			else
			{
				ps.setInt(24, Integer.parseInt(extFormTrkrObj.getBuyoutNoOfDays()));
			}
			ps.setBoolean(25, extFormTrkrObj.getBuyoutWaived());
			ps.setString(26, extFormTrkrObj.getBuyoutWaivedDays());
			ps.setString(27, extFormTrkrObj.getInsertedBy());
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
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
	public List<ExitFormalityTrackerObj> getExitFormailityRevHist(){
		List<ExitFormalityTrackerObj> exitFormailityTrkrVerHist = new ArrayList<ExitFormalityTrackerObj>();

		String query = "SELECT Emp_Id, Last_Working_Date, Resignation_Date, Resignation_Reason, Notice_Period, Comments, Login_Id_Terminated, Login_Id_Term_Date, "
						+ "	Medical_Insurance_Informed, Library_Book_Collected, Exit_Interview_Completed, Birthday_App_Deletion, Salary_Hold, Fnf_Completed, "
						+ " Ts_Submitted, Releiving_Letter_Issued, Releiving_Letter_Issue_Date, Service_Letter_Issued, Service_Letter_Issue_Date, Buyout_Letter_Issued, "
						+ " Buyout_Amount_Paid, Buyout_Amount, Buyout_Number_of_Days, Buyout_Waived, Buyout_Waived_Days, Buyout_Letter_Issue_Date, Deleted, "
						+ " Inserted_By, Insertion_Date FROM EXIT_TRACKER";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while(rs.next()){
				ExitFormalityTrackerObj exitFormailityTrkrObj = new ExitFormalityTrackerObj();

				exitFormailityTrkrObj.setEmployeeId(rs.getString("Emp_Id"));
				
				if(rs.getDate("Last_Working_Date") !=null)
				{
					exitFormailityTrkrObj.setLastWorkingDate(rs.getDate("Last_Working_Date"));
				}
				else
				{
					exitFormailityTrkrObj.setLastWorkingDate(null);
				}
				
				if(rs.getDate("Resignation_Date") != null)
				{
					exitFormailityTrkrObj.setResignationDate(rs.getDate("Resignation_Date"));
				}
				else
				{
					exitFormailityTrkrObj.setResignationDate(null);
				}
				
				
				exitFormailityTrkrObj.setReasonForResignation(rs.getString("Resignation_Reason"));
				exitFormailityTrkrObj.setNoticePeriod(rs.getString("Notice_Period"));
				exitFormailityTrkrObj.setComments(rs.getString("Comments"));
				exitFormailityTrkrObj.setIdAndEmailTerminated(rs.getBoolean("Login_Id_Terminated"));
				if(rs.getDate("Login_Id_Term_Date") != null)
				{
					exitFormailityTrkrObj.setIdAndEmailTerminatedDate(rs.getDate("Login_Id_Term_Date"));
					
				}
				else
				{
					exitFormailityTrkrObj.setIdAndEmailTerminatedDate(null);
					
				}
				exitFormailityTrkrObj.setMedicalInsuranceNotified(rs.getBoolean("Medical_Insurance_Informed"));
				exitFormailityTrkrObj.setLibraryBooksCollected(rs.getString("Library_Book_Collected"));
				exitFormailityTrkrObj.setExitInterviewCompleted(rs.getBoolean("Exit_Interview_Completed"));
				exitFormailityTrkrObj.setBirthdayAppDeletion(rs.getBoolean("Birthday_App_Deletion"));
				exitFormailityTrkrObj.setSalaryHold(rs.getBoolean("Salary_Hold"));
			
				if(rs.getDate("Fnf_Completed") != null)
				{
					exitFormailityTrkrObj.setFnfCompleted(rs.getDate("Fnf_Completed"));
				}
				else
				{
					exitFormailityTrkrObj.setFnfCompleted(null);
				}
				
				exitFormailityTrkrObj.setTimesheetSubmitted(rs.getBoolean("Ts_Submitted"));
				exitFormailityTrkrObj.setReleivingLetterIssued(rs.getBoolean("Releiving_Letter_Issued"));
			
				
				if(rs.getDate("Releiving_Letter_Issue_Date") != null)
				{
					exitFormailityTrkrObj.setReleivingLetterDate(rs.getDate("Releiving_Letter_Issue_Date"));
					
				}
				else
				{
					exitFormailityTrkrObj.setReleivingLetterDate(null);
					
				}
				
				
				exitFormailityTrkrObj.setServiceLetterIssued(rs.getBoolean("Service_Letter_Issued"));
				
				if(rs.getDate("Service_Letter_Issue_Date") != null)
				{
					exitFormailityTrkrObj.setServiceLetterDate(rs.getDate("Service_Letter_Issue_Date"));
					
				}
				else
				{
					exitFormailityTrkrObj.setServiceLetterDate(null);
					
				}
				
				exitFormailityTrkrObj.setBuyoutLetterIssued(rs.getBoolean("Buyout_Letter_Issued"));
				
				if(rs.getDate("Buyout_Letter_Issue_Date") != null)
				{
					exitFormailityTrkrObj.setBuyoutLetterDate(rs.getDate("Buyout_Letter_Issue_Date"));
				}
				else
				{
					exitFormailityTrkrObj.setBuyoutLetterDate(null);
				}
			
				exitFormailityTrkrObj.setBuyoutAmountPaid(rs.getBoolean("Buyout_Amount_Paid"));
				exitFormailityTrkrObj.setBuyoutAmount(rs.getString("Buyout_Amount"));
				
				if (!exitFormailityTrkrObj.getBuyoutAmountPaid()) {
					exitFormailityTrkrObj.setBuyoutNoOfDays(""+ 0);
				}
				else
				{
					
					exitFormailityTrkrObj.setBuyoutNoOfDays(rs.getString("Buyout_Number_of_Days"));
			
				}
				exitFormailityTrkrObj.setBuyoutWaived(rs.getBoolean("Buyout_Waived"));
				exitFormailityTrkrObj.setBuyoutWaivedDays("" + rs.getInt("Buyout_Waived_Days"));
				exitFormailityTrkrObj.setDeletedB(rs.getBoolean("Deleted"));
				exitFormailityTrkrObj.setInsertedBy(rs.getString("Inserted_By"));
				exitFormailityTrkrObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
			
				exitFormailityTrkrVerHist.add(exitFormailityTrkrObj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return exitFormailityTrkrVerHist;
	}
	
	
	
	public void clear(ExitFormalityTrackerObj exit){
		
		 Date date3 = null;
		 
		exit.setBirthdayAppDeletion(false);
		exit.setBuyoutAmount(null);
		exit.setBuyoutAmountPaid(false);
		exit.setBuyoutLetterDate(date3);
		exit.setBuyoutLetterIssued(false);
		exit.setBuyoutNoOfDays(null);
		exit.setBuyoutWaived(false);
		exit.setComments(null);
		exit.setEmployeeId(null);
		exit.setExitInterviewCompleted(false);
		exit.setFnfCompleted(date3);
		exit.setIdAndEmailTerminated(false);
		exit.setIdAndEmailTerminatedDate(date3);
		exit.setInsertedBy(null);
		exit.setInsertedDate(date3);
		exit.setLastWorkingDate(date3);
		exit.setLibraryBooksCollected(null);
		exit.setMedicalInsuranceNotified(false);
		exit.setNoticePeriod(null);
		exit.setReasonForResignation(null);
		exit.setReleivingLetterDate(date3);
		exit.setReleivingLetterIssued(false);
		exit.setReleivingLetterDate(date3);
		exit.setResignationDate(date3);
		exit.setSalaryHold(false);
		exit.setServiceLetterDate(date3);
		exit.setServiceLetterIssued(false);
		exit.setTimesheetSubmitted(false);
		
		
	}//end clear`


	public List<ExitFormalityTrackerObj> getExitDetails(String empid) {
		
		List<ExitFormalityTrackerObj>	exformality = new ArrayList<ExitFormalityTrackerObj>();
		
		String query = "select Emp_Id,Resignation_Date,Notice_Period, Last_Working_Date, Resignation_Reason,"
				+ " Comments, Fnf_Completed,Login_Id_Terminated,Login_Id_Term_Date, "
				+ " Library_Book_Collected,Exit_Interview_Completed,Medical_Insurance_Informed,"
				+ " Releiving_Letter_Issued, Releiving_Letter_Issue_Date, Service_Letter_Issued, Service_Letter_Issue_Date,"
				+ "Buyout_Letter_Issued,Buyout_Letter_Issue_Date, Buyout_Amount_Paid, Buyout_Amount, Buyout_Number_of_Days,"
				+ "Buyout_Waived, Buyout_Waived_Days, "
				+ "Ts_Submitted,Birthday_App_Deletion,Salary_Hold,Deleted  from EXIT_TRACKER where Emp_Id=? ";
		
	
		
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
	
				ExitFormalityTrackerObj exit = new ExitFormalityTrackerObj();
				exit.setEmployeeId(rs.getString("Emp_Id"));
				
				if(rs.getDate("Last_Working_Date") !=null)
				{
					exit.setLastWorkingDate(rs.getDate("Last_Working_Date"));
				}
				else
				{
					exit.setLastWorkingDate(null);
				}
				
				if(rs.getDate("Resignation_Date") != null)
				{
					exit.setResignationDate(rs.getDate("Resignation_Date"));
				}
				else
				{
					exit.setResignationDate(null);
				}
				
				exit.setNoticePeriod(rs.getString("Notice_Period"));
				exit.setReasonForResignation(rs.getString("Resignation_Reason"));
				exit.setComments(rs.getString("Comments"));
				
				if(rs.getDate("Fnf_Completed") != null)
				{
					exit.setFnfCompleted(rs.getDate("Fnf_Completed"));
				}
				else
				{
					exit.setFnfCompleted(null);
				}
				
				exit.setIdAndEmailTerminated(rs.getBoolean("Login_Id_Terminated"));
				
				if(rs.getDate("Login_Id_Term_Date") != null)
				{
					exit.setIdAndEmailTerminatedDate(rs.getDate("Login_Id_Term_Date"));
					
				}
				else
				{
					exit.setIdAndEmailTerminatedDate(null);
					
				}
				exit.setLibraryBooksCollected(rs.getString("Library_Book_Collected"));
				exit.setExitInterviewCompleted(rs.getBoolean("Exit_Interview_Completed"));
				exit.setMedicalInsuranceNotified(rs.getBoolean("Medical_Insurance_Informed"));
				exit.setReleivingLetterIssued(rs.getBoolean("Releiving_Letter_Issued"));
				
				if(rs.getDate("Releiving_Letter_Issue_Date") != null)
				{
					exit.setReleivingLetterDate(rs.getDate("Releiving_Letter_Issue_Date"));
					
				}
				else
				{
					exit.setReleivingLetterDate(null);
					
				}
				
				exit.setServiceLetterIssued(rs.getBoolean("Service_Letter_Issued"));
			
				if(rs.getDate("Service_Letter_Issue_Date") != null)
				{
					exit.setServiceLetterDate(rs.getDate("Service_Letter_Issue_Date"));
					
				}
				else
				{
					exit.setServiceLetterDate(null);
					
				}
				
				exit.setBuyoutLetterIssued(rs.getBoolean("Buyout_Letter_Issued"));
				
				if(rs.getDate("Buyout_Letter_Issue_Date") != null)
				{
					exit.setBuyoutLetterDate(rs.getDate("Buyout_Letter_Issue_Date"));
				}
				else
				{
					exit.setBuyoutLetterDate(null);
				}
				exit.setBuyoutAmountPaid(rs.getBoolean("Buyout_Amount_Paid"));
				exit.setBuyoutAmount(rs.getString("Buyout_Amount"));
				exit.setBuyoutWaived(rs.getBoolean("Buyout_Waived"));
			
				if (!exit.getBuyoutAmountPaid()) {
					exit.setBuyoutNoOfDays(""+ 0);
				}
				else
				{
					
					exit.setBuyoutNoOfDays(rs.getString("Buyout_Number_of_Days"));
			
				}
				exit.setBuyoutWaivedDays(rs.getString("Buyout_Waived_Days"));
				exit.setTimesheetSubmitted(rs.getBoolean("Ts_Submitted"));
				exit.setBirthdayAppDeletion(rs.getBoolean("Birthday_App_Deletion"));
				exit.setSalaryHold(rs.getBoolean("Salary_Hold"));
				exit.setDeletedB(rs.getBoolean("Deleted"));
				exformality.add(exit);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  exformality;
		
	}

	public boolean updateExitFormality(ExitFormalityTrackerObj exitform) {
		
		boolean success = true;
		System.out.println("aa gya ree");
	
		String query = "UPDATE EXIT_TRACKER set Resignation_Date=?,Notice_Period=?, Last_Working_Date=?, Resignation_Reason=?,"
				+ " Comments=?, Fnf_Completed=?,Login_Id_Terminated=?,Login_Id_Term_Date=?, "
				+ " Library_Book_Collected=?,Exit_Interview_Completed=?,Medical_Insurance_Informed=?,"
				+ " Releiving_Letter_Issued=?, Releiving_Letter_Issue_Date=?, Service_Letter_Issued=?,"
				+ " Service_Letter_Issue_Date=?,Buyout_Letter_Issued=?,Buyout_Letter_Issue_Date=?, "
				+ "Buyout_Amount_Paid=?, Buyout_Amount=?, Buyout_Number_of_Days=?,"
				+ "Buyout_Waived=?, Buyout_Waived_Days=?, "
				+ "Ts_Submitted=?,Birthday_App_Deletion=?,Salary_Hold=? where Emp_Id=?";

		
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			if(exitform.getResignationDate() != null)
			{
				ps.setDate(1, new Date(exitform.getResignationDate().getTime()));
				
			}
			else
			{
				ps.setDate(1, null);
				
			}
			ps.setString(2, exitform.getNoticePeriod());
		
			if(exitform.getLastWorkingDate() != null)
			{
				ps.setDate(3, new Date(exitform.getLastWorkingDate().getTime()));
				
			}
			else
			{
				ps.setDate(3, null);
				
			}
			ps.setString(4, exitform.getReasonForResignation());
			ps.setString(5, exitform.getComments());
			
			if(exitform.getFnfCompleted() != null)
			{
				ps.setDate(6, new Date(exitform.getFnfCompleted().getTime()));
				
			}
			else
			{
				ps.setDate(6, null);
				
			}
			
			ps.setBoolean(7, exitform.getIdAndEmailTerminated());
		
			if (exitform.getIdAndEmailTerminatedDate() != null)
			{
				ps.setDate(8, new Date(exitform.getIdAndEmailTerminatedDate().getTime()));
				
			}
			else
			{
				ps.setDate(8, null);
			}
			ps.setString(9, exitform.getLibraryBooksCollected());
			ps.setBoolean(10, exitform.getExitInterviewCompleted());
			ps.setBoolean(11, exitform.getMedicalInsuranceNotified());
			ps.setBoolean(12, exitform.getServiceLetterIssued());
		
			
			if (exitform.getServiceLetterDate() != null)
			{
				ps.setDate(13, new Date(exitform.getServiceLetterDate().getTime()));
			}
			else
			{
				ps.setDate(13, null);
			}	
			
			ps.setBoolean(14, exitform.getReleivingLetterIssued());
	
			if (exitform.getReleivingLetterDate() != null)
			{
				ps.setDate(15, new Date(exitform.getReleivingLetterDate().getTime()));
			}
			else
			{
				ps.setDate(15, null);

			}
			ps.setBoolean(16, exitform.getBuyoutLetterIssued());
		
			if (exitform.getBuyoutLetterDate() != null) 
			{
				ps.setDate(17, new Date(exitform.getBuyoutLetterDate().getTime()));
			}
			else
			{
				ps.setDate(17, null);
			}
			ps.setBoolean(18, exitform.getBuyoutAmountPaid());
			ps.setString(19, exitform.getBuyoutAmount());
			ps.setInt(20, Integer.parseInt(exitform.getBuyoutNoOfDays()));
			ps.setBoolean(21, exitform.getBuyoutWaived());
			ps.setString(22, exitform.getBuyoutWaivedDays());
			ps.setBoolean(23, exitform.getTimesheetSubmitted());
			ps.setBoolean(24, exitform.getBirthdayAppDeletion());
			ps.setBoolean(25, exitform.getSalaryHold());
			ps.setInt(26, Integer.parseInt(exitform.getEmployeeId()));
			
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}

	public boolean deleteFieldForExit(ExitFormalityTrackerObj exit) {
		// TODO Auto-generated method stub
		
		boolean success = true;
		
		String query = "UPDATE EXIT_TRACKER SET Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(exit.getEmployeeId()));
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