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

@WebServlet(name = "clientes", urlPatterns = {"/clientes", "/clientes/create", "/clientes/update", "/clientes/delete", "/clientes/save", "/clientes/edit" , "/clientes/destroy"})
public class clientes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/clientes/create")) {
            showNewForm(request, response);
        } else if (action.equals("/clientes/edit")) {
            showEditForm(request, response);
        } else if (action.equals("/clientes/delete")) {
            deleteCliente(request, response);
        } else {
            listClientes(request, response);
        }

    }

    private void listClientes(HttpServletRequest request, HttpServletResponse response) {
        String view = "views/cliente/ClienteList.jsp";
        List<Cliente> clientes = null;
        try {
            ClienteDAO ClienteDAO = new ClienteDAO(Conexion.getConnection());
            clientes = ClienteDAO.getClientes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("clientes", clientes);
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/cliente/ClienteForm.jsp";
        try {
            request.setAttribute("cliente", new Cliente());
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/cliente/ClienteUpdate.jsp";
        searchCliente(request, response, view);
    }

    private void deleteCliente(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/cliente/ClienteDelete.jsp";
        searchCliente(request, response, view);
    }

    private void searchCliente(HttpServletRequest request, HttpServletResponse response, String view) {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = null;
        try {
            ClienteDAO clienteDAO = new ClienteDAO(Conexion.getConnection());
            cliente = clienteDAO.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("cliente", cliente);
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/clientes/save")) {
            saveCliente(request, response);
        }else if (action.equals("/clientes/update")) {
            updateCliente(request, response);
        } else if (action.equals("/clientes/destroy")) {
            destroyCliente(request, response);
        }
    }

    private void saveCliente(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/cliente/ClienteList.jsp";
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setDireccion(request.getParameter("direccion"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setEmail(request.getParameter("email"));
        try {
            ClienteDAO clienteDAO = new ClienteDAO(Conexion.getConnection());
            clienteDAO.insertar(cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/clientes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCliente(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/cliente/ClienteList.jsp";
        Cliente cliente = new Cliente();
        cliente.setId(Integer.parseInt(request.getParameter("id")));
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setDireccion(request.getParameter("direccion"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setEmail(request.getParameter("email"));
        try {
            ClienteDAO clienteDAO = new ClienteDAO(Conexion.getConnection());
            clienteDAO.actualizar(cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/clientes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void destroyCliente(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/cliente/ClienteList.jsp";
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            ClienteDAO clienteDAO = new ClienteDAO(Conexion.getConnection());
            clienteDAO.eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/clientes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
