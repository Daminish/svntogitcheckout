angular.module('myApp')
	.config(['$urlRouterProvider', '$stateProvider','$locationProvider', function ($urlRouterProvider, $stateProvider,$locationProvider) {
		
		
		$stateProvider
			.state('login', {
				url: '/',
				templateUrl: 'views/loginTemplate.html',
				controller: 'loginCtrl'
			})

			.state('home', {
				url: '/home',
				templateUrl: 'views/homeTemplate.html',
//				resolve:{
//					userLgData:['jsonTransfer',function(jsonTransfer){
//						return jsonTransfer.getInfo()
//					}],
//					userData:['userAuthentication','$scope',function(userAuthentication,$scope){
//							userAuthentication.requestCount(userLgData.employeeId).then(function(res){
//							console.log("Request Count ",res.data);
//							$scope.count = res.data;
//							// jsonTransfer.setJson($scope.z);
//						})
//					}]
//					},
				controller: 'homeCtrl'
			})
			
			.state('newRequest', {
				url: '/newRequest',
				templateUrl: 'views/newRequest.html'
//				controller: 'newRequestCtrl'
			})
			// .state('home.newRequest.personalDetails', {
			//   url: '/personalDetails',
			//   templateUrl:'views/personalDetails.html',
			//   })

			.state('myrequests', {
				url: '/myrequests',
				templateUrl: 'views/myRequestsList.html',
				controller: 'requestListCtrl'
			})
			.state('approval', {
				url: '/approval',
				templateUrl: 'views/newRequest.html',
				controller: 'newRequestCtrl'
			})
			.state('pendingrequests', {
				url: '/pendingRequests',
				templateUrl: 'views/requestList.html',
				controller: 'newRequestCtrl'
			})
			.state('actionOnRequest',{
				url: '/level1Approve',
				templateUrl: 'views/approveRequest.html',
				controller: 'approverController'
			})
			.state('editRequest', {
				url: '/editRequest',
				templateUrl: 'views/editRequest.html',
//				controller: 'editRequestCtrl'
			})
		   $urlRouterProvider.otherwise('/'); 
			//$locationProvider.html5Mode(true);
	}])

	.component('headerComponent', {
		templateUrl: 'views/headerTemplate.html',
		controller:'headerCtrl'
	})
	
	.component('loginHeaderComponent', {
		templateUrl: 'views/loginHeaderTemplate.html'
	})	 
 

	.component('homeComponent', {
		templateUrl: 'views/homeTemplate.html',
		controller: 'homeCtrl'
	})
	.component('myRequestComponent', {
		template: '<h1>myRequestComponent</h1>'
	})
	.component('newRequestComponent', {
		templateUrl: '../views/newRequest.html',

		// $routeConfig:[
		// 	{path:'/personalDetails', component:'personalDetailsComponent',useAsDefault:true},
		// 	{path:'/requestDetails', component:'requestDetailsComponent'},
		// 	{path:'/confirm', component:'confirmComponent'}
		// 	],
		controller: 'newRequestCtrl'
	})
	.component('personalDetailsComponent', {
		templateUrl: '../views/tabs.html',
		//controller: 'newRequestCtrl'
	})
	.component('requestDetailsComponent', {
		templateUrl: '../views/requestDetails.html',
		controller: 'newRequestCtrl'
	})
	.component('confirmComponent', {
		templateUrl: '../views/confirmView.html',
		controller: 'newRequestCtrl'
	})
	.component('requestListComponent', {
		templateUrl: 'views/requestList.html',
		controller: 'newRequestCtrl'
	})
	.component('approvalComponent', {
		templateUrl: 'views/confirmView.html',
	})
	.component('requestListComponent', {
		templateUrl: '../views/requestList.html',
		controller: 'newRequestCtrl'
	})


	.component('footerComponent', {
		templateUrl: 'views/footerTemplate.html'
	})
	// .value('$routerRootComponent','masterComponent')
	// .component('masterComponent',{
	// 	template:'<ng-outlet></ng-outlet>',
	// 	$routeConfig:[
	// 	{path:'/login', component:'loginComponent',useAsDefault:true},
	// 	{path:'/home/...',component:'homeComponent'}
	// 	]
	// })
	.component('loginComponent', {
		templateUrl: 'views/loginTemplate.html',
		controller: 'loginCtrl'
	})
	.component('personalDetailsComponent', {
		templateUrl: 'views/firstTab.html'
	})

// .component('homeComponent',{
// 	template:'<div><header-component></header-component></div>
// 	<div>
// 	<div ui-view></div>
// 	</div>
// 	<div>
// 	<footer-component></footer-component></div>'
// 	// $routeConfig:[
// 	// {path:'/', component:'dashboardComponent'},
// 	// {path:'/myrequests', component:'myRequestComponent'},
// 	// {path:'/newrequest/...', component:'newRequestComponent'},
// 	// {path:'/approval', component:'approvalComponent'},
// 	// {path:'/reports', component:'reportsComponent'}
// 	// ]
// })