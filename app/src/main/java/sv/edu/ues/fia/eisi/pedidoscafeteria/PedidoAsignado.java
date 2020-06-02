package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class PedidoAsignado {
    private int idPedido;
    private int idUsuario;
    private int idPedidoAsignado;

    public PedidoAsignado(int idPedido, int idUsuario, int idPedidoAsignado) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.idPedidoAsignado = idPedidoAsignado;
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

    public int getIdPedidoAsignado() {
        return idPedidoAsignado;
    }

    public void setIdPedidoAsignado(int idPedidoAsignado) {
        this.idPedidoAsignado = idPedidoAsignado;
    }
}
