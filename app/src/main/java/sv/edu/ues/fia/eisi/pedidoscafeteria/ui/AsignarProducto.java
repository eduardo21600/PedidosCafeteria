package sv.edu.ues.fia.eisi.pedidoscafeteria.ui;

public class AsignarProducto{

    private int IDMENU;
    private int IDPRODUCTO;
    public AsignarProducto(){}
    public AsignarProducto(int IDMENU, int IDPRODUCTO) {
        this.IDMENU = IDMENU;
        this.IDPRODUCTO = IDPRODUCTO;
    }

    public int getIDMENU() {
        return IDMENU;
    }

    public void setIDMENU(int IDMENU) {
        this.IDMENU = IDMENU;
    }

    public int getIDPRODUCTO() {
        return IDPRODUCTO;
    }

    public void setIDPRODUCTO(int IDPRODUCTO) {
        this.IDPRODUCTO = IDPRODUCTO;
    }
}

