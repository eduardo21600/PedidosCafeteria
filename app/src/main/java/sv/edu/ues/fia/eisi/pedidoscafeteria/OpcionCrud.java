package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class OpcionCrud {

    private String idOpcion;
    private String descOpcion;
    private int numCrud;

    public OpcionCrud(){

    }

    public OpcionCrud(String idOpcion, String descOpcion, int numCrud) {
        this.idOpcion = idOpcion;
        this.descOpcion = descOpcion;
        this.numCrud = numCrud;
    }

    public String getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(String idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getDescOpcion() {
        return descOpcion;
    }

    public void setDescOpcion(String descOpcion) {
        this.descOpcion = descOpcion;
    }

    public int getNumCrud() {
        return numCrud;
    }

    public void setNumCrud(int numCrud) {
        this.numCrud = numCrud;
    }

}
