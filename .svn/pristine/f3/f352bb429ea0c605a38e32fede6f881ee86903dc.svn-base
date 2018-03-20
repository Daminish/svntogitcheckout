// create the controller for dashboard
app.controller('dashboardController', function($rootScope,$scope,Auth,$location) {
    // create a message
	console.log("dashboard!!");
	/*var today = new Date()
	var curHr = today.getHours()
	console.log(curHr);
	if (curHr < 12) {
	  console.log('good morning');
	  $scope.grt = 'Good Morning';
	} else if (curHr < 18) {
	  console.log('good afternoon');
	  $scope.grt = 'Good afternoon';
	} else {
	  console.log('good evening');
	  $scope.grt = 'Good evening';
	}*/
	console.log($rootScope.user);
	$scope.name = $rootScope.user.data.User.firstName;
	$scope.logout = function(){
		window.localStorage.setItem('session', '');
		$rootScope.user = {};
		$location.path('/login');
	}
    $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {

	    if(!value && oldValue) {
	      console.log("Disconnect");
	      $location.path('/login');
	    }

	    
    });
});
