angular.module('myApp')
.directive('calendar',function () {
	return{
		restrict:`E`,
		replace:true,
		templateUrl:`../capco-travel-portal-webApp/views/calendarTemplate.html`
		
	};

});