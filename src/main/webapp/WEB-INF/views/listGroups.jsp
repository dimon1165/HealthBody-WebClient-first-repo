<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>group</title>
<link rel="icon"
	href="https://d13yacurqjgara.cloudfront.net/users/678458/screenshots/1856046/h-icon.png">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar">1</span> <span class="icon-bar">2</span> <span
					class="icon-bar">3</span>
			</button>
			<a class="navbar-brand" href="HomePage.html">Health Body</a>
			<ul class="nav navbar-nav">
				<li><a href="usercabinet.html">My Cabinet</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="#">Contact</a></li>
				<li><a href="userlist.html">User List</a></li>
				<li><a href="listGroups.html">Groups</a></li>
				<li><a href="listCompetitions.html">Competitions</a></li>
			</ul>
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
				<th>Name:</th>
				<th>Status:</th>
				<th>Participants:</th>
			</tr>
			<tr>
				<c:forEach items="${getGroups}" var="p">
					<tr>
						<td><a
							href="group.html?nameGroup=${p.name}&userLogin=${getUser.login}">${p.name}</a><br>
						<br>
						<td>${p.status}<br>
						<br>
						<td />
						<c:forEach begin="0" end="${fn:length(p.users)}" var="index">
							<c:out value="${p.users[index]}" />
							<br>
							<c:out value="${p.firstname[index]}" />
							<br>
							<c:out value="${p.lastname[index]}" />
							<br>
							<br>
						</c:forEach>
						<td />
					</tr>
				</c:forEach>
			</tr>
		</table>
	</div>

	<div class="container" align="center">
		<ul class="pagination">
			<li><a
				href="<c:url value="/listGroups.html" >
        <c:param name="groupsParticipantsPartnumber" value="${currentPage - 1}"/>${p}</c:url>">«</a></li>
			<li><c:forEach begin="${startPartNumber}"
					end="${endpagePartNumber}" var="p">
					<a
						href="<c:url value="/listGroups.html" >
        <c:param name="groupsParticipantsPartnumber" value="${p}"/>${p}</c:url>">${p}</a>
				</c:forEach>
			<li><a
				href="<c:url value="/listGroups.html" >
        <c:param name="groupsParticipantsPartnumber" value="${currentPage + 1}"/>${p}</c:url>">»</a></li>
		</ul>
	</div>
</body>
</html>