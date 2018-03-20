// create the controller for dashboard
app.controller('signupController', function($rootScope, $scope, Auth, $http, $location) {

	console.log('signupController');
	$rootScope.sign = "in"
	
	//console.log(userId);
	$scope.register = function() {
		console.log($scope.uName);
		var data = {
				userId:$scope.uName,
				password:$scope.pass,
				firstName:$scope.fName,
				lastName:$scope.lName,
				userTypeName:$scope.uType,
				mobileNo:$scope.cNum,
				thaiId:$scope.thaiId,
				homeAddress:$scope.hAdd,
				workAddress:$scope.wAdd
				}
		console.log(data);
		$http.post('/living/register', data).then(function(res) {

			//Auth.setUser("userAdmin");
			$location.path("/login");
		}, function(err) {
			console.log(err.data);
		});
	}
});