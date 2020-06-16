package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class ProductoAsignar {
    private int idmenu;
    private int idProducto;
    public ProductoAsignar() {
    }

    public ProductoAsignar(int idmenu, int idProducto) {
        this.idmenu = idmenu;
        this.idProducto = idProducto;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}
