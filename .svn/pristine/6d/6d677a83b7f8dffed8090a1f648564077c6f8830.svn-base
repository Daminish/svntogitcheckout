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
import com.capco.hcm.model.EmployeeBasicInfoObj;
import com.capco.hcm.utils.StringUtils;

/**
 * @author Mohit Gangil
 *
 */

public class EmployeeBasicInfoDao implements Serializable {
	private static final long serialVersionUID = 7765326596616754228L;
	
	private DatabaseController dbCtrl;
	
	public DatabaseController getDbCtrl() {
		return dbCtrl;
	}
	public void setDbCtrl(DatabaseController dbCtrl) {
		this.dbCtrl = dbCtrl;
	}
	
	
	public void updateEmpdetails(List<EmployeeBasicInfoObj> empobj)
	{
		for(EmployeeBasicInfoObj obj:empobj)
		{
			if(null != obj.getEmpId() ||  !"0".equals(obj.getEmpId()))
			{
				
				System.out.println("Object main kya hain bai "+ obj);
				
				EmployeeBasicInfoObj basicInfo = getBasicInfo(obj.getEmpId());
				
				
				if(basicInfo !=null && basicInfo.getEmpId()!=null && Integer.parseInt(basicInfo.getEmpId())>0)
				{
					System.out.println("Comes under update"+obj);
					updateBasic(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertBasicInfo(obj);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param basicInfo
	 * @param insertedBy
	 * @return
	 */
	public boolean insertBasicInfo(EmployeeBasicInfoObj basicInfo){//, String insertedBy){
		boolean success = true;
		String query = "INSERT INTO EMP_INFO (Emp_Id, Emp_Name, Sec_Emp_Id, File_No, Date_Of_Birth, Joining_Date, Emp_Status, Gender, HcBp, "
				+ " Capco_Login_Id, Designation, Atp, Service, Project,Type_Of_Employment, Billing_Status, Fte_Contractor, Start_Date, "
				+ " Official_Email_Id, Personal_Email_Id, Contact_Number, House, Deleted,Inserted_By) "
				+ " VALUES "
				+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, false,?) ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);

			ps.setInt(1, Integer.parseInt(basicInfo.getEmpId()));
			ps.setString(2, basicInfo.getEmpName());
			ps.setString(3, basicInfo.getSecondaryEmpId());
			ps.setString(4, basicInfo.getFileNo());
			
			if(basicInfo.getDateOfBirth() != null)
			{
				ps.setDate(5, new Date(basicInfo.getDateOfBirth().getTime()));
				
			}
			else
			{
				ps.setDate(5, null);
				
			}
			
			if(basicInfo.getJoiningDate() != null)
			{
				ps.setDate(6, new Date(basicInfo.getJoiningDate().getTime()));
				
			}
			else
			{
				ps.setDate(6, null);
				
			}
			
			ps.setString(7, basicInfo.getEmployeeStatus());
			ps.setBoolean(8, basicInfo.getGender());
			ps.setString(9, basicInfo.getHcBpId());
			ps.setString(10, basicInfo.getLoginId());
			ps.setString(11, basicInfo.getDesignation());
			ps.setBoolean(12, basicInfo.getAtpB());
			ps.setBoolean(13, basicInfo.getService());
			ps.setString(14, basicInfo.getProject());
			ps.setString(15, basicInfo.getTypeOfEmployment());
			ps.setBoolean(16, basicInfo.getBillingStatus());
			ps.setBoolean(17, basicInfo.getFteOrContractor());
			
			if(basicInfo.getStartDate() != null)
			{
				ps.setDate(18, new Date(basicInfo.getStartDate().getTime()));
				
			}
			else
			{
				ps.setDate(18, null);
				
			}
			ps.setString(19, basicInfo.getOfficialEmailId());
			ps.setString(20, basicInfo.getPerosnalEmailId());
			ps.setString(21, basicInfo.getContactNumber());
			ps.setString(22, basicInfo.getAssignedHouse());
			ps.setString(23, basicInfo.getInsertedBy());
			
			
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
	 * @param empId
	 * @param employeeName
	 * @param secondaryEmpId
	 * @return
	 */
	public List<EmployeeBasicInfoObj> searchForEmployeeBasicInfo(String empId, String employeeName, String secondaryEmpId,
			String employeeStatus, String designation, String project){
		
		List<EmployeeBasicInfoObj> filteredEmployeeList = new ArrayList<EmployeeBasicInfoObj>();
		StringBuilder query = new StringBuilder("SELECT Emp_Id, Emp_Name, Sec_Emp_Id, Designation,Project, Joining_Date, Official_Email_Id, Emp_Status "
				+ " FROM EMP_INFO ");

		if(StringUtils.isNullOrEmpty(empId) && StringUtils.isNullOrEmpty(employeeName) 
				&& StringUtils.isNullOrEmpty(secondaryEmpId) && StringUtils.isNullOrEmpty(designation) && StringUtils.isNullOrEmpty(project) 
				&& employeeStatus == null){
		}else{
			query = query.append(" WHERE ");
			
			boolean addAndKeyword = false;
			if(!StringUtils.isNullOrEmpty(empId)){
				query = query.append(" CAST(Emp_Id AS CHAR(9)) like '%").append(empId).append("%' ");
				addAndKeyword = true;
			}
			if(!StringUtils.isNullOrEmpty(employeeName)){
				if(addAndKeyword)
					query = query.append(" AND ");
				else
					addAndKeyword = true;
			
				query = query.append(" UPPER(Emp_Name) like '%").append(employeeName.toUpperCase()).append("%' ");
			}
			
			if(!StringUtils.isNullOrEmpty(secondaryEmpId)){
				if(addAndKeyword)
					query = query.append(" AND ");
				else
					addAndKeyword = true;
			
				query = query.append(" Sec_Emp_Id like '%").append(secondaryEmpId.toUpperCase()).append("%' ");
			}
			
			if(!StringUtils.isNullOrEmpty(designation)){
				if(addAndKeyword)
					query = query.append(" AND ");
				else
					addAndKeyword = true;
			
				query = query.append(" Designation like '%").append(designation.toUpperCase()).append("%' ");
			}
			
			if(!StringUtils.isNullOrEmpty(project)){
				if(addAndKeyword)
					query = query.append(" AND ");
				else
					addAndKeyword = true;
			
				query = query.append(" Project like '%").append(project.toUpperCase()).append("%' ");
			}
			
			if(StringUtils.isNullOrEmpty(employeeStatus) || !employeeStatus.equals("NA")){
				if(addAndKeyword)
					query = query.append(" AND ");
				query = query.append(" Emp_Status = '").append(employeeStatus.trim()).append("'");
			}
		}

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = dbCtrl.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());

			while(rs.next()){
				EmployeeBasicInfoObj basicInfo = new EmployeeBasicInfoObj();
				
				basicInfo.setEmpId("" + rs.getInt("Emp_Id"));
				basicInfo.setEmpName(rs.getString("Emp_Name"));
				basicInfo.setSecondaryEmpId(rs.getString("Sec_Emp_Id"));
				basicInfo.setDesignation(rs.getString("Designation"));
				basicInfo.setProject(rs.getString("Project"));
				
				if(rs.getDate("Joining_Date") != null)
				{
					basicInfo.setJoiningDate(new java.util.Date(rs.getDate("Joining_Date").getTime()));
					
				}
				else
				{
					basicInfo.setJoiningDate(null);
					
				}
				basicInfo.setOfficialEmailId(rs.getString("Official_Email_Id"));
				basicInfo.setEmployeeStatus(rs.getString("Emp_Status"));

				filteredEmployeeList.add(basicInfo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return filteredEmployeeList;
	}

	/**
	 * 
	 * @return
	 */
	public EmployeeBasicInfoObj getBasicInfo(String employeeId){
		EmployeeBasicInfoObj basicInfo = new EmployeeBasicInfoObj();
		String query = "SELECT Emp_Id, Emp_Name, Sec_Emp_Id, File_No, Date_Of_Birth, Joining_Date, Emp_Status, Gender, HcBp, "
				+ " Capco_Login_Id, Designation, Atp, Service, Project,Type_Of_Employment, Billing_Status, Fte_Contractor, Start_Date, "
				+ " Official_Email_Id, Personal_Email_Id, Contact_Number, House, Inserted_By"
				+ " FROM EMP_INFO "
				+ " WHERE Emp_Id = ? AND Insertion_Date = (SELECT MAX(Insertion_Date) FROM EMP_INFO WHERE Emp_Id = ?)";

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
				basicInfo.setEmpId("" + rs.getInt("Emp_Id"));
				basicInfo.setEmpName(rs.getString("Emp_Name"));
				basicInfo.setSecondaryEmpId(rs.getString("Sec_Emp_Id"));
				basicInfo.setFileNo(rs.getString("File_No"));
				
				if(rs.getDate("Date_Of_Birth") != null)
				{
					basicInfo.setDateOfBirth(new java.util.Date(rs.getDate("Date_Of_Birth").getTime()));
				}
				else
				{
					basicInfo.setDateOfBirth(null);
				}
			
				if(rs.getDate("Joining_Date") != null)
				{
					basicInfo.setJoiningDate(new java.util.Date(rs.getDate("Joining_Date").getTime()));
				}
				else
				{
					basicInfo.setJoiningDate(null);
				}
				
				basicInfo.setEmployeeStatus(rs.getString("Emp_Status"));
				basicInfo.setGender(rs.getBoolean("Gender"));
				basicInfo.setHcBpId(rs.getString("HcBp"));
				basicInfo.setLoginId(rs.getString("Capco_Login_Id"));
				basicInfo.setDesignation(rs.getString("Designation"));
				basicInfo.setAtpB(rs.getBoolean("Atp"));
				basicInfo.setService(rs.getBoolean("Service"));
				basicInfo.setProject(rs.getString("Project"));
				basicInfo.setProject(rs.getString("Type_Of_Employment"));
				basicInfo.setBillingStatus(rs.getBoolean("Billing_Status"));
				basicInfo.setFteOrContractor(rs.getBoolean("Fte_Contractor"));
				//basicInfo.set(new java.util.Date(new java.util.Date(rs.getDate("Previous_Last_Working_Date")); //TODO check this field
				if(rs.getDate("Start_Date") != null)
				{
					basicInfo.setStartDate(new java.util.Date(rs.getDate("Start_Date").getTime()));
				}
				else
				{
					basicInfo.setStartDate(null);
				}
			
				basicInfo.setOfficialEmailId(rs.getString("Official_Email_Id"));
				basicInfo.setPerosnalEmailId(rs.getString("Personal_Email_Id"));
				basicInfo.setContactNumber(rs.getString("Contact_Number"));
				basicInfo.setAssignedHouse(rs.getString("House"));
				//TODO ADD the field inserted by here
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return basicInfo;
	}
	
	/*public List<EmployeeBasicInfoObj> getBasicInfoRevHistory(String employeeId){
		List<EmployeeBasicInfoObj> basicInfoRevHist = new ArrayList<EmployeeBasicInfoObj>();
		//TODO complete the code
		String query = "SELECT Emp_Id, Emp_Name, Sec_Emp_Id, File_No, Date_Of_Birth, Joining_Date, Emp_Status, Gender, HcBp, "
				+ " Capco_Login_Id, Designation, Atp, Service, Project,Type_Of_Employment, Billing_Status, Fte_Contractor, Previous_Last_Working_Date, "
				+ " Official_Email_Id, Personal_Email_Id, Contact_Number, House, Inserted_By"
				+ " FROM EMP_INFO "
				+ " WHERE Emp_Id = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);

			ps.setInt(1, Integer.parseInt(employeeId));

			rs = ps.executeQuery();

			while(rs.next()){
				EmployeeBasicInfoObj basicInfo = new EmployeeBasicInfoObj();
				basicInfo.setEmpId("" + rs.getInt("Emp_Id"));
				basicInfo.setEmpName(rs.getString("Emp_Name"));
				basicInfo.setSecondaryEmpId(rs.getString("Sec_Emp_Id"));
				basicInfo.setFileNo(rs.getString("File_No"));
				basicInfo.setDateOfBirth(new java.util.Date(rs.getDate("Date_Of_Birth").getTime()));
				basicInfo.setJoiningDate(new java.util.Date(rs.getDate("Joining_Date").getTime()));
				basicInfo.setEmployeeStatus(rs.getString("Emp_Status"));
				basicInfo.setGender(rs.getBoolean("Gender"));
				basicInfo.setHcBpId(rs.getString("HcBp"));
				basicInfo.setLoginId(rs.getString("Capco_Login_Id"));
				basicInfo.setDesignation(rs.getString("Designation"));
				basicInfo.setAtpB(rs.getBoolean("Atp"));
				basicInfo.setService(rs.getBoolean("Service"));
				basicInfo.setProject(rs.getString("Project"));
				basicInfo.setProject(rs.getString("Type_Of_Employment"));
				basicInfo.setBillingStatus(rs.getBoolean("Billing_Status"));
				basicInfo.setFteOrContractor(rs.getBoolean("Fte_Contractor"));
				//basicInfo.set(new java.util.Date(new java.util.Date(rs.getDate("Previous_Last_Working_Date")); //TODO check this field
				basicInfo.setOfficialEmailId(rs.getString("Official_Email_Id"));
				basicInfo.setPerosnalEmailId(rs.getString("Personal_Email_Id"));
				basicInfo.setContactNumber(rs.getString("Contact_Number"));
				basicInfo.setAssignedHouse(rs.getString("House"));
				//TODO ADD the field "inserted by" here
				basicInfoRevHist.add(basicInfo);
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return basicInfoRevHist;
	}
*/
	

	/*public EmployeeBasicInfoObj getBasicInfo(String employeeId){
		EmployeeBasicInfoObj basicInfo = new EmployeeBasicInfoObj();
		String query = "SELECT Emp_Id, Emp_Name, Sec_Emp_Id, File_No, Date_Of_Birth, Joining_Date, Emp_Status, Gender, HcBp, "
				+ " Capco_Login_Id, Designation, Atp, Service, Project,Type_Of_Employment, Billing_Status, Fte_Contractor, Previous_Last_Working_Date, "
				+ " Official_Email_Id, Personal_Email_Id, Contact_Number, House, Inserted_By"
				+ " FROM EMP_INFO "
				+ " WHERE Emp_Id = ? AND Insertion_Date = (SELECT MAX(Insertion_Date) FROM EMP_INFO WHERE Emp_Id = ?)";

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
				basicInfo.setEmpId("" + rs.getInt("Emp_Id"));
				basicInfo.setEmpName(rs.getString("Emp_Name"));
				basicInfo.setFileNo(rs.getString("File_No"));
				basicInfo.setDateOfBirth(new java.util.Date(rs.getDate("Date_Of_Birth").getTime()));
				basicInfo.setJoiningDate(new java.util.Date(rs.getDate("Joining_Date").getTime()));
				basicInfo.setEmployeeStatus(rs.getString("Emp_Status"));
				basicInfo.setGender(rs.getBoolean("Gender"));
				basicInfo.setHcBpId(rs.getString("HcBp"));
				basicInfo.setLoginId(rs.getString("Capco_Login_Id"));
				basicInfo.setDesignation(rs.getString("Designation"));
				basicInfo.setAtpB(rs.getBoolean("Atp"));
				basicInfo.setService(rs.getBoolean("Service"));
				basicInfo.setProject(rs.getString("Project"));
				basicInfo.setProject(rs.getString("Type_Of_Employment"));
				basicInfo.setBillingStatus(rs.getBoolean("Billing_Status"));
				basicInfo.setFteOrContractor(rs.getBoolean("Fte_Contractor"));
				//basicInfo.set(new java.util.Date(new java.util.Date(rs.getDate("Previous_Last_Working_Date")); //TODO check this field
				basicInfo.setOfficialEmailId(rs.getString("Official_Email_Id"));
				basicInfo.setPerosnalEmailId(rs.getString("Personal_Email_Id"));
				basicInfo.setContactNumber(rs.getString("Contact_Number"));
				basicInfo.setAssignedHouse(rs.getString("House"));
				//TODO ADD the field inserted by here
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return basicInfo;
	}*/
	
	

	public List<String> getAllDesignation() {
		
		List<String> categories = new ArrayList<String>();
		String query = "SELECT DISTINCT Field_Name FROM HCM_STATIC_TABLE WHERE Field_Category = 'DESIGNATION'";
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

	public List<EmployeeBasicInfoObj> getBasicInfoDetails() {
		

		List<EmployeeBasicInfoObj> employees = new ArrayList<EmployeeBasicInfoObj>();
		
		String query = "SELECT Emp_Id, Emp_Name, Sec_Emp_Id, File_No, Date_Of_Birth, Joining_Date, Emp_Status, Gender, HcBp, "
				+ " Capco_Login_Id, Designation, Atp, Service, Project,Type_Of_Employment, Billing_Status, Fte_Contractor, Start_Date, "
				+ " Official_Email_Id, Personal_Email_Id, Contact_Number, House,Deleted, Inserted_By,Insertion_Date  FROM EMP_INFO";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				EmployeeBasicInfoObj emp = new EmployeeBasicInfoObj();
				emp.setEmpId("" + rs.getInt("Emp_Id"));
				emp.setEmpName(rs.getString("Emp_Name"));
				emp.setSecondaryEmpId(rs.getString("Sec_Emp_Id"));
				emp.setFileNo(rs.getString("File_No"));
				
				if(rs.getDate("Date_Of_Birth") != null)
				{
					emp.setDateOfBirth(new java.util.Date(rs.getDate("Date_Of_Birth").getTime()));
				}
				else
				{
					emp.setDateOfBirth(null);
				}
			
				if(rs.getDate("Joining_Date") != null)
				{
					emp.setJoiningDate(new java.util.Date(rs.getDate("Joining_Date").getTime()));
				}
				else
				{
					emp.setJoiningDate(null);
				}
				
				emp.setEmployeeStatus(rs.getString("Emp_Status"));
				emp.setGender(rs.getBoolean("Gender"));
				emp.setHcBpId(rs.getString("HcBp"));
				emp.setLoginId(rs.getString("Capco_Login_Id"));
				emp.setDesignation(rs.getString("Designation"));
				emp.setAtpB(rs.getBoolean("Atp"));
				emp.setService(rs.getBoolean("Service"));
				emp.setProject(rs.getString("Project"));
				emp.setTypeOfEmployment(rs.getString("Type_Of_Employment"));
				emp.setBillingStatus(rs.getBoolean("Billing_Status"));
				emp.setFteOrContractor(rs.getBoolean("Fte_Contractor"));
			
				if(rs.getDate("Start_Date") != null)
				{
					emp.setStartDate(new java.util.Date(rs.getDate("Start_Date").getTime()));
					
				}
				else
				{
					emp.setStartDate(null);
					
				}
				emp.setOfficialEmailId(rs.getString("Official_Email_Id"));
				emp.setPerosnalEmailId(rs.getString("Personal_Email_Id"));
				emp.setContactNumber(rs.getString("Contact_Number"));
				emp.setAssignedHouse(rs.getString("House"));
				emp.setDeletedB(rs.getBoolean("Deleted"));
				emp.setInsertedBy(rs.getString("Inserted_By"));
				emp.setInsertedDate((new java.util.Date(rs.getDate("Insertion_Date").getTime())));
				
				
				employees.add(emp);
				
				
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return  employees;
		
	
	}

	public boolean updateBasic(EmployeeBasicInfoObj basic) {
		
		System.out.println(basic);
		boolean success = true;
		
		
		String query = "UPDATE EMP_INFO SET Emp_Name=?, Sec_Emp_Id=?, File_No=?, Date_Of_Birth=?, Joining_Date=?,"
				+ " Emp_Status=?, Gender=?, HcBp=?,Capco_Login_Id=?, Designation=?, Atp=?, Service=?, Project=?,"
				+ "Type_Of_Employment=?, Billing_Status=?, Fte_Contractor=?, Start_Date=?,Official_Email_Id=?,"
				+ " Personal_Email_Id=?, Contact_Number=?, House=? where Emp_Id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, basic.getEmpName());
			ps.setString(2, basic.getSecondaryEmpId());
			ps.setString(3, basic.getFileNo());
			
			if(basic.getDateOfBirth() != null)
			{
				ps.setDate(4, new Date(basic.getDateOfBirth().getTime()));
				
			}
			else
			{
				ps.setDate(4, null);
				
			}
			
			if(basic.getJoiningDate() != null)
			{
				ps.setDate(5, new Date(basic.getJoiningDate().getTime()));
				
			}
			else
			{
				ps.setDate(5, null);
				
			}
			
		
			ps.setString(6, basic.getEmployeeStatus());
			ps.setBoolean(7, basic.getGender());
			ps.setString(8, basic.getHcBpId());
			ps.setString(9, basic.getLoginId());
			ps.setString(10, basic.getDesignation());
			ps.setBoolean(11, basic.getAtpB());
			ps.setBoolean(12, basic.getService());
			ps.setString(13, basic.getProject());
			ps.setString(14, basic.getTypeOfEmployment());
			ps.setBoolean(15, basic.getBillingStatus());
			ps.setBoolean(16, basic.getFteOrContractor());
		
			if(basic.getStartDate() != null)
			{
				ps.setDate(17, new Date(basic.getStartDate().getTime()));
			}
			else
			{
				ps.setDate(17, null);
			}

			
			ps.setString(18, basic.getOfficialEmailId());
			ps.setString(19, basic.getPerosnalEmailId());
			ps.setString(20, basic.getContactNumber());
			ps.setString(21, basic.getAssignedHouse());
			ps.setInt(22, Integer.parseInt(basic.getEmpId()));
			
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
		
	}

	public boolean deleteFieldForBasicHistory(EmployeeBasicInfoObj basic) {
		
		
		boolean success = true;
		
		String query = "UPDATE EMP_INFO SET Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1,Integer.parseInt(basic.getEmpId()));
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
