drop database Capco_Living_Portal;
create database `Capco_Living_Portal`;

use `Capco_Living_Portal`;

CREATE TABLE `Capco_Living_Portal`.`User` (
  `uid` int(11) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `user_type` varchar(50) DEFAULT NULL,
  `mobile_no` varchar(50) DEFAULT NULL,
  `home_address` varchar(255) DEFAULT NULL,
  `work_address` varchar(255) DEFAULT NULL,
  `thai_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
  );

CREATE TABLE `home` (
  `Category_id` int(11) NOT NULL AUTO_INCREMENT,
  `Category_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Category_id`)
);  

CREATE TABLE `Capco_Living_Portal`.`Country` (
    `country_id` int,
    `country_name` varchar(50) ,
                PRIMARY KEY (`country_id`)
    ); 

CREATE TABLE `Capco_Living_Portal`.`City` (
    `country_id` int,
    `city_id` int,
    `city_name` varchar(50) ,
     `northeast_lat` double DEFAULT NULL,

     `northeast_long` double DEFAULT NULL,
  
     `southwest_lat` double DEFAULT NULL,
  
     `southwest_long` double DEFAULT NULL,
     PRIMARY KEY (`city_id`),
     foreign key (`country_id`)
     references Country (`country_id`)
  ); 



CREATE TABLE `Capco_Living_Portal`.`Locality` (
                `city_id` int,
    `locality_id` int,
    `locality_name` varchar(50) ,
     `lattitude` double DEFAULT NULL,
     `longitude` double DEFAULT NULL, 
     `zip_code` int,
                PRIMARY KEY (`locality_id`),
     foreign key (`city_id`)
     references City (`city_id`)
   );  


CREATE TABLE `budget_plan` (
  
`Budget_id` int(11) NOT NULL,
  
`user_id` varchar(50) DEFAULT NULL,
  
`applicant_type` varchar(50) DEFAULT NULL,
  
`estimated_mortgage_amount` double DEFAULT NULL,
  
`mortgage_term` double DEFAULT NULL,
  PRIMARY KEY (`Budget_id`),
   
FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)

);



CREATE TABLE `total_income` (
  `Income_id` int(11) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `gross_annual_salary` int(11) NOT NULL,
  `other_yearly_income` int(11) DEFAULT NULL,
  `estimated_monthly_income` int(11) NOT NULL,
  PRIMARY KEY (`Income_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);  


CREATE TABLE `Capco_Living_Portal`.`Monthly_Expenses` (
                `user_id` varchar(50) ,
    `expense_id` int NOT NULL AUTO_INCREMENT,
                `category` varchar(50),
    `expense_type` varchar(50),
    `amount` double,
    `description` varchar(50),
                PRIMARY KEY (`expense_id`),
     foreign key (user_id)
     references User (user_id)
    ); 




CREATE TABLE `Capco_Living_Portal`.`Property` (
                `property_id` int NOT NULL,
                `locality_id` int ,
                `property_type` varchar(50),
    `property_value` double,
    `property_name` varchar(50),
    `property_bedrooms` varchar(50),
    `property_address` varchar(255),
    `property_specification` varchar(255),
                `property_description` varchar(255),
                `property_area` varchar(255),
    `property_availability_date` date,
`property_Status` tinyint(1) DEFAULT NULL,
  `is_negotiable` tinyint(1) DEFAULT NULL, 
    `lattitude` double ,
    `longitude` double ,
                PRIMARY KEY (`property_id`),
     foreign key (`locality_id`)
     references Locality (`locality_id`)
   );




CREATE TABLE `Capco_Living_Portal`.`Home_Amenities` (
                `amenity_id` int,
    `amenity_name` varchar(50),
                primary key (`amenity_id`)
     );


CREATE TABLE `Capco_Living_Portal`.`Locality_Amenities`(
                `locality_amenity_id` int,
    `locality_amenity_name` varchar(50),
    primary key(`locality_amenity_id`)
    );



CREATE TABLE `Capco_Living_Portal`.`Home_Amenities_Property`(
                `property_id` int,
    `amenity_id` int,
    `amenity_name` varchar(50)
    );




CREATE TABLE`Capco_Living_Portal`.`Locality_Amenities_Relation`(
                `locality_amenity_id` int,
    `locality_amenity_name` varchar(50),
    `locality_id` int
    );





CREATE TABLE `Capco_Living_Portal`.`School` (
                `school_id` int,
    `school_name` varchar(50),
    `school_rating` varchar(50) ,
                PRIMARY KEY (`school_id`)
    ); 




  CREATE TABLE `Capco_Living_Portal`.`Broadband` (
    `service_id` int,
    `service_provider_name` varchar(50) ,
    `service_rating` varchar(50),
                PRIMARY KEY (`service_id`)
    );


  CREATE TABLE `Capco_Living_Portal`.`School_Locality` (
                `school_id` int,
    `locality_id` int,
    `school_rating` varchar(50) ,
                foreign key (`locality_id`)
                references locality (`locality_id`)
    );



CREATE TABLE `Capco_Living_Portal`.`Broadband_Locality` (
                `service_id` int,
    `locality_id` int,
    `service_rating` varchar(50) ,
                foreign key (`locality_id`)
                references locality (`locality_id`)
    ); 




create table `capco_living_portal`.`crime`(crime_id int not null,crime_name varchar(50),crime_rating varchar(50),primary key(crime_id));


create table `capco_living_portal`.`Locality_Crime_relation`(locality_id int, crime_id int ,crime_rating varchar(50),foreign key (crime_id) references crime (crime_id));


create table `capco_living_portal`.`Locality_population_relation` (locality_id int,male_population double default null,female_population double default null,childrens_below_18 double default null,foreign key (locality_id) references locality (locality_id));





insert into `capco_living_portal`.`user`(uid,user_id,password,first_name,last_name,user_type,Mobile_no,Home_address,Work_address,Thai_id) values (1,'user1@capco.com','user','shubham','kherde','user1','8408939068','gajanan nagar,wardha','kharadi,pune','ABCD1234') ;

insert into `capco_living_portal`.`user`(uid,user_id,password,first_name,last_name,user_type,Mobile_no,Home_address,Work_address,Thai_id) values (2,'user2@capco.com','user12','rohit','jadhav','user2','8486878987','akurdi,pune','shivajinagar,pune','PQRS1234') ;





insert into `capco_living_portal`.`country`(country_id,country_name) values(1,'india'); 
 



insert into `capco_living_portal`.`city` (country_id,city_id,city_name,northeast_lat,northeast_long,southwest_lat,southwest_long)  values (1,49,'Pune',18.6346965,73.9753461,18.4136739,73.7398911);

insert into `capco_living_portal`.`city` (country_id,city_id,city_name,northeast_lat,northeast_long,southwest_lat,southwest_long)  values (1,22,'Ahmedabad',23.1378156,72.7026413,22.9028759,72.4541115);

insert into `capco_living_portal`.`city` (country_id,city_id,city_name,northeast_lat,northeast_long,southwest_lat,southwest_long)  values (1,5,'Bangalore',13.173706,77.8826809,12.7342888,77.3791981);

insert into `capco_living_portal`.`city` (country_id,city_id,city_name,northeast_lat,northeast_long,southwest_lat,southwest_long)  values (1,12,'Hyderabad',17.579448,78.6913581,17.2383708,78.2401412);

                

insert into `capco_living_portal`.`locality` (city_id,locality_id,locality_name,zip_code,lattitude,longitude) values (49,1,'kharadi',411014,18.5204,73.8567);

insert into `capco_living_portal`.`locality` (city_id,locality_id,locality_name,zip_code,lattitude,longitude) values (49,2,'Hadapsar',411014,18.5204,73.8567);

insert into `capco_living_portal`.`locality` (city_id,locality_id,locality_name,zip_code,lattitude,longitude) values (49,3,'Vimannagar',411014,18.5204,73.8567);



                
                

insert into `capco_living_portal`.`budget_plan` (Budget_id,User_id,Applicant_type,Estimated_Mortgage_Amount,Mortgage_term) values (1,'user1@capco.com','Single',800000,20);

insert into `capco_living_portal`.`budget_plan` (Budget_id,User_id,Applicant_type,Estimated_Mortgage_Amount,Mortgage_term) values (2,'user2@capco.com','Joint applicant',900000,10);
    
                
                
                
insert into `capco_living_portal`.`total_income` (Income_id,User_id,Gross_annual_salary,Other_yearly_income,Estimated_Monthly_Income) values (1,'user1@capco.com',30000,20000,3000);

insert into `capco_living_portal`.`total_income` (Income_id,User_id,Gross_annual_salary,Other_yearly_income,Estimated_Monthly_Income) values (2,'user2@capco.com',40000,60000,7000);




insert into `capco_living_portal`.`monthly_expenses` (Expense_id,User_id,Category,Expense_type,Amount,Description) values (1,'user1@capco.com','Bill payments','Loan Payments',800000,'Happy life');

insert into `capco_living_portal`.`monthly_expenses` (Expense_id,User_id,Category,Expense_type,Amount,Description) values (2,'user2@capco.com','Entertainment','Cinema',900,'on monday');




insert into `capco_living_portal`.`property` (locality_id,property_id,property_type,property_value,property_name,property_bedrooms,property_address,property_specification,property_description,property_area,property_availability_date,lattitude,longitude,property_status,is_negotiable) values (1,1,'flat',2000000,'dream park',4,'near zensar,kharadi','internet access,parking','Spacious room','3000 sqft','2018-02-01',18.5204,73.8567,1,0);                     
 
insert into `capco_living_portal`.`property` (locality_id,property_id,property_type,property_value,property_name,property_bedrooms,property_address,property_specification,property_description,property_area,property_availability_date,lattitude,longitude,property_status,is_negotiable) values (1,2,'flat',3000000,'villa',3,'near wtc,kharadi','internet access','Spacious room','4000 sqft','2018-02-28',18.5204,73.8567,1,1);  

insert into `capco_living_portal`.`property` (locality_id,property_id,property_type,property_value,property_name,property_bedrooms,property_address,property_specification,property_description,property_area,property_availability_date,lattitude,longitude,property_status,is_negotiable) values (1,3,'house',4000000,'welcome house',2,'gajanan nagar,kharadi','internet access,parking,gym','Spacious room','5000 sqft','2018-02-12',18.5204,73.8567,1,0);  

insert into `capco_living_portal`.`property` (locality_id,property_id,property_type,property_value,property_name,property_bedrooms,property_address,property_specification,property_description,property_area,property_availability_date,lattitude,longitude,property_status,is_negotiable) values (2,4,'flat',5000000,'live together',5,'society no 1 ,hadapasar','internet access,parking','Spacious room','3000 sqft','2018-02-23',18.5204,73.8567,1,0);  

insert into `capco_living_portal`.`property` (locality_id,property_id,property_type,property_value,property_name,property_bedrooms,property_address,property_specification,property_description,property_area,property_availability_date,lattitude,longitude,property_status,is_negotiable) values (2,5,'house',6000000,'dream park',4,'society no 2,hadapsar','internet access,parking,gym','Spacious room,sunset view','3500 sqft','2018-02-11',18.5204,73.8567,1,1);  

insert into `capco_living_portal`.`property` (locality_id,property_id,property_type,property_value,property_name,property_bedrooms,property_address,property_specification,property_description,property_area,property_availability_date,lattitude,longitude,property_status,is_negotiable) values (2,6,'flat',7000000,'ram nivas',3,'society no 3,hadapsar','internet access,temple','Spacious room','3900 sqft','2018-02-08',18.5204,73.8567,1,0);  

insert into `capco_living_portal`.`property` (locality_id,property_id,property_type,property_value,property_name,property_bedrooms,property_address,property_specification,property_description,property_area,property_availability_date,lattitude,longitude,property_status,is_negotiable) values (3,7,'flat',2000000,'av park',5,'near petrol pump,viman nagar','wifi access,parking','Spacious room','2000 sqft','2017-02-01',18.5204,73.8567,1,1);  

insert into `capco_living_portal`.`property` (locality_id,property_id,property_type,property_value,property_name,property_bedrooms,property_address,property_specification,property_description,property_area,property_availability_date,lattitude,longitude,property_status,is_negotiable) values (3,8,'house',4000000,'chikli park',4,'near genral store,viman nagar','wifi access,parking','Spacious room','4000 sqft','2017-05-06',18.5204,73.8567,1,1); 

insert into `capco_living_portal`.`property` (locality_id,property_id,property_type,property_value,property_name,property_bedrooms,property_address,property_specification,property_description,property_area,property_availability_date,lattitude,longitude,property_status,is_negotiable) values (3,9,'flat',3000000,'atlas park',3,'near genral store,viman nagar','wifi access,parking','Spacious room','6000 sqft','2017-08-03',18.5204,73.8567,1,0);




insert into `capco_living_portal`.`home_amenities` (amenity_id,amenity_name) values (1,'security');

insert into `capco_living_portal`.`home_amenities` (amenity_id,amenity_name) values (2,'gym');

insert into `capco_living_portal`.`home_amenities` (amenity_id,amenity_name) values (3,'refrigerator');

insert into `capco_living_portal`.`home_amenities` (amenity_id,amenity_name) values (4,'kitchen');

insert into `capco_living_portal`.`home_amenities` (amenity_id,amenity_name) values (5,'swimming pool');



insert into `capco_living_portal`.`home_amenities_property` (property_id,amenity_id,amenity_name) values (1,1,'security');

insert into `capco_living_portal`.`home_amenities_property` (property_id,amenity_id,amenity_name) values (1,2,'gym');

insert into `capco_living_portal`.`home_amenities_property` (property_id,amenity_id,amenity_name) values (2,3,'refrigerator');

insert into `capco_living_portal`.`home_amenities_property` (property_id,amenity_id,amenity_name) values (2,4,'kitchen');

insert into `capco_living_portal`.`home_amenities_property` (property_id,amenity_id,amenity_name) values (3,4,'kitchen');

insert into `capco_living_portal`.`home_amenities_property` (property_id,amenity_id,amenity_name) values (4,5,'swimming pool');


insert into `capco_living_portal`.`Locality_Amenities` (Locality_Amenity_id,Locality_Amenity_name) values (1,'Restaurant');

insert into `capco_living_portal`.`Locality_Amenities` (Locality_Amenity_id,Locality_Amenity_name) values (2,'Hospital');

insert into `capco_living_portal`.`Locality_Amenities` (Locality_Amenity_id,Locality_Amenity_name) values (3,'Super Market');

insert into `capco_living_portal`.`Locality_Amenities` (Locality_Amenity_id,Locality_Amenity_name) values (4,'School');

insert into `capco_living_portal`.`Locality_Amenities` (Locality_Amenity_id,Locality_Amenity_name) values (5,'Mall');






insert into `capco_living_portal`.`Locality_Amenities_relation` (locality_id,locality_amenity_id,locality_amenity_name) values (1,1,'Restaurant');

insert into `capco_living_portal`.`Locality_Amenities_relation` (locality_id,locality_amenity_id,locality_amenity_name) values (1,2,'Hospital');

insert into `capco_living_portal`.`Locality_Amenities_relation` (locality_id,locality_amenity_id,locality_amenity_name) values (1,3,'Super market');

insert into `capco_living_portal`.`Locality_Amenities_relation` (locality_id,locality_amenity_id,locality_amenity_name) values (2,4,'School');

insert into `capco_living_portal`.`Locality_Amenities_relation` (locality_id,locality_amenity_id,locality_amenity_name) values (2,5,'Mall');






insert into `capco_living_portal`.`school`(school_id,school_name,school_rating) values(1,'abc','80%');
                
insert into `capco_living_portal`.`school`(school_id,school_name,school_rating) values(2,'BGS','75%');

insert into `capco_living_portal`.`school`(school_id,school_name,school_rating) values(3,'DY patil','90%');

insert into `capco_living_portal`.`school`(school_id,school_name,school_rating) values(4,'pccoe','85%'); 
 


insert into `capco_living_portal`.`broadband`(service_id,service_provider_name,service_rating) values(1,'micromax','85%');

insert into `capco_living_portal`.`broadband`(service_id,service_provider_name,service_rating) values(2,'tikona','90%');

insert into `capco_living_portal`.`broadband`(service_id,service_provider_name,service_rating) values(3,'reliance','70%');

insert into `capco_living_portal`.`broadband`(service_id,service_provider_name,service_rating) values(4,'idea','95%'); 
 
  
insert into `capco_living_portal`.`school_locality`(school_id,locality_id,school_rating) values(1,1,'80%');
    
insert into `capco_living_portal`.`school_locality`(school_id,locality_id,school_rating) values(2,2,'75%');
     
insert into `capco_living_portal`.`school_locality`(school_id,locality_id,school_rating) values(3,3,'90%');
      
insert into `capco_living_portal`.`school_locality`(school_id,locality_id,school_rating) values(4,2,'85%'); 





insert into `capco_living_portal`.`broadband_locality`(service_id,locality_id,service_rating) values(1,1,'85%');
   
insert into `capco_living_portal`.`broadband_locality`(service_id,locality_id,service_rating) values(2,2,'90%'); 

insert into `capco_living_portal`.`broadband_locality`(service_id,locality_id,service_rating) values(3,3,'70%');    
     




insert into `capco_living_portal`.`crime` (crime_id,crime_name,crime_rating) values (1,'Domestic','30%');
    
insert into `capco_living_portal`.`crime` (crime_id,crime_name,crime_rating) values (2,'thief','34%');    
    
insert into `capco_living_portal`.`crime` (crime_id,crime_name,crime_rating) values (3,'fraud','50%');



    
insert into `capco_living_portal`.`locality_crime_relation`(locality_id,crime_id,crime_rating) values(1,1,'70%');

insert into `capco_living_portal`.`locality_crime_relation`(locality_id,crime_id,crime_rating) values(2,2,'80%');

insert into `capco_living_portal`.`locality_crime_relation`(locality_id,crime_id,crime_rating) values(3,3,'70%'); 




insert into `capco_living_portal`.`Locality_population_relation`(locality_id,male_population,female_population,childrens_below_18) values(1,50000,30000,2000); 

insert into `capco_living_portal`.`Locality_population_relation`(locality_id,male_population,female_population,childrens_below_18) values(2,30000,4000,200);  

insert into `capco_living_portal`.`Locality_population_relation`(locality_id,male_population,female_population,childrens_below_18) values(3,80000,32000,2600); 








