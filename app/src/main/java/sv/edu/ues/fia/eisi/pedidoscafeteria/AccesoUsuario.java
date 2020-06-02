package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class AccesoUsuario {
    private int idUsuario;
    private String idOpcion;
    private int idAccesoUsuario;

    public AccesoUsuario(){

    }

    public AccesoUsuario(int idUsuario, String idOpcion, int idAccesoUsuario) {
        this.idUsuario = idUsuario;
        this.idOpcion = idOpcion;
        this.idAccesoUsuario = idAccesoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
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
