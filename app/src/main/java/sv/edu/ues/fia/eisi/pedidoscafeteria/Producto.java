package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Producto {
    private int idProduto;
    private String nombreProducto;
    private int precioUnitario;
    private String descProducto;

    public Producto(){}

    public Producto(int idProduto, String nombreProducto, int precioUnitario, String descProducto) {
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

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }
}
