<%@ page language="java" contentType="text/html; charset=charset=UTF-8" pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Risultato della ricerca di Player</title>
</head>
<body>
	<h1>Risultato della ricerca di Player</h1>
	
	<table border="2" cellpadding="5">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Squadra</th>
			<th>Posizione</th>
		</tr>  
	   <c:forEach var="p" items="${players}">   
		   <tr>  
			   <td>${p.id}</td>  
			   <td>${p.name}</td>  
			   <td>${p.lastName}</td>  
			   <td>${p.team}</td>
			   <td>${p.position}</td>
		   </tr>  
	   </c:forEach>  
	</table>  
	<br/>  
	<a href="players">Torna alla lista di Player</a>  
</body>
</html>