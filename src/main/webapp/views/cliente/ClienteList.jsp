<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.Cliente" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 02/20/23
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Clientes</h1>

<table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th></th>
        <th>Nombre</th>
        <th>Direccion</th>
        <th>Telefono</th>
        <th>E-mail</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${clientes != null}">
        <c:forEach items="${clientes}" var="client">
            <tr>
                <td></td>
                <td>${client.nombre}</td>
                <td>${client.direccion}</td>
                <td>${client.telefono}</td>
                <td>${client.email}</td>
                <td>
                    <a href="editClient.jsp?id=${client.id}" class="btn btn-primary">Editar</a>
                    <a href="deleteClient.jsp?id=${client.id}" class="btn btn-danger">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>

<jsp:include page="/layout/footer.jsp"></jsp:include>
