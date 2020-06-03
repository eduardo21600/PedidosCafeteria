package sv.edu.ues.fia.eisi.pedidoscafeteria;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ControladorBD extends SQLiteOpenHelper {

    private static final String nombreBD = "cafeteriaUES.s3db";
    private SQLiteDatabase db;
    //campos de las bases
    private static final String[] camposUsuario = {"", "", "", "", "", ""};


    public ControladorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //Preguntar a Spiderman si esta parte del código falla o hay dudas

    //CRUD usuario
    public String insertar(Usuario usuario) {

        String resultado = "Usuario creado, bienvenido " + usuario;
        long comprobador = 0;
        ContentValues usu = new ContentValues();
        usu.put("idUsuario", usuario.getIdUsuario());
        usu.put("idTipoUsuario", usuario.getIdTipoUsuario());
        usu.put("nombreUsuario", usuario.getNombreUsuario());
        usu.put("apellidoUsuario", usuario.getApellidoUsuario());
        usu.put("teleUsuario", usuario.getTeleUsuario());
        usu.put("contrasena", usuario.getContrasena());


        //comprobando integridad
        String[] tipoU = {String.valueOf(usuario.getIdTipoUsuario())};
        Cursor cur = db.query("TipoUsuario", null, "idTipoUsuario = ?", tipoU, null, null, null);

        if (cur.moveToFirst()) {
            comprobador = db.insert("usuario", null, usu);

            if (comprobador == -1 || comprobador == 0) {
                resultado = "Oh, oh, parece que ese id de usuario ya existe, prueba con otro";

            }

        } else {

            resultado = "El tipo de usuario no existe";
        }

        return resultado;
    }

    public Usuario ConsultaUsuario(String idUsuario) {
        String[] id = {idUsuario};
        Cursor cur = db.rawQuery("select * from Usuario where idUsuario =" + idUsuario, null);
        if (cur.moveToFirst()) {
            Usuario usu1 = new Usuario();
            usu1.setIdUsuario((cur.getInt(0)));
            usu1.setIdTipoUsuario((cur.getInt(1)));
            usu1.setContrasena(cur.getString(2));
            usu1.setNombreUsuario(cur.getString(3));
            usu1.setTeleUsuario(cur.getString(4));
            usu1.setApellidoUsuario(cur.getString(5));

            return usu1;

        } else {
            return null;
        }

    }

    public List<Usuario> ConsultaUsuarios() {
        Cursor cur = db.rawQuery("SELECT * FROM Usuario", null);
        List<Usuario> usus = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                usus.add(new Usuario(cur.getInt(0)
                        , cur.getInt(1)
                        , cur.getString(2)
                        , cur.getString(3)
                        , cur.getString(4)
                        , cur.getString(5)));
            } while (cur.moveToNext());


        }
        return usus;
    }

    public String actualizarUsuario(Usuario usuario) {
        String resultado = "datos actualizados";
        String[] id = {String.valueOf(usuario.getIdUsuario())};
        Cursor cur = db.query("Usuario", null, "idUsuario = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            String[] idTU = {String.valueOf(usuario.getIdTipoUsuario())};
            Cursor k = db.query("TipoUsuario", null, "idTipoUsuario = ?", idTU, null, null, null);
            if (k.moveToFirst()) {
                ContentValues usuact = new ContentValues();
                usuact.put("idUsuario", usuario.getIdUsuario());
                usuact.put("idTipoUsuario", usuario.getIdTipoUsuario());
                usuact.put("contrasena", usuario.getContrasena());
                usuact.put("nombreUsuario", usuario.getNombreUsuario());
                usuact.put("teleUsuario", usuario.getTeleUsuario());
                usuact.put("apellidoUsuario", usuario.getApellidoUsuario());
                db.update("Usuario", usuact, "idUsuario=?", id);

            } else {
                resultado = "el tipo de usuario no existe, pruebe con uno existente";
            }

        } else {
            resultado = "no hay registros de usuario con el código " + id;
        }
        return resultado;
    }

    public String eliminarUsuario(Usuario usuario)
            /*
            Con este método recomiendo que a la hora de usarlo se haga en un dialog box
            donde se le diga que si borra este usario se borraá tod lo relacionado a el
            y de ser si la respuesta se borre y de ser no, pos no pasa nada, que mas va a pasar
            jaja k kreisi, por cierto acabo de descubrir que cuando escribo to do se pone en amariilo
            https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
            bien loco

             */ {
        String resultado = "se ha eliminado el usuario " + usuario.getIdUsuario();
        String[] id = {String.valueOf(usuario.getIdTipoUsuario())};
        Cursor cur = db.query("Usuario", null, "idUsuario = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            Cursor k = db.query("PedidoRealizado", null, "idUsuario = ?", id, null, null, null);
            Cursor m = db.query("Local", null, "idUsuario = ?", id, null, null, null);
            if (k.moveToFirst()) {
                int cantPR = db.delete("PedidoRealizado", "idUsuario=" + usuario.getIdUsuario(), null);
                resultado = resultado + " y el/los " + cantPR + " pedidos de este usuario ";
            }
            if (m.moveToFirst()) {
                int cantL = db.delete("Local", "idUsuario =" + usuario.getIdUsuario(), null);
                resultado = resultado + " y el/los " + cantL + " locales de los que era propietario  ";
            }
            int cantU = db.delete("Usuario", "idUsuario =" + usuario.getIdUsuario(), null);

        } else {
            resultado = "el usuario no existe";
        }
        return null;

    }
    //CRUD tipoUsuario

    public String CrearTipoUsuario(TipoUsuario tipousuario) {
        String resultado = "Tipo de usuario creado ";
        ContentValues tusu = new ContentValues();
        tusu.put("idUsuario", tipousuario.getIdTipoUsuario());
        tusu.put("idTipoUsuario", tipousuario.getNomTipoUsuario());

        long comprobador = 0;
        comprobador = db.insert("TipoUsuario", null, tusu);
        if (comprobador == -1 || comprobador == 0) {
            resultado = "oh, oh ya existe un tipo de usuario con ese codigo ): ";
        }
        return resultado;
    }

    public TipoUsuario ConsultaTipoUsuario(String idTipoUsuario) {
        String[] id = {idTipoUsuario};
        Cursor cur = db.rawQuery("select * from TipoUsuario where idTipoUsuario =" + idTipoUsuario, null);
        if (cur.moveToFirst()) {
            TipoUsuario tusu1 = new TipoUsuario();
            tusu1.setIdTipoUsuario((cur.getInt(0)));
            tusu1.setNomTipoUsuario((cur.getString(1)));


            return tusu1;

        } else {
            return null;
        }


    }

    public List<TipoUsuario> ConsultaTiposUsuario() {
        Cursor cur = db.rawQuery("SELECT * FROM TipoUsuario", null);
        List<TipoUsuario> tusus = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                tusus.add(new TipoUsuario(cur.getInt(0), cur.getString(1)));
            } while (cur.moveToNext());


        }
        return tusus;
    }

    public String ActualizarTipoUsuario(TipoUsuario tipousuario) {
        String resultado = "tipo de dato actualizado";
        String[] id = {String.valueOf(tipousuario.getIdTipoUsuario())};
        Cursor cur = db.query("TipoUsuario", null, "idTipoUsuario = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            ContentValues tusuact = new ContentValues();
            tusuact.put("idTipoUsuario", tipousuario.getIdTipoUsuario());
            tusuact.put("nombreUsuario", tipousuario.getNomTipoUsuario());

            db.update("TipoUsuario", tusuact, "idTipoUsuario=?", id);

        } else {
            resultado = "dato no existente ";
        }

        return resultado;
    }


    public String eliminarTipoUsuario(TipoUsuario tipousuario) {
        int comprobador = 0;
        int cont = 0;
        String resultado = comprobador + " tipos de datos eliminados ";
        String[] id = {String.valueOf(tipousuario.getIdTipoUsuario())};
        Cursor cur = db.query("TipoUsuario", null, "idTipoUsuario = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            Cursor k = db.query("Usuario", null, "idTipoUsuario", id, null, null, null);
            if (k.moveToFirst()) {
                cont = db.delete("Usuario", "idTipoUsuario =" + tipousuario.getIdTipoUsuario(), null);
                resultado = resultado + ", " + cont + " Usuarios eliminados con ese código";
            }
            comprobador = db.delete("TipoUsuario", "idTipoUsuario =" + tipousuario.getIdTipoUsuario(), null);
        } else {
            resultado = "Ese tipo de usuario no existe";
        }
        return resultado;
    }

    //CRUD estadopedido

    public String CrearEstadoPedido(EstadoPedido estadopedido) {
        String resultado = "nuevo estado creado ";
        ContentValues esta7u7 = new ContentValues();
        esta7u7.put("idEstadoPedido", estadopedido.getIdEstadoPedido());
        esta7u7.put("descEstadoPedido", estadopedido.getDescEstadoPedido());

        long comprobador = 0;
        comprobador = db.insert("EstadoPedido", null, esta7u7);
        if (comprobador == -1 || comprobador == 0) {
            resultado = "oh, oh ya existe un estado de pedido con el codigo :( ";
        }
        return resultado;

    }

    public EstadoPedido ConsultaEstadoPedido(String idEstadoPedido)
    {

        String[] id = {idEstadoPedido};
        Cursor cur = db.rawQuery("select * from EstadoPedido where idEstadoPedido =" + id, null);
        if (cur.moveToFirst()) {
            EstadoPedido esta7u7 = new EstadoPedido();
            esta7u7.setIdEstadoPedido((cur.getInt(0)));
            esta7u7.setDescEstadoPedido((cur.getString(1)));


            return esta7u7;

        } else {
            return null;
        }


    }
    public List<EstadoPedido> ConsultaEstadosPedido() {
        Cursor cur = db.rawQuery("SELECT * FROM EstadoPedido", null);
        List<EstadoPedido> esta7u7 = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                esta7u7.add(new EstadoPedido(cur.getInt(0), cur.getString(1)));
            } while (cur.moveToNext());


        }
        return esta7u7;
    }
    public String ActualizarEstadoPedido(EstadoPedido estadopedido) {
        String resultado = "tipo de dato actualizado";
        String[] id = {String.valueOf(estadopedido.getIdEstadoPedido())};
        Cursor cur = db.query("EstadoPedido", null, "idEstadoPedido = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            ContentValues esta7u7 = new ContentValues();
            esta7u7.put("idEstadoPedido", estadopedido.getIdEstadoPedido());
            esta7u7.put("descEstadoPedido", estadopedido.getDescEstadoPedido());

            db.update("EstadoPedido", esta7u7, "idEstadoPedido=?", id);

        } else {
            resultado = "dato no existente ";
        }

        return resultado;
    }
    public String eliminarEstadoPedido(EstadoPedido estadopedido) {
        int comprobador = 0;
        int cont = 0;
        String resultado = comprobador + " estados  eliminados ";
        String[] id = {String.valueOf(estadopedido.getIdEstadoPedido())};
        Cursor cur = db.query("EstadoPedido", null, "idEstadoPedido = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            Cursor k = db.query("Pedido", null, "idEstadoPedido", id, null, null, null);
            if (k.moveToFirst()) {
                cont = db.delete("Pedido", "idEstadoPedido =" + estadopedido.getIdEstadoPedido(), null);
                resultado = resultado + ", " + cont + " Pedidos eliminados con ese estado";
            }
            comprobador = db.delete("EstadoPedido", "idEstadoPedido =" + estadopedido.getIdEstadoPedido(), null);
        } else {
            resultado = "Ese estado no existe";
        }
        return resultado;
    }

    //CRUD PRODUCTO
    public String CrearProducto(Producto producto) {
        String resultado = "producto creado ";
        ContentValues pro777 = new ContentValues();
        pro777.put("idProducto", producto.getIdProduto());
        pro777.put("NombreProducto", producto.getNombreProducto());
        pro777.put("precioUnitario", producto.getPrecioUnitario());
        pro777.put("descProducto", producto.getDescProducto());



        long comprobador = 0;
        comprobador = db.insert("Producto", null, pro777);
        if (comprobador == -1 || comprobador == 0) {
            resultado = "oh, oh ya existe un producto con ese codigo ): ";
        }
        return resultado;
    }

    public Producto ConsultaProducto(String idProducto) {
        String[] id = {idProducto};
        Cursor cur = db.rawQuery("select * from Producto where idProducto =" + idProducto, null);
        if (cur.moveToFirst()) {
            Producto pro777 = new Producto();
            pro777.setIdProduto((cur.getInt(0)));
            pro777.setNombreProducto((cur.getString(1)));
            pro777.setPrecioUnitario((cur.getInt(2)));
            pro777.setDescProducto((cur.getString(3)));



            return pro777;

        } else {
            return null;
        }


    }

    public List<Producto> ConsultaProductos() {
        Cursor cur = db.rawQuery("SELECT * FROM Producto", null);
        List<Producto> pro777 = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                pro777.add(new Producto(cur.getInt(0), cur.getString(1),cur.getInt(2),cur.getString(3)));
            } while (cur.moveToNext());


        }
        return pro777;
    }

    public String ActualizarProducto(Producto producto) {
        String resultado = "producto actualizado";
        String[] id = {String.valueOf(producto.getIdProduto())};
        Cursor cur = db.query("Producto", null, "idProducto = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            ContentValues pro777 = new ContentValues();
            pro777.put("idProducto", producto.getIdProduto());
            pro777.put("nombreProducto", producto.getNombreProducto());
            pro777.put("precioUnitario", producto.getPrecioUnitario());
            pro777.put("descProducto", producto.getDescProducto());

            db.update("Producto", pro777, "idProducto=?", id);

        } else {
            resultado = "dato no existente ";
        }

        return resultado;
    }


    public String eliminarProducto(Producto producto) {
        int comprobador = 0;
        int cont = 0;
        String resultado = comprobador + " productos eliminados ";
        String[] id = {String.valueOf(producto.getIdProduto())};
        Cursor cur = db.query("Producto", null, "idProducto = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            Cursor k = db.query("Menu", null, "idProducto", id, null, null, null);
            if (k.moveToFirst()) {
                cont = db.delete("Menu", "idProducto =" + producto.getIdProduto(), null);

                resultado = resultado + ", " + cont + " Menus eliminados con ese producto";
            }
            comprobador = db.delete("Producto", "idProducto =" + producto.getIdProduto(), null);
        } else {
            resultado = "Ese Producto no existe";
        }
        return resultado;
    }

    //CRUD MENU
    public String CrearMenu(Menu menu) {
        String resultado = "Menu creado ";
        ContentValues uwu = new ContentValues();
        uwu.put("idMenu", menu.getIdMenu());
        uwu.put("idProducto", menu.getIdProducto());
        uwu.put("idLocal", menu.getIdLocal());
        uwu.put("PrecioMenu", menu.getPrecioMenu());
        uwu.put("fechaDesdeMenu", menu.getFechaDesdeMenu());
        uwu.put("fechaHastaMenu", menu.getFechaHastaMenu());
        uwu.put("nomMenu", menu.getNomMenu());


        long comprobador = 0;
        comprobador = db.insert("Menu", null, uwu);
        if (comprobador == -1 || comprobador == 0) {
            resultado = "oh, oh ya existe un Menu con ese codigo ): ";
        }
        return resultado;
    }

    public Menu ConsultaMenu(String idMenu) {
        String[] id = {idMenu};
        Cursor cur = db.rawQuery("select * from Menu where idMenu =" + idMenu, null);
        if (cur.moveToFirst()) {
            Menu uwu = new Menu();
            uwu.setIdMenu((cur.getInt(0)));
            uwu.setIdProducto((cur.getInt(1)));
            uwu.setIdLocal((cur.getInt(2)));
            uwu.setPrecioMenu((cur.getInt(3)));
            uwu.setFechaDesdeMenu((cur.getString(4)));
            uwu.setFechaHastaMenu((cur.getString(5)));
            uwu.setNomMenu((cur.getString(6)));
            return uwu;
        } else {
            return null;
        }


    }

    public List<Menu> ConsultaMenus() {
        Cursor cur = db.rawQuery("SELECT * FROM Menu", null);
        List<Menu> uwu = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                uwu.add(new Menu(cur.getInt(0),
                        cur.getInt(1),
                        cur.getInt(2),
                        cur.getInt(3),
                        cur.getString(4),
                        cur.getString(5),
                        cur.getString(6)));
            } while (cur.moveToNext());


        }
        return uwu;
    }

    public String ActualizarMenu(Menu menu) {
        String resultado = "Menu actualizado";
        String[] id = {String.valueOf(menu.getIdMenu())};
        Cursor cur = db.query("Menu", null, "idMenu = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            ContentValues uwu = new ContentValues();
            uwu.put("idMenu", menu.getIdMenu());
            uwu.put("idProducto", menu.getIdProducto());
            uwu.put("idLocal", menu.getIdLocal());
            uwu.put("precioMenu", menu.getPrecioMenu());
            uwu.put("fechaDesdeMenu", menu.getFechaDesdeMenu());
            uwu.put("fechaHastaMenu", menu.getFechaHastaMenu());
            uwu.put("nomMenu", menu.getNomMenu());


            db.update("Menu", uwu, "idMenu=?", id);

        } else {
            resultado = "Ese menu no existente ";
        }

        return resultado;
    }


    public String eliminarMenu(Menu menu) {
        int comprobador = 0;
        int cont = 0;
        String resultado = comprobador + " Menu eliminados ";
        String[] id = {String.valueOf(menu.getIdMenu())};
        Cursor cur = db.query("Menu", null, "idMenu = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            Cursor k = db.query("DetallePedido", null, "idMenu", id, null, null, null);
            if (k.moveToFirst()) {
                cont = db.delete("DetallePedido", "idMenu =" + menu.getIdMenu(), null);

                resultado = resultado + ", " + cont + " Detalles eliminados con este producto";
            }
            comprobador = db.delete("Menu", "idMenu =" + menu.getIdMenu(), null);
        } else {
            resultado = "Ese Menu no existe";
        }
        return resultado;
    }

        //CRUD DETALLE PEDIDO
        public String Crear(DetallePedido detallepedido) {
            String resultado = "detalle de pedido creado ";
            ContentValues depe = new ContentValues();
            depe.put("cantidad", detallepedido.getCantidad());
            depe.put("subtotal", detallepedido.getSubtotal());
            depe.put("idDetallePedido", detallepedido.getIdDetallePedido());
            depe.put("idMenu", detallepedido.getIdMenu());


            long comprobador = 0;
            comprobador = db.insert("DetallePedido", null, depe);
            if (comprobador == -1 || comprobador == 0) {
                resultado = "oh, oh ya existe un DetallePedido con ese codigo ): ";
            }
            return resultado;
        }

    public DetallePedido ConsultaDetallePedido(String iddetallePedido) {
        String[] id = {iddetallePedido};
        Cursor cur = db.rawQuery("select * from DetallePedido where idDetallePedido =" + iddetallePedido, null);
        if (cur.moveToFirst()) {
            DetallePedido depe = new DetallePedido();
            depe.setCantidad((cur.getInt(0)));
            depe.setSubtotal((cur.getInt(1)));
            depe.setIdDetallePedido((cur.getInt(2)));
            depe.setIdMenu((cur.getInt(3)));


            return depe;

        } else {
            return null;
        }


    }

    public List<DetallePedido> ConsultaDetallePedidos() {
        Cursor cur = db.rawQuery("SELECT * FROM DetallePedido", null);
        List<DetallePedido> depe = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                depe.add(new DetallePedido(cur.getInt(0),
                        cur.getInt(1),
                        cur.getInt(2),
                        cur.getInt(3)));
            } while (cur.moveToNext());


        }
        return depe;
    }

    public String ActualizarDetallePedido(DetallePedido detallepedido) {
        String resultado = "Detalle de pedido actualizado";
        String[] id = {String.valueOf(detallepedido.getIdDetallePedido())};
        Cursor cur = db.query("DetallePedido", null, "idDetallePedido = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            ContentValues depe = new ContentValues();
            depe.put("cantidad", detallepedido.getCantidad());
            depe.put("subtotal", detallepedido.getSubtotal());
            depe.put("idDetallePedido", detallepedido.getIdDetallePedido());
            depe.put("idMenu", detallepedido.getIdMenu());


            db.update("DetallePedido", depe, "idDetallePedido=?", id);

        } else {
            resultado = "no existe ese detalle de pedido";
        }

        return resultado;
    }


    public String eliminarDetallePedido(DetallePedido detallepedido) {
        int comprobador = 0;
        int cont = 0;
        String resultado = comprobador + " detalle de pedidos eliminados ";
        String[] id = {String.valueOf(detallepedido.getIdDetallePedido())};
        Cursor cur = db.query("DetallePedido", null, "idDetallePedido = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            Cursor k = db.query("Pedido", null, "idDetallePedido", id, null, null, null);
            if (k.moveToFirst()) {
                cont = db.delete("Pedido", "idDetallePedido =" + detallepedido.getIdDetallePedido(), null);
                resultado = resultado + ", " + cont + "  pedidos con ese código";
            }
            comprobador = db.delete("DetallePedido", "idDetallePedido =" + detallepedido.getIdDetallePedido(), null);
        } else {
            resultado = "Ese detalle de pedido no existe";
        }
        return resultado;
    }



            //CRUD LOCAL











        //Aquí termina la ayuda de tu vecino el hombre araña

        //Preguntar a Vane si esta parte del código falla o hay dudas

        //Aquí termina la parte de Vane


        //Integridad
        //fin integridad

}
