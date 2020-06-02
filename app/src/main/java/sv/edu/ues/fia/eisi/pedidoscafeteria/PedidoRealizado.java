package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class PedidoRealizado {

    private int idPedido;
    private int idUsuario;
    private int idPedidoRealizado;

    public PedidoRealizado(){

    }

    public PedidoRealizado(int idPedido, int idUsuario, int idPedidoRealizado) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.idPedidoRealizado = idPedidoRealizado;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPedidoRealizado() {
        return idPedidoRealizado;
    }

    public void setIdPedidoRealizado(int idPedidoRealizado) {
        this.idPedidoRealizado = idPedidoRealizado;
    }
}
