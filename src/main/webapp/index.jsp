<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html >
  
  <head>
    <meta charset="utf-8">
    <title>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Le styles -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <style>
      body { padding-top: 60px; /* 60px to make the container go all the way
      to the bottom of the topbar */ }
    </style>
    <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js">
      </script>
    <![endif]-->
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    <style>
    </style>
  </head>
  <body>
    <div class="navbar navbar-fixed-top navbar-inverse">
      <div class="navbar-inner">
        <div class="container">
          
          <ul class="nav">
          </ul>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="hero-unit">
        <div>
          <h1>
            Welcome to MBank <sec:authentication property="name"/>!
          </h1>
        </div>
       
       <!-- Admin Action  --> 
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a class="btn btn-primary" href="addClient.html">
          Add Client »
        </a>
        </sec:authorize>
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a class="btn btn-primary" href="updateClientAdmin.html">
          Update Client »
        </a>
        </sec:authorize>
        
        <br>
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a class="btn btn-primary" href="removeClient.html">
          Remove Client »
        </a>
        </sec:authorize>
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a class="btn btn-primary" href="viewAllClientDetails.html">
          View All Client Details »
        </a>
        </sec:authorize>
        
        <br>
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a class="btn btn-primary" href="viewAllAccountDetails.html">
          View All Account Details »
        </a>
        </sec:authorize>
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a class="btn btn-primary" href="viewAllDepositsDetails.html">
          View All Deposits Details »
        </a>
        </sec:authorize>
        
        <br>
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a class="btn btn-primary" href="viewAllActivitiesDetails.html">
          View All Activities Details »
        </a>
        </sec:authorize>
   
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a class="btn btn-primary" href="updateSystemProperty.html">
          Update System Property »
        </a>
        </sec:authorize>
        
        <br>
        <!-- Client And Admin Action  -->
        
        <a class="btn btn-primary" href="viewSystemPropertyAdmin.html">
          View System Property »    
        </a>
        
         <a class="btn btn-primary" href="viewClientDetails.html">
          View Client Details »    
        </a>
        
        <br>
        
        <a class="btn btn-primary" href="viewActivityDetails.html">
          View Activity Details »
        </a>        
        
        <a class="btn btn-primary" href="viewAccountDetails.html">
          View Account Details »
        </a>
        
         <a class="btn btn-primary" href="viewDepositsDetails.html">
          View Deposits Details »
        </a>
        
        <br>
        <!--  client action  -->
        
        <a class="btn btn-primary" href="updateClient.html">
          Update Client »
        </a>    
        
         <a class="btn btn-primary" href="crateNewDeposit.html">
          Create New Deposit »
        </a>    
        
        <br>
        
         <a class="btn btn-primary" href="depositToAccount.html">
          Deposit To Account »
        </a>    
        
         <a class="btn btn-primary" href="withdrawFromAccount.html">
          Withdraw From Account »
        </a>    
        
        <br>
        
         <a class="btn btn-primary" href="preOpenDeposite.html">
          Pre open Deposite»
        </a>    
  
         <br>
         
         <a class="btn btn-warning" href="j_spring_security_logout">
        	Logout >>
        </a>
        
      </div>
      <div>
      </div>
    </div>
    
    <script src="jquery-1.8.3.js">
    </script>
    
    <script src="assets/js/bootstrap.js">
    </script>
  </body>
</html>