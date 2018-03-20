// create the controller for dashboard
app.controller('estimatebudgetController', function($rootScope,$scope,Auth,$location, $http) {
	console.log("estimatebudgetController!!");
	$scope.mortgage_amount;
	$scope.mortgage_term;
	
	$scope.setVal = function(){
		
		var amount = $scope.mortgage_amount;
		var term = $scope.mortgage_term;
		if(amount && term && $scope.thaiId1){
			$rootScope.mortgage_amount = amount;
			$rootScope.mortgage_term = term;
			$location.path("/estimatebudget2");
		}else{
			alert("Please fill empty feild");
		}
		
		
	}
	
	
	/*$rootScope.mortgage_amount = $scope.budget.mortgage_amount;
	$rootScope.mortgage_term = $scope.budget.mortgage_term;*/
	
    $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {

	    if(!value && oldValue) {
	      console.log("Disconnect");
	      $location.path('/login');
	    }

	    
    });
    
   
   
});