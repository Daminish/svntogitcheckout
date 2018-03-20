use capco_hcm;
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
	Deleted BOOLEAN,
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);