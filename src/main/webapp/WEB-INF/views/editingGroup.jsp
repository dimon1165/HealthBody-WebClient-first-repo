<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
				<li><a href="userlist.html">Users</a></li>
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
		<form:form action="editingGroup.html" method="POST"
			commandName="group">
			<table class="table">
				<tr class="info">
					<td><form:label path="idGroup">Edit Group :</form:label></td>
					<td><form:input path="idGroup" readonly="true" name="name" type="hidden" value="${group.idGroup}"></form:input>${group.name}</td>
				</tr>
				<tr>
					<td><form:label path="name">Name:</form:label></td>
					<td><form:input path="name"></form:input></td>
				</tr>
				<tr>
					<td><form:label path="descriptions">Description :</form:label></td>
					<td><textarea name="descriptions"  rows="4" cols="50">${group.descriptions}</textarea></td>
				</tr>
				<tr>
					<td><form:label path="count">Users :</form:label></td>
					<td><form:input path="count" readonly="true" name="name" type="hidden" value="${group.count}"></form:input>${group.count}</td>
				</tr>
				<tr>
					<td><form:label path="scoreGroup">Score :</form:label></td>
					<td><form:input path="scoreGroup" readonly="true" name="name" type="hidden" value="${group.scoreGroup}"></form:input>${group.scoreGroup}</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="btn btn-success" type="submit"
						value="Save" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>