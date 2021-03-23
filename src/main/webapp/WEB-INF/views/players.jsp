<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lista di Player</title>
</head>
<body>
	<h1>Lista di Player</h1>
	<p>
		<form:form method="GET" action="search">
			 <input type="text" name="keyword" placeholder="Cerca un player" />
			 <input type="submit" value ="Cerca" />
		</form:form>
	</p>
	<table border="2" cellpadding="5">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Squadra</th>
			<th>Posizione</th>
			<th>Azione</th>
		</tr>  
	   <c:forEach var="p" items="${players}">   
		   <tr>  
			   <td>${p.id}</td>  
			   <td>${p.name}</td>  
			   <td>${p.lastName}</td>  
			   <td>${p.team}</td>
			   <td>${p.position}</td>
			   <td>
			     <a href="editplayer/${p.id}">Edit</a>
			     &nbsp;&nbsp;&nbsp;
			     <a href="deleteplayer/${p.id}">Delete</a>
			   </td>  
		   </tr>  
	   </c:forEach>  
	</table>  
	<br/>  
	<a href="addform">Aggiungi nuovo Player</a>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/">Torna alla Home</a>
</body>
</html>