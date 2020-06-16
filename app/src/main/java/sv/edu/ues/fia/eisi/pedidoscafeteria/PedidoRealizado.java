package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class PedidoRealizado {

    private int idPedido;
    private String idUsuario;
    private String tipo;

    public PedidoRealizado(){

    }

    public PedidoRealizado(int idPedido, String idUsuario, String tipo) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
