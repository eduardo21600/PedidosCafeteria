package sv.edu.ues.fia.eisi.pedidoscafeteria;

import java.util.concurrent.atomic.AtomicInteger;

public class Facultad {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int idFacultad;
    private String nomFacultad;

    public Facultad(){

    }

    public Facultad(String nomFacultad) {
        this.idFacultad = count.incrementAndGet();
        this.nomFacultad = nomFacultad;
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
