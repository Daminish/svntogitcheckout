<!DOCTYPE html>
<html ng-app="myApp">
<head>
  <title></title>
  <link rel="stylesheet" type="text/css" href="/capco-travel-portal-webApp/assets/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="/capco-travel-portal-webApp/assets/dist/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="/capco-travel-portal-webApp/assets/dist/css/cust-Travel-Portal.css">
  <link rel="stylesheet" href="../capco-travel-portal-webApp/assets/js/calendar/bootstrap_calendar.css" type="text/css" />
	<base href="/capco-travel-portal-webApp/" />
</head>
<body>
  <div ui-view></div>
  <footer-component></footer-component>
  
  
  
  <script type="text/javascript">
        function GetDays(){
                var dropdt = new Date(document.getElementById("forexToDate").value);
                var pickdt = new Date(document.getElementById("forexFromDate").value);
                return parseInt((dropdt - pickdt) / (24 * 3600 * 1000));
        }

        function cal(){
        if(document.getElementById("forexFromDate")){
            document.getElementById("numdays2").value=GetDays();
        }  
    }

    </script> 


        <!-- Angular Scripts -->
        <script src="../capco-travel-portal-webApp/assets/js/angular/angular.min.js"></script>
        <script src="../capco-travel-portal-webApp/assets/js/angular/angular-ui-router.js"></script>
       <!--  <script src="../capco-travel-portal-webApp/assets/js/angular/component-router.js"></script> -->
        <script src="../capco-travel-portal-webApp/assets/js/angular/angular-messages.js"></script>
         
        
       
        <!--Bootstrap and Jquery Scripts  -->
        <script src="../capco-travel-portal-webApp/assets/js/jquery/jquery.min.js"></script>
        <script src="../capco-travel-portal-webApp/assets/js/bootstrap/bootstrap.min.js"></script>
		<script src="../capco-travel-portal-webApp/assets/js/bootstrap/ui-bootstrap-tpls.js"></script>
        <script src="/capco-travel-portal-webApp/modules/module.js"></script>
        <script src="/capco-travel-portal-webApp/controllers/controller.js"></script>
        <script src="/capco-travel-portal-webApp/controllers/loginCtrl.js"></script>
        <script src="/capco-travel-portal-webApp/controllers/newRequest.js"></script>
        <script src="/capco-travel-portal-webApp/services/factory.js"></script>
      	<script src="/capco-travel-portal-webApp/components/components.js"></script>
        <!-- <script src="/capco-travel-portal-webApp/directives/calendarDirective.js"></script> -->
         <!-- Calendar Block Scripts -->
        <script src="/capco-travel-portal-webApp/assets/js/calendar/bootstrap_calendar.js"></script>
        <!-- <script src="../assets/js/calendar/demo.js"></script>-->
        
        


</body>
</html>