package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class DetallePedido {
    private int cantidad;
    private double subtotal;
    private int idDetallePedido;




    public DetallePedido(){}

    public DetallePedido(int cantidad, int subtotal, int idDetallePedido) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.idDetallePedido = idDetallePedido;

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
}
