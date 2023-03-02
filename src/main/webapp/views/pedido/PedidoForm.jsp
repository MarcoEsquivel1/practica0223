<%-- 
    Document   : PedidoForm
    Created on : 03-01-2023, 01:48:09 PM
    Author     : Mery Acevedo
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Nuevo pedido</h1>

<form action="${pageContext.request.contextPath}/pedidos/save" method="post">
    <div class="form-group my-3">
        <div class="form-group my-3">
            <select>
                <c:forEach items="${clientes}" var="client">
                   <option value="${client.id}">
                           ${client.nombre}
                   </option>


                </c:forEach>

            </select>
        </div>
    </div>
        <div class="form-group my-3">
            <label for="total">Total</label>
            <input type="number" step="0.01" class="form-control" id="total" name="total" value="${pedido.total}"  required>
        </div>
        <div class="form-group my-3">
            <label for="estado">Estado</label>
            <input type="text" class="form-control" id="estado" name="estado" value="${pedido.estado}"required>
        </div>
        <div class="form-group my-3">
            <label for="fecha">Fecha</label>
            <input type="date" class="form-control" id="fecha" name="fecha" value="${pedido.fecha}" >
        </div>
    <div class="form-group my-3">
        <button type="submit" class="btn btn-primary" >Guardar</button>
    </div>
  </form>
<jsp:include page="/layout/footer.jsp"></jsp:include>
