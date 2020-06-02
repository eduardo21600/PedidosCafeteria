package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class DetalleProducto {
    private int idDetallePedido;
    private int idMenu;
    private int cantidad;
    private int subtotal;

    public DetalleProducto(){}

    public DetalleProducto(int idDetallePedido, int idMenu, int cantidad, int subtotal) {
        this.idDetallePedido = idDetallePedido;
        this.idMenu = idMenu;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}

