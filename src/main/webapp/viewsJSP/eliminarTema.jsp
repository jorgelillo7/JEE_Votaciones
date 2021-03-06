<?xml version="1.0" encoding="UTF-8"  ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Eliminar temas</title>
</head>
<body>

	<c:set var="eTemaView" scope="request" value="${temaView}" />

	<form action="/JEE_ECP/jsp/eliminarTemaAutenticado" method="post">
		<p>
			Listado de Temas: <select name="temaABorrar">
				<c:forEach var="tema" items="${eTemaView.temas}">
					<option value="${tema.getId()}">${tema.toString()}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<input type="submit" value="Borrar" />
		</p>
	</form>

	<p>
		<a href="/JEE_ECP/jsp/home">Volver a Home</a>
	</p>
</body>
</html>