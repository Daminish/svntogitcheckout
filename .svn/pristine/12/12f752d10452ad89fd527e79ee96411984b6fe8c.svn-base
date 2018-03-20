use capco_hcm;
CREATE TABLE INDUCTION_TRACKER (
	Emp_Id INT,
	First_Email_Date DATE,
	Second_Email_Date DATE,
	Third_Email_Date DATE,
	Fourth_Email_Date DATE,
	Fifth_Email_Date DATE,
	Attended_On DATE,
	Deleted BOOLEAN,
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);