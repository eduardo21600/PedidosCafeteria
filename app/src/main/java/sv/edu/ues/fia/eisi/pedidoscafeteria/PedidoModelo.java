package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class PedidoModelo {
    private int idPedido;
    private String nomCliente;
    private String tipo;

    public PedidoModelo(int idPedido, String nomCliente, String tipo) {
        this.idPedido = idPedido;
        this.nomCliente = nomCliente;
        this.tipo = tipo;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
