// create the controller for dashboard
app.controller('profileController', function($rootScope,$scope,Auth,$location) {
    // create a message
	console.log("profileController!!");
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
	$scope.name = $rootScope.user.data.User.firstName +' '+$rootScope.user.data.User.lastName;
	$scope.thaiId = $rootScope.user.data.User.thaiId;
	$scope.number = $rootScope.user.data.User.mobileNo;
	$scope.address = $rootScope.user.data.User.homeAddress;
	
    $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {

	    if(!value && oldValue) {
	      console.log("Disconnect");
	      $location.path('/login');
	    }

	    
    });
});
