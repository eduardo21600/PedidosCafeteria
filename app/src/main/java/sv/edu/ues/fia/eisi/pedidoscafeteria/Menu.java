package sv.edu.ues.fia.eisi.pedidoscafeteria;

import java.util.List;

public class Menu
{
    private  int idMenu;
    private List<Producto> productos;
    private int idLocal;
    private double precioMenu;
    private String fechaDesdeMenu; //van string porque sqlite no tiene formato para fechas
    private String fechaHastaMenu;
    private String nomMenu;

    public Menu(){}

    public Menu(int idMenu, List<Producto> productos, int idLocal, double precioMenu, String fechaDesdeMenu, String fechaHastaMenu, String nomMenu) {
        this.idMenu = idMenu;
        this.productos = productos;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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
