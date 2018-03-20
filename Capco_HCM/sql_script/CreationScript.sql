CREATE DATABASE capco_hcm;

use capco_hcm;

CREATE TABLE EMP_INFO (
	Emp_Id INT, 
	Emp_Name VARCHAR(100), 
	Sec_Emp_Id VARCHAR(10), 
	File_No VARCHAR(20), 
	Date_Of_Birth DATE, 
	Joining_Date DATE, 
	Emp_Status CHAR(1), 
	Gender BOOLEAN, 
	HcBp VARCHAR(100), 
	Capco_Login_Id VARCHAR(5), 
	Designation CHAR(2), 
	Atp BOOLEAN, 
	Service CHAR(1), 
	Project VARCHAR(10), 
	Type_Of_Employment VARCHAR(15),
	Billing_Status BOOLEAN, 
	Fte_Contractor BOOLEAN, 
	Start_Date DATE, 
	Official_Email_Id VARCHAR(100),
	Personal_Email_Id VARCHAR(100), 
	Contact_Number VARCHAR(15), 
	House CHAR(1), 
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	PRIMARY KEY(Emp_Id, Insertion_Date)
);

CREATE TABLE DOC_BGV_TRACKER (
	Emp_Id INT, 
	Pre_Onboarding_Initiated BOOLEAN,
	Owner VARCHAR(100),
	Welcome_Email_Date DATE,
	Document_Received_Date DATE,
	First_Chaser DATE,
	Second_Chaser DATE,
	Third_Chaser DATE,
	Escalation_Date DATE,
	Nhp_Creation_Date DATE,
	New_Hire_Provision VARCHAR(256),
	Signed_Offer_Letter BOOLEAN,
	Cv BOOLEAN,
	Personal_Details_Form BOOLEAN,
	Mediclaim_Nomination_Form BOOLEAN,
	PF_Nomination_Form BOOLEAN,
	Passport_Size_Photo BOOLEAN,
	Nda BOOLEAN,
	Code_Of_Conduct BOOLEAN,
	Releiving_Letter BOOLEAN,
	Experience_Letter BOOLEAN,
	Last_Payslips BOOLEAN,
	Marksheets BOOLEAN,
	Pan_Card VARCHAR(10),
	Passport_Number VARCHAR(10),
	Passport_Expire DATE,
	Types_Of_Visa VARCHAR(256),
	Visa_Country VARCHAR(256),
	Bgv_Initiated_Date DATE,
	Bgv_Ref_Number VARCHAR(20),
	Bgv_Interim_Report DATE,
	Bgv_Final_Report DATE,
	Bgv_Color_Code CHAR(1),
	Bgv_Sign_Off_By VARCHAR(100),
	Bgv_Sign_Off_Date DATE,
	Bgv_Closure_Date DATE,
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT	
);

CREATE TABLE EMPLOYEE_BAND_TRACKER (
	Emp_Id INT, 
	Hired_Band CHAR(2), 
	Manager_Cit VARCHAR(100), 
	Manager_Ps VARCHAR(100),
	Team_Lead_Ps VARCHAR(100),
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE REVIEW_CYCLE_TRACKER (
	Emp_Id INT,
	Review_Cycle VARCHAR(10),
	Review_Band CHAR(2),
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	FOREIGN KEY (Emp_Id)
		REFERENCES EMPLOYEE_BAND_TRACKER(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE ENDORSEMENT_TRACKER (
	Emp_Id INT, 
	GPA_Sum_Assured VARCHAR(15),
	GTL_Sum_Insured VARCHAR(15),
	GMC_Sum_Insured VARCHAR(15),
	Annual_Ctc VARCHAR(15),
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT	
);

CREATE TABLE NOMINATION_DETAILS (
	Emp_Id INT, 
	Serial_No INT,
	Deleted BOOLEAN,
	Dependent_Name VARCHAR(100),
	Relation VARCHAR(20),
 	Gender boolean,
	Nominee_Date_Of_Birth DATE,
	Nominee_Age VARCHAR(3),
	EmailId VARCHAR(100),
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id) 
		REFERENCES ENDORSEMENT_TRACKER(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE EXIT_TRACKER (
	Emp_Id INT,
	Last_Working_Date DATE,
	Resignation_Date DATE,
	Resignation_Reason VARCHAR(256),
	Notice_Period VARCHAR(15),
	Comments VARCHAR(256),
	Medical_Insurance_Informed boolean,
	Library_Book_Collected boolean,
	Exit_Interview_Completed boolean,
	Greytip_Id_Disabled boolean,
	Salary_Hold boolean,
	Fnf_Completed boolean,
	Ts_Submitted boolean,
	Login_Id_Terminated boolean,
	Login_Id_Term_Date DATE,
	Releiving_Letter_Issued boolean,
	Releiving_Letter_Issue_Date DATE,
	Service_Letter_Issued boolean,
	Service_Letter_Issue_Date DATE,
	Buyout_Letter_Issued boolean,
	Buyout_Letter_Issue_Date DATE,
	Buyout_Amount_Paid boolean,
	Buyout_Amount VARCHAR(15),
	Buyout_Number_of_Days INT,
	Buyout_Waived boolean,
	Buyout_Waived_Days INT,
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE FINANCIAL_TRACKER (
	Emp_Id INT,
	Pf_Doc_Received_Date DATE,
	Sent_to_Pf_Date DATE,
	Uan_Number VARCHAR(15),
	Bank_Name VARCHAR(30),
	Account_Holder_Name VARCHAR(100),
	Account_Number VARCHAR(20),
	Ifsc_Code VARCHAR(15),
	Bank_Branch_Name VARCHAR(100),
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE INDUCTION_TRACKER (
	Emp_Id INT,
	First_Email_Date DATE,
	Second_Email_Date DATE,
	Third_Email_Date DATE,
	Fourth_Email_Date DATE,
	Fifth_Email_Date DATE,
	Attended_On DATE,
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE PROBATION_TRACKER (
	Emp_Id INT,
	Probation_Confirm_Date DATE,
	First_Chaser_Date DATE,
	Second_Chaser_Date DATE,
	Third_Chaser_Date DATE,
	Escalated_Hcbp_Date DATE,
	Confirmed_Date DATE,
	Feedback_Form_Received CHAR(1),
	Letter_Issue_Date DATE,
	Status CHAR(1),
	Extended_Date DATE,
	Extension_Reason VARCHAR(1024),
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE HCM_STATIC_TABLE (
	Field_Name VARCHAR(100),
	Field_Key VARCHAR(100),
	Field_Category VARCHAR(100),
	Deleted BOOLEAN
);