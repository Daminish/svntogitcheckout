angular.module('myApp')
.controller("homeCtrl",function ($scope,$state,$http,userAuthentication,jsonTransfer) {
	$scope.user={};
	$scope.user=jsonTransfer.getInfo();
	console.log("get Info ",$scope.user)
	userAuthentication.dummy().then(function(res){
		console.log("resp from jsonplaceholder",res.data[0]);
		$scope.a = res.data[0].userId;
		// jsonTransfer.setJson($scope.z);
	})

	$scope.newrequest=function(){
<<<<<<< .mine
		$state.go('newRequest');
		$http.get("http://localhost:8080/capco-travel-portal-webApp/approverAndProjectList/1").then(
||||||| .r198
		$state.go(`newRequest`);
		$http.get("http://localhost:8080/capco-travel-portal-webApp/approverAndProjectList/1").then(
=======
		$state.go('newRequest');
		$http.get("http://localhost:8080/capco-travel-portal-webApp/approverAndProjectList/"+userAuthentication.level).then(
>>>>>>> .r216
				function(res){
				$scope.projectList=res.data;
				console.log("projectList: ",$scope.projectList)
				},function(err){
					console.log(err)
				})
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

<<<<<<< .mine
.controller('approverController',['$scope','$location','travelRequest','firstApprover',function($scope,$location,travelRequest,firstApprover){
	 
	 $scope.empObj = {}
	 $scope.flightObj = {}
	 $scope.cabObj = {}
	 $scope.accomodationObj = {}
	 $scope.forexObj = {}
	$scope.requestToApproveDetails=travelRequest.request; 
	 $scope.flightObj=$scope.requestToApproveDetails.travelRequest.flightRequest;
		$scope.cabObj=$scope.requestToApproveDetails.travelRequest.cabRequest;
		$scope.accomodationObj=$scope.requestToApproveDetails.travelRequest.accomodationRequest;
		$scope.forexObj=$scope.requestToApproveDetails.travelRequest.forexRequest;
		$scope.currentStatus="";
			 $scope.rejectRemark="";
			 $scope.action="";
			$scope.forwardToApprove=function(requestId){
				$scope.l2Approver=16292;
				$scope.currentStatus="Pending L2";
				$scope.action="L1 Approved";
				$scope.userData={
						 requestId:requestId,
						 approverId: $scope.l2Approver,
						 currentStatus: $scope.currentStatus,
						 remark: $scope.rejectRemark,
						 actionOnRequest: $scope.action
				}
				firstApprover.sendRequest($scope.userData).then(function(res){
					console.log(res.data)
				},function(err){
					
				})
			}
			$scope.l1Reject=function(requestId){
				$scope.currentStatus="Rejected";
				$scope.action="L1 Rejected";
				$scope.l1Approver=16294;
					$scope.userData={
						 requestId:requestId,
						 approverId: $scope.l1Approver,
						 currentStatus: $scope.currentStatus,
						 remark: $scope.rejectRemark,
						 actionOnRequest: $scope.action
				}
				console.log($scope.userData);
			}
			firstApprover.sendRequest($scope.userData).then(function(res){
				console.log(res.data)
			},function(err){
				
			})
}])
||||||| .r198
.controller('requestController',function($scope,$location,firstApprover){
	$scope.forwardToApprove=function(){
		$scope.userData={
				 "requestId":20170723,
				 "approverId": 16292,
				 "currentStatus": "Pending L2",
				 "remark": "L1 Approved",
				 "actionOnRequest": ""
		}
		firstApprover.sendRequest(userData).then(function(res){
			console.log(res.data)
		},function(err){
			
		})
	}
})
=======
.controller('approverController',['$scope','$location','travelRequest','firstApprover',function($scope,$location,travelRequest,firstApprover){
	 
	 $scope.empObj = {}
	 $scope.flightObj = {}
	 $scope.cabObj = {}
	 $scope.accomodationObj = {}
	 $scope.forexObj = {}
	$scope.requestToApproveDetails=travelRequest.request; 
	 $scope.flightObj=$scope.requestToApproveDetails.travelRequest.flightRequest;
		$scope.cabObj=$scope.requestToApproveDetails.travelRequest.cabRequest;
		$scope.accomodationObj=$scope.requestToApproveDetails.travelRequest.accomodationRequest;
		$scope.forexObj=$scope.requestToApproveDetails.travelRequest.forexRequest;
		$scope.currentStatus="";
			 $scope.rejectRemark="";
			 $scope.action="";
			$scope.forwardToApprove=function(requestId){
				$scope.l2Approver=16292;
				$scope.currentStatus="Pending L2";
				$scope.action="L1 Approved";
				$scope.userData={
						 requestId:requestId,
						 approverId: $scope.l2Approver,
						 currentStatus: $scope.currentStatus,
						 remark: $scope.rejectRemark,
						 actionOnRequest: $scope.action
				}
				firstApprover.sendRequest($scope.userData).then(function(res){
					console.log(res.data)
				},function(err){
					
				})
			}
			$scope.l1Reject=function(requestId){
				$scope.currentStatus="Rejected";
				$scope.action="L1 Rejected";
				$scope.l1Approver=16294;
					$scope.userData={
						 requestId:requestId,
						 approverId: $scope.l1Approver,
						 currentStatus: $scope.currentStatus,
						 remark: $scope.rejectRemark,
						 actionOnRequest: $scope.action
				}
				console.log($scope.userData);
					firstApprover.sendRequest($scope.userData).then(function(res){
						console.log(res.data)
					},function(err){
						
					})
			}
			
}])
>>>>>>> .r216



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