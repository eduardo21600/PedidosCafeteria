package sv.edu.ues.fia.eisi.pedidoscafeteria;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Pedido{

    private int idPedido;
    private ArrayList<DetallePedido> detallePedidos;
    private int idEstadoPedido;
    private int idLocal;
    private int idUbicacion;
    private String fechaPedido;
    private double totalPedido;

    public Pedido(){

    }

    public Pedido(int idPedido, ArrayList<DetallePedido> detallePedidos, int idEstadoPedido, int idLocal, int idUbicacion, String fechaPedido, double totalPedido) {
        this.idPedido = idPedido;
        this.detallePedidos = detallePedidos;
        this.idEstadoPedido = idEstadoPedido;
        this.idLocal = idLocal;
        this.idUbicacion = idUbicacion;
        this.fechaPedido = fechaPedido;
        this.totalPedido = totalPedido;
    }

    public Pedido(String idPedido, String idDetallePedido) {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public ArrayList<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(ArrayList<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public int getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(int idEstadoPedido) {
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
    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

}
