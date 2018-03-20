<!DOCTYPE html>
<html ng-app="myApp">
<head>
  <title></title>
  <link rel="stylesheet" type="text/css" href="/capco-travel-portal-webApp/assets/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="/capco-travel-portal-webApp/assets/dist/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="/capco-travel-portal-webApp/assets/dist/css/cust-Travel-Portal.css">
  <link rel="stylesheet" href="../capco-travel-portal-webApp/assets/js/calendar/bootstrap_calendar.css" type="text/css" />
<!-- <base href="/" /> -->
</head>
<body>
 <header-component></header-component>
  <div ui-view></div>
  <footer-component></footer-component>


        <!-- Angular Scripts -->
        <script src="../capco-travel-portal-webApp/assets/js/angular/angular.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.3/angular-ui-router.js"></script>
       <!--  <script src="../capco-travel-portal-webApp/assets/js/angular/component-router.js"></script> -->
        <script src="/capco-travel-portal-webApp/assets/js/angular/angular-messages.js"></script>
        
       
        <!--Bootstrap and Jquery Scripts  -->
        <script src="../capco-travel-portal-webApp/assets/js/jquery/jquery.min.js"></script>
        <script src="../capco-travel-portal-webApp/assets/js/bootstrap/bootstrap.min.js"></script>

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