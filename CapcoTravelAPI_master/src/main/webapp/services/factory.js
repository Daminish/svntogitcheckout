angular.module("myApp")
 
.factory("jsonTransfer",[function(){
		var Info = {};

	return {
	    getInfo: function () {
	        return Info;
	    },
	    setInfo: function (value) {
	        Info = value;
	        console.log("in jsonTransfer",Info);
	    }
	};

	}]) 
	 



.factory("firstApprover",['$http',function($http){
	var firstApproverObj={};
	firstApproverObj.sendRequest=function(userData){
		return $http({
			method:'POST',
			data:userData,
			url:"http://localhost:8080/capco-travel-portal-webApp/approveRequest"
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
	var baseUrl = "https://capco.com/v1/";

	// dummy
	userAuthenticationResponse.requestCount=function(employeeId){
		return $http.get('http://localhost:8080/capco-travel-portal-webApp/getRequestCount/'+employeeId);
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
				url:"http://localhost:8080/capco-travel-portal-webApp/portalLogin",
				data:user
			})
	};

	//1.To Logout User
	userAuthenticationResponse.logoutUser=function(){
			return $http.post("http://localhost:8080/capco-travel-portal-webApp/logout");
	};

	//3.To Track Session
	userAuthenticationResponse.keepAlive=function(){
			return $http.get(baseUrl +"keepalive");
	};

	return userAuthenticationResponse;
}])//Factory Block End

//Factory for getAllRecords,getSpecificRecord,createRecord,updateRecord
.factory("travelRequest",['$http','userAuthentication',function($http,userAuthentication){ 
	var travelRequestRecords={};
	var baseUrl = "https://capco.com/v1/";

	//Functions
	//1.To Get All Records by approver
	travelRequestRecords.getAllRecords=function(approverId){
		return $http({
			method:'GET',
			url:"http://localhost:8080/capco-travel-portal-webApp/getAllRequest/"+approverId
		})
};
	//2.To Get All Records by request id
	travelRequestRecords.getRequestDetails=function(requestId){
		var request;
		return $http({
			method:'GET',
			url:"http://localhost:8080/capco-travel-portal-webApp/request/"+requestId
		})
	};

	//get records by user id
	travelRequestRecords.getRequestDetailsByUserId=function(userId){//userId
		return $http({
			method:'GET',
			url:"http://localhost:8080/capco-travel-portal-webApp/getRequestDetailsByUserId/"+userId
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
			url:"http://localhost:8080/capco-travel-portal-webApp/request",
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
			url:"http://localhost:8080/capco-travel-portal-webApp/request/cancel/"+requestId
		})
	};
	
	//6. to get project list, aprover list
	travelRequestRecords.getApproverAndProjectList=function(){
		return $http({
			method:'GET',
			url:"http://localhost:8080/capco-travel-portal-webApp/approverAndProjectList/"+userAuthentication.level
		})
	} 
	

	return travelRequestRecords;
}])//Factory Block End