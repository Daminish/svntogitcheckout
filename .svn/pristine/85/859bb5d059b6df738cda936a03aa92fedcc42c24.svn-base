angular.module("myApp")
	.controller("requestListCtrl", function ($scope, $location,$state, userAuthentication, travelRequest, jsonTransfer,$filter,editRequestJsonTransfer,requestTypeParameterTransfer){
		$scope.user={};
    	$scope.user=jsonTransfer.getInfo();
		// get all requests of user
    	$scope.userRequest;
    	
        var requestDetailsType=requestTypeParameterTransfer.getInfo();
        console.log(requestTypeParameterTransfer.getInfo(),"hello");
        $scope.typeOfReq=requestDetailsType.type;
        console.log(requestDetailsType.type,"$scope.requestDetailsType");
        
        if(requestDetailsType.type==null){
        travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
               console.log(res.data,"ByUserId");       
               $scope.userRequest=[];
               if(res.data.length==0){
                     alert("No requests found");
                     $state.go('home')
               }
               else{
                     for(var i = 0; i<res.data.length;i++){
                            if(res.data[i].currentStatus!="Cancelled"){
                                   $scope.userRequest.push(res.data[i]);
                            }
                     }
               }
               

        },function(err){
               if(err.status==400){
                     console.log(err.data)
                     $state.go('login')
               }
        })
        }
        else{
               travelRequest.getRequestDetailsByType(requestDetailsType.type).then(function(res){
                     requestDetailsType.type=null;
                     console.log(res,"ByType"); 
                     $scope.userRequest=[];
               if(res.data.requestListVO.length==0){
                     alert("No requests found");
                     $state.go('home')
               }
               else{
                     for(var i = 0; i<res.data.requestListVO.length;i++){
                            if(res.data.requestListVO[i].currentStatus!="Cancelled"){
                                   $scope.userRequest.push(res.data.requestListVO[i]);
                            }
                     }
               }
//            console.log(res.data);
               },function(err){
                   if(err.status==400){
                       console.log(err.data)
                       $state.go('login')
                 }
          })
        }

    			 

    	
    	/*travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
    		$scope.userRequest=[];
    	  		if(res.data.length==0){
    			alert("No requests found");
    			$state.go('home')
    		}
    		else{
    			for(var i = 0; i<res.data.length;i++){
    				if(res.data[i].currentStatus!="Cancelled"){
    					$scope.userRequest.push(res.data[i]);
    				}
    			}
    		}
   		

    	},function(err){
    		if(err.status!=200){
    			console.log(err.data)
    			$state.go('login')
    		}
    	})*/
    	
    	//returning home/dashboard from Request list
    	$scope.home = function(){
    		$state.go("home");
    	}

    	
    	
//Get Request details on click of specific request in request list
    	$scope.requestDetails;
    	$scope.getRequestDetails=function(requestId){
        	travelRequest.getRequestDetails(requestId).then(function(res){
        		$scope.requestDetails=res.data;
        		$scope.flightObj=$scope.requestDetails.travelRequest.flightRequest;
        		$scope.cabObj=$scope.requestDetails.travelRequest.cabRequest;
        		$scope.accomodationObj=$scope.requestDetails.travelRequest.accomodationRequest;
        		$scope.forexObj=$scope.requestDetails.travelRequest.forexRequest;
        		$scope.visaObj=$scope.requestDetails.travelRequest.visaRequestVO;
        		$scope.empDetails=$scope.requestDetails.travelRequest.empData;
        		console.log("request Details ",$scope.requestDetails);
        		console.log("emp Date", $scope.empDetails);
        	},function(err){
        		if(err.status==400){
        			console.log(err.data)
        			$state.go('login')
        		}
        	});
    	}
        
	
    	
    	// deleting request by request Id
    	$scope.deleteRequest=function(requestId){
    		travelRequest.deleteRecord(requestId).then(function(){
    			console.log("request deleted ",requestId)
    			travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
    		$scope.userRequest=[];
    		if(res.data.length==0){
    			alert("No requests found");
    			$state.go('home')
    		}
    		else{
    			for(var i = 0; i<res.data.length;i++){
    				if(res.data[i].currentStatus!="Cancelled"){
    					$scope.userRequest.push(res.data[i]);
    				}
    			}
    		}

    	},function(err){
    		if(err.status==400){
    			console.log(err.data)
    			$state.go('login')
    		}
    	})
    			travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
    		$scope.userRequest=[];
    		if(res.data.length==0){
    			alert("No requests found");
    			$state.go('home')
    		}
    		else{
    			for(var i = 0; i<res.data.length;i++){
    				if(res.data[i].currentStatus!="Cancelled"){
    					$scope.userRequest.push(res.data[i]);
    				}
    			}
    		}

    	},function(err){
    		if(err.status==400){
    			console.log(err.data)
    			$state.go('login')
    		}
    	})
    		},function(err){
    			if(err.status==400){
    				console.log(err.data)
    				$state.go('login')
    			}
    		});
    		
    	}
    	
    	  $scope.empObj = {}
  	    //$scope.empObj.approverName={};
          $scope.flightObj = {};
          $scope.visaObj={};
          $scope.flightNull = null;

          $scope.cabObj = {}
          $scope.cabNull = null;

          $scope.accomodationObj = {}
          $scope.accomodationNull = null;

          $scope.forexObj = {}
          $scope.forexNull = null
          $scope.empObj.employeeNameq = "";
    	 $scope.editRequest=function(requestId){
        	  $scope.abc="abc";
        	 
         	travelRequest.getRequestDetails(requestId).then(function(res){
         		$scope.requestDetails=res.data.travelRequest;
         		console.log("before transfer",$scope.requestDetails);
         		editRequestJsonTransfer.setInfo($scope.requestDetails);
         		 $state.go("editRequest");
//         		console.log($scope.requestDetails.travelRequest.requestedFor,'requested for');
//         		$scope.empObj.employeeNameq = $scope.requestDetails.travelRequest.requestedFor;
//         		$scope.flightObj=$scope.requestDetails.travelRequest.flightRequest;
//         		$scope.cabObj=$scope.requestDetails.travelRequest.cabRequest;
//         		$scope.accomodationObj=$scope.requestDetails.travelRequest.accomodationRequest;
//         		$scope.forexObj=$scope.requestDetails.travelRequest.forexRequest;
//         		
//         		console.log("request Details for edit ",$scope.empObj.employeeNameq,$scope.requestDetails);
         	},function(err){
         		if(err.status==400){
        			console.log(err.data)
        			$state.go('login')
        		}
         	});
         	
         }
	})
	
    .controller("newRequestCtrl", function ($scope, $location,$state, userAuthentication, travelRequest, jsonTransfer, $filter, $mdDialog) {
    	$scope.user={}; 
    	$scope.projectList={};
    	$scope.user=jsonTransfer.getInfo();
    	$scope.classValue1 = "active";
    	
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
					if(err.status==400){
						console.log(err.data)
						$state.go('login')
					}
					console.log(err)
				}) 


            $scope.empObj = {}
    	
    	    //$scope.empObj.approverName = {};
    		   	
            $scope.flightObj = {};
            
            $scope.cabObj = {}
            $scope.cabObj.country = "INDIA";
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
    				
    		
    					 

                    
                    //------------------------------------------------
                    //Ui bootstrap date logic for accomodation form::
                    
                    //variable declaration
                    
                    var today = new Date();
                    $scope.dateFormat = 'dd-MM-yyyy';
                    $scope.accomodationObj.checkInDate=new Date();
                    $scope.accomodationObj.checkOutDate= new Date();
                    
                    
                    $scope.fromDateOptions = {
                            formatYear: 'yy',
                            startingDay: 1,
                            minDate: today,
                            maxDate: new Date(2030, 5, 22)
                        };
                        $scope.toDateOptions = {
                            formatYear: 'yy',
                            startingDay: 1,
                            minDate: today,
                            maxDate: new Date(2030, 5, 22)
                        };
                        $scope.fromDatePopup = {
                            opened: false
                        };
                        $scope.toDatePopup = {
                            opened: false
                        };
                        $scope.ChangeToMinDate = function(accoFromDate) {
                            if (accoFromDate != null) {
                            	$scope.tempDate= new Date(accoFromDate)
                                var expiryMinDate = new Date($scope.tempDate.setDate(accoFromDate.getDate()+1));

                                $scope.toDateOptions.minDate = expiryMinDate;
                                $scope.accomodationObj.checkOutDate = expiryMinDate;
                            }
                        };
                        $scope.ChangeToMinDate();
                        $scope.OpenFromDate = function() {
                            $scope.fromDatePopup.opened = !$scope.fromDatePopup.opened;
                        };
                        $scope.OpenToDate = function() {
                            $scope.toDatePopup.opened = !$scope.toDatePopup.opened;
                        };
                    
                        //End of hotel/accomodation date logic
                        
                        
                       //UI bootstrap date picker taxi date logic::
                       //
                        
                        $scope.cabFromDate=new Date();
                        $scope.cabObj.toDate= new Date();
                        $scope.dateFormat = 'dd-MM-yyyy';
                        
                        $scope.taxiFromDateOptions = {
                                formatYear: 'yy',
                                startingDay: 1,
                                minDate: today,
                                maxDate: new Date(2030, 5, 22)
                            };
                            $scope.taxiToDateOptions = {
                                formatYear: 'yy',
                                startingDay: 1,
                                minDate: today,
                                maxDate: new Date(2030, 5, 22)
                            };
                            $scope.taxiFromDatePopup = {
                                opened: false
                            };
                            $scope.taxiToDatePopup = {
                                opened: false
                            };
                            $scope.taxiChangeToMinDate = function(taxiFromDate) {
                                if (taxiFromDate != null) {
                                	$scope.cabFromDate=taxiFromDate;
                                	var expiryMinDate = new Date(taxiFromDate);
                                    $scope.taxiToDateOptions.minDate = expiryMinDate;
                                    $scope.cabObj.toDate = expiryMinDate;
                                }
                            };
                            $scope.taxiChangeToMinDate();
                            $scope.taxiOpenFromDate = function() {
                                $scope.taxiFromDatePopup.opened = !$scope.taxiFromDatePopup.opened;
                            };
                            $scope.taxiOpenToDate = function() {
                                $scope.taxiToDatePopup.opened = !$scope.taxiToDatePopup.opened;
                            };
                            
                            //End of taxi date picker logic
                            
                            
                            //Datepicker logic for flight
                            
                            
                            $scope.flightObj.departureDate=new Date();
                            $scope.flightObj.returnDate= new Date();
                            $scope.flightObj.dateOfBirth= new Date((today.getFullYear()-18),01,01);
                            $scope.flightObj.passportIssueDate= new Date();
                            $scope.flightObj.passportExpiryDate= new Date($scope.flightObj.passportIssueDate.getFullYear()+10,$scope.flightObj.passportIssueDate.getMonth(),$scope.flightObj.passportIssueDate.getDate());
                            //$scope.dateFormat = 'dd/MM/yyyy';
                            $scope.flightDepartureDateOptions = {
                                    formatYear: 'yy',
                                    startingDay: 1,
                                    minDate: today,
                                    maxDate: new Date(2030, 5, 22)
                                };
                                $scope.flightReturnDateOptions = {
                                    formatYear: 'yy',
                                    startingDay: 1,
                                    minDate: today,
                                    maxDate: new Date(2030, 5, 22)
                                };
                                $scope.dobOptions = {
                                        formatYear: 'yy',
                                        startingDay: new Date(1970, 5, 22),
                                        minDate: new Date((today.getFullYear()-60),1,1),
                                        maxDate: new Date((today.getFullYear()-18),1,1)
                                    };
                                $scope.passIssueDateOptions = {
                                        formatYear: 'yy',
                                        startingDay: 1,
                                        minDate: new Date((today.getFullYear()-9),1,1),
                                        maxDate: today
                                    };
                                $scope.passExpiryDateOptions = {
                                        formatYear: 'yy',
                                        startingDay: 1,
                                        minDate: new Date($scope.flightObj.passportIssueDate.getFullYear()+10,$scope.flightObj.passportIssueDate.getMonth(),$scope.flightObj.passportIssueDate.getDate()),
                                        maxDate: new Date($scope.flightObj.passportIssueDate.getFullYear()+20,$scope.flightObj.passportIssueDate.getMonth(),$scope.flightObj.passportIssueDate.getDate())
                                    };
                                $scope.flightDepartureDatePopup = {
                                    opened: false
                                };
                                $scope.flightReturnDatePopup = {
                                    opened: false
                                };
                                $scope.dobPopup = {
                                        opened: false
                                    };
                                $scope.passIssueDatePopup = {
                                        opened: false
                                    };
                                $scope.passExpiryDatePopup = {
                                        opened: false
                                    };
                                $scope.flightChangeToMinDate = function(flightDepartureDate) {
                                    if (flightDepartureDate != null) {

                                        //var expiryMinDate = new Date(accoFromDate.setDate(accoFromDate.getDate()+1));
                                    	var expiryMinDate = new Date(flightDepartureDate);
                                        $scope.flightReturnDateOptions.minDate = expiryMinDate;
                                        $scope.flightObj.returnDate = expiryMinDate;
                                    }
                                };
                                $scope.flightChangeToMinDate();
                                $scope.flightInternationalChangeToMinDate = function(flightDepartureDate) {
                                    if (flightDepartureDate != null) {
                                    	$scope.tempDate= new Date(flightDepartureDate);
                                        var expiryMinDate = new Date($scope.tempDate.setDate($scope.tempDate.getDate()+2));
                                        $scope.flightReturnDateOptions.minDate = expiryMinDate;
                                        $scope.flightObj.returnDate = expiryMinDate;
                                    }
                                };
                                $scope.flightInternationalChangeToMinDate();
                                
                                $scope.passExpiryChangeToMinDate = function(passIssueDate) {
                                    if (passIssueDate != null) {
                                    	$scope.tempDate= new Date(passIssueDate);
                                        var expiryMinDate = new Date($scope.tempDate.setDate($scope.tempDate.getFullYear()+10,$scope.tempDate.getMonth(),$scope.tempDate.getDate()));
                                        $scope.passExpiryDateOptions.minDate = expiryMinDate;
                                        $scope.flightObj.passportExpiryDate = expiryMinDate;
                                    }
                                };
                               $scope.chooseReturnDate=function(passExpiryDate){
                                	
                                	var date1 = new Date($scope.flightObj.returnDate); //Remember, months are 0 based in JS
                                	var date2 = new Date(passExpiryDate);
                                	var year1 = date1.getFullYear();
                                	var year2 = date2.getFullYear();
                                	var month1 = date1.getMonth();
                                	var month2 = date2.getMonth();
                                	if(month1===0){ 
                                	  month1++;
                                	  month2++;
                                	} 
                                	$scope.numberOfMonths =(((year2-year1)*12)+(month2-month1));
                                	console.log("flight no. of months",$scope.numberOfMonths);
                                	if($scope.numberOfMonths<=6){
                                		$scope.returnDateErr="Return Date must be less than 6 months of Passport Expiry Date"
                                    		self.mainForm.flightReturnDate.$setValidity('flightReturnDate',false);
                                	}
                                	else{
                                		self.mainForm.flightReturnDate.$setValidity('flightReturnDate',true);
                                		$scope.returnDateErr="";
                                	}
                                	
                                }
                               
                                $scope.flightOpenDepartureDate = function() {
                                    $scope.flightDepartureDatePopup.opened = !$scope.flightDepartureDatePopup.opened;
                                };
                                $scope.flightOpenReturnDate = function() {
                                    $scope.flightReturnDatePopup.opened = !$scope.flightReturnDatePopup.opened;
                                };
                                $scope.OpenDob = function() {
                                    $scope.dobPopup.opened = !$scope.dobPopup.opened;
                                };
                                $scope.openPassIssueDate = function() {
                                    $scope.passIssueDatePopup.opened = !$scope.passIssueDatePopup.opened;
                                };
                                $scope.openPassExpiryDate = function() {
                                    $scope.passExpiryDatePopup.opened = !$scope.passExpiryDatePopup.opened;
                                };
                            
                            
                            //End of flight datepicker
                            
                            
                            //Forex date logic using ui-bootstrap
                           
                            $scope.forexObj.forexFromDate = new Date();
                            $scope.forexObj.forexToDate = new Date();
                            
                            $scope.forexFromDateOptions = {
                                    formatYear: 'yy',
                                    startingDay: 1,
                                    minDate: today,
                                    maxDate: new Date(2030, 5, 22)
                                };
                                $scope.forexToDateOptions = {
                                    formatYear: 'yy',
                                    startingDay: 1,
                                    minDate: today,
                                    maxDate: new Date(2030, 5, 22)
                                };
                                $scope.forexFromDatePopup = {
                                    opened: false
                                };
                                $scope.forexToDatePopup = {
                                    opened: false
                                };
                                $scope.forexChangeToMinDate = function(forexFromDate) {
                                    if (forexFromDate != null) {

                                        //var expiryMinDate = new Date(accoFromDate.setDate(accoFromDate.getDate()+1));
                                    	var expiryMinDate = new Date(forexFromDate);
                                        $scope.forexToDateOptions.minDate = expiryMinDate;
                                        $scope.forexObj.forexToDate = expiryMinDate;
                                    }
                                };
                                $scope.forexChangeToMinDate();
                                $scope.forexOpenFromDate = function() {
                                    $scope.forexFromDatePopup.opened = !$scope.forexFromDatePopup.opened;
                                };
                                $scope.forexOpenToDate = function() {
                                	console.log("forexopentodate ",$scope.forexFromDate)
                                    $scope.forexToDatePopup.opened = !$scope.forexToDatePopup.opened;
                                };
                            
                            //End of date logic of Forex
                        
    				
    				
    				$scope.selectCheckInTime=function(checkInTime){
    					var fullTime=timeConversionTo24Hrs(checkInTime);
    					$scope.accomodationObj.checkIn=appendDateTime($scope.checkIn, fullTime);
    					//alert($scope.accomodationObj.checkIn);
    					
    				}
    				
    				
    				$scope.selectPickUpTime=function(checkInTime){
    					var fullTime=timeConversionTo24Hrs(checkInTime);
    					$scope.cabFromDate=$filter('date')($scope.cabFromDate, "yyyy-MM-dd"); 
    					console.log("select Pickup Time",$scope.cabFromDate, fullTime)
    					$scope.cabObj.fromDateAndTime=appendDateTime($scope.cabFromDate, fullTime);
    				}
    				
    				$scope.selectCheckOutTime=function(checkOutTime){
    					var fullTime=timeConversionTo24Hrs(checkOutTime);
    					$scope.accomodationObj.checkOut=appendDateTime($scope.checkOut, fullTime);
    					//alert($scope.accomodationObj.checkOut);
    					
    				}

        $scope.clickHand = function() {
            console.log($scope);
        }




            //All the error messages and dropdown fields data is coming from here
       $scope.daysessions=[];
            userAuthentication.errMsg().then(function (res) {
                $scope.err = res;
                $scope.requestObject = res.data;
                $scope.intervals = $scope.requestObject.timeIntervals;
                // $scope.intervals=$scope.err.data.timeIntervals[0];
//                $scope.checkInTime = $scope.requestObject.timeIntervals[0];
//                $scope.checkOutTime = $scope.requestObject.timeIntervals[0];
//                $scope.cabObj.pickUpTime = $scope.requestObject.timeIntervals[0];

                $scope.requestObject = res.data;
                $scope.personalDetails = $scope.requestObject.personalDetails;

                $scope.requestDetails = $scope.requestObject.requestDetails;
                $scope.accomodation = $scope.requestDetails.accomodation;
                $scope.taxi = $scope.requestDetails.taxi;
                $scope.flight = $scope.requestDetails.flight;
                $scope.forex = $scope.requestDetails.forex;
                $scope.daysessions=$scope.requestDetails.daySessions;
                 console.log("daysessions:", $scope.daysessions);

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
        	$scope.requestToApprove=[];        	
        	
            $scope.checkList = function(){
            	travelRequest.getAllRecords($scope.approverId).then(function(res){
            
            	console.log("get all records executed");
        		if(res.data.requestListVO.length==0){
        			alert("No requests found");
        			$state.go('home');
        		}
        		else{
        			for(var i = 0; i<res.data.requestListVO.length;i++){
        				if(res.data.requestListVO[i].currentStatus=="L1 Pending" || 
        						res.data.requestListVO[i].currentStatus=="L2 Pending"){
        					
        					$scope.requestToApprove.push(res.data.requestListVO[i]);
        				}
        			}
        			console.log($scope.requestToApprove)
        		}
        	},function(err){
        		if(err.status==400){
        			console.log(err.data)
        			$state.go('login')
        		}
        		console.log("error: ",err);
        	})
            }

        	
 


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
            		if(err.status==400){
            			console.log(err.data)
            			$state.go('login')
            		}
            	});
            }
        	console.log("service variable ",travelRequest.request)
//        	console.log("request to approve Details ",$scope.requestToApproveDetails);
        	
        	

        	$scope.noAccessMsg="";
            $scope.gradeCheckForCab = function(carType){
                 
                   switch($scope.user.grade)
        	
                   {
                    case "M0":
                    case "M1":
                    case "M2":
                    case "M3":
                    case "M4": if(carType!="Economy(Indica,Swift,...)"){
                                        $scope.noAccessMsg="Please take authorisation from Manager";
                            }
                            else{
                                 $scope.noAccessMsg="";
                            }
                            break;
                    case "M5":
                    case "M6": if(carType=="SUV & Luxury(Innova,Corolla Altis,...)"){
                                $scope.noAccessMsg="Please take authorisation from Manager";
                                }
                                else{
                                $scope.noAccessMsg="";
                                }
                                break;
                     }
                 }


             $scope.gradeCheckForFlight = function(prefClass){
                   switch($scope.user.grade)
                   {
                    case "M0":
                    case "M1":
                    case "M2":
                    case "M3":
                    case "M4":
                    case "M5":
                    case "M6": if(prefClass!="Economy"){
                                 $scope.noAccessMsg="Please take authorisation from Manager";
                                                    }
                                                   else{
                                                        $scope.noAccessMsg="";
                                }break;
                   
                   }


            }

           //flight DOB
             $scope.chooseDOB=function(){
//            	$scope.flightObj.dateOfBirth=$filter('date')($scope.flightObj.dateOfBirth, "yyyy-MM-dd");
            	  $scope.DOB=$filter('date')($scope.flightObj.dateOfBirth, "yyyy-MM-dd");
                  console.log($scope.DOB);
                  
                      $scope.age;
                       // $scope.invalidDOB="";

                      var today = new Date();
                      console.log(today);
                      var birthDate = new Date($scope.DOB);
                      console.log(birthDate)
                      
                     $scope.age = today.getFullYear() - birthDate.getFullYear();
                      console.log($scope.age);
                      var m = today.getMonth() - birthDate.getMonth();
                      
                      if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                          $scope.age--;
                      }
                      
                  //document.getElementById("sample").min=new Date();
                  var date=document.getElementById("dob").value;
                  
                  console.log(date,"date");
                  var t=new Date();
                  var today1=formatDate(t);
                  //var tISO=d.toISOString();
                  console.log(today1,"today1");
                  console.log(date);
                  
                  if($scope.DOB > today1){
                         console.log("in if");
                  document.getElementById("dob").value="";
                  //alert("waring!");
                  $scope.invalidDOB="Please select valid date of birth";
                  self.mainForm.$invalid=true;
                  }
                  else if($scope.age<18)
                      {
                         
                          $scope.invalidDOB="Your not eligible for passport";
                          self.mainForm.$invalid=true;
                          console.log($scope.invalidDOB);
                      }
                      else{
                         $scope.invalidDOB="";
                      }
//                $scope.dob=date;
//                $scope.dobErr="";
                 
           }


        	
        	
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
            	if($scope.forexObj.forexCountry=="USA"){
            		//$scope.currency="USD";
            		$scope.amount=60;
            	}
            	$scope.forexObj.forexAmount=(days * $scope.amount)
            	//$scope.forexObj.forexCountry=$scope.forexObj.forexCountry.country
            	console.log("forexAmount=",$scope.forexObj.forexAmount)
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
            
//		Countries validation
            $scope.checkCountry=function(){
            	var origin=$scope.flightObj.originCountry.toUpperCase();
            	var destination=$scope.flightObj.destinationCountry.toUpperCase();
            	if(origin == destination){
            		$scope.originCountryErr="Origin and destination country should be different";
            		$scope.flightObj.originCountry=origin;
            		$scope.flightObj.destinationCountry=destination;
            	}
            	else{
            		$scope.originCountryErr="";
            	}
            	if($scope.flightObj.originCountry=="" || $scope.flightObj.destinationCountry==""){
            		$scope.originCountryErr="";
            	}
            }
            
//            Cities validation
            $scope.checkCity=function(){
            	var originCity=$scope.flightObj.departureLocation.toUpperCase();
            	var destinationCity=$scope.flightObj.destinationLocation.toUpperCase();
            	if(originCity == destinationCity){
            		$scope.originCityErr="From and to city should be different";
            		self.mainForm.flightFromCity.$setValidity('flightFromCity',false);
            		self.mainForm.flightToCity.$setValidity('flightToCity',false);
            		$scope.flightObj.departureLocation=originCity;
            		$scope.flightObj.destinationLocation=destinationCity;
            	}
            	else{
            		if($scope.flightObj.departureLocation==null || $scope.flightObj.destinationLocation==null){
                		$scope.originCityErr="";
                	}
            		$scope.originCityErr="";
            		self.mainForm.flightFromCity.$setValidity('flightFromCity',true);
            		self.mainForm.flightToCity.$setValidity('flightToCity',true);
            	}
            	
            }
            
            $scope.checkAccomodationCity=function(origin,destination){
            	var originCity=origin.toUpperCase();
            	var destinationCity=destination.toUpperCase();
            	if(originCity == destinationCity){
            		$scope.accomodationCityErr="Country and City should be different";
            		self.mainForm.accommodationCountry.$setValidity('accommodationCountry',false);
            		self.mainForm.accommodationCity.$setValidity('accommodationCity',false);
            		$scope.accomodationObj.country=originCity;
            		$scope.accomodationObj.city=destinationCity;
            	}
            	else{
            		if($scope.accomodationObj.country==null || $scope.accomodationObj.city==null){
                		$scope.accomodationCityErr="";
                	}
            		$scope.accomodationCityErr="";
            		self.mainForm.accommodationCountry.$setValidity('accommodationCountry',true);
            		self.mainForm.accommodationCity.$setValidity('accommodationCity',true);
            	}
            	
            }
            
            $scope.checkCabCity=function(origin,destination){
            	var originCity=origin.toUpperCase();
            	var destinationCity=destination.toUpperCase();
            	if(originCity == destinationCity){
            		$scope.cabCityErr="Pickup and drop City should be different";
            		self.mainForm.cabCity.$setValidity('cabCity',false);
            		self.mainForm.cabToCity.$setValidity('cabToCity',false);
            		$scope.cabObj.pickupCity=originCity;
            		$scope.cabObj.dropCity=destinationCity;
            	}
            	else{
            		if(($scope.cabObj.pickupCity==null && $scope.cabObj.dropCity==null) && ($scope.cabObj.pickupCity=="" || $scope.cabObj.dropCity=="") ){
                		$scope.cabCityErr="";
                	}
            		$scope.cabCityErr="";
            		self.mainForm.cabCity.$setValidity('cabCity',true);
            		self.mainForm.cabToCity.$setValidity('cabToCity',true);
            	}
            	
            }
            
            $scope.checkCabLocation=function(origin,destination){
            	var originCity=origin.toUpperCase();
            	var destinationCity=destination.toUpperCase();
            	if(originCity == destinationCity){
            		$scope.cabLocationErr="Pickup and drop location should be different";
            		self.mainForm.cabPickupPoint.$setValidity('cabPickupPoint',false)
            		self.mainForm.cabDropPoint.$setValidity('cabDropPoint',false)
            		$scope.cabObj.pickUplocation=originCity;
            		$scope.cabObj.dropLocation=destinationCity;
            	}
            	else{
            		if($scope.cabObj.pickUpLocation==null && $scope.cabObj.dropLocation==null){
                		$scope.cabLocationErr="";
                		
                	}
            		$scope.cabLocationErr="";
            		self.mainForm.cabPickupPoint.$setValidity('cabPickupPoint',true)
            		self.mainForm.cabDropPoint.$setValidity('cabDropPoint',true)
            	}
            	
            }
            
            
            
            
             $scope.travelRequest= {
             	    requestId: "",
             	    requestedBy: $scope.user.employeeId,
             	    //requestType: $scope.self,
             	    //requestedFor : $scope.empObj.employeeName,
             	    //projectName : $scope.projectName,
             	    currentStatus: "L1 Pending",          	  
             	    remark: "",
             	    actionOnRequest: "",
             	    accomodationRequest : null,
             	    cabRequest : null,
             	    flightRequest : null,
             	    forexRequest : null,
             	    visaRequestVO : null
             	    	}
             
             
             
 // Checking and unchecking of checkbox's decides whether to send null or object.
            $scope.checkedOrNotFlight = function (checkVal) {
                if (checkVal == true) {
                    $scope.travelRequest.flightRequest = $scope.flightObj;
                } else {
                    $scope.travelRequest.flightRequest = null;
                }
            }

            $scope.checkedOrNotAccomodation = function (checkVal) {
                if (checkVal == true) {
                    $scope.travelRequest.accomodationRequest = $scope.accomodationObj;
                } else {
                    $scope.travelRequest.accomodationRequest=null;
                }
            }

            $scope.checkedOrNotForex = function (checkVal) {
            	
                if (checkVal == true) {
                    $scope.travelRequest.forexRequest = $scope.forexObj;
                } else {
                    $scope.travelRequest.forexRequest = null;
                }
            }

            $scope.checkedOrNotCab = function (checkVal) {
            	
                if (checkVal == true) {
                    $scope.travelRequest.cabRequest = $scope.cabObj;
                } else {
                    $scope.travelRequest.cabRequest = null;
                }
            }
            
			$scope.checkedOrNotVisa = function (checkVal) {
			     if (checkVal == true) {
			         $scope.travelRequest.visaRequestVO = $scope.visaObj;
			     } else {
			         $scope.travelRequest.visaRequestVO = null;
			     }
			}
            
            /* $scope.forexObj.forexAmount="100";
             //advance amount
             $scope.forexObj.forexToDate="2018-01-01 12:00:00";*/
             $scope.send = function () {
            	$scope.travelRequest.approverId= $scope.empObj.approverName.approverId;
            	$scope.travelRequest.requestedFor = $scope.empObj.employeeName;
            	$scope.travelRequest.projectName = $scope.empObj.projectName;
            	$scope.travelRequest.projectCode = $scope.empObj.projectCode;
            	if($scope.forexCheckBox){
            		var forCountry=$scope.forexObj.forexCountry.country;
            		$scope.forexObj.forexCountry=forCountry;
            	}
            	if($scope.user.isApprover==true){
            		$scope.travelRequest.currentStatus = "L2 Pending";
            	}
            	//$scope.travelRequest.cabRequest.pickUpTime = $scope.cabObj.pickUpTime;
            	console.log($scope.q,'q',$scope.empObj.projectName,'w')
            	console.log("Approver ID : " + $scope.travelRequest.approverId);
            	console.log("Approver EMPID : " + $scope.empObj.approverName.approverId);
            	console.log("data--",$scope.travelRequest);
            	travelRequest.createRecord($scope.travelRequest)
                    .then(function (data) {
                        console.log(data);
                        /*$scope.showAlert = function(ev) {
        			        // Appending dialog to document.body to cover sidenav in docs app
        			        // Modal dialogs should fully cover application
        			        // to prevent interaction outside of dialog
        			        $mdDialog.show(
        			          $mdDialog.alert()
        			            .parent(angular.element(document.querySelector('#popupContainer')))
        			            .clickOutsideToClose(false)
        			            .title('Submitted!!')
        			            .textContent('Your request has been submitted successfully')
        			            .ariaLabel('Alert Dialog Demo')
        			            .ok('Ok')
        			            .targetEvent(ev)
        			            
        			        );
        			      };*/
                        /*$(document).ready(function(){
                            $("#btn").click(function(){
                                $("#a").modal();
                            });
                        });*/
                        alert("Request submitted successfully");
                        $state.go('home');
                    },function(err){
                    	console.log("error ",err)
                    	alert("Request not submitted, Please enter all the fields in request details.");
                    	if(err.status==400){
                			console.log(err.data)
                			$state.go('login')
                		}
                    });
            }
             	
             
             
             $scope.save = function () {
             	$scope.travelRequest.approverId= $scope.empObj.approverName.approverId;
             	$scope.travelRequest.requestedFor = $scope.empObj.employeeName;
             	$scope.travelRequest.currentStatus = "Pending";
             	$scope.travelRequest.projectName = $scope.empObj.projectName;
             	$scope.travelRequest.projectCode = $scope.empObj.projectCode;
             	//$scope.travelRequest.cabRequest.pickUpTime = $scope.cabObj.pickUpTime;
             	if($scope.forexCheckBox){
            		$scope.forexObj.forexCountry=$scope.forexObj.forexCountry.country;
            	}
             	console.log($scope.q,'q',$scope.empObj.projectName,'w')
             	console.log("Approver ID : " + $scope.travelRequest.approverId);
             	console.log("Approver EMPID : " + $scope.empObj.approverName.approverId);
             	console.log("data--",$scope.travelRequest);
             	travelRequest.createRecord($scope.travelRequest)
                     .then(function (data) {
                         console.log(data);
                         alert("Request Saved as draft ",data.requestId);
                         $state.go('home');
                     },function(err){
                     	console.log("error ",err)
                     	alert("Request not saved, Please enter all the fields in request details.");
                     	if(err.status==400){
                			console.log(err.data)
                			$state.go('login')
                		}
                     });
             }
             
             
             $scope.back = function(){
            	 $state.go('home');
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
            //javascript bootstrap tooltip
            $(document).ready(function(){
                $('[data-toggle="tooltip"]').tooltip();   
            });
            
            $scope.travelRequest.requestType="Self";
            $scope.requestType=[{type:"Self"},{type:"Other"}]
	        $scope.getrequestType=function(){
		 return $scope.requestType;
            }
	 $scope.cell={
			 evaluator:"Self"
	 	}
	
            $scope.empName="Employee Name:";
            $scope.empObj.employeeName=$scope.user.employeeName;
        	$scope.empObj.employeeMobileNumber=$scope.user.employeeMobileNumber;
         	
         	
            $scope.setPersonalData=function(){
            	 if($scope.cell.evaluator=="Self"){
         			$scope.travelRequest.requestType="Self";
         		}		
         		else{
         			$scope.travelRequest.requestType="Other";
         		}
            	if($scope.cell.evaluator=="Self"){
            	$scope.empObj.employeeName=$scope.user.employeeName;
            	$scope.empObj.employeeMobileNumber=$scope.user.employeeMobileNumber;
            	
             	$scope.empName="Employee Name:";
             	$scope.empObj.employeeLocation=null;
             	$scope.empObj.approverName=null;
             	$scope.getProjectName();
             	console.log($scope.empName);
            }else if($scope.cell.evaluator=="Other"){
            	$scope.empObj.employeeName="";
            	$scope.empObj.employeeMobileNumber="";
            	$scope.empObj.employeeLocation=null;
            	$scope.empObj.projectCode="";
            	$scope.empObj.projectName="";
            	$scope.empObj.approverName=null;
            	$scope.empName="Requested For:";
            	console.log($scope.empName);
            }
            }
            
            $scope.GetDays=function(){
                var dropdt = new Date($scope.forexObj.forexFromDate);
                var pickdt = new Date($scope.forexObj.forexToDate);
                var noOfDays = ((pickdt- dropdt) / (24 * 3600 * 1000));
                noOfDays = noOfDays+1;//including day of drop
                noOfDays = noOfDays+"";
                return noOfDays;
        }

        $scope.cal=function(){
        	$scope.perdiem=0;
              //$scope.forexObj.forexFromDate=new Date($scope.forexObj.forexFromDate);
              console.log($scope.from);
             console.log($scope.forexObj.forexToDate,"pppppp");
//             $scope.ss=$scope.forexObj.forexToDate;
//             console.log($scope.ss,"pppppp");
             //$scope.forexObj.forexToDate=new Date($scope.forexObj.forexToDate);
            //$scope.from=$filter('date')($scope.forexObj.forexFromDate, "yyyy-MM-dd");
            //$scope.forexObj.forexFromDate=$scope.from;
            console.log($scope.from);
            //$scope.toDate=$filter('date')($scope.forexObj.forexToDate, "yyyy-MM-dd"); 
            //$scope.forexObj.forexToDate=$scope.toDate;
            console.log($scope.toDate);
        if($scope.forexObj.forexToDate){
             $scope.forexObj.forexNoOfDays=$scope.GetDays();        
        } 
        for(var i = 0; i<$scope.projectList.perDeimList.length; i++){
       	 if($scope.projectList.perDeimList[i].country==$scope.forexObj.forexCountry.country){
    			
    			$scope.perdiem=$scope.projectList.perDeimList[i].perdiem;
    			$scope.currency=$scope.projectList.perDeimList[i].currency;
    		}
       	
        }
        $scope.amount=$scope.forexObj.forexNoOfDays * $scope.perdiem ;
        $scope.forexObj.forexAmount=$scope.amount;
        
        console.log("forex perdiem ",$scope.forexObj.forexAmount)
    }

            
        $scope.myFormName = "personal";
        $scope.form1Name = "form1";
//        	$scope.classValue1 = "active";
        $scope.firstTabClass = true;
        $scope.secondTabClass = false;
        $scope.thirdTabClass = false;
       
        
        var self = this;
        self.personal = "personal";
        self.accommodationForm = "accommodationForm";
        self.visaForm = "visaForm";
        self.mainForm = "mainForm";
        $scope.returnSessions=$scope.daysessions;
        $scope.changeSession=function(session){
    		$scope.retString=$scope.flightObj.returnDate.toDateString();
    		$scope.depString=$scope.flightObj.departureDate.toDateString();
    		if($scope.depString==$scope.retString /*&& $scope.flightObj.returnTime==session*/){
    			if(session!='Night'){
    				if($scope.flightObj.departureTime=='Afternoon'){
    					$scope.returnSessions=['Evening','Night'];
    				}
    				else if(session=='Evening'){
    					$scope.returnSessions=['Night'];
    				}
    				else if(session=='Morning'){
        				$scope.returnSessions=['Afternoon','Evening','Night'];
        			}
    			/*self.mainForm.flightReturnTime.$setValidity("flightReturnTime",false);
    			$scope.returnSameErr="Departure time and return time cannot be same.";*/
    			}
    			else {
    				$scope.tomorrow = new Date();
					console.log($scope.flightObj.departureDate,"$scope.flightObj.departureDate")
				    $scope.tomorrow.setDate($scope.flightObj.departureDate.getDate() + 1);
					
					console.log( $scope.flightObj.departureDate,"$scope.flightObj.departureDate");
					console.log( $scope.tomorrow," $scope.tomorrow");
					
					$scope.tomorrow.setDate($scope.flightObj.departureDate.getDate() + 1);
				    $scope.tomorrow=$filter('date')($scope.tomorrow, "yyyy-MM-dd");;
				    $scope.nextDay=new Date($scope.tomorrow);
				    $scope.flightObj.returnDate= $scope.nextDay;
				    $scope.returnSessions=['Morning','Afternoon','Evening','Night'];
    			}
            }
    		else if($scope.depString==$scope.retString){
    			self.mainForm.flightReturnTime.$setValidity("flightReturnTime",true);
    			$scope.returnSameErr="";
    		}
    		else{
    			$scope.returnSessions=['Morning','Afternoon','Evening','Night'];
    		}
   	
    }
        
        $scope.TabClick = function(formName){
//        	console.log(self);
//        	console.log(self.personal.$valid);
//        	console.log(self.accommodationForm.$valid);
//        	console.log($scope.myFormName,"myformname");
        if(formName!=undefined){
        if(self.personal.$valid){
        	console.log(self.personal.$valid,'personal is valid');
        $scope.classValue2 = "active";
        $scope.classValue1 = "";
        $scope.classValue3 = "";
        $scope.secondTabClass = true;
        $scope.firstTabClass = false;
        $scope.thirdTabClass = false;
        }
        if(self.personal.$valid && formName == "mainForm" && self.mainForm.$valid == true){
        $scope.classValue3 = "active";
        $scope.classValue1 = "";
        $scope.classValue2 = "";
        $scope.secondTabClass = false;
        $scope.firstTabClass = false;
        $scope.thirdTabClass = true;
        }
        }
        else{
        	$scope.classValue2 = "";
        	$scope.classValue1 = "active";
        	$scope.classValue3 = "";
        $scope.secondTabClass = false;
        $scope.firstTabClass = true;
        $scope.thirdTabClass = false;
        }

        }
       
        $scope.flag1 = false;
        $scope.accommodationCheckBox=false;
        $scope.flag2 = false;
        $scope.taxiCheckBox = false;
        $scope.flag3 = false;
        $scope.flightCheckBox = false;
        $scope.flag4 = false;
        $scope.forexCheckBox = false;
        $scope.flag5 = false;
        $scope.visaCheckBox = false;
        console.log($scope.flag1,$scope.flag2,$scope.flag3,$scope.flag4,$scope.flag5,'0');
        /**/
        $scope.confirmDisable = function(){
        	if((self.personal.$valid || self.mainForm.$invalid) && $scope.accommodationCheckBox == false && $scope.taxiCheckBox == false && $scope.flightCheckBox == false && $scope.forexCheckBox == false && $scope.visaCheckBox == false && ($scope.flightObj.travelType != "one way" || $scope.flightObj.travelType != "round way" && $scope.flightObj.tourType != "domestic" || $scope.flightObj.tourType !="international")){ 
        		console.log("false");
        		$scope.classValue3 = "";
                $scope.classValue1 = "";
                $scope.classValue2 = "active";
                $scope.secondTabClass = true;
                $scope.firstTabClass = false;
                $scope.thirdTabClass = false;
        	}
        } 
        $scope.accomodationValid = function(val){
        	//console.log(self.visaForm,'self 1');
        	if(val == true){
        	//console.log(self.visaForm," self 2")
        	if(self.visaForm.$valid == true){
        	$scope.flag1 = true;
        	$scope.flag2 = true;
        	$scope.flag3 = true;
        	$scope.flag4 = true;
        	$scope.flag5 = true;
        	console.log($scope.flag1,$scope.flag2,$scope.flag3,$scope.flag4,$scope.flag5,'1');
        	}
        	//console.log("valid form");

        	else if(val == false){
        	$scope.flag1 = true;
        	$scope.flag2 = true;
        	$scope.flag3 = true;
        	$scope.flag4 = true;
        	$scope.flag5 = true;
        	console.log($scope.flag1,$scope.flag2,$scope.flag3,$scope.flag4,$scope.flag5,'2');
        	}
        	else{
        	console.log($scope.flag1,$scope.flag2,$scope.flag3,$scope.flag4,$scope.flag5,'3');
        	}
        	}
        	}
        
        $scope.masterFlag;
        $scope.onClickingConfirm = function(){
        if(($scope.flag1 == true)&& ($scope.flag2 == true) && ($scope.flag3 == true) && ($scope.flag3 == true) && ($scope.flag5 == true)){
        $scope.masterFlag = true;
        $scope.secondTabClass = false;
        $scope.firstTabClass = false;
        $scope.thirdTabClass = true;
        }
        else{
        $scope.secondTabClass = true;
        $scope.firstTabClass = false;
        $scope.thirdTabClass = false;
        }
        }

        $scope.function1 = function(formName1){
        console.log('key is pressed');
        console.log(formName1.$valid);
        if(formName1.$valid == true){
        $scope.flag1 = true;
        console.log($scope.flag1,'flag1');
        }
        }
        
//    	$scope.function2 = function(formName){
//    	console.log('key is pressed');
//    	console.log(formName.$valid);
//    	if(formName.$valid == true){
//    	$scope.flag1 = true;
//    	console.log($scope.flag1,'flag1');
//    	}
//    	}

//    	$scope.function3 = function(formName){
//    	console.log('key is pressed');
//    	console.log(formName.$valid);
//    	if(formName.$valid == true){
//    	$scope.flag1 = true;
//    	console.log($scope.flag1,'flag1');
//    	}
//    	}

    $scope.function4 = function(formName){
    console.log('key is pressed');
    console.log(formName.$valid);
    if(formName.$valid == true){
    $scope.flag1 = true;
    console.log($scope.flag1,'flag1');
    }
    }

    $scope.function5 = function(formName){
    console.log('key is pressed');
    console.log(formName.$valid);
    if(formName.$valid == true){
    $scope.flag1 = true;
    console.log($scope.flag1,'flag1');
    }
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

    $scope.discard=function(checkBoxName){
    	//alert($scope.accommodationCheckBox);
    	if(checkBoxName=="accomodation"){
	    	if(!$scope.accommodationCheckBox){
	    		var x=confirm("You are about to discard accomodation form");
	    		if(!x){
	    			$scope.accommodationCheckBox=true;
	    			$scope.checkedOrNotAccomodation($scope.accommodationCheckBox);
	    		}
	    	}
    	}
    	
    	if(checkBoxName=="taxi"){
	    	if(!$scope.taxiCheckBox){
	    		var x=confirm("You are about to discard taxi form");
	    		$scope.cabObj=null;
	    		if(!x){
	    			$scope.taxiCheckBox=true;
	    			$scope.checkedOrNotCab($scope.taxiCheckBox);
	    		}
	    	}
    	}
    	
    	if(checkBoxName=="flight"){
	    	if(!$scope.flightCheckBox){
	    		var x=confirm("You are about to discard flight form");
	    		if(!x){
	    			$scope.flightCheckBox=true;
	    			$scope.checkedOrNotFlight($scope.flightCheckBox);
	    		}
	    	}
    	}
    	if(checkBoxName=="forex"){
	    	if(!$scope.forexCheckBox){
	    		var x=confirm("You are about to discard forex form");
	    		if(!x){
	    			$scope.forexCheckBox=true;
	    			$scope.checkedOrNotForex($scope.forexCheckBox);
	    		}
	    	}
    	}
    	if(checkBoxName=="visa"){
	    	if(!$scope.visaCheckBox){
	    		var x=confirm("You are about to discard visa form");
	    		if(!x){
	    			$scope.visaCheckBox = true;	
	    			$scope.checkedOrNotVisa($scope.visaCheckBox);
	    		}
	    	}
    	}
    	
    	
    }
         
      $scope.checkCarRequestType=function(){
    	  if($scope.cabObj.pickupCity!=undefined && $scope.cabObj.pickupCity!=""){
    	  if($scope.cabObj.taxiTravelType=='Airport Pickup'){
    		  $scope.cabObj.pickUpLocation=$scope.cabObj.pickupCity+" Airport";
    		  
    	  }
    	  
    	  if($scope.cabObj.taxiTravelType=='Airport Drop'){
    		  $scope.cabObj.dropLocation=$scope.cabObj.pickupCity+" Airport";
    		  
    	  }
    	  }
      }  
      
      $scope.bindDateAndTime=function(){
    	  if($scope.accommodationCheckBox == true){
    		  		console.log("Helllllooooooooooo");
    		  		console.log($scope.accomodationObj.checkInTime,"$scope.accomodationObj.checkInTime");
    		  		console.log($scope.accomodationObj.checkOutTime,"$scope.accomodationObj.checkOutTime");
    		  		
    		  		console.log($scope.accomodationObj.checkInDate,"$scope.accomodationObj.checkIn");
    		  		console.log($scope.accomodationObj.checkOutDate,"$scope.accomodationObj.checkOut");
    		  		
    		  		$scope.inDate=$filter('date')($scope.accomodationObj.checkInDate, "yyyy-MM-dd"); 
    		  		$scope.outDate=$filter('date')($scope.accomodationObj.checkOutDate, "yyyy-MM-dd"); 
    		  		
    		  		var fullTime=timeConversionTo24Hrs($scope.accomodationObj.checkInTime);
    		  		$scope.accomodationObj.checkIn=appendDateTime($scope.inDate, fullTime);
					//alert($scope.accomodationObj.checkIn);
				
					var fullTime=timeConversionTo24Hrs($scope.accomodationObj.checkOutTime);
					$scope.accomodationObj.checkOut=appendDateTime($scope.outDate, fullTime);
					
					console.log($scope.accomodationObj.checkIn,"$scope.accomodationObj.checkIn");
					console.log($scope.accomodationObj.checkOut,"$scope.accomodationObj.checkOut");
					
					//alert($scope.accomodationObj.checkOut);
    	  }
    	}
      
      
      
    } )