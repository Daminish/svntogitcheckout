// create the controller for estimatebudget3Controller
app.controller('estimatebudget3Controller', function($rootScope,$scope,Auth,$location) {
	console.log("estimatebudget3Controller!!");
	$scope.morAmount = $rootScope.monthlyData.data.mortgage_amount;
	$scope.morTerm = $rootScope.monthlyData.data.mortgage_term;
	$scope.loan_mortgage = (($rootScope.monthlyData.data.emi * $scope.morTerm) * 65)/100;
	$scope.avl_balance = $rootScope.monthlyData.data.accountBalance;
	$scope.total_income = $rootScope.monthlyData.data.total_income;
	$scope.mon_income = ($scope.total_income /12).toFixed(2);
	console.log($rootScope.monthlyData);
    $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {

	    if(!value && oldValue) {
	      console.log("Disconnect");
	      $location.path('/login');
	    }

	    
    });
   
});