package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Usuario {
    private int idUsuario;
    private int idTipoUsuario;
    private String contrasena;
    private String nombreUsuario;
    private String teleUsuario;
    private String apellidoUsuario;

    public Usuario(){}

    public Usuario(int idUsuario, int idTipoUsuario, String contrasena, String nombreUsuario, String teleUsuario, String apellidoUsuario) {
        this.idUsuario = idUsuario;
        this.idTipoUsuario = idTipoUsuario;
        this.contrasena = contrasena;
        this.nombreUsuario = nombreUsuario;
        this.teleUsuario = teleUsuario;
        this.apellidoUsuario = apellidoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTeleUsuario() {
        return teleUsuario;
    }

    public void setTeleUsuario(String teleUsuario) {
        this.teleUsuario = teleUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }
}
