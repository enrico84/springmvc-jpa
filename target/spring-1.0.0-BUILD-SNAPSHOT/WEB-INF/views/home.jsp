<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ page session="false"%>
<html>
<head>
	<title>Home</title>
	
	<!-- Caricare file statici come js, css e img nelle jsp -->
	<spring:url value="//resources/css/springmvc.css" var="springmvcCSS" />
	<spring:url value="//resources/js/springmvc.js" var="springmvcJS" />
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>  -->
	<spring:url value="//resources/js/jquery.1.10.2.min.js" var="jqueryJs" />
	
	<link href="${springmvcCSS}" rel="stylesheet" />
	<script src="${jqueryJs}"></script>
	<script src="${springmvcJS}"></script>
	<!-- Finish adding tags -->
</head>
<body>
	<h1> Seleziona se aggiungere un Player o vedere la lista dei Player </h1>
		
	<div id="springMvcJpaMessage"></div>
	
	<p>  The time on the server is ${serverTime}. </p>

	<p> <a href="simpleForm">View simple form</a> | <a href="players">View Players</a> | <a href="addform">Add Players</a> <p/>
</body>
</html>
