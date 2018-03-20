angular.module("myApp")
	.controller("requestListCtrl", function ($scope, $location,$state, userAuthentication, travelRequest, jsonTransfer,$filter){
		$scope.user={};
    	$scope.user=jsonTransfer.getInfo();
		// get all requests of user
    	$scope.userRequest;
    	travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
    		$scope.userRequest=[];
    		for(var i = 0; i<res.data.length;i++){
    			if(res.data[i].currentStatus!="cancelled"){
    				$scope.userRequest.push(res.data[i]);
    			}
    		}
    		console.log(res.data);

    	})

//Get Request details on click of specific request in request list
    	$scope.requestDetails;
    	$scope.getRequestDetails=function(requestId){
        	travelRequest.getRequestDetails(requestId).then(function(res){
        		$scope.requestDetails=res.data;
        		$scope.flightObj=$scope.requestDetails.travelRequest.flightRequest;
        		$scope.cabObj=$scope.requestDetails.travelRequest.cabRequest;
        		$scope.accomodationObj=$scope.requestDetails.travelRequest.accomodationRequest;
        		$scope.forexObj=$scope.requestDetails.travelRequest.forexRequest;
        		console.log("request Details ",$scope.requestDetails);
        	},function(err){

        	})
        }
    	
    	// deleting request by request Id
    	$scope.deleteRequest=function(requestId){
    		travelRequest.deleteRecord(requestId).then(function(){
    			console.log("request deleted ",requestId)
    		})
    		
    	}
	})
	
    .controller("newRequestCtrl", function ($scope, $location,$state, userAuthentication, travelRequest, jsonTransfer,$filter) {
    	$scope.user={};
    	$scope.projectList={};
    	$scope.user=jsonTransfer.getInfo();
    	
    	console.log("get Info newrequestctrl ",$scope.user)
    	if($scope.user.isApprover){
    		userAuthentication.level=2
    	}
    	else{
    		userAuthentication.level=1
    	}
    	travelRequest.getApproverAndProjectList().then(
				function(res){
				$scope.projectList=res.data;
				travelRequest.approverAndProjectList=$scope.projectList;
				console.log("projectList: ",$scope.projectList)
				},function(err){
					console.log(err)
				}) 


            $scope.empObj = {}
    	
    	    $scope.empObj.approverName = {};
    		   	
            $scope.flightObj = {};
            
            $scope.cabObj = {}
            
            $scope.accomodationObj = {}
                        
            $scope.forexObj = {}
                      
            $scope.visaObj = {};

            
            
    	//$scope.travelRequest={};
//    	$scope.travelRequest.empData ={};
//            $scope.travelRequest.cabRequest = {};
//            $scope.travelRequest.flightRequest = {};
//            $scope.travelRequest.accomodationRequest = {};
//            $scope.travelRequest.forexRequest = {};
    	
            $scope.accomodationAccordion = "panel-collapse collapse";
            $scope.taxiAccordion = "panel-collapse collapse";
            $scope.flightAccordion = "panel-collapse collapse";
            $scope.forexAccordion = "panel-collapse collapse";
            $scope.visaAccordion = "panel-collapse collapse";
            $scope.accomodationHeading = "panel-heading";
            $scope.activeForm = []


    			//to convert 12 hrs to 24 hrs
    			var timeConversionTo24Hrs = function(timeIn12){
    				 // var timeIn12 = "11:30 pm"
    				console.log(timeIn12,"tIn12");
    				  var arr = timeIn12.split('');
    				  var hours = arr[0]+arr[1];
    				  var minutes = arr[3]+arr[4];
    				 // var seconds = arr[6]+arr[7];
    				  var ampm = arr[6];
    				  hours = +hours;
    				  if(hours<13){
    				    if(ampm=="p"){
    				      hours += 12;
    				    }
    				  }
    				  var timeIn24 = hours +":" + minutes+":00";
    				  console.log(timeIn24,"in 24");
    				  return timeIn24;
    				}
    			
    			
    			// to convert 24 to 12 hrs
    			var timeConversionTo12Hrs = function(timeIn24){
    			   // var timeIn24 = "17:30:10";
    			    console.log(timeIn24," in 24 hours format");
    			    var ampm = "am";
    			    var time = timeIn24.split(':');
    			    var hours = parseInt(time[0]);
    			    var minutes = time[1];
    			    var seconds = time[2];
    			    
    			    if(hours>12){
    			      hours = hours-12;
    			      ampm = "pm";
    			    }
    			    var timeIn12 = hours + ":" + minutes + ":" + seconds + " "+ampm;
    			    console.log(timeIn12,"in 12 hours format");
    			} 
    			
    			var appendDateTime=function(date, time){
    				var dateTime=date+" "+time;
    				return dateTime;
    			}
    			//initiate date to todays date
//    			var dateOptions=function(){
//    				document.getElementById("checkIn").value=new Date();
//    				document.getElementById("checkOut").value=new Date();
//
//    				}
//    				dateOptions();
    				
    				// change date format to yyyy-MM-dd
    				var formatDate=function(date) {
    				    var d = new Date(date),
    				        month = '' + (d.getMonth() + 1),
    				        day = '' + d.getDate(),
    				        year = d.getFullYear();

    				    if (month.length < 2) month = '0' + month;
    				    if (day.length < 2) day = '0' + day;

    				    return [year, month, day].join('-');
    				}
    				
    				
    				//accomodation check in date

    				$scope.chooseCheckInDate=function(){

    					
    					var inDate=document.getElementById("checkIn").value;
    					
    					var t=new Date();
    					var today=formatDate(t);
    					console.log(today);
    					console.log(inDate);
    					//minimum date
    					if(inDate < today){
    					document.getElementById("checkIn").value="";
    					$scope.checkInErr="Please enter date greater or equal to today";
    					}else{
    					document.getElementById("checkOut").value=inDate;
    					$scope.checkIn=inDate;
    					$scope.checkInErr="";
    					}
    					
    				}


    				//accomodation check out date
    				$scope.chooseCheckOutDate=function(){

    					
    					var outDate=document.getElementById("checkOut").value;
    					
    					//var t=new Date();
    					var today=document.getElementById("checkIn").value;
    					console.log(today);
    					console.log(outDate);
    					//minimum date
    					if(outDate < today){
    					document.getElementById("checkOut").value=today;
    					//alert("waring!");
    					$scope.checkOutErr="Please enter date greater or equal to Check-in date";
    					}else{
    					//document.getElementById("toDate").value=d;
    					$scope.checkOut=outDate;
    					$scope.checkOutErr="";
    					}

    				}
    				
    				// cab from date
    				$scope.chooseFromDate=function(){

    					
    					var inDate=document.getElementById("fromDate").value;
    					
    					var t=new Date();
    					var today=formatDate(t);
    					console.log(today);
    					console.log(inDate);
    					//minimum date
    					if(inDate < today){
    					document.getElementById("fromDate").value="";
    					//alert("waring!");
    					$scope.fromDateErr="Please enter date greater or equal to today";
    					}else{
    					document.getElementById("toDate").value=inDate;
    					$scope.fromDate=inDate;
    					$scope.fromDateErr="";
    					}
    					
    				}


    				//cab to date
    				$scope.chooseToDate=function(){

    					
    					var d=document.getElementById("toDate").value;
    					
    					//var t=new Date();
    					var today=document.getElementById("fromDateAndTime").value;
    					console.log(today);
    					console.log(d);
    					//minimum date
    					if(d < today){
    					document.getElementById("toDate").value=today;
    					//alert("waring!");
    					$scope.toDateErr="Please enter date greater or equal to From date";
    					}else{
    					//document.getElementById("toDate").value=d;
    					$scope.cabObj.toDate=d+" 00:00:00";
    					$scope.toDateErr="";
    					}
    					
    				}

    				//flight departure date
    				$scope.chooseDepartureDate=function(){

    					
    					var inDate=document.getElementById("departureDate").value;
    					
    					var temp=new Date();
    					var today=formatDate(temp);
    					console.log(today);
    					console.log(inDate);
    					//minimum date
    					if(inDate < today){
    					document.getElementById("departureDate").value="";
    					$scope.departureDateErr="Please enter date greater or equal to today";
    					}else{
    			
    					$scope.flightObj.departureDate=inDate+" 00:00:00";
    					$scope.departureDateErr="";
    					document.getElementById("returnDate").value=inDate;
    					}
    					
    				}

    				//flight return date

    				$scope.chooseReturnDate=function(){

    					
    					var outDate=document.getElementById("returnDate").value;
    					
    					//var t=new Date();
    					var today=document.getElementById("departureDate").value;
    					console.log(today);
    					console.log(outDate);
    					//minimum date
    					if(outDate < today){
    					document.getElementById("returnDate").value=today;
    					//alert("waring!");
    					$scope.returnDateErr="Please enter date greater or equal to departure date";
    					}else{
    					//document.getElementById("toDate").value=d;
    					$scope.flightObj.returnDate=outDate+" 00:00:00";
    					$scope.returnDateErr="";
    					}
    					
    				}

    				//flight DOB
    				$scope.chooseDOB=function(){

    					//document.getElementById("sample").min=new Date();
    					var date=document.getElementById("dob").value;
    					
    					var t=new Date();
    					var today=formatDate(t);
    					//var tISO=d.toISOString();
    					console.log(today);
    					console.log(date);
    					
    					if(date > today){
    					document.getElementById("dob").value="";
    					//alert("waring!");
    					$scope.dobErr="Please select valid date of birth";
    					}
    					$scope.flightObj.dateOfBirth=date+" 00:00:00";
    					$scope.dobErr="";
    					
    				}
    				//flight passport issue date
    				

    				$scope.choosePassportIssueDate=function(){

    					
    					var inDate=document.getElementById("passportIssueDate").value;
    					
    					var temp=new Date();
    					var today=formatDate(temp);
    					console.log(today);
    					console.log(inDate);
    					//minimum date
    					if(inDate > today){
    					document.getElementById("passportIssueDate").value="";
    					$scope.passportIssueDateErr="Please enter date greater or equal to today";
    					}else{
    					document.getElementById("passportExpiryDate").value=inDate;
    					$scope.flightObj.passportIssueDate=inDate+ " 00:00:00";
    					$scope.passportIssueDateErr="";
    					}
    					
    				}



    				$scope.choosePassportExpiryDate=function(){

    					
    					var outDate=document.getElementById("passportExpiryDate").value;
    					
    					//var t=new Date();
    					var today=document.getElementById("passportIssueDate").value;
    					console.log(today);
    					console.log(outDate);
    					//minimum date
    					if(outDate < today){
    					document.getElementById("passportExpiryDate").value=today;
    					//alert("waring!");
    					$scope.passportExpiryDateErr="Please enter date greater or equal to passport issue date";
    					}else{
    					//document.getElementById("toDate").value=d;
    					$scope.flightObj.passportReturnDate=outDate+" 00:00:00";
    					$scope.passportExpiryDateErr="";
    					}
    					
    				}
    				
    				//forex from date
    				$scope.chooseForexFromDate=function(){

    					
    					var inDate=document.getElementById("forexFromDate").value;
    					
    					var temp=new Date();
    					var today=formatDate(temp);
    					console.log(today);
    					console.log(inDate);
    					//minimum date
    					if(inDate < today){
    					document.getElementById("forexFromDate").value="";
    					$scope.forexFromDateErr="Please enter date greater or equal to today";
    					}else{
    					document.getElementById("forexToDate").value=inDate;
    					$scope.forexObj.forexFromDate=inDate+="00:00:00";
    					$scope.forexFromDateErr="";
    					}
    					
    				}



    				$scope.chooseForexToDate=function(){

    					
    					var outDate=document.getElementById("forexToDate").value;
    					
    					//var t=new Date();
    					var today=document.getElementById("forexFromDate").value;
    					console.log(today);
    					console.log(outDate);
    					//minimum date
    					if(outDate < today){
    					document.getElementById("forexToDate").value=today;
    					//alert("waring!");
    					$scope.forexToDateErr="Please enter date greater or equal to from date";
    					}else{
    					//document.getElementById("toDate").value=d;
    					$scope.forexObj.forexToDate=outDate+" 00:00:00";
    					$scope.forexToDateErr="";
    					}
    					
    				}
    				
    				
    				$scope.selectCheckInTime=function(checkInTime){
    					var fullTime=timeConversionTo24Hrs(checkInTime);
    					$scope.accomodationObj.checkIn=appendDateTime($scope.checkIn, fullTime);
    					//alert($scope.accomodationObj.checkIn);
    					
    				}
    				
    				$scope.selectPickUpTime=function(checkInTime){
    					var fullTime=timeConversionTo24Hrs(checkInTime);
    					$scope.cabObj.fromDate=appendDateTime($scope.fromDate, fullTime);
    					//alert($scope.accomodationObj.checkIn);
    					
    				}
    				
    				$scope.selectCheckOutTime=function(checkOutTime){
    					var fullTime=timeConversionTo24Hrs(checkOutTime);
    					$scope.accomodationObj.checkOut=appendDateTime($scope.checkOut, fullTime);
    					//alert($scope.accomodationObj.checkOut);
    					
    				}

        $scope.clickHand = function() {
            console.log($scope);
        }




            //All the error messages and dropdown fields data is comming from here
            userAuthentication.errMsg().then(function (res) {
                $scope.err = res;
                $scope.requestObject = res.data;
                $scope.intervals = $scope.requestObject.timeIntervals;
                // $scope.intervals=$scope.err.data.timeIntervals[0];
                $scope.checkInTime = $scope.requestObject.timeIntervals[0];
                $scope.checkOutTime = $scope.requestObject.timeIntervals[0];
                $scope.cabObj.pickUpTime = $scope.requestObject.timeIntervals[0];

                $scope.requestObject = res.data;
                $scope.personalDetails = $scope.requestObject.personalDetails;

                $scope.requestDetails = $scope.requestObject.requestDetails;
                $scope.accomodation = $scope.requestDetails.accomodation;
                $scope.taxi = $scope.requestDetails.taxi;
                $scope.flight = $scope.requestDetails.flight;
                $scope.forex = $scope.requestDetails.forex;

                // console.log("err:", $scope.forex);

                // $scope.city = res.data.requestDetails.accomodation.city;
                // console.log($scope.city)

            });

            $scope.errMsg = "errMsg";


            $scope.collapseAccordion = function (accordionName) {


                if (accordionName == "accomodationAccordion") {
                	
                    if ($scope.accomodationAccordion == "panel-collapse collapse") {
                        $scope.accomodationAccordion = "panel-collapse collapse in";
                        $scope.accomodationStatus = true;
                    } else {
                        $scope.accomodationAccordion = "panel-collapse collapse";
                        $scope.accomodationStatus= false;
                    }
                }

                if (accordionName == "taxiAccordion") {

                    if ($scope.taxiAccordion == "panel-collapse collapse") {
                        $scope.taxiAccordion = "panel-collapse collapse in";
                        $scope.taxiStatus = true;
                    } else {
                        $scope.taxiAccordion = "panel-collapse collapse";
                        $scope.taxiStatus = false;
                    }
                }

                if (accordionName == "flightAccordion") {

                    if ($scope.flightAccordion == "panel-collapse collapse") {
                        $scope.flightAccordion = "panel-collapse collapse in";
                        $scope.flightStatus = true;
                    } else {
                        $scope.flightAccordion = "panel-collapse collapse";
                        $scope.flightStatus = false;
                    }
                }

                if (accordionName == "forexAccordion") {

                    if ($scope.forexAccordion == "panel-collapse collapse") {
                        $scope.forexAccordion = "panel-collapse collapse in";
                        $scope.forexStatus = true;
                    } else {
                        $scope.forexAccordion = "panel-collapse collapse";
                        $scope.forexStatus = false;
                    }
                }
                
                if (accordionName == "visaAccordion") {

                    if ($scope.visaAccordion == "panel-collapse collapse") {
                        $scope.visaAccordion = "panel-collapse collapse in";
                        $scope.visaStatus = true;
                    } else {
                        $scope.visaAccordion = "panel-collapse collapse";
                        $scope.visaStatus = false;
                    }
                }


            }

// Get all records for the approver to take action
            $scope.approverId=$scope.user.employeeId;
        	$scope.requestToApprove;
            travelRequest.getAllRecords($scope.approverId).then(function(res){
        		$scope.requestToApprove=res.data;
        	},function(err){
        		console.log("error: ",err);
        	})

        	
 


        	// Get request to take action on         	
        	$scope.requestToApproveDetails;
        	$scope.aq;
        	$scope.getRequestToApproveDetails=function(requestId){
        		
            	travelRequest.getRequestDetails(requestId).then(function(res){
            		$scope.requestToApproveDetails=res.data;
            		$state.go('actionOnRequest')
            		
            		
            		console.log("request to approve Details inside function",$scope.requestToApproveDetails);
            		travelRequest.request = $scope.requestToApproveDetails;
            	},function(err){

            	})
            }
        	console.log("service variable ",travelRequest.request)
//        	console.log("request to approve Details ",$scope.requestToApproveDetails);
        	
        	


        	
        	
        	/*$scope.gradeCheckForCab = function(carType){
                switch(jsonObj)
                {
                 case "m0":
                 case "m1":
                 case "m2":
                 case "m3":
                 case "m4": if(carType!="Hatchback"){
                                                 console.log("Please Consult with your manager");
                                                 }break;
                 case "m5":
                 case "m6": if(carType=="Luxury"){
                             console.log("Please Consult with your manager");
                             }
                             break;
                 default:
                         console.log("Mast");
                }


         }
*/



            $scope.accomodationCheckInDate;
            $scope.accomodationCheckOutDate;
            $scope.accomodationCheckInTime;
            $scope.accomodationCheckOutTime;
            $scope.forexObj.forexNoOfDays;
            $scope.addDays = function (fromDate, toDate) {
            	var to = new Date(toDate);
            	var from = new Date(fromDate);
            	var oneDay=24*60*60*1000; // hr * min * sec * millisec  
            	var days = Math.round(Math.abs((to.getTime()-from.getTime())/(oneDay)));
            	$scope.forexObj.forexNoOfDays=days;
            	alert($scope.forexObj.forexNoOfDays)
//            	var d1 = new Date(fromDate);
//            	var d2 = new Date(toDate);
//            	var d1 = moment(fromDate);
//            	var d2 = moment(toDate);
//            	var days = moment.duration(d2.diff(d1)).asDays();
//            	$scope.forexObj.forexNoOfDays=days;
//            	alert($scope.forexObj.forexNoOfDays);
            } 



           
// Confirm modal
            
           /* $scope.showConfirmModal = false;
            $scope.unCheckAcco = function(){
                if($scope.accommodationForm.$dirty){
                    //show confirmation popup to ask yes or no
                    $scope.showConfirmModalAcco = true;
                }

             }

             $scope.unCheckFlight = function(){
                if($scope.flightForm.$dirty){
                    //show confirmation popup to ask yes or no
                    $scope.showConfirmModalFlight = true;
                }

             }

             $scope.unCheckCab = function(){
                if($scope.cabForm.$dirty){
                    //show confirmation popup to ask yes or no
                    $scope.showConfirmModalCab = true;
                }

             }

             $scope.unCheckForex = function(){
                if($scope.forexForm.$dirty){
                    //show confirmation popup to ask yes or no
                    $scope.showConfirmModalForex = true;
                }
             }


             $scope.confirmDiscardAccoYes = function(){
                $scope.accommodationCheckBox = {}
             }
             $scope.confirmDiscardAccoNo = function(){
                $scope.accommodationCheckBox = true;
            }



            $scope.confirmDiscardFlightYes = function(){
                $scope.accommodationCheckBox = {}
             }

             $scope.confirmDiscardFlightNo = function(){
                $scope.accommodationCheckBox = true;
            }



            $scope.confirmDiscardCabYes = function(){
                $scope.accommodationCheckBox = {}
             }

             $scope.confirmDiscardCabNo = function(){
                $scope.accommodationCheckBox = true;
            }


            $scope.confirmDiscardForexYes = function(){
                $scope.accommodationCheckBox = {}
             }

             $scope.confirmDiscardForexkNo = function(){
                $scope.accommodationCheckBox = true;
            }
*/

//            $scope.travelRequest = {
//                requestId: "",
//                requestedBy: 16290,
//                requestedFor: "Self",
//                approverId: 16294,
//                currentStatus: "submitted",
//                createdOn: "2017-11-02 00:00:00",
//                modifiedOn: "2017-11-02 00:00:00",
//                remark: "new Request",
//                actionOnRequest: "",
//                //empData:$scope.empObj
//                // flightRequest:$scope.flightObj,
//                // cabRequest:$scope.cabObj,
//                // accomodationRequest:$scope.accomodationObj,
//                // forexRequest:$scope.forexObj
//            }
//
            //Approver id from approver name:
            
             
             $scope.travelRequest= {
             	    requestId: "",
             	    requestedBy: $scope.user.employeeId,
             	    //requestType: $scope.self,
             	    //requestedFor : $scope.empObj.employeeName,
             	    //projectName : $scope.projectName,
             	    currentStatus: "Pending L1",
             	    remark: "new Request",
             	    actionOnRequest: ""
             	    	}
             
             
             
             $scope.travelRequest.accomodationRequest = null;
             $scope.travelRequest.cabRequest = null;
             $scope.travelRequest.flightRequest = null;
             $scope.travelRequest.forexRequest=null;
             $scope.travelRequest.visaRequestVO=null;
             
             
             
//            $scope.travelRequest.empData = $scope.empObj;
//            $scope.travelRequest.cabRequest = $scope.cabObj;
//            //$scope.travelRequest.flightRequest = $scope.flightObj;
//            $scope.travelRequest.accomodationRequest = $scope.accomodationObj;
//            $scope.travelRequest.forexRequest = $scope.forexObj;
            //$scope.travelRequest.approverId=$scope.travelRequest.empData.approverName;
            //$scope.travelRequest.approverId= $scope.empObj.approverName.approverId;
//            $scope.cabObj.fromDate="2017-11-20 04:30:00"
//            $scope.cabObj.toDate="2017-11-20 04:30:00"
        

            
            $scope.checkedOrNotFlight = function (checkVal) {
                if (checkVal == true) {
                    $scope.travelRequest.flightRequest = $scope.flightObj;
                } else {
                    $scope.travelRequest.flightRequest = null;
                }
//                console.log($scope.travelRequest);
            }

            $scope.checkedOrNotAccomodation = function (checkVal) {
                if (checkVal == true) {
                    $scope.travelRequest.accomodationRequest = $scope.accomodationObj;
                } else {
                    $scope.travelRequest.accomodationRequest=null;
                }
//                console.log($scope.travelRequest);
            }

            $scope.checkedOrNotForex = function (checkVal) {
            	
                if (checkVal == true) {
                    $scope.travelRequest.forexRequest = $scope.forexObj;
                } else {
                    $scope.travelRequest.forexRequest = null;
                }
                //console.log($scope.travelRequest);
            }

            $scope.checkedOrNotCab = function (checkVal) {
            	
                if (checkVal == true) {
                    $scope.travelRequest.cabRequest = $scope.cabObj;
                } else {
                    $scope.travelRequest.cabRequest = null;
                }
                //console.log($scope.travelRequest);
            }
            
			$scope.checkedOrNotVisa = function (checkVal) {
				alert("insied fun");
			                if (checkVal == true) {
			                	alert("insied if");
			                    $scope.travelRequest.visaRequestVO = $scope.visaObj;
			                } else {
			                	
			                	alert("insied if");
			                    $scope.travelRequest.visaRequestVO = null;
			                }
			                //console.log($scope.travelRequest);
			            }
            
            /* $scope.forexObj.forexAmount="100";
             //advance amount
             $scope.forexObj.forexToDate="2018-01-01 12:00:00";*/
             $scope.send = function () {
            	$scope.travelRequest.approverId= $scope.empObj.approverName.approverId;
            	$scope.travelRequest.requestedFor = $scope.empObj.employeeName;
            	$scope.travelRequest.projectName = $scope.empObj.projectName;
            	//$scope.travelRequest.cabRequest.pickUpTime = $scope.cabObj.pickUpTime;
            	console.log($scope.q,'q',$scope.empObj.projectName,'w')
            	console.log("Approver ID : " + $scope.travelRequest.approverId);
            	console.log("Approver EMPID : " + $scope.empObj.approverName.approverId);
            	console.log("data--",$scope.travelRequest);
            	travelRequest.createRecord($scope.travelRequest)
                    .then(function (data) {
                        console.log(data);
                        alert("Request Submitted");
                        $state.go('home');
                    },function(err){
                    	console.log("error ",err)
                    	alert("Request not submitted, Please enter all the fields in request details.");
                    });
            }




            $scope.colorChangePersonal = function () {
                $scope.tabClassPersonal = "whiteTab";
                $scope.tabClassRequest = "greyTab";
                $scope.tabClassConfirm = "greyTab";
            }

            $scope.colorChangeRequest = function () {
                $scope.tabClassPersonal = "greyTab";
                $scope.tabClassRequest = "whiteTab";
                $scope.tabClassConfirm = "greyTab";
                $scope.progressClass1 = "progress-bar";
            }

            $scope.colorChangeConfirm = function () {
                $scope.tabClassPersonal = "greyTab";
                $scope.tabClassRequest = "greyTab";
                $scope.tabClassConfirm = "whiteTab";
                $scope.progressClass2 = "progress-bar";
            }

            $scope.openNextForm = function (accordionName) {

                if (accordionName == "accomodationAccordion") {

                    if ($scope.accomodationAccordion == "panel-collapse collapse in") {
                        $scope.accomodationAccordion = "panel-collapse collapse";
                        $scope.accomodationHeading += "back-green";
                    }
                }


            }

            $scope.checkClick=function(temp){
                console.log("temp"+temp);
                console.log($scope.flightObj.tourType);

            }
            
            
            

            //Need this js to open and close accordion after clicking next
            // $scope.selectForm1 = function (formName) {


            //     $scope.checkBox = "$scope." + formName;
            //     //  alert( $scope.checkBox);
            //     if (formName == 'accommodationCheckBox') {
            //         if ($scope.accommodationCheckBox && !$scope.activeForm.includes("accommodationCheckBox")) {
            //             $scope.activeForm.push("accommodationCheckBox");
            //             console.log("if");
            //         } else if ($scope.activeForm.includes("accommodationCheckBox")) {
            //             $scope.activeForm.splice("accommodationCheckBox");
            //             console.log("elseif");
            //         }
            //     }

            //     if (formName == 'taxiCheckBox') {

            //         if ($scope.taxiCheckBox && !$scope.activeForm.includes("taxiCheckBox")) {
            //             $scope.activeForm.push("taxiCheckBox");
            //         } else if ($scope.activeForm.includes("taxiCheckBox")) {
            //             $scope.activeForm.splice("taxiCheckBox");
            //         }
            //     }

            //     if (formName == 'flightCheckBox') {
            //         if ($scope.flightCheckBox && !$scope.activeForm.includes("flightCheckBox")) {
            //             $scope.activeForm.push("flightCheckBox");
            //         } else if ($scope.activeForm.includes("flightCheckBox")) {
            //             $scope.activeForm.splice("flightCheckBox");
            //         }
            //     }

            //     if (formName == 'forexCheckBox') {
            //         if ($scope.forexCheckBox && !$scope.activeForm.includes("forexCheckBox")) {
            //             $scope.activeForm.push("forexCheckBox");
            //         } else if ($scope.activeForm.includes("forexCheckBox")) {
            //             $scope.activeForm.splice("forexCheckBox");
            //         }
            //     }

            //     console.log($scope.activeForm);
            // }
            
            $scope.nextPreviosButton=function(value){
            	if(true){
            	$("#"+value).click();
            	}
            	} 

            //javascript bootstrap tooltip
            $(document).ready(function(){
                $('[data-toggle="tooltip"]').tooltip();   
            });
            
            $scope.empName="";
            $scope.setPersonalData=function(){
            	
            	if($scope.travelRequest.requestType=="Self"){
            	$scope.empObj.employeeName=$scope.user.employeeName;
            	$scope.empObj.employeeMobileNumber=$scope.user.employeeMobileNumber;
            	//$scope.empObj.employeeLocation=$scope.user.employeeLocation;
             	$scope.empObj.projectCode=$scope.user.projectCode;
             	$scope.empName="Employee Name:";
             	 $scope.getProjectName();
             	 console.log($scope.travelRequest.requestType);
            }else if($scope.travelRequest.requestType=="Other"){
            	$scope.empObj.employeeName="";
            	$scope.empObj.employeeMobileNumber="";
            	//$scope.empObj.employeeLocation="";
            	$scope.empObj.projectCode="";
            	$scope.empName="Requested For:";
            	console.log($scope.travelRequest.requestType);
            }
            }
            
            $scope.getProjectName=function(){
            	console.log("projectList",$scope.projectList);
            	for(var i=0;i<$scope.projectList.listOfProjectDetails.length;i++){
            		if($scope.projectList.listOfProjectDetails[i].projectCode==$scope.empObj.projectCode){
            			
            			$scope.empObj.projectName=$scope.projectList.listOfProjectDetails[i].projectName;
            			
            		}
            		
            	}
            	 for(var i = 0; i<$scope.projectList.listOfApprovers.length; i++){
                	 if($scope.projectList.listOfApprovers[i].approverName==$scope.empObj.approverName){
             			
             			$scope.empObj.approverId=$scope.projectList.listOfApprovers[i].approverId;
             			
             		}

                 }
                 console.log($scope.empObj);
            }
            
            
            
         
                       
    } )
