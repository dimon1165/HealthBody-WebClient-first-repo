<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Edit Group</title>
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
			<a class="navbar-brand" href="main.html">Health Body</a>
			<ul class="nav navbar-nav">
				<li><a href="userCabinet.html">${user.login}</a></li>
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
	<div class="container">
		<div class="row">
			<div class="panel panel-info">
			<div class="panel-heading">
              <h3 class="panel-title">Group</h3>
            </div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 col-lg-3 " align="center">
							<img alt="User Pic" src="resources/img/group.png"
								class="img-circle img-responsive">
						</div>
						<div class=" col-md-9 col-lg-9 ">
							<table class="table table-user-information">
								<tbody>
									<tr>
										<td>Name :</td>
										<td>${group.name}</td>
									</tr>
									<tr>
										<td>Description :</td>
										<td>${group.descriptions}</td>
									</tr>
									<tr>
										<td>Users :</td>
										<td>${group.count}</td>
									</tr>
									<tr>
										<td>Score :</td>
										<td>${group.scoreGroup}</td>
									</tr>
								</tbody>
								<tr>
										<td colspan="2" align="left"><a class="btn btn-primary"  type="submit" href="editGroupDescription.html?nameGroup=${group.idGroup}">Edit</a>&nbsp; &nbsp;<a class="btn btn-primary" type="submit" href="listGroups.html">Groups</a></td>
									</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>