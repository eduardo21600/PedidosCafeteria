package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class TipoUsuario {
    private int idTipoUsuario;
    private String nomTipoUsuario;

    public TipoUsuario(){}

    public TipoUsuario(int idTipoUsuario, String nomTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
        this.nomTipoUsuario = nomTipoUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNomTipoUsuario() {
        return nomTipoUsuario;
    }

    public void setNomTipoUsuario(String nomTipoUsuario) {
        this.nomTipoUsuario = nomTipoUsuario;
    }
}
