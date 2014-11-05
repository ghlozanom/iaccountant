<%@page import="com.gabloz.iaccountant.bo.TipoCuentaBO"%>
<%@page import="java.util.List"%>
<%@page import="com.gabloz.iaccountant.entity.TipoCuenta"%>
<%@page import="com.gabloz.iaccountant.bo.ConceptoBO"%>
<%@page import="com.gabloz.iaccountant.entity.Concepto"%>
<%@page import="com.gabloz.iaccountant.bo.MovimientoBO"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="java.util.Collection"%>
<%@page import="com.gabloz.iaccountant.entity.Movimiento"%>
<%@page import="com.gabloz.iaccountant.web.movimiento.MovimientoWebBO"%>

<!DOCTYPE html>
<html>
	<head>  
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />      
		<link rel="stylesheet" href="css/iacc.css" />
		
		<title>I, Accountant - Home</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">  
		
		<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
		
		<!-- En este javascript esta toda la logica, donde se cargan las interfaces que son como plantillas -->
		<script src="javascript/iacc.js"></script>
	</head>
   
    <body >
    
	 <%
	    UserService userService = UserServiceFactory.getUserService();
	    User usuario = userService.getCurrentUser();
	%>   
	
	   <%@include file="mHomePage.html" %>
	   <%@include file="mIngresarMovimientoPage.html" %>	   
	   <%@include file="mVerMovimientosPage.html" %>
	   <%@include file="mUserPage.html" %>
	   <%@include file="mVerGastosPage.html" %>
	   <%@include file="mDetalleMovimientosPage.html" %>	    
        
    </body>
</html>