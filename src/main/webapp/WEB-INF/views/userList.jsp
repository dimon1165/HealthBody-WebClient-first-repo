<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>usercabinet</title>
</head> -->
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Users</title>
<link rel="icon"
	href="https://d13yacurqjgara.cloudfront.net/users/678458/screenshots/1856046/h-icon.png">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script  type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" async></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script  type="text/javascript" src="resources/js/bootstrap.min.js" async></script>

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar">1</span> <span class="icon-bar">2</span> <span
					class="icon-bar">3</span>
			</button>
			<a class="navbar-brand" href="main.html">Health Body</a>
			<ul class="nav navbar-nav">
				<li><a href="userCabinet.html">${login}</a></li>
				<li><a href="userlist.html">Users</a></li>
				<li><a href="listGroups.html">Groups</a></li>
				<li><a href="listCompetitions.html">Competitions</a></li>
			</ul>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<form class="navbar-form navbar-right" role="form">
				<a class="btn btn-success" type="submit" href="logout.html">Sign
					out</a>
			</form>
		</div>
	</div>
	</nav>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<table class="table">
			<tr class="info">
				<th>Login</th>
				<th>Name</th>
				<th>Surname</th>
				<th>E-mail :</th>
				<th>Age</th>
				<th>Weight :</th>
				<th>Gender :</th>
				<th>Health :</th>
				<th>Score :</th>
				<th>Role :</th>
			</tr>
			<c:forEach items="${AllUsers}" var="p">
				<tr>
					<td>${p.login}</td>
					<td>${p.firstname}</td>
					<td>${p.lastname}</td>
					<td>${p.email}</td>
					<td>${p.age}</td>
					<td>${p.weight}</td>
					<td>${p.gender}</td>
					<td>${p.health}</td>
					<td>${p.score}</td>
					<td>${p.roleName}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="container" align="center">
		<ul class="pagination">

			<%--For displaying Previous link --%>
			<li><c:if test="${currentPage != 1}">
					<a
						href="<c:url value="/userlist.html" >
        <c:param name="partNumber" value="${currentPage - 1}"/>${p}</c:url>">«</a>
				</c:if></li>

			<%--For displaying Page numbers --%>
			<li><c:forEach begin="${startPartNumber}"
					end="${lastPartNumber}" var="p">
					<a
						href="<c:url value="/userlist.html" >
        <c:param name="partNumber" value="${p}"/>${p}</c:url>">${p}</a>
				</c:forEach></li>

			<%--For displaying Next link --%>
			<li><c:if test="${currentPage lt lastPartNumber}">
					<a
						href="<c:url value="/userlist.html" >
        <c:param name="partNumber" value="${currentPage + 1}"/>${p}</c:url>">»</a>
				</c:if></li>
		</ul>
	</div>
</body>
</html>