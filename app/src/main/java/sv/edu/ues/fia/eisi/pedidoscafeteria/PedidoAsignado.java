package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class PedidoAsignado {
    private int idPedido;
    private String idUsuario;
    private int idPedidoAsignado;

    public PedidoAsignado(int idPedido, String idUsuario, int idPedidoAsignado) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.idPedidoAsignado = idPedidoAsignado;
    }

    public PedidoAsignado() {

    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPedidoAsignado() {
        return idPedidoAsignado;
    }

    public void setIdPedidoAsignado(int idPedidoAsignado) {
        this.idPedidoAsignado = idPedidoAsignado;
    }
}
