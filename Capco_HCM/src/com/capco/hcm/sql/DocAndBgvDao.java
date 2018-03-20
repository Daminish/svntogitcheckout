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
import com.capco.hcm.model.EmployeeBasicInfoObj;


/**
 * @author Mohit Gangil
 *
 */

public class DocAndBgvDao implements Serializable{
	private static final long serialVersionUID = -2325921145785706004L;
	
	private DatabaseController dbCtrl;
	
	public DatabaseController getDbCtrl() {
		return dbCtrl;
	}

	public void setDbCtrl(DatabaseController dbCtrl) {
		this.dbCtrl = dbCtrl;
	}

	public List<DocAndBgvObj> getDocAndBgvStatus(){
		
		List<DocAndBgvObj> background = new ArrayList<DocAndBgvObj>();
		
		String query = "SELECT Emp_Id, Pre_Onboarding_Initiated, Owner, Welcome_Email_Date, Document_Received_Date, First_Chaser, Second_Chaser, Third_Chaser, "
						+ " Escalation_Date, Nhp_Creation_Date, New_Hire_Provision, Signed_Offer_Letter, Cv, Personal_Details_Form, Mediclaim_Nomination_Form, "
						+ " PF_Nomination_Form, Passport_Size_Photo, Nda, Code_Of_Conduct, Releiving_Letter, Experience_Letter, Last_Payslips, Marksheets, "
						+ " Pan_Card, Passport_Number, Passport_Expire, Types_Of_Visa, Visa_Country, Bgv_Initiated_Date, Bgv_Ref_Number, Bgv_Interim_Report, "
						+ " Bgv_Final_Report, Bgv_Color_Code, Bgv_Sign_Off_By, Bgv_Sign_Off_Date, Bgv_Closure_Date, Package_Initiated, Employment,"
						+ " Court_Check, Identity_Check, Country_Check, Reference_Check, Cv_Check, Deleted, Insertion_Date, Inserted_By "
						+ " FROM DOC_BGV_TRACKER ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				DocAndBgvObj dabObj = new DocAndBgvObj();
				dabObj.setEmployeeId(rs.getString("Emp_Id"));
				if(rs.getDate("Bgv_Closure_Date") != null)
				{
					dabObj.setBgvClosureDate(new java.util.Date(rs.getDate("Bgv_Closure_Date").getTime()));
					
				}
				else
				{
					dabObj.setBgvClosureDate(null);
					
				}
				
				if(rs.getDate("Bgv_Final_Report") != null)
				{
					dabObj.setBgvFinalReportDate(new java.util.Date(rs.getDate("Bgv_Final_Report").getTime()));
					
				}
				else
				{
					dabObj.setBgvFinalReportDate(null);
					
				}
				
				if(rs.getDate("Bgv_Initiated_Date") != null)
				{
					dabObj.setBgvInitiatedDate(new java.util.Date(rs.getDate("Bgv_Initiated_Date").getTime()));
				}
				else
				{
					dabObj.setBgvInitiatedDate(null);
				}
				
				if(rs.getDate("Bgv_Interim_Report") != null)
				{
					dabObj.setBgvInterimReportDate(new java.util.Date(rs.getDate("Bgv_Interim_Report").getTime()));
					
				}
				else
				{
					dabObj.setBgvInterimReportDate(null);
					
				}
				
				dabObj.setBgvRefNo(rs.getString("Bgv_Ref_Number"));
				dabObj.setBgvReportColorCode(rs.getString("Bgv_Color_Code"));
				dabObj.setBgvSignOffBy(rs.getString("Bgv_Sign_Off_By"));
		
				if(rs.getDate("Bgv_Sign_Off_Date") != null)
				{
					dabObj.setBgvSignOffDate(new java.util.Date(rs.getDate("Bgv_Sign_Off_Date").getTime()));
					
				}
				else
				{
					dabObj.setBgvSignOffDate(null);
					
				}
				dabObj.setCountry(rs.getString("Visa_Country"));
				dabObj.setCvReceived(rs.getBoolean("Cv"));
		
				if(rs.getDate("Document_Received_Date") != null)
				{
					dabObj.setDocReceivedOnDate(new java.util.Date(rs.getDate("Document_Received_Date").getTime()));
					
				}
				else
				{
					dabObj.setDocReceivedOnDate(null);
					
				}
				

				if(rs.getDate("Escalation_Date") != null)
				{
					dabObj.setEscalationDate(new java.util.Date(rs.getDate("Escalation_Date").getTime()));
					
				}
				else
				{
					dabObj.setEscalationDate(null);
					
				}
			
				dabObj.setExperienceLetterReceived(rs.getBoolean("Experience_Letter"));
			
				if(rs.getDate("First_Chaser") != null)
				{
					dabObj.setFirstChaserDate(new java.util.Date(rs.getDate("First_Chaser").getTime()));
					
				}
				else
				{
					dabObj.setFirstChaserDate(null);
					
				}
				
				dabObj.setLastPayslipsReceived(rs.getBoolean("Last_Payslips"));
				dabObj.setMarksheetsReceived(rs.getBoolean("Marksheets"));
				dabObj.setMedicliamNominationReceived(rs.getBoolean("Mediclaim_Nomination_Form"));
				dabObj.setNdaReceived(rs.getBoolean("Nda"));
			
				if(rs.getDate("Nhp_Creation_Date") != null)
				{
					dabObj.setNewHireProvisionCreationDate(new java.util.Date(rs.getDate("Nhp_Creation_Date").getTime()));
					
				}
				else
				{
					dabObj.setNewHireProvisionCreationDate(null);
					
				}
			
				dabObj.setOwner(rs.getString("Owner"));
				dabObj.setPanCardReceived(rs.getString("Pan_Card"));
			
				if(rs.getDate("Passport_Expire") != null)
				{
					dabObj.setPassportExpiry(new java.util.Date(rs.getDate("Passport_Expire").getTime()));
					
				}
				else
				{
					dabObj.setPassportExpiry(null);
					
				}
				
				dabObj.setPassportNumberReceived(rs.getString("Passport_Number"));
				dabObj.setPassportSizePhotoReceived(rs.getBoolean("Passport_Size_Photo"));
				dabObj.setPersonalDetailsFormReceived(rs.getBoolean("Personal_Details_Form"));
				dabObj.setPfNominationReceived(rs.getBoolean("PF_Nomination_Form"));
				dabObj.setPreOnboarding(rs.getBoolean("Pre_Onboarding_Initiated"));
				dabObj.setReleivingLetterReceived(rs.getBoolean("Releiving_Letter"));
			
				if(rs.getDate("Second_Chaser") != null)
				{
					dabObj.setSecondChaserDate(new java.util.Date(rs.getDate("Second_Chaser").getTime()));
					
				}
				else
				{
					dabObj.setSecondChaserDate(null);
					
				}
				
				dabObj.setSignedCodeOfConductReceived(rs.getBoolean("Code_Of_Conduct"));
				dabObj.setSignedOfferLetterReceived(rs.getBoolean("Signed_Offer_Letter"));
				
				if(rs.getDate("Third_Chaser") != null)
				{
					dabObj.setThirdChaserDate(new java.util.Date(rs.getDate("Third_Chaser").getTime()));
					
				}
				else
				{
					dabObj.setThirdChaserDate(null);
					
				}
				
				dabObj.setVisaType(rs.getString("Types_Of_Visa"));
			
				if(rs.getDate("Welcome_Email_Date") != null)
				{
					dabObj.setWelcomeEmailSentDate(new java.util.Date(rs.getDate("Welcome_Email_Date").getTime()));
					
				}
				else
				{
					dabObj.setWelcomeEmailSentDate(null);
					
				}
				
				dabObj.setPackageInitiated(rs.getString("Package_Initiated"));
				dabObj.setEmployment(rs.getString("Employment"));
				dabObj.setCourtCheck(rs.getString("Court_Check"));
				dabObj.setIdentityCheck(rs.getString("Identity_Check"));
				dabObj.setCountryCheck(rs.getString("Country_Check"));
				dabObj.setReferenceCheck(rs.getString("Reference_Check"));
				dabObj.setCvCheck(rs.getString("Cv_Check"));
				dabObj.setInsertedBy(rs.getString("Inserted_By"));
				dabObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
				
				background.add(dabObj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return background;
	}
	
	
	public void updateEmpDocdetails(List<DocAndBgvObj> empobj)
	{
		for(DocAndBgvObj obj:empobj)
		{
			if(null != obj.getEmployeeId() ||  !"0".equals(obj.getEmployeeId()))
			{
				
				System.out.println("Object main kya hain bai "+ obj);
				
				DocAndBgvObj background = (DocAndBgvObj) getbackgroundInfo(obj.getEmployeeId());
				
				
				if(background !=null && background.getEmployeeId()!=null && Integer.parseInt(background.getEmployeeId())>0)
				{
					System.out.println("Comes under update"+obj);
					updateVerify(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertDocAndBgvStatus(obj);
				}
			}
		}
	}
	
	
	
	
	public boolean insertDocAndBgvStatus(DocAndBgvObj dabObj){//, String empId, String updatedBy){
		boolean success = true;

		String query = "INSERT INTO DOC_BGV_TRACKER (Emp_Id, Pre_Onboarding_Initiated, Owner, Welcome_Email_Date, Document_Received_Date, First_Chaser, "
						+ " Second_Chaser, Third_Chaser, Escalation_Date, Nhp_Creation_Date, New_Hire_Provision, Signed_Offer_Letter, Cv, Personal_Details_Form, "
						+ " Mediclaim_Nomination_Form, PF_Nomination_Form, Passport_Size_Photo, Nda, Code_Of_Conduct, Releiving_Letter, Experience_Letter, "
						+ " Last_Payslips, Marksheets, Pan_Card, Passport_Number, Passport_Expire, Types_Of_Visa, Visa_Country, Bgv_Initiated_Date, Bgv_Ref_Number, "
						+ " Bgv_Interim_Report, Bgv_Final_Report, Bgv_Color_Code, Bgv_Sign_Off_By, Bgv_Sign_Off_Date, Bgv_Closure_Date,  Package_Initiated,"
						+ " Employment, Court_Check, Identity_Check, Country_Check, Reference_Check, Cv_Check, Deleted, Inserted_By)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
						+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(dabObj.getEmployeeId()));
			ps.setBoolean(2, dabObj.getPreOnboarding());
			ps.setString(3, dabObj.getOwner());
			
			if(dabObj.getWelcomeEmailSentDate() != null)
			{
				ps.setDate(4, new Date( dabObj.getWelcomeEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(4, null);
				
			}
			
			if(dabObj.getDocReceivedOnDate() != null)
			{
				ps.setDate(5, new Date( dabObj.getDocReceivedOnDate().getTime()));
				
			}
			else
			{
				ps.setDate(5, null);
				
			}
			
			if(dabObj.getFirstChaserDate() != null)
			{
				ps.setDate(6, new Date( dabObj.getFirstChaserDate().getTime()));
				
			}
			else
			{
				ps.setDate(6, null);
				
			}
			
			if(dabObj.getSecondChaserDate() != null)
			{
				ps.setDate(7, new Date( dabObj.getSecondChaserDate().getTime()));
			}
			else
			{
				ps.setDate(7, null);
			}
			
			if(dabObj.getThirdChaserDate() != null)
			{
				ps.setDate(8, new Date( dabObj.getThirdChaserDate().getTime()));
			}
			else
			{
				ps.setDate(8, null);
			}
			
			
			if(dabObj.getEscalationDate() != null)
			{
				ps.setDate(9, new Date( dabObj.getEscalationDate().getTime()));
				
			}
			else
			{
				ps.setDate(9, null);
				
			}
			
			if(dabObj.getNewHireProvisionCreationDate() != null)
			{
				ps.setDate(10, new Date( dabObj.getNewHireProvisionCreationDate().getTime()));
				
			}
			else
			{
				ps.setDate(10, null);
				
			}
			
			ps.setBoolean(11, dabObj.getNewHireProvision());
			ps.setBoolean(12, dabObj.getSignedOfferLetterReceived());
			ps.setBoolean(13, dabObj.getCvReceived());
			ps.setBoolean(14, dabObj.getPersonalDetailsFormReceived());
			ps.setBoolean(15, dabObj.getMedicliamNominationReceived());
			ps.setBoolean(16, dabObj.getPfNominationReceived());
			ps.setBoolean(17, dabObj.getPassportSizePhotoReceived());
			ps.setBoolean(18, dabObj.getNdaReceived());
			ps.setBoolean(19, dabObj.getSignedCodeOfConductReceived());
			ps.setBoolean(20, dabObj.getReleivingLetterReceived());
			ps.setBoolean(21, dabObj.getExperienceLetterReceived());
			ps.setBoolean(22, dabObj.getLastPayslipsReceived());
			ps.setBoolean(23, dabObj.getMarksheetsReceived());
			ps.setString(24, dabObj.getPanCardReceived());
			ps.setString(25, dabObj.getPassportNumberReceived());
			
			if(dabObj.getPassportExpiry() != null)
			{
				ps.setDate(26, new Date( dabObj.getPassportExpiry().getTime()));
				
			}
			else
			{
				ps.setDate(26, null);
				
			}
			
			ps.setString(27, dabObj.getVisaType());
			ps.setString(28, dabObj.getCountry());
			
			if(dabObj.getBgvInitiatedDate() != null)
			{
				ps.setDate(29, new Date( dabObj.getBgvInitiatedDate().getTime()));
			}
			else
			{
				ps.setDate(29, null);
			}
			
			ps.setString(30, dabObj.getBgvRefNo());
			
			if(dabObj.getBgvInterimReportDate() != null)
			{
				ps.setDate(31, new Date( dabObj.getBgvInterimReportDate().getTime()));
				
			}
			else
			{
				ps.setDate(31, null);
				
			}
			
			if(dabObj.getBgvFinalReportDate() != null)
			{
				ps.setDate(32, new Date( dabObj.getBgvFinalReportDate().getTime()));
				
			}
			else
			{
				ps.setDate(32, null);
				
			}
			ps.setString(33, dabObj.getBgvReportColorCode());
			ps.setString(34, dabObj.getBgvSignOffBy());
			
			if(dabObj.getBgvSignOffDate() != null)
			{
				ps.setDate(35, new Date( dabObj.getBgvSignOffDate().getTime()));
				
			}
			else
			{
				ps.setDate(35, null);
				
			}
			
			if(dabObj.getBgvClosureDate() != null)
			{
				ps.setDate(36, new Date( dabObj.getBgvClosureDate().getTime()));
				
			}
			else
			{
				ps.setDate(36, null);
				
			}
			ps.setString(37, dabObj.getPackageInitiated());
			ps.setString(38, dabObj.getEmployment());
			ps.setString(39, dabObj.getCourtCheck());
			ps.setString(40, dabObj.getIdentityCheck());
			ps.setString(41, dabObj.getCountryCheck());
			ps.setString(42, dabObj.getReferenceCheck());
			ps.setString(43, dabObj.getCvCheck());
			ps.setString(44, dabObj.getInsertedBy());
			ps.execute();
		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}
	
	/*public List<DocAndBgvObj> getDocAndBgvRevisionHistory(String empId){
		List<DocAndBgvObj> dabObjRevHist = new ArrayList<DocAndBgvObj>();
		
		String query = "SELECT Pre_Onboarding_Initiated, Owner, Welcome_Email_Date, Document_Received_Date, First_Chaser, Second_Chaser, Third_Chaser, "
						+ " Escalation_Date, Nhp_Creation_Date, New_Hire_Provision, Signed_Offer_Letter, Cv, Personal_Details_Form, Mediclaim_Nomination_Form, "
						+ " PF_Nomination_Form, Passport_Size_Photo, Nda, Code_Of_Conduct, Releiving_Letter, Experience_Letter, Last_Payslips, Marksheets, "
						+ " Pan_Card, Passport_Number, Passport_Expire, Types_Of_Visa, Bgv_Initiated_Date, Bgv_Ref_Number, Bgv_Interim_Report, Bgv_Final_Report, "
						+ " Bgv_Color_Code, Bgv_Sign_Off_By, Bgv_Sign_Off_Date, Bgv_Closure_Date, Package_Initiated, Employment, Court_Check, Identity_Check,"
						+ "  Country_Check, Reference_Check, Cv_Check,  Insertion_Date, Inserted_By "
						+ " FROM DOC_BGV_TRACKER WHERE Emp_Id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(empId));
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				DocAndBgvObj dabObj = new DocAndBgvObj();
				
				dabObj.setBgvClosureDate(new java.util.Date(rs.getDate("Bgv_Closure_Date").getTime()));
				dabObj.setBgvFinalReportDate(new java.util.Date(rs.getDate("Bgv_Final_Report").getTime()));
				dabObj.setBgvInitiatedDate(new java.util.Date(rs.getDate("Bgv_Initiated_Date").getTime()));
				dabObj.setBgvInterimReportDate(new java.util.Date(rs.getDate("Bgv_Interim_Report").getTime()));
				dabObj.setBgvRefNo(rs.getString("Bgv_Ref_Number"));
				dabObj.setBgvReportColorCode(rs.getString("Bgv_Color_Code"));
				dabObj.setBgvSignOffBy(rs.getString("Bgv_Sign_Off_By"));
				dabObj.setBgvSignOffDate(new java.util.Date(rs.getDate("Bgv_Sign_Off_Date").getTime()));
				dabObj.setCountry(rs.getString("Visa_Country"));
				dabObj.setCvReceived(rs.getBoolean("Cv"));
				dabObj.setDocReceivedOnDate(new java.util.Date(rs.getDate("Document_Received_Date").getTime()));
				dabObj.setEscalationDate(new java.util.Date(rs.getDate("Escalation_Date").getTime()));
				dabObj.setExperienceLetterReceived(rs.getBoolean("Experience_Letter"));
				dabObj.setFirstChaserDate(new java.util.Date(rs.getDate("First_Chaser").getTime()));
				dabObj.setLastPayslipsReceived(rs.getBoolean("Last_Payslips"));
				dabObj.setMarksheetsReceived(rs.getBoolean("Marksheets"));
				dabObj.setMedicliamNominationReceived(rs.getBoolean("Mediclaim_Nomination_Form"));
				dabObj.setNdaReceived(rs.getBoolean("Nda"));
				dabObj.setNewHireProvisionCreationDate(new java.util.Date(rs.getDate("Nhp_Creation_Date").getTime()));
				dabObj.setOwner(rs.getString("Owner"));
				dabObj.setPanCardReceived(rs.getString("Pan_Card"));
				dabObj.setPassportExpiry(new java.util.Date(rs.getDate("Passport_Expire").getTime()));
				dabObj.setPassportNumberReceived(rs.getString("Passport_Number"));
				dabObj.setPassportSizePhotoReceived(rs.getBoolean("Passport_Size_Photo"));
				dabObj.setPersonalDetailsFormReceived(rs.getBoolean("Personal_Details_Form"));
				dabObj.setPfNominationReceived(rs.getBoolean("PF_Nomination_Form"));
				dabObj.setPreOnboarding(rs.getBoolean("Pre_Onboarding_Initiated"));
				dabObj.setReleivingLetterReceived(rs.getBoolean("Releiving_Letter"));
				dabObj.setSecondChaserDate(new java.util.Date(rs.getDate("Second_Chaser").getTime()));
				dabObj.setSignedCodeOfConductReceived(rs.getBoolean("Code_Of_Conduct"));
				dabObj.setSignedOfferLetterReceived(rs.getBoolean("Signed_Offer_Letter"));
				dabObj.setThirdChaserDate(new java.util.Date(rs.getDate("Third_Chaser").getTime()));
				dabObj.setVisaType(rs.getString("Types_Of_Visa"));
				dabObj.setWelcomeEmailSentDate(new java.util.Date(rs.getDate("Welcome_Email_Date").getTime()));
				dabObj.setPackageInitiated(rs.getString("Package_Initiated"));
				dabObj.setEmployment(rs.getString("Employment"));
				dabObj.setCourtCheck(rs.getString("Court_Check"));
				dabObj.setIdentityCheck(rs.getString("Identity_Check"));
				dabObj.setCountryCheck(rs.getString("Country_Check"));
				dabObj.setReferenceCheck(rs.getString("Reference_Check"));
				dabObj.setCvCheck(rs.getString("Cv_Check"));
				dabObj.setInsertedBy(rs.getString("Inserted_By"));
				dabObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
				
				dabObjRevHist.add(dabObj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return dabObjRevHist;
	}*/

	public DocAndBgvObj getbackgroundInfo(String employeeId){
		DocAndBgvObj dabObj = new DocAndBgvObj();
		String query ="SELECT Emp_Id, Pre_Onboarding_Initiated, Owner, Welcome_Email_Date, Document_Received_Date, First_Chaser, Second_Chaser, Third_Chaser, "
				+ " Escalation_Date, Nhp_Creation_Date, New_Hire_Provision, Signed_Offer_Letter, Cv, Personal_Details_Form, Mediclaim_Nomination_Form, "
				+ " PF_Nomination_Form, Passport_Size_Photo, Nda, Code_Of_Conduct, Releiving_Letter, Experience_Letter, Last_Payslips, Marksheets, "
				+ " Pan_Card, Passport_Number, Passport_Expire, Types_Of_Visa, Visa_Country, Bgv_Initiated_Date, Bgv_Ref_Number, Bgv_Interim_Report, Bgv_Final_Report, "
				+ " Bgv_Color_Code, Bgv_Sign_Off_By, Bgv_Sign_Off_Date, Bgv_Closure_Date, Package_Initiated, Employment, Court_Check, Identity_Check,"
				+ "  Country_Check, Reference_Check, Cv_Check,Deleted   "
				+ " FROM DOC_BGV_TRACKER where Emp_Id=? ";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(employeeId));
				rs = ps.executeQuery();
		
		while(rs.next()){
			
			dabObj.setEmployeeId(""+rs.getInt("Emp_Id"));
			
			if(rs.getDate("Bgv_Closure_Date") != null)
			{
				dabObj.setBgvClosureDate(new java.util.Date(rs.getDate("Bgv_Closure_Date").getTime()));
				
			}
			else
			{
				dabObj.setBgvClosureDate(null);
				
			}
			
			if(rs.getDate("Bgv_Final_Report") != null)
			{
				dabObj.setBgvFinalReportDate(new java.util.Date(rs.getDate("Bgv_Final_Report").getTime()));
				
			}
			else
			{
				dabObj.setBgvFinalReportDate(null);
				
			}
			
			if(rs.getDate("Bgv_Initiated_Date") != null)
			{
				dabObj.setBgvInitiatedDate(new java.util.Date(rs.getDate("Bgv_Initiated_Date").getTime()));
			}
			else
			{
				dabObj.setBgvInitiatedDate(null);
			}
			
			if(rs.getDate("Bgv_Interim_Report") != null)
			{
				dabObj.setBgvInterimReportDate(new java.util.Date(rs.getDate("Bgv_Interim_Report").getTime()));
				
			}
			else
			{
				dabObj.setBgvInterimReportDate(null);
				
			}
			
			dabObj.setBgvRefNo(rs.getString("Bgv_Ref_Number"));
			dabObj.setBgvReportColorCode(rs.getString("Bgv_Color_Code"));
			dabObj.setBgvSignOffBy(rs.getString("Bgv_Sign_Off_By"));
			
			if(rs.getDate("Bgv_Sign_Off_Date") != null)
			{
				dabObj.setBgvSignOffDate(new java.util.Date(rs.getDate("Bgv_Sign_Off_Date").getTime()));
				
			}
			else
			{
				dabObj.setBgvSignOffDate(null);
				
			}
			
			dabObj.setCountry(rs.getString("Visa_Country"));
			dabObj.setCvReceived(rs.getBoolean("Cv"));
			
			if(rs.getDate("Document_Received_Date") != null)
			{
				dabObj.setDocReceivedOnDate(new java.util.Date(rs.getDate("Document_Received_Date").getTime()));
				
			}
			else
			{
				dabObj.setDocReceivedOnDate(null);
				
			}
			
			if(rs.getDate("Escalation_Date") != null)
			{
				dabObj.setEscalationDate(new java.util.Date(rs.getDate("Escalation_Date").getTime()));
				
			}
			else
			{
				dabObj.setEscalationDate(null);
				
			}
			dabObj.setExperienceLetterReceived(rs.getBoolean("Experience_Letter"));
			
			if(rs.getDate("First_Chaser") != null)
			{
				dabObj.setFirstChaserDate(new java.util.Date(rs.getDate("First_Chaser").getTime()));
				
			}
			else
			{
				dabObj.setFirstChaserDate(null);
				
			}
			dabObj.setLastPayslipsReceived(rs.getBoolean("Last_Payslips"));
			dabObj.setMarksheetsReceived(rs.getBoolean("Marksheets"));
			dabObj.setMedicliamNominationReceived(rs.getBoolean("Mediclaim_Nomination_Form"));
			dabObj.setNdaReceived(rs.getBoolean("Nda"));
			
			if(rs.getDate("Nhp_Creation_Date") != null)
			{
				dabObj.setNewHireProvisionCreationDate(new java.util.Date(rs.getDate("Nhp_Creation_Date").getTime()));
				
			}
			else
			{
				dabObj.setNewHireProvisionCreationDate(null);
				
			}
			dabObj.setOwner(rs.getString("Owner"));
			dabObj.setPanCardReceived(rs.getString("Pan_Card"));
			
			if(rs.getDate("Passport_Expire") != null)
			{
				dabObj.setPassportExpiry(new java.util.Date(rs.getDate("Passport_Expire").getTime()));
				
			}
			else
			{
				dabObj.setPassportExpiry(null);
				
			}
			dabObj.setPassportNumberReceived(rs.getString("Passport_Number"));
			dabObj.setPassportSizePhotoReceived(rs.getBoolean("Passport_Size_Photo"));
			dabObj.setPersonalDetailsFormReceived(rs.getBoolean("Personal_Details_Form"));
			dabObj.setPfNominationReceived(rs.getBoolean("PF_Nomination_Form"));
			dabObj.setPreOnboarding(rs.getBoolean("Pre_Onboarding_Initiated"));
			dabObj.setReleivingLetterReceived(rs.getBoolean("Releiving_Letter"));
			
			if(rs.getDate("Second_Chaser") != null)
			{
				dabObj.setSecondChaserDate(new java.util.Date(rs.getDate("Second_Chaser").getTime()));
				
			}
			else
			{
				dabObj.setSecondChaserDate(null);
				
			}
			dabObj.setSignedCodeOfConductReceived(rs.getBoolean("Code_Of_Conduct"));
			dabObj.setSignedOfferLetterReceived(rs.getBoolean("Signed_Offer_Letter"));
			
			if(rs.getDate("Third_Chaser") != null)
			{
				dabObj.setThirdChaserDate(new java.util.Date(rs.getDate("Third_Chaser").getTime()));
				
			}
			else
			{
				dabObj.setThirdChaserDate(null);
				
			}
			
			dabObj.setVisaType(rs.getString("Types_Of_Visa"));
			
			if(rs.getDate("Welcome_Email_Date") != null)
			{
				dabObj.setWelcomeEmailSentDate(new java.util.Date(rs.getDate("Welcome_Email_Date").getTime()));
				
			}
			else
			{
				dabObj.setWelcomeEmailSentDate(null);
				
			}
			dabObj.setPackageInitiated(rs.getString("Package_Initiated"));
			dabObj.setEmployment(rs.getString("Employment"));
			dabObj.setCourtCheck(rs.getString("Court_Check"));
			dabObj.setIdentityCheck(rs.getString("Identity_Check"));
			dabObj.setCountryCheck(rs.getString("Country_Check"));
			dabObj.setReferenceCheck(rs.getString("Reference_Check"));
			dabObj.setCvCheck(rs.getString("Cv_Check"));
			dabObj.setDeletedB(rs.getBoolean("Deleted"));
			
				}
			}catch(SQLException e){
				e.printStackTrace();
			} finally{
				
				try{ps.close();}catch(SQLException e){e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}
			return dabObj;
			
		}
	
	
	
	
	public List<DocAndBgvObj> getVerify(String empid) {
		// TODO Auto-generated method stub
		
		List<DocAndBgvObj>	verify = new ArrayList<DocAndBgvObj>();
		
		String query = "SELECT Emp_Id, Pre_Onboarding_Initiated, Owner, Welcome_Email_Date, Document_Received_Date, First_Chaser, Second_Chaser, Third_Chaser, "
				+ " Escalation_Date, Nhp_Creation_Date, New_Hire_Provision, Signed_Offer_Letter, Cv, Personal_Details_Form, Mediclaim_Nomination_Form, "
				+ " PF_Nomination_Form, Passport_Size_Photo, Nda, Code_Of_Conduct, Releiving_Letter, Experience_Letter, Last_Payslips, Marksheets, "
				+ " Pan_Card, Passport_Number, Passport_Expire, Types_Of_Visa, Visa_Country, Bgv_Initiated_Date, Bgv_Ref_Number, Bgv_Interim_Report, Bgv_Final_Report, "
				+ " Bgv_Color_Code, Bgv_Sign_Off_By, Bgv_Sign_Off_Date, Bgv_Closure_Date, Package_Initiated, Employment, Court_Check, Identity_Check,"
				+ "  Country_Check, Reference_Check, Cv_Check,Deleted   "
				+ " FROM DOC_BGV_TRACKER where Emp_Id=? ";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(empid));
			rs = ps.executeQuery();
	
	while(rs.next()){
		DocAndBgvObj dabObj = new DocAndBgvObj();
		dabObj.setEmployeeId(""+rs.getInt("Emp_Id"));
		
		if(rs.getDate("Bgv_Closure_Date") != null)
		{
			dabObj.setBgvClosureDate(new java.util.Date(rs.getDate("Bgv_Closure_Date").getTime()));
			
		}
		else
		{
			dabObj.setBgvClosureDate(null);
			
		}
		
		if(rs.getDate("Bgv_Final_Report") != null)
		{
			dabObj.setBgvFinalReportDate(new java.util.Date(rs.getDate("Bgv_Final_Report").getTime()));
			
		}
		else
		{
			dabObj.setBgvFinalReportDate(null);
			
		}
		
		if(rs.getDate("Bgv_Initiated_Date") != null)
		{
			dabObj.setBgvInitiatedDate(new java.util.Date(rs.getDate("Bgv_Initiated_Date").getTime()));
		}
		else
		{
			dabObj.setBgvInitiatedDate(null);
		}
		
		if(rs.getDate("Bgv_Interim_Report") != null)
		{
			dabObj.setBgvInterimReportDate(new java.util.Date(rs.getDate("Bgv_Interim_Report").getTime()));
			
		}
		else
		{
			dabObj.setBgvInterimReportDate(null);
			
		}
		
		dabObj.setBgvRefNo(rs.getString("Bgv_Ref_Number"));
		dabObj.setBgvReportColorCode(rs.getString("Bgv_Color_Code"));
		dabObj.setBgvSignOffBy(rs.getString("Bgv_Sign_Off_By"));
		
		if(rs.getDate("Bgv_Sign_Off_Date") != null)
		{
			dabObj.setBgvSignOffDate(new java.util.Date(rs.getDate("Bgv_Sign_Off_Date").getTime()));
			
		}
		else
		{
			dabObj.setBgvSignOffDate(null);
			
		}
		
		dabObj.setCountry(rs.getString("Visa_Country"));
		dabObj.setCvReceived(rs.getBoolean("Cv"));
		
		if(rs.getDate("Document_Received_Date") != null)
		{
			dabObj.setDocReceivedOnDate(new java.util.Date(rs.getDate("Document_Received_Date").getTime()));
			
		}
		else
		{
			dabObj.setDocReceivedOnDate(null);
			
		}
		
		if(rs.getDate("Escalation_Date") != null)
		{
			dabObj.setEscalationDate(new java.util.Date(rs.getDate("Escalation_Date").getTime()));
			
		}
		else
		{
			dabObj.setEscalationDate(null);
			
		}
		dabObj.setExperienceLetterReceived(rs.getBoolean("Experience_Letter"));
		
		if(rs.getDate("First_Chaser") != null)
		{
			dabObj.setFirstChaserDate(new java.util.Date(rs.getDate("First_Chaser").getTime()));
			
		}
		else
		{
			dabObj.setFirstChaserDate(null);
			
		}
		dabObj.setLastPayslipsReceived(rs.getBoolean("Last_Payslips"));
		dabObj.setMarksheetsReceived(rs.getBoolean("Marksheets"));
		dabObj.setMedicliamNominationReceived(rs.getBoolean("Mediclaim_Nomination_Form"));
		dabObj.setNdaReceived(rs.getBoolean("Nda"));
		
		if(rs.getDate("Nhp_Creation_Date") != null)
		{
			dabObj.setNewHireProvisionCreationDate(new java.util.Date(rs.getDate("Nhp_Creation_Date").getTime()));
			
		}
		else
		{
			dabObj.setNewHireProvisionCreationDate(null);
			
		}
		dabObj.setOwner(rs.getString("Owner"));
		dabObj.setPanCardReceived(rs.getString("Pan_Card"));
		
		if(rs.getDate("Passport_Expire") != null)
		{
			dabObj.setPassportExpiry(new java.util.Date(rs.getDate("Passport_Expire").getTime()));
			
		}
		else
		{
			dabObj.setPassportExpiry(null);
			
		}
		dabObj.setPassportNumberReceived(rs.getString("Passport_Number"));
		dabObj.setPassportSizePhotoReceived(rs.getBoolean("Passport_Size_Photo"));
		dabObj.setPersonalDetailsFormReceived(rs.getBoolean("Personal_Details_Form"));
		dabObj.setPfNominationReceived(rs.getBoolean("PF_Nomination_Form"));
		dabObj.setPreOnboarding(rs.getBoolean("Pre_Onboarding_Initiated"));
		dabObj.setReleivingLetterReceived(rs.getBoolean("Releiving_Letter"));
		
		if(rs.getDate("Second_Chaser") != null)
		{
			dabObj.setSecondChaserDate(new java.util.Date(rs.getDate("Second_Chaser").getTime()));
			
		}
		else
		{
			dabObj.setSecondChaserDate(null);
			
		}
		dabObj.setSignedCodeOfConductReceived(rs.getBoolean("Code_Of_Conduct"));
		dabObj.setSignedOfferLetterReceived(rs.getBoolean("Signed_Offer_Letter"));
		
		if(rs.getDate("Third_Chaser") != null)
		{
			dabObj.setThirdChaserDate(new java.util.Date(rs.getDate("Third_Chaser").getTime()));
			
		}
		else
		{
			dabObj.setThirdChaserDate(null);
			
		}
		
		dabObj.setVisaType(rs.getString("Types_Of_Visa"));
		
		if(rs.getDate("Welcome_Email_Date") != null)
		{
			dabObj.setWelcomeEmailSentDate(new java.util.Date(rs.getDate("Welcome_Email_Date").getTime()));
			
		}
		else
		{
			dabObj.setWelcomeEmailSentDate(null);
			
		}
		dabObj.setPackageInitiated(rs.getString("Package_Initiated"));
		dabObj.setEmployment(rs.getString("Employment"));
		dabObj.setCourtCheck(rs.getString("Court_Check"));
		dabObj.setIdentityCheck(rs.getString("Identity_Check"));
		dabObj.setCountryCheck(rs.getString("Country_Check"));
		dabObj.setReferenceCheck(rs.getString("Reference_Check"));
		dabObj.setCvCheck(rs.getString("Cv_Check"));
		dabObj.setDeletedB(rs.getBoolean("Deleted"));
		
		verify.add(dabObj);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return verify;
		
	}

	public boolean updateVerify(DocAndBgvObj doc) {
		// TODO Auto-generated method stub
		
		
		System.out.println(doc);
		boolean success = true;
		
		String query = "UPDATE DOC_BGV_TRACKER SET  Pre_Onboarding_Initiated=?, Owner=?, Welcome_Email_Date=?,"
				+ " Document_Received_Date=?, First_Chaser=?, Second_Chaser=?, Third_Chaser=?, "
				+ " Escalation_Date=?, Nhp_Creation_Date=?, New_Hire_Provision=?, Signed_Offer_Letter=?, Cv=?,"
				+ " Personal_Details_Form=?, Mediclaim_Nomination_Form=?,PF_Nomination_Form=?, Passport_Size_Photo=?,"
				+ " Nda=?, Code_Of_Conduct=?, Releiving_Letter=?, Experience_Letter=?, Last_Payslips=?, Marksheets=?, "
				+ " Pan_Card=?, Passport_Number=?, Passport_Expire=?, Types_Of_Visa=?, Visa_Country=?, Bgv_Initiated_Date=?,"
				+ " Bgv_Ref_Number=?, Bgv_Interim_Report=?, Bgv_Final_Report=?,Bgv_Color_Code=?, Bgv_Sign_Off_By=?,"
				+ " Bgv_Sign_Off_Date=?, Bgv_Closure_Date=?, Package_Initiated=?, Employment=?, Court_Check=?,"
				+ " Identity_Check=?,Country_Check=?, Reference_Check=?, Cv_Check=? where Emp_Id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, doc.getPreOnboarding());
			ps.setString(2, doc.getOwner());
			
			if(doc.getWelcomeEmailSentDate() != null)
			{
				ps.setDate(3, new Date( doc.getWelcomeEmailSentDate().getTime()));
				
			}
			else
			{
				ps.setDate(3, null);
				
			}
			
			if(doc.getDocReceivedOnDate() != null)
			{
				ps.setDate(4, new Date( doc.getDocReceivedOnDate().getTime()));
				
			}
			else
			{
				ps.setDate(4, null);
				
			}
			
			if(doc.getFirstChaserDate() != null)
			{
				ps.setDate(5, new Date( doc.getFirstChaserDate().getTime()));
				
			}
			else
			{
				ps.setDate(5, null);
				
			}
			
			if(doc.getSecondChaserDate() != null)
			{
				ps.setDate(6, new Date( doc.getSecondChaserDate().getTime()));
			}
			else
			{
				ps.setDate(6, null);
			}
			
			if(doc.getThirdChaserDate() != null)
			{
				ps.setDate(7, new Date( doc.getThirdChaserDate().getTime()));
			}
			else
			{
				ps.setDate(7, null);
			}
			
			
			if(doc.getEscalationDate() != null)
			{
				ps.setDate(8, new Date( doc.getEscalationDate().getTime()));
				
			}
			else
			{
				ps.setDate(8, null);
				
			}
			
			if(doc.getNewHireProvisionCreationDate() != null)
			{
				ps.setDate(9, new Date( doc.getNewHireProvisionCreationDate().getTime()));
				
			}
			else
			{
				ps.setDate(9, null);
				
			}
			
			ps.setBoolean(10, doc.getNewHireProvision());
			ps.setBoolean(11, doc.getSignedOfferLetterReceived());
			ps.setBoolean(12, doc.getCvReceived());
			ps.setBoolean(13, doc.getPersonalDetailsFormReceived());
			ps.setBoolean(14, doc.getMedicliamNominationReceived());
			ps.setBoolean(15, doc.getPfNominationReceived());
			ps.setBoolean(16, doc.getPassportSizePhotoReceived());
			ps.setBoolean(17, doc.getNdaReceived());
			ps.setBoolean(18, doc.getSignedCodeOfConductReceived());
			ps.setBoolean(19, doc.getReleivingLetterReceived());
			ps.setBoolean(20, doc.getExperienceLetterReceived());
			ps.setBoolean(21, doc.getLastPayslipsReceived());
			ps.setBoolean(22, doc.getMarksheetsReceived());
			ps.setString(23, doc.getPanCardReceived());
			ps.setString(24, doc.getPassportNumberReceived());
		
			if(doc.getPassportExpiry() != null)
			{
				ps.setDate(25, new Date( doc.getPassportExpiry().getTime()));
				
			}
			else
			{
				ps.setDate(25, null);
				
			}
			
			ps.setString(26, doc.getVisaType());
			ps.setString(27, doc.getCountry());
		
			if(doc.getBgvInitiatedDate() != null)
			{
				ps.setDate(28, new Date( doc.getBgvInitiatedDate().getTime()));
			}
			else
			{
				ps.setDate(28, null);
			}
			
			ps.setString(29, doc.getBgvRefNo());
		
			if(doc.getBgvInterimReportDate() != null)
			{
				ps.setDate(30, new Date( doc.getBgvInterimReportDate().getTime()));
				
			}
			else
			{
				ps.setDate(30, null);
				
			}
			
			if(doc.getBgvFinalReportDate() != null)
			{
				ps.setDate(31, new Date( doc.getBgvFinalReportDate().getTime()));
				
			}
			else
			{
				ps.setDate(31, null);
				
			}
			
			
			ps.setString(32, doc.getBgvReportColorCode());
			ps.setString(33, doc.getBgvSignOffBy());
		
			if(doc.getBgvSignOffDate() != null)
			{
				ps.setDate(34, new Date( doc.getBgvSignOffDate().getTime()));
				
			}
			else
			{
				ps.setDate(34, null);
				
			}
			
			if(doc.getBgvClosureDate() != null)
			{
				ps.setDate(35, new Date( doc.getBgvClosureDate().getTime()));
				
			}
			else
			{
				ps.setDate(35, null);
				
			}
			
			ps.setString(36, doc.getPackageInitiated());
			ps.setString(37, doc.getEmployment());
			ps.setString(38, doc.getCourtCheck());
			ps.setString(39, doc.getIdentityCheck());
			ps.setString(40, doc.getCountryCheck());
			ps.setString(41, doc.getReferenceCheck());
			ps.setString(42, doc.getCvCheck());
			ps.setInt(43, Integer.parseInt(doc.getEmployeeId()));
			
			
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
		
	}

	public boolean deleteFieldForBackgroundHistory(DocAndBgvObj document) {
		
		boolean success = true;
		
		String query = "UPDATE DOC_BGV_TRACKER SET Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1,Integer.parseInt(document.getEmployeeId()));
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
