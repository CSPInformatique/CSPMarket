<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<spring:url var="cssUrl" value="/resources/css" />
<spring:url var="jsUrl" value="/resources/js" />

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Etude Dimension Fantastique</title>

    <!-- Bootstrap core CSS -->
    <link href="${cssUrl}/libs/bootstrap.min.css" rel="stylesheet">
    <link href="${cssUrl}/libs/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${cssUrl}/cspMarket.css" rel="stylesheet">
    
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="${jsUrl}/libs/html5shiv.js"></script>
	<![endif]-->
	
 	<script src="${jsUrl}/libs/jquery-1.10.2.min.js"></script>
 	<script src="${jsUrl}/libs/underscore.js"></script>
 	<script src="${jsUrl}/libs/backbone.js"></script>
  </head>

  <body>
	<tiles:insertAttribute name="menu" />

    <div class="container">
		<div class="body-content">
	      	<div class="div-content">
				<tiles:insertAttribute name="content" />
			</div>
			<tiles:insertAttribute name="footer" />
		</div>
    </div>
    
	<script type="text/javascript">
       	var ctx = "${pageContext.servletContext.contextPath}";
       	
    	_.templateSettings = {
    	    interpolate: /\<\@\=(.+?)\@\>/gim,
    	    evaluate: /\<\@([\s\S]+?)\@\>/gim,
    	    escape: /\<\@\-(.+?)\@\>/gim
    	};
    	
    	$(document).ready(function(){
    		initPage();
    	});
   	</script>

	<script src="${jsUrl}/participation.js"></script>   	
    <script src="${jsUrl}/poll.js"></script>
    <script src="${jsUrl}/question.js"></script>
    
    <script src="${jsUrl}/app.js"></script>
  </body>
</html>