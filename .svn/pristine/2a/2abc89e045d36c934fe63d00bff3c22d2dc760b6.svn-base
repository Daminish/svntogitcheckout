use capco_hcm;
CREATE TABLE USER_INFO (
	Emp_Id INT,
	Capco_ID VARCHAR(6),
	Fis_Id VARCHAR(10),
	User_Name VARCHAR(25),
	Admin_Rights boolean,
	Hcm_Rights boolean,
	Normal_User boolean,
	User_Deleted boolean,
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);