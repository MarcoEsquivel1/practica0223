package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Cliente;
import dao.ClienteDAO;
import utils.Conexion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "cliente", value = "/cliente")
public class cliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "views/cliente/ClienteList.jsp";
        List<Cliente> clientes = null;
        try {
            ClienteDAO ClienteDAO = new ClienteDAO(Conexion.getConnection());
            clientes = ClienteDAO.getClientes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
