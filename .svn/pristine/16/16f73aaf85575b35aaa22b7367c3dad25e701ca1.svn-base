package com.capco.hcm.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import com.capco.hcm.controller.DatabaseController;
import com.capco.hcm.model.EmployeeBasicInfoObj;
import com.capco.hcm.model.HcmLetterReqObj;

/**
 * @author Mohit Gangil
 *
 */

public class HcmLetterReqSqlDao implements Serializable {

	
	private static final long serialVersionUID = 6299565695719213810L;
	private DatabaseController dbCtrl;
	
	//GETTERS SETTERS
		public DatabaseController getDbCtrl() {
			return dbCtrl;
		}
		public void setDbCtrl(DatabaseController dbCtrl) {
			this.dbCtrl = dbCtrl;
		
		}

	/**
	 * 
	 * @return
	 */
		
		public void updateEmpHCMdetails(List<HcmLetterReqObj> empobj)
		{
			for(HcmLetterReqObj obj:empobj)
			{
				if(null != obj.getEmpId() ||  !"0".equals(obj.getEmpId()))
				{
					
					System.out.println("Object main kya hain bai "+ obj);
					
					HcmLetterReqObj hcmInfo = getHcmDetailsInfo(obj.getEmpId());
					
					
					if(hcmInfo !=null && hcmInfo.getEmpId()!=null && Integer.parseInt(hcmInfo.getEmpId())>0)
					{
						System.out.println("Comes under update"+obj);
						updateLetterRequest(obj.getEmpId(), obj);
					}
					else
					{
						System.out.println("Comes under insert"+obj);
						
						insertLetterRequest(obj);
					}
				}
			}
		}
		
		
		public HcmLetterReqObj getHcmDetailsInfo(String empid) {
			
		HcmLetterReqObj	hcmletter = new HcmLetterReqObj();
			
			String query = "select Emp_Id,Emp_Id_Capco,Emp_Name,Fis_Eid,Passport_Number, Status,Type_Of_Letter, Letter_Purpose,"
					+ "  Purpose_Other_Reason, Other_Visa_Letter, Travel_Country, Passport_From_Date, Passport_End_Date, "
					+ "Stay_From, Stay_To, Passport_Name, Designation,Gender, Cover_Letter_From, Cover_Letter_To,"
					+ "Emp_Address, Addressed_To, Insertion_Date from HCM_LETTER where Emp_Id=? AND "
					+ "Insertion_Date = (SELECT MAX(Insertion_Date) FROM HCM_LETTER WHERE Emp_Id = ?)";
			
			
		
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try
			{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(empid));
				ps.setInt(2, Integer.parseInt(empid));
				rs = ps.executeQuery();
				
				while(rs.next()){
					
					hcmletter.setEmpId(rs.getString("Emp_Id"));
					hcmletter.setEmpIdCapco(rs.getString("Emp_Id_Capco"));
					hcmletter.setEmpName(rs.getString("Emp_Name"));
					hcmletter.setFisEid(rs.getString("Fis_Eid"));
					hcmletter.setPassportNumber(rs.getString("Passport_Number"));
					hcmletter.setStatus(rs.getBoolean("Status"));
					hcmletter.setTypeOfLetter(rs.getString("Type_Of_Letter"));
					hcmletter.setLetterPurpose(rs.getString("Letter_Purpose"));
					hcmletter.setPurposeOtherReason(rs.getString("Purpose_Other_Reason"));
					hcmletter.setOtherVisaLetter(rs.getString("Other_Visa_Letter"));
					hcmletter.setTravelCountry(rs.getString("Travel_Country"));
					
					if(rs.getDate("Passport_From_Date") != null)
					{
						hcmletter.setPassportFromDate(new java.util.Date(rs.getDate("Passport_From_Date").getTime()));
						
					}
					else
					{
						hcmletter.setPassportFromDate(null);
						
					}
					
					if(rs.getDate("Passport_End_Date") != null)
					{
						hcmletter.setPassportEndDate(new java.util.Date(rs.getDate("Passport_End_Date").getTime()));
						
					}
					else
					{
						hcmletter.setPassportEndDate(null);
						
					}
					
					if(rs.getDate("Stay_From") != null)
					{
						hcmletter.setStayFrom(new java.util.Date(rs.getDate("Stay_From").getTime()));
					}
					else
					{
						hcmletter.setStayFrom(null);
					}
					
					if(rs.getDate("Stay_To") != null)
					{
						hcmletter.setStayTo(new java.util.Date(rs.getDate("Stay_To").getTime()));
					}
					else
					{
						hcmletter.setStayTo(null);
					}
					
				
					hcmletter.setPassportName(rs.getString("Passport_Name"));
					hcmletter.setDesignation(rs.getString("Designation"));
					hcmletter.setGender(rs.getBoolean("Gender"));
					
					
					if(rs.getDate("Cover_Letter_From") != null)
					{
						hcmletter.setCoverLetterFrom(new java.util.Date(rs.getDate("Cover_Letter_From").getTime()));
					}
					else
					{
						hcmletter.setCoverLetterFrom(null);
					}
					
					if(rs.getDate("Cover_Letter_To") != null)
					{
						hcmletter.setCoverLetterTo(new java.util.Date(rs.getDate("Cover_Letter_To").getTime()));
					}
					else
					{
						hcmletter.setCoverLetterTo(null);
					}
					
					
					hcmletter.setEmpAddress(rs.getString("Emp_Address"));
					hcmletter.setAddressedTo(rs.getString("Addressed_To"));
				
					hcmletter.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
					
				
					
				
				}
			}catch(SQLException e){
				e.printStackTrace();
			} finally{
				
				try{ps.close();}catch(SQLException e){e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}
			return  hcmletter;
			
		}
		
		
		
	public List<HcmLetterReqObj> getAllRequestsForLetters(){
		List<HcmLetterReqObj> allLetterRequests = new ArrayList<HcmLetterReqObj>();
		//TODO complete the query
		String query = "SELECT * FROM HCM_LETTER";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = dbCtrl.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				HcmLetterReqObj letterObj = new HcmLetterReqObj();
				//TODO code to get values from table
				letterObj.setEmpId(rs.getString("Emp_Id"));
				letterObj.setEmpIdCapco(rs.getString("Emp_Id_Capco"));
				letterObj.setTypeOfLetter(rs.getString("Type_Of_Letter"));
				letterObj.setLetterPurpose(rs.getString("Letter_Purpose"));
				letterObj.setPurposeOtherReason(rs.getString("Purpose_Other_Reason"));
				letterObj.setOtherVisaLetter(rs.getString("Other_Visa_Letter"));
				letterObj.setTravelCountry(rs.getString("Travel_Country"));
				
				if(rs.getDate("Passport_From_Date") != null)
				{
					letterObj.setPassportFromDate(new java.util.Date(rs.getDate("Passport_From_Date").getTime()));
					
				}
				else
				{
					letterObj.setPassportFromDate(null);
					
				}
				
				if(rs.getDate("Passport_End_Date") != null)
				{
					letterObj.setPassportEndDate(new java.util.Date(rs.getDate("Passport_End_Date").getTime()));
					
				}
				else
				{
					letterObj.setPassportEndDate(null);
					
				}
				
			
				
				if(rs.getDate("Stay_From") != null)
				{
					letterObj.setStayFrom(new java.util.Date(rs.getDate("Stay_From").getTime()));
				}
				else
				{
					letterObj.setStayFrom(null);
				}
				
				if(rs.getDate("Stay_To") != null)
				{
					letterObj.setStayTo(new java.util.Date(rs.getDate("Stay_To").getTime()));
				}
				else
				{
					letterObj.setStayTo(null);
				}
				
				letterObj.setDesignation(rs.getString("Designation"));
				letterObj.setPassportName(rs.getString("Passport_Name"));
				letterObj.setGender(rs.getBoolean("Gender"));
				
				
				if(rs.getDate("Cover_Letter_From") != null)
				{
					letterObj.setCoverLetterFrom(new java.util.Date(rs.getDate("Cover_Letter_From").getTime()));
				}
				else
				{
					letterObj.setCoverLetterFrom(null);
				}
				
				if(rs.getDate("Cover_Letter_To") != null)
				{
					letterObj.setCoverLetterTo(new java.util.Date(rs.getDate("Cover_Letter_To").getTime()));
				}
				else
				{
					letterObj.setCoverLetterTo(null);
				}
				
				letterObj.setEmpName(rs.getString("Emp_Name"));
				letterObj.setFisEid(rs.getString("Fis_Eid"));
				letterObj.setPassportNumber(rs.getString("Passport_Number"));
				letterObj.setStatus(rs.getBoolean("Status"));
				letterObj.setEmpAddress(rs.getString("Emp_Address"));
				letterObj.setAddressedTo(rs.getString("Addressed_To"));
				letterObj.setInsertedBy(rs.getString("Inserted_By"));
				letterObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
				allLetterRequests.add(letterObj);
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return allLetterRequests;
	}

	/**
	 * 
	 * @param empId
	 * @return
	 */
	/*public List<HcmLetterReqObj> getLetterRequestsForEmployee(String empId){
		List<HcmLetterReqObj> letterReqForEmp = new ArrayList<HcmLetterReqObj>();
		
		//TODO complete the query
				String query = "SELECT Emp_Id,Emp_Id_Capco, Type_Of_Letter, Letter_Purpose, Purpose_Other_Reason, "
				+ " Other_Visa_Letter, Travel_Country, Passport_From_Date, Passport_End_Date, Stay_From, Stay_To, Designation, Passport_Name, "
				+ " Gender, Cover_Letter_From, Cover_Letter_To, Emp_Name, Fis_Eid, Passport_Number, Status, "
				+ " Emp_Address, Addressed_To, "
				+ " Inserted_By, Insertion_Date FROM HCM_LETTER  WHERE Emp_Id = ?";
				
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try{
					conn = dbCtrl.getConnection();
					ps = conn.prepareStatement(query);
					
					ps.setInt(1, Integer.parseInt(empId));
					rs = ps.executeQuery(query);
					
					while(rs.next()){
						HcmLetterReqObj letterObj = new HcmLetterReqObj();
						//TODO code to get values from table
						letterObj.setEmpIdCapco(rs.getString("Emp_Id_Capco"));
						letterObj.setTypeOfLetter(rs.getString("Type_Of_Letter"));
						letterObj.setLetterPurpose(rs.getString("Letter_Purpose"));
						letterObj.setInvitePurpose(rs.getString("Invite_Purpose"));
						letterObj.setPurposeOtherReason(rs.getString("Purpose_Other_Reason"));
						letterObj.setOtherVisaLetter(rs.getString("Other_Visa_Letter"));
						letterObj.setTravelCountry(rs.getString("Travel_Country"));
						letterObj.setPassportEndDate(new java.util.Date(rs.getDate("Passport_From_Date").getTime()));
						letterObj.setPassportEndDate(new java.util.Date(rs.getDate("Passport_From_Date").getTime()));
						letterObj.setStayFrom(new java.util.Date(rs.getDate("Stay_From").getTime()));
						letterObj.setStayTo(new java.util.Date(rs.getDate("Stay_To").getTime()));
						letterObj.setDesignation(rs.getString("Designation"));
						letterObj.setPassportName(rs.getString("Passport_Name"));
						letterObj.setGender(rs.getBoolean("Gender"));
						letterObj.setCoverLetterFrom(new java.util.Date(rs.getDate("Cover_Letter_From").getTime()));
						letterObj.setCoverLetterTo(new java.util.Date(rs.getDate("Cover_Letter_To").getTime()));
						letterObj.setEmpName(rs.getString("Emp_Name"));
						letterObj.setFisEid(rs.getString("Fis_Eid"));
						letterObj.setPassportNumber(rs.getString("Passport_Number"));
						letterObj.setStatus(rs.getBoolean("Status"));
						letterObj.setEmpAddress(rs.getString("Emp_Address"));
						letterObj.setAddressedTo(rs.getString("Addressed_To"));
						letterObj.setInsertedBy(rs.getString("Inserted_By"));
						letterObj.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
					

						letterReqForEmp.add(letterObj);
					}

				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try {rs.close();} catch (SQLException e) {e.printStackTrace();}
					try {st.close();} catch (SQLException e) {e.printStackTrace();}
					dbCtrl.closeConnection(conn);
				}
		
		return letterReqForEmp;
	}*/
	
	/**
	 * 
	 * @param letterRequest
	 */
	public boolean insertLetterRequest(HcmLetterReqObj letterRequest){
		//TODO add the query
		boolean success = true;
		
		System.out.println("inside the method");
		String query = "INSERT INTO HCM_LETTER (Emp_Id,Emp_Id_Capco, Emp_Name, Fis_Eid, Passport_Number, Status, Type_Of_Letter, Letter_Purpose,  Purpose_Other_Reason, "
				+ " Other_Visa_Letter, Travel_Country, Passport_From_Date, Passport_End_Date, Stay_From, Stay_To, Passport_Name, Designation, "
				+ " Gender, Cover_Letter_From, Cover_Letter_To,  "
				+ " Emp_Address, Addressed_To,  Inserted_By)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = dbCtrl.getConnection();
			System.out.println("inside connection"+conn);
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(letterRequest.getEmpId()));
			ps.setString(2, letterRequest.getEmpIdCapco());
			ps.setString(3, letterRequest.getEmpName());
			ps.setString(4, letterRequest.getFisEid());
			ps.setString(5, letterRequest.getPassportNumber());
			ps.setBoolean(6, letterRequest.getStatus());
			ps.setString(7, letterRequest.getTypeOfLetter());
			ps.setString(8, letterRequest.getLetterPurpose());
			ps.setString(9, letterRequest.getPurposeOtherReason());
			ps.setString(10, letterRequest.getOtherVisaLetter());
			ps.setString(11, letterRequest.getTravelCountry());
			
			
			
			
			
			if(letterRequest.getTravelCountry() == null)
			 {
				 	ps.setDate(12, null);
					ps.setDate(13, null);
					ps.setDate(14, null);
					ps.setDate(15, null);
					ps.setString(16, null);
					ps.setString(17, null);
					ps.setBoolean(18, false);
					
			 }
			 else
			 {
				 	ps.setDate(12, new Date(letterRequest.getPassportFromDate().getTime()));
					ps.setDate(13, new Date(letterRequest.getPassportEndDate().getTime()));
					ps.setDate(14, new Date(letterRequest.getStayFrom().getTime()));
					ps.setDate(15, new Date(letterRequest.getStayTo().getTime()));
					ps.setString(16, letterRequest.getPassportName());
					ps.setString(17, letterRequest.getDesignation());
					ps.setBoolean(18, letterRequest.getGender());
			 }
			
			if(letterRequest.getCoverLetterFrom() != null)
			{
				ps.setDate(19, new Date(letterRequest.getCoverLetterFrom().getTime()));
			}
			else
			{
				ps.setDate(19, null);
			}
			
			if(letterRequest.getCoverLetterTo() != null)
			{
				ps.setDate(20, new Date(letterRequest.getCoverLetterTo().getTime()));
			}
			else
			{
				ps.setDate(20, null);
			}
			
			
			
			/*System.out.println("comes in the cover letter"+letterRequest.getTypeOfLetter());
			switch (letterRequest.getTypeOfLetter()) {
			case "Employment Letter":
				ps.setDate(19, null);
				ps.setDate(20, null);
				break;
			case "Address Proof":
				ps.setDate(19, null);
				ps.setDate(20, null);
				break;
			case "Invite Letter":
				ps.setDate(19, null);
				ps.setDate(20, null);
				break;
			case "Other Visa Letter":
				ps.setDate(19, null);
				ps.setDate(20, null);
				break;	
			default:
				ps.setDate(19, new Date(letterRequest.getCoverLetterFrom().getTime()));
				ps.setDate(20, new Date(letterRequest.getCoverLetterTo().getTime()));
				break;
			}
			*/
			
			ps.setString(21, letterRequest.getEmpAddress());
			ps.setString(22, letterRequest.getAddressedTo());
			ps.setString(23, letterRequest.getInsertedBy());
			
			
			ps.execute();
			
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return success;

	}

	
		public boolean getDuplicate(String emp, HcmLetterReqObj hcm){
		System.out.println("Inside getDuplicate ()");
		boolean success = false;
		
		String query = "SELECT Emp_id FROM HCM_LETTER  WHERE Emp_Id ="+"'"+emp+"'";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = dbCtrl.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				return success=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		System.out.println("The boolenan values @ :"+success);
		return success;
		
	}
		
			//Method to update the HCM letter
		public boolean updateLetterRequest(String emp, HcmLetterReqObj letterReqObj){
			System.out.println("Inside the update()");
			boolean success = true;	
			String queryupdate = "Update HCM_LETTER set Emp_Id_Capco=?, Emp_Name=?, Fis_Eid=?, Passport_Number=?, Status=?,"
					+ " Type_Of_Letter=?, Letter_Purpose=?,  Purpose_Other_Reason=?, Other_Visa_Letter=?, Travel_Country=?,"
					+ " Passport_From_Date=?, Passport_End_Date=?, Stay_From=?, Stay_To=?, Passport_Name=?,Designation=?,"
					+ " Gender=?, Cover_Letter_From=?, Cover_Letter_To=?,Emp_Address=?, Addressed_To=? where Emp_Id=?";
			
			
			Connection conn = null;
			PreparedStatement psupdate =null;
			
			try{
				
				conn = dbCtrl.getConnection();
				System.out.println("inside connection"+conn);
				psupdate = conn.prepareStatement(queryupdate);
			
				
				psupdate.setString(1, letterReqObj.getEmpIdCapco());
				psupdate.setString(2, letterReqObj.getEmpName());
				psupdate.setString(3, letterReqObj.getPassportNumber());
				psupdate.setString(4, letterReqObj.getFisEid());
				psupdate.setBoolean(5, letterReqObj.getStatus());
				psupdate.setString(6, letterReqObj.getTypeOfLetter());
				psupdate.setString(7, letterReqObj.getLetterPurpose());
				psupdate.setString(8, letterReqObj.getPurposeOtherReason());
				psupdate.setString(9, letterReqObj.getOtherVisaLetter());
				psupdate.setString(10, letterReqObj.getTravelCountry());
				 if(letterReqObj.getTravelCountry() == null)
				 {
					 psupdate.setDate(11, null);
					 psupdate.setDate(12, null);
					 psupdate.setDate(13, null);
					 psupdate.setDate(14, null);
					 psupdate.setString(15, null);
					 psupdate.setString(16, null);
					 psupdate.setBoolean(17, false);
						
				 }
				 else
				 {
					 psupdate.setDate(11, new Date(letterReqObj.getPassportFromDate().getTime()));
					 psupdate.setDate(12, new Date(letterReqObj.getPassportEndDate().getTime()));
					 psupdate.setDate(13, new Date(letterReqObj.getStayFrom().getTime()));
					 psupdate.setDate(14, new Date(letterReqObj.getStayTo().getTime()));
					 psupdate.setString(15, letterReqObj.getPassportName());
					 psupdate.setString(16, letterReqObj.getDesignation());
					 psupdate.setBoolean(17, letterReqObj.getGender());
				 }
				 
				 if(letterReqObj.getCoverLetterFrom() != null)
					{
					 psupdate.setDate(18, new Date(letterReqObj.getCoverLetterFrom().getTime()));
					}
					else
					{
						psupdate.setDate(18, null);
					}
					
					if(letterReqObj.getCoverLetterTo() != null)
					{
						psupdate.setDate(19, new Date(letterReqObj.getCoverLetterTo().getTime()));
					}
					else
					{
						psupdate.setDate(19, null);
					}
					
				 
				/*System.out.println("comes in the cover letter"+letterReqObj.getTypeOfLetter());
				switch (letterReqObj.getTypeOfLetter()) {
				case "Employment Letter":
					psupdate.setDate(18, null);
					psupdate.setDate(19, null);
					break;
				case "Address Proof":
					psupdate.setDate(18, null);
					psupdate.setDate(19, null);
					break;
				case "Invite Letter":
					psupdate.setDate(18, null);
					psupdate.setDate(19, null);
					break;
				case "Other Visa Letter":
					psupdate.setDate(18, null);
					psupdate.setDate(19, null);
					break;		
				default:
					psupdate.setDate(18, new Date(letterReqObj.getCoverLetterFrom().getTime()));
					psupdate.setDate(19, new Date(letterReqObj.getCoverLetterTo().getTime()));
					break;
				}*/
				
				psupdate.setString(20, letterReqObj.getEmpAddress());
				psupdate.setString(21, letterReqObj.getAddressedTo());
				psupdate.setInt(22, Integer.parseInt(emp));
				
			
				psupdate.executeUpdate();
				
								
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {psupdate.close();} catch (SQLException e) {e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}
			return success;
			
		}

		public List<HcmLetterReqObj> getHcmDetails(String empid) {
			
			List<HcmLetterReqObj>	hcm = new ArrayList<HcmLetterReqObj>();
			
			String query = "select Emp_Id,Emp_Id_Capco,Emp_Name,Fis_Eid,Passport_Number, Status,Type_Of_Letter, Letter_Purpose,"
					+ "  Purpose_Other_Reason, Other_Visa_Letter, Travel_Country, Passport_From_Date, Passport_End_Date, "
					+ "Stay_From, Stay_To, Passport_Name, Designation,Gender, Cover_Letter_From, Cover_Letter_To,"
					+ "Emp_Address, Addressed_To, Insertion_Date from HCM_LETTER where Emp_Id=?  ";
			
			
		
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
					HcmLetterReqObj hcmletter = new HcmLetterReqObj();
					hcmletter.setEmpId(rs.getString("Emp_Id"));
					hcmletter.setEmpIdCapco(rs.getString("Emp_Id_Capco"));
					hcmletter.setEmpName(rs.getString("Emp_Name"));
					hcmletter.setFisEid(rs.getString("Fis_Eid"));
					hcmletter.setPassportNumber(rs.getString("Passport_Number"));
					hcmletter.setStatus(rs.getBoolean("Status"));
					hcmletter.setTypeOfLetter(rs.getString("Type_Of_Letter"));
					hcmletter.setLetterPurpose(rs.getString("Letter_Purpose"));
					hcmletter.setPurposeOtherReason(rs.getString("Purpose_Other_Reason"));
					hcmletter.setOtherVisaLetter(rs.getString("Other_Visa_Letter"));
					hcmletter.setTravelCountry(rs.getString("Travel_Country"));
					
					if(rs.getDate("Passport_From_Date") != null)
					{
						hcmletter.setPassportFromDate(new java.util.Date(rs.getDate("Passport_From_Date").getTime()));
						
					}
					else
					{
						hcmletter.setPassportFromDate(null);
						
					}
					
					if(rs.getDate("Passport_End_Date") != null)
					{
						hcmletter.setPassportEndDate(new java.util.Date(rs.getDate("Passport_End_Date").getTime()));
						
					}
					else
					{
						hcmletter.setPassportEndDate(null);
						
					}
					
					if(rs.getDate("Stay_From") != null)
					{
						hcmletter.setStayFrom(new java.util.Date(rs.getDate("Stay_From").getTime()));
					}
					else
					{
						hcmletter.setStayFrom(null);
					}
					
					if(rs.getDate("Stay_To") != null)
					{
						hcmletter.setStayTo(new java.util.Date(rs.getDate("Stay_To").getTime()));
					}
					else
					{
						hcmletter.setStayTo(null);
					}
					
				
					hcmletter.setPassportName(rs.getString("Passport_Name"));
					hcmletter.setDesignation(rs.getString("Designation"));
					hcmletter.setGender(rs.getBoolean("Gender"));
					
					
					if(rs.getDate("Cover_Letter_From") != null)
					{
						hcmletter.setCoverLetterFrom(new java.util.Date(rs.getDate("Cover_Letter_From").getTime()));
					}
					else
					{
						hcmletter.setCoverLetterFrom(null);
					}
					
					if(rs.getDate("Cover_Letter_To") != null)
					{
						hcmletter.setCoverLetterTo(new java.util.Date(rs.getDate("Cover_Letter_To").getTime()));
					}
					else
					{
						hcmletter.setCoverLetterTo(null);
					}
					
					
					hcmletter.setEmpAddress(rs.getString("Emp_Address"));
					hcmletter.setAddressedTo(rs.getString("Addressed_To"));
				
					hcmletter.setInsertedDate(new java.util.Date(rs.getDate("Insertion_Date").getTime()));
					
					hcm.add(hcmletter);
					
				
				}
			}catch(SQLException e){
				e.printStackTrace();
			} finally{
				
				try{ps.close();}catch(SQLException e){e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}
			return  hcm;
			
		}
}