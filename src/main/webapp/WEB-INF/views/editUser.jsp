<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>editUser</title>
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
		<form:form action="editUser.html" method="POST"
			commandName="userToEdit">
			<table class="table">
				<tr class="info">
					<td><form:label path="login">Login:</form:label></td>
					<td><form:input path="login" disabled="true" /></td>
				</tr>
				<tr class="info">
					<td><form:label path="firstname">Name:</form:label></td>
					<td><form:input path="firstname" /></td>
				</tr>
				<tr class="info">
					<td><form:label path="lastname">SurName</form:label></td>
					<td><form:input path="lastname" /></td>
				</tr>
				<tr class="info">
					<td><form:label path="age">Age</form:label></td>
					<td><form:input path="age" /></td>
				</tr>
				<tr class="info">
					<td><form:label path="weight">Weight</form:label></td>
					<td><form:input path="weight" /></td>
				</tr>
				<tr class="info">
					<td><form:radiobutton path="gender" value="MALE" />Male</td>
					<td><form:radiobutton path="gender" value="FEMALE" />Female</td>
				</tr>
				<tr class="info">
					<td><form:label path="health">Health</form:label></td>
					<td><form:input path="health" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>