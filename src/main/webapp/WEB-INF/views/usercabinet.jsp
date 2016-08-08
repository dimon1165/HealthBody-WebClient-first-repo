<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Health Body</title>
<link rel="icon"
	href="https://d13yacurqjgara.cloudfront.net/users/678458/screenshots/1856046/h-icon.png">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
 <nav class="navbar navbar-inverse navbar-fixed-top">
 <div class="container">
	<div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar">1</span>
            <span class="icon-bar">2</span>
            <span class="icon-bar">3</span>
          </button>
          <a class="navbar-brand" href="HomePage.html">Health Body</a>
           <ul class="nav navbar-nav">
                    <li>
                        <a href="usercabinet.html">My Cabinet</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                    <li>
                        <a href="userlist.html">User List</a>
                    </li>
                    <li>
                    <a href="listGroups.html">Groups</a>
                    </li>
                </ul>
                </div>
        </div>
        </nav>
         <br>
         <br>
    <div class="container">
		<img src="${getUser.photoURL}"
			alt="Cinque Terre" width="334" height="336">
		<table class="table">
			<tr class="info">
				<th>Login :</th>
				<th>Name :</th>
				<th>Surname :</th>
				<th>Age :</th>
				<th>Weight :</th>
				<th>Gender :</th>
				<th>Role :</th>
				<th>Status :</th>
				<th>Score :</th>
			</tr>
			<tr class="info">
				<td>${getUser.login}</td>
				<td>${getUser.firstname}</td>
				<td>${getUser.lastname}</td>
				<td>${getUser.age}</td>
				<td>${getUser.weight}</td>
				<td>${getUser.gender}</td>
				<td>${getUser.roleName}</td>
				<td>${getUser.status}</td>
				<td>${getUser.score}</td>
			</tr>
		</table>
		<table class="table">
			<tr class="info">
				<th>Groups :</th>
			</tr>
			<tr class="info">
				<c:forEach items="${getUser.groups}" var="p">
					<td><a href="group.html?nameGroup=${p.name}&userLogin=${getUser.login}">${p.name}</a></td>
				</c:forEach>
			</tr>
		</table>
		</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>