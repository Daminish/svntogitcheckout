package com.capco.hcm.utils;

import java.io.IOException;

/**
 * @author Mohit Gangil
 *
 */
public enum TemplateFieldsEnum {

	Basic_Info("Basic Info"), 
	BGV("BGV"), 
	Induction("Induction"), 
	Exit("Exit"), 
	Bank("Bank"), 
	Probation("Probation"), 
	Review_History("Review History"), 
	Endorsement("Endorsement"),
	Project_History("Project History"),
	User_Info ("User Info"),
	Static ("Static"),
	Hcm_Letter ("Hcm Letter");
	
	
	private final String sheetName;

	private TemplateFieldsEnum(final String sheetName) {
		this.sheetName = sheetName;
	}
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateBasicInfoEnum {
		Emp_Name ("Emp Name"),
		Capco_Emp_Id ("Capco Emp Id"),
		Fis_Emp_Id ("Fis Emp Id"),
		File_No ("File No"),
		DOB ("Date of Birth"),
		DOJ ("Date of Joining"),
		Start_Date ("Start Date"),
		Emp_Status ("Employment Status"),
		Gender ("Gender"),
		HCBP_Id ("HCBP Id"),
		Capco_Login_Id ("Capco Login Id"),
		Designation ("Designation"),
		ATP ("ATP"),
		Service ("Service"),
		Project ("Assigned Project"),
		Billing_Status ("Billing Status"),
		FTE_Contractor ("FTE Contractor"),
		Type_Of_Employment ("Type Of Employment"),
		Official_Email_Id ("Official Email Id"),
		Personal_Email_Id ("Personal Email Id"),
		Contact_No ("Primary Contact No"),
		House ("Assigned House"),
		//Performance info
		Hired_At_Band ("Hired At Band"),
		Manager_CIT ("Manager CIT"),
		Performance_Manager ("Performance  Manager"),
		TL_in_PeopleSoft ("TL in PeopleSoft"),
		//Insurance
		GPA_Sum_Assured ("GPA Sum Assured"),
		GTL_Sum_Assured ("GTL Sum Assured"),
		GMC_Sum_Insured ("GMC Sum Insured"),
		Annual_CTC ("Annual CTC"),
		//Probation
		Probation_Status("Probation Status"),
		Probation_Confirmed_Date("Probation Confirmed Date"),
		Confirmation_Letter_Date("Confirmation Letter Date"),
		//Audit
		Deleted("Deleted"),
		Insertion_Date ("Insertion Date"),
		Inserted_By ("Inserted By");

		private  String colName;

		private TemplateBasicInfoEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}
	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateBgvEnum {

		Capco_Emp_Id ("Capco Emp Id"),
		Pre_Onboarding_Initiated ("Pre Onboarding Initiated"),
		Owner ("Owner"),
		Nhp_Creation_Date ("NHP Creation Date"),
		Welcome_Email_Date ("Welcome Email Date"),
		First_Chaser ("First Chaser Date"),
		Second_Chaser ("Second Chaser Date"),
		Third_Chaser ("Third Chaser Date"),
		Escalation_Date ("Escalation Date"),
		Document_Received_Date ("Document Received Date"),
		//BGV
		Bgv_Initiated_Date ("Bgv Initiated Date"),
		Bgv_Ref_Number ("Bgv Ref Number"),
		Bgv_Interim_Report ("Bgv Interim Report"),
		Bgv_Final_Report ("Bgv Final Report"),
		Bgv_Color_Code ("Bgv Color Code"),
		Bgv_Sign_Off_By ("Bgv Sign Off By"),
		Bgv_Sign_Off_Date ("Bgv Sign Off Date"),
		Bgv_Closure_Date ("Bgv Closure Date"),
		Package_Initiated ("Package Initiated"),
		Education ("Education"),
		Employment ("Employment"),
		Court_Check ("Court Check"),
		Identity_Check ("Identity Check"),
		Country_Check ("Country Check"),
		Reference_Check ("Reference Check"),
		Cv_Check ("Cv Check"),
		//CHECKLIST
		Signed_Offer_Letter ("Signed Offer Letter Received"),
		Cv ("CV Received"),
		Personal_Details_Form ("Personal Details Form Received"),
		Mediclaim_Nomination_Form ("Mediclaim Nomination Form Received"),
		PF_Nomination_Form ("PF Nomination Form Received"),
		Passport_Size_Photo ("Passport Size Photo Received"),
		Nda ("Signed NDA Received"),
		Code_Of_Conduct ("Code Of Conduct Received"),
		Releiving_Letter ("Releiving Letter Received"),
		Experience_Letter ("Experience Letter Received"),
		Last_Payslips ("Last Payslips Received"),
		Marksheets ("Marksheets Received"),
		//MISC
		Pan_Card ("Pan Card"),
		Passport_Number ("Passport Number"),
		Passport_Expire ("Passport Expire"),
		Types_Of_Visa ("Types Of Visa"),
		Visa_Country ("Visa Country"),
		Deleted("Deleted"),
		Insertion_Date ("Insertion Date"),
		Inserted_By ("Inserted By");

		private final String colName;

		private TemplateBgvEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}

	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateInductionEnum {
		
		Capco_Emp_Id ("Capco Emp Id"),
		First_Email_Date ("First Email Date"),
		Second_Email_Date ("Second Email Date"),
		Third_Email_Date ("Third Email Date"),
		Fourth_Email_Date ("Fourth Email Date"),
		Fifth_Email_Date ("Fifth Email Date"),
		Attended_On ("Attended On"),
		Deleted("Deleted"),
		Insertion_Date ("Insertion Date"),
		Inserted_By ("Inserted By");

		
		private final String colName;
		
		private TemplateInductionEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}
	
	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateBankEnum {
		
		Capco_Emp_Id ("Capco Emp Id"),
		PF_Doc_Received_Date ("PF Doc Received Date"),
		PF_Doc_Sent_Date ("PF Doc Sent Date"),
		UAN_Number ("UAN Number"),
		Bank_Name ("Bank Name"),
		Account_Holder_Name ("Account Holder Name"),
		Account_Number ("Account Number"),
		IIFSC_Code ("IIFSC Code"),
		Bank_Branch_Name ("Bank Branch Name"),
		Deleted("Deleted"),
		Insertion_Date ("Insertion Date"),
		Inserted_By ("Inserted By");

		
		private final String colName;
		
		private TemplateBankEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}

	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateHcmLetterEnum {
		
		Capco_Emp_Id ("Capco Emp Id"),
		Emp_Id_Capco ("Emp Id Capco"),
		Type_Of_Letter ("Type Of Letter"),
		letter_Purpose ("letter Purpose"),
		Invite_Purpose ("Invite Purpose"),
		Purpose_Other_Reason ("Purpose Other Reason"),
		Other_Visa_Letter ("Other Visa Letter"),
		Travel_Country ("Travel Country"),
		Passport_From_Date ("Passport From Date"),
		Passport_End_Date ("Passport End Date"),
		Stay_From ("Stay From"),
		Stay_To ("Stay To"),
		Designation ("Designation"),
		Passport_Name ("Passport Name"),
		Cover_Letter_From ("Cover Letter From"),
		Cover_Letter_To ("Cover Letter To"),
		Emp_Name ("Emp Name"),
		Fis_Eid ("Fis_Eid"),
		Passport_Number ("Passport Number"),
		Emp_Address ("Emp Address"),
		Addressed_To ("Addressed To"),
		Insertion_Date ("Insertion Date"),
		Inserted_By ("Inserted By");

		
		private final String colName;
		
		private TemplateHcmLetterEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}
	
	
	
	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateUserInfoEnum {
		
		Capco_Emp_Id ("Capco Emp Id"),
		Username ("Username"),
		Capco_Id ("Capco Id"),
		Fis_Id ("Fis Id"),
		Admin_Rights ("Admin Rights"),
		Hcm_Rights ("Hcm Rights"),
		Normal_User ("Normal User"),
		User_Deleted ("User Deleted"),
		Insertion_Date ("Insertion Date"),
		Inserted_By ("Inserted By");

		
		private final String colName;
		
		private TemplateUserInfoEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}
	

	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateStaticEnum {
		
		Field_Name ("Field Name"),
		Field_Key ("Field Key"),
		Field_Category ("Field Category"),
		DeletedB ("DeletedB");
				
		private final String colName;
		
		private TemplateStaticEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}
	
	
	
	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateExitEnum {
		Capco_Emp_Id ("Capco Emp Id"),
		//Summary
		Resignation_Date ("Resignation Date"),
		Last_Working_Date ("Last Working Date"),
		Notice_Period ("Notice Period"),
		Resignation_Reason ("Resignation Reason"),
		Comments ("Comments"),
		//Checklist
		Medical_Insurance_Informed ("Medical Insurance Informed"),
		Ts_Submitted ("Timesheet Submitted"),
		Exit_Interview_Completed ("Exit Interview Completed"),
		Birthday_App_Deletion ("Birthday App Deletion"),
		Salary_Hold ("Salary Hold"),
		//Details
		Library_Book_Collected ("Library Book Collected"),
		Fnf_Completed ("Fnf Completed"),
		Login_Id_Terminated ("Login Id Terminated"),
		Login_Id_Term_Date ("Login Id Term Date"),
		Releiving_Letter_Issued ("Releiving Letter Issued"),
		Releiving_Letter_Issue_Date ("Releiving Letter Issue Date"),
		Service_Letter_Issued ("Service Letter Issued"),
		Service_Letter_Issue_Date ("Service Letter Issue Date"),
		Buyout_Letter_Issued ("Buyout Letter Issued"),
		Buyout_Letter_Issue_Date ("Buyout Letter Issue Date"),
		Buyout_Amount_Paid("Buyout Amount Paid"),
		Buyout_Amount("Buyout Amount"),
		Buyout_Number_of_Days("Buyout Number of Days "),
		Buyout_Waived("Buyout Waived"),
		Buyout_Waived_Days("Buyout Waived Days"),
		Deleted("Deleted"),
		Inserted_By ("Inserted By"),
		Insertion_Date ("Insertion Date");

		private final String colName;

		private TemplateExitEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}
	
	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateProbationEnum {
		Capco_Emp_Id ("Capco Emp Id"),
		//Probation
		Probation_Confirm_Date("Probation Confirm Date"),
		First_Chaser_Date ("First Chaser Date"),
		Second_Chaser_Date ("Second Chaser Date"),
		Third_Chaser_Date ("Third Chaser Date"),
		Escalated_HCBP_Date ("Escalated to HCBP Date"),
		Confirmed_Date("Confirmed Date"),
		Feedback_Form_Recieved ("Feedback Form Recieved"),
		Letter_Issue_Date("Letter Issue Date"),
		Status("Status"),
		Probation_Extended_Date ("Probation Extended To Date"),
		Extension_Reason("Extension Reason"),
		Deleted("Deleted"),
		Inserted_By ("Inserted By"),
		Insertion_Date ("Insertion Date");


		
		
		private final String colName;

		private TemplateProbationEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}
	
	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateEndorsementEnum {
		Capco_Emp_Id ("Capco Emp Id"),
		Serial_No ("Serial No"),
		Deleted ("Deleted"),
		Dependent_Name ("Dependent Name"),
		Relation ("Relation"),
		Gender ("Gender"),
		Nominee_Date_Of_Birth ("Nominee Date Of Birth"),
		Nominee_Age ("Nominee Age"),
		EmailId ("EmailId"),
		Inserted_By ("Inserted By"),
		Insertion_Date ("Insertion Date");

		private final String colName;
		
		private TemplateEndorsementEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}

	}
	
	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	public enum TemplateReviewEnum {
		
		Capco_Emp_Id ("Capco Emp Id"),
		Review_Cycle ("Review Cycle"),
		Review_Bnd ("Review Band"),
		Inserted_By ("Inserted By"),
		Insertion_Date ("Insertion Date");

		private final String colName;
		
		private TemplateReviewEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}

	/**
	 * 
	 * @author Gaurav Sharma
	 *
	 */
	
	public enum TemplateProjectHistoryEnum {
		
		Capco_Emp_Id ("Capco Emp Id"),
		From_Date ("From Date"),
		To_Date ("To Date"),
		Project_List ("Project List"),
		Role_Assigned ("Role Assigned"),
		Reporting_To ("Reporting To"),
		Managed_By ("Managed By"),
		Deleted("Deleted"),
		Insertion_Date ("Insertion Date"),
		Inserted_By ("Inserted By");

		private final String colName;

		private TemplateProjectHistoryEnum(final String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}
	}
	
	public static void main(String[] args) throws IOException {
		for(TemplateFieldsEnum s : TemplateFieldsEnum.values())
			System.out.print(s.getSheetName() + ", ");
		
		System.out.println();
		System.out.println(TemplateBasicInfoEnum.class.getSimpleName() + ": " );
		for(TemplateBasicInfoEnum tbi : TemplateBasicInfoEnum.values())
			System.out.print(tbi.getColName() + ", ");
		
		
		System.out.println();
		System.out.println(TemplateBgvEnum.class.getSimpleName() + ": " );
		for(TemplateBgvEnum tbe : TemplateBgvEnum.values())
			System.out.print(tbe.getColName() + ", ");
		
		System.out.println();
		System.out.println(TemplateExitEnum.class.getSimpleName() + ": " );
		for(TemplateExitEnum tee : TemplateExitEnum.values())
			System.out.print(tee.getColName() + ", ");
		
		System.out.println();
		System.out.println(TemplateEndorsementEnum.class.getSimpleName() + ": " );
		for(TemplateEndorsementEnum tene : TemplateEndorsementEnum.values())
			System.out.print(tene.getColName() + ", ");
		
		System.out.println();
		System.out.println(TemplateInductionEnum.class.getSimpleName() + ": " );
		for(TemplateInductionEnum tin : TemplateInductionEnum.values())
			System.out.print(tin.getColName() + ", ");
		
		System.out.println();
		System.out.println(TemplateBankEnum.class.getSimpleName() + ": " );
		for(TemplateBankEnum tin : TemplateBankEnum.values())
			System.out.print(tin.getColName() + ", ");
		
		System.out.println();
		System.out.println(TemplateReviewEnum.class.getSimpleName() + ": " );
		for(TemplateReviewEnum tin : TemplateReviewEnum.values())
			System.out.print(tin.getColName() + ", ");
		
		System.out.println();
		System.out.println(TemplateProjectHistoryEnum.class.getSimpleName() + ": " );
		for(TemplateProjectHistoryEnum tin : TemplateProjectHistoryEnum.values())
			System.out.print(tin.getColName() + ", ");
		
		System.out.println();
		System.out.println(TemplateUserInfoEnum.class.getSimpleName() + ": " );
		for(TemplateUserInfoEnum tin : TemplateUserInfoEnum.values())
			System.out.print(tin.getColName() + ", ");
		
		System.out.println();
		System.out.println(TemplateStaticEnum.class.getSimpleName() + ": " );
		for(TemplateStaticEnum tin : TemplateStaticEnum.values())
			System.out.print(tin.getColName() + ", ");
		
		System.out.println();
		System.out.println(TemplateHcmLetterEnum.class.getSimpleName() + ": " );
		for(TemplateHcmLetterEnum tin : TemplateHcmLetterEnum.values())
			System.out.print(tin.getColName() + ", ");
		
		
	}
}