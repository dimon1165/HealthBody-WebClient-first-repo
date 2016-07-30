<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>usercabinet</title>
</head>
<body>
	<table cellpadding="2" cellspacing="30">
			<tr><td>Login : ${getUser.login}</td></tr>
			<tr><td>Name : ${getUser.firstname}</td></tr>
			<tr><td>Surname : ${getUser.lastname}</td></tr>
			<tr><td>Age : ${getUser.age}</td></tr>
			<tr><td>Weight : ${getUser.weight}</td></tr>
			<tr><td>Gender : ${getUser.gender}</td></tr>
			<tr><td>photoURL : ${getUser.photoURL}</td></tr>
			<tr><td>Role : ${getUser.roleName}</td></tr>
			<tr><td>Status : ${getUser.status}</td></tr>
			<tr><td>Score : ${getUser.score}</td></tr>
			<tr><td>Groups : </td></tr>			
			<c:forEach items="${getUser.groups}" var="p">
			 <td>${p.name}</td> 
			</c:forEach>
	</table>
</body>
</html>