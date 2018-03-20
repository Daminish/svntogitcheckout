app.factory("jsonFactory", function($http){
  return { 

    getCities : function(){
            return $http.get('/search/city/');
    },
  	getLocalities : function(cityId){
  		console.log("cityId",cityId);
  		return $http.get('/search/city/'+cityId+'/locality');
  	}
   }   
});