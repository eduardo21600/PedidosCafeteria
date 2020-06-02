package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Facultad {
    private String idFacultad;
    private String nomFacultad;

    public Facultad(){

    }

    public Facultad(String idFacultad, String nomFacultad) {
        this.idFacultad = idFacultad;
        this.nomFacultad = nomFacultad;
    }

    public String getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(String idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNomFacultad() {
        return nomFacultad;
    }

    public void setNomFacultad(String nomFacultad) {
        this.nomFacultad = nomFacultad;
    }



}
