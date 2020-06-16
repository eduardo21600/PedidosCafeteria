package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class AccesoUsuario {
    private String idUsuario;
    private String idOpcion;

    public AccesoUsuario(){

    }

    public AccesoUsuario(String idUsuario, String idOpcion) {
        this.idUsuario = idUsuario;
        this.idOpcion = idOpcion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(String idOpcion) {
        this.idOpcion = idOpcion;
    }

}
