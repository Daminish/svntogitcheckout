app.directive('validationError', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            scope.$watch(attrs['validationError'], function (errMsg) {
                if (elem[0] && elem[0].setCustomValidity) { // HTML5 validation
                    console.log("Error");
                    elem[0].setCustomValidity(errMsg);
                }
                if (ctrl) { // AngularJS validation
                    ctrl.$setValidity('validationError', errMsg ? false : true);
                }
            });
        }
    }
});

app.directive('myDirective', function(authuser) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, mCtrl) {
            function myValidation(value) {
                if (value.indexOf("e") > -1) {
                    mCtrl.$setValidity('charE', true);
                } else {
                    mCtrl.$setValidity('charE', false);
                }
                console.log(authuser.multiply(20,5));
                return value;
            }
            
            mCtrl.$parsers.push(myValidation);
        }
    };
  });

  app.directive("component", function() {
    return {
        scope: {
          ngSrc: "@", //Text Binding
        },
        controller: function($scope, authuser) {
           $scope.doThings = function() {
               //MyService.doThings();
               var val = authuser.multiply(10,20);
               console.log(val);
           }
        },
        restrict: 'E',
        transclude: true,
        template: "<a href='{{ng-src}}' ng-click='doThings' ng-transclude></a>"
    }
});

  app.factory('authuser', function() {
    var factory = {};
    
    factory.multiply = function(a, b) {
       return a * b
    }
    
    return factory;
 }); 