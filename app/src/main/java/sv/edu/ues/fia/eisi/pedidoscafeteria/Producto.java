package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Producto {
    private int idProduto;
    private String nombreProducto;
    private double precioUnitario;
    private String descProducto;

    public Producto(){}

    public Producto(int idProduto, String nombreProducto,double precioUnitario, String descProducto) {
        this.idProduto = idProduto;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.descProducto = descProducto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }
}
