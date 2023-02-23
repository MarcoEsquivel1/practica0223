package controllers;

import dao.ClienteDAO;
import dao.PedidoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Cliente;
import models.Pedido;
import utils.Conexion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "pedido", value = "/pedido")
public class pedido extends HttpServlet {
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
