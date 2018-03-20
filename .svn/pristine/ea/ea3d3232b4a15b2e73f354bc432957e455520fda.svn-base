angular.module('myApp')
.controller("loginCtrl",function ($scope,$location,userAuthentication,jsonTransfer,travelRequest,$mdDialog) {
	$scope.user={};
	$scope.login=function(){
		
		$scope.showLoading = true;
		console.log("user ",$scope.user);
		
		userAuthentication.loginUser($scope.user).then(function(res){
			console.log("Login response ",res.data)
			jsonTransfer.setInfo(res.data)
			if(res.data!=""){
				
				$scope.showLoading = false;
				$location.path('/home');
			}
			else{
				$scope.showAlert = function(ev) {
	        // Appending dialog to document.body to cover sidenav in docs app
	        // Modal dialogs should fully cover application
	        // to prevent interaction outside of dialog
	        $mdDialog.show(
	          $mdDialog.alert()
	            .parent(angular.element(document.querySelector('#popupContainer')))
	            .clickOutsideToClose(false)
	            .title('Login Failed')
	            .textContent('Please enter valid username or password')
	            .ariaLabel('Alert Dialog Demo')
	            .ok('Try Again')
            	.targetEvent(ev)
	            
	        );
	      };
//			      $scope.user.capcoUserId = "";
//			      $scope.user.password = "";
				//alert("Login Failed")
				
				
			}
			
		},function(err){
			
		});
		

			
	}

	/*$scope.returnHome = function(){
		$location.path('/home');
	}*/
})