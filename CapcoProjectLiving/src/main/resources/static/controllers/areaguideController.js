// create the controller for areaguide
app.controller('areaguideController', ['$scope','jsonFactory', function ($scope, jsonFactory) {
	
	$scope.showIcons = true;
	$scope.areaguideheading = "Area Guide";
	$scope.showGPS = true;
	$scope.showSearchDiv = "col-xs-11";
	$scope.localityList = [];
	var latlng;
	var marker;
	
	// JSON data method calling
	jsonFactory.getCities().then(function(result) {
        $scope.cityList=result.data;
        console.log($scope.cityList);
    },
    function(error){
        console.error("The async call has fail");
    }); 
	
	// map related stuff
	 var mapOptions = {
             zoom: 4,
             center: new google.maps.LatLng(25,80),
             mapTypeId: google.maps.MapTypeId.ROADMAP
         }

     $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
	 
	// getLocalitiesAvailability() for fetching localities as per city selection 
	 $scope.getLocalitiesAvailability = function(locality){
	 		
	 		console.log(locality);
	 		console.log($scope.localityList[0]);
	 		angular.forEach($scope.localityList, function(iLocality){
	 			
	 			var nlocality= locality.toLowerCase();
				var nlocalityName = iLocality.locality_name.toLowerCase();
				
	 			latlng = "";
	 			marker = "";
	 			if(nlocalityName == nlocality){
	 				console.log("iLocality.locality_name",iLocality.locality_name);
	 				$scope.locality = iLocality.locality_name;
	 				console.log("nlocalityName == nlocality",nlocalityName == nlocality);
	 				console.log("iLocality.lattitude ,iLocality.longitude",iLocality.lattitude ,iLocality.longitude);
	 				
	 				latLng = new google.maps.LatLng(iLocality.lattitude ,iLocality.longitude);
	 			    console.log("latlng : ",latLng);  
	 		        // Creating a marker and putting it on the map			 
	 		        marker = new google.maps.Marker({
	 	              position: latLng,
	 	              map: $scope.map
	 	              
	 	            });
	 			}
	 		});
	 	}
	 	
	 // getSuggestion() for getting city suggestions
		$scope.getSuggestion = function(insertedcity){
			
			angular.forEach($scope.cityList, function(city){
				
				var nInsertedCity = insertedcity.toLowerCase();
				var nCity = city.city_name.toLowerCase();
				
				if(nCity == nInsertedCity){
					console.log("city.city_id : " , city.city_id);
					var cityId = city.city_id;
					
					//  Page Styling change
					$scope.showLocality = true;
					$scope.showIcons = false;
					$scope.areaguideheading = "Enter Location";
					$scope.showGPS = false;
					$scope.showSearchDiv = "col-xs-12";
					$scope.city = city.city_name;
					
					// finding Latitude & Longitude
//					latLng = new google.maps.LatLng(city.lattitude,city.longitude);
//					marker = new google.maps.Marker({
//			              position: latLng,
//			              map: $scope.map
//					});
					
					jsonFactory.getLocalities(cityId).then(function(result) {
						console.log("Result : ", result.data);
				        $scope.localityList=result.data;
				        console.log($scope.localityList,"locality from backend");
		
				    },
				    function(error){
				        console.error(error,"The async call has fail");
				    }); 
				}
			});
		}
		
		
		var close = true;
		$scope.openNav = function() {
		    
		    if(close){
		        document.getElementById("myBottomnav").style.height = "470px";
		        document.getElementById("up").style.display = "none";
		        document.getElementById("down").style.display = "initial";
		        close = false;
		    }else{
		        document.getElementById("myBottomnav").style.height = "80px";
		        document.getElementById("up").style.display = "initial";
		        document.getElementById("down").style.display = "none";
		        close = true;
		    }
		    
		    
		}

	
}]);