angular.module('myApp')
.controller("loginCtrl",function ($scope,$location,userAuthentication,jsonTransfer,travelRequest) {
	$scope.user={};
	$scope.login=function(){
		$scope.showLoading = true;
		console.log("user ",$scope.user);
		userAuthentication.loginUser($scope.user).then(function(res){
			jsonTransfer.setInfo(res.data)
			if(res.data!=""){
				$scope.showLoading = false;
				$location.path('/home');
			}
			else{
				alert("Enter valid credentials");
				$location.path('/');
			}
			
		},function(err){
			
			
			
		});
		

			
	}

	/*$scope.returnHome = function(){
		$location.path('/home');
	}*/
})