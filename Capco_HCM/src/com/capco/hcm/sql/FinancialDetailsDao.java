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
import com.capco.hcm.model.FinancialDetailsObj;
import com.capco.hcm.model.InductionTrackerObj;


/**
 * @author Mohit Gangil
 *
 */

public class FinancialDetailsDao implements Serializable {
	private static final long serialVersionUID = 8151167905907395844L;

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
	
	
	public void updateEmpFinancialdetails(List<FinancialDetailsObj> finance)
	{
		for(FinancialDetailsObj obj:finance)
		{
			if(null != obj.getEmployeeId() ||  !"0".equals(obj.getEmployeeId()))
			{
				
				System.out.println("Object main kya hain bai "+ obj);
				
				FinancialDetailsObj fin =  getFinancialDetailsTrackerStatus(obj.getEmployeeId());
				
				
				if(fin !=null && fin.getEmployeeId()!=null && Integer.parseInt(fin.getEmployeeId())>0)
				{
					System.out.println("Comes under update"+obj);
					updateFinance(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertFinancialDetailsTrackerStatus(obj);
				}
			}
		}
	}
	
	
	public FinancialDetailsObj getFinancialDetailsTrackerStatus(String employeeId){
		FinancialDetailsObj finDetailsObj = new FinancialDetailsObj();
		String query = "SELECT Emp_Id, Pf_Doc_Received_Date, Sent_to_Pf_Date, Uan_Number, Bank_Name, Account_Holder_Name, Account_Number, "
						+ " Ifsc_Code, Bank_Branch_Name, Insertion_Date, Inserted_By FROM FINANCIAL_TRACKER AS FT1 WHERE FT1.Emp_Id = ? AND "
						+ " FT1.Insertion_Date = (SELECT MAX(FT2.Insertion_Date) FROM FINANCIAL_TRACKER AS FT2 WHERE FT2.Emp_Id = ?) ";

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
				finDetailsObj.setEmployeeId("" + rs.getInt("Emp_Id"));
				finDetailsObj.setPfDocsReceivedDate(new java.util.Date(rs.getDate("Pf_Doc_Received_Date").getTime()));
				finDetailsObj.setSentToPfOfficeDate(new java.util.Date(rs.getDate("Sent_to_Pf_Date").getTime()));
				finDetailsObj.setUanNumber(rs.getString("Uan_Number"));
				finDetailsObj.setBankName(rs.getString("Bank_Name"));
				finDetailsObj.setNameOfAccountHolder(rs.getString("Account_Holder_Name"));
				finDetailsObj.setBankAccountNumber(rs.getString("Account_Number"));
				finDetailsObj.setBankIifscCode(rs.getString("Ifsc_Code"));
				finDetailsObj.setBankBranchName(rs.getString("Bank_Branch_Name"));
				finDetailsObj.setInsertedBy(rs.getString("Inserted_By"));
				finDetailsObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return finDetailsObj;
	}

	/**
	 * 
	 * @param extFormTrkrObj
	 * @param employeeId
	 * @return
	 */
	public boolean insertFinancialDetailsTrackerStatus(FinancialDetailsObj finDetObj){ //, String employeeId, String insertedBy){
		boolean success = true;

		String empid =	finDetObj.getEmployeeId();
		System.out.println("the empid is from session "+empid);
		
		String userid = finDetObj.getInsertedBy();
		System.out.println("the user id is "+userid);
		
		String query = "INSERT INTO FINANCIAL_TRACKER (Emp_Id, Pf_Doc_Received_Date, Sent_to_Pf_Date, Uan_Number, Bank_Name, Account_Holder_Name, "
						+ " Account_Number, Ifsc_Code, Bank_Branch_Name,Deleted, Inserted_By) VALUES "
						+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, false,?)";

		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(finDetObj.getEmployeeId()));
			 
			if(finDetObj.getPfDocsReceivedDate() != null)
			{
				ps.setDate(2, new Date(finDetObj.getPfDocsReceivedDate().getTime()));
				
			}
			else
			{
				ps.setDate(2, null);
				
			}
			
			
			if(finDetObj.getSentToPfOfficeDate() != null)
			{
				ps.setDate(3, new Date(finDetObj.getSentToPfOfficeDate().getTime()));
				
			}
			else
			{
				ps.setDate(3, null);
				
			}
			ps.setString(4, finDetObj.getUanNumber());
			ps.setString(5, finDetObj.getBankName());
			ps.setString(6, finDetObj.getNameOfAccountHolder());
			ps.setString(7, finDetObj.getBankAccountNumber());
			ps.setString(8, finDetObj.getBankIifscCode());
			ps.setString(9, finDetObj.getBankBranchName());
			ps.setString(10, finDetObj.getInsertedBy());
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
	public List<FinancialDetailsObj> getFinDetailsRevHist(){
		List<FinancialDetailsObj> finDetTrkrVerHist = new ArrayList<FinancialDetailsObj>();

		String query = "SELECT Emp_Id, Pf_Doc_Received_Date, Sent_to_Pf_Date, Uan_Number, Bank_Name, Account_Holder_Name, Account_Number, "
						+ " Ifsc_Code, Bank_Branch_Name, Insertion_Date, Inserted_By "
						+ " FROM FINANCIAL_TRACKER ";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while(rs.next()){
				FinancialDetailsObj finDetailsObj = new FinancialDetailsObj();
				finDetailsObj.setEmployeeId("" + rs.getInt("Emp_Id"));
				
				
				if(rs.getDate("Pf_Doc_Received_Date") != null)
				{
					finDetailsObj.setPfDocsReceivedDate(new java.util.Date(rs.getDate("Pf_Doc_Received_Date").getTime()));
					
				}
				else
				{
					finDetailsObj.setPfDocsReceivedDate(null);
					
				}
				
				if(rs.getDate("Sent_to_Pf_Date") != null)
				{
					finDetailsObj.setSentToPfOfficeDate(new java.util.Date(rs.getDate("Sent_to_Pf_Date").getTime()));
					
				}
				else
				{
					finDetailsObj.setSentToPfOfficeDate(null);
					
				}
				finDetailsObj.setUanNumber(rs.getString("Uan_Number"));
				finDetailsObj.setBankName(rs.getString("Bank_Name"));
				finDetailsObj.setNameOfAccountHolder(rs.getString("Account_Holder_Name"));
				finDetailsObj.setBankAccountNumber(rs.getString("Account_Number"));
				finDetailsObj.setBankIifscCode(rs.getString("Ifsc_Code"));
				finDetailsObj.setBankBranchName(rs.getString("Bank_Branch_Name"));
				finDetailsObj.setInsertedBy(rs.getString("Inserted_By"));
				finDetailsObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
				
				finDetTrkrVerHist.add(finDetailsObj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return finDetTrkrVerHist;
	}

	
	public List<FinancialDetailsObj> getFinanceDetails(String empid) {
		
		
		List<FinancialDetailsObj> finance = new ArrayList<FinancialDetailsObj>();
		
		String query = "select Emp_Id,Pf_Doc_Received_Date,Sent_to_Pf_Date,Uan_Number,Bank_Name,"
				+ " Account_Holder_Name, Ifsc_Code, Bank_Branch_Name,Account_Number,Deleted from FINANCIAL_TRACKER where Emp_Id=? ";
		
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
				FinancialDetailsObj fin = new FinancialDetailsObj();
				
				if(rs.getDate("Pf_Doc_Received_Date") != null)
				{
					fin.setPfDocsReceivedDate(new java.util.Date(rs.getDate("Pf_Doc_Received_Date").getTime()));
					
				}
				else
				{
					fin.setPfDocsReceivedDate(null);
					
				}
				
				if(rs.getDate("Sent_to_Pf_Date") != null)
				{
					fin.setSentToPfOfficeDate(new java.util.Date(rs.getDate("Sent_to_Pf_Date").getTime()));
					
				}
				else
				{
					fin.setSentToPfOfficeDate(null);
					
				}
				
				fin.setEmployeeId(rs.getString("Emp_Id"));
				fin.setUanNumber(rs.getString("Uan_Number"));
				fin.setBankName(rs.getString("Bank_Name"));
				fin.setNameOfAccountHolder(rs.getString("Account_Holder_Name"));
				fin.setBankIifscCode(rs.getString("Ifsc_Code"));
				fin.setBankBranchName(rs.getString("Bank_Branch_Name"));
				fin.setDeletedB(rs.getBoolean("Deleted"));
				fin.setBankAccountNumber(rs.getString("Account_Number"));
				
				finance.add(fin);
				
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  finance;
		
	
	}

	public boolean updateFinance(FinancialDetailsObj finance) {
		
		System.out.println(finance);
		boolean success = true;
		
		String query = "UPDATE FINANCIAL_TRACKER SET Pf_Doc_Received_Date=?,Sent_to_Pf_Date=?,Uan_Number=?,Bank_Name=?,"
				+ " Account_Holder_Name=?, Ifsc_Code=?, Bank_Branch_Name=?, Account_Number=?"
				+ " where Emp_Id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			if(finance.getPfDocsReceivedDate() != null)
			{
				ps.setDate(1, new Date(finance.getPfDocsReceivedDate().getTime()));
				
			}
			else
			{
				ps.setDate(1, null);
				
			}
			
			if(finance.getSentToPfOfficeDate() != null)
			{
				ps.setDate(2, new Date(finance.getSentToPfOfficeDate().getTime()));
				
			}
			else
			{
				ps.setDate(2, null);
				
			}
			
			ps.setString(3, finance.getUanNumber());
			ps.setString(4, finance.getBankName());
			ps.setString(5, finance.getNameOfAccountHolder());
			ps.setString(6, finance.getBankIifscCode());
			ps.setString(7, finance.getBankBranchName());
			ps.setString(8, finance.getBankAccountNumber());
			ps.setInt(9, Integer.parseInt(finance.getEmployeeId()));
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}

	public boolean deleteFinanceHistory(FinancialDetailsObj finance) {
		// TODO Auto-generated method stub
		
		boolean success = true;
		
		String query = "UPDATE FINANCIAL_TRACKER SET Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(finance.getEmployeeId()));
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