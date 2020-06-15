package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class DetallePedido {

    private int cantidad;
    private double subtotal;
    private int idDetallePedido;

    private int idMenu;

    public DetallePedido(){}

    public DetallePedido(int cantidad, double subtotal, int idDetallePedido, int idMenu) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.idDetallePedido = idDetallePedido;
        this.idMenu = idMenu;
    }


    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

}
