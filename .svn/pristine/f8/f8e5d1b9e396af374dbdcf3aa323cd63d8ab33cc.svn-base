// create the controller for dashboard
app.controller('forgotPassController', function($rootScope, $scope, Auth, $http, $location) {

	console.log('forgotPassController');
	$rootScope.sign = "in"
	//$rootScope.mail = $scope.emailForgot;
	//console.log(userId);
	$scope.toLogin = function(){
		$location.path("/login");
	}
	$scope.forgotLink = function(email){
		console.log(email);
		$rootScope.mail = email;
		$http.get('/living/forgot/password?userId='+email).then(function(res) {
			console.log(res);
			//Auth.setUser("userAdmin");
			$rootScope.sign = "vl";
			
			$location.path("/validate");
		}, function(err) {
			console.log(err.data);
			$rootScope.mail = "";
		});
	}
	
	$scope.validateToken = function(token){
		console.log($rootScope.mail);
		var mail = $rootScope.mail;
		var tok = parseInt(token);
		var data = {
				email : mail,
				token : tok
		}
		console.log(data);
		$http.post('/living/forgot/validatetoken/', data).then(function(res) {
			console.log(res);
			//Auth.setUser("userAdmin");
			$rootScope.sign = "res"
			$location.path("/resetPass");
		}, function(err) {
			console.log(err.data);
		});
	}
	
	if($scope.password == $scope.repassword){
		$scope.resetPassword = function(password){
			console.log($rootScope.mail);
			var mail = $rootScope.mail;
			var pass = password;
			var data = {
					email : mail,
					password : pass
			}
			console.log(data);
			$http.put('/living/updatePassword/', data).then(function(res) {
				console.log(res);
				//Auth.setUser("userAdmin");
				$rootScope.sign = "in"
				$location.path("/login");
			}, function(err) {
				console.log(err.data);
			});
		}
	}else{
		$scope.password = "";
		$scope.repassword = "";
		alert("Password do not match");
	}
	
});