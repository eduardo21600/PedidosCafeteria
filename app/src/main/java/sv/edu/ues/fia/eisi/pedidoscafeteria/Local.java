package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Local {
    private int idLocal;
    private int idUsuario;
    private String nombreLocal; // 50


    public Local(){}

    public Local(int idLocal,int idUsuario, String nombreLocal) {
        this.idLocal = idLocal;
        this.nombreLocal = nombreLocal;
        this.idUsuario = idUsuario;
    }



    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }
}
