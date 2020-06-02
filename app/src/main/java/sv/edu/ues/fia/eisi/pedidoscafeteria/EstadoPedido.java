package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class EstadoPedido {
    private int idEstadoPedido;
    private  String descEstadoPedido;

    public EstadoPedido(){}

    public EstadoPedido(int idEstadoPedido, String descEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
        this.descEstadoPedido = descEstadoPedido;
    }

    public int getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(int idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public String getDescEstadoPedido() {
        return descEstadoPedido;
    }

    public void setDescEstadoPedido(String descEstadoPedido) {
        this.descEstadoPedido = descEstadoPedido;
    }
}
