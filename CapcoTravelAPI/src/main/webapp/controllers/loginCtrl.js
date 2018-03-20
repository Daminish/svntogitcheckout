angular.module('myApp')
.controller("loginCtrl",function ($scope,$location,userAuthentication,jsonTransfer,travelRequest) {
	$scope.user={};
	$scope.login=function(){
<<<<<<< .mine
		console.log("user ",$scope.user);
		userAuthentication.loginUser($scope.user).then(function(res){
			jsonTransfer.setInfo(res.data)
			$location.path('/home');
		});
			
||||||| .r198
		userAuthentication.dummy().then(function(res){
		console.log("resp from jsonplaceholder",res.data[0]);
		$scope.z = res;
		jsonTransfer.setJson($scope.z);
		console.log('z',$scope.z)
		$location.path(`/home`);
	})
	
		
=======
		console.log("user ",$scope.user);
		userAuthentication.loginUser($scope.user).then(function(res){
			if(res.data.isApprover==true){
				userAuthentication.level=2;
				alert(userAuthentication.level)
			}
			jsonTransfer.setInfo(res.data)
			$location.path('/home');
		});
			
>>>>>>> .r216
	}

	$scope.returnHome = function(){
		$location.path('/home');
	}
})