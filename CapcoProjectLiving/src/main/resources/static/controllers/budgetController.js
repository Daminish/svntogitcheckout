// create the controller for dashboard
app.controller('budgetController', function($scope,Auth,$location) {
    console.log("Budget");
    $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {

	    if(!value && oldValue) {
	      console.log("Disconnect");
	      $location.path('/login');
	    }

	    
});
});