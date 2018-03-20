angular
		.module("myApp")
		.controller(
				"editRequestCtrl",
				function($scope, $location, $state, userAuthentication,
						travelRequest, $filter, editRequestJsonTransfer,
						jsonTransfer) {
					$scope.empObj = {};
					$scope.accomodationObj = {};
					$scope.cabObj = {};
					$scope.cabObj.country = "INDIA";
					$scope.flightObj = {};
					$scope.forexObj = {};
					$scope.visaObj = {};
					$scope.classValue1 = "active";
					$scope.accomodationCheckInDate;
					$scope.accomodationCheckOutDate;
					$scope.accomodationCheckInTime;
					$scope.accomodationCheckOutTime;
					$scope.forexObj.forexNoOfDays;
					//approverList
					$scope.user = {};
					$scope.projectList = {};
					$scope.user = jsonTransfer.getInfo();
					$scope.editRequestId;
					$scope.errMsg = "errMsg";

					$scope.travelRequest = {
						requestId : "",
						requestedBy : $scope.user.employeeId,
						//requestType: $scope.self,
						//requestedFor : $scope.empObj.employeeName,
						//projectName : $scope.projectName,
						currentStatus : "L1 Pending",
						remark : "",
						actionOnRequest : "Edit",
						accomodationRequest : null,
						cabRequest : null,
						flightRequest : null,
						forexRequest : null,
						visaRequestVO : null
					}

					if ($scope.user.isApprover) {
						userAuthentication.level = 2
					} else {
						userAuthentication.level = 1
					}

					travelRequest
							.getApproverAndProjectList()
							.then(
									function(res) {
										$scope.projectList = res.data;
										travelRequest.approverAndProjectList = $scope.projectList;
										console.log("projectList: ",
												$scope.projectList)
									}, function(err) {
										console.log(err)
									})

					//All the error messages and dropdown fields data is comming from here
					userAuthentication
							.errMsg()
							.then(
									function(res) {
										$scope.err = res;
										$scope.requestObject = res.data;
										$scope.intervals = $scope.requestObject.timeIntervals;
										$scope.personalDetails = $scope.requestObject.personalDetails;
										$scope.requestDetails = $scope.requestObject.requestDetails;
										$scope.accomodation = $scope.requestDetails.accomodation;
										$scope.taxi = $scope.requestDetails.taxi;
										$scope.flight = $scope.requestDetails.flight;
										$scope.forex = $scope.requestDetails.forex;
										$scope.visa = $scope.requestDetails.visa;

										// console.log("err:", $scope.forex);

										// $scope.city = res.data.requestDetails.accomodation.city;
										// console.log($scope.city)

									});

					//Null or send object

					$scope.collapseAccordion = function(accordionName) {

						if (accordionName == "accomodationAccordion") {

							if ($scope.accomodationAccordion == "panel-collapse collapse") {
								$scope.accomodationAccordion = "panel-collapse collapse in";
								$scope.accomodationStatus = true;
							} else {
								$scope.accomodationAccordion = "panel-collapse collapse";
								$scope.accomodationStatus = false;
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

					//to convert 12 hrs to 24 hrs
					var timeConversionTo24Hrs = function(timeIn12) {
						// var timeIn12 = "11:30 pm"
						console.log(timeIn12, "tIn12");
						var arr = timeIn12.split('');
						var hours = arr[0] + arr[1];
						var minutes = arr[3] + arr[4];
						// var seconds = arr[6]+arr[7];
						var ampm = arr[6];
						hours = +hours;
						if (hours < 13) {
							if (ampm == "p") {
								hours += 12;
							}
						}
						var timeIn24 = hours + ":" + minutes + ":00";
						console.log(timeIn24, "in 24");
						return timeIn24;
					}

					// to convert 24 to 12 hrs
					var timeConversionTo12Hrs = function(timeIn24) {
						// var timeIn24 = "17:30:10";
						console.log(timeIn24, " in 24 hours format");
						var ampm = "am";
						var time = timeIn24.split(':');
						var hours = parseInt(time[0]);
						var minutes = time[1];
						var seconds = time[2];

						if (hours > 12) {
							hours = hours - 12;
							ampm = "pm";
						}
						//to append 0 for time less than 12 noon
						if (hours < 12) {
							hours = "0" + hours;
						}
						var timeIn12 = hours + ":" + minutes + " " + ampm;
						console.log(timeIn12, "in 12 hours format");
						return timeIn12;
					}

					//alert(editRequestJsonTransfer.getEditRequestInfo());
					$scope.travelRequest = editRequestJsonTransfer
							.getEditRequestInfo();
					console.log("after transfer edit", $scope.travelRequest);
					//emp object
					$scope.empObj.employeeName = $scope.travelRequest.requestedFor;
					$scope.empObj.employeeMobileNumber = $scope.travelRequest.empData[0].employeeMobileNumber//$scope.travelRequest.
					$scope.empObj.employeeLocation = $scope.travelRequest.empData[0].employeeLocation;
					$scope.empObj.projectName = $scope.travelRequest.projectName;
					$scope.empObj.projectCode = $scope.travelRequest.projectCode;
					$scope.empObj.approverName = $scope.travelRequest.empData[1].employeeName;
					$scope.editRequestId = $scope.travelRequest.requestId;
					// alert($scope.empObj.x);
					//accomodation obj
					if ($scope.travelRequest.accomodationRequest != null) {
						$scope.accomodationObj = $scope.travelRequest.accomodationRequest;
						$scope.accomodationObj.checkInDate = new Date(
								$scope.travelRequest.accomodationRequest.checkInDate);
						$scope.accomodationObj.checkOutDate = new Date(
								$scope.travelRequest.accomodationRequest.checkOutDate);
						$scope.accommodationCheckBox = true;
						$scope.checkInTime = timeConversionTo12Hrs($scope.travelRequest.accomodationRequest.checkInTime);
						$scope.checkOutTime = timeConversionTo12Hrs($scope.travelRequest.accomodationRequest.checkOutTime);
						//console.log("checkin time",  $scope.checkInTime);
					}
					//cabObj
					if ($scope.travelRequest.cabRequest != null) {
						$scope.cabObj = $scope.travelRequest.cabRequest;
						$scope.cabObj.taxiTravelType = $scope.travelRequest.cabRequest.taxiTravelType;
						//x$scope.cabObj.fromDate=$scope.travelRequest.cabRequest.
						console.log("cabObj", $scope.cabObj);
						//alert($scope.travelRequest.cabRequest.fromDate);
						//alert(new Date($scope.travelRequest.cabRequest.fromDate));
						$scope.travelRequest.cabRequest.fromDate = new Date(
								$scope.travelRequest.cabRequest.fromDate);
						$scope.travelRequest.cabRequest.toDate = new Date(
								$scope.travelRequest.cabRequest.toDate);
						$scope.cabObj.fromDate = new Date(
								$scope.travelRequest.cabRequest.fromDate);
						$scope.cabObj.toDate = new Date(
								$scope.travelRequest.cabRequest.toDate);
						$scope.taxiCheckBox = true;
						$scope.cabObj.pickUpTime = timeConversionTo12Hrs($scope.travelRequest.cabRequest.fromTime);
					}
					//flightObj
					if ($scope.travelRequest.flightRequest != null) {
						$scope.flightObj = $scope.travelRequest.flightRequest;

						$scope.travelRequest.flightRequest.departureDate = new Date(
								$scope.travelRequest.flightRequest.departureDate);
						$scope.travelRequest.flightRequest.returnDate = new Date(
								$scope.travelRequest.flightRequest.returnDate);
						$scope.travelRequest.flightRequest.dateOfBirth = new Date(
								$scope.travelRequest.flightRequest.dateOfBirth);
						$scope.travelRequest.flightRequest.passportIssueDate = new Date(
								$scope.travelRequest.flightRequest.passportIssueDate);
						$scope.travelRequest.flightRequest.passportExpiryDate = new Date(
								$scope.travelRequest.flightRequest.passportExpiryDate);
						$scope.flightObj.departureDate = new Date(
								$scope.travelRequest.flightRequest.departureDate);
						$scope.flightObj.returnDate = new Date(
								$scope.travelRequest.flightRequest.returnDate);
						$scope.flightObj.dateOfBirth = new Date(
								$scope.travelRequest.flightRequest.dateOfBirth);
						$scope.flightCheckBox = true;
					}
					//forexObj
					if ($scope.travelRequest.forexRequest != null) {
						$scope.forexObj = $scope.travelRequest.forexRequest;
						$scope.travelRequest.forexRequest.forexFromDate = new Date(
								$scope.travelRequest.forexRequest.forexFromDate);
						$scope.travelRequest.forexRequest.forexToDate = new Date(
								$scope.travelRequest.forexRequest.forexToDate);
						$scope.forexObj.forexFromDate = new Date(
								$scope.travelRequest.forexRequest.forexFromDate);
						$scope.forexObj.forexToDate = new Date(
								$scope.travelRequest.forexRequest.forexToDate);
						$scope.forexCheckBox = true;
						$scope.forexObj.forexCountry = $scope.travelRequest.forexRequest.forexCountry;
						console.log("ForexObj edit", $scope.forexObj)
					}

					$scope.checkedOrNotVisa = function(checkVal) {
						if (checkVal == true) {
							$scope.travelRequest.visaRequestVO = $scope.visaObj;
							console.log($scope.travelRequest.visaRequestVO);
						} else {
							$scope.travelRequest.visaRequestVO = null;
						}
						//console.log($scope.travelRequest);
					}

					//visaObj
					if ($scope.travelRequest.visaRequestVO != null) {
						$scope.visaObj = $scope.travelRequest.visaRequestVO;
						$scope.visaCheckBox = true;
						console.log($scope.visaCheckBox);
						//document.getElementById("visaCheckBox").checked = true;
						$scope.checkedOrNotVisa($scope.visaCheckBox);
					}

					$scope.editRequest = function(requestId) {
						$scope.travelRequest.approverId = $scope.empObj.approverName.approverId;
						$scope.travelRequest.requestedFor = $scope.empObj.employeeName;
						$scope.travelRequest.projectName = $scope.empObj.projectName;
						$scope.travelRequest.projectCode = $scope.empObj.projectCode;
						//$scope.travelRequest.cabRequest.pickUpTime = $scope.cabObj.pickUpTime;

						console.log("request id" + requestId)
						console.log($scope.q, 'q', $scope.empObj.projectName,
								'w')
						console.log("Approver ID : "
								+ $scope.travelRequest.approverId);
						console.log("Approver EMPID : "
								+ $scope.empObj.approverName.approverId);
						console.log(" edit data-->>", $scope.travelRequest);
						travelRequest
								.editRecord($scope.travelRequest,
										$scope.travelRequest.requestId)
								.then(
										function(data) {
											console.log("edit object ", data);
											alert("Request Submitted");
											$state.go('home');
										},
										function(err) {
											console.log("error ", err)
											alert("Request not submitted, Please enter all the fields in request details.");
										});
					}

					//date validations
					/* --------------------------code from new request----------------------------*/

					var appendDateTime = function(date, time) {
						var dateTime = date + " " + time;
						return dateTime;
					}
					//initiate date to todays date
					//                         var dateOptions=function(){
					//                                document.getElementById("checkIn").value=new Date();
					//                                document.getElementById("checkOut").value=new Date();
					//
					//                                }
					//                                dateOptions();

					// change date format to yyyy-MM-dd
					var formatDate = function(date) {
						var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
								+ d.getDate(), year = d.getFullYear();

						if (month.length < 2)
							month = '0' + month;
						if (day.length < 2)
							day = '0' + day;

						return [ year, month, day ].join('-');
					}

					//------------------------------------------------
					//Ui bootstrap date logic for accomodation form::

					//variable declaration

					var today = new Date();
					$scope.dateFormat = 'dd/MM/yyyy';
					$scope.accomodationObj.checkInDate = new Date();
					$scope.accomodationObj.checkOutDate = new Date();

					$scope.fromDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : today,
						maxDate : new Date(2030, 5, 22)
					};
					$scope.toDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : today,
						maxDate : new Date(2030, 5, 22)
					};
					$scope.fromDatePopup = {
						opened : false
					};
					$scope.toDatePopup = {
						opened : false
					};
					$scope.ChangeToMinDate = function(accoFromDate) {
						if (accoFromDate != null) {
							$scope.tempDate = new Date(accoFromDate)
							var expiryMinDate = new Date($scope.tempDate
									.setDate(accoFromDate.getDate() + 1));

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

					$scope.cabObj.fromDate = new Date();
					$scope.cabObj.toDate = new Date();
					$scope.dateFormat = 'dd/MM/yyyy';

					$scope.taxiFromDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : today,
						maxDate : new Date(2030, 5, 22)
					};
					$scope.taxiToDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : today,
						maxDate : new Date(2030, 5, 22)
					};
					$scope.taxiFromDatePopup = {
						opened : false
					};
					$scope.taxiToDatePopup = {
						opened : false
					};
					$scope.taxiChangeToMinDate = function(taxiFromDate) {
						if (taxiFromDate != null) {

							//var expiryMinDate = new Date(accoFromDate.setDate(accoFromDate.getDate()+1));
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

					$scope.flightObj.departureDate = new Date();
					$scope.flightObj.returnDate = new Date();
					$scope.flightObj.dateOfBirth = new Date((today
							.getFullYear() - 18), 01, 01);
					$scope.flightObj.passportIssueDate = new Date();
					$scope.flightObj.passportExpiryDate = new Date(
							$scope.flightObj.passportIssueDate.getFullYear() + 10,
							$scope.flightObj.passportIssueDate.getMonth(),
							$scope.flightObj.passportIssueDate.getDate());
					//$scope.dateFormat = 'dd/MM/yyyy';
					$scope.flightDepartureDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : today,
						maxDate : new Date(2030, 5, 22)
					};
					$scope.flightReturnDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : today,
						maxDate : new Date(2030, 5, 22)
					};
					$scope.dobOptions = {
						formatYear : 'yy',
						startingDay : new Date(1970, 5, 22),
						minDate : new Date((today.getFullYear() - 60), 1, 1),
						maxDate : new Date((today.getFullYear() - 18), 1, 1)
					};
					$scope.passIssueDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : new Date((today.getFullYear() - 9), 1, 1),
						maxDate : today
					};
					$scope.passExpiryDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : new Date($scope.flightObj.passportIssueDate
								.getFullYear() + 10,
								$scope.flightObj.passportIssueDate.getMonth(),
								$scope.flightObj.passportIssueDate.getDate()),
						maxDate : new Date($scope.flightObj.passportIssueDate
								.getFullYear() + 20,
								$scope.flightObj.passportIssueDate.getMonth(),
								$scope.flightObj.passportIssueDate.getDate())
					};
					$scope.flightDepartureDatePopup = {
						opened : false
					};
					$scope.flightReturnDatePopup = {
						opened : false
					};
					$scope.dobPopup = {
						opened : false
					};
					$scope.passIssueDatePopup = {
						opened : false
					};
					$scope.passExpiryDatePopup = {
						opened : false
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
					$scope.flightInternationalChangeToMinDate = function(
							flightDepartureDate) {
						if (flightDepartureDate != null) {
							$scope.tempDate = new Date(flightDepartureDate);
							var expiryMinDate = new Date($scope.tempDate
									.setDate($scope.tempDate.getDate() + 2));
							$scope.flightReturnDateOptions.minDate = expiryMinDate;
							$scope.flightObj.returnDate = expiryMinDate;
						}
					};
					$scope.flightInternationalChangeToMinDate();

					$scope.passExpiryChangeToMinDate = function(passIssueDate) {
						if (passIssueDate != null) {
							$scope.tempDate = new Date(passIssueDate);
							var expiryMinDate = new Date($scope.tempDate
									.setDate(
											$scope.tempDate.getFullYear() + 10,
											$scope.tempDate.getMonth(),
											$scope.tempDate.getDate()));
							$scope.passExpiryDateOptions.minDate = expiryMinDate;
							$scope.flightObj.passportExpiryDate = expiryMinDate;
						}
					};

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

					$scope.forexObj.forexFromDate = new Date();
					$scope.forexObj.forexToDate = new Date();

					$scope.forexFromDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : today,
						maxDate : new Date(2030, 5, 22)
					};
					$scope.forexToDateOptions = {
						formatYear : 'yy',
						startingDay : 1,
						minDate : today,
						maxDate : new Date(2030, 5, 22)
					};
					$scope.forexFromDatePopup = {
						opened : false
					};
					$scope.forexToDatePopup = {
						opened : false
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
						console.log("forexopentodate ", $scope.forexFromDate)
						$scope.forexToDatePopup.opened = !$scope.forexToDatePopup.opened;
					};

					//End of date logic of Forex

					$scope.selectCheckInTime = function(checkInTime) {
						var fullTime = timeConversionTo24Hrs(checkInTime);
						$scope.accomodationObj.checkIn = appendDateTime(
								$scope.checkIn, fullTime);
						//alert($scope.accomodationObj.checkIn);

					}

					$scope.selectPickUpTime = function(checkInTime) {
						var fullTime = timeConversionTo24Hrs(checkInTime);
						$scope.cabObj.fromDateAndTime = appendDateTime(
								$scope.fromDateAndTime, fullTime);
						//alert($scope.accomodationObj.checkIn);

					}

					$scope.selectCheckOutTime = function(checkOutTime) {
						var fullTime = timeConversionTo24Hrs(checkOutTime);
						$scope.accomodationObj.checkOut = appendDateTime(
								$scope.checkOut, fullTime);
						//alert($scope.accomodationObj.checkOut);

					}

					$scope.clickHand = function() {
						console.log($scope);
					}

					$scope.daysessions = [];
					userAuthentication
							.errMsg()
							.then(
									function(res) {
										$scope.err = res;
										$scope.requestObject = res.data;
										$scope.intervals = $scope.requestObject.timeIntervals;
										// $scope.intervals=$scope.err.data.timeIntervals[0];
										//                   $scope.checkInTime = $scope.requestObject.timeIntervals[0];
										//                   $scope.checkOutTime = $scope.requestObject.timeIntervals[0];
										//                   $scope.cabObj.pickUpTime = $scope.requestObject.timeIntervals[0];

										$scope.requestObject = res.data;
										$scope.personalDetails = $scope.requestObject.personalDetails;

										$scope.requestDetails = $scope.requestObject.requestDetails;
										$scope.accomodation = $scope.requestDetails.accomodation;
										$scope.taxi = $scope.requestDetails.taxi;
										$scope.flight = $scope.requestDetails.flight;
										$scope.forex = $scope.requestDetails.forex;
										$scope.daysessions = $scope.requestDetails.daySessions;
										console.log("daysessions:",
												$scope.daysessions);

										// console.log("err:", $scope.forex);

										// $scope.city = res.data.requestDetails.accomodation.city;
										// console.log($scope.city)

									});

					// Get all records for the approver to take action
					$scope.approverId = $scope.user.employeeId;
					$scope.requestToApprove = [];

					travelRequest
							.getAllRecords($scope.approverId)
							.then(
									function(res) {
										if (res.data.requestListVO.length == 0) {
											//                                alert("No requests found");
											//                                $state.go('home')
										} else {
											for (var i = 0; i < res.data.requestListVO.length; i++) {
												if (res.data.requestListVO[i].currentStatus == "L1 Pending"
														|| res.data.requestListVO[i].currentStatus == "L2 Pending") {

													$scope.requestToApprove
															.push(res.data.requestListVO[i]);
												}
											}
											console
													.log($scope.requestToApprove)
										}
									}, function(err) {
										console.log("error: ", err);
									})

					// Get request to take action on              
					$scope.requestToApproveDetails;
					$scope.aq;
					$scope.getRequestToApproveDetails = function(requestId) {

						travelRequest
								.getRequestDetails(requestId)
								.then(
										function(res) {
											$scope.requestToApproveDetails = res.data;
											$state.go('actionOnRequest')

											console
													.log(
															"request to approve Details inside function",
															$scope.requestToApproveDetails);
											travelRequest.request = $scope.requestToApproveDetails;
										}, function(err) {

										});
					}
					console.log("service variable ", travelRequest.request)
					//                  console.log("request to approve Details ",$scope.requestToApproveDetails);

					$scope.noAccessMsg = "";
					$scope.gradeCheckForCab = function(carType) {

						switch ($scope.user.grade)

						{
						case "M0":
						case "M1":
						case "M2":
						case "M3":
						case "M4":
							if (carType != "Economy(Indica,Swift,...)") {
								$scope.noAccessMsg = "Please take authorisation from Manager";
							} else {
								$scope.noAccessMsg = "";
							}
							break;
						case "M5":
						case "M6":
							if (carType == "SUV & Luxury(Innova,Corolla Altis,...)") {
								$scope.noAccessMsg = "Please take authorisation from Manager";
							} else {
								$scope.noAccessMsg = "";
							}
							break;
						}
					}

					$scope.gradeCheckForFlight = function(prefClass) {
						switch ($scope.user.grade) {
						case "M0":
						case "M1":
						case "M2":
						case "M3":
						case "M4":
						case "M5":
						case "M6":
							if (prefClass != "Economy") {
								$scope.noAccessMsg = "Please take authorisation from Manager";
							} else {
								$scope.noAccessMsg = "";
							}
							break;

						}

					}

					//flight DOB
					$scope.chooseDOB = function() {
						$scope.flightObj.dateOfBirth = new Date(
								$scope.flightObj.dateOfBirth);
						$scope.flightObj.dateOfBirth = $filter('date')(
								$scope.flightObj.dateOfBirth, "yyyy-MM-dd");
						console.log($scope.flightObj.dateOfBirth,
								"$scope.flightObj.DOB");
						$scope.DOB = $scope.flightObj.dateOfBirth;
						console.log($scope.DOB);

						$scope.age;
						// $scope.invalidDOB="";

						var today = new Date();
						console.log(today);
						var birthDate = new Date($scope.DOB);
						console.log(birthDate)

						$scope.age = today.getFullYear()
								- birthDate.getFullYear();
						console.log($scope.age);
						var m = today.getMonth() - birthDate.getMonth();

						if (m < 0
								|| (m === 0 && today.getDate() < birthDate
										.getDate())) {
							$scope.age--;
						}

						//document.getElementById("sample").min=new Date();
						var date = document.getElementById("dob").value;

						console.log(date, "date");
						var t = new Date();
						var today1 = formatDate(t);
						//var tISO=d.toISOString();
						console.log(today1, "today1");
						console.log(date);

						if ($scope.DOB > today1) {
							console.log("in if");
							document.getElementById("dob").value = "";
							//alert("waring!");
							$scope.invalidDOB = "Please select valid date of birth";
						} else if ($scope.age < 18) {

							$scope.invalidDOB = "Your not eligible for passport";
							console.log($scope.invalidDOB);
						} else {
							$scope.invalidDOB = "";
						}

					}

					//Approver id from approver name:

					//                  Countries validation
					$scope.checkCountry = function() {
						var origin = $scope.flightObj.originCountry
								.toUpperCase();
						var destination = $scope.flightObj.destinationCountry
								.toUpperCase();
						if (origin == destination) {
							$scope.originCountryErr = "Origin and destination country should be different";
							$scope.flightObj.originCountry = origin;
							$scope.flightObj.destinationCountry = destination;
						} else {
							$scope.originCountryErr = "";
						}
						if ($scope.flightObj.originCountry == ""
								|| $scope.flightObj.destinationCountry == "") {
							$scope.originCountryErr = "";
						}
					}

					//                 Cities validation
					$scope.checkCity = function() {
						var originCity = $scope.flightObj.departureLocation
								.toUpperCase();
						var destinationCity = $scope.flightObj.destinationLocation
								.toUpperCase();
						if (originCity == destinationCity) {
							$scope.originCityErr = "From and to city should be different";
							self.mainForm.flightFromCity.$setValidity(
									'flightFromCity', false);
							self.mainForm.flightToCity.$setValidity(
									'flightToCity', false);
							$scope.flightObj.departureLocation = originCity;
							$scope.flightObj.destinationLocation = destinationCity;
						} else {
							if ($scope.flightObj.departureLocation == null
									|| $scope.flightObj.destinationLocation == null) {
								$scope.originCityErr = "";
							}
							$scope.originCityErr = "";
							self.mainForm.flightFromCity.$setValidity(
									'flightFromCity', true);
							self.mainForm.flightToCity.$setValidity(
									'flightToCity', true);
						}

					}

					$scope.checkAccomodationCity = function(origin, destination) {
						var originCity = origin.toUpperCase();
						var destinationCity = destination.toUpperCase();
						if (originCity == destinationCity) {
							$scope.accomodationCityErr = "Country and City should be different";
							self.mainForm.accommodationCountry.$setValidity(
									'accommodationCountry', false);
							self.mainForm.accommodationCity.$setValidity(
									'accommodationCity', false);
							$scope.accomodationObj.country = originCity;
							$scope.accomodationObj.city = destinationCity;
						} else {
							if ($scope.accomodationObj.country == null
									|| $scope.accomodationObj.city == null) {
								$scope.accomodationCityErr = "";
							}
							$scope.accomodationCityErr = "";
							self.mainForm.accommodationCountry.$setValidity(
									'accommodationCountry', true);
							self.mainForm.accommodationCity.$setValidity(
									'accommodationCity', true);
						}

					}

					$scope.checkCabCity = function(origin, destination) {
						var originCity = origin.toUpperCase();
						var destinationCity = destination.toUpperCase();
						if (originCity == destinationCity) {
							$scope.cabCityErr = "Pickup and drop City should be different";
							self.mainForm.cabCity
									.$setValidity('cabCity', false);
							self.mainForm.cabToCity.$setValidity('cabToCity',
									false);
							$scope.cabObj.pickupCity = originCity;
							$scope.cabObj.dropCity = destinationCity;
						} else {
							if (($scope.cabObj.pickupCity == null && $scope.cabObj.dropCity == null)
									&& ($scope.cabObj.pickupCity == "" || $scope.cabObj.dropCity == "")) {
								$scope.cabCityErr = "";
							}
							$scope.cabCityErr = "";
							self.mainForm.cabCity.$setValidity('cabCity', true);
							self.mainForm.cabToCity.$setValidity('cabToCity',
									true);
						}

					}

					$scope.checkCabLocation = function(origin, destination) {
						var originCity = origin.toUpperCase();
						var destinationCity = destination.toUpperCase();
						if (originCity == destinationCity) {
							$scope.cabLocationErr = "Pickup and drop location should be different";
							self.mainForm.cabPickupPoint.$setValidity(
									'cabPickupPoint', false)
							self.mainForm.cabDropPoint.$setValidity(
									'cabDropPoint', false)
							$scope.cabObj.pickUplocation = originCity;
							$scope.cabObj.dropLocation = destinationCity;
						} else {
							if ($scope.cabObj.pickUpLocation == null
									&& $scope.cabObj.dropLocation == null) {
								$scope.cabLocationErr = "";

							}
							$scope.cabLocationErr = "";
							self.mainForm.cabPickupPoint.$setValidity(
									'cabPickupPoint', true)
							self.mainForm.cabDropPoint.$setValidity(
									'cabDropPoint', true)
						}

					}

					$scope.checkedOrNotFlight = function(checkVal) {
						if (checkVal == true) {
							$scope.travelRequest.flightRequest = $scope.flightObj;
						} else {
							$scope.travelRequest.flightRequest = null;
						}
						//                     console.log($scope.travelRequest);
					}

					$scope.checkedOrNotAccomodation = function(checkVal) {
						if (checkVal == true) {
							$scope.travelRequest.accomodationRequest = $scope.accomodationObj;
						} else {
							$scope.travelRequest.accomodationRequest = null;
						}
						//                     console.log($scope.travelRequest);
					}

					$scope.checkedOrNotForex = function(checkVal) {

						if (checkVal == true) {
							$scope.travelRequest.forexRequest = $scope.forexObj;
						} else {
							$scope.travelRequest.forexRequest = null;
						}
						//console.log($scope.travelRequest);
					}

					$scope.checkedOrNotCab = function(checkVal) {

						if (checkVal == true) {
							$scope.travelRequest.cabRequest = $scope.cabObj;
						} else {
							$scope.travelRequest.cabRequest = null;
						}
						//console.log($scope.travelRequest);
					}

					$scope.send = function() {
						//$scope.travelRequest.approverId= $scope.empObj.approverName.approverId;
						$scope.travelRequest.requestedFor = $scope.empObj.employeeName;
						$scope.travelRequest.projectName = $scope.empObj.projectName;
						$scope.travelRequest.projectCode = $scope.empObj.projectCode;
						/* if($scope.forexCheckBox){
							var forCountry=$scope.forexObj.forexCountry.country;
							$scope.forexObj.forexCountry=forCountry;
						}*/
						$scope.travelRequest.actionOnRequest = "Edit";
						$scope.travelRequest.requestId = $scope.editRequestId;
						//$scope.travelRequest.cabRequest.pickUpTime = $scope.cabObj.pickUpTime;
						console.log($scope.q, 'q', $scope.empObj.projectName,
								'w')
						console.log("Approver ID : "
								+ $scope.travelRequest.approverId);
						console.log("Approver EMPID : "
								+ $scope.empObj.approverName.approverId);
						console.log($scope.editRequestId + "data--",
								$scope.travelRequest);
						travelRequest
								.editRecord($scope.travelRequest,
										$scope.editRequestId)
								.then(
										function(data) {
											console.log(data);
											alert("Request Submitted");
											$state.go('home');
										},
										function(err) {
											console.log("error ", err)
											alert("Request not submitted, Please enter all the fields in request details.");
										});
					}

					$scope.colorChangePersonal = function() {
						$scope.tabClassPersonal = "whiteTab";
						$scope.tabClassRequest = "greyTab";
						$scope.tabClassConfirm = "greyTab";
					}

					$scope.colorChangeRequest = function() {
						$scope.tabClassPersonal = "greyTab";
						$scope.tabClassRequest = "whiteTab";
						$scope.tabClassConfirm = "greyTab";
						$scope.progressClass1 = "progress-bar";
					}

					$scope.colorChangeConfirm = function() {
						$scope.tabClassPersonal = "greyTab";
						$scope.tabClassRequest = "greyTab";
						$scope.tabClassConfirm = "whiteTab";
						$scope.progressClass2 = "progress-bar";
					}

					$scope.openNextForm = function(accordionName) {

						if (accordionName == "accomodationAccordion") {

							if ($scope.accomodationAccordion == "panel-collapse collapse in") {
								$scope.accomodationAccordion = "panel-collapse collapse";
								$scope.accomodationHeading += "back-green";
							}
						}

					}

					$scope.checkClick = function(temp) {
						console.log("temp" + temp);
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

					$scope.nextPreviosButton = function(value) {
						if (true) {
							$("#" + value).click();
						}
					}

					//javascript bootstrap tooltip
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});

					if ($scope.travelRequest.requestType == "Self") {
						$scope.empName = "Employee Name:";
					} else {
						$scope.empName = "Requested For:";
					}
					$scope.cell = {
						evaluator : "Self"
					}

					$scope.empName = "Employee Name:";
					$scope.empObj.employeeName = $scope.user.employeeName;
					$scope.empObj.employeeMobileNumber = $scope.user.employeeMobileNumber;

					$scope.setPersonalData = function() {
						if ($scope.cell.evaluator == "Self") {
							$scope.travelRequest.requestType = "Self";
						} else {
							$scope.travelRequest.requestType = "Other";
						}
						if ($scope.cell.evaluator == "Self") {
							$scope.empObj.employeeName = $scope.user.employeeName;
							$scope.empObj.employeeMobileNumber = $scope.user.employeeMobileNumber;

							$scope.empName = "Employee Name:";
							$scope.empObj.employeeLocation = null;
							$scope.empObj.approverName = null;
							$scope.getProjectName();
							console.log($scope.empName);
						} else if ($scope.cell.evaluator == "Other") {
							$scope.empObj.employeeName = "";
							$scope.empObj.employeeMobileNumber = "";
							$scope.empObj.employeeLocation = null;
							$scope.empObj.projectCode = "";
							$scope.empObj.projectName = "";
							$scope.empObj.approverName = null;
							$scope.empName = "Requested For:";
							console.log($scope.empName);
						}
					}

					$scope.getProjectName = function() {
						console.log("projectList", $scope.projectList);
						for (var i = 0; i < $scope.projectList.listOfProjectDetails.length; i++) {
							if ($scope.projectList.listOfProjectDetails[i].projectCode == $scope.empObj.projectCode) {

								$scope.empObj.projectName = $scope.projectList.listOfProjectDetails[i].projectName;

							}

						}
						for (var i = 0; i < $scope.projectList.listOfApprovers.length; i++) {
							if ($scope.projectList.listOfApprovers[i].approverName == $scope.empObj.approverName) {

								$scope.empObj.approverId = $scope.projectList.listOfApprovers[i].approverId;

							}

						}
						console.log($scope.empObj);
					}

					$scope.back = function() {
						$state.go('home');
					}

					$scope.GetDays = function() {
						var dropdt = new Date($scope.forexObj.forexFromDate);
						var pickdt = new Date($scope.forexObj.forexToDate);
						var noOfDays = ((pickdt - dropdt) / (24 * 3600 * 1000));
						noOfDays = noOfDays + 1;//including day of drop
						noOfDays = noOfDays + "";
						return noOfDays;
					}

					$scope.cal = function() {
						$scope.perdiem = 0;

						if ($scope.forexObj.forexToDate) {
							$scope.forexObj.forexNoOfDays = $scope.GetDays();
						}
						for (var i = 0; i < $scope.projectList.perDeimList.length; i++) {
							if ($scope.projectList.perDeimList[i].country == $scope.forexObj.forexCountry.country) {

								$scope.perdiem = $scope.projectList.perDeimList[i].perdiem;
								$scope.currency = $scope.projectList.perDeimList[i].currency;
							}

						}
						$scope.amount = $scope.forexObj.forexNoOfDays
								* $scope.perdiem;
						$scope.forexObj.forexAmount = $scope.amount;
						//$scope.forexObj.forexCountry=$scope.forexObj.forexCountry.country;
						console.log("forex perdiem ",
								$scope.forexObj.forexAmount)
					}

					$scope.myFormName = "personal";
					$scope.form1Name = "form1";
					//                  $scope.classValue1 = "active";
					$scope.firstTabClass = true;
					$scope.secondTabClass = false;
					$scope.thirdTabClass = false;
					$scope.tabsArr = [];
					$scope.addToArray = function(oForm) {
						debugger;
						alert();
						//                  $scope.tabsArr.push(oForm);
						//console.log($scope.tabsArr);
					}

					var self = this;
					self.personal = "personal";
					self.accommodationForm = "accommodationForm";
					self.visaForm = "visaForm";
					self.mainForm = "mainForm";

					$scope.returnSessions = $scope.daysessions;
					$scope.changeSession = function(session) {
						$scope.retString = $scope.flightObj.returnDate
								.toDateString();
						$scope.depString = $scope.flightObj.departureDate
								.toDateString();
						if ($scope.depString == $scope.retString /*&& $scope.flightObj.returnTime==session*/) {
							if (session != 'Night') {
								if ($scope.flightObj.departureTime == 'Afternoon') {
									$scope.returnSessions = [ 'Evening',
											'Night' ];
								} else if (session == 'Evening') {
									$scope.returnSessions = [ 'Night' ];
								} else if (session == 'Morning') {
									$scope.returnSessions = [ 'Afternoon',
											'Evening', 'Night' ];
								}
								/*self.mainForm.flightReturnTime.$setValidity("flightReturnTime",false);
								$scope.returnSameErr="Departure time and return time cannot be same.";*/
							} else {
								$scope.tomorrow = new Date();
								console.log($scope.flightObj.departureDate,
										"$scope.flightObj.departureDate")
								$scope.tomorrow
										.setDate($scope.flightObj.departureDate
												.getDate() + 1);

								console.log($scope.flightObj.departureDate,
										"$scope.flightObj.departureDate");
								console
										.log($scope.tomorrow,
												" $scope.tomorrow");

								$scope.tomorrow
										.setDate($scope.flightObj.departureDate
												.getDate() + 1);
								$scope.tomorrow = $filter('date')(
										$scope.tomorrow, "yyyy-MM-dd");
								;
								$scope.nextDay = new Date($scope.tomorrow);
								$scope.flightObj.returnDate = $scope.nextDay;
								$scope.returnSessions = [ 'Morning',
										'Afternoon', 'Evening', 'Night' ];
							}
						} else if ($scope.depString == $scope.retString) {
							self.mainForm.flightReturnTime.$setValidity(
									"flightReturnTime", true);
							$scope.returnSameErr = "";
						} else {
							$scope.returnSessions = [ 'Morning', 'Afternoon',
									'Evening', 'Night' ];
						}

					}

					$scope.TabClick = function(formName) {
						//                  debugger;
						//                  console.log(self);
						//                  console.log(self.personal.$valid);
						//                  console.log(self.accommodationForm.$valid);
						//                  console.log($scope.myFormName,"myformname");
						if (formName != undefined) {
							if (self.personal.$valid) {
								console.log(self.personal.$valid,
										'personal is valid');
								$scope.classValue2 = "active";
								$scope.classValue1 = "";
								$scope.classValue3 = "";
								$scope.secondTabClass = true;
								$scope.firstTabClass = false;
								$scope.thirdTabClass = false;
							}
							if (self.personal.$valid && formName == "mainForm"
									&& self.mainForm.$valid == true) {
								$scope.classValue3 = "active";
								$scope.classValue1 = "";
								$scope.classValue2 = "";
								$scope.secondTabClass = false;
								$scope.firstTabClass = false;
								$scope.thirdTabClass = true;
							}
						} else {
							$scope.classValue2 = "";
							$scope.classValue1 = "active";
							$scope.classValue3 = "";
							$scope.secondTabClass = false;
							$scope.firstTabClass = true;
							$scope.thirdTabClass = false;
						}

					}

					$scope.flag1 = false;
					$scope.accommodationCheckBox;
					$scope.flag2 = false;
					$scope.taxiCheckBox;
					$scope.flag3 = false;
					$scope.flightCheckbox;
					$scope.flag4 = false;
					$scope.forexCheckbox;
					$scope.flag5 = false;
					$scope.visaCheckbox;
					console.log($scope.flag1, $scope.flag2, $scope.flag3,
							$scope.flag4, $scope.flag5, '0');

					$scope.accomodationValid = function(val) {
						//console.log(self.visaForm,'self 1');
						if (val == true) {
							//console.log(self.visaForm," self 2")
							if (self.visaForm.$valid == true) {
								$scope.flag1 = true;
								$scope.flag2 = true;
								$scope.flag3 = true;
								$scope.flag4 = true;
								$scope.flag5 = true;
								console.log($scope.flag1, $scope.flag2,
										$scope.flag3, $scope.flag4,
										$scope.flag5, '1');
							}
							//console.log("valid form");

							else if (val == false) {
								$scope.flag1 = true;
								$scope.flag2 = true;
								$scope.flag3 = true;
								$scope.flag4 = true;
								$scope.flag5 = true;
								console.log($scope.flag1, $scope.flag2,
										$scope.flag3, $scope.flag4,
										$scope.flag5, '2');
							} else {
								console.log($scope.flag1, $scope.flag2,
										$scope.flag3, $scope.flag4,
										$scope.flag5, '3');
							}
						}
					}

					$scope.masterFlag;
					$scope.onClickingConfirm = function() {
						if (($scope.flag1 == true) && ($scope.flag2 == true)
								&& ($scope.flag3 == true)
								&& ($scope.flag3 == true)
								&& ($scope.flag5 == true)) {
							$scope.masterFlag = true;
							$scope.secondTabClass = false;
							$scope.firstTabClass = false;
							$scope.thirdTabClass = true;
						} else {
							$scope.secondTabClass = true;
							$scope.firstTabClass = false;
							$scope.thirdTabClass = false;
						}
					}

					$scope.function1 = function(formName1) {
						console.log('key is pressed');
						console.log(formName1.$valid);
						if (formName1.$valid == true) {
							$scope.flag1 = true;
							console.log($scope.flag1, 'flag1');
						}
					}

					//            $scope.function2 = function(formName){
					//            console.log('key is pressed');
					//            console.log(formName.$valid);
					//            if(formName.$valid == true){
					//            $scope.flag1 = true;
					//            console.log($scope.flag1,'flag1');
					//            }
					//            }

					//            $scope.function3 = function(formName){
					//            console.log('key is pressed');
					//            console.log(formName.$valid);
					//            if(formName.$valid == true){
					//            $scope.flag1 = true;
					//            console.log($scope.flag1,'flag1');
					//            }
					//            }

					$scope.function4 = function(formName) {
						console.log('key is pressed');
						console.log(formName.$valid);
						if (formName.$valid == true) {
							$scope.flag1 = true;
							console.log($scope.flag1, 'flag1');
						}
					}

					$scope.function5 = function(formName) {
						console.log('key is pressed');
						console.log(formName.$valid);
						if (formName.$valid == true) {
							$scope.flag1 = true;
							console.log($scope.flag1, 'flag1');
						}
					}

				})
