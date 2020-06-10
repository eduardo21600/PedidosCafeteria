package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class AccesoUsuario {
    private String idUsuario;
    private String idOpcion;
    private int idAccesoUsuario;

    public AccesoUsuario(){

    }

    public AccesoUsuario(String idUsuario, String idOpcion, int idAccesoUsuario) {
        this.idUsuario = idUsuario;
        this.idOpcion = idOpcion;
        this.idAccesoUsuario = idAccesoUsuario;
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

    public int getIdAccesoUsuario() {
        return idAccesoUsuario;
    }

    public void setIdAccesoUsuario(int idAccesoUsuario) {
        this.idAccesoUsuario = idAccesoUsuario;
    }

}
