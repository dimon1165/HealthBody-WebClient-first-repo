<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Join the competition</title>
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
				<th>Name :</th>
				<td>${getCompetition.name}</td>
			</tr>
			<tr class="info">
				<th>Description :</th>
				<td>${getCompetition.description}</td>
			</tr>
			<tr class="info">
				<th>Start Date :</th>
				<td>${getCompetition.startDate}</td>
			</tr>
			<tr class="info">
				<th>Finish Date :</th>
				<td>${getCompetition.finishDate}</td>
			</tr>
			<tr>
				<td><a class="btn btn-success" type="submit"
					href="joinCompetition.html?nameCompetition=${getCompetition.name}&userLogin=${user.login}">Join
						Competition</a></td>
			</tr>
		</table>
	</div>
</body>
</html>