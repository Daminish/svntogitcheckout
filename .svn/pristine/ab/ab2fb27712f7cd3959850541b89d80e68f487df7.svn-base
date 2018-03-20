use capco_hcm;
CREATE TABLE JOINING_BENEFITS_TRACKER (
	Emp_Id INT, 
	Reloc_Assistance BOOLEAN,
	Reloc_Assist_Amt VARCHAR(30),
	Joining_Bonus BOOLEAN,
	Joining_Bonus_Amt VARCHAR(30),
	Referral_Bonus BOOLEAN,
	Referral_Bonus_Amt VARCHAR(30),
	Referral_Candidate_Name VARCHAR(100),
	Deleted BOOLEAN,
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT	
);