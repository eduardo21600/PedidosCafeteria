package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Ubicacion {

    private int idUbicacion;
    private String idFacultad;
    private int idPedido;
    private String direcUbicacion;
    private String nomUbicacion;
    private String puntoRefUbicacion;

    public Ubicacion(){

    }

    public Ubicacion(int idUbicacion, String idFacultad, int idPedido, String direcUbicacion, String nomUbicacion, String puntoRefUbicacion) {
        this.idUbicacion = idUbicacion;
        this.idFacultad = idFacultad;
        this.idPedido = idPedido;
        this.direcUbicacion = direcUbicacion;
        this.nomUbicacion = nomUbicacion;
        this.puntoRefUbicacion = puntoRefUbicacion;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(String idFacultad) {
        this.idFacultad = idFacultad;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDirecUbicacion() {
        return direcUbicacion;
    }

    public void setDirecUbicacion(String direcUbicacion) {
        this.direcUbicacion = direcUbicacion;
    }

    public String getNomUbicacion() {
        return nomUbicacion;
    }

    public void setNomUbicacion(String nomUbicacion) {
        this.nomUbicacion = nomUbicacion;
    }

    public String getPuntoRefUbicacion() {
        return puntoRefUbicacion;
    }

    public void setPuntoRefUbicacion(String puntoRefUbicacion) {
        this.puntoRefUbicacion = puntoRefUbicacion;
    }

}
