angular.module("myApp")
 
.factory("jsonTransfer",[function(){
		var Info = {};

	return {
	    getInfo: function () {
	    	console.log(Info,"before sending getinfo method");
	        return Info;
	    },
	    setInfo: function (value) {
	        Info = value;
	        console.log("in jsonTransfer",Info);
	    }
	    
	    
	};

	}]) 
	
	
	.factory("editRequestJsonTransfer",[function(){
		var InfoData = {};

	return {
	    
	    setInfo: function (value) {
	        InfoData = value;
	        console.log("in edit jsonTransfer",InfoData);
	    },
	    getEditRequestInfo: function () {
	    	console.log(InfoData,"before sending edit getinfo method");
	        return InfoData;
	    }
	    
	    
	};

	}]) 
	 



.factory("firstApprover",['$http',function($http){
	var firstApproverObj={};
	var baseUrl = "http://localhost:8080/capco-travel-portal-webApp";
	firstApproverObj.sendRequest=function(userData){
		return $http({
			method:'POST',
			data:userData,
			url:baseUrl + "/request/approver/action/"+userData.requestId
		})
		console.log(userData);
	}
	
	firstApproverObj.getRequest=function(){
		
	}
	return firstApproverObj;

}])

//Factory for Login,Logout,KeepAlive 
.factory("userAuthentication",['$http',function($http){ 
	var userAuthenticationResponse={};
	var baseUrl = "http://localhost:8080/capco-travel-portal-webApp";
	//var baseUrl = "https://capco.com/v1/";

	// dummy
	userAuthenticationResponse.requestCount=function(employeeId){
		return $http.get(baseUrl + "/request/user/approved");
	}

	// Error messages json
	userAuthenticationResponse.errMsg = function(){
		return $http.get('../capco-travel-portal-webApp/json/constant.json');
	}

	//Functions
	//1.To Login User
	userAuthenticationResponse.loginUser=function(user){
		console.log("Login ",user)
			return $http({
				method:"POST",
				url: baseUrl + "/portalLogin",
				data:user
			})
	};

	//1.To Logout User
	userAuthenticationResponse.logoutUser=function(){
			return $http.post(baseUrl + "/logout");
	};

	//3.To Track Session
	userAuthenticationResponse.keepAlive=function(){
			return $http({
				method:"GET",
				url:"http://jsonplaceholder.typicode.com/users"
			});
			
			
	};

	return userAuthenticationResponse;
}])//Factory Block End

//Factory for getAllRecords,getSpecificRecord,createRecord,updateRecord
.factory("travelRequest",['$http','userAuthentication',function($http,userAuthentication){ 
	var travelRequestRecords={};
	var baseUrl = "http://localhost:8080/capco-travel-portal-webApp";
//	var baseUrl = "https://capco.com/v1/";

	//Functions
	//1.To Get All Records by approver
	travelRequestRecords.getAllRecords=function(approverId){
		return $http({
			method:'GET',
			url:baseUrl + "/request/approver/list"
		})
};
	//2.To Get All Records by request id
	travelRequestRecords.getRequestDetails=function(requestId){
		var request;
		return $http({
			method:'GET',
			url:baseUrl + "/request/"+requestId
		})
	};

	//get records by user id
	travelRequestRecords.getRequestDetailsByUserId=function(userId){//userId
		return $http({
			method:'GET',
			url:baseUrl + "/request"
		})
	};

	
	//2.To Get Specific Record 
	travelRequestRecords.getSpecificRecord=function(user){
			return $http.post(baseUrl + "request",user);
	};

	//3.To Create Records 
	travelRequestRecords.createRecord=function(requestObj){
		console.log("create record")
	return	$http({
			method:'PUT',
			url: baseUrl + "/request",
			data:{"travelRequest":requestObj}
		})
	};

	//4.To Update Records 
	travelRequestRecords.updateRecord=function(user){
			return $http.post(baseUrl + "request",user);
	};
	
	//5. To delete Records
	travelRequestRecords.deleteRecord=function(requestId){
		console.log("delete record")
	return	$http({
			method:'POST',
			url:baseUrl + "/request/cancel/" +requestId
		})
	};
	
	//6. to get project list, aprover list
	travelRequestRecords.getApproverAndProjectList=function(){
		return $http({
			method:'GET',
			url:baseUrl + "/request/load/data/" +userAuthentication.level
		})
	} 
	
	//7. edit request
	travelRequestRecords.editRecord=function(requestObj,requestId){
		console.log("edit record")
	return	$http({
			method:'POST',
			url:baseUrl + "/request/status/" + requestId,
			data:{"travelRequest":requestObj}
		})
		
	
	};
	
	//8. Get all RequestsByType
	travelRequestRecords.getRequestDetailsByType=function(Type){
		console.log("Records By Type")
		return $http({
			method:'GET',
			url:baseUrl + "/request/filter/"+Type
		})
	}; 
	return travelRequestRecords;
}])//Factory Block End



.factory("requestTypeParameterTransfer",[function(){
		var Info = {};

	return {
	    getInfo: function () {
	    	console.log(Info,"before sending getinfo method");
	        return Info;
	    },
	    setInfo: function (value) {
	        Info.type = value;
	        console.log("in requestTypeParameterTransfer",Info.type);
	    }
	    
	    
	};

	}]) 

