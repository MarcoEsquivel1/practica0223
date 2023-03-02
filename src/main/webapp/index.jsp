<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 09/02/2023
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1>Inicio</h1>
<c:if test="${error != null}">
    <div class="alert alert-danger" role="alert">
        ${error}
    </div>
</c:if>

<jsp:include page="/layout/footer.jsp"></jsp:include>
