use capco_hcm;
CREATE TABLE HCM_LETTER (
	Emp_Id INT,
	Emp_Id_Capco VARCHAR(50),
	Emp_Name VARCHAR(70),
	Passport_Number VARCHAR(70),
	Fis_Eid VARCHAR(70),
	Status BOOLEAN,
	Type_Of_Letter VARCHAR(70),
	Letter_Purpose VARCHAR(70),
	Purpose_Other_Reason VARCHAR(70),
	Other_Visa_Letter VARCHAR(70),
	Travel_Country VARCHAR(70),
	Passport_From_Date DATE,
	Passport_End_Date DATE,
	Stay_From DATE,
	Stay_To DATE,
	Passport_Name VARCHAR(30),
	Designation VARCHAR(15),
	Gender BOOLEAN,
	Cover_Letter_From DATE,
	Cover_Letter_To DATE,
	Emp_Address VARCHAR(100),
	Addressed_To VARCHAR(100), 
	Insertion_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Inserted_By VARCHAR(100),
	FOREIGN KEY (Emp_Id)
		REFERENCES EMP_INFO(Emp_Id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);