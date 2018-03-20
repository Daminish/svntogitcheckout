// create the controller for debitController
app.controller('debitController', function($scope,Auth,$location) {
    // create a message
	console.log("debitController!!");
    $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {

	    if(!value && oldValue) {
	      console.log("Disconnect");
	      $location.path('/login');
	    }

	    
});
});