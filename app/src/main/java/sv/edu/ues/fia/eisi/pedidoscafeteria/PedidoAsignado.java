package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class PedidoAsignado {
    private int idPedido;
    private String idUsuario;

    public PedidoAsignado(int idPedido, String idUsuario) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
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

}
