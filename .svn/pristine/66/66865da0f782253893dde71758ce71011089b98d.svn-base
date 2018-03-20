// create the controller for Monthly budget
app.controller('monthlyexpensesController', function($rootScope,$scope,Auth,$location,$http) {
    // create a message
	console.log("monthlyexpensesController!!");
	console.log($rootScope.budget);
	$scope.budget = {
			monthlyExpenses : {
				creadit_card : 0,
				loan_rapayment : 0,
				electricity_bill : 0,
				cinema : 0,
				maintenance : 0,
				child_care : 0,
				fitness : 0,
				fuel : 0
			}
	};
	$scope.budget.total_income = $rootScope.total_income;
	$scope.budget.mortgage_amount = $rootScope.mortgage_amount;
	$scope.budget.mortgage_term = $rootScope.mortgage_term;
	$scope.total = function(){
		return (parseInt($scope.budget.monthlyExpenses.creadit_card) 
		+ parseInt($scope.budget.monthlyExpenses.loan_rapayment) 
		+ parseInt($scope.budget.monthlyExpenses.electricity_bill)
		+ parseInt($scope.budget.monthlyExpenses.cinema)
		+ parseInt($scope.budget.monthlyExpenses.maintenance)
		+ parseInt($scope.budget.monthlyExpenses.child_care)
		+ parseInt($scope.budget.monthlyExpenses.fitness)
		+ parseInt($scope.budget.monthlyExpenses.fuel));
	}
	
    $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {

	    if(!value && oldValue) {
	      console.log("Disconnect");
	      $location.path('/login');
	    }

	    
    });
    
    $scope.budgeting = function(){
    	console.log($scope.budget);
    	var data = $scope.budget;
    	if($scope.budget.monthlyExpenses.creadit_card && $scope.budget.monthlyExpenses.loan_rapayment && data){
    		$http.post('/living/budgeting/', data).then(function(res) {
    			console.log(res);
    			//Auth.setUser("userAdmin");
    			//$rootScope.sign = "vl"
    			$rootScope.monthlyData = res;
    			$location.path("/estimatebudget3");
    		}, function(err) {
    			console.log(err.data);
    		});
    	}else{
    		alert("Please fill empty fields");
    	}
    	
    }
});