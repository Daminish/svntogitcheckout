// create the controller for estimatebudgetController
app.controller('estimatebudget2Controller', function($rootScope, $scope, Auth,
		$location) {
	console.log("estimatebudget2Controller!!");
	$scope.total_income;
	$scope.monthIncome = function(){
		console.log("changed");
		if(!$scope.total_income){
			var point1 = (0 + $scope.other_income)/12;
			$scope.monthly_income = Math.floor(point1 * 100) / 100;
		}else if(!$scope.other_income){
			var point2 = ($scope.total_income + 0)/12;
			$scope.monthly_income = Math.floor(point2 * 100) / 100;
		}else{
			var point = ($scope.total_income + $scope.other_income)/12;
			$scope.monthly_income = Math.floor(point * 100) / 100;
		}
		
	}
	
	$scope.setVal = function() {

		var total_income = parseInt($scope.total_income) + parseInt($scope.other_income) + parseInt($scope.monthly_income);
		
		if (total_income && $scope.other_income && $scope.monthly_income) {
			$rootScope.total_income = total_income;
			$location.path("/monthlyexpences");
		} else {
			alert("Please fill empty feild");
		}

	}

	$scope.$watch(Auth.isLoggedIn, function(value, oldValue) {

		if (!value && oldValue) {
			console.log("Disconnect");
			$location.path('/login');
		}

	});

});