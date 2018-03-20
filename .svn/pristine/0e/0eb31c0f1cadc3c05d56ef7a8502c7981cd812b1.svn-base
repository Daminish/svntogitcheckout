angular.module('myApp')

.controller("headerCtrl",['$scope','$http','$state','userAuthentication','jsonTransfer',function($scope,$http,$state,userAuthentication,jsonTransfer){
	$scope.user={};
	$scope.user=jsonTransfer.getInfo();
	
	//var jsonObj=jsonTransfer.getHeaderInfo();
	/*console.log(jsonObj,"headerCtrl")
	$scope.userName=jsonObj;*/
	
	var originatorEv;

    $scope.openMenu = function($mdMenu, ev) {
      originatorEv = ev;
      $mdMenu.open(ev);
    };

	$scope.logout=function(){
		userAuthentication.logoutUser().then(function(res){
			console.log("logout message ",res.data)
			$state.go("login")
		},function(err){
			
			console.log("logout error ",err)
		})
	}
	
	$scope.home = function(){
		$state.go('home');
	}
}])


.controller("homeCtrl",function ($scope,$state,$http,$filter,userAuthentication,jsonTransfer,travelRequest,requestTypeParameterTransfer) {
	$scope.user={};
	$scope.user=jsonTransfer.getInfo();
	console.log("get Info ",$scope.user)
	userAuthentication.requestCount($scope.user.employeeId).then(function(res){
		console.log("resp from jsonplaceholder",res.data);
		$scope.count = res.data.requestCountVO;
		// jsonTransfer.setJson($scope.z);
	},function(err){
		alert("Enter valid credentials");
		$state.go('login')
	})

	$scope.tripsObj={};
	$scope.countabcd = +0;
	$scope.accomodationFlag=false;
	$scope.flightFlag=false;
	$scope.cabFlag=false;
	$scope.iconVisible = "display_visible";
	$scope.iconInvisible = "display_hidden";
	userAuthentication.requestCount($scope.user.employeeId).then(function(trips){
//		console.log(trips.data.mainRequestVOList,"trips");
		$scope.tripsObj=trips.data.mainRequestVOList;


		console.log($scope.user.employeeId);
		var count=-1;	
		angular.forEach($scope.tripsObj,function(obj){
			count++;
					})
		console.log(count);
		$scope.i;
		for(i=0;i<=count;i++)
		{
			
			if($scope.tripsObj[i].accomodationRequest!=null)
				{
					$scope.accomodationIconClass = "display_visible";
					$scope.tripsObj[i].accomodationRequest.category;
				$scope.tripsObj[i].accomodationRequest.createdOn=new Date($scope.tripsObj[i].accomodationRequest.createdOn);
					$scope.createdOn=$filter('date')($scope.tripsObj[i].accomodationRequest.createdOn, 'MMM d, y h:mm a');
					$scope.accomodationFlag=true;
					console.log($scope.accomodationFlag,"!null");
//					console.log($scope.createdOn);
				}
//			else{
//				$scope.accomodationFlag=false;
//				console.log($scope.accomodationFlag,"Null");
//			}
			if($scope.tripsObj[i].cabRequest!=null)
			{
				$scope.cabIconClass="display_visible";
				$scope.tripsObj[i].cabRequest.createdOn=new Date($scope.tripsObj[i].cabRequest.createdOn);
				$scope.createdOn=$filter('date')($scope.tripsObj[i].cabRequest.createdOn, 'MMM d, y h:mm a');
				$scope.cabFlag=true;
//				console.log($scope.createdOn);
			}
//			else{
//				$scope.cabFlag=false;
//			}
			if($scope.tripsObj[i].flightRequest!=null)
			{
				$scope.flightIconClass="display_visible";
				$scope.tripsObj[i].flightRequest.createdOn=new Date($scope.tripsObj[i].flightRequest.createdOn);
				$scope.createdOn=$filter('date')($scope.tripsObj[i].flightRequest.createdOn, 'MMM d, y h:mm a');
				$scope.flightFlag=true;
//				console.log($scope.createdOn);
			}
//			else{
//				$scope.flightFlag=false;
//			}
			if($scope.tripsObj[i].forexRequest!=null)
			{
				$scope.forexIconClass="display_visible";
				$scope.tripsObj[i].forexRequest.createdOn=new Date($scope.tripsObj[i].forexRequest.createdOn);
				$scope.createdOn=$filter('date')($scope.tripsObj[i].forexRequest.createdOn, 'MMM d, y h:mm a');
				$scope.forexFlag=true;
//				console.log($scope.createdOn);
			}
//			else{
//				$scope.forexFlag=false;
//			}
			if($scope.tripsObj[i].visaRequestVO!=null)
			{
				$scope.tripsObj[i].visaRequestVO.createdOn=new Date($scope.tripsObj[i].visaRequestVO.createdOn);
				$scope.createdOn=$filter('date')($scope.tripsObj[i].visaRequestVO.createdOn, 'MMM d, y h:mm a');
				$scope.visaFlag=true;
//				console.log($scope.createdOn);
			}
//			else{
//				$scope.visaFlag=false;
//			}
		
			
			
		}
		
	},function(err){
		if(err.status==400){
			console.log(err.data)
			$state.go('login')
		}
		console.log(err,"eroooooooorrrrrrrrrrrr");
	})
	

	$scope.getRequestDetails=function(requestId){
    	travelRequest.getRequestDetails(requestId).then(function(res){
    		$scope.requestDetails=res.data;
    		$scope.flightObj=$scope.requestDetails.travelRequest.flightRequest;
    		$scope.cabObj=$scope.requestDetails.travelRequest.cabRequest;
    		$scope.accomodationObj=$scope.requestDetails.travelRequest.accomodationRequest;
    		$scope.forexObj=$scope.requestDetails.travelRequest.forexRequest;
    		$scope.visaObj=$scope.requestDetails.travelRequest.visaRequestVO;

    		console.log("request Details ",$scope.requestDetails);
    	},function(err){
    		if(err.status==400){
    			console.log(err.data)
    			$state.go('login')
    		}
    	})
    }
	
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
	$scope.requestDetailsByType=function(requestDetails){
		$scope.requestDetailsType=requestDetails;
		requestTypeParameterTransfer.setInfo($scope.requestDetailsType);
		console.log($scope.requestDetailsType,"$scope.requestDetailsType");
		$state.go('myrequests');
	}
	console.log("Z=",$scope.z)
	// var jsonObj=jsonTransfer.getJson();
	// $scope.a = jsonObj[0].userId;


	// console.log(self.a,"dashboardCtrl Json");

		/*$(document).ready( function(){
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
	});*/
		
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
	
	 $scope.billable=[{type:"Billable"},{type:"Non-Billable"}]
	        $scope.getBillable=function(){
		 return $scope.billable;
	 }
	 $scope.cell={
			 evaluator:"Billable"
	 }

	 $scope.projectList = travelRequest.approverAndProjectList;

	 $scope.requestToApproveDetails=travelRequest.request; 
	 $scope.flightObj=$scope.requestToApproveDetails.travelRequest.flightRequest;
		$scope.cabObj=$scope.requestToApproveDetails.travelRequest.cabRequest;
		$scope.accomodationObj=$scope.requestToApproveDetails.travelRequest.accomodationRequest;
		$scope.forexObj=$scope.requestToApproveDetails.travelRequest.forexRequest;
		$scope.visaObj=$scope.requestToApproveDetails.travelRequest.visaRequestVO;
		$scope.currentStatus;
			 $scope.rejectRemark="";
			 $scope.action;
			 $scope.secondApprover={};
//			 $scope.secondApprover.name={};
//			 $scope.secondApprover.name.approverId;
			 
			 $scope.forwardToApprove=function(requestId){
				 $scope.userData={}
				$scope.billable;
				$scope.currentStatus="L2 Pending";
				$scope.userData={
						 requestId:requestId,
						 currentStatus: $scope.currentStatus,
						 remark: $scope.rejectRemark,
//						 billable: true
				}
				
				//console.log("first billable",$scope.userData.billable)
		if($scope.cell.evaluator=="Billable"){
			$scope.userData.billable=true;
		}		
		else{
			$scope.userData.billable=false;
		}
				$scope.userData.actionOnRequest="Approved";
				$scope.userData.approverId=$scope.secondApprover.name.approverId;
				// $scope.userData.billable=""//$scope.billable;
				console.log("userData ",$scope.userData,"Billable ",$scope.billable)
				firstApprover.sendRequest($scope.userData).then(function(res){
					console.log("first approver",res.data);
					$state.go('pendingrequests');
				},function(err){
					if(err.status==400){
						console.log(err.data)
						$state.go('login')
					}
				})
			}
				
				
				$scope.l1Reject=function(requestId){
					$scope.currentStatus="Rejected";
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
							if(err.status==400){
								console.log(err.data)
								$state.go('login')
							}
						})
				}
				
// Second level approve and reject
				$scope.secondLevelApproveRequest=function(requestId){
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
						if(err.status==400){
							console.log(err.data)
							$state.go('login')
						}
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
						if(err.status==400){
							console.log(err.data)
							$state.go('login')
						}
					})
				}
				
				$scope.requestList = function(){
					$state.go('pendingrequests');
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