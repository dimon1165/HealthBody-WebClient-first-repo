<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Cabinet</title>
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
				<li><a href="userCabinet.html">${user.login}</a></li>
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
	<div class="container">
		<div class="row">
			<div class="panel panel-info">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 col-lg-3 " align="center">
							<img alt="User Pic" src="${user.photoURL}"
								class="img-circle img-responsive">
						</div>
						<div class=" col-md-9 col-lg-9 ">
							<table class="table table-user-information">
								<tbody>
									<tr>
										<td>Role :</td>
										<td>${user.roleName}</td>
									</tr>
									<tr>
										<td>Name :</td>
										<td>${user.firstname}</td>
									</tr>
									<tr>
										<td>Surname :</td>
										<td>${user.lastname}</td>
									</tr>
									<tr>
										<td>Age :</td>
										<td>${user.age}</td>
									</tr>

									<tr>
									<tr>
										<td>Gender :</td>
										<td>${user.gender}</td>
									</tr>
									<tr>
										<td>Weight :</td>
										<td>${user.weight}</td>
									</tr>
									<tr>
										<td>Email :</td>
										<td>${user.email}</td>
									</tr>
									<tr>
										<td>Health :</td>
										<td>${user.health}</td>
									</tr>
								</tbody>
							</table>
							<a href="editUser.html?userLogin=${user.login}"
								class="btn btn-primary">Edit</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div>
		<div class="container">
			<div class="row">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Groups :</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<table class="table table-user-information">
								<tr>
									<c:forEach items="${user.groups}" var="p">
										<td><a
											href="group.html?nameGroup=${p.idGroup}&userLogin=${user.login}" style="color: black">${p.name}</a></td>
									</c:forEach>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Competitions :</h3>
					</div>
					<c:if test="${empty usercompetitions}">
						<div class="panel-body">
							<div class="row">
								<table class="table table-user-information">
									<tr>
										<td align="left">You don't have any competitions yet,
											chose some competition from list <a
											href="listCompetitions.html" > competitions</a>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
					<c:if test="${not empty usercompetitions}">
						<div class="panel-body">
							<div class="row">
								<table class="table table-user-information">
									<tr>
										<c:forEach items="${usercompetitions}" var="p">
											<td><a
												href="competition.html?nameCompetition=${p.name}&userLogin=${user.login}" style="color: black">${p.name}</a></td>

										</c:forEach>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>