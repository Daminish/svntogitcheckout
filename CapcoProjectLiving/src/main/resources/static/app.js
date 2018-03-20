//create the angular module
var app = angular.module('myApp',['ngRoute']);

//configure routing
app.config(function($routeProvider){
    $routeProvider

    //for home
    .when('/login',{
        templateUrl : 'views/login.html',
        controller : 'loginController'
    })

    //for profile
    .when('/dashboard',{
        templateUrl : 'views/dashboard.html',
        controller : 'dashboardController'
    })

    //for registration
    .when('/budget',{
        templateUrl : 'views/budget.html',
        controller : 'budgetController'
    })

      //for estimate budget
    .when('/estimatebudget',{
        templateUrl : 'views/estimatebudget.html',
        controller : 'estimatebudgetController'
    })
    
        //for estimatebudget2
    .when('/estimatebudget2',{
        templateUrl : 'views/estimatebudget2.html',
        controller : 'estimatebudget2Controller'
    })
    
    .when('/estimatebudget3',{
        templateUrl : 'views/estimatebudget3.html',
        controller : 'estimatebudget3Controller'
    })
    
    
        //for monthlyexpenses
    .when('/monthlyexpences',{
        templateUrl : 'views/monthlyexpenses.html',
        controller : 'monthlyexpensesController'
    })
    
    
        //for costs
    .when('/costs',{
        templateUrl : 'views/costs.html',
        controller : 'registerController'
    })
    
     //for debit payment
    .when('/debitpayment',{
        templateUrl : 'views/debitpayment.html',
        controller : 'debitController'
    })
    //For profile
    .when('/profile',{
        templateUrl : 'views/profile.html',
        controller : 'profileController'
    })
    
    //For signup
    .when('/signup',{
        templateUrl : 'views/signup.html',
        controller : 'signupController'
    })
    
    //For Forgot Password
    .when('/forgot',{
        templateUrl : 'views/forgotpassword.html',
        controller : 'forgotPassController'
    })
    
    //For Forgot Password
    .when('/resetPass',{
        templateUrl : 'views/resetpassword.html',
        controller : 'forgotPassController'
    })
    
    //For Forgot Password
    .when('/validate',{
        templateUrl : 'views/validateToken.html',
        controller : 'forgotPassController'
    })
    
    .when('/findhome',{
        templateUrl : 'views/findhome.html'
    })
    
    .when('/areaguide',{
        templateUrl : 'views/areaguide.html',
        controller : 'areaguideController'
    })

    .otherwise({redirectTo: '/'});
})


app.run(['$rootScope', '$location', 'Auth', function ($rootScope, $location, Auth) {
    $rootScope.$on('$routeChangeStart', function (event) {

        if (!Auth.isLoggedIn()) {
            console.log('DENY');
            console.log($rootScope.sign);
            
            //event.preventDefault();
            if($rootScope.sign == "up"){
            	$location.path('/signup');
            }else if($rootScope.sign == "fp"){
            	
            	$location.path('/forgot');
            	//$rootScope.sign = "in";
            }else if($rootScope.sign == "res"){
            	console.log('reses');
            	$location.path('/resetPass');
            	//$rootScope.sign = "in";
            }else if($rootScope.sign == "vl"){
            	console.log('val');
            	$location.path('/validate');
            	//$rootScope.sign = "in";
            }else{
            	$location.path('/login');
            }
            
        }
        else {
            console.log('ALLOW');
        }
    });
}]);

app.factory('Auth', function(){
	var user;
	//console.log("hi admin "+user);
	return{
	    setUser : function(aUser){
	    	//console.log("hi setUser "+user);
	    	window.localStorage.setItem('session', 'on');
	        user = aUser;
	    },
	    isLoggedIn : function(){
	        return(window.localStorage.getItem('session'))? true : false;
	    }
	  }
	});

app.filter('num', function() {
    return function(input) {
       return parseInt(input, 10);
    }
});
