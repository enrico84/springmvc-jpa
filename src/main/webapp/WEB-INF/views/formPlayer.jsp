<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Form view</title>
</head>
<body>
	<h3>Benvenuto, inserisci i dati del Player</h3>
	<h4>${message}</h4>
	<form:form method="POST" action="${pageContext.request.contextPath}/addPlayer" modelAttribute="player">
	<table>
		<tr>
			<td><form:label path="name">Nome</form:label></td>
			<td><form:input path="name"/></td>
		</tr>
		<tr>
			<td><form:label path="lastName">Cognome</form:label></td>
			<td><form:input path="lastName"/></td>
		</tr>
		<tr>
			<td><form:label path="team">Team</form:label></td>
			<td><form:input path="team"/></td>
		</tr>
		<tr>
			<td><form:label path="position">Posizione</form:label></td>
			<td><form:input path="position"/></td>
		</tr>
		<tr>
			<td><input type="submit" name="submit" value="Submit" /></td>
			<td><input type="submit" name="cancel" value="Cancel" /></td>
			<td><input type="submit" name="edit" value="Edit" /></td>
		</tr>
	</table>
	
	</form:form>
</body>
</html>