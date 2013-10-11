<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
  		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
    
    		<c:url var="homeUrl" value="/" />
    		<a class="brand" href="${homeUrl}"></a>
    
    		<div class="nav-collapse collapse">
	     		 <ul class="nav">
	      			<li class="wallets"><a href="1">Étude Dimension Fantastique</a></li>
				</ul>
    		</div>
    	</div>
	</div>
</div>

<script type="text/template" id="pollsMenu-template">
	<@ _.each(polls, function(stock) { @>
		<li><a href="#" class="<@= poll.id @>"><@= poll.name @></a></li>
	<@}); @>
</script>
