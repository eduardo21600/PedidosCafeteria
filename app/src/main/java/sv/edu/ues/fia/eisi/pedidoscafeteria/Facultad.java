package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Facultad {
    private int idFacultad;
    private String nomFacultad;

    public Facultad(){

    }

    public Facultad(int idFacultad, String nomFacultad) {
        this.idFacultad = idFacultad;
        this.nomFacultad = nomFacultad;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNomFacultad() {
        return nomFacultad;
    }

    public void setNomFacultad(String nomFacultad) {
        this.nomFacultad = nomFacultad;
    }



}
