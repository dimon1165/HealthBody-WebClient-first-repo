<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>group</title>
</head>
<body>
<table cellpadding="5" cellspacing="30">
		<tr>
			<th>Name : </th>
			<th>Description : </th>
			<th>Count: </th>
			<th>Score of group : </th>
		</tr>
			<tr>
				<td>${getGroup.name}</td>
				<td>${getGroup.descriptions}</td>
				<td>${getGroup.count}</td>
				<td>${getGroup.scoreGroup}</td>
			</tr>
	</table>
</body>
</html>