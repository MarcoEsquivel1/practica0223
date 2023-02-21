package dao;
import models.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection con;

    public ClienteDAO(Connection con) {
        this.con = con;
    }

    public List<Cliente> getClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            Cliente cliente = new Cliente(id, nombre, direccion, telefono, email);
            clientes.add(cliente);
        }
        return clientes;
    }

    public Cliente buscarPorId(int id) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            cliente = new Cliente(id, nombre, direccion, telefono, email);
        }
        return cliente;
    }

    public void insertar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nombre, direccion, telefono, email) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getDireccion());
        ps.setString(3, cliente.getTelefono());
        ps.setString(4, cliente.getEmail());
        ps.executeUpdate();
    }

    public void actualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, direccion = ?, telefono = ?, email = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getDireccion());
        ps.setString(3, cliente.getTelefono());
        ps.setString(4, cliente.getEmail());
        ps.setInt(5, cliente.getId());
        ps.executeUpdate();
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
