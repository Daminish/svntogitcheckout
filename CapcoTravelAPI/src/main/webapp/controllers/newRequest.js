angular.module("myApp")
    .controller("newRequestCtrl", function ($scope, $location,$state, userAuthentication, travelRequest, jsonTransfer) {
    	$scope.user={};
    	$scope.user=jsonTransfer.getInfo();
    	console.log("get Info newrequestctrl ",$scope.user)

            $scope.accomodationAccordion = "panel-collapse collapse";
            $scope.taxiAccordion = "panel-collapse collapse";
            $scope.flightAccordion = "panel-collapse collapse";
            $scope.forexAccordion = "panel-collapse collapse";
            $scope.accomodationHeading = "panel-heading";
            $scope.activeForm = []

            //we dont need this because now we are using ui-routing.
            //=======================

            // $scope.showPersonalDetails = function () {
            //     $location.path('home/newrequest/personalDetails');
            // }
            // $scope.showRequestDetails = function () {
            //     $location.path('home/newrequest/requestDetails');
            // }
            // $scope.showConfirmPage = function () {
            //     $location.path('home/newrequest/confirm');
            // }


            //Datepicker

            var today = new Date();
            $scope.checkInDate = today;
            $scope.checkOutDate = new Date();
            $scope.dateFormat = 'yyyy-MM-dd';
            $scope.checkInDateOptions = {
                formatYear: 'yy',
                startingDay: 1,
                minDate: today,
                maxDate: new Date(2030, 5, 22)
            };
            $scope.checkOutDateOptions = {
                formatYear: 'yy',
                startingDay: 1,
                minDate: today,
                maxDate: new Date(2030, 5, 22)
            };
            $scope.checkInDatePopup = {
                opened: false
            };
            $scope.checkOutDatePopup = {
                opened: false
            };
            $scope.ChangecheckOutMinDate = function (checkInDate) {
                if (checkInDate != null) {
                    var checkOutMinDate = new Date(checkInDate);
                    $scope.checkOutDateOptions.minDate = checkOutMinDate;
                    $scope.chekOutDate = checkOutMinDate;
                }
            };
            $scope.ChangecheckOutMinDate();
            $scope.OpencheckInDate = function () {
                $scope.checkInDatePopup.opened = !$scope.checkInDatePopup.opened;
            };
            $scope.OpencheckOutDate = function () {
                $scope.checkOutDatePopup.opened = !$scope.checkOutDatePopup.opened;
            };


        $scope.clickHand = function() {
            console.log($scope);
        }



            userAuthentication.dummy().then(function (res) {
//                console.log(res.data[0]);
                $scope.z = res;

            })

            //All the error messages and dropdown fields data is comming from here
            userAuthentication.errMsg().then(function (res) {
                // console.log("hi",res);
                $scope.err = res;
                $scope.requestObject = res.data;
                $scope.intervals = $scope.requestObject.timeIntervals;
                // $scope.intervals=$scope.err.data.timeIntervals[0];
                $scope.checkInTime = $scope.requestObject.timeIntervals[0];
                $scope.checkOutTime = $scope.requestObject.timeIntervals[0];
                $scope.pickupTime = $scope.requestObject.timeIntervals[0];

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
                // alert(travelType+"-- "+accordionName);
                //acc = accordionName;
                // if(travelType=="panel-collapse collapse "){
                // alert(acc+"--if ");
                // $scope.acc="panel-collapse collapse in ";
                // alert()
                // }else{
                // alert($scope.accordionName+"--else ");
                // $scope.acc="panel-collapse collapse ";
                // }

                if (accordionName == "accomodationAccordion") {

                    if ($scope.accomodationAccordion == "panel-collapse collapse") {
                        $scope.accomodationAccordion = "panel-collapse collapse in";
                    } else {
                        $scope.accomodationAccordion = "panel-collapse collapse";
                    }
                }

                if (accordionName == "taxiAccordion") {

                    if ($scope.taxiAccordion == "panel-collapse collapse") {
                        $scope.taxiAccordion = "panel-collapse collapse in";
                    } else {
                        $scope.taxiAccordion = "panel-collapse collapse";
                    }
                }

                if (accordionName == "flightAccordion") {

                    if ($scope.flightAccordion == "panel-collapse collapse") {
                        $scope.flightAccordion = "panel-collapse collapse in";
                    } else {
                        $scope.flightAccordion = "panel-collapse collapse";
                    }
                }

                if (accordionName == "forexAccordion") {

                    if ($scope.forexAccordion == "panel-collapse collapse") {
                        $scope.forexAccordion = "panel-collapse collapse in";
                    } else {
                        $scope.forexAccordion = "panel-collapse collapse";
                    }
                }

            }

// Get all records for the approver to take action
            $scope.approverId=16292;
        	$scope.requestToApprove;
            travelRequest.getAllRecords($scope.approverId).then(function(res){
        		$scope.requestToApprove=res.data;
//        		console.log($scope.requestToApprove);
        	},function(err){
        		console.log("error: ",err);
        	})

        	
 // get all requests of user
        	$scope.userRequest;
        	travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
        		$scope.userRequest=[];
        		for(var i = 0; i<res.data.length;i++){
        			if(res.data[i].currentStatus!="cancelled"){
        				$scope.userRequest.push(res.data[i]);
        			}
        		}
        		//console.log($scope.userRequest)
        	})

// Get Request details on click of specific request in request list
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
<<<<<<< .mine


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
||||||| .r198
=======


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
>>>>>>> .r216
        	
        	
// deleting request by request Id
        	$scope.deleteRequest=function(requestId){
        		travelRequest.deleteRecord(requestId).then(function(){
        			console.log("request deleted ",requestId)
        		})
        		
        	}


            $scope.accomodationCheckInDate;
            $scope.accomodationCheckOutDate;
            $scope.accomodationCheckInTime;
            $scope.accomodationCheckOutTime;
            $scope.forexToDate;
            $scope.addDays = function (date, noOfDays) {
            	alert("add days")
                var dat = new Date(date);
                dat.setDate(dat.getDate() + noOfDays);
                $scope.forexToDate = dat;

            }

            //   $scope.clickhi = function(){
            //     $scope.empObj={
            //         employeeId: 15977,
            //         // employeeName: $scope.employeeName,
            //         emailId: "saraswati.khade@capco.com",
            //         employeeLocation: $scope.employeeLocation,
            //        //
            //         projectName:$scope.projectName,
            //         employeeMobileNumber: $scope.employeeMobileNumber,
            //         isApprover: "false",
            //     }
            //     console.log($scope.empObj);
            // }

            // $scope.aaa = {};
            // $scope.aaa.prop= "www";
            // $scope.employeeName="akshay";
            //    $scope.employeeName;
            //     $scope.empObj={
            //         employeeId: 15977,
            //         employeeName: $scope.employeeName,
            //         emailId: "saraswati.khade@capco.com",
            //         employeeLocation: $scope.employeeLocation,
            //         projectCode: $scope.projectCode,
            //         projectName:$scope.projectName,
            //         employeeMobileNumber: $scope.employeeMobileNumber,
            //         isApprover: "false",
            //     }
            // $scope.abcd = $scope.empObj.employeeName;
            // console.log($scope.empObj);

            $scope.empObj = {}

            $scope.flightObj = {}
            $scope.flightNull = null;

            $scope.cabObj = {}
            $scope.cabNull = null;

            $scope.accomodationObj = {}
            $scope.accomodationNull = null;

            $scope.forexObj = {}
            $scope.forexNull = null;
            

            $scope.showConfirmModal = false;
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
//            $scope.travelRequest.empData = $scope.empObj;
//            $scope.travelRequest.cabRequest = $scope.cabObj;
//            $scope.travelRequest.flightRequest = $scope.flightObj;
//            $scope.travelRequest.accomodationRequest = $scope.accomodationObj;
//            $scope.travelRequest.forexRequest = $scope.forexObj;
//            $scope.cabObj.fromDate="2017-11-20 04:30:00"
//            $scope.cabObj.toDate="2017-11-20 04:30:00"
        
            	  $scope.travelRequest= {
            	    requestId: "",
            	    requestedBy: 16294,
            	    requestedFor:"Self",
            	    approverId: 16292,
            	    currentStatus: "Pending",
            	    remark: "new Request",
            	    actionOnRequest: "",
            	    empData: {
            	      employeeId: 16290,
            	      employeeName: "Saraswati Khade",
            	      emailId: "saraswati.khade@capco.com",
            	      employeeLocation: "Pune",
            	      projectCode: "8888",
            	      employeeMobileNumber: 9970174436,
            	      isApprover: "false"
            	    },
            	    flightRequest: {
            	      tourType: 1,
            	      travelType: 1,
            	      mealPref: "veg",
            	      passportNumber: "H2855908",
            	      businessPurpose: "Training",
            	      prefClass: "Business",
            	      seatPref: "Window",
            	      nationality: "Indian",
            	      passportIssueDate: "2011-01-22 00:00:00",
            	      passportExpiryDate: "2021-01-21 00:00:00",
            	      issuingAuthority: "Passport Officer",
            	      passportIssuePlace: "Pune",
            	      isValidVisa: 1,
            	      returnDateTime: "2017-11-22",
            	      remark: "Training in Bangalore",
            	      departureLocation: "Pune",
            	      destinationLocation: "Mumbai",
            	      departureDate: "2017-11-22 00:00:00",
            	      departureTime: "11:00",
            	      surname: "Khade",
            	      givenName: "Saraswati Khade",
            	      dateOfBirth: "2017-11-22 00:00:00"
            	    },
            	    cabRequest: {
            	      taxiTravelType: "outstation",
            	      fullDay: "true",
            	      numberOfPersons: 1,
            	      city: "New york",
            	      country: "USA",
            	      pickUpLocation: "Pune",
            	      dropLocation: "Bangalore",
            	      pickUpTime: "11:00",
            	      fromDate: "2017-11-03 00:00:00",
            	      toDate: "2017-11-22 00:00:00",
            	      carType: "sedan",
            	      remarks: "cab Request"
            	    },
            	    accomodationRequest: {
            	      checkIn: "2017-10-01 14:22:28",
            	      checkOut: "2017-10-30 14:22:28",
            	      country: "USA",
            	      city: "Irving",
            	      currency: "USD",
            	      budget: "2000",
            	      category: "Hotel",
            	      roomType: "Single",
            	      accomodationType: "External",
            	      remarks: "Accomodation request"
            	    },
            	    forexRequest: {
            	      forexToDate: "2017-10-31 14:22:28",
            	      forexFromDate: "2017-09-30 14:22:28",
            	      forexCurrency: "USD",
            	      forexCountry: "USA",
            	      forexNoOfDays: 31,
            	      forexAmount: 100,
            	      forexRemarks: "forex Request",
            	      forexCollectionCenter: "Pune",
            	      forexBankDesk: "Pune"
            	    }
            	  }

            	  
            	  
            	  $scope.checkedOrNotFlight = function (checkVal) {
                      if (checkVal == true) {
                          $scope.travelRequest.flightRequest = $scope.flightObj;
                      } else {
                          $scope.travelRequest.flightRequest = $scope.flightNull;
                      }
                      console.log($scope.travelRequest);
                  }

                  $scope.checkedOrNotAccomodation = function (checkVal) {
                      if (checkVal == true) {
                          $scope.travelRequest.accomodationRequest = $scope.accomodationObj;
                      } else {
                          $scope.travelRequest.accomodationRequest = $scope.accomodationNull;
                      }
                  }

                  $scope.checkedOrNotForex = function (checkVal) {
                      if (checkVal == true) {
                          $scope.travelRequest.forexRequest = $scope.forexObj;
                      } else {
                          $scope.travelRequest.forexRequest = $scope.forexNull;
                      }
                  }

                  $scope.checkedOrNotCab = function (checkVal) {
                      if (checkVal == true) {
                          $scope.travelRequest.cabRequest = $scope.cabObj;
                      } else {
                          $scope.travelRequest.cabRequest = $scope.cabNull;
                      }
                  }
                  
                  
            $scope.send = function () {
                travelRequest.createRecord($scope.travelRequest)
                    .then(function (data) {
                        console.log(data);
                    },function(err){
                    	console.log("error ",err)
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

            //javascript bootstrap tooltip
            $(document).ready(function(){
                $('[data-toggle="tooltip"]').tooltip();   
            });
            
         
                       
    } )
