package com.capco.hcm.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.capco.hcm.controller.DatabaseController;
import com.capco.hcm.model.PromotionTrackerObj;
import com.capco.hcm.model.ReviewCycleTracker;

/**
 * @author Mohit Gangil
 *
 */

public class PromotionTrackerDao implements Serializable {
	private static final long serialVersionUID = -7271669404283161230L;

	private DatabaseController dbCtrl;
	
	//GETTERS AND SETTERS
		public DatabaseController getDbCtrl() {
			return dbCtrl;
		}
		public void setDbCtrl(DatabaseController dbCtrl) {
			this.dbCtrl = dbCtrl;
		}


		
		public void updateEmpPromotiondetails(List<PromotionTrackerObj> empobj)
		{
			for(PromotionTrackerObj obj:empobj)
			{
				if(null != obj.getEmployeeId() ||  !"0".equals(obj.getEmployeeId()))
				{
					
					System.out.println("Object main kya hain bai "+ obj);
					
					PromotionTrackerObj promoInfo = getHireInfo(obj.getEmployeeId());
					
					
					if(promoInfo !=null && promoInfo.getEmployeeId()!=null && Integer.parseInt(promoInfo.getEmployeeId())>0)
					{
						System.out.println("Comes under update"+obj);
						updateEmployeeReviewTracker(obj, obj.getEmployeeId());
					}
					else
					{
						System.out.println("Comes under insert"+obj);
						
						insertEmployeeReviewTracker(obj, obj.getEmployeeId());
					}
				}
			}
		}
		
		
		private boolean updateEmployeeReviewTracker(PromotionTrackerObj obj, String employeeId) {
			// TODO Auto-generated method stub
		
			System.out.println("Comes under update of promotion tracker");
			System.out.println(obj);
			boolean success = true;
			
			String query = "UPDATE EMPLOYEE_BAND_TRACKER SET Hired_Band=?, Manager_Cit=?,Manager_Ps=?,Team_Lead_Ps=?,Inserted_By=? "
					+ "where Emp_Id=?";
				
		
			Connection conn = null;
			PreparedStatement ps = null;
			
			try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
				ps.setString(1, obj.getHiredAtBand());
				ps.setString(2, obj.getManagerCit());
				ps.setString(3, obj.getPerfMngrPsOrCoach());
				ps.setString(4, obj.getTeamLeadPs());
				ps.setString(5, obj.getInsertedBy());
				ps.setInt(6, Integer.parseInt(employeeId));
				
				ps.executeUpdate();
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
		
		
		public PromotionTrackerObj getHireInfo(String employeeId)
		{
			
			PromotionTrackerObj review = new PromotionTrackerObj();
			
	String query = "SELECT Emp_Id, Hired_Band, Manager_Cit, Manager_Ps, Team_Lead_Ps,Insertion_Date, Inserted_By from EMPLOYEE_BAND_TRACKER where Emp_Id = ?";
					
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
		
			try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(employeeId));


				rs = ps.executeQuery();				
				while(rs.next()){
					
					review.setEmployeeId(rs.getString("Emp_Id"));
					review.setHiredAtBand(rs.getString("Hired_Band"));
					review.setManagerCit(rs.getString("Manager_Cit"));
					review.setPerfMngrPsOrCoach(rs.getString("Manager_Ps"));
					review.setTeamLeadPs(rs.getString("Team_Lead_Ps"));
					review.setInsertedDate(rs.getDate("Insertion_Date"));
					review.setInsertedBy(rs.getString("Inserted_By"));
					
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{rs.close();}catch(SQLException e){e.printStackTrace();}
				try{ps.close();}catch(SQLException e){e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}

			return review;
			
		}
		
	/*public PromotionTrackerObj getEmployeeReviewHistory(String employeeId){
		PromotionTrackerObj empReviewTrkrObj = new PromotionTrackerObj();
		List<ReviewCycleTracker> bandTrackerList = new ArrayList<>();

		String query = "SELECT Emp_Id, Hired_Band, Manager_Cit, Manager_Ps, Team_Lead_Ps, Review_Cycle, Review_Band "
				+ " FROM EMPLOYEE_BAND_TRACKER AS ebt INNER JOIN REVIEW_CYCLE_TRACKER as rct "
				+ " ON ebt.Emp_Id = rct.Emp_Id WHERE ebt.Emp_Id = ? "
				+ "	AND ebt.Insertion_Date = (SELECT MAX(ebt2.Insertion_Date) FROM EMPLOYEE_BAND_TRACKER AS ebt2 WHERE ebt2.Emp_Id = ?)"
				+ "	AND rct.Insertion_Date = (SELECT MAX(rct2.Insertion_Date) FROM REVIEW_CYCLE_TRACKER AS rct2 WHERE rct2.Emp_Id = ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try{
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);

			ps.setInt(1, Integer.parseInt(employeeId));
			ps.setInt(2, Integer.parseInt(employeeId));
			ps.setInt(3, Integer.parseInt(employeeId));

			rs = ps.executeQuery();

			while(rs.next()){
				if(StringUtils.isNullOrEmpty(empReviewTrkrObj.getHiredAtBand()))
					empReviewTrkrObj.setHiredAtBand(rs.getString("Hired_Band"));

				if(StringUtils.isNullOrEmpty(empReviewTrkrObj.getManagerCit()))
					empReviewTrkrObj.setManagerCit(rs.getString("Manager_Cit"));

				if(StringUtils.isNullOrEmpty(empReviewTrkrObj.getPerfMngrPsOrCoach()))
					empReviewTrkrObj.setPerfMngrPsOrCoach(rs.getString("Manager_Ps"));

				if(StringUtils.isNullOrEmpty(empReviewTrkrObj.getTeamLeadPs()))
					empReviewTrkrObj.setTeamLeadPs(rs.getString("Team_Lead_Ps"));

				ReviewCycleTracker rct = empReviewTrkrObj.getNewInstaceOfReviewCycleTrkr();
				rct.setReviewBand(rs.getString("Review_Band"));
				rct.setReviewDate(rs.getString("Review_Cycle"));
				bandTrackerList.add(rct);
			}
			empReviewTrkrObj.setBandTracker(bandTrackerList);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return empReviewTrkrObj;
	}
*/
	/**
	 * 
	 * @param empReviewTrkrObj
	 * @param employeeId
	 * @return
	 */
	public boolean insertEmployeeReviewTracker(PromotionTrackerObj empReviewTrkrObj, String employeeId){
		boolean success = true;

		String query = "INSERT INTO EMPLOYEE_BAND_TRACKER (Emp_Id, Hired_Band, Manager_Cit, Manager_Ps, Team_Lead_Ps, Inserted_By) VALUES (?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		conn = dbCtrl.getConnection();
		
		try{
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(employeeId));
			ps.setString(2, empReviewTrkrObj.getHiredAtBand());
			ps.setString(3, empReviewTrkrObj.getManagerCit());
			ps.setString(4, empReviewTrkrObj.getPerfMngrPsOrCoach());
			ps.setString(5, empReviewTrkrObj.getTeamLeadPs());
			ps.setString(6, empReviewTrkrObj.getInsertedBy());//TODO add the insertion name
			
			ps.execute();
		}catch(SQLException e){
			success = false;
			e.printStackTrace();
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			//dbCtrl.closeConnection(conn);
		}

		query = "select Insertion_Date from EMP_INFO where Emp_Id=?";
		
		ResultSet rs = null;
		List<Date> fieldNameList = new ArrayList<Date>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			
			ps = conn.prepareStatement(query);
			ps.setInt(1,Integer.parseInt(employeeId));
			rs = ps.executeQuery();
			while(rs.next()){
				fieldNameList.add(rs.getDate("Insertion_Date"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			success = false;
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}
		
		for(Date fName : fieldNameList){
			//Cannot have a review cycle before an employee joins the organization
	
			ReviewCycleTracker reviewCycleTrkr = empReviewTrkrObj.getNewInstaceOfReviewCycleTrkr();
			reviewCycleTrkr.setReviewBand(empReviewTrkrObj.getHiredAtBand());
			reviewCycleTrkr.setReviewDate(sdf.format(fName)); // date
			updateEmployeeReviewCycleHistory(reviewCycleTrkr.getReviewBand(),reviewCycleTrkr.getReviewDate(), employeeId, reviewCycleTrkr.getInsertedBy());
		}
		
		return success;
	}
	
	
	
		public List<ReviewCycleTracker> getPromotionTrackerValues(String employeeId)
		{
			
			String query = "SELECT Emp_Id,Review_Cycle, Review_Band from REVIEW_CYCLE_TRACKER where Emp_Id=? ";
					
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<ReviewCycleTracker> promo = new ArrayList<ReviewCycleTracker>();

			try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(employeeId));
				

				rs = ps.executeQuery();				
				while(rs.next()){
					
					ReviewCycleTracker review = new com.capco.hcm.model.ReviewCycleTracker();
					review.setEmpId(rs.getString("Emp_Id"));
					review.setReviewBand(rs.getString("Review_Band"));
					review.setReviewDate(rs.getString("Review_Cycle"));
					promo.add(review);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{rs.close();}catch(SQLException e){e.printStackTrace();}
				try{ps.close();}catch(SQLException e){e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}

			return promo;
			
		}
		
		public List<ReviewCycleTracker> getPromotionTrackerHistory()
		{
			
			String query = "SELECT Emp_Id,Review_Cycle, Review_Band,Insertion_Date,Inserted_By "
					+ " from REVIEW_CYCLE_TRACKER";
					
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<ReviewCycleTracker> promo = new ArrayList<ReviewCycleTracker>();

			try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
			
				rs = ps.executeQuery();				
				while(rs.next()){
					
					ReviewCycleTracker review = new com.capco.hcm.model.ReviewCycleTracker();
					review.setEmpId(rs.getString("Emp_Id"));
					review.setReviewBand(rs.getString("Review_Band"));
					review.setReviewDate(rs.getString("Review_Cycle"));
					review.setInsertedDate(rs.getDate("Insertion_Date"));
					review.setInsertedBy(rs.getString("Inserted_By"));
					promo.add(review);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{rs.close();}catch(SQLException e){e.printStackTrace();}
				try{ps.close();}catch(SQLException e){e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}

			return promo;
			
		}
		
		public List<PromotionTrackerObj> getHireHistory()
		{
			
			String query = "SELECT Emp_Id, Hired_Band, Manager_Cit, Manager_Ps, Team_Lead_Ps,Insertion_Date, "
					+ " Inserted_By from EMPLOYEE_BAND_TRACKER ";
					
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<PromotionTrackerObj> promo = new ArrayList<PromotionTrackerObj>();

			try{
				conn = dbCtrl.getConnection();
				ps = conn.prepareStatement(query);
			
				rs = ps.executeQuery();				
				while(rs.next()){
					
					PromotionTrackerObj review = new PromotionTrackerObj();
					review.setEmployeeId(rs.getString("Emp_Id"));
					review.setHiredAtBand(rs.getString("Hired_Band"));
					review.setManagerCit(rs.getString("Manager_Cit"));
					review.setPerfMngrPsOrCoach(rs.getString("Manager_Ps"));
					review.setTeamLeadPs(rs.getString("Team_Lead_Ps"));
					review.setInsertedDate(rs.getDate("Insertion_Date"));
					review.setInsertedBy(rs.getString("Inserted_By"));
					promo.add(review);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{rs.close();}catch(SQLException e){e.printStackTrace();}
				try{ps.close();}catch(SQLException e){e.printStackTrace();}
				dbCtrl.closeConnection(conn);
			}

			return promo;
			
		}
		
		
		
		
	/**
	 * 
	 * @param empReviewTrkrObj
	 * @param employeeId
	 * @return
	 */
	public boolean updateEmployeeReviewCycleHistory(String reviewBand,String reviewDate, String employeeId, String insertedby){
		boolean success = true;
		String query = "INSERT INTO REVIEW_CYCLE_TRACKER(Emp_Id, Review_Cycle, Review_Band,Inserted_By ) VALUES (?, ?, ?,?)";
		
		System.out.println("band main ktya hain "+ reviewBand);
		System.out.println("date main ktya hain "+ reviewDate);
		System.out.println("id main ktya hain "+ employeeId);
		System.out.println("inserted by  main ktya hain "+ insertedby);
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			ReviewCycleTracker obj = new ReviewCycleTracker();
			
			System.out.println(" band" + obj.getReviewBand());
			System.out.println("date " + obj.getReviewDate());
			System.out.println("inserted " + obj.getInsertedBy());
			System.out.println("id " + obj.getEmpId());
			
			
			
			conn = dbCtrl.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, employeeId);
			ps.setString(2, reviewDate);
			ps.setString(3, reviewBand);
			ps.setString(4, insertedby);
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
			success = false;
		}finally{
			try{ps.close();}catch(SQLException e){e.printStackTrace();}
			dbCtrl.closeConnection(conn);
		}

		return success;
	}

	

}
