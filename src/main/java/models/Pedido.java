package models;

import java.math.BigDecimal;
import java.util.Date;

public class Pedido {
    private int id;
    private int idCliente;
    private Date fecha;
    private BigDecimal total;
    private String estado;
    private String nombreCliente;

    public Pedido() {
    }

    public Pedido(int id, int idCliente, Date fecha, BigDecimal total, String estado, String nombreCliente) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
    }

    public Pedido(int id, int idCliente, Date fecha, BigDecimal total, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
