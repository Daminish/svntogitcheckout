package com.capco.hcm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.capco.hcm.controller.DatabaseController;
import com.capco.hcm.model.DocAndBgvObj;
import com.capco.hcm.model.EmployeeBasicInfoObj;
import com.capco.hcm.model.EndorsementTrackerObj;
import com.capco.hcm.model.ExitFormalityTrackerObj;
import com.capco.hcm.model.FinancialDetailsObj;
import com.capco.hcm.model.HcmLetterReqObj;
import com.capco.hcm.model.InductionTrackerObj;
import com.capco.hcm.model.JoiningBenefitsInfoObj;
import com.capco.hcm.model.NomineeTrackerObj;
import com.capco.hcm.model.ProbationTrackerObj;
import com.capco.hcm.model.ProjectHistoryObj;
import com.capco.hcm.model.PromotionTrackerObj;
import com.capco.hcm.model.ReviewCycleTracker;
import com.capco.hcm.model.SkillSetTrainingInfoObj;
import com.capco.hcm.model.StaticTableObject;
import com.capco.hcm.model.TravelBondInfoObj;
import com.capco.hcm.model.UserInfoObj;
import com.capco.hcm.sql.BenefitsTravelTrainingDao;
import com.capco.hcm.sql.DocAndBgvDao;
import com.capco.hcm.sql.EmployeeBasicInfoDao;
import com.capco.hcm.sql.EndorsementTrackerDao;
import com.capco.hcm.sql.ExitFormalityTrackerDao;
import com.capco.hcm.sql.FinancialDetailsDao;
import com.capco.hcm.sql.HcmLetterReqSqlDao;
import com.capco.hcm.sql.InductionTrackerDao;
import com.capco.hcm.sql.ProbationTrackerDao;
import com.capco.hcm.sql.ProjectHistoryDao;
import com.capco.hcm.sql.PromotionTrackerDao;
import com.capco.hcm.sql.StaticTableDao;
import com.capco.hcm.sql.UserInfoDao;
import com.capco.hcm.view.fragments.EmpBasicInfoView;

	
public class ExcelFileReader {
	
private DatabaseController dbCtrl;
	
	public DatabaseController getDbCtrl() {
		return dbCtrl;
	}
	public void setDbCtrl(DatabaseController dbCtrl) {
		this.dbCtrl = dbCtrl;
	}
	private static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");
	Collection<File> files;
	XSSFWorkbook workbook = null;
	
	
	private static final String BASIC_INFO = "Basic Info";
	private static final String BGV = "BGV";
	private static final String INDUCTON = "Induction";
	private static final String FINANCE = "Financial Details";
	private static final String EXIT = "Exit Formality";
	private static final String PROBATION = "Probation";
	private static final String USER = "UserInfo";
	private static final String PROJECT = "Project History";
	private static final String STATIC = "Static";
	private static final String ENDORSEMENT = "Endorsement";
	private static final String NOMINATION = "Nomination";
	private static final String PROMOTION = "Promotion";
	private static final String REVIEW = "Review History";
	private static final String SKILL = "Skills";
	private static final String TRAVEL = "Travel";
	private static final String BENEFIT = "Benefits";
	private static final String HCM = "Hcm Letter";
	

	// All Dao classes for Calling
	private EmployeeBasicInfoDao empBasicInfoDao;
	private DocAndBgvDao bgvDocDao;
	private InductionTrackerDao inductionTrkrDao;
	private FinancialDetailsDao finDetailDao;
	private ExitFormalityTrackerDao exitFormilityDao;
	private ProbationTrackerDao probationTrkrDao;
	private UserInfoDao userInfoDao;
	private ProjectHistoryDao projecthistoryDao;
	private StaticTableDao staticDao;
	private EndorsementTrackerDao endorseTrkrDao;
	private BenefitsTravelTrainingDao beneTrvlTrainDao;
	private HcmLetterReqSqlDao letterReqSqlDao;
	
	// All getter and setter for Dao classes
	public HcmLetterReqSqlDao getLetterReqSqlDao() {
		return letterReqSqlDao;
	}
	public void setLetterReqSqlDao(HcmLetterReqSqlDao letterReqSqlDao) {
		this.letterReqSqlDao = letterReqSqlDao;
	}
	
	public BenefitsTravelTrainingDao getBeneTrvlTrainDao() {
		return beneTrvlTrainDao;
	}

	public void setBeneTrvlTrainDao(BenefitsTravelTrainingDao beneTrvlTrainDao) {
		this.beneTrvlTrainDao = beneTrvlTrainDao;
	}

	public PromotionTrackerDao getPromotionTrkrDao() {
		return promotionTrkrDao;
	}

	public void setPromotionTrkrDao(PromotionTrackerDao promotionTrkrDao) {
		this.promotionTrkrDao = promotionTrkrDao;
	}


	private PromotionTrackerDao promotionTrkrDao;
	
	
	public EndorsementTrackerDao getEndorseTrkrDao() {
		return endorseTrkrDao;
	}

	public void setEndorseTrkrDao(EndorsementTrackerDao endorseTrkrDao) {
		this.endorseTrkrDao = endorseTrkrDao;
	}

	public StaticTableDao getStaticDao() {
		return staticDao;
	}

	public void setStaticDao(StaticTableDao staticDao) {
		this.staticDao = staticDao;
	}

	public ProjectHistoryDao getProjecthistoryDao() {
		return projecthistoryDao;
	}

	public void setProjecthistoryDao(ProjectHistoryDao projecthistoryDao) {
		this.projecthistoryDao = projecthistoryDao;
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public ProbationTrackerDao getProbationTrkrDao() {
		return probationTrkrDao;
	}

	public void setProbationTrkrDao(ProbationTrackerDao probationTrkrDao) {
		this.probationTrkrDao = probationTrkrDao;
	}

	public ExitFormalityTrackerDao getExitFormilityDao() {
		return exitFormilityDao;
	}

	public void setExitFormilityDao(ExitFormalityTrackerDao exitFormilityDao) {
		this.exitFormilityDao = exitFormilityDao;
	}

	public FinancialDetailsDao getFinDetailDao() {
		return finDetailDao;
	}

	public void setFinDetailDao(FinancialDetailsDao finDetailDao) {
		this.finDetailDao = finDetailDao;
	}

	public InductionTrackerDao getInductionTrkrDao() {
		return inductionTrkrDao;
	}

	public void setInductionTrkrDao(InductionTrackerDao inductionTrkrDao) {
		this.inductionTrkrDao = inductionTrkrDao;
	}

	public DocAndBgvDao getBgvDocDao() {
		return bgvDocDao;
	}

	public void setBgvDocDao(DocAndBgvDao bgvDocDao) {
		this.bgvDocDao = bgvDocDao;
	}

	public EmployeeBasicInfoDao getEmpBasicInfoDao() {
		return empBasicInfoDao;
	}

	public void setEmpBasicInfoDao(EmployeeBasicInfoDao empBasicInfoDao) {
		this.empBasicInfoDao = empBasicInfoDao;
	}

	// All classes for List
	private List<DocAndBgvObj> backgroundList;
	private List<EmployeeBasicInfoObj> employeeInfoList;
	private List<InductionTrackerObj> inductionList;
	private List<FinancialDetailsObj> financeList;
	private List<ProbationTrackerObj> probationList;
	private List<UserInfoObj> userList;
	private List<ProjectHistoryObj> projectList;
	private List<StaticTableObject> staticList;
	private List<EndorsementTrackerObj> endorsementList;
	private List<NomineeTrackerObj> nominationList;
	private List<PromotionTrackerObj> promotionList;
	private List<ReviewCycleTracker> reviewList;
	private List<TravelBondInfoObj> travelList;
	private List<JoiningBenefitsInfoObj> benefitsList;
	private List<HcmLetterReqObj> hcmList;
	
	// All getter and setter method
	public List<HcmLetterReqObj> getHcmList() {
		return hcmList;
	}
	public void setHcmList(List<HcmLetterReqObj> hcmList) {
		this.hcmList = hcmList;
	}
	
	public List<TravelBondInfoObj> getTravelList() {
		return travelList;
	}

	public void setTravelList(List<TravelBondInfoObj> travelList) {
		this.travelList = travelList;
	}

	public List<JoiningBenefitsInfoObj> getBenefitsList() {
		return benefitsList;
	}

	public void setBenefitsList(List<JoiningBenefitsInfoObj> benefitsList) {
		this.benefitsList = benefitsList;
	}

	public List<SkillSetTrainingInfoObj> getSkillsList() {
		return skillsList;
	}

	public void setSkillsList(List<SkillSetTrainingInfoObj> skillsList) {
		this.skillsList = skillsList;
	}


	private List<SkillSetTrainingInfoObj> skillsList;
	
	
	
	
	public List<ReviewCycleTracker> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<ReviewCycleTracker> reviewList) {
		this.reviewList = reviewList;
	}

	public List<PromotionTrackerObj> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<PromotionTrackerObj> promotionList) {
		this.promotionList = promotionList;
	}

	public List<NomineeTrackerObj> getNominationList() {
		return nominationList;
	}

	public void setNominationList(List<NomineeTrackerObj> nominationList) {
		this.nominationList = nominationList;
	}

	public List<EndorsementTrackerObj> getEndorsementList() {
		return endorsementList;
	}

	public void setEndorsementList(List<EndorsementTrackerObj> endorsementList) {
		this.endorsementList = endorsementList;
	}

	public List<StaticTableObject> getStaticList() {
		return staticList;
	}

	public void setStaticList(List<StaticTableObject> staticList) {
		this.staticList = staticList;
	}

	public List<ProjectHistoryObj> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectHistoryObj> projectList) {
		this.projectList = projectList;
	}

	public List<UserInfoObj> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfoObj> userList) {
		this.userList = userList;
	}

	public List<ProbationTrackerObj> getProbationList() {
		return probationList;
	}

	public void setProbationList(List<ProbationTrackerObj> probationList) {
		this.probationList = probationList;
	}

	public List<ExitFormalityTrackerObj> getExitList() {
		return exitList;
	}

	public void setExitList(List<ExitFormalityTrackerObj> exitList) {
		this.exitList = exitList;
	}


	private List<ExitFormalityTrackerObj> exitList;

	public List<FinancialDetailsObj> getFinanceList() {
		return financeList;
	}

	public void setFinanceList(List<FinancialDetailsObj> financeList) {
		this.financeList = financeList;
	}

	public List<InductionTrackerObj> getInductionList() {
		return inductionList;
	}

	public void setInductionList(List<InductionTrackerObj> inductionList) {
		this.inductionList = inductionList;
	}

	public List<DocAndBgvObj> getBackgroundList() {
		return backgroundList;
	}

	public void setBackgroundList(List<DocAndBgvObj> backgroundList) {
		this.backgroundList = backgroundList;
	}

	public List<EmployeeBasicInfoObj> getEmployeeInfoList() {
	return employeeInfoList;
	}
	
	public void setEmployeeInfoList(List<EmployeeBasicInfoObj> employeeInfoList) {
	this.employeeInfoList = employeeInfoList;
	}
	
	
	
	private void writeBGV() throws Exception{
		 backgroundList = bgvDocDao.getDocAndBgvStatus();
		 XSSFSheet firstSheet = workbook.getSheet(BGV);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( DocAndBgvObj background : backgroundList) {
				writeBgvSheet(background, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	private void writeTravel() throws Exception{
		
		travelList = beneTrvlTrainDao.getTravelHistory();
		 XSSFSheet firstSheet = workbook.getSheet(TRAVEL);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( TravelBondInfoObj travel : travelList) {
				writeTravelSheet(travel, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	private void writeBenefits() throws Exception{
		
		benefitsList = beneTrvlTrainDao.getBenefitHistory();
		 XSSFSheet firstSheet = workbook.getSheet(BENEFIT);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( JoiningBenefitsInfoObj  benefits : benefitsList) {
				writeBenefitsSheet(benefits, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	private void writeSkills() throws Exception{
		
		skillsList = beneTrvlTrainDao.getSkillHistory();
		 XSSFSheet firstSheet = workbook.getSheet(SKILL);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( SkillSetTrainingInfoObj  skills : skillsList) {
				writeSkillsSheet(skills, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	private void writeReview() throws Exception{
		
		reviewList = promotionTrkrDao.getPromotionTrackerHistory();
		 XSSFSheet firstSheet = workbook.getSheet(REVIEW);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( ReviewCycleTracker review : reviewList) {
				writeReviewSheet(review, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	
	private void writeEndorsement() throws Exception{
		
		endorsementList = endorseTrkrDao.getEndorsementHistory();
		 XSSFSheet firstSheet = workbook.getSheet(ENDORSEMENT);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( EndorsementTrackerObj endorsement : endorsementList) {
				writeEndorsementSheet(endorsement, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	private void writePromotion() throws Exception{
		
		promotionList = promotionTrkrDao.getHireHistory();
		 XSSFSheet firstSheet = workbook.getSheet(PROMOTION);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( PromotionTrackerObj promotion : promotionList) {
				writePromotionSheet(promotion, firstSheet.createRow(rownum));

				rownum++;
			}
	}

	
	

	private void writeNominee() throws Exception{
		
		nominationList = endorseTrkrDao.getNomineeHistory();
		 XSSFSheet firstSheet = workbook.getSheet(NOMINATION);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( NomineeTrackerObj nominee : nominationList) {
				writeNomineeSheet(nominee, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	
	
	private void writeInduction() throws Exception{
		
		
		inductionList = inductionTrkrDao.getInductionTrkrRevHist();
		 XSSFSheet firstSheet = workbook.getSheet(INDUCTON);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( InductionTrackerObj Induction : inductionList) {

				writeInductionSheet(Induction, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	private void writeProbation() throws Exception{
		
		
		probationList = probationTrkrDao.getProbationRevHist();
		 XSSFSheet firstSheet = workbook.getSheet(PROBATION);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( ProbationTrackerObj probation : probationList) {

				writeProbationSheet(probation, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	private void writeProject() throws Exception{
		
		
		projectList =projecthistoryDao.getProjectHistory();
 		 XSSFSheet firstSheet = workbook.getSheet(PROJECT);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( ProjectHistoryObj project : projectList) {

				writeProjectSheet(project, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	private void writeStatic() throws Exception{
		
		
		staticList =  staticDao.getValuesForCategoryHistory();
		 XSSFSheet firstSheet = workbook.getSheet(STATIC);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( StaticTableObject statically : staticList) {

				writeStaticSheet(statically, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	
	private void writeUser() throws Exception{
		
		
		userList = userInfoDao.getUserHist();
		 XSSFSheet firstSheet = workbook.getSheet(USER);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( UserInfoObj user : userList) {

				writeUserSheet(user, firstSheet.createRow(rownum));

				rownum++;
			}
	}
	
	
	
	private void writeFinance() throws Exception{
		

	 financeList = finDetailDao.getFinDetailsRevHist();
	 XSSFSheet	firstSheet = workbook.getSheet(FINANCE);
		int rownum = firstSheet.getLastRowNum()+1;
		for ( FinancialDetailsObj finance : financeList) {

			writeFinanceSheet(finance, firstSheet.createRow(rownum));

			rownum++;
		}
	}
	
	private void writeHcm() throws Exception{
		

		hcmList = letterReqSqlDao.getAllRequestsForLetters();
		 XSSFSheet	firstSheet = workbook.getSheet(HCM);
			int rownum = firstSheet.getLastRowNum()+1;
			for ( HcmLetterReqObj hcm : hcmList) {

				writeHcmSheet(hcm, firstSheet.createRow(rownum));

				rownum++;
			}
		}
	
	
	private void writeExit() throws Exception
	{

		exitList = exitFormilityDao.getExitFormailityRevHist();
		XSSFSheet firstSheet = workbook.getSheet(EXIT);
				int rownum = firstSheet.getLastRowNum()+1;
				for ( ExitFormalityTrackerObj exit : exitList) {
		
					writeExitSheet(exit, firstSheet.createRow(rownum));
		
					rownum++;
				}
	}


	public void readFile(String fileName, String sheetName) throws IOException {

		
		//XSSFSheet firstSheet = null;
		List<EmployeeBasicInfoObj> employeeInfoList;
		
		System.out.println("fileName" + fileName);
		
		try {
			workbook = new XSSFWorkbook(new FileInputStream(fileName));
			System.out.println("workbook" + workbook +"===="+empBasicInfoDao);
			
			if (BASIC_INFO.equalsIgnoreCase(sheetName)) {
				employeeInfoList = getEmpBasicInfoDao().getBasicInfoDetails();
				XSSFSheet firstSheet =workbook.getSheet(BASIC_INFO);
				int rownum = firstSheet.getLastRowNum()+1;
				for (EmployeeBasicInfoObj employeeInfo : employeeInfoList) {

					writeBasicInfoSheet(employeeInfo, firstSheet.createRow(rownum));

					rownum++;
				}
				writeBGV();
				writeInduction();
				writeFinance();
				writeExit();
				writeProbation();
				writeUser();
				writeProject();
				writeStatic();
				writeEndorsement();
				writeNominee();	
				writePromotion();
				writeReview();
				writeSkills();
				writeTravel();
				writeBenefits();
				writeHcm();
			}
			else if("BGV".equalsIgnoreCase(sheetName)){				
				
				writeBGV();				
				
			}
			
			else if("Induction".equalsIgnoreCase(sheetName)){
				
				
				writeInduction();
					
				}
			
			
			else if("Financial Details".equalsIgnoreCase(sheetName)){
				
				writeFinance();
					
					
				}
			
			else if("Exit Formality".equalsIgnoreCase(sheetName)){
				
				writeExit();
					
				}

			else if("Probation".equalsIgnoreCase(sheetName)){
				
				writeProbation();
					
				}	
			 
			else if("UserInfo".equalsIgnoreCase(sheetName)){
				
				writeUser();
					
				}	
			
			else if("Project History".equalsIgnoreCase(sheetName)){
				
				writeProject();
					
				}
			else if("Static".equalsIgnoreCase(sheetName)){
				
				writeStatic();
					
				}
			
			else if("Endorsement".equalsIgnoreCase(sheetName)){
				
				writeEndorsement();	
				}
			
			else if("Nomination".equalsIgnoreCase(sheetName)){
				
				writeNominee();	
				}

			else if("Promotion".equalsIgnoreCase(sheetName)){
				
				writePromotion();	
				}
			
			else if("Review History".equalsIgnoreCase(sheetName)){
				
				writeReview();	
				}
			
			else if("Travel".equalsIgnoreCase(sheetName)){
							
				writeTravel();	
				}
						
			else if("Benefits".equalsIgnoreCase(sheetName)){
				
				writeBenefits();
				}
						
			else if("Skills".equalsIgnoreCase(sheetName)){
				
				writeSkills();
				}
			
			else if("Hcm Letter".equalsIgnoreCase(sheetName)){
				writeHcm();
			}
				
				String names[] = fileName.split("/");
				System.out.println("names ============"+names);
				fileName = names[0]+"/"+names[1]+"/"+"HCM_file.xlsx";
				System.out.println("fileName ============"+fileName);
	            FileOutputStream outputStream = new FileOutputStream(fileName);
	            workbook.write(outputStream);
	            workbook.close();
	            outputStream.close();

		} catch (Exception exe) {
			System.out.println("workbook" + workbook);
			exe.printStackTrace();
		}

		finally {
			workbook.close();
		
		}

	}


	void writeHcmSheet(HcmLetterReqObj hcm, Row row) throws Exception
	{
		try {
			
			Cell cell = row.createCell(0);
			cell.setCellValue((Integer.parseInt(hcm.getEmpId())));
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(hcm.getEmpIdCapco());
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(hcm.getTypeOfLetter());
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(hcm.getLetterPurpose());
		
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(hcm.getPurposeOtherReason());
			
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(hcm.getOtherVisaLetter());
			
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(hcm.getTravelCountry());
		
			Cell cell7 = row.createCell(7);
			setCellDateValue(cell7, hcm.getPassportFromDate());
			
			Cell cell8 = row.createCell(8);
			setCellDateValue(cell8, hcm.getPassportEndDate());
			
			Cell cell9 = row.createCell(9);
			setCellDateValue(cell9, hcm.getStayFrom());
			
			Cell cell10 = row.createCell(10);
			setCellDateValue(cell10, hcm.getStayTo());
			
			Cell cell11 = row.createCell(11);
			cell11.setCellValue(hcm.getDesignation());
			
			Cell cell12 = row.createCell(12);
			cell12.setCellValue(hcm.getPassportName());
			
			Cell cell13 = row.createCell(13);
			cell13.setCellValue(hcm.getGender());
			
			Cell cell14 = row.createCell(14);
			setCellDateValue(cell14, hcm.getCoverLetterFrom());
			
			Cell cell15 = row.createCell(15);
			setCellDateValue(cell15, hcm.getCoverLetterTo());
			
			Cell cell16 = row.createCell(16);
			cell16.setCellValue(hcm.getEmpName());
			
			Cell cell17 = row.createCell(17);
			cell17.setCellValue(hcm.getFisEid());
			
			Cell cell18 = row.createCell(18);
			cell18.setCellValue(hcm.getPassportNumber());

			Cell cell19 = row.createCell(19);
			cell19.setCellValue(hcm.getEmpAddress());
			
			
			Cell cell20 = row.createCell(20);
			cell20.setCellValue(hcm.getAddressedTo());
			
			Cell cell21 = row.createCell(21);
			setCellDateValue(cell21,hcm.getInsertedDate());
			Cell cell22 = row.createCell(22);
			cell22.setCellValue(hcm.getInsertedBy());
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	
	
	
	void writeTravelSheet(TravelBondInfoObj travel, Row row) throws Exception
	{
		try {
			
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(travel.getEmpId());
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(travel.getTypeOfTravel());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(travel.getTravelCountry());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(travel.getClawBackMonth());
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(travel.getNumberOfMonth());
			
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(travel.getDeletedB());
			
			Cell cell6 = row.createCell(6);
			setCellDateValue(cell6,travel.getInsertedDate());
			
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(travel.getInsertedBy());
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	void writeBenefitsSheet(JoiningBenefitsInfoObj benefit, Row row) throws Exception
	{
		try {
			
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(benefit.getEmpId());
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(benefit.getRelocAssistance());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(benefit.getRelocAssistAmt());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(benefit.getJoiningBonus());
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(benefit.getJoiningBonusAmt());
			
			
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(benefit.getReferralBonus());
			
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(benefit.getReferralBonusAmt());
			
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(benefit.getReferrealCandidateName());
			
			Cell cell8 = row.createCell(8);
			cell8.setCellValue(benefit.getDeletedB());
		
			Cell cell9 = row.createCell(9);
			setCellDateValue(cell9,benefit.getInsertedDate());
			
			Cell cell10 = row.createCell(10);
			cell10.setCellValue(benefit.getInsertedBy());
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	void writeSkillsSheet(SkillSetTrainingInfoObj skill, Row row) throws Exception
	{
		try {
			
			
			
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(skill.getEmpId());
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(skill.getPrimarySkills());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(skill.getCurrentSkill());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(skill.getCapcoCertification());
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(skill.getOtherTrainings());
			
			
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(skill.getPriorExp());
			
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(skill.getTotalExp());
			
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(skill.getCapcoExp());
			
			Cell cell8 = row.createCell(8);
			cell8.setCellValue(skill.getDeletedB());
		
			Cell cell9 = row.createCell(9);
			setCellDateValue(cell9,skill.getInsertedDate());
			
			Cell cell10 = row.createCell(10);
			cell10.setCellValue(skill.getInsertedBy());
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	
	
	
	void writeReviewSheet(ReviewCycleTracker review, Row row) throws Exception
	{
		try {
			
			
			
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(review.getEmpId());
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(review.getReviewDate());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(review.getReviewBand());
			
		
			Cell cell3 = row.createCell(3);
			setCellDateValue(cell3,review.getInsertedDate());
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(review.getInsertedBy());
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	void writePromotionSheet(PromotionTrackerObj promotion, Row row) throws Exception
	{
		try {
			
			
			
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(promotion.getEmployeeId());
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(promotion.getHiredAtBand());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(promotion.getManagerCit());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(promotion.getPerfMngrPsOrCoach());
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(promotion.getTeamLeadPs());

			Cell cell5 = row.createCell(5);
			setCellDateValue(cell5,promotion.getInsertedDate());
			
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(promotion.getInsertedBy());
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	
	void writeNomineeSheet(NomineeTrackerObj nominee, Row row) throws Exception
	{
		try {
			
			
			
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(nominee.getEmpid());
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(nominee.getDeletedB());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(nominee.getDependentsName());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(nominee.getRelation());
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(nominee.getGender());
			

			Cell cell5 = row.createCell(5);
			setCellDateValue(cell5,nominee.getDateOfBirthOfNominee());
			
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(nominee.getAgeOfNominee());
			
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(nominee.getEmailId());
			
			Cell cell8 = row.createCell(8);
			setCellDateValue(cell8,nominee.getInsertedDate());
			
			Cell cell9 = row.createCell(9);
			cell9.setCellValue(nominee.getInsertedBy());
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	void writeEndorsementSheet(EndorsementTrackerObj endorsement, Row row) throws Exception
	{
		try {
			
			
			
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(endorsement.getEmpid());
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(endorsement.getAnnualCtc());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(endorsement.getGpaSumAssured());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(endorsement.getGtlSumInsured());
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(endorsement.getGmcSumInsured());
			
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(endorsement.getDeletedB());
			
			Cell cell6 = row.createCell(6);
			setCellDateValue(cell6,endorsement.getInsertedDate());
			
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(endorsement.getInsertedBy());
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	void writeStaticSheet(StaticTableObject stat, Row row) throws Exception
	{
		try {
			
			
			
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(stat.getFieldName());
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(stat.getFieldKey());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(stat.getFieldCategory());
			
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(stat.getDeletedB());
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	void writeProjectSheet(ProjectHistoryObj project, Row row) throws Exception
	{
		try {
			
			Cell cell = row.createCell(0);
			cell.setCellValue((Integer.parseInt(project.getEmployeeId())));
			
			Cell cell1 = row.createCell(1);
			setCellDateValue(cell1,project.getFromDate());
			
			Cell cell2 = row.createCell(2);
			setCellDateValue(cell2,project.getToDate());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(project.getProjectList());
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(project.getReportingTo());
			
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(project.getManagedBy());
			
			
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(project.getDeletedB());
			
			Cell cell7 = row.createCell(7);
			setCellDateValue(cell7,project.getInsertedDate());
			Cell cell8 = row.createCell(8);
			cell8.setCellValue(project.getInsertedBy());
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	void writeUserSheet(UserInfoObj user, Row row) throws Exception
	{
		try {
			
			Cell cell = row.createCell(0);
			cell.setCellValue((Integer.parseInt(user.getEmployeeId())));
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(user.getUserName());
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(user.getCapcoId());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(user.getFisId());
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(user.getAdminRights());
			
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(user.getHcmRights());
			
			
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(user.getUserDeleted());
			
			Cell cell7 = row.createCell(7);
			setCellDateValue(cell7,user.getInsertedDate());
			Cell cell8 = row.createCell(8);
			cell8.setCellValue(user.getInsertedBy());
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	
	void writeProbationSheet(ProbationTrackerObj probation, Row row) throws Exception
	{
		try {
			
			Cell cell = row.createCell(0);
			cell.setCellValue((Integer.parseInt(probation.getEmployeeId())));
			Cell cell1 = row.createCell(1);
			setCellDateValue(cell1,probation.getProbationConfirmationDate());
			Cell cell2 = row.createCell(2);
			setCellDateValue(cell2, probation.getFirstChaserDate());
			Cell cell3 = row.createCell(3);
			setCellDateValue(cell3,probation.getSecondChaserDate());
			Cell cell4 = row.createCell(4);
			setCellDateValue(cell4, probation.getThirdChaserDate());
			Cell cell5 = row.createCell(5);
			setCellDateValue(cell5, probation.getEscalatedToHcbpOnDate());
			Cell cell6 = row.createCell(6);
			setCellDateValue(cell6, probation.getConfirmationDate());
			
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(probation.getFeedbackFormReceived());
			
			Cell cell8 = row.createCell(8);
			setCellDateValue(cell8, probation.getLetterSentDate());
			
			Cell cell9 = row.createCell(9);
			cell9.setCellValue(probation.getConfirmationStatus());
			
			Cell cell10 = row.createCell(10);
			setCellDateValue(cell10, probation.getExtendedToDate());
			
			Cell cell11 = row.createCell(11);
			cell11.setCellValue(probation.getExtensionReason());
			
			
			Cell cell12 = row.createCell(12);
			cell12.setCellValue(probation.getDeletedB());
			
			Cell cell13 = row.createCell(13);
			setCellDateValue(cell13,probation.getInsertedDate());
			Cell cell14 = row.createCell(14);
			cell14.setCellValue(probation.getInsertedBy());
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	
	void writeExitSheet(ExitFormalityTrackerObj exit, Row row) throws Exception
	{
		try {
			
			Cell cell = row.createCell(0);
			cell.setCellValue((Integer.parseInt(exit.getEmployeeId())));
			Cell cell1 = row.createCell(1);
			setCellDateValue(cell1,exit.getResignationDate());
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(exit.getNoticePeriod());
			
			Cell cell3 = row.createCell(3);
			setCellDateValue(cell3,exit.getLastWorkingDate());
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(exit.getReasonForResignation());
			
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(exit.getComments());
			
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(exit.getMedicalInsuranceNotified());
			
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(exit.getTimesheetSubmitted());
			
			Cell cell8 = row.createCell(8);
			cell8.setCellValue(exit.getExitInterviewCompleted());
			
			Cell cell9 = row.createCell(9);
			cell9.setCellValue(exit.getBirthdayAppDeletion());
			
			Cell cell10 = row.createCell(10);
			cell10.setCellValue(exit.getSalaryHold());
			
			Cell cell11 = row.createCell(11);
			cell11.setCellValue(exit.getLibraryBooksCollected());
			
			Cell cell12 = row.createCell(12);
			setCellDateValue(cell12, exit.getFnfCompleted());
			
			Cell cell13 = row.createCell(13);
			cell13.setCellValue(exit.getIdAndEmailTerminated());
			
			Cell cell14 = row.createCell(14);
			setCellDateValue(cell14, exit.getIdAndEmailTerminatedDate());
			
			Cell cell15 = row.createCell(15);
			cell15.setCellValue(exit.getReleivingLetterIssued());
			
			Cell cell16 = row.createCell(16);
			setCellDateValue(cell16, exit.getReleivingLetterDate());
			
			Cell cell17 = row.createCell(17);
			cell17.setCellValue(exit.getServiceLetterIssued());
			
			Cell cell18 = row.createCell(18);
			setCellDateValue(cell18, exit.getServiceLetterDate());
			
			Cell cell19 = row.createCell(19);
			cell19.setCellValue(exit.getBuyoutLetterIssued());
			
			Cell cell20 = row.createCell(20);
			setCellDateValue(cell20, exit.getBuyoutLetterDate());
			
			Cell cell21 = row.createCell(21);
			cell21.setCellValue(exit.getBuyoutAmountPaid());

			Cell cell22 = row.createCell(22);
			cell22.setCellValue(exit.getBuyoutAmount());
			
			Cell cell23 = row.createCell(23);
			cell23.setCellValue(exit.getBuyoutNoOfDays());
			
			Cell cell24 = row.createCell(24);
			cell24.setCellValue(exit.getBuyoutWaived());
			
			Cell cell25 = row.createCell(25);
			cell25.setCellValue(exit.getBuyoutWaivedDays());
			
			Cell cell26 = row.createCell(26);
			cell26.setCellValue(exit.getDeletedB());
			
			Cell cell27 = row.createCell(27);
			setCellDateValue(cell27,exit.getInsertedDate());
			Cell cell28 = row.createCell(28);
			cell28.setCellValue(exit.getInsertedBy());
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	void writeFinanceSheet(FinancialDetailsObj finance, Row row) throws Exception
	{
		try {
			
			Cell cell = row.createCell(0);
			cell.setCellValue((Integer.parseInt(finance.getEmployeeId())));
			Cell cell1 = row.createCell(1);
			setCellDateValue(cell1,finance.getPfDocsReceivedDate());
			Cell cell2 = row.createCell(2);
			setCellDateValue(cell2,finance.getSentToPfOfficeDate());
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(finance.getUanNumber());
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(finance.getBankName());
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(finance.getBankBranchName());
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(finance.getNameOfAccountHolder());
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(finance.getBankAccountNumber());
			
			Cell cell8 = row.createCell(8);
			cell8.setCellValue(finance.getBankIifscCode());
			
			Cell cell9 = row.createCell(9);
			cell9.setCellValue(finance.getDeletedB());
			
			
			Cell cell10 = row.createCell(10);
			setCellDateValue(cell10,finance.getInsertedDate());
			Cell cell11 = row.createCell(11);
			cell11.setCellValue(finance.getInsertedBy());
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	
	void writeInductionSheet(InductionTrackerObj induction, Row row) throws Exception
	{
		try {
			
			Cell cell = row.createCell(0);
			cell.setCellValue((Integer.parseInt(induction.getEmployeeId())));
			Cell cell1 = row.createCell(1);
			setCellDateValue(cell1,induction.getFirstEmailSentDate());
			Cell cell2 = row.createCell(2);
			setCellDateValue(cell2,induction.getSecondEmailSentDate());
			Cell cell3 = row.createCell(3);
			setCellDateValue(cell3,induction.getThirdEmailSentDate());
			Cell cell4 = row.createCell(4);
			setCellDateValue(cell4,induction.getFourthEmailSentDate());
			Cell cell5 = row.createCell(5);
			setCellDateValue(cell5,induction.getFifthEmailSentDate());
			Cell cell6 = row.createCell(6);
			setCellDateValue(cell6,induction.getAttendedOnDate());
			
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(induction.getDeletedB());
			
			
			Cell cell8 = row.createCell(8);
			setCellDateValue(cell8,induction.getInsertedDate());
			Cell cell9 = row.createCell(9);
			cell9.setCellValue(induction.getInsertedBy());
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
	
		
	}
	
	
	
		void writeBgvSheet(DocAndBgvObj backgroundInfo, Row row) throws Exception
		{
			try {
				
				Cell cell = row.createCell(0);
				cell.setCellValue((Integer.parseInt(backgroundInfo.getEmployeeId())));
				Cell cell1 = row.createCell(1);
				cell1.setCellValue(backgroundInfo.getPreOnboarding());
				Cell cell2 = row.createCell(2);
				cell2.setCellValue(backgroundInfo.getOwner());
				Cell cell3 = row.createCell(3);
				setCellDateValue(cell3,backgroundInfo.getNewHireProvisionCreationDate());
				Cell cell4 = row.createCell(4);
				setCellDateValue(cell4,backgroundInfo.getWelcomeEmailSentDate());
				Cell cell5 = row.createCell(5);
				setCellDateValue(cell5,backgroundInfo.getDocReceivedOnDate());
				Cell cell6 = row.createCell(6);
				setCellDateValue(cell6,backgroundInfo.getFirstChaserDate());
				Cell cell7 = row.createCell(7);
				setCellDateValue(cell7,backgroundInfo.getSecondChaserDate());
				Cell cell8 = row.createCell(8);
				setCellDateValue(cell8,backgroundInfo.getThirdChaserDate());
				Cell cell9 = row.createCell(9);
				setCellDateValue(cell9,backgroundInfo.getEscalationDate());
				Cell cell10 = row.createCell(10);
				cell10.setCellValue(backgroundInfo.getSignedOfferLetterReceived());
				Cell cell11 = row.createCell(11);
				cell11.setCellValue(backgroundInfo.getCvReceived());
				Cell cell12 = row.createCell(12);
				cell12.setCellValue(backgroundInfo.getPersonalDetailsFormReceived());
				Cell cell13 = row.createCell(13);
				cell13.setCellValue(backgroundInfo.getMedicliamNominationReceived());
				Cell cell14 = row.createCell(14);
				cell14.setCellValue(backgroundInfo.getPfNominationReceived());
				Cell cell15 = row.createCell(15);
				cell15.setCellValue(backgroundInfo.getPassportSizePhotoReceived());
				Cell cell16 = row.createCell(16);
				cell16.setCellValue(backgroundInfo.getNdaReceived());
				Cell cell17 = row.createCell(17);
				cell17.setCellValue(backgroundInfo.getSignedCodeOfConductReceived());
				Cell cell18 = row.createCell(18);
				cell18.setCellValue(backgroundInfo.getReleivingLetterReceived());
				Cell cell19 = row.createCell(19);
				cell19.setCellValue(backgroundInfo.getExperienceLetterReceived());
				Cell cell20 = row.createCell(20);
				cell20.setCellValue(backgroundInfo.getLastPayslipsReceived());
				Cell cell21 = row.createCell(21);
				cell21.setCellValue(backgroundInfo.getMarksheetsReceived());
				Cell cell22 = row.createCell(22);
				cell22.setCellValue(backgroundInfo.getPanCardReceived());
				Cell cell23 = row.createCell(23);
				cell23.setCellValue(backgroundInfo.getPassportNumberReceived());
				Cell cell24 = row.createCell(24);
				setCellDateValue(cell24,backgroundInfo.getPassportExpiry());
				Cell cell25 = row.createCell(25);
				cell25.setCellValue(backgroundInfo.getVisaType());
				Cell cell26 = row.createCell(26);
				cell26.setCellValue(backgroundInfo.getCountry());
				Cell cell27 = row.createCell(27);
				setCellDateValue(cell27, backgroundInfo.getBgvInitiatedDate());
				Cell cell28 = row.createCell(28);
				cell28.setCellValue(backgroundInfo.getBgvRefNo());
				Cell cell29 = row.createCell(29);
				setCellDateValue(cell29,backgroundInfo.getBgvInterimReportDate());
				Cell cell30 = row.createCell(30);
				setCellDateValue(cell30,backgroundInfo.getBgvFinalReportDate());
				Cell cell31 = row.createCell(31);
				cell31.setCellValue(backgroundInfo.getBgvReportColorCode());
				Cell cell32 = row.createCell(32);
				cell32.setCellValue(backgroundInfo.getBgvSignOffBy());
				Cell cell33 = row.createCell(33);
				setCellDateValue(cell33,backgroundInfo.getBgvSignOffDate());
				Cell cell34 = row.createCell(34);
				setCellDateValue(cell34,backgroundInfo.getBgvClosureDate());
				Cell cell35 = row.createCell(35);
				cell35.setCellValue(backgroundInfo.getPackageInitiated());
				Cell cell36 = row.createCell(36);
				cell36.setCellValue(backgroundInfo.getEmployment());
				Cell cell37 = row.createCell(37);
				cell37.setCellValue(backgroundInfo.getCourtCheck());
				Cell cell38 = row.createCell(38);
				cell38.setCellValue(backgroundInfo.getIdentityCheck());
				Cell cell39 = row.createCell(39);
				cell39.setCellValue(backgroundInfo.getCountryCheck());
				Cell cell40 = row.createCell(40);
				cell40.setCellValue(backgroundInfo.getReferenceCheck());
				Cell cell41 = row.createCell(41);
				cell41.setCellValue(backgroundInfo.getCvCheck());
				Cell cell42 = row.createCell(42);
				cell42.setCellValue(backgroundInfo.getDeletedB());
				Cell cell43 = row.createCell(43);
				setCellDateValue(cell43,backgroundInfo.getInsertedDate());
				Cell cell44 = row.createCell(44);
				cell44.setCellValue(backgroundInfo.getInsertedBy());
				
				
				
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		
		
			
		}

	private void setCellDateValue(Cell cell,Date date){
		if (date == null )
		{
			cell.setCellValue("");
		}
		else
		{		
			
			cell.setCellValue(SDF.format(date));
		}
	}
	
	
	

		void writeBasicInfoSheet(EmployeeBasicInfoObj employeeInfo, Row row) throws Exception {

		try {
			
			
			
			Cell cell = row.createCell(0);
			cell.setCellValue((Integer.parseInt(employeeInfo.getEmpId())));
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(employeeInfo.getEmpName());
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(employeeInfo.getSecondaryEmpId());
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(employeeInfo.getLoginId());
			Cell cell4 = row.createCell(4);	
			cell4.setCellValue(employeeInfo.getDesignation());
			Cell cell5 = row.createCell(5); 
			cell5.setCellValue(employeeInfo.getGender());
			
			
			Cell cell6 = row.createCell(6);
			setCellDateValue(cell6,employeeInfo.getDateOfBirth());	
			
			Cell cell7 = row.createCell(7);
			setCellDateValue(cell7,employeeInfo.getJoiningDate());
			
			Cell cell8 = row.createCell(8);
			setCellDateValue(cell8,employeeInfo.getStartDate());
		
			Cell cell9 = row.createCell(9);
			cell9.setCellValue(employeeInfo.getOfficialEmailId());
			Cell cell10 = row.createCell(10);
			cell10.setCellValue(employeeInfo.getPerosnalEmailId());
			Cell cell11 = row.createCell(11);
			cell11.setCellValue(employeeInfo.getEmployeeStatus());
			Cell cell12 = row.createCell(12);
			cell12.setCellValue(employeeInfo.getFileNo());
			Cell cell13 = row.createCell(13);
			cell13.setCellValue(employeeInfo.getHcBpId());
			Cell cell14 = row.createCell(14);
			cell14.setCellValue(employeeInfo.getContactNumber());
			Cell cell15 = row.createCell(15);
			cell15.setCellValue(employeeInfo.getProject());
			Cell cell16 = row.createCell(16);
			cell16.setCellValue(employeeInfo.getAssignedHouse());
			Cell cell17 = row.createCell(17);
			cell17.setCellValue(employeeInfo.getAtpB());
			Cell cell18 = row.createCell(18);
			cell18.setCellValue(employeeInfo.getService());
			Cell cell19 = row.createCell(19);
			cell19.setCellValue(employeeInfo.getBillingStatus());
			Cell cell20 = row.createCell(20);
			cell20.setCellValue(employeeInfo.getFteOrContractor());
			Cell cell21 = row.createCell(21);
			cell21.setCellValue(employeeInfo.getDeletedB());
			Cell cell22 = row.createCell(22);
			cell22.setCellValue(employeeInfo.getTypeOfEmployment());
			Cell cell23 = row.createCell(23);
			cell23.setCellValue(employeeInfo.getInsertedBy());
			Cell cell24 = row.createCell(24);
			setCellDateValue(cell24,employeeInfo.getInsertedDate());
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	
		}
		
	
		
	/*private String readCellValue(Cell c) {
		String cellValue = "";
		if (c == null)
			return "";
		switch (c.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			cellValue = c.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = "" + c.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cellValue = "" + c.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			cellValue = "";
			break;
		}

		if (StringUtils.isNullOrEmpty(cellValue))
			cellValue = "";
		return cellValue;
	}*/



		public void readuploadFile(InputStream inputData) {
			
			
			List<EmployeeBasicInfoObj> employeeInfoList =new ArrayList<EmployeeBasicInfoObj>();
			List<DocAndBgvObj> backgroundList = new ArrayList<DocAndBgvObj>();
			List<InductionTrackerObj> inductionList = new ArrayList<InductionTrackerObj>();
			List<FinancialDetailsObj> financeList = new ArrayList<FinancialDetailsObj>();
			List<ExitFormalityTrackerObj> exitList = new ArrayList<ExitFormalityTrackerObj>();
			List<ProbationTrackerObj> probationList = new ArrayList<ProbationTrackerObj>();
			List<UserInfoObj> userList = new ArrayList<UserInfoObj>();
			List<ProjectHistoryObj> projectList = new ArrayList<ProjectHistoryObj>();
			List<TravelBondInfoObj> travelList = new ArrayList<TravelBondInfoObj>();
			List<SkillSetTrainingInfoObj> skillList = new ArrayList<SkillSetTrainingInfoObj>();
			List<JoiningBenefitsInfoObj> benefitList = new ArrayList<JoiningBenefitsInfoObj>();
			List<EndorsementTrackerObj> endoList = new ArrayList<EndorsementTrackerObj>();
			List<NomineeTrackerObj> nomineeList = new ArrayList<NomineeTrackerObj>();
			List<PromotionTrackerObj> promoList = new ArrayList<PromotionTrackerObj>();
			List<StaticTableObject> staticList = new ArrayList<StaticTableObject>();
 			List<HcmLetterReqObj> hcmList = new ArrayList<HcmLetterReqObj>();
			List<ReviewCycleTracker> reviewList = new ArrayList<ReviewCycleTracker>();
 			
			try {
				workbook = new XSSFWorkbook(inputData);
				System.out.println("workbook" + workbook +"===="+empBasicInfoDao);
				// BASIC INFO UPLOAD FUNCTIONALITY
				XSSFSheet firstSheet =workbook.getSheet(BASIC_INFO);
				XSSFSheet secondSheet =workbook.getSheet(BGV);
				XSSFSheet thirdsheet =workbook.getSheet(INDUCTON);
				XSSFSheet fourthsheet =workbook.getSheet(FINANCE);
				XSSFSheet fifthsheet =workbook.getSheet(EXIT);
				XSSFSheet sixthsheet =workbook.getSheet(PROBATION);
				XSSFSheet sevensheet =workbook.getSheet(USER);
				XSSFSheet eightsheet =workbook.getSheet(PROMOTION);
				XSSFSheet ninethsheet =workbook.getSheet(REVIEW);
				XSSFSheet tenthsheet =workbook.getSheet(ENDORSEMENT);
				XSSFSheet elevensheet =workbook.getSheet(NOMINATION);
				XSSFSheet twelvesheet =workbook.getSheet(PROJECT);
				XSSFSheet thirteensheet =workbook.getSheet(STATIC);
				XSSFSheet fifteensheet =workbook.getSheet(TRAVEL);
				XSSFSheet sixteenth =workbook.getSheet(SKILL);
				XSSFSheet seventeenth =workbook.getSheet(BENEFIT);
				XSSFSheet fourtteensheet =workbook.getSheet(HCM);

									
									

				
				
				if(firstSheet.getSheetName().equalsIgnoreCase(BASIC_INFO))
				{
				System.out.println("First sheet"+ firstSheet.getLastRowNum());
					DataFormatter formatter = new DataFormatter();
					EmployeeBasicInfoObj obj = new EmployeeBasicInfoObj();
					for(int i=0;i<=firstSheet.getLastRowNum();i++)
					{
						
						if(i!=0 && i!=1)
						{
							Row row = firstSheet.getRow(i);
							
							Cell cell1 =row.getCell(0);
							System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							obj.setEmpId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							
							Cell cell2 =row.getCell(1);
							System.out.println(cell2);
							obj.setEmpName(formatter.formatCellValue(cell2));
							
							Cell cell3 =row.getCell(2);
							System.out.println(cell3);
							obj.setSecondaryEmpId(formatter.formatCellValue(cell3));
							
							Cell cell4 =row.getCell(3);
							System.out.println(cell4);
							obj.setLoginId(formatter.formatCellValue(cell4));
							
							Cell cell5 =row.getCell(4);
							System.out.println(cell5);
							obj.setDesignation(formatter.formatCellValue(cell5));
							
							Cell cell6 =row.getCell(5);
							System.out.println(cell6);
							obj.setGender(Boolean.valueOf(formatter.formatCellValue(cell6)));
							
							Cell cell7 =row.getCell(6);
							System.out.println(cell7);
							obj.setDateOfBirth(SDF.parse(formatter.formatCellValue(cell7)));
						
							
							Cell cell8 =row.getCell(7);
							System.out.println(cell8);
							obj.setJoiningDate(SDF.parse(formatter.formatCellValue(cell8)));
							
							Cell cell9 =row.getCell(8);
							System.out.println(cell9);
							obj.setStartDate(SDF.parse(formatter.formatCellValue(cell9)));
							
							Cell cell10 =row.getCell(9);
							System.out.println(cell10);
							obj.setOfficialEmailId(formatter.formatCellValue(cell10));
							
							Cell cell11 =row.getCell(10);
							System.out.println(cell11);
							obj.setPerosnalEmailId(formatter.formatCellValue(cell11));
							
							Cell cell12 =row.getCell(11);
							System.out.println(cell12);
							obj.setEmployeeStatus(formatter.formatCellValue(cell12));
							
							Cell cell13 =row.getCell(12);
							System.out.println(new java.text.DecimalFormat("0").format(cell13.getNumericCellValue()));
							obj.setFileNo(new java.text.DecimalFormat("0").format(cell13.getNumericCellValue()));
							
							Cell cell14 =row.getCell(13);
							System.out.println(cell14);
							obj.setHcBpId(formatter.formatCellValue(cell14));
							
							Cell cell15 =row.getCell(14);
							System.out.println(new java.text.DecimalFormat("0").format(cell15.getNumericCellValue()));
							obj.setContactNumber(new java.text.DecimalFormat("0").format(cell15.getNumericCellValue()));
							
							
							Cell cell16 =row.getCell(15);
							System.out.println(cell16);
							obj.setProject(formatter.formatCellValue(cell16));
							
							Cell cell17 =row.getCell(16);
							System.out.println(cell17);
							obj.setAssignedHouse(formatter.formatCellValue(cell17));
							
							Cell cell18 =row.getCell(17);
							System.out.println(cell18);
							obj.setAtpB(Boolean.valueOf(formatter.formatCellValue(cell18)));
							
							Cell cell19 =row.getCell(18);
							System.out.println(cell19);
							obj.setService(Boolean.valueOf(formatter.formatCellValue(cell19)));
							
							Cell cell20 =row.getCell(19);
							System.out.println(cell20);
							obj.setBillingStatus(Boolean.valueOf(formatter.formatCellValue(cell20)));
							
							Cell cell21 =row.getCell(20);
							System.out.println(cell21);
							obj.setFteOrContractor(Boolean.valueOf(formatter.formatCellValue(cell21)));
							
							Cell cell22 =row.getCell(21);
							System.out.println(cell22);
							obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell22)));
							
							Cell cell23 =row.getCell(22);
							System.out.println(cell23);
							obj.setTypeOfEmployment(formatter.formatCellValue(cell23));
							
							Cell cell24 =row.getCell(23);
							System.out.println(cell24);
							obj.setInsertedBy(formatter.formatCellValue(cell24));
							
							
							employeeInfoList.add(obj);
						    System.out.println("LIST OF EMPLOYEE"+employeeInfoList);
						  
						    if(obj.getEmpId() == null)
						    {
						    	System.out.println("MOHTI GANGIL INSER");
							    
						    	empBasicInfoDao.updateEmpdetails(employeeInfoList);
						    }
						    else
						    {
						    	System.out.println("MOHTI GANGIL UPDATE");
							    empBasicInfoDao.updateEmpdetails(employeeInfoList);
						    }
							
							
						}
						
					
						
						
					}
					
					
					
					
				}
				
					
				// BGV UPLOAD FUNCTIONALITY
					
					
				 if(secondSheet.getSheetName().equalsIgnoreCase(BGV))
					{
					System.out.println("Second sheet"+ secondSheet.getLastRowNum());
					DataFormatter formatter = new DataFormatter();
					DocAndBgvObj obj1 = new DocAndBgvObj();
					for(int i=0;i<=secondSheet.getLastRowNum();i++)
					{
						
						if(i!=0 && i!=1)
						{
							Row row = secondSheet.getRow(i);
							
							Cell cell1 =row.getCell(0);
							System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							obj1.setEmployeeId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							
							Cell cell2 =row.getCell(1);
							System.out.println(cell2);
							obj1.setPreOnboarding(Boolean.valueOf(formatter.formatCellValue(cell2)));
							
							Cell cell3 =row.getCell(2);
							System.out.println(cell3);
							obj1.setOwner(formatter.formatCellValue(cell3));
							
							Cell cell4 =row.getCell(3);
							System.out.println(cell4);
							obj1.setNewHireProvisionCreationDate(SDF.parse(formatter.formatCellValue(cell4)));
							
							Cell cell5 =row.getCell(4);
							System.out.println(cell5);
							obj1.setWelcomeEmailSentDate(SDF.parse(formatter.formatCellValue(cell5)));
							
							Cell cell6 =row.getCell(5);
							System.out.println(cell6);
							obj1.setDocReceivedOnDate(SDF.parse(formatter.formatCellValue(cell6)));
							
							Cell cell7 =row.getCell(6);
							System.out.println(cell7);
							obj1.setFirstChaserDate(SDF.parse(formatter.formatCellValue(cell7)));
						
							Cell cell8 =row.getCell(7);
							System.out.println(cell8);
							obj1.setSecondChaserDate(SDF.parse(formatter.formatCellValue(cell8)));
							
							Cell cell9 =row.getCell(8);
							System.out.println(cell9);
							obj1.setThirdChaserDate(SDF.parse(formatter.formatCellValue(cell9)));
							
							Cell cell10 =row.getCell(9);
							System.out.println(cell10);
							obj1.setEscalationDate(SDF.parse(formatter.formatCellValue(cell10)));
							
							Cell cell11 =row.getCell(10);
							System.out.println(cell11);
							obj1.setSignedOfferLetterReceived(Boolean.valueOf(formatter.formatCellValue(cell11)));
							
							Cell cell12 =row.getCell(11);
							System.out.println(cell12);
							obj1.setCvReceived(Boolean.valueOf(formatter.formatCellValue(cell12)));
							
							Cell cell13 =row.getCell(12);
							System.out.println(cell13);
							obj1.setPersonalDetailsFormReceived(Boolean.valueOf(formatter.formatCellValue(cell13)));
							
							Cell cell14 =row.getCell(13);
							System.out.println(cell14);
							obj1.setMedicliamNominationReceived(Boolean.valueOf(formatter.formatCellValue(cell14)));
							
							Cell cell15 =row.getCell(14);
							System.out.println(cell15);
							obj1.setPfNominationReceived(Boolean.valueOf(formatter.formatCellValue(cell15)));
							
							Cell cell16 =row.getCell(15);
							System.out.println(cell16);
							obj1.setPassportSizePhotoReceived(Boolean.valueOf(formatter.formatCellValue(cell16)));
							
							Cell cell17 =row.getCell(16);
							System.out.println(cell17);
							obj1.setNdaReceived(Boolean.valueOf(formatter.formatCellValue(cell17)));
							
							
							Cell cell18 =row.getCell(17);
							System.out.println(cell18);
							obj1.setSignedCodeOfConductReceived(Boolean.valueOf(formatter.formatCellValue(cell18)));
							
							Cell cell19 =row.getCell(18);
							System.out.println(cell19);
							obj1.setReleivingLetterReceived(Boolean.valueOf(formatter.formatCellValue(cell19)));
							
							Cell cell20 =row.getCell(19);
							System.out.println(cell20);
							obj1.setExperienceLetterReceived(Boolean.valueOf(formatter.formatCellValue(cell20)));
							
							Cell cell21 =row.getCell(20);
							System.out.println(cell21);
							obj1.setLastPayslipsReceived(Boolean.valueOf(formatter.formatCellValue(cell21)));
							
							Cell cell22 =row.getCell(21);
							System.out.println(cell22);
							obj1.setMarksheetsReceived(Boolean.valueOf(formatter.formatCellValue(cell22)));
							
							Cell cell23 =row.getCell(22);
							System.out.println(cell23);
							obj1.setPanCardReceived(formatter.formatCellValue(cell23));
							
							Cell cell24 =row.getCell(23);
							System.out.println(cell24);
							obj1.setPassportNumberReceived(formatter.formatCellValue(cell24));

							
							Cell cell25 =row.getCell(24);
							System.out.println(cell25);
							obj1.setPassportExpiry(SDF.parse(formatter.formatCellValue(cell25)));
							
							
							Cell cell26 =row.getCell(25);
							System.out.println(cell26);
							obj1.setVisaType(formatter.formatCellValue(cell26));
							
							
							Cell cell27 =row.getCell(26);
							System.out.println(cell27);
							obj1.setCountry(formatter.formatCellValue(cell27));
							
							
							
							Cell cell28 =row.getCell(27);
							System.out.println(cell28);
							obj1.setBgvInitiatedDate(SDF.parse(formatter.formatCellValue(cell28)));
							
							Cell cell29 =row.getCell(28);
							System.out.println(cell29);
							obj1.setBgvRefNo(formatter.formatCellValue(cell29));
							
							Cell cell30 =row.getCell(29);
							System.out.println(cell30);
							obj1.setBgvInterimReportDate(SDF.parse(formatter.formatCellValue(cell30)));

							
							Cell cell31 =row.getCell(30);
							System.out.println(cell31);
							obj1.setBgvFinalReportDate(SDF.parse(formatter.formatCellValue(cell31)));

							
							Cell cell32 =row.getCell(31);
							System.out.println(cell32);
							obj1.setBgvReportColorCode(formatter.formatCellValue(cell32));
							
							
							Cell cell33 =row.getCell(32);
							System.out.println(cell33);
							obj1.setBgvSignOffBy(formatter.formatCellValue(cell33));
							
							Cell cell34 =row.getCell(33);
							System.out.println(cell34);
							obj1.setBgvSignOffDate(SDF.parse(formatter.formatCellValue(cell34)));
							
							Cell cell35 =row.getCell(34);
							System.out.println(cell35);
							obj1.setBgvClosureDate(SDF.parse(formatter.formatCellValue(cell35)));
							
							Cell cell36 =row.getCell(35);
							System.out.println(cell36);
							obj1.setPackageInitiated(formatter.formatCellValue(cell36));
							
							Cell cell37 =row.getCell(36);
							System.out.println(cell37);
							obj1.setEmployment(formatter.formatCellValue(cell37));
							
							Cell cell38 =row.getCell(37);
							System.out.println(cell38);
							obj1.setCourtCheck(formatter.formatCellValue(cell38));
							
							Cell cell39 =row.getCell(38);
							System.out.println(cell39);
							obj1.setIdentityCheck(formatter.formatCellValue(cell39));
							
							Cell cell40 =row.getCell(39);
							System.out.println(cell40);
							obj1.setCountryCheck(formatter.formatCellValue(cell40));
							
							Cell cell41 =row.getCell(40);
							System.out.println(cell41);
							obj1.setReferenceCheck(formatter.formatCellValue(cell41));
							
							Cell cell42 =row.getCell(41);
							System.out.println(cell42);
							obj1.setCvCheck(formatter.formatCellValue(cell42));
							
							Cell cell43 =row.getCell(42);
							System.out.println(cell43);
							obj1.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell43)));
							
							Cell cell44 =row.getCell(44);
							System.out.println(cell44);
							obj1.setInsertedBy(formatter.formatCellValue(cell44));
							
							backgroundList.add(obj1);
						    System.out.println("LIST OF EMPLOYEE"+backgroundList);
						  
						    if(obj1.getEmployeeId() == null)
						    {
						    	System.out.println("MOHTI GANGIL INSER");
							    
						    	bgvDocDao.updateEmpDocdetails(backgroundList);
						    }
						    else
						    {
						    	System.out.println("MOHTI GANGIL UPDATE");
						    	bgvDocDao.updateEmpDocdetails(backgroundList);
						    }
							
							
						}
						
						
						
					}
					
					
					
					}
					
					
					// INDUCTION UPLOAD FUNCTIONALITY
					
				 if(thirdsheet.getSheetName().equalsIgnoreCase(INDUCTON))
					{
					System.out.println("Third sheet"+ thirdsheet.getLastRowNum());
					DataFormatter formatter = new DataFormatter();
					InductionTrackerObj obj1 = new InductionTrackerObj();
					for(int i=0;i<=thirdsheet.getLastRowNum();i++)
					{
						
						if(i!=0 && i!=1)
						{
							Row row = thirdsheet.getRow(i);
							
							Cell cell1 =row.getCell(0);
							System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							obj1.setEmployeeId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							
							Cell cell2 =row.getCell(1);
							System.out.println(cell2);
							obj1.setFirstEmailSentDate(SDF.parse(formatter.formatCellValue(cell2)));
							
							Cell cell3 =row.getCell(2);
							System.out.println(cell3);
							obj1.setSecondEmailSentDate(SDF.parse(formatter.formatCellValue(cell3)));
							
							Cell cell4 =row.getCell(3);
							System.out.println(cell4);
							obj1.setThirdEmailSentDate(SDF.parse(formatter.formatCellValue(cell4)));
							
							Cell cell5 =row.getCell(4);
							System.out.println(cell5);
							obj1.setFourthEmailSentDate(SDF.parse(formatter.formatCellValue(cell5)));
							
							Cell cell6 =row.getCell(5);
							System.out.println(cell6);
							obj1.setFifthEmailSentDate(SDF.parse(formatter.formatCellValue(cell6)));
							
							Cell cell7 =row.getCell(6);
							System.out.println(cell7);
							obj1.setAttendedOnDate(SDF.parse(formatter.formatCellValue(cell7)));
						
							Cell cell8 =row.getCell(7);
							System.out.println(cell8);
							obj1.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell8)));
							
							Cell cell9 =row.getCell(9);
							System.out.println(cell9);
							obj1.setInsertedBy(formatter.formatCellValue(cell9));
							
							inductionList.add(obj1);
						    System.out.println("LIST OF EMPLOYEE"+inductionList);
						  
						    if(obj1.getEmployeeId() == null)
						    {
						    	System.out.println("MOHTI GANGIL INSER");
							    
						    	inductionTrkrDao.updateEmpInductiondetails(inductionList);
						    }
						    else
						    {
						    	System.out.println("MOHTI GANGIL UPDATE");
						    	inductionTrkrDao.updateEmpInductiondetails(inductionList);
						    }
							
							
						}
						
					
						
						
					}
					
					
					}
					
					// FINANCE  UPLOAD FUNCTIONALITY
				
				 if(fourthsheet.getSheetName().equalsIgnoreCase(FINANCE))
					{
					System.out.println("Fourth sheet"+ fourthsheet.getLastRowNum());
					DataFormatter formatter = new DataFormatter();
					FinancialDetailsObj obj1 = new FinancialDetailsObj();
					for(int i=0;i<=fourthsheet.getLastRowNum();i++)
					{
						
						if(i!=0 && i!=1)
						{
							Row row = fourthsheet.getRow(i);
							
							Cell cell1 =row.getCell(0);
							System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							obj1.setEmployeeId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							
							Cell cell2 =row.getCell(1);
							System.out.println(cell2);
							obj1.setPfDocsReceivedDate(SDF.parse(formatter.formatCellValue(cell2)));
							
							Cell cell3 =row.getCell(2);
							System.out.println(cell3);
							obj1.setSentToPfOfficeDate(SDF.parse(formatter.formatCellValue(cell3)));
							
							Cell cell4 =row.getCell(3);
							System.out.println(new java.text.DecimalFormat("0").format(cell4.getNumericCellValue()));
							obj1.setUanNumber(new java.text.DecimalFormat("0").format(cell4.getNumericCellValue()));
							
							Cell cell5 =row.getCell(4);
							System.out.println(cell5);
							obj1.setBankName(formatter.formatCellValue(cell5));
							
							Cell cell6 =row.getCell(5);
							System.out.println(cell6);
							obj1.setBankBranchName(formatter.formatCellValue(cell6));
							
							Cell cell7 =row.getCell(6);
							System.out.println(cell7);
							obj1.setNameOfAccountHolder(formatter.formatCellValue(cell7));
							
							Cell cell8 =row.getCell(7);
							System.out.println(cell8);
							obj1.setBankAccountNumber(formatter.formatCellValue(cell8));
							
							Cell cell9 =row.getCell(8);
							System.out.println(cell9);
							obj1.setBankIifscCode(formatter.formatCellValue(cell9));
						
							Cell cell10 =row.getCell(9);
							System.out.println(cell10);
							obj1.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell10)));
							
							Cell cell11 =row.getCell(11);
							System.out.println(cell11);
							obj1.setInsertedBy(formatter.formatCellValue(cell11));
							
							financeList.add(obj1);
						    System.out.println("LIST OF EMPLOYEE"+financeList);
						  
						    if(obj1.getEmployeeId() == null)
						    {
						    	System.out.println("MOHTI GANGIL INSER");
							    
						    	finDetailDao.updateEmpFinancialdetails(financeList);
						    }
						    else
						    {
						    	System.out.println("MOHTI GANGIL UPDATE");
						    	finDetailDao.updateEmpFinancialdetails(financeList);
						    }
							
							
						}
						
						
						
					}
					
					
					
					}
					
					// Exit Formality  UPLOAD FUNCTIONALITY
					
					
					
				 if(fifthsheet.getSheetName().equalsIgnoreCase(EXIT))
					{
					System.out.println("Fifth sheet"+ fifthsheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						ExitFormalityTrackerObj obj = new ExitFormalityTrackerObj();
						for(int i=0;i<=fifthsheet.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = fifthsheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmployeeId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setResignationDate(SDF.parse(formatter.formatCellValue(cell2)));
								
								Cell cell3 =row.getCell(2);
								System.out.println(new java.text.DecimalFormat("0").format(cell3.getNumericCellValue()));
								obj.setNoticePeriod(new java.text.DecimalFormat("0").format(cell3.getNumericCellValue()));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setLastWorkingDate(SDF.parse(formatter.formatCellValue(cell4)));
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setReasonForResignation(formatter.formatCellValue(cell5));
								
								Cell cell6 =row.getCell(5);
								System.out.println(cell6);
								obj.setComments(formatter.formatCellValue(cell6));
								
								Cell cell7 =row.getCell(6);
								System.out.println(cell7);
								obj.setMedicalInsuranceNotified(Boolean.valueOf(formatter.formatCellValue(cell7)));
							
								Cell cell8 =row.getCell(7);
								System.out.println(cell8);
								obj.setTimesheetSubmitted(Boolean.valueOf(formatter.formatCellValue(cell8)));
								
								Cell cell9 =row.getCell(8);
								System.out.println(cell9);
								obj.setExitInterviewCompleted(Boolean.valueOf(formatter.formatCellValue(cell9)));
								
								Cell cell10 =row.getCell(9);
								System.out.println(cell10);
								obj.setBirthdayAppDeletion(Boolean.valueOf(formatter.formatCellValue(cell10)));
								
								Cell cell11 =row.getCell(10);
								System.out.println(cell11);
								obj.setSalaryHold(Boolean.valueOf(formatter.formatCellValue(cell11)));
								
								Cell cell12 =row.getCell(11);
								System.out.println(cell12);
								obj.setLibraryBooksCollected(formatter.formatCellValue(cell12));
								
								Cell cell13 =row.getCell(12);
								System.out.println(cell13);
								obj.setFnfCompleted(SDF.parse(formatter.formatCellValue(cell13)));
								
								Cell cell14 =row.getCell(13);
								System.out.println(cell14);
								obj.setIdAndEmailTerminated(Boolean.valueOf(formatter.formatCellValue(cell14)));
								
								Cell cell15 =row.getCell(14);
								System.out.println(cell15);
								obj.setIdAndEmailTerminatedDate(SDF.parse(formatter.formatCellValue(cell15)));
								
								Cell cell16 =row.getCell(15);
								System.out.println(cell16);
								obj.setReleivingLetterIssued(Boolean.valueOf(formatter.formatCellValue(cell16)));
								
								Cell cell17 =row.getCell(16);
								System.out.println(cell17);
								obj.setReleivingLetterDate(SDF.parse(formatter.formatCellValue(cell17)));	
								
								
								
								Cell cell18 =row.getCell(17);
								System.out.println(cell18);
								obj.setServiceLetterIssued(Boolean.valueOf(formatter.formatCellValue(cell18)));
								
								Cell cell19 =row.getCell(18);
								System.out.println(cell19);
								obj.setServiceLetterDate(SDF.parse(formatter.formatCellValue(cell19)));	
								
								
								Cell cell20 =row.getCell(19);
								System.out.println(cell20);
								obj.setBuyoutLetterIssued(Boolean.valueOf(formatter.formatCellValue(cell20)));
								
								Cell cell21 =row.getCell(20);
								System.out.println(cell21);
								obj.setBuyoutLetterDate(SDF.parse(formatter.formatCellValue(cell21)));
								
								Cell cell22 =row.getCell(21);
								System.out.println(cell22);
								obj.setBuyoutAmountPaid(Boolean.valueOf(formatter.formatCellValue(cell22)));
								
								Cell cell23 =row.getCell(22);
								System.out.println(cell23);
								obj.setBuyoutAmount(formatter.formatCellValue(cell23));
								
								Cell cell24 =row.getCell(23);
								System.out.println(cell24);
								obj.setBuyoutNoOfDays(formatter.formatCellValue(cell24));
								
								Cell cell25 =row.getCell(24);
								System.out.println(cell25);
								obj.setBuyoutWaived(Boolean.valueOf(formatter.formatCellValue(cell25)));
								
								Cell cell26 =row.getCell(25);
								System.out.println(cell26);
								obj.setBuyoutWaivedDays(formatter.formatCellValue(cell26));
								
								Cell cell27 =row.getCell(26);
								System.out.println(cell27);
								obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell27)));
								
								
								Cell cell29 =row.getCell(28);
								System.out.println(cell29);
								obj.setInsertedBy(formatter.formatCellValue(cell29));
								
								exitList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+exitList);
							  
							    if(obj.getEmployeeId() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	exitFormilityDao.updateEmpExitFormalitydetails(exitList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	exitFormilityDao.updateEmpExitFormalitydetails(exitList);
							    }
								
									
							}
							
							
							
						}
						
						
					}
					
					
					// Probation Tracker  UPLOAD FUNCTIONALITY
					
					
					
				 if(sixthsheet.getSheetName().equalsIgnoreCase(PROBATION))
					{
					System.out.println("sixth sheet"+ sixthsheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						ProbationTrackerObj obj = new ProbationTrackerObj();
						for(int i=0;i<=sixthsheet.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = sixthsheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmployeeId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setProbationConfirmationDate(SDF.parse(formatter.formatCellValue(cell2)));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setFirstChaserDate(SDF.parse(formatter.formatCellValue(cell3)));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setSecondChaserDate(SDF.parse(formatter.formatCellValue(cell4)));
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setThirdChaserDate(SDF.parse(formatter.formatCellValue(cell5)));
								
								Cell cell6 =row.getCell(5);
								System.out.println(cell6);
								obj.setEscalatedToHcbpOnDate(SDF.parse(formatter.formatCellValue(cell6)));
								
								Cell cell7 =row.getCell(6);
								System.out.println(cell7);
								obj.setConfirmationDate(SDF.parse(formatter.formatCellValue(cell7)));
							
								Cell cell8 =row.getCell(7);
								System.out.println(cell8);
								obj.setFeedbackFormReceived(Boolean.valueOf(formatter.formatCellValue(cell8)));
								
								Cell cell9 =row.getCell(8);
								System.out.println(cell9);
								obj.setLetterSentDate(SDF.parse(formatter.formatCellValue(cell9)));
								
								Cell cell10 =row.getCell(9);
								System.out.println(cell10);
								obj.setConfirmationStatus(Boolean.valueOf(formatter.formatCellValue(cell10)));
								
								Cell cell11 =row.getCell(10);
								System.out.println(cell11);
								obj.setExtendedToDate(SDF.parse(formatter.formatCellValue(cell11)));
								
								Cell cell12 =row.getCell(11);
								System.out.println(cell12);
								obj.setExtensionReason(formatter.formatCellValue(cell12));
								
								Cell cell13 =row.getCell(12);
								System.out.println(cell13);
								obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell13)));
								
								
								Cell cell14 =row.getCell(14);
								System.out.println(cell14);
								obj.setInsertedBy(formatter.formatCellValue(cell14));
								
								probationList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+probationList);
							  
							    if(obj.getEmployeeId() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	probationTrkrDao.updateEmpProbationdetails(probationList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	probationTrkrDao.updateEmpProbationdetails(probationList);
							    }
								
									
							}
							
							
							
						}
						
						
					}
					
					// User INFO  UPLOAD FUNCTIONALITY
					
					
					
				 if(sevensheet.getSheetName().equalsIgnoreCase(USER))
					{
					System.out.println("SEVEN sheet"+ sevensheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						UserInfoObj obj = new UserInfoObj();
						for(int i=0;i<=sevensheet.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = sevensheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmployeeId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setUserName(formatter.formatCellValue(cell2));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setCapcoId(formatter.formatCellValue(cell3));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setFisId(formatter.formatCellValue(cell4));
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setAdminRights(Boolean.valueOf(formatter.formatCellValue(cell5)));
								
								Cell cell6 =row.getCell(5);
								System.out.println(cell6);
								obj.setHcmRights(Boolean.valueOf(formatter.formatCellValue(cell6)));
								
							
								Cell cell7 =row.getCell(6);
								System.out.println(cell7);
								obj.setUserDeleted(Boolean.valueOf(formatter.formatCellValue(cell7)));
								
								
								Cell cell8 =row.getCell(8);
								System.out.println(cell8);
								obj.setInsertedBy(formatter.formatCellValue(cell8));
								
								userList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+userList);
							  
							    if(obj.getEmployeeId() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	userInfoDao.updateEmpUserdetails(userList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	userInfoDao.updateEmpUserdetails(userList);
							    }	
								
								
							}
							
						
							
						}
						
						
					}
					
					
					// PROMOITON  UPLOAD FUNCTIONALITY
					
					
					
				 if(eightsheet.getSheetName().equalsIgnoreCase(PROMOTION))
					{
					System.out.println("eightsheet sheet"+ eightsheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						PromotionTrackerObj obj = new PromotionTrackerObj();
						for(int i=0;i<=eightsheet.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = eightsheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmployeeId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setHiredAtBand(formatter.formatCellValue(cell2));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setManagerCit(formatter.formatCellValue(cell3));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setPerfMngrPsOrCoach(formatter.formatCellValue(cell4));
								
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setTeamLeadPs(formatter.formatCellValue(cell5));
								
							
								Cell cell6 =row.getCell(6);
								System.out.println(cell6);
								obj.setInsertedBy(formatter.formatCellValue(cell6));
								
								
								promoList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+promoList);
							  
							    if(obj.getEmployeeId() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	promotionTrkrDao.updateEmpPromotiondetails(promoList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	promotionTrkrDao.updateEmpPromotiondetails(promoList);
							    }
								
							}
							
							
							
						}
						
						
					}
					
					
					
					
				//Review History 
			
				/* if(ninethsheet.getSheetName().equalsIgnoreCase(REVIEW))
					{
					System.out.println("ninethsheet sheet"+ ninethsheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						
						ReviewCycleTracker obj = new ReviewCycleTracker();
						for(int i=0;i<=ninethsheet.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = ninethsheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmpId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setReviewDate(formatter.formatCellValue(cell2));
								
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setReviewBand(formatter.formatCellValue(cell3));
								
								
								Cell cell4 =row.getCell(4);
								System.out.println(cell4);
								obj.setInsertedBy(formatter.formatCellValue(cell4));
								
								reviewList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+reviewList);
							  
							    if(obj.getEmpId() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	promotionTrkrDao.updateEmployeeReviewCycleHistory(obj.getReviewBand(), obj.getReviewDate(), obj.getEmpId(), obj.getInsertedBy());
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	System.out.println("obj ke band main kya hain " + obj.getReviewBand());
							    	System.out.println("obj ke band main kya hain " + obj.getReviewDate());
							    	System.out.println("obj ke band main kya hain " + obj.getEmpId());
							    	System.out.println("obj ke band main kya hain " + obj.getInsertedBy());
							    	
							    	
							    	promotionTrkrDao.updateEmployeeReviewCycleHistory(obj.getReviewBand(), obj.getReviewDate(), obj.getEmpId(), obj.getInsertedBy());
									
							    }
								
									
							}
							
							
							
						}
						
						
					}*/
					
					
					// ENDORSEMENT  UPLOAD FUNCTIONALITY
					
					
					
				 if(tenthsheet.getSheetName().equalsIgnoreCase(ENDORSEMENT))
					{
					System.out.println("tenthsheet sheet"+ tenthsheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						EndorsementTrackerObj obj = new EndorsementTrackerObj();
						for(int i=0;i<=tenthsheet.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = tenthsheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmpid(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setAnnualCtc(formatter.formatCellValue(cell2));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setGpaSumAssured(formatter.formatCellValue(cell3));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setGtlSumInsured(formatter.formatCellValue(cell2));
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setGmcSumInsured(formatter.formatCellValue(cell5));
								
								Cell cell6 =row.getCell(5);
								System.out.println(cell6);
								obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell6)));
								
								
								Cell cell7 =row.getCell(7);
								System.out.println(cell7);
								obj.setInsertedBy(formatter.formatCellValue(cell7));
								
								
								endoList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+endoList);
							  
							    if(obj.getEmpid() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	endorseTrkrDao.updateEmpEndorsementdetails(endoList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	endorseTrkrDao.updateEmpEndorsementdetails(endoList);
							    }
								
							}
							
							
							
						}
						
						
					}
					
					
					
					
					// NOMINATION  UPLOAD FUNCTIONALITY
					
					
					
				 if(elevensheet.getSheetName().equalsIgnoreCase(NOMINATION))
					{
					System.out.println("elevensheet sheet"+ elevensheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						NomineeTrackerObj obj = new NomineeTrackerObj();
						for(int i=0;i<=elevensheet.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = elevensheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmpid(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell2)));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setDependentsName(formatter.formatCellValue(cell3));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setRelation(formatter.formatCellValue(cell4));
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setGender(Boolean.valueOf(formatter.formatCellValue(cell5)));
								
								Cell cell6 =row.getCell(5);
								System.out.println(cell6);
								obj.setDateOfBirthOfNominee(SDF.parse(formatter.formatCellValue(cell6)));
								
								Cell cell7 =row.getCell(6);
								System.out.println(cell7);
								obj.setAgeOfNominee(formatter.formatCellValue(cell7));
								
								Cell cell8 =row.getCell(7);
								System.out.println(cell8);
								obj.setEmailId(formatter.formatCellValue(cell8));
								
								
								Cell cell9 =row.getCell(9);
								System.out.println(cell9);
								obj.setInsertedBy(formatter.formatCellValue(cell9));
								
								nomineeList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+nomineeList);
							  
							    if(obj.getEmpid() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	endorseTrkrDao.updateEmpNomineedetails(nomineeList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	endorseTrkrDao.updateEmpNomineedetails(nomineeList);
							    }	
								
								
							}
							
							
							
						}
						
						
					}
					
					
					
					
					// PROJECT HISTORY  UPLOAD FUNCTIONALITY
					
					
					
				 if(twelvesheet.getSheetName().equalsIgnoreCase(PROJECT))
					{
					System.out.println("twelvesheet sheet"+ twelvesheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						ProjectHistoryObj obj = new ProjectHistoryObj();
						for(int i=0;i<=twelvesheet.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = twelvesheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmployeeId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setFromDate(SDF.parse(formatter.formatCellValue(cell2)));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setToDate(SDF.parse(formatter.formatCellValue(cell3)));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setProjectList(formatter.formatCellValue(cell4));
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setReportingTo(formatter.formatCellValue(cell5));
								
								Cell cell6 =row.getCell(5);
								System.out.println(cell6);
								obj.setManagedBy(formatter.formatCellValue(cell6));
								
							
								Cell cell7 =row.getCell(6);
								System.out.println(cell7);
								obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell7)));
								
								
								Cell cell8 =row.getCell(8);
								System.out.println(cell8);
								obj.setInsertedBy(formatter.formatCellValue(cell8));
								
								projectList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+projectList);
							  
							    if(obj.getEmployeeId() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	projecthistoryDao.updateEmpProjectHistorydetails(projectList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	projecthistoryDao.updateEmpProjectHistorydetails(projectList);
							    }
								
									
							}
							

							
							
						}
						
					}
					
					
						
					
						// STATIC UPLOAD FUNCTIONALITY
					
					
					
				 if(thirteensheet.getSheetName().equalsIgnoreCase(STATIC))
					{
					System.out.println("thirteensheet sheet"+ thirteensheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						StaticTableObject obj = new StaticTableObject();
						for(int i=0;i<=thirteensheet.getLastRowNum();i++)
						{
							
							if(i!=0)
							{
								Row row = thirteensheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(cell1);
								obj.setFieldName(formatter.formatCellValue(cell1));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setFieldKey(formatter.formatCellValue(cell2));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setFieldCategory(formatter.formatCellValue(cell3));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell4)));
								
								staticList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+staticList);
							  
							    if(obj.getFieldCategory() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	staticDao.updateEmpStaticdetails(staticList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	staticDao.updateEmpStaticdetails(staticList);
							    }
								
								
									
							}
							
							
							
						}
					
					}
					
					
						// TRAVEL   UPLOAD FUNCTIONALITY
					
					
					
				 if(fifteensheet.getSheetName().equalsIgnoreCase(TRAVEL))
					{
					System.out.println("fifteensheet sheet"+ fifteensheet.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						TravelBondInfoObj obj = new TravelBondInfoObj();
						for(int i=0;i<=fifteensheet.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = fifteensheet.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmpId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setTypeOfTravel(formatter.formatCellValue(cell2));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setTravelCountry(formatter.formatCellValue(cell3));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setClawBackMonth(formatter.formatCellValue(cell4));
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setNumberOfMonth(formatter.formatCellValue(cell5));
								
								Cell cell6 =row.getCell(5);
								System.out.println(cell6);
								obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell6)));
								
								
								Cell cell7 =row.getCell(7);
								System.out.println(cell7);
								obj.setInsertedBy(formatter.formatCellValue(cell7));
								
								travelList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+travelList);
							  
							    if(obj.getEmpId() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	beneTrvlTrainDao.updateEmpTraveldetails(travelList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	beneTrvlTrainDao.updateEmpTraveldetails(travelList);
							    }	
								
								
							}
						

						
						}
						
					}
					
					// Skills   UPLOAD FUNCTIONALITY
					
					
					
				 if(sixteenth.getSheetName().equalsIgnoreCase(SKILL))
					{
					System.out.println("sixteenth sheet"+ sixteenth.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						SkillSetTrainingInfoObj obj = new SkillSetTrainingInfoObj();
						for(int i=0;i<=sixteenth.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = sixteenth.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmpId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setPrimarySkills(formatter.formatCellValue(cell2));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setCurrentSkill(formatter.formatCellValue(cell3));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setCapcoCertification(formatter.formatCellValue(cell4));
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setOtherTrainings(formatter.formatCellValue(cell5));
								
								Cell cell6 =row.getCell(5);
								System.out.println(cell6);
								obj.setPriorExp(formatter.formatCellValue(cell6));
								
								Cell cell7 =row.getCell(6);
								System.out.println(cell7);
								obj.setTotalExp(formatter.formatCellValue(cell7));
								
								Cell cell8 =row.getCell(7);
								System.out.println(cell8);
								obj.setCapcoExp(formatter.formatCellValue(cell8));
								
								Cell cell9 =row.getCell(8);
								System.out.println(cell9);
								obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell9)));
								
								
								Cell cell10 =row.getCell(10);
								System.out.println(cell10);
								obj.setInsertedBy(formatter.formatCellValue(cell10));
								
								skillList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+skillList);
							  
							    if(obj.getEmpId() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	beneTrvlTrainDao.updateEmpSkilldetails(skillList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	beneTrvlTrainDao.updateEmpSkilldetails(skillList);
							    }
								
									
							}
							
							
							
						}
						
						
					}
					
					// JOINING BENEFITS   UPLOAD FUNCTIONALITY
					
					
					
				 if(seventeenth.getSheetName().equalsIgnoreCase(BENEFIT))
					{
					System.out.println("seventeenth sheet"+ seventeenth.getLastRowNum());
						DataFormatter formatter = new DataFormatter();
						JoiningBenefitsInfoObj obj = new JoiningBenefitsInfoObj();
						for(int i=0;i<=seventeenth.getLastRowNum();i++)
						{
							
							if(i!=0 && i!=1)
							{
								Row row = seventeenth.getRow(i);
								
								Cell cell1 =row.getCell(0);
								System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								obj.setEmpId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
								
								Cell cell2 =row.getCell(1);
								System.out.println(cell2);
								obj.setRelocAssistance(Boolean.valueOf(formatter.formatCellValue(cell2)));
								
								Cell cell3 =row.getCell(2);
								System.out.println(cell3);
								obj.setRelocAssistAmt(formatter.formatCellValue(cell3));
								
								Cell cell4 =row.getCell(3);
								System.out.println(cell4);
								obj.setJoiningBonus(Boolean.valueOf(formatter.formatCellValue(cell2)));
								
								Cell cell5 =row.getCell(4);
								System.out.println(cell5);
								obj.setJoiningBonusAmt(formatter.formatCellValue(cell5));
								
								Cell cell6 =row.getCell(5);
								System.out.println(cell6);
								obj.setReferralBonus(Boolean.valueOf(formatter.formatCellValue(cell2)));
								
								Cell cell7 =row.getCell(6);
								System.out.println(cell7);
								obj.setReferralBonusAmt(formatter.formatCellValue(cell7));
								
								Cell cell8 =row.getCell(7);
								System.out.println(cell8);
								obj.setReferrealCandidateName(formatter.formatCellValue(cell8));
								
								Cell cell9 =row.getCell(8);
								System.out.println(cell9);
								obj.setDeletedB(Boolean.valueOf(formatter.formatCellValue(cell9)));
								
								
								Cell cell10 =row.getCell(10);
								System.out.println(cell10);
								obj.setInsertedBy(formatter.formatCellValue(cell10));
								

								benefitList.add(obj);
							    System.out.println("LIST OF EMPLOYEE"+benefitList);
							  
							    if(obj.getEmpId() == null)
							    {
							    	System.out.println("MOHTI GANGIL INSER");
								    
							    	beneTrvlTrainDao.updateEmpJoningdetails(benefitList);
							    }
							    else
							    {
							    	System.out.println("MOHTI GANGIL UPDATE");
							    	beneTrvlTrainDao.updateEmpJoningdetails(benefitList);
							    }
								
								
							}
							
							
							
						}
						
						
					}
					

					// HCM LETTER UPLOAD FUNCTIONALITY
					
				/*	
				else if(fourtteensheet.getSheetName().equalsIgnoreCase(HCM))
					{
					System.out.println("fourtteensheet sheet"+ fourtteensheet.getLastRowNum());
					DataFormatter formatter = new DataFormatter();
					HcmLetterReqObj obj1 = new HcmLetterReqObj();
					for(int i=0;i<=fourtteensheet.getLastRowNum();i++)
					{
						
						if(i!=0 && i!=1)
						{
							Row row = fourtteensheet.getRow(i);
							
							Cell cell1 =row.getCell(0);
							System.out.println(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							obj1.setEmpId(new java.text.DecimalFormat("0").format(cell1.getNumericCellValue()));
							
							Cell cell2 =row.getCell(1);
							System.out.println(cell2);
							obj1.setEmpIdCapco(formatter.formatCellValue(cell2));
							
							Cell cell3 =row.getCell(2);
							System.out.println(cell3);
							obj1.setTypeOfLetter(formatter.formatCellValue(cell3));
							
							Cell cell4 =row.getCell(3);
							System.out.println(cell4);
							obj1.setLetterPurpose(formatter.formatCellValue(cell4));
							
							Cell cell5 =row.getCell(4);
							System.out.println(cell5);
							obj1.setPurposeOtherReason(formatter.formatCellValue(cell5));
							
							Cell cell6 =row.getCell(5);
							System.out.println(cell6);
							obj1.setOtherVisaLetter(formatter.formatCellValue(cell6));
							
							Cell cell7 =row.getCell(6);
							System.out.println(cell7);
							obj1.setTravelCountry(formatter.formatCellValue(cell7));
						
							Cell cell8 =row.getCell(7);
							System.out.println(cell8);
							obj1.setPassportFromDate(SDF.parse(formatter.formatCellValue(cell8)));
							
							Cell cell9 =row.getCell(8);
							System.out.println(cell9);
							obj1.setPassportEndDate(SDF.parse(formatter.formatCellValue(cell9)));
							
							Cell cell10 =row.getCell(9);
							System.out.println(cell10);
							obj1.setStayFrom(SDF.parse(formatter.formatCellValue(cell10)));
							
							Cell cell11 =row.getCell(10);
							System.out.println(cell11);
							obj1.setStayTo(SDF.parse(formatter.formatCellValue(cell11)));
							
							Cell cell12 =row.getCell(11);
							System.out.println(cell12);
							obj1.setDesignation(formatter.formatCellValue(cell12));
							
							Cell cell13 =row.getCell(12);
							System.out.println(cell13);
							obj1.setPassportName(formatter.formatCellValue(cell13));
							
							Cell cell14 =row.getCell(13);
							System.out.println(cell14);
							obj1.setGender(Boolean.valueOf(formatter.formatCellValue(cell14)));
							
							Cell cell15 =row.getCell(14);
							System.out.println(cell15);
							obj1.setCoverLetterFrom(SDF.parse(formatter.formatCellValue(cell15)));
							
							
							Cell cell16 =row.getCell(15);
							System.out.println(cell16);
							obj1.setCoverLetterTo(SDF.parse(formatter.formatCellValue(cell16)));
							
							
							Cell cell17 =row.getCell(16);
							System.out.println(cell17);
							obj1.setEmpName(formatter.formatCellValue(cell17));
							
							
							Cell cell18 =row.getCell(17);
							System.out.println(cell18);
							obj1.setFisEid(formatter.formatCellValue(cell18));
							
							Cell cell19 =row.getCell(18);
							System.out.println(cell19);
							obj1.setPassportNumber(formatter.formatCellValue(cell19));
							
							Cell cell20 =row.getCell(19);
							System.out.println(cell20);
							obj1.setEmpAddress(formatter.formatCellValue(cell20));
							
							Cell cell21 =row.getCell(20);
							System.out.println(cell21);
							obj1.setAddressedTo(formatter.formatCellValue(cell21));
						
							Cell cell22 =row.getCell(22);
							System.out.println(cell22);
							obj1.setInsertedBy(formatter.formatCellValue(cell22));
				
							
							hcmList.add(obj1);
						    System.out.println("LIST OF EMPLOYEE"+hcmList);
						  
						    if(obj1.getEmpId() == null)
						    {
						    	System.out.println("MOHTI GANGIL INSER");
							    
						    	letterReqSqlDao.updateEmpHCMdetails(hcmList);
						    }
						    else
						    {
						    	System.out.println("MOHTI GANGIL UPDATE");
						    	letterReqSqlDao.updateEmpHCMdetails(hcmList);
						    }
							
							}
							
							
							
						
							
						}
						
					}*/

				
					
					
			        workbook.close();
			       
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			finally{
				if(inputData !=null){
					try {
						inputData.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
			
			
		}