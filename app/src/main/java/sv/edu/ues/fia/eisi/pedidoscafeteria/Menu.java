package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Menu
{
    private  int idMenu;
    private int idProducto;
    private int idLocal;
    private double precioMenu;
    private String fechaDesdeMenu; //van string porque sqlite no tiene formato para fechas
    private String fechaHastaMenu;
    private String nomMenu;

    public Menu(){}


    public Menu(int idMenu, int idProducto, int idLocal, double precioMenu, String fechaDesdeMenu, String fechaHastaMenu, String nomMenu) {
        this.idMenu = idMenu;
        this.idProducto = idProducto;
        this.idLocal = idLocal;
        this.precioMenu = precioMenu;
        this.fechaDesdeMenu = fechaDesdeMenu;
        this.fechaHastaMenu = fechaHastaMenu;
        this.nomMenu = nomMenu;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public double getPrecioMenu() {
        return precioMenu;
    }

    public void setPrecioMenu(double precioMenu) {
        this.precioMenu = precioMenu;
    }

    public String getFechaDesdeMenu() {
        return fechaDesdeMenu;
    }

    public void setFechaDesdeMenu(String fechaDesdeMenu) {
        this.fechaDesdeMenu = fechaDesdeMenu;
    }

    public String getFechaHastaMenu() {
        return fechaHastaMenu;
    }

    public void setFechaHastaMenu(String fechaHastaMenu) {
        this.fechaHastaMenu = fechaHastaMenu;
    }

    public String getNomMenu() {
        return nomMenu;
    }

    public void setNomMenu(String nomMenu) {
        this.nomMenu = nomMenu;
    }
}
