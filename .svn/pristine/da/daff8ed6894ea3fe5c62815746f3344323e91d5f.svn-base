use capco_hcm;
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
	Deleted BOOLEAN,
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);