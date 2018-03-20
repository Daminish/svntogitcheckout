angular.module('myApp')

.controller("headerCtrl",['$scope','$http','$state','userAuthentication','jsonTransfer',function($scope,$http,$state,userAuthentication,jsonTransfer){
	$scope.user={};
	$scope.user=jsonTransfer.getInfo();
	
	//var jsonObj=jsonTransfer.getHeaderInfo();
	/*console.log(jsonObj,"headerCtrl")
	$scope.userName=jsonObj;*/

	$scope.logout=function(){
		userAuthentication.logoutUser().then(function(res){
			console.log("logout message ",res.data)
			$state.go("login")
		},function(err){
			console.log("logout error ",err)
		})
	}
}])


.controller("homeCtrl",function ($scope,$state,$http,userAuthentication,jsonTransfer) {
	$scope.user={};
	$scope.user=jsonTransfer.getInfo();
	console.log("get Info ",$scope.user)
	userAuthentication.requestCount($scope.user.employeeId).then(function(res){
		console.log("resp from jsonplaceholder",res.data);
		$scope.count = res.data;
		// jsonTransfer.setJson($scope.z);
	},function(err){
		alert("Enter valid credentials");
		$state.go('login')
	})

//	console.log(userLgData);
	
	$scope.newrequest=function(){
		$state.go('newRequest');
		
	}
	$scope.requestList=function(){
		$state.go('pendingrequests')
	}
	


	$scope.myRequestList = function(){
		$state.go('myrequests');
	}
	console.log("Z=",$scope.z)
	// var jsonObj=jsonTransfer.getJson();
	// $scope.a = jsonObj[0].userId;


	// console.log(self.a,"dashboardCtrl Json");

		$(document).ready( function(){
  		var cTime = new Date(), month = cTime.getMonth()+1, year = cTime.getFullYear();
  		console.log("cTime:",cTime);
		theMonths = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

		theDays = ["S", "M", "T", "W", "T", "F", "S"];
	    events = [
	      [
	        "1/"+"/"+month+"/"+year, 
	        'Meet a friend', 
	        '#', 
	        '#177bbb', 
	        'Contents here'
	      ],
	      [
	        "7/"+month+"/"+year, 
	        'Kick off meeting!', 
	        '#', 
	        '#1bbacc', 
	        'Have a kick off meeting with .inc company'
	      ],
	      [
	        "17/"+month+"/"+year, 
	        'Milestone release', 
	        '#', 
	        '#fcc633', 
	        'Contents here'
	      ],
	      [
	        "19/"+month+"/"+year, 
	        'A link', 
	        'http://www.google.com', 
	        '#e33244'
	      ]
	    ];
	    $('#calendar').calendar({
	        months: theMonths,
	        days: theDays,
	        events: events,
	        popover_options:{
	            placement: 'top',
	            html: true
	        }
	    });
	});
		
})

.controller('approverController',['$scope','$state','$location','travelRequest','firstApprover','userAuthentication','jsonTransfer',function($scope,$state,$location,travelRequest,firstApprover,userAuthentication,jsonTransfer){
	 $scope.level = userAuthentication.level;
	 $scope.user={};
 	 $scope.user=jsonTransfer.getInfo();
	 $scope.empObj = {}
	 $scope.flightObj = {}
	 $scope.cabObj = {}
	 $scope.accomodationObj = {}
	 $scope.forexObj = {}
	 
	 $scope.akshay;
	 

	 $scope.projectList = travelRequest.approverAndProjectList;

	 $scope.requestToApproveDetails=travelRequest.request; 
	 $scope.flightObj=$scope.requestToApproveDetails.travelRequest.flightRequest;
		$scope.cabObj=$scope.requestToApproveDetails.travelRequest.cabRequest;
		$scope.accomodationObj=$scope.requestToApproveDetails.travelRequest.accomodationRequest;
		$scope.forexObj=$scope.requestToApproveDetails.travelRequest.forexRequest;
		$scope.currentStatus;
			 $scope.rejectRemark="";
			 $scope.action;
			 $scope.secondApprover={};
			 $scope.secondApprover.name={};
			 $scope.secondApprover.name.approverId;
			 
			 
			 $scope.forwardToApprove=function(requestId){
				$scope.currentStatus="Pending L2";
				$scope.akshay;
				$scope.userData={
						 requestId:requestId,
						 currentStatus: $scope.currentStatus,
						 remark: $scope.rejectRemark,
						 billable: $scope.akshay
				}
				console.log("first billable",$scope.akshay)
				
				$scope.userData.actionOnRequest="Approved";
				$scope.userData.approverId=$scope.secondApprover.name.approverId;
				console.log("userData ",$scope.userData,"Billable ",$scope.akshay)
				firstApprover.sendRequest($scope.userData).then(function(res){
					console.log("first approver",res.data);
					$state.go('pendingrequests');
				},function(err){
					
				})
			}
				
				
				$scope.l1Reject=function(requestId){
					$scope.currentStatus="L1 Rejected";
					$scope.action="Rejected";
					$scope.l1Approver=$scope.user.employeeId;
						$scope.userData={
							 requestId:requestId,
							 approverId: $scope.l1Approver,
							 currentStatus: $scope.currentStatus,
							 remark: $scope.rejectRemark,
							 actionOnRequest: $scope.action,
							 
					}
						$scope.userData.billable;
						console.log($scope.userData);
						firstApprover.sendRequest($scope.userData).then(function(res){
							console.log(res.data);
							$state.go('pendingrequests');
						},function(err){
							
						})
				}
				
// Second level approve and reject
				$scope.secondLevelApproveRequest=function(requestId){
					alert("second level")
					$scope.currentStatus="Approved";
					$scope.action="Approved";
					$scope.userData={
							 requestId:requestId,
							 currentStatus: $scope.currentStatus,
							 remark: $scope.rejectRemark,
							 actionOnRequest: $scope.action
					}
					$scope.userData.billable;
					$scope.userData.approverId=$scope.user.employeeId;
					firstApprover.sendRequest($scope.userData).then(function(res){
						console.log("second approver",res.data);
						$state.go('pendingrequests');
					},function(err){
						
					})
				}
				
				$scope.secondLevelRejectRequest=function(requestId){
					$scope.currentStatus="Rejected";
					$scope.action="Rejected";
					$scope.userData={
							 requestId:requestId,
							 currentStatus: $scope.currentStatus,
							 remark: $scope.rejectRemark,
							 actionOnRequest: $scope.action
					}
					$scope.userData.billable;
					$scope.userData.approverId=$scope.user.employeeId;
					firstApprover.sendRequest($scope.userData).then(function(res){
						console.log("second approver",res.data);
						$state.go('pendingrequests');
					},function(err){
						
					})
				}
			
			
}])



//Don't need it now because of ui-routing so we can remove the below state.go code
//=============================
// .controller('requestController',function($scope,$state){
// 	// $location.path('/myrequests/personalDetails.html')
// 	$scope.showPersonalDetails = function(){
// 		$state.go('home.newrequest.personalDetails');
// 	}
// 	$scope.showRequestDetails = function(){
// 		$state.go('home/newrequest/requestDetails');
// 	}
// 	$scope.showConfirmPage = function(){
// 		$state.go('home/newrequest/confirm');
// 	}
// })



// .controller('masterCtrl',function($scope,$location,userAuthentication,jsonTransfer){
// 	$scope.login=function(){
// 		userAuthentication.dummy().then(function(res){
// 		console.log("resp from jsonplaceholder",res.data[0]);
// 		$scope.z = res;
		
// 		jsonTransfer.setJson($scope.z);
// 		console.log('z',$scope.z)
// 	})
// 		$location.path(`/home`);
// 	}
// })