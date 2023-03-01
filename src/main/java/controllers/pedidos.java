package controllers;

import dao.PedidoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Pedido;
import utils.Conexion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "pedidos", urlPatterns = {"/pedidos", "/pedidos/create", "/pedidos/update", "/pedidos/delete"})
public class pedidos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "views/pedido/PedidoList.jsp";
        List<Pedido> pedidos = null;
        try {
            PedidoDAO PedidoDAO = new PedidoDAO(Conexion.getConnection());
            pedidos = PedidoDAO.getPedidos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("pedidos", pedidos);
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
