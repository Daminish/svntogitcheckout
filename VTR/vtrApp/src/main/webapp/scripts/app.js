// create the module and name it vtrApp
var vtrApp = angular.module('vtrApp', ['ui.router','ui.select', 'ngSanitize', 'ngCookies']);

var restServiceUrl = "http://localhost:8080/visaTravelRequest/services/";

vtrApp.run(function($rootScope, $state, $cookieStore, $http) {
	
	/*// keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
    	console.log("$rootScope.globals.currentUser username"+$rootScope.globals.currentUser.username);
        console.log("$rootScope.globals.currentUser authdata"+$rootScope.globals.currentUser.authdata);
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }*/
    
	$rootScope.$on("$stateChangeStart", function(event, toState, toParams,
			fromState, fromParams) {
		if (toState.authenticate && !$rootScope.globals.currentUser) {
			// User isn’t authenticated
			$state.go("login");
			event.preventDefault();
		}
	});
});

vtrApp.directive('numbersOnly', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attr, ngModelCtrl) {
            function fromUser(text) {
                if (text) {
                    var transformedInput = text.replace(/[^0-9]/g, '');

                    if (transformedInput !== text) {
                        ngModelCtrl.$setViewValue(transformedInput);
                        ngModelCtrl.$render();
                    }
                    return transformedInput;
                }
                return undefined;
            }            
            ngModelCtrl.$parsers.push(fromUser);
        }
    };
});

vtrApp.filter('genderFilter', function() {
    return function(input) {
    	if (input === 'M') {
            return 'Male';
    	}
    	else if (input === 'F') {
            return 'Female';
    	}

    }
});

// configure our routes
vtrApp.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider

		//login page
		.state('login', {
			url			: "/login",
			templateUrl : 'pages/login.html',
			controller  : 'loginController',
			authenticate: false
		})
		
		// home page
		.state('home', {
			//url			: "/home",
			templateUrl : 'pages/home.html',
			controller  : 'homeController',
			authenticate: true
		})
	
		//visa request page
		.state('visaRequests', {
			//url			: "/visaRequests",
			params: {
	            action: 'vRequests'
	        },
			templateUrl : 'pages/visaRequests.html',
			controller  : 'visaRequestsController',
			authenticate: true
		})
		
		//visa action page
		.state('visaRequestActions', {
			//url			: "/visaRequests",
			params: {
	            action: 'vActions'
	        },
			templateUrl : 'pages/visaRequests.html',
			controller  : 'visaRequestsController',
			authenticate: true
		})

		/*//visa request action page
		.state('visaRequestActions', {
			templateUrl : 'pages/visalRequestActions.html',
			controller  : 'visaRequestActionsController'
		})*/

		//visa request add or edit page
		.state('visaRequestAddOrEdit', {
			//url			: "/visaRequestAddOrEdit",
			params: {
	            action: null
	        },
			templateUrl : 'pages/visaRequestsAddOrEdit.html',
			controller  : 'visaRequestsAddOrEditController',
			authenticate: true
		})
		
		//travel request page
		.state('travelRequests', {
			templateUrl : 'pages/travelRequests.html',
			controller  : 'travelRequestsController'
		})
		//travel request action page
		.state('travelRequestActions', {
			templateUrl : 'pages/travelRequestActions.html',
			controller  : 'travelRequestActionsController',
			authenticate: true
		});
	
	$urlRouterProvider.otherwise("/login");
});

vtrApp.service('vtrService', function($rootScope, $http) {
	
	var empList = [];
	var loggedEmpObj = null;
	var visaRequestObj = {};
	var travelDestList = [];
	var m6AndM7List = [];
	var fromRequestOrAction = "";
	
	this.setVisaRequestObj = function(visaRequest) {
		visaRequestObj = visaRequest;
	}
	
	this.getVisaRequestObj = function(){
        return visaRequestObj;
    }
	
	this.loadTravelDestinations = function(){
		if (typeof travelDestList !== "undefined" && travelDestList !== null && travelDestList.length <= 0) {
			console.log("inside travelDestList null");
			$http({
	            method : 'GET',
	            url : restServiceUrl + 'getTravelDestinations'
	        }).then(function successCallback(response) {
	        	//console.log("response data="+response.data);
	        	travelDestList = response.data;
	        }, function errorCallback(response) {
	            console.log(response.statusText);
	        });
		}
    }
	
	this.getTravelDestList = function(){
        return travelDestList;
    }
	
	this.loadM6AndM7List = function(){
		if (typeof m6AndM7List !== "undefined" && m6AndM7List !== null && m6AndM7List.length <= 0) {
			console.log("inside m6AndM7List null");
			$http({
	            method : 'GET',
	            url : restServiceUrl + 'getM6AndM7/'+$rootScope.loggedInEmpID
	        }).then(function successCallback(response) {
	        	//console.log("response data="+response.data);
	        	m6AndM7List = response.data;
	        }, function errorCallback(response) {
	            console.log(response.statusText);
	        });
		}
    }
	
	this.getM6AndM7List = function(){
        return m6AndM7List;
    }
	
	this.loadEmpList = function(){
		if (typeof empList !== "undefined" && empList !== null && empList.length <= 0) {
			console.log("inside emp list null");
			$http({
	            method : 'GET',
	            url : restServiceUrl + 'getAllEmployee/'+$rootScope.loggedInEmpID
	        }).then(function successCallback(response) {
	        	//console.log("response data="+response.data);
	        	empList = response.data;
	        }, function errorCallback(response) {
	            console.log(response.statusText);
	        });
		}
    }
	
	this.getEmpList = function(){
		return empList;
	}
	
	this.setLoggedInEmpObj = function(empObj){
		loggedEmpObj = empObj;
		$rootScope.loggedInEmpID = loggedEmpObj.empid;
		$rootScope.loggedInEmpName = loggedEmpObj.empname;
    }
	
	this.getLoggedInEmpObj = function(){
		return loggedEmpObj;
	}
	
	this.loadAllRequired = function() {
		this.loadTravelDestinations();
		this.loadM6AndM7List();
		this.loadEmpList();
	}
	
	this.clearAllRequired = function() {
		empList = [];
		loggedEmpObj = null;
		visaRequestObj = {};
		travelDestList = [];
		m6AndM7List = [];
		fromRequestOrAction = "";
	}
	
	this.getFromRequestOrAction = function(){
		return fromRequestOrAction;
	}
	
	this.setFromRequestOrAction = function(fromRorA){
		fromRequestOrAction = fromRorA;
	}
});

vtrApp.controller('loginController',function ($scope, $rootScope, $state, AuthenticationService) {
    // reset login status
    AuthenticationService.ClearCredentials();

    $scope.login = function () {
        $scope.dataLoading = true;
        AuthenticationService.Login($scope.username, $scope.password, function (response) {
            if (response.success) {
                AuthenticationService.SetCredentials($scope.username, $scope.password);
                $state.go("home");
            } else {
                $scope.error = response.message;
                $scope.dataLoading = false;
            }
        });
    };
});
    

vtrApp.controller('homeController',function ($scope) {

});

// create the controller and inject Angular's $scope
vtrApp.controller('visaRequestsController', function($scope, $rootScope, $http, $state, $stateParams, vtrService) {

	$scope.visaRequests = [];
	
	_refreshVisaRequestsData();

	 //Private Methods 
    //HTTP GET- get all visa requests
    function _refreshVisaRequestsData() {
    	
    	vtrService.setFromRequestOrAction($stateParams.action);
    	
    	$scope.isVRequests = true;
    	$scope.heading = 'Visa Request(s)';
    	if ($stateParams.action == 'vActions') {
    		$scope.isVRequests = false;
    		$scope.heading = 'Visa Action(s)';
    	}
    	
    	var service = "allVisaActionForEmp/";
    	if ($scope.isVRequests) {
    		service = "allVisaRequestByEmp/";
    	}
    	
        $http({
            method : 'GET',
            url : restServiceUrl + service +$rootScope.loggedInEmpID
        }).then(function successCallback(response) {
            $scope.visaRequests = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
    
    $scope.goToViewVisaRequest = function(visaRequest) {
    	vtrService.setVisaRequestObj(visaRequest);
    	$state.go("visaRequestAddOrEdit", {action: 'VIEW'});
	}
    
    $scope.goToAddNewVisaRequest = function() {
    	vtrService.setVisaRequestObj(null);
		$state.go("visaRequestAddOrEdit", {action: 'ADD'});
	}
    
    $scope.goToEditVisaRequest = function(visaRequest) {
    	vtrService.setVisaRequestObj(visaRequest);
    	$state.go("visaRequestAddOrEdit", {action: 'EDIT'});
	}
    
    $scope.reloadVisaRequests = function() {
		$state.reload();
	}
    
    $scope.isDisplayEditBtn = function(visaRequest) {
    	if($scope.isVRequests) {
    		if(visaRequest.inqueue === "EMPLOYEE") {
    			return true;
    		}
    	}
    	else {
    		return true;
    	}
    	return false;
    }
    
    $scope.isDisplayDeleteBtn = function(visaRequest) {
    	if($scope.isVRequests) {
    		if(visaRequest.inqueue === "EMPLOYEE") {
    			return true;
    		}
    	}
    	return false;
    }
    
    $scope.goToDeletetVisaRequest = function(visaRequest) {
		var r = confirm("Are you sure you want to delete Visa Request: "+visaRequest.visarequestid+" ?");
		var deletedID = visaRequest.visarequestid;
		if (r == true) {
			console.log("Confirmed for delete");
			$http({
	            method : 'DELETE',
	            url : restServiceUrl + 'deleteVisaRequest/'+$rootScope.loggedInEmpID+'/' + visaRequest.visarequestid
	        }).then(function successCallback(response) {
	        	console.log(response.statusText);
	        	alert("Visa Request : "+deletedID + " deleted!");
	        	$scope.reloadVisaRequests();
	        }, function errorCallback(response) {
	            console.log(response.statusText);
	        });
		} else {
			console.log("NOT Confirmed for delete");
		}
	}
    
});

vtrApp.controller('visaRequestActionsController', function($scope) {
	$scope.heading = 'Visa Request Actions page - Work In Progress';
});

vtrApp.controller('visaRequestsAddOrEditController', function($scope, $rootScope, $http, $state, $stateParams, vtrService) {
	
	$scope.isAdd = false;
	$scope.employee = {};
	$scope.empList = [];
	$scope.travelDestList = [];
	$scope.m6AndM7List = [];
	
	$scope.visaRequest = {};
	
	prepareButtons(false, false, false);
	
	$scope.empList = vtrService.getEmpList();
	$scope.travelDestList = vtrService.getTravelDestList();
	$scope.m6AndM7List = vtrService.getM6AndM7List();
	
	init();
	
	function init() {
		$scope.visaRequest = {};
		
		if ($stateParams.action == 'ADD') {
			prepareAddScreen();
		}
		else if ($stateParams.action == 'EDIT') {
			prepareEditScreen();
		}
		else if ($stateParams.action == 'VIEW') {
			prepareViewScreen();
		}
	}
	
	function prepareEmployeeBlock(isBlockVisible, isBlockDisabled) {
		$scope.isEmployeeBlockVisible = isBlockVisible;
		$scope.empBlockDisabled = isBlockDisabled;
	}
	
	function prepareManagerBlock(isBlockVisible, isBlockDisabled, isBlockRequired) {
		$scope.isManagerBlockVisible = isBlockVisible;
		$scope.managerBlockDisabled = isBlockDisabled;
		$scope.managerBlockRequired = isBlockRequired;
	}
	
	function prepareFinanceBlock(isBlockVisible, isBlockDisabled, isBlockRequired) {
		$scope.isFinanceBlockVisible = isBlockVisible;
		$scope.financeBlockDisabled = isBlockDisabled;
		$scope.financeBlockRequired = isBlockRequired;
	}
	
	function preparePartnerBlock(isBlockVisible, isBlockDisabled, isBlockRequired, isDisplayOnlyForPartner) {
		$scope.isPartnerBlockVisible = isBlockVisible;
		$scope.partnerBlockDisabled = isBlockDisabled;
		$scope.partnerBlockRequired = isBlockRequired;
		$scope.isShowForOnlyPartner = isDisplayOnlyForPartner;
	}
	
	function prepareButtons(isDraftBtnShow, isSubmitBtnShow, isResetBtnShow) {
		$scope.isSaveDraftBtnVisible = isDraftBtnShow;
		$scope.isSubmitBtnVisible = isSubmitBtnShow;
		$scope.isResetBtnVisible = isResetBtnShow;
	}
	
	function resetEmployeeBlockForAdd() {
		
		$scope.employee.selected = undefined;
		
		$scope.visaRequest.selforothers = true;
		setEmployeeDetails(vtrService.getLoggedInEmpObj());
		
		$scope.visaRequest.approvingmanager = undefined;
		$scope.visaRequest.traveldestination = undefined;
		$scope.visaRequest.visatype = undefined;
		$scope.visaRequest.typeofvisit = undefined;
		$scope.visaRequest.projectcode = "";
		$scope.visaRequest.activitycode = "";
	}
	
	function prepareAddScreen() {
		console.log("Add Action!");
		
		$scope.isAdd = true;
		
		$scope.heading = 'Add New - Visa Request';
		$scope.submitBtnLabel = "Submit";
		
		prepareEmployeeBlock(true, false);
		prepareManagerBlock(false, true, false);
		prepareFinanceBlock(false, true, false);
		preparePartnerBlock(false, true, false, false);
		
		resetEmployeeBlockForAdd();
		
		prepareButtons(true, true, true);
	}
	
	function prepareEditScreen() {
		
		$scope.visaRequest = angular.copy(vtrService.getVisaRequestObj());
		
		//console.log(angular.toJson($scope.visaRequest));
		console.log("Edit Action!");
		$scope.isAdd = false;
		$scope.heading = 'Edit - Visa Request';
		$scope.aManagerDisabled = true;
		
		if($scope.visaRequest.inqueue === "EMPLOYEE") {
			
			$scope.submitBtnLabel = "Submit";
			
			$scope.aManagerDisabled = false;
			
			prepareEmployeeBlock(true, false);
			prepareManagerBlock(false, true, false);
			prepareFinanceBlock(false, true, false);
			preparePartnerBlock(false, true, false, false);
			
			prepareButtons(true, true, true);
		}
		else if($scope.visaRequest.inqueue === "MANAGER") {
			
			$scope.submitBtnLabel = "Approve";
			
			prepareEmployeeBlock(true, false);
			prepareManagerBlock(true, false, true);
			prepareFinanceBlock(false, true, false);
			preparePartnerBlock(false, true, false, false);
			
			prepareButtons(false, true, true);
			
			if ($scope.visaRequest.partnercomments != "undefined"
				&& $scope.visaRequest.partnercomments != null
				&& $scope.visaRequest.partnercomments != "") {
				
				$scope.isPartnerBlockVisible = true;
			}

		}
		else if($scope.visaRequest.inqueue === "FINANCE") {
			
			$scope.submitBtnLabel = "Update";
		
			prepareEmployeeBlock(true, true);
			prepareManagerBlock(true, true, true);
			prepareFinanceBlock(true, false, true);
			preparePartnerBlock(false, true, false, false);
			
			prepareButtons(false, true, true);
			
			if ($scope.visaRequest.partnercomments != "undefined"
				&& $scope.visaRequest.partnercomments != null
				&& $scope.visaRequest.partnercomments != "") {
				
				$scope.isPartnerBlockVisible = true;
			}
		}
		else if($scope.visaRequest.inqueue === "PARTNER") {
			
			$scope.submitBtnLabel = "Update";
			
			prepareEmployeeBlock(true, true);
			prepareManagerBlock(true, true, true);
			prepareFinanceBlock(true, true, true);
			preparePartnerBlock(true, false, true, true);
			
			prepareButtons(false, true, true);
			
			$scope.partnerToQ = "";
			$scope.partnerToQDisabled = true;
			$scope.partnerToQRequired = false;
		}
	}
	
	function prepareViewScreen() {
		
		$scope.visaRequest = vtrService.getVisaRequestObj();
		
		console.log("View Action!");
		$scope.isAdd = false;
		$scope.heading = 'View - Visa Request';
		$scope.aManagerDisabled = true;
		
		prepareButtons(false, false, false);
		
		if($scope.visaRequest.inqueue === "EMPLOYEE") {
			prepareEmployeeBlock(true, true);
			prepareManagerBlock(false, true, false);
			prepareFinanceBlock(false, true, false);
			preparePartnerBlock(false, true, false, false);
		}
		else if($scope.visaRequest.inqueue === "MANAGER") {
			prepareEmployeeBlock(true, true);
			prepareManagerBlock(true, true, false);
			prepareFinanceBlock(false, true, false);
			preparePartnerBlock(false, true, false, false);
			if ($scope.visaRequest.partnercomments != "undefined"
				&& $scope.visaRequest.partnercomments != null
				&& $scope.visaRequest.partnercomments != "") {
				
				$scope.isPartnerBlockVisible = true;
			}
		}
		else if($scope.visaRequest.inqueue === "FINANCE") {
			prepareEmployeeBlock(true, true);
			prepareManagerBlock(true, true, false);
			prepareFinanceBlock(true, true, false);
			preparePartnerBlock(false, true, false, false);
			if ($scope.visaRequest.partnercomments != "undefined"
				&& $scope.visaRequest.partnercomments != null
				&& $scope.visaRequest.partnercomments != "") {
				
				$scope.isPartnerBlockVisible = true;
			}
		}
		else if($scope.visaRequest.inqueue === "PARTNER") {
			prepareEmployeeBlock(true, true);
			prepareManagerBlock(true, true, false);
			prepareFinanceBlock(true, true, false);
			preparePartnerBlock(true, true, false, false);
		}
		else {
			prepareEmployeeBlock(true, true);
			prepareManagerBlock(true, true, false);
			prepareFinanceBlock(true, true, false);
			preparePartnerBlock(true, true, false, false);
		}
	}

	$scope.selfOrOthersChange = function() {
		if($scope.visaRequest.selforothers) {
			setEmployeeDetails(vtrService.getLoggedInEmpObj());
		} else {
			$scope.employee.selected = undefined;
			setEmployeeDetails(null);
		}
	}
	
	$scope.empChange = function(item, model) {
		//console.log(item);
		setEmployeeDetails(item);
	};
	
	$scope.pActionChange = function() {
		console.log("$scope.partnerAction"+$scope.partnerAction);
		if($scope.partnerAction === 'Need More Details') {
			$scope.partnerToQDisabled = false;
			$scope.partnerToQRequired = true;
		}
		else {
			$scope.partnerToQ = "";
			$scope.partnerToQDisabled = true;
			$scope.partnerToQRequired = false;
		}
	};
	
	function setEmployeeDetails(obj) {
		if(obj != null) {
			$scope.visaRequest.travelersempid 		= obj.empid;
			$scope.visaRequest.travelersname 		= obj.empname;
			$scope.visaRequest.travelersmlevel 		= obj.mlevel;
			$scope.visaRequest.travelersdesignation	= obj.designation;
			$scope.visaRequest.travelersgender 		= obj.gender;
			$scope.visaRequest.travelersmailid 		= obj.mailid;
		}
		else {
			$scope.visaRequest.travelersempid 		= null;
			$scope.visaRequest.travelersname 		= null;
			$scope.visaRequest.travelersmlevel 		= null;
			$scope.visaRequest.travelersdesignation	= null;
			$scope.visaRequest.travelersgender 		= null;
			$scope.visaRequest.travelersmailid 		= null;
		}
		
	}
	
	$scope.clear = function() {
		if(confirm("Are you sure you want to Rest?")) {
			init();
		}
	};
	
	function validateAddForm() {
		if($scope.visaRequest.travelersempid === null || $scope.visaRequest.travelersempid === ""){
			alert("Travelers Emp. ID cannot be blank!");
			return false;
		}
		if($scope.visaRequest.selforothers){
			if($rootScope.loggedInEmpID != $scope.visaRequest.travelersempid){
				alert("Logged in User Emp. ID and Travelers Emp. ID should be same when Visa Request enetering for Self!");
				return false;
			}
		}
		else {
			if($rootScope.loggedInEmpID === $scope.visaRequest.travelersempid){
				alert("Logged in User Emp. ID and Travelers Emp. ID should not be same when Visa Request enetering for Others!");
				return false;
			}
		}
		return true;
	}
	
	function callService(saveAction) {
		console.log(angular.toJson($scope.visaRequest));
		var method = "";
        var url = "";
        
        method = "POST";
        url = restServiceUrl + 'createOrUpdateVisaRequest/'+$rootScope.loggedInEmpID+'/'+saveAction;

        $http({
            method : method,
            url : url,
            data : angular.toJson($scope.visaRequest),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then(function successCallback(response) {
        	goBack();
        }, function errorCallback(response) {
            alert(response.data.message);
        });
	}
      
	$scope.addEditVisaRequest = function(clickedAction) {
		if($stateParams.action == 'ADD') {
			if (!$scope.visaRequestForm.$valid) {
				alert("Enter the correct details to Add New - Visa Request!");
				return;
			}
			if(!validateAddForm()){
				alert("Enter the correct details to Add New - Visa Request!");
				return;
			}
			
			var saveAction  = '';
			var isConfirmed = true;
			if (clickedAction == 'DRAFT') {
				saveAction  = 'CREATE';
			}
			else if (clickedAction == 'SUBMIT') {
				saveAction  = 'CREATE_NEXT';
				isConfirmed = confirm("Are you sure you want to Submit?");
			}
			
			if (isConfirmed) {
				callService(saveAction);	
			}
			
		}
		else if ($stateParams.action == 'EDIT') {
			if ($scope.visaRequestForm.$valid) {
				//console.log(angular.toJson($scope.visaRequest));
				var saveAction  = '';
				var isConfirmed = true;
				if (clickedAction == 'DRAFT') {
					saveAction  = 'DRAFT';
					isConfirmed = confirm("Are you sure you want to Save as Draft?");
				}
				else if (clickedAction == 'SUBMIT') {
					saveAction  = 'NEXT';
					if($scope.visaRequest.inqueue === 'PARTNER') {
						if($scope.partnerAction == 'Need More Details'){
							if($scope.partnerToQ == 'Manager') {
								saveAction  = 'PARTNERtoMANAGER';
							}
							else if($scope.partnerToQ == 'Finance') {
								saveAction  = 'PARTNERtoFINANCE';
							}
						}
					}
					isConfirmed = confirm("Are you sure you want to continue?");
				}
				
				if (isConfirmed) {
					callService(saveAction);	
				}
			}
			else {
				console.log("Invalid form, cannot submit!");
			}
		}
    };
    
	$scope.goToVisaRequests = function() {
		
		var isConfirmed = false;
		if (!($stateParams.action == 'VIEW')) {
			if(confirm("Are you sure you want to exit this page?")) {
				isConfirmed = true;
			}
		}
		else {
			isConfirmed = true;
		}
		
		if(isConfirmed) {
			goBack();
		}
	}
	
	function goBack() {
		if (vtrService.getFromRequestOrAction() == 'vActions') {
			$state.go("visaRequestActions", {action: vtrService.getFromRequestOrAction()});
		}
		else {
			$state.go("visaRequests", {action: vtrService.getFromRequestOrAction()});
		}
	}
});

vtrApp.controller('travelRequestsController', function($scope) {
	$scope.heading = 'Travel Requests page - Work In Progress';
});

vtrApp.controller('travelRequestActionsController', function($scope) {
	$scope.heading = 'Travel Request Actions page - Work In Progress';
});


vtrApp.factory('AuthenticationService',
	function (vtrService, Base64, $http, $cookieStore, $rootScope, $timeout) {
	    var service = {};
	
	    service.Login = function (username, password, callback) {
	
	        /*
			 * Dummy authentication for testing, uses $timeout to simulate
			 * api call ----------------------------------------------
			 */
	        /*$timeout(function () {
	            var response = { success: username === 'test' && password === 'test' };
	            if (!response.success) {
	                response.message = 'Username or password is incorrect';
	            }
	            callback(response);
	        }, 1000);*/
	
	
	        /*
			 * Use this for real authentication
			 * ----------------------------------------------
			 */
	    	
	    	var isSuccess = false;
	    	$http({
	            method : 'GET',
	            url : restServiceUrl + 'authenticateAndGetEmp/'+username+'/'+password
	        }).then(function successCallback(response) {
	        	//console.log("response data="+response.data);
	        	vtrService.setLoggedInEmpObj(response.data);
	        	vtrService.loadAllRequired();
	        	
	        	callback({ success: true});

	        }, function errorCallback(response) {
	            console.log(response.statusText);
	            
	            var errorResponse = { success: false };
	            errorResponse.message = response.data.message;
	            
	            callback(errorResponse);
	        });
	    	
	    	
	    		
	        // $http.post('/api/authenticate', { username: username,
			// password: password })
	        // .success(function (response) {
	        // callback(response);
	        // });
	
	    };
	
	    service.SetCredentials = function (username, password) {
	    	
	    	$rootScope.isLoggedIn = true;
	    	
	        var authdata = Base64.encode(username + ':' + password);
	
	        $rootScope.globals = {
	            currentUser: {
	                username: username,
	                authdata: authdata
	            }
	        };
	
	        $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;
	        $cookieStore.put('globals', $rootScope.globals);
	    };
	
	    service.ClearCredentials = function () {
	    	vtrService.clearAllRequired();
	    	$rootScope.isLoggedIn = false;
	        $rootScope.globals = {};
	        $cookieStore.remove('globals');
	        $http.defaults.headers.common.Authorization = 'Basic ';
	    };
	
	    return service;
});

vtrApp.factory('Base64', function () {

    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';

    return {
        encode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);

            return output;
        },

        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                window.alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";

            } while (i < input.length);

            return output;
        }
    };

});