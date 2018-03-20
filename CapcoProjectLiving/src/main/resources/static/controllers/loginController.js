// create the controller for dashboard
app.controller('loginController', function($rootScope,$scope, Auth, $http, $location) {

	console.log($scope.userName);

	var uname = $scope.userName;
	var pass = $scope.password;
	$scope.login = function(uname, pass) {
		var data = {
			email : uname,
			password : pass
		}
		
		if(data.email){
			//console.log("if");
			$http.post('/living/login', data).then(function(res) {
				console.log(res);
				$rootScope.user = res;
				console.log($rootScope.user)
				Auth.setUser("userAdmin");
				$location.path("/dashboard");
			}, function(err) {
				console.log(err.data);
			});
		}else{
			alert("Enter username and password");
		}
		
	}
	
	$scope.signup = function(){
		$rootScope.sign = "up";
		$location.path("/signup");
	}
	
	$scope.forgotPass = function(){
		$rootScope.sign = "fp";
		$location.path("/forgot");
	}
});