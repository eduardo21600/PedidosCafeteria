package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class PedidoRealizado {

    private int idPedido;
    private int idUsuario;
    private int idPedidoRealizado;
    private String tipo;

    public PedidoRealizado(){

    }

    public PedidoRealizado(int idPedido, int idUsuario, int idPedidoRealizado) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.idPedidoRealizado = idPedidoRealizado;
        this.tipo = tipo;
    }

    public PedidoRealizado(int idPedido, int idUsuario, int idPedidoRealizado, String tipo) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.idPedidoRealizado = idPedidoRealizado;
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
