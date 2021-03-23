<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Info player</title>
</head>
<body>
	<h1>Visualizzo le info del player aggiunto</h1>
	<table border="2" cellpadding="5">
		<tr>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Squadra</th>
			<th>Posizione</th>
		</tr>  
		   <tr>  
			   <td>${name}</td>  
			   <td>${lastName}</td>  
			   <td>${team}</td>
			   <td>${position}</td>
		   </tr>  
	</table>  
	<br/>  
	<a href="${pageContext.request.contextPath}/">Tona alla home</a>  
</body>
</html>