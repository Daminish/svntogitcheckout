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
import com.capco.hcm.model.EndorsementTrackerObj;
import com.capco.hcm.model.JoiningBenefitsInfoObj;
import com.capco.hcm.model.NomineeTrackerObj;


/**
 * @author Mohit Gangil
 *
 */

public class EndorsementTrackerDao implements Serializable {
	private static final long serialVersionUID = 4490433765606961476L;

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
	
	public void updateEmpEndorsementdetails(List<EndorsementTrackerObj> empobj)
	{
		for(EndorsementTrackerObj obj:empobj)
		{
			if(null != obj.getEmpid() ||  !"0".equals(obj.getEmpid()))
			{
				
				System.out.println("Object main kya hain bai "+ obj);
				
				EndorsementTrackerObj endoInfo = getEndorsementTrackerStatus(obj.getEmpid());
				
				
				if(endoInfo !=null && endoInfo.getEmpid()!=null && Integer.parseInt(endoInfo.getEmpid())>0)
				{
					System.out.println("Comes under update"+obj);
					updateEndorsement(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertEndorsementTrackerStatus(obj, obj.getEmpid());
				}
			}
		}
	}
	
	
	public void updateEmpNomineedetails(List<NomineeTrackerObj> empobj)
	{
		for(NomineeTrackerObj obj:empobj)
		{
			if(null != obj.getEmpid() ||  !"0".equals(obj.getEmpid()))
			{
				
				System.out.println("Object main kya hain bai "+ obj);
				
				NomineeTrackerObj nomiInfo = getNomineeInfo(obj.getEmpid());
				
				
				if(nomiInfo !=null && nomiInfo.getEmpid()!=null && Integer.parseInt(nomiInfo.getEmpid())>0)
				{
					System.out.println("Comes under update"+obj);
					updateNominee(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertNomineeTrackerStatus(obj);
				}
			}
		}
	}
	
	
	public NomineeTrackerObj getNomineeInfo(String employeeId) {
		
		NomineeTrackerObj	nominees = new NomineeTrackerObj();
		
		String query = "select Emp_Id,Dependent_Name,Relation, Gender, Nominee_Date_Of_Birth,EmailId, Nominee_Age, Deleted from "
				+ "NOMINATION_DETAILS where Emp_Id=? AND Insertion_Date = (SELECT MAX(Insertion_Date) FROM"
				+ " NOMINATION_DETAILS  WHERE Emp_Id = ?)";

		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(employeeId));
			ps.setInt(2, Integer.parseInt(employeeId));
			rs = ps.executeQuery();
			
			while(rs.next()){
	
				
				nominees.setEmpid(rs.getString("Emp_Id"));
				nominees.setDependentsName(rs.getString("Dependent_Name"));
				nominees.setRelation(rs.getString("Relation"));
				nominees.setGender(rs.getBoolean("Gender"));
				
				if(rs.getDate("Nominee_Date_Of_Birth") != null)
				{
					nominees.setDateOfBirthOfNominee(rs.getDate("Nominee_Date_Of_Birth"));
					
				}
				else
				{
					nominees.setDateOfBirthOfNominee(null);
					
				}
				nominees.setEmailId(rs.getString("EmailId"));
				nominees.setAgeOfNominee(rs.getString("Nominee_Age"));
				nominees.setDeletedB(rs.getBoolean("Deleted"));
				
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  nominees;
		
		
		
	}
	
	
	
	public EndorsementTrackerObj getEndorsementTrackerStatus(String employeeId){
		EndorsementTrackerObj endorseTrkrObj = new EndorsementTrackerObj();
		String query = "SELECT Emp_Id, GPA_Sum_Assured, GTL_Sum_Insured, GMC_Sum_Insured, Annual_Ctc "
				+ "FROM ENDORSEMENT_TRACKER  WHERE Emp_Id = ? AND Insertion_Date = (SELECT MAX(Insertion_Date) FROM"
				+ " ENDORSEMENT_TRACKER  WHERE Emp_Id = ? ) ";

		
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
					endorseTrkrObj.setEmpid("" + rs.getInt("Emp_Id"));
					endorseTrkrObj.setAnnualCtc(rs.getString("Annual_Ctc"));
					endorseTrkrObj.setGpaSumAssured(rs.getString("GPA_Sum_Assured"));
					endorseTrkrObj.setGtlSumInsured(rs.getString("GTL_Sum_Insured"));
					endorseTrkrObj.setGmcSumInsured(rs.getString("GMC_Sum_Insured"));
				}


		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return endorseTrkrObj;
	}

	/**
	 * 
	 * @param extFormTrkrObj
	 * @param employeeId
	 * @return
	 */
	public boolean insertEndorsementTrackerStatus(EndorsementTrackerObj endorsementTrkrObj, String employeeId){
		boolean success = true;
		String query = "INSERT INTO ENDORSEMENT_TRACKER (Emp_Id, Annual_Ctc, GPA_Sum_Assured, GTL_Sum_Insured, "
				+ "GMC_Sum_Insured,Deleted, Inserted_By) "
				+ "VALUES (?, ?, ?, ?, ?, false,?)";

		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);

			ps.setInt(1, Integer.parseInt(employeeId));
			ps.setString(2, endorsementTrkrObj.getAnnualCtc());
			ps.setString(3, endorsementTrkrObj.getGpaSumAssured());
			ps.setString(4, endorsementTrkrObj.getGtlSumInsured());
			ps.setString(5, endorsementTrkrObj.getGmcSumInsured());
			ps.setString(6, endorsementTrkrObj.getInsertedBy());//TODO add the piece of code for inserted by
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

	public boolean insertNomineeTrackerStatus(NomineeTrackerObj nomineetracker) {
		boolean success = true;
		String query = "INSERT INTO NOMINATION_DETAILS (Emp_Id, Deleted, Dependent_Name, Relation, Gender, Nominee_Date_Of_Birth, Nominee_Age, EmailId, Inserted_By) "
				+ " VALUES (?, false, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		try{
			
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(nomineetracker.getEmpid()));
			ps.setString(2, nomineetracker.getDependentsName());
			ps.setString(3, nomineetracker.getRelation());
			ps.setBoolean(4, nomineetracker.getGender());
			
			if(nomineetracker.getDateOfBirthOfNominee() != null)
			{
				ps.setDate(5, new Date(nomineetracker.getDateOfBirthOfNominee().getTime()));
				
			}
			else
			{
				ps.setDate(5, null);
				
			}
			
			ps.setString(6, nomineetracker.getAgeOfNominee());
			ps.setString(7, nomineetracker.getEmailId());
			ps.setString(8, nomineetracker.getInsertedBy());
			
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

	

	public List<NomineeTrackerObj> getNomineeDetails(String employeeId) {
		
		List<NomineeTrackerObj>	nomination = new ArrayList<NomineeTrackerObj>();
		
		String query = "select Emp_Id,Dependent_Name,Relation, Gender, Nominee_Date_Of_Birth,EmailId, Nominee_Age, Deleted from "
				+ "NOMINATION_DETAILS where Emp_Id=? ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(employeeId));
			rs = ps.executeQuery();
			
			while(rs.next()){
	
				NomineeTrackerObj nominees = new NomineeTrackerObj();
				nominees.setEmpid(rs.getString("Emp_Id"));
				nominees.setDependentsName(rs.getString("Dependent_Name"));
				nominees.setRelation(rs.getString("Relation"));
				nominees.setGender(rs.getBoolean("Gender"));
				
				if(rs.getDate("Nominee_Date_Of_Birth") != null)
				{
					nominees.setDateOfBirthOfNominee(rs.getDate("Nominee_Date_Of_Birth"));
					
				}
				else
				{
					nominees.setDateOfBirthOfNominee(null);
					
				}
				nominees.setEmailId(rs.getString("EmailId"));
				nominees.setAgeOfNominee(rs.getString("Nominee_Age"));
				nominees.setDeletedB(rs.getBoolean("Deleted"));
				
				nomination.add(nominees);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  nomination;
		
		
		
	}
	
	public List<NomineeTrackerObj> getNomineeHistory() {
		
		List<NomineeTrackerObj>	nomination = new ArrayList<NomineeTrackerObj>();
		
		String query = "select Emp_Id,Dependent_Name,Relation, Gender, Nominee_Date_Of_Birth,EmailId, Nominee_Age,"
				+ " Deleted, Insertion_Date, Inserted_By from "
				+ "NOMINATION_DETAILS";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
	
				NomineeTrackerObj nominees = new NomineeTrackerObj();
				nominees.setEmpid(rs.getString("Emp_Id"));
				nominees.setDependentsName(rs.getString("Dependent_Name"));
				nominees.setRelation(rs.getString("Relation"));
				nominees.setGender(rs.getBoolean("Gender"));
				
				if(rs.getDate("Nominee_Date_Of_Birth") != null)
				{
					nominees.setDateOfBirthOfNominee(rs.getDate("Nominee_Date_Of_Birth"));
					
				}
				else
				{
					nominees.setDateOfBirthOfNominee(null);
					
				}
				nominees.setEmailId(rs.getString("EmailId"));
				nominees.setAgeOfNominee(rs.getString("Nominee_Age"));
				nominees.setDeletedB(rs.getBoolean("Deleted"));
				nominees.setInsertedDate(rs.getDate("Insertion_Date"));
				nominees.setInsertedBy(rs.getString("Inserted_By"));
				
				nomination.add(nominees);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  nomination;
		
		
		
	}
	

	public boolean updateNominee(NomineeTrackerObj nominee) {
		
		System.out.println(nominee);
		boolean success = true;
		
		String query = "UPDATE NOMINATION_DETAILS SET Dependent_Name=?,Relation=?,Gender=?, Nominee_Date_Of_Birth=?, "
				+ "Nominee_Age=? where EmailId=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, nominee.getDependentsName());
			ps.setString(2, nominee.getRelation());
			ps.setBoolean(3, nominee.getGender());
			
			if(nominee.getDateOfBirthOfNominee() != null)
			{
				ps.setDate(4,  new Date(nominee.getDateOfBirthOfNominee().getTime()));
				
			}
			else
			{
				ps.setDate(4, null);
				
			}
			ps.setString(5, nominee.getAgeOfNominee());
			ps.setString(6, nominee.getEmailId());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}

	public boolean deleteNominee(NomineeTrackerObj nominee) {
		
		boolean success = true;
		
		String query = "UPDATE NOMINATION_DETAILS SET Deleted = TRUE WHERE EmailId = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, nominee.getEmailId());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return success;
		
		}

	public List<EndorsementTrackerObj> getEndorsementDetails(String empid) {
		
		List<EndorsementTrackerObj> endorsemet = new ArrayList<EndorsementTrackerObj>();
		
		String query = "select Emp_Id,GPA_Sum_Assured,GTL_Sum_Insured, GMC_Sum_Insured, Annual_Ctc,Deleted from "
				+ "ENDORSEMENT_TRACKER where Emp_Id=? ";
		
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
				EndorsementTrackerObj end  = new EndorsementTrackerObj();
				end.setEmpid(rs.getString("Emp_Id"));
				end.setGpaSumAssured(rs.getString("GPA_Sum_Assured"));
				end.setGtlSumInsured(rs.getString("GTL_Sum_Insured"));
				end.setGmcSumInsured(rs.getString("GMC_Sum_Insured"));
				end.setAnnualCtc(rs.getString("Annual_Ctc"));
				end.setDeletedB(rs.getBoolean("Deleted"));
				
				endorsemet.add(end);
		
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  endorsemet;
		
	}
		
	public List<EndorsementTrackerObj> getEndorsementHistory() {
		
		List<EndorsementTrackerObj> endorsemet = new ArrayList<EndorsementTrackerObj>();
		
		String query = "select Emp_Id,GPA_Sum_Assured,GTL_Sum_Insured, GMC_Sum_Insured, Annual_Ctc,Deleted,Insertion_Date,"
				+ " Inserted_By from "
				+ "ENDORSEMENT_TRACKER ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				EndorsementTrackerObj end  = new EndorsementTrackerObj();
				end.setEmpid(rs.getString("Emp_Id"));
				end.setGpaSumAssured(rs.getString("GPA_Sum_Assured"));
				end.setGtlSumInsured(rs.getString("GTL_Sum_Insured"));
				end.setGmcSumInsured(rs.getString("GMC_Sum_Insured"));
				end.setAnnualCtc(rs.getString("Annual_Ctc"));
				end.setDeletedB(rs.getBoolean("Deleted"));
				end.setInsertedDate(rs.getDate("Insertion_Date"));
				end.setInsertedBy(rs.getString("Inserted_By"));
				
				endorsemet.add(end);
		
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  endorsemet;
		
	}
	

	public boolean updateEndorsement(EndorsementTrackerObj endor) {
		
		System.out.println(endor);
		boolean success = true;
		
		String query = "UPDATE ENDORSEMENT_TRACKER SET GPA_Sum_Assured=?,GTL_Sum_Insured=?, GMC_Sum_Insured=?, "
				+ "Annual_Ctc=? where Emp_Id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			ps.setString(1, endor.getGpaSumAssured());
			ps.setString(2, endor.getGtlSumInsured());
			ps.setString(3, endor.getGmcSumInsured());
			ps.setString(4, endor.getAnnualCtc());
			ps.setInt(5, Integer.parseInt(endor.getEmpid()));
			
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
		
	}

	public boolean deleteEndorsement(EndorsementTrackerObj endorse) {
		
		boolean success = true;
		
		String query = "UPDATE ENDORSEMENT_TRACKER SET Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(endorse.getEmpid()));
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

