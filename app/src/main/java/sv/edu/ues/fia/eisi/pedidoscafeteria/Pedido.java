package sv.edu.ues.fia.eisi.pedidoscafeteria;

import java.util.Date;

public class Pedido {

    private int idPedido;
    private int idDetallePedido;
    private String idEstadoPedido;
    private int idLocal;
    private int idUbicacion;
    private String fechaPedido;
    private float totalPedido;

    public Pedido(){

    }

    public Pedido(int idPedido, int idDetallePedido, String idEstadoPedido, int idLocal, int idUbicacion, String fechaPedido, float totalPedido) {
        this.idPedido = idPedido;
        this.idDetallePedido = idDetallePedido;
        this.idEstadoPedido = idEstadoPedido;
        this.idLocal = idLocal;
        this.idUbicacion = idUbicacion;
        this.fechaPedido = fechaPedido;
        this.totalPedido= totalPedido;
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

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    public float getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(float totalPedido) {
        this.totalPedido = totalPedido;
    }

}
