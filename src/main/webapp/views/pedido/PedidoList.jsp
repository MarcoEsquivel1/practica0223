<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 02/20/23
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="mb-5" style="text-align: center">Pedidos</h1>

<table  class="table table-striped table-bordered table-hover table-sm">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Cliente</th>
        <th scope="col">Fecha</th>
        <th scope="col">Total</th>
        <th scope="col">Estado</th>
        <th scope="col">Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${pedidos != null}">
        <c:forEach items="${pedidos}" var="pedido">
            <tr>
                <td>${pedido.id}</td>
                <td>${pedido.nombreCliente}</td>
                <td>${pedido.fecha}</td>
                <td>${pedido.total}</td>
                <td>${pedido.estado}</td>
                <td>
                    <a href="/pedidos/detalle?id=${pedido.id}" class="btn btn-primary btn-sm">Detalle</a>
                    <a href="/pedidos/editar?id=${pedido.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="/pedidos/eliminar?id=${pedido.id}" class="btn btn-danger btn-sm">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>

<jsp:include page="/layout/footer.jsp"></jsp:include>
