package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Ubicacion {

    private int idUbicacion;
    private int idFacultad;
    //private int idPedido;
    private String direcUbicacion;
    private String nomUbicacion;
    private String puntoRefUbicacion;
    private String idUsuario;

    public Ubicacion(){

    }

    public Ubicacion(int idUbicacion, int idFacultad, /*int idPedido,*/ String direcUbicacion, String nomUbicacion, String puntoRefUbicacion, String idUsuario) {
        this.idUbicacion = idUbicacion;
        this.idFacultad = idFacultad;
//        this.idPedido = idPedido;
        this.direcUbicacion = direcUbicacion;
        this.nomUbicacion = nomUbicacion;
        this.puntoRefUbicacion = puntoRefUbicacion;
        this.idUsuario = idUsuario;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    /*public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }*/

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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }


}
