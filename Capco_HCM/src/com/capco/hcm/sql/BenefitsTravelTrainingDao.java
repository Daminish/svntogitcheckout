package com.capco.hcm.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capco.hcm.controller.DatabaseController;
import com.capco.hcm.model.EmployeeBasicInfoObj;
import com.capco.hcm.model.JoiningBenefitsInfoObj;
import com.capco.hcm.model.SkillSetTrainingInfoObj;
import com.capco.hcm.model.TravelBondInfoObj;
/**
 * @author Mohit Gangil
 *
 */
public class BenefitsTravelTrainingDao implements Serializable {
	private static final long serialVersionUID = 7038766950801529801L;

	private DatabaseController dbCtrl;

	public DatabaseController getDbCtrl() {
		return dbCtrl;
	}
	public void setDbCtrl(DatabaseController dbCtrl) {
		this.dbCtrl = dbCtrl;
	}

	
	public void updateEmpTraveldetails(List<TravelBondInfoObj> empobj)
	{
		for(TravelBondInfoObj obj:empobj)
		{
			if(null != obj.getEmpId() ||  !"0".equals(obj.getEmpId()))
			{
				
				System.out.println("Object main kya hain bai "+ obj);
				
				TravelBondInfoObj travelInfo = getTravelInfo(obj.getEmpId());
				
				
				if(travelInfo !=null && travelInfo.getEmpId()!=null && Integer.parseInt(travelInfo.getEmpId())>0)
				{
					System.out.println("Comes under update"+obj);
					updatedValue(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertTravelBondDetails(obj);
				}
			}
		}
	}
	
	
	public void updateEmpSkilldetails(List<SkillSetTrainingInfoObj> empobj)
	{
		for(SkillSetTrainingInfoObj obj:empobj)
		{
			if(null != obj.getEmpId() ||  !"0".equals(obj.getEmpId()))
			{
				
				System.out.println("Object main kya hain bai "+ obj);
				
				SkillSetTrainingInfoObj skillInfo = getSkillInfo(obj.getEmpId());
				
				
				if(skillInfo !=null && skillInfo.getEmpId()!=null && Integer.parseInt(skillInfo.getEmpId())>0)
				{
					System.out.println("Comes under update"+obj);
					updatedSkillValue(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertSkillsetDetails(obj);
				}
			}
		}
	}
	
	public void updateEmpJoningdetails(List<JoiningBenefitsInfoObj> empobj)
	{
		for(JoiningBenefitsInfoObj obj:empobj)
		{
			if(null != obj.getEmpId() ||  !"0".equals(obj.getEmpId()))
			{
				
				System.out.println("Object main kya hain bai "+ obj);
				
				JoiningBenefitsInfoObj joiningInfo = getBenefitInfo(obj.getEmpId());
				
				
				if(joiningInfo !=null && joiningInfo.getEmpId()!=null && Integer.parseInt(joiningInfo.getEmpId())>0)
				{
					System.out.println("Comes under update"+obj);
					updatedBenefitValue(obj);
				}
				else
				{
					System.out.println("Comes under insert"+obj);
					
					insertBenefitsDetails(obj);
				}
			}
		}
	}
	
	
	
	public JoiningBenefitsInfoObj getBenefitInfo(String empid) {
		
	JoiningBenefitsInfoObj join = new JoiningBenefitsInfoObj();
		
		String query = "select Emp_Id,Reloc_Assistance,Joining_Bonus,Referral_Bonus,Reloc_Assist_Amt, Joining_Bonus_Amt, Referral_Bonus_Amt, Referral_Candidate_Name,Deleted "
				+ "from JOINING_BENEFITS_TRACKER where Emp_Id=? AND Insertion_Date = (SELECT MAX(Insertion_Date) FROM JOINING_BENEFITS_TRACKER WHERE Emp_Id = ?)";
		
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
		
				
				join.setEmpId(rs.getString("Emp_Id"));
				join.setRelocAssistance(rs.getBoolean("Reloc_Assistance"));
				join.setJoiningBonus(rs.getBoolean("Joining_Bonus"));
				join.setReferralBonus(rs.getBoolean("Referral_Bonus"));
				join.setRelocAssistAmt(rs.getString("Reloc_Assist_Amt"));
				join.setJoiningBonusAmt(rs.getString("Joining_Bonus_Amt"));
				join.setReferralBonusAmt(rs.getString("Referral_Bonus_Amt"));
				join.setDeletedB(rs.getBoolean("Deleted"));
				join.setReferrealCandidateName(rs.getString("Referral_Candidate_Name"));
				
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return join;
		
	}
	
	
	public SkillSetTrainingInfoObj getSkillInfo(String empid) {
		
		SkillSetTrainingInfoObj skills = new SkillSetTrainingInfoObj();
		
		String query = "select Emp_Id,Primary_Skills, Current_Skill, Capco_Certification, Other_Trainings,Prior_Exp,Capco_Exp,Total_Exp,Deleted "
				+ "from SKILL_SET_TRACKER where Emp_Id=? AND Insertion_Date = (SELECT MAX(Insertion_Date) FROM SKILL_SET_TRACKER WHERE Emp_Id = ?)";
		
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
				
				skills.setEmpId(rs.getString("Emp_Id"));
				skills.setPrimarySkills(rs.getString("Primary_Skills"));
				skills.setCurrentSkill(rs.getString("Current_Skill"));
				skills.setCapcoCertification(rs.getString("Capco_Certification"));
				skills.setOtherTrainings(rs.getString("Other_Trainings"));
				skills.setPriorExp(rs.getString("Prior_Exp"));
				skills.setCapcoExp(rs.getString("Capco_Exp"));
				skills.setTotalExp(rs.getString("Total_Exp"));
				skills.setDeletedB(rs.getBoolean("Deleted"));
				
				
				
				
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return skills;
	}
	
	
	
	public TravelBondInfoObj getTravelInfo(String empid) {
		
		TravelBondInfoObj	travel = new TravelBondInfoObj();
		
		String query = "select Emp_Id,Travel_Country, Type_Of_Travel, Claw_Back_Month, Number_Of_Month, Deleted "
				+ "from TRAVEL_BOND_TRACKER where Emp_Id=? AND Insertion_Date = (SELECT MAX(Insertion_Date) FROM TRAVEL_BOND_TRACKER WHERE Emp_Id = ?)";
		
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
			
				travel.setEmpId(""+ rs.getInt("Emp_Id"));
				travel.setTravelCountry(rs.getString("Travel_Country"));
				travel.setTypeOfTravel(rs.getString("Type_Of_Travel"));
				travel.setClawBackMonth(rs.getString("Claw_Back_Month"));
				travel.setNumberOfMonth(rs.getString("Number_Of_Month"));
				travel.setDeletedB(rs.getBoolean("Deleted"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return travel;
		
	}
	
	
	
	
	public List<JoiningBenefitsInfoObj> getBenefitHistory() {
		
		List<JoiningBenefitsInfoObj> joining = new ArrayList<JoiningBenefitsInfoObj>();
		
		String query = "select Emp_Id,Reloc_Assistance,Joining_Bonus,Referral_Bonus,Reloc_Assist_Amt, "
				+ "Joining_Bonus_Amt, Referral_Bonus_Amt, Referral_Candidate_Name,Deleted, Insertion_Date, Inserted_By "
				+ "from JOINING_BENEFITS_TRACKER ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
		
				JoiningBenefitsInfoObj join =  new JoiningBenefitsInfoObj();
				join.setEmpId(rs.getString("Emp_Id"));
				join.setRelocAssistance(rs.getBoolean("Reloc_Assistance"));
				join.setJoiningBonus(rs.getBoolean("Joining_Bonus"));
				join.setReferralBonus(rs.getBoolean("Referral_Bonus"));
				join.setRelocAssistAmt(rs.getString("Reloc_Assist_Amt"));
				join.setJoiningBonusAmt(rs.getString("Joining_Bonus_Amt"));
				join.setReferralBonusAmt(rs.getString("Referral_Bonus_Amt"));
				join.setDeletedB(rs.getBoolean("Deleted"));
				join.setReferrealCandidateName(rs.getString("Referral_Candidate_Name"));
				join.setInsertedDate(rs.getDate("Insertion_Date"));
				join.setInsertedBy(rs.getString("Inserted_By"));
				
				
				joining.add(join);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return joining;
		
	}

	public List<SkillSetTrainingInfoObj> getSkillHistory() {
		
		List<SkillSetTrainingInfoObj> skill = new ArrayList<SkillSetTrainingInfoObj>();
		
		String query = "select Emp_Id,Primary_Skills, Current_Skill, Capco_Certification, Other_Trainings,"
				+ "Prior_Exp,Capco_Exp,Total_Exp,Deleted,Insertion_Date, Inserted_By "
				+ "from SKILL_SET_TRACKER";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				SkillSetTrainingInfoObj skills = new SkillSetTrainingInfoObj();
				skills.setEmpId(rs.getString("Emp_Id"));
				skills.setPrimarySkills(rs.getString("Primary_Skills"));
				skills.setCurrentSkill(rs.getString("Current_Skill"));
				skills.setCapcoCertification(rs.getString("Capco_Certification"));
				skills.setOtherTrainings(rs.getString("Other_Trainings"));
				skills.setPriorExp(rs.getString("Prior_Exp"));
				skills.setCapcoExp(rs.getString("Capco_Exp"));
				skills.setTotalExp(rs.getString("Total_Exp"));
				skills.setDeletedB(rs.getBoolean("Deleted"));
				skills.setInsertedDate(rs.getDate("Insertion_Date"));
				skills.setInsertedBy(rs.getString("Inserted_By"));
				
				skill.add(skills);
				
				
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return skill;
	}

	
	
	
	public List<TravelBondInfoObj> getTravelHistory() {
		
		
		List<TravelBondInfoObj>	travel = new ArrayList<TravelBondInfoObj>();
		
		String query = "select Emp_Id,Travel_Country, Type_Of_Travel, Claw_Back_Month, Number_Of_Month, Deleted ,"
				+ "Insertion_Date, Inserted_By from TRAVEL_BOND_TRACKER ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				TravelBondInfoObj tr = new TravelBondInfoObj();
				tr.setEmpId(rs.getString("Emp_Id"));
				tr.setTravelCountry(rs.getString("Travel_Country"));
				tr.setTypeOfTravel(rs.getString("Type_Of_Travel"));
				tr.setClawBackMonth(rs.getString("Claw_Back_Month"));
				tr.setNumberOfMonth(rs.getString("Number_Of_Month"));
				tr.setDeletedB(rs.getBoolean("Deleted"));
				tr.setInsertedDate(rs.getDate("Insertion_Date"));
				tr.setInsertedBy(rs.getString("Inserted_By"));
				
				travel.add(tr);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return travel;
		
	}
	
	/**
	 * 
	 * @param jbiObj
	 * @return
	 */
	public boolean insertBenefitsDetails(JoiningBenefitsInfoObj jbiObj){
		boolean success = true;
		String query = "INSERT INTO JOINING_BENEFITS_TRACKER (Emp_Id, Reloc_Assistance, Reloc_Assist_Amt, Joining_Bonus, Joining_Bonus_Amt, "
						+ " Referral_Bonus, Referral_Bonus_Amt, Referral_Candidate_Name,Deleted, Inserted_By) VALUES (?, ?, ?, ?, ?, ?, ?, ?, false,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, jbiObj.getEmpId());
			ps.setBoolean(2, jbiObj.getRelocAssistance());
			ps.setString(3, jbiObj.getRelocAssistAmt());
			ps.setBoolean(4, jbiObj.getJoiningBonus());
			ps.setString(5, jbiObj.getJoiningBonusAmt());
			ps.setBoolean(6, jbiObj.getReferralBonus());
			ps.setString(7, jbiObj.getReferralBonusAmt());
			ps.setString(8, jbiObj.getReferrealCandidateName());
			ps.setString(9, jbiObj.getInsertedBy());
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
	
	/**
	 * 
	 * @param sstiObj
	 * @return
	 */
	public boolean insertSkillsetDetails(SkillSetTrainingInfoObj sstiObj){
		boolean success = true;
		String query = "INSERT INTO SKILL_SET_TRACKER (Emp_Id, Primary_Skills, Current_Skill, Capco_Certification, Other_Trainings, Prior_Exp, "
						+ " Total_Exp, Capco_Exp,Deleted, Inserted_By) VALUES (?, ?, ?, ?, ?, ?, ?, ?, false,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, sstiObj.getEmpId());
			ps.setString(2, sstiObj.getPrimarySkills());
			ps.setString(3, sstiObj.getCurrentSkill());
			ps.setString(4, sstiObj.getCapcoCertification());
			ps.setString(5, sstiObj.getOtherTrainings());
			ps.setString(6, sstiObj.getPriorExp());
			ps.setString(7, sstiObj.getTotalExp());
			ps.setString(8, sstiObj.getCapcoExp());
			ps.setString(9, sstiObj.getInsertedBy());
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
	
	/**
	 * 
	 * @param tbiObj
	 * @return
	 */
	public boolean insertTravelBondDetails(TravelBondInfoObj tbiObj){
		boolean success = true;
		String query = "INSERT INTO TRAVEL_BOND_TRACKER (Emp_Id, Type_Of_Travel, Travel_Country, Claw_Back_Month, Number_Of_Month, Deleted, Inserted_By) "
						+ " VALUES (?, ?, ?, ?, ?, FALSE, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, tbiObj.getEmpId());
			ps.setString(2, tbiObj.getTypeOfTravel());
			ps.setString(3, tbiObj.getTravelCountry());
			ps.setString(4, tbiObj.getClawBackMonth());
			ps.setString(5, tbiObj.getNumberOfMonth());
			ps.setString(6, tbiObj.getInsertedBy());
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

	public boolean updatedValue(TravelBondInfoObj travel) {
		// TODO Auto-generated method stub
		
		System.out.println(travel);
		boolean success = true;
		
		String query = "UPDATE TRAVEL_BOND_TRACKER SET Type_Of_Travel = ?, Travel_Country=?,Claw_Back_Month=? ,Number_Of_Month=? where Emp_Id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, travel.getTypeOfTravel());
			ps.setString(2, travel.getTravelCountry());
			ps.setString(3, travel.getClawBackMonth());
			ps.setString(4, travel.getNumberOfMonth());
			ps.setInt(5, Integer.parseInt(travel.getEmpId()));
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
	}

	public List<TravelBondInfoObj> getTravel(String empid) {
		
		List<TravelBondInfoObj>	travel = new ArrayList<TravelBondInfoObj>();
		
		String query = "select Emp_Id,Travel_Country, Type_Of_Travel, Claw_Back_Month, Number_Of_Month, Deleted "
				+ "from TRAVEL_BOND_TRACKER where Emp_Id=? ";
		
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
				TravelBondInfoObj tr = new TravelBondInfoObj();
				tr.setEmpId(rs.getString("Emp_Id"));
				tr.setTravelCountry(rs.getString("Travel_Country"));
				tr.setTypeOfTravel(rs.getString("Type_Of_Travel"));
				tr.setClawBackMonth(rs.getString("Claw_Back_Month"));
				tr.setNumberOfMonth(rs.getString("Number_Of_Month"));
				tr.setDeletedB(rs.getBoolean("Deleted"));
				
				travel.add(tr);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return travel;
		
	}

	public boolean deleteFieldForTravel(TravelBondInfoObj travel) {
		// TODO Auto-generated method stub
		boolean success = true;
		
		String query = "UPDATE TRAVEL_BOND_TRACKER SET Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(travel.getEmpId()));
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return success;
		
	
	}

	public List<JoiningBenefitsInfoObj> getBenefit(String empid) {
		
		List<JoiningBenefitsInfoObj> joining = new ArrayList<JoiningBenefitsInfoObj>();
		
		String query = "select Emp_Id,Reloc_Assistance,Joining_Bonus,Referral_Bonus,Reloc_Assist_Amt, Joining_Bonus_Amt, Referral_Bonus_Amt, Referral_Candidate_Name,Deleted "
				+ "from JOINING_BENEFITS_TRACKER where Emp_Id=?";
		
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
		
				JoiningBenefitsInfoObj join =  new JoiningBenefitsInfoObj();
				join.setEmpId(rs.getString("Emp_Id"));
				join.setRelocAssistance(rs.getBoolean("Reloc_Assistance"));
				join.setJoiningBonus(rs.getBoolean("Joining_Bonus"));
				join.setReferralBonus(rs.getBoolean("Referral_Bonus"));
				join.setRelocAssistAmt(rs.getString("Reloc_Assist_Amt"));
				join.setJoiningBonusAmt(rs.getString("Joining_Bonus_Amt"));
				join.setReferralBonusAmt(rs.getString("Referral_Bonus_Amt"));
				join.setDeletedB(rs.getBoolean("Deleted"));
				join.setReferrealCandidateName(rs.getString("Referral_Candidate_Name"));
				
				
				joining.add(join);
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return joining;
		
	}

	public boolean updatedBenefitValue(JoiningBenefitsInfoObj benefit) {
		
		System.out.println(benefit);
		boolean success = true;
		
		String query = "UPDATE JOINING_BENEFITS_TRACKER SET Reloc_Assistance=?,Joining_Bonus=?,Referral_Bonus=?, "
				+ "Reloc_Assist_Amt = ?,Joining_Bonus_Amt=? ,Referral_Bonus_Amt=?, Referral_Candidate_Name=?"
				+ " where Emp_Id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, benefit.getRelocAssistance());
			ps.setBoolean(2, benefit.getJoiningBonus());
			ps.setBoolean(3, benefit.getReferralBonus());
			ps.setString(4, benefit.getRelocAssistAmt());
			ps.setString(5, benefit.getJoiningBonusAmt());
			ps.setString(6, benefit.getReferralBonusAmt());
			ps.setString(7, benefit.getReferrealCandidateName());
			ps.setInt(8, Integer.parseInt(benefit.getEmpId()));
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
		
	}

	public boolean deleteFieldForBenefit(JoiningBenefitsInfoObj benefit) {
		
		boolean success = true;
		
		String query = "UPDATE JOINING_BENEFITS_TRACKER SET Deleted = TRUE WHERE Referral_Candidate_Name = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, benefit.getReferrealCandidateName());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return success;
		
	}

	public List<SkillSetTrainingInfoObj> getSkill(String empid) {
		
		List<SkillSetTrainingInfoObj> skill = new ArrayList<SkillSetTrainingInfoObj>();
		
		String query = "select Emp_Id,Primary_Skills, Current_Skill, Capco_Certification, Other_Trainings,Prior_Exp,Capco_Exp,Total_Exp,Deleted "
				+ "from SKILL_SET_TRACKER where Emp_Id=?";
		
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
				SkillSetTrainingInfoObj skills = new SkillSetTrainingInfoObj();
				skills.setEmpId(rs.getString("Emp_Id"));
				skills.setPrimarySkills(rs.getString("Primary_Skills"));
				skills.setCurrentSkill(rs.getString("Current_Skill"));
				skills.setCapcoCertification(rs.getString("Capco_Certification"));
				skills.setOtherTrainings(rs.getString("Other_Trainings"));
				skills.setPriorExp(rs.getString("Prior_Exp"));
				skills.setCapcoExp(rs.getString("Capco_Exp"));
				skills.setTotalExp(rs.getString("Total_Exp"));
				skills.setDeletedB(rs.getBoolean("Deleted"));
				
				skill.add(skills);
				
				
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		return skill;
	}

	public boolean updatedSkillValue(SkillSetTrainingInfoObj skill) {
		
		System.out.println(skill);
		boolean success = true;
		
		String query = "UPDATE SKILL_SET_TRACKER SET Primary_Skills = ?,Current_Skill=? ,Capco_Certification=?,"
				+ "Other_Trainings=?,Prior_Exp=?,Capco_Exp=?, Total_Exp=?"
				+ " where Emp_Id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, skill.getPrimarySkills());
			ps.setString(2, skill.getCurrentSkill());
			ps.setString(3, skill.getCapcoCertification());
			ps.setString(4, skill.getOtherTrainings());
			ps.setString(5, skill.getPriorExp());
			ps.setString(6, skill.getCapcoExp());
			ps.setString(7, skill.getTotalExp());
			ps.setInt(8, Integer.parseInt(skill.getEmpId()));
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		return success;
		
	}

	public boolean deleteFieldForSkill(SkillSetTrainingInfoObj skill) {
		
		boolean success = true;
		
		String query = "UPDATE SKILL_SET_TRACKER SET Deleted = TRUE WHERE Emp_Id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(skill.getEmpId()));
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

	




