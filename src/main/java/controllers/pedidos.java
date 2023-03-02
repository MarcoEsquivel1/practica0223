package controllers;

import dao.ClienteDAO;
import dao.PedidoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Pedido;

import utils.Conexion;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import models.Cliente;

@WebServlet(name = "pedidos", urlPatterns = {"/pedidos", "/pedidos/create", "/pedidos/update", "/pedidos/delete"})
public class pedidos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/pedidos/create")) {
            showNewForm(request, response);
        } else if (action.equals("/pedidos/edit")) {
            showEditForm(request, response);
        } else if (action.equals("/pedidos/delete")) {
            deletePedido(request, response);
        } else {
            listPedidos(request, response);
        }

    }
    
     private void listPedidos(HttpServletRequest request, HttpServletResponse response) {
        String view = "views/Pedidos/PedidoList.jsp";
        List<Pedido> Pedidos = null;
        try {
            PedidoDAO pedidosDAO = new PedidoDAO(Conexion.getConnection());
            Pedidos = pedidosDAO.getPedidos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("pedidos", Pedidos);

        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/pedido/PedidoForm.jsp";
        try {
            request.setAttribute("pedido", new Pedido());
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/pedido/PedidoUpdate.jsp";
        searchPedido(request, response, view);
    }

    private void deletePedido(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/pedido/PedidoDelete.jsp";
        searchPedido(request, response, view);
    }

    private void searchPedido(HttpServletRequest request, HttpServletResponse response, String view) {
        int id = Integer.parseInt(request.getParameter("id"));
        Pedido pedido = null;
        try {
            PedidoDAO pedidoDAO = new PedidoDAO(Conexion.getConnection());
            pedido = pedidoDAO.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("pedido", pedido);
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
        if (action.equals("/pedido/save")) {
            try {
                savePedido(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("/pedido/update")) {
            try {
                updatePedido(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("/pedido/destroy")) {
            destroyPedido(request, response);
        }
    }

    private void savePedido(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String view = "../views/pedido/PedidoList.jsp";
        Pedido pedido = new Pedido();
        pedido.setTotal(BigDecimal.valueOf(Double.parseDouble(request.getParameter("total"))));
        pedido.setEstado(request.getParameter("estado"));
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormateada = formato.parse(request.getParameter("fecha").toString());
        pedido.setFecha(dataFormateada);

        try {
            PedidoDAO pedidoDAO = new PedidoDAO(Conexion.getConnection());
            pedidoDAO.insertar(pedido);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/pedidos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePedido(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String view = "../views/pedido/PedidoList.jsp";
        Pedido pedido = new Pedido();
        pedido.setId(Integer.parseInt(request.getParameter("id")));
        pedido.setTotal(BigDecimal.valueOf(Double.parseDouble(request.getParameter("total"))));
        pedido.setEstado(request.getParameter("estado"));
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormateada = formato.parse(request.getParameter("fecha").toString());
        pedido.setFecha(dataFormateada);
        try {
            PedidoDAO pedidoDAO = new PedidoDAO(Conexion.getConnection());
            pedidoDAO.actualizar(pedido);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/pedidos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void destroyPedido(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/pedido/PedidoList.jsp";
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            PedidoDAO pedidoDAO = new PedidoDAO(Conexion.getConnection());
            pedidoDAO.eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/pedidos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
}
