package sv.edu.ues.fia.eisi.pedidoscafeteria;

import java.util.Date;

public class Pedido {

    private int idPedido;
    private int idDetallePedido;
    private String idEstadoPedido;
    private int idLocal;
    private int idUbicacion;
    private Date fechaPedido;

    public Pedido(){

    }

    public Pedido(int idPedido, int idDetallePedido, String idEstadoPedido, int idLocal, int idUbicacion, Date fechaPedido) {
        this.idPedido = idPedido;
        this.idDetallePedido = idDetallePedido;
        this.idEstadoPedido = idEstadoPedido;
        this.idLocal = idLocal;
        this.idUbicacion = idUbicacion;
        this.fechaPedido = fechaPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public String getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(String idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
