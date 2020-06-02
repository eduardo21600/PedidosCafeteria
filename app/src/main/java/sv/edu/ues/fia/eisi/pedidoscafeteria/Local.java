package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Local {
    private int idLocal;
    private String nombreLocal; // 50

    public Local(){}

    public Local(int idLocal, String nombreLocal) {
        this.idLocal = idLocal;
        this.nombreLocal = nombreLocal;
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
