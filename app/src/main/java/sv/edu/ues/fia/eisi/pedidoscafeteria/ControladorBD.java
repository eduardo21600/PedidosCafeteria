package sv.edu.ues.fia.eisi.pedidoscafeteria;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ControladorBD {
    private SQLiteDatabase db;
    private DataBaseHelper DBHelper;
    private final Context context;
    public ControladorBD(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DataBaseHelper(context);

    }



    private static class DataBaseHelper extends SQLiteOpenHelper  {
        private static final String nombreBD = "cafeteriaUES.s3db";
        private static final int VERSION = 1;

        public DataBaseHelper(Context context) {
            super(context, nombreBD, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL("create table ACCESOUSUARIO \n" +
                        "(\n" +
                        "   IDUSUARIO            INTEGER              not null,\n" +
                        "   ID_OPCION            CHAR(3)              not null,\n" +
                        "   IDACCESOUSUARIO      INTEGER              not null,\n" +
                        "   constraint PK_ACCESOUSUARIO primary key (IDUSUARIO, ID_OPCION, IDACCESOUSUARIO)\n" +
                        ");");
                db.execSQL("create table DETALLEPEDIDO\n" +
                        "(\n" +
                        "   CANTIDAD             smallint not null,\n" +
                        "   SUBTOTAL             real not null,\n" +
                        "   IDDETALLEPEDIDO      int not null,\n" +
                        "   IDMENU               int not null,\n" +
                        "   primary key (IDDETALLEPEDIDO)\n" +
                        ");\n");
                db.execSQL("create table ESTADPEDIDO\n" +
                        "(\n" +
                        "   IDESTADOPEDIDO       char(2) not null,\n" +
                        "   DESCESTADOPEDIDO     varchar(30) not null,\n" +
                        "   primary key (IDESTADOPEDIDO)\n" +
                        ");");
                db.execSQL("create table FACULTAD\n" +
                        "(\n" +
                        "   IDFACULTAD           char(2) not null,\n" +
                        "   NOMFACULTAD          varchar(30) not null,\n" +
                        "   primary key (IDFACULTAD)\n" +
                        ");");
                db.execSQL("create table LOCAL\n" +
                        "(\n" +
                        "   IDLOCAL              int not null,\n" +
                        "   IDUSUARIO            int,\n" +
                        "   NOMBRELOCAL          varchar(50) not null,\n" +
                        "   primary key (IDLOCAL)\n" +
                        ");");
                db.execSQL("create table MENU\n" +
                        "(\n" +
                        "   IDMENU               int not null,\n" +
                        "   IDPRODUCTO           int not null,\n" +
                        "   IDLOCAL              int,\n" +
                        "   PRECIOMENU           numeric(12,2) not null,\n" +
                        "   FECHADESDEMENU       date not null,\n" +
                        "   FECHAHASTAMENU       date not null,\n" +
                        "   NOMMENU              varchar(50) not null,\n" +
                        "   primary key (IDMENU)\n" +
                        ");");
                db.execSQL("create table OPCIONCRUD\n" +
                        "(\n" +
                        "   ID_OPCION            char(3) not null,\n" +
                        "   DESOPCION            varchar(30) not null,\n" +
                        "   NUMCRUD              int not null,\n" +
                        "   primary key (ID_OPCION)\n" +
                        ");");
                db.execSQL("create table PEDIDO\n" +
                        "(\n" +
                        "   IDPEDIDO             int not null,\n" +
                        "   IDDETALLEPEDIDO      int,\n" +
                        "   IDESTADOPEDIDO       char(2),\n" +
                        "   IDLOCAL              int,\n" +
                        "   IDUBICACION          int not null,\n" +
                        "   FECHAPEDIDO          time not null,\n" +
                        "   TOTALPEDIDO          real not null,\n" +
                        "   primary key (IDPEDIDO)\n" +
                        ");");
                db.execSQL("create table PEDIDOREALIZADO\n" +
                        "(\n" +
                        "   IDPEDIDO             int not null,\n" +
                        "   IDUSUARIO            int not null,\n" +
                        "   IDPEDIDOREALIZADO    int not null,\n" +
                        "   primary key (IDPEDIDO, IDUSUARIO, IDPEDIDOREALIZADO)\n" +
                        ");");
                db.execSQL("create table PEDIDOSASIGNADOS\n" +
                        "(\n" +
                        "   IDPEDIDO             int not null,\n" +
                        "   IDUSUARIO            int not null,\n" +
                        "   IDPEDIDOASIGNADO     int not null,\n" +
                        "   primary key (IDPEDIDO, IDUSUARIO, IDPEDIDOASIGNADO)\n" +
                        ");");
                db.execSQL("create table PRODUCTO\n" +
                        "(\n" +
                        "   IDPRODUCTO           int not null,\n" +
                        "   NOMBREPRODUCTO       varchar(50) not null,\n" +
                        "   PRECIOUNITARIO       real not null,\n" +
                        "   DESCPRODUCTO         varchar(50),\n" +
                        "   primary key (IDPRODUCTO)\n" +
                        ");");
                db.execSQL("create table TIPOUSUARIO\n" +
                        "(\n" +
                        "   IDTIPOUSUARIO        int not null,\n" +
                        "   NOMTIPOUSUARIO       char(10) not null,\n" +
                        "   primary key (IDTIPOUSUARIO)\n" +
                        ");");
                db.execSQL("create table UBICACION\n" +
                        "(\n" +
                        "   IDUBICACION          int not null,\n" +
                        "   IDFACULTAD           char(2),\n" +
                        "   IDPEDIDO             int not null,\n" +
                        "   DIRECUBICACION       varchar(100) not null,\n" +
                        "   NOMUBICACION         varchar(30) not null,\n" +
                        "   PUNTOREFUBICACION    varchar(50) not null,\n" +
                        "   primary key (IDUBICACION)\n" +
                        ");");
                db.execSQL("create table USUARIO\n" +
                        "(\n" +
                        "   IDUSUARIO            int not null,\n" +
                        "   IDTIPOUSUARIO        int,\n" +
                        "   CONTRASENA           varchar(10) not null,\n" +
                        "   NOMBREUSUARIO        varchar(30) not null,\n" +
                        "   TELEUSUARIO          varchar(9),\n" +
                        "   APELLIDOUSUARIO      varchar(30) not null,\n" +
                        "   primary key (IDUSUARIO)\n" +
                        ");");


            }catch(SQLException e)

            {
                e.printStackTrace();
            }
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }



    }
    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        DBHelper.close();
    }





    //Preguntar a Spiderman si esta parte del código falla o hay dudas

    //CRUD usuario
    public String insertar(Usuario usuario) {

        String resultado = "Usuario creado, bienvenido " + usuario;
        long comprobador = 0;
        ContentValues usu = new ContentValues();
        usu.put("IDUSUARIO", usuario.getIdUsuario());
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

    public String CrearLocal(Local local) {
        String resultado = "Local creado ";
        ContentValues lokxti = new ContentValues();
        lokxti.put("idLocal", local.getIdLocal());
        lokxti.put("idUsuario", local.getIdUsuario());
        lokxti.put("nombreLocal", local.getNombreLocal());


        long comprobador = 0;
        comprobador = db.insert("Local", null, lokxti);
        if (comprobador == -1 || comprobador == 0) {
            resultado = "oh, oh ya existe un local con ese codigo ): ";
        }
        return resultado;
    }

    public Local ConsultaLocal(String idLocal) {
        String[] id = {idLocal};
        Cursor cur = db.rawQuery("select * from Local where idLocal =" + idLocal, null);
        if (cur.moveToFirst()) {
            Local lokxti = new Local();
            lokxti.setIdLocal((cur.getInt(0)));
            lokxti.setIdUsuario((cur.getInt(1)));
            lokxti.setNombreLocal((cur.getString(2)));


            return lokxti;

        } else {
            return null;
        }


    }

    public List<Local> ConsultaLocales() {
        Cursor cur = db.rawQuery("SELECT * FROM Local", null);
        List<Local> lokxti = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                lokxti.add(new Local(cur.getInt(0),cur.getInt(1), cur.getString(2)));
            } while (cur.moveToNext());


        }
        return lokxti;
    }

    public String ActualizarLocal(Local local) {
        String resultado = "local actualizado";
        String[] id = {String.valueOf(local.getIdLocal())};
        Cursor cur = db.query("Local", null, "idLocal = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            ContentValues lokxti = new ContentValues();
            lokxti.put("idLocal", local.getIdLocal());
            lokxti.put("idTipoUsuario", local.getIdUsuario());
            lokxti.put("nombreLocal", local.getNombreLocal());


            db.update("Local", lokxti, "idLocal=?", id);

        } else {
            resultado = "Local no existente ";
        }

        return resultado;
    }


    public String eliminarLocal(Local local) {
        int comprobador = 0;
        int cont = 0;
        int cont1 = 0;
        String resultado = comprobador + " locales eliminados ";
        String[] id = {String.valueOf(local.getIdLocal())};
        Cursor cur = db.query("Local", null, "idLocal = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            Cursor k = db.query("Menu", null, "idLocal", id, null, null, null);
            Cursor p = db.query("Pedido", null, "idLocal", id, null, null, null);
            if (k.moveToFirst()) {
                cont = db.delete("Menu", "idLocal =" + local.getIdLocal(), null);
                resultado = resultado + ", " + cont + " Menus eliminados de el local " + local.getNombreLocal();
            }
            if (p.moveToFirst())
            {
                cont1 = db.delete("Pedido", "idLocal =" + local.getIdLocal(), null);
                resultado = resultado + ", " + cont1 + " pedidos eliminados de este local ";
            }

            comprobador = db.delete("Local", "idLocal =" + local.getIdLocal(), null);
        } else {
            resultado = "Ese local no existe";
        }
        return resultado;
    }


        //Aquí termina la ayuda de tu vecino el hombre araña

//Preguntar a Vane si esta parte del código falla o hay dudas

    //CRUD Ubicacion
    public String insertar(Ubicacion ubicacion){
        String resultado="Se guardó correctamente su ubicación" + ubicacion;
        long contador=0;
        //verificando Integridad
        // verificar que existe ubicacion
        if (verificarIntegridad(ubicacion, 1)) {
            //se encontro ubicacion
            resultado="Esta ubicacion ya existe. Registro Duplicado, ERROR";
        } else if(verificarIntegridad(ubicacion,2) && verificarIntegridad(ubicacion,3)){ //verificando que exista idPedido en pedido e idFacultad en facultad para insertar en ubicacion
            ContentValues ubic = new ContentValues();
            ubic.put("idUbicacion", ubicacion.getIdUbicacion());
            ubic.put("idFacultad", ubicacion.getIdFacultad());
            ubic.put("idPedido", ubicacion.getIdPedido());
            ubic.put("direcUbicacion", ubicacion.getDirecUbicacion());
            ubic.put("nomUbicacion", ubicacion.getNomUbicacion());
            ubic.put("puntoRefUbicacion", ubicacion.getPuntoRefUbicacion());
            contador=db.insert("Ubicacion", null, ubic);
        }
        else {
            resultado="Facultad y Pedido no existen";
        }
        return resultado;
    }

    public Ubicacion consultarUbicacion(String idubicacion){
        Cursor cursor = db.rawQuery("select * from Ubicacion where idUbicacion =" + idubicacion, null);
        //si existe ubicacion
        if(cursor.moveToFirst())
        {
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setIdUbicacion(cursor.getInt(0));
            ubicacion.setIdFacultad(cursor.getString(1));
            ubicacion.setIdPedido(cursor.getInt(2));
            ubicacion.setDirecUbicacion(cursor.getString(3));
            ubicacion.setNomUbicacion(cursor.getString(4));
            ubicacion.setPuntoRefUbicacion(cursor.getString(5));
            return ubicacion;
        }
        else {
            return null;
        }
    }

    public List<Ubicacion> ConsultaUbicaciones() {
        Cursor cur = db.rawQuery("SELECT * FROM Ubicacion", null);
        List<Ubicacion> ubic = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                ubic.add(new Ubicacion(cur.getInt(0),
                        cur.getString(1),
                        cur.getInt(2),
                        cur.getString(3),
                        cur.getString(4),
                        cur.getString(5)));
            } while (cur.moveToNext());
        }
        return ubic;
    }

    public String actualizar(Ubicacion ubicacion){
        //verificando que exista ubicacion
        if(verificarIntegridad(ubicacion, 1)){
            if(verificarIntegridad(ubicacion,2) || verificarIntegridad(ubicacion,3)){
                String[] idU = {String.valueOf(ubicacion.getIdUbicacion())};
                ContentValues cv = new ContentValues();
                cv.put("idUbicacion",ubicacion.getIdUbicacion());
                cv.put("idPedido",ubicacion.getIdPedido());
                cv.put("idFacultad",ubicacion.getIdFacultad());
                cv.put("nomUbicacion",ubicacion.getNomUbicacion());
                cv.put("direcUbicacion",ubicacion.getDirecUbicacion());
                cv.put("puntoRefUbicacion",ubicacion.getPuntoRefUbicacion());
                db.update("Ubicacion", cv, "idUbicacion = ?", idU);
                return "Registro de Ubicacion Actualizado Correctamente";
            }
            else{
                return "El codigo de facultad o pedido no existe";
            }
        }else{
            return "Registro con codigo " + ubicacion.getIdUbicacion() + " no existe";
        }
    }
    public String eliminar(Ubicacion ubicacion){
        String resultado = "Se elimino la ubicacion: " + ubicacion.getIdUbicacion();
        int contadorF = 0;
        int contadorP=0;
        int contadorU=0;
        //verificar que exista ubicacion
        if(verificarIntegridad(ubicacion,1)){
            //verificar que exista pedido para eliminar en cascada
            if(verificarIntegridad(ubicacion,3)){
                contadorP+=db.delete("Pedido", "idPedido= '"+ubicacion.getIdPedido()+"'",null);
                resultado+= resultado + " Se elimino el/los "+ contadorP+" registros de Pedido";
            }
            contadorU+=db.delete("Ubicacion","idUbicacion= '"+ubicacion.getIdUbicacion()+"'",null);
        }else {
            resultado= "La ubicacion no existe";
        }
        return resultado;
    }

    //CRUD Pedido
    public String insertar(Pedido pedido){
        String resultado="Se guardó correctamente nuevo pedido " + pedido;
        long contador=0;
        //verificando Integridad
        // verificar que existe Pedido
        if (verificarIntegridad(pedido, 4)) {
            //se encontro pedido
            resultado="Este pedido ya existe. Registro Duplicado, ERROR";
        } else if(verificarIntegridad(pedido,5) && verificarIntegridad(pedido,6) && verificarIntegridad(pedido,7) && verificarIntegridad(pedido,8)){ //verificando que exista idDetallePedido en DetallePedido e idEstadoPedido en EstadPedido e idLocal en Local e idUbicacion en Ubicacion para insertar en pedido
            ContentValues pedi = new ContentValues();
            pedi.put("idPedido", pedido.getIdPedido());
            pedi.put("idDetallePedido",pedido.getIdDetallePedido());
            pedi.put("idEstadoPedido",pedido.getIdEstadoPedido());
            pedi.put("idLocal",pedido.getIdLocal());
            pedi.put("idUbicacion",pedido.getIdUbicacion());
            pedi.put("fechaPedido",pedido.getFechaPedido());
            pedi.put("totalPedido",pedido.getTotalPedido());
            contador=db.insert("Pedido", null, pedi);
        }
        else {
            resultado="DetallePedido,EstadoPedido,Local y Ubicacion no existen";
        }
        return resultado;
    }

    public Pedido consultarPedido(String idPedido){
        Cursor cursor = db.rawQuery("select * from Pedido where idPedido =" + idPedido, null);
        //si existe pedido
        if(cursor.moveToFirst())
        {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(cursor.getInt(0));
            pedido.setIdDetallePedido(cursor.getInt(1));
            pedido.setIdEstadoPedido(cursor.getString(2));
            pedido.setIdLocal(cursor.getInt(3));
            pedido.setIdUbicacion(cursor.getInt(4));
            pedido.setFechaPedido(cursor.getString(5));
            pedido.setTotalPedido(cursor.getFloat(6));
            return pedido;
        }
        else {
            return null;
        }
    }

    public List<Pedido> ConsultaPedidos() {
        Cursor cur = db.rawQuery("SELECT * FROM Pedido", null);
        List<Pedido> pedido = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                pedido.add(new Pedido(cur.getInt(0),
                        cur.getInt(1),
                        cur.getString(2),
                        cur.getInt(3),
                        cur.getInt(4),
                        cur.getString(5),
                        cur.getFloat(6)));
            } while (cur.moveToNext());
        }
        return pedido;
    }

    public String actualizar(Pedido pedido){
        //verificando que exista pedido
        if(verificarIntegridad(pedido, 4)){
            if(verificarIntegridad(pedido,5) && verificarIntegridad(pedido,6) && verificarIntegridad(pedido,7)&& verificarIntegridad(pedido,8)){
                String[] idP = {String.valueOf(pedido.getIdPedido())};
                ContentValues cv = new ContentValues();
                cv.put("idPedido",pedido.getIdPedido());
                cv.put("idDetallePedido",pedido.getIdDetallePedido());
                cv.put("idEstadoPedido",pedido.getIdEstadoPedido());
                cv.put("idLocal",pedido.getIdLocal());
                cv.put("idUbicacion",pedido.getIdUbicacion());
                cv.put("fechaPedido",pedido.getFechaPedido());
                cv.put("totalPedido",pedido.getTotalPedido());
                db.update("Pedido", cv, "idPedido = ?", idP);
                return "Registro de Pedido Actualizado Correctamente";
            }
            else{
                return "El codigo de DetallePedido o EstadPedido o Local o Ubicacion no existe";
            }
        }else{
            return "Registro con codigo " + pedido.getIdPedido() + " no existe";
        }
    }
    public String eliminar(Pedido pedido){
        String resultado = "Se elimino el pedido: " + pedido.getIdPedido();
        int contadorPR = 0;
        int contadorPA=0;
        int contadorU=0;
        //verificar que exista Pedido
        if(verificarIntegridad(pedido,4)){
            //verificar que exista PedidoAsignado y PedidoRealizado y Ubicacion para eliminar en cascada
            if(verificarIntegridad(pedido,10)){
                contadorPR+=db.delete("PedidoRealizado","idPedido= '"+pedido.getIdPedido()+"'",null);
                resultado+= resultado + " Se elimino el/los " + contadorPR + " registros de DetallePedido";
            }
            if(verificarIntegridad(pedido,11)){
                contadorPA+=db.delete("PedidoAsignado", "idPedido= '"+pedido.getIdPedido()+"'",null);
                resultado+= resultado + " Se elimino el/los "+ contadorPA+" registros de EstadoPedido";
            }
            if(verificarIntegridad(pedido,8)){
                contadorU+=db.delete("Ubicacion", "idUbicacion= '"+pedido.getIdUbicacion()+"'",null);
                resultado+= resultado + " Se elimino el/los "+ contadorU+" registros de Ubicacion";
            }
            contadorU+=db.delete("Pedido","idPedido= '"+pedido.getIdPedido()+"'",null);
        }else {
            resultado= "El pedido no existe";
        }
        return resultado;
    }

    //CRUD Facultad
    public String insertar(Facultad facultad){
        String resultado="Se guardó correctamente la nueva facultad " + facultad;
        long contador=0;
        //verificando Integridad
        // verificar que existe Facultad
        if (verificarIntegridad(facultad, 9)) {
            //se encontro facultad
            resultado="Esta facultad ya existe. Registro Duplicado, ERROR";
        } else {
            ContentValues facu = new ContentValues();
            facu.put("idFacultad", facultad.getIdFacultad());
            facu.put("nomFacultad",facultad.getNomFacultad());
            contador=db.insert("Facultad", null, facu);
        }
        return resultado;
    }

    public Facultad consultarFacultad(String idFacultad){
        Cursor cursor = db.rawQuery("select * from Facultad where idFacultad =" + idFacultad, null);
        //si existe facultad
        if(cursor.moveToFirst())
        {
            Facultad facultad = new Facultad();
            facultad.setIdFacultad(cursor.getString(0));
            facultad.setNomFacultad(cursor.getString(1));
            return facultad;
        }
        else {
            return null;
        }
    }

    public List<Facultad> ConsultaFacultades() {
        Cursor cur = db.rawQuery("SELECT * FROM Facultad", null);
        List<Facultad> facu = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                facu.add(new Facultad(cur.getString(0),
                        cur.getString(1)));
            } while (cur.moveToNext());

        }
        return facu;
    }

    public String actualizar(Facultad facultad){
        //verificando que exista facultad
        if(verificarIntegridad(facultad, 9)){
            String[] idF = {facultad.getIdFacultad()};
            ContentValues cv = new ContentValues();
            cv.put("idFacultad",facultad.getIdFacultad());
            cv.put("nomFacultad",facultad.getNomFacultad());
            db.update("Facultad", cv, "idFacultad = ?", idF);
            return "Registro de Facultad Actualizado Correctamente";
        }else{
            return "Registro con codigo " + facultad.getIdFacultad()+ " no existe";
        }
    }
    public String eliminar(Facultad facultad){
        String resultado = "Se elimino facultad: " + facultad.getIdFacultad();
        int contadorU=0;
        //verificar que exista Facultad
        if(verificarIntegridad(facultad,9)){
            //verificar que exista en Ubicacion para eliminar en cascada
            if(verificarIntegridad(facultad,12)){
                contadorU+=db.delete("Ubicacion", "idFacultad= '"+facultad.getIdFacultad()+"'",null);
                resultado+= resultado + " Se elimino el/los "+ contadorU+" registros de Ubicacion";
            }
            contadorU+=db.delete("Facultad","idFacultad= '"+facultad.getIdFacultad()+"'",null);
        }else {
            resultado= "Facultad no existe";
        }
        return resultado;
    }
    //CRUD PedidoAsignado
    public String insertar(PedidoAsignado pedidoAsignado){
        String resultado="Se guardó correctamente pedidoAsignado N°: ";
        long contador=0;
        //verificando Integridad
        if (verificarIntegridad(pedidoAsignado, 13)) {
            ContentValues pasig = new ContentValues();
            pasig.put("idPedidoAsignado", pedidoAsignado.getIdPedidoAsignado());
            pasig.put("idPedido", pedidoAsignado.getIdPedido());
            pasig.put("idUsuario", pedidoAsignado.getIdUsuario());
            contador=db.insert("PedidoAsignado", null, pasig);
        } if(contador==-1 || contador==0)
        {
            resultado= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            resultado+=resultado+contador;
        }
        return resultado;
    }

    public PedidoAsignado consultarPedAsig(String idPedidoAsignado, String idPedido, String idUsuario){
        String[] id = {idPedidoAsignado,idPedido,idUsuario};
        Cursor cursor = db.rawQuery("select * from PedidoAsignado where idPedidoAsignado = ? and idPedido = ? and idUsuario = ?" + id, null);
        //si existe pedidoAsignado
        if(cursor.moveToFirst())
        {
            PedidoAsignado pedidoAsignado = new PedidoAsignado();
            pedidoAsignado.setIdPedidoAsignado(cursor.getInt(0));
            pedidoAsignado.setIdPedido(cursor.getInt(1));
            pedidoAsignado.setIdUsuario(cursor.getInt(2));
            return pedidoAsignado;
        }
        else {
            return null;
        }
    }

    public List<PedidoAsignado> ConsultaPedidosAsignados() {
        Cursor cur = db.rawQuery("SELECT * FROM PedidoAsignado", null);
        List<PedidoAsignado> peasig = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                peasig.add(new PedidoAsignado(cur.getInt(0),
                        cur.getInt(1),
                        cur.getInt(2)));
            } while (cur.moveToNext());

        }
        return peasig;
    }

    public String actualizar(PedidoAsignado pedidoAsignado){
        //verificando integridad
        if(verificarIntegridad(pedidoAsignado, 14)){
            String[] idPA = {String.valueOf(pedidoAsignado.getIdPedidoAsignado()),String.valueOf(pedidoAsignado.getIdPedido()),String.valueOf(pedidoAsignado.getIdUsuario())};
            ContentValues cv = new ContentValues();
            cv.put("idPedidoAsignado",pedidoAsignado.getIdPedidoAsignado());
            cv.put("idPedido",pedidoAsignado.getIdPedido());
            cv.put("idUsuario",pedidoAsignado.getIdUsuario());
            db.update("PedidoAsignado", cv, "idPedidoAsignado = ? and idPedido= ? and idUsuario= ?", idPA);
            return "Registro de PedidoAsignado Actualizado Correctamente";
        }else{
            return "Registro con codigo no existe";
        }
    }
    public String eliminar(PedidoAsignado pedidoAsignado){
        String resultado = "Se elimino pedidoAsignado: " + pedidoAsignado.getIdPedidoAsignado();
        String[] idPA = {String.valueOf(pedidoAsignado.getIdPedidoAsignado()),String.valueOf(pedidoAsignado.getIdPedido()),String.valueOf(pedidoAsignado.getIdUsuario())};
        int contadorPA=0;
        //verificar que exista pedidoAsignado
        if(verificarIntegridad(pedidoAsignado,14)){
            contadorPA+=db.delete("PedidoAsignado","idPedidoAsignado= ? and idPedido = ? and idUsuario = ? '"+ idPA +"'",null);
        }else {
            resultado= "El pedido Asignado no existe";
        }
        return resultado;
    }

    //CRUD PedidoRealizado
    public String insertar(PedidoRealizado pedidoRealizado){
        String resultado="Se guardó correctamente pedidoRealizado N°: ";
        long contador=0;
        //verificando Integridad
        if (verificarIntegridad(pedidoRealizado, 15)) {
            ContentValues preal = new ContentValues();
            preal.put("idPedidoRealizado", pedidoRealizado.getIdPedidoRealizado());
            preal.put("idPedido", pedidoRealizado.getIdPedido());
            preal.put("idUsuario", pedidoRealizado.getIdUsuario());
            contador=db.insert("PedidoRealizado", null, preal);
        } if(contador==-1 || contador==0)
        {
            resultado= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            resultado+=resultado+contador;
        }
        return resultado;
    }

    public PedidoRealizado consultarPedReal(String idPedidoRealizado, String idPedido, String idUsuario){
        String[] id = {idPedidoRealizado,idPedido,idUsuario};
        Cursor cursor = db.rawQuery("select * from PedidoRealizado where idPedidoRealizado = ? and idPedido = ? and idUsuario = ?" + id, null);
        //si existe pedidoRealizado
        if(cursor.moveToFirst())
        {
            PedidoRealizado pedidoRealizado = new PedidoRealizado();
            pedidoRealizado.setIdPedidoRealizado(cursor.getInt(0));
            pedidoRealizado.setIdPedido(cursor.getInt(1));
            pedidoRealizado.setIdUsuario(cursor.getInt(2));
            return pedidoRealizado;
        }
        else {
            return null;
        }
    }

    public List<PedidoRealizado> ConsultaPedidosRealizados() {
        Cursor cur = db.rawQuery("SELECT * FROM PedidoRealizado", null);
        List<PedidoRealizado> pedreal = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                pedreal.add(new PedidoRealizado(cur.getInt(0),
                        cur.getInt(1),
                        cur.getInt(2)));
            } while (cur.moveToNext());

        }
        return pedreal;
    }

    public String actualizar(PedidoRealizado pedidoRealizado){
        //verificando integridad
        if(verificarIntegridad(pedidoRealizado, 16)){
            String[] idPR = {String.valueOf(pedidoRealizado.getIdPedidoRealizado()),String.valueOf(pedidoRealizado.getIdPedido()),String.valueOf(pedidoRealizado.getIdUsuario())};
            ContentValues cv = new ContentValues();
            cv.put("idPedidoRealizado",pedidoRealizado.getIdPedidoRealizado());
            cv.put("idPedido",pedidoRealizado.getIdPedido());
            cv.put("idUsuario",pedidoRealizado.getIdUsuario());
            db.update("PedidoRealizado", cv, "idPedidoRealizado = ? and idPedido= ? and idUsuario= ?", idPR);
            return "Registro de PedidoRealizado Actualizado Correctamente";
        }else{
            return "Registro con codigo no existe";
        }
    }
    public String eliminar(PedidoRealizado pedidoRealizado){
        String resultado = "Se elimino pedidoRealizado: " + pedidoRealizado.getIdPedidoRealizado();
        String[] idPR = {String.valueOf(pedidoRealizado.getIdPedidoRealizado()),String.valueOf(pedidoRealizado.getIdPedido()),String.valueOf(pedidoRealizado.getIdUsuario())};
        int contadorPR=0;
        //verificar que exista pedidoRealizado
        if(verificarIntegridad(pedidoRealizado,16)){
            contadorPR+=db.delete("PedidoRealizado","idPedidoRealizado= ? and idPedido = ? and idUsuario = ? '"+ idPR +"'",null);
        }else {
            resultado= "El pedido Realizado no existe";
        }
        return resultado;
    }

    //CRUD AccesoUsuario
    public String insertar(AccesoUsuario accesoUsuario){
        String resultado="Se guardó correctamente AccesoUsuario N°: ";
        long contador=0;
        //verificando Integridad
        if (verificarIntegridad(accesoUsuario, 17)) {
            ContentValues accus = new ContentValues();
            accus.put("idAccesoUsuario", accesoUsuario.getIdAccesoUsuario());
            accus.put("id_Opcion", accesoUsuario.getIdOpcion());
            accus.put("idUsuario", accesoUsuario.getIdUsuario());
            contador=db.insert("AccesoUsuario", null, accus);
        } if(contador==-1 || contador==0)
        {
            resultado= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            resultado+=resultado+contador;
        }
        return resultado;
    }

    public AccesoUsuario consultarAccesoUsuario(String idAccesoUsuario, String idOpcion, String idUsuario){
        String[] id = {idAccesoUsuario,idOpcion,idUsuario};
        Cursor cursor = db.rawQuery("select * from AccesoUsuario where idAccesoUsuario = ? and id_Opcion = ? and idUsuario = ?" + id, null);
        //si existe AccesoUsuario
        if(cursor.moveToFirst())
        {
            AccesoUsuario accesoUsuario = new AccesoUsuario();
            accesoUsuario.setIdAccesoUsuario(cursor.getInt(0));
            accesoUsuario.setIdOpcion(cursor.getString(1));
            accesoUsuario.setIdUsuario(cursor.getInt(2));
            return accesoUsuario;
        }
        else {
            return null;
        }
    }

    public List<AccesoUsuario> ConsultaAccesoUsuarios() {
        Cursor cur = db.rawQuery("SELECT * FROM AccesoUsuario", null);
        List<AccesoUsuario> accusu = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                accusu.add(new AccesoUsuario(cur.getInt(0),
                        cur.getString(1),
                        cur.getInt(2)));
            } while (cur.moveToNext());

        }
        return accusu;
    }

    public String actualizar(AccesoUsuario accesoUsuario){
        //verificando integridad
        if(verificarIntegridad(accesoUsuario, 18)){
            String[] idAU = {String.valueOf(accesoUsuario.getIdAccesoUsuario()),accesoUsuario.getIdOpcion(),String.valueOf(accesoUsuario.getIdUsuario())};
            ContentValues cv = new ContentValues();
            cv.put("idAccesoUsuario",accesoUsuario.getIdAccesoUsuario());
            cv.put("id_Opcion",accesoUsuario.getIdOpcion());
            cv.put("idUsuario",accesoUsuario.getIdUsuario());
            db.update("AccesoUsuario", cv, "idAccesoUsuario= ? and id_Opcion= ? and idUsuario= ?", idAU);
            return "Registro de AccesoUsuario Actualizado Correctamente";
        }else{
            return "Registro no existe";
        }
    }
    public String eliminar(AccesoUsuario accesoUsuario){
        String resultado = "Se elimino accesoUsuario: " + accesoUsuario.getIdAccesoUsuario();
        String[] idAU = {String.valueOf(accesoUsuario.getIdAccesoUsuario()),accesoUsuario.getIdOpcion(),String.valueOf(accesoUsuario.getIdUsuario())};
        int contadorAU=0;
        //verificar que exista accesoUsuario
        if(verificarIntegridad(accesoUsuario,18)){
            contadorAU+=db.delete("AccesoUsuario","idAccesoUsuario= ? and id_Opcion = ? and idUsuario = ? '"+ idAU +"'",null);
        }else {
            resultado= "El AccesoUsuario no existe";
        }
        return resultado;
    }

    //CRUD OpcionCrud
    public String insertar(OpcionCrud opcionCrud){
        String resultado="Se guardó correctamente la opcionCrud " + opcionCrud;
        long contador=0;
        //verificando Integridad
        // verificar que existe opcionCrud
        if (verificarIntegridad(opcionCrud, 19)) {
            //se encontro opcionCrud
            resultado="Esta opcionCrud ya existe. Registro Duplicado, ERROR";
        } else {
            ContentValues opCrud = new ContentValues();
            opCrud.put("id_Opcion", opcionCrud.getIdOpcion());
            opCrud.put("desOpcion",opcionCrud.getDescOpcion());
            opCrud.put("numCrud", opcionCrud.getNumCrud());
            contador=db.insert("OpcionCrud", null, opCrud);
        }
        return resultado;
    }

    public OpcionCrud consultarOpcionCrud(String idOpcionCrud){
        Cursor cursor = db.rawQuery("select * from OpcionCrud where id_Opcion =" + idOpcionCrud, null);
        //si existe opcionCrud
        if(cursor.moveToFirst())
        {
            OpcionCrud opcionCrud = new OpcionCrud();
            opcionCrud.setIdOpcion(cursor.getString(0));
            opcionCrud.setDescOpcion(cursor.getString(1));
            opcionCrud.setNumCrud(cursor.getInt(2));
            return opcionCrud;
        }
        else {
            return null;
        }
    }

    public List<OpcionCrud> ConsultaOpcionesCrud() {
        Cursor cur = db.rawQuery("SELECT * FROM OpcionCrud", null);
        List<OpcionCrud> opcrud = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                opcrud.add(new OpcionCrud(cur.getString(0),
                        cur.getString(1),
                        cur.getInt(2)));
            } while (cur.moveToNext());

        }
        return opcrud;
    }

    public String actualizar(OpcionCrud opcionCrud){
        //verificando que exista opcionCrud
        if(verificarIntegridad(opcionCrud, 19)){
            String[] idOC = {opcionCrud.getIdOpcion()};
            ContentValues cv = new ContentValues();
            cv.put("id_Opcion",opcionCrud.getIdOpcion());
            cv.put("desOpcion",opcionCrud.getDescOpcion());
            cv.put("numCrud", opcionCrud.getNumCrud());
            db.update("OpcionCrud", cv, "id_Opcion = ?", idOC);
            return "Registro de OpcionCrud Actualizado Correctamente";
        }else{
            return "Registro con codigo " + opcionCrud.getIdOpcion() + " no existe";
        }
    }
    public String eliminar(OpcionCrud opcionCrud){
        String resultado = "Se elimino opcionCrud: " + opcionCrud.getIdOpcion();
        int contadorOC=0;
        //verificar que exista OpcionCrud
        if(verificarIntegridad(opcionCrud,19)){
            //verificar que exista en AccesoUsuario para eliminar en cascada
            if(verificarIntegridad(opcionCrud,20)){
                contadorOC+=db.delete("AccesoUsuario", "id_Opcion= '"+opcionCrud.getIdOpcion()+"'",null);
                resultado+= resultado + " Se elimino el/los "+ contadorOC+" registros de AccesoUsuario";
            }
            contadorOC+=db.delete("OpcionCrud","id_Opcion= '" +opcionCrud.getIdOpcion()+"'",null);
        }else {
            resultado= "OpcionCrud no existe";
        }
        return resultado;
    }

    //Aquí termina la parte de Vane

    //Integridad
    //fin integridad

    //Integridad
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //verificar que exista ubicacion
                Ubicacion ubicacion = (Ubicacion) dato;
                String[] ub = {String.valueOf(ubicacion.getIdUbicacion())};
                Cursor c1 = db.query("Ubicacion", null, "idUbicacion = ?", ub, null, null, null);
                if (c1.moveToFirst()) {
                    //Se encontro ubicacion
                    return true;
                }
                return false;
            }
            case 2: {
                //verificar que en ubicacion exista facultad
                Ubicacion ubicacion1 = (Ubicacion) dato;
                Cursor c1 = db.query(true, "Facultad", new String[]{"idFacultad"}, "idFacultad= '" + ubicacion1.getIdFacultad() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 3: {
                //verificar que en ubicacion exista pedido
                Ubicacion ubicacion2 = (Ubicacion) dato;
                Cursor c1 = db.query(true, "Pedido", new String[]{"idPedido"}, "idPedido= '" + ubicacion2.getIdPedido() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;

            }
            case 4: {
                //verificar que exista Pedido
                Pedido pedido = (Pedido) dato;
                String[] ped = {String.valueOf(pedido.getIdPedido())};
                Cursor c1 = db.query("Pedido", null, "idPedido = ?", ped, null, null, null);
                if (c1.moveToFirst()) {
                    //Se encontro pedido
                    return true;
                }
                return false;
            }
            case 5: {
                //verificar que en pedido exista DetallePedido
                Pedido pedido2 = (Pedido) dato;
                Cursor c1 = db.query(true, "DetallePedido", new String[]{"idDetallePedido"}, "idDetallePedido= '" + pedido2.getIdDetallePedido() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 6: {
                //verificar que en pedido exista idEstadoPedido
                Pedido pedido3 = (Pedido) dato;
                Cursor c1 = db.query(true, "EstadPedido", new String[]{"idEstadoPedido"}, "idEstadoPedido= '" + pedido3.getIdEstadoPedido() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 7: {
                //verificar que en pedido exista idLocal
                Pedido pedido4 = (Pedido) dato;
                Cursor c1 = db.query(true, "Local", new String[]{"idLocal"}, "idLocal= '" + pedido4.getIdLocal() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 8: {
                //verificar que en pedido exista idUbicacion
                Pedido pedido5 = (Pedido) dato;
                Cursor c1 = db.query(true, "Ubicacion", new String[]{"idUbicacion"}, "idUbicacion= '" + pedido5.getIdUbicacion() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 9: {
                //verificar que exista Facultad
                Facultad facultad = (Facultad) dato;
                String[] fac = {facultad.getIdFacultad()};
                Cursor c1 = db.query("Facultad", null, "idFacultad = ?", fac, null, null, null);
                if (c1.moveToFirst()) {
                    //Se encontro facultad
                    return true;
                }
                return false;
            }
            case 10: {
                //verificar que idPedido este en PedidosRealizados
                Pedido pedido6 = (Pedido) dato;
                Cursor c1 = db.query(true, "PedidoRealizado", new String[]{"idPedido"}, "idPedido= '" + pedido6.getIdPedido() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 11: {
                //verificar que idPedido este en PedidosAsignados
                Pedido pedido7 = (Pedido) dato;
                Cursor c1 = db.query(true, "PedidoAsignado", new String[]{"idPedido"}, "idPedido= '" + pedido7.getIdPedido() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;

            }
            case 12: {
                //verificar que idFacultad este en Ubicacion
                Facultad facultad2 = (Facultad) dato;
                Cursor c1 = db.query(true, "Ubicacion", new String[]{"idFacultad"}, "idFacultad= '" + facultad2.getIdFacultad() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 13: {
                //verificar que al insertar pedidoAsignado exista idPedido e idUsuario
                PedidoAsignado pedidoAsignado = (PedidoAsignado) dato;
                String[] id1 = {String.valueOf(pedidoAsignado.getIdPedido())};
                String[] id2 = {String.valueOf(pedidoAsignado.getIdUsuario())};
                Cursor cursor1 = db.query("Pedido", null, "idPedido = ?", id1, null,
                        null, null);
                Cursor cursor2 = db.query("Usuario", null, "idUsuario = ?", id2,
                        null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;

            }
            case 14:{
//verificar que al modificar o eliminar pedidoAsignado exista idPedido del Pedido, idUsuario y el idPedidoAsignado
                PedidoAsignado pedidoAsignado1=(PedidoAsignado) dato;
                String[] ids = {String.valueOf(pedidoAsignado1.getIdPedidoAsignado()), String.valueOf(pedidoAsignado1.getIdPedido()),
                            String.valueOf(pedidoAsignado1.getIdUsuario())};
                Cursor c = db.query("PEDIDOASIGNADO", null, "IDPEDIDOASIGNADO= ? AND IDPEDIDO = ? AND IDUSUARIO= ?", ids, null, null, null);
                    if(c.moveToFirst()){
//Se encontraron datos
                        return true;
                    }
                    return false;
                }
            case 15: {
                //verificar que al insertar pedidoRealizado exista idPedido e idUsuario
                PedidoRealizado pedidoRealizado = (PedidoRealizado) dato;
                String[] id1 = {String.valueOf(pedidoRealizado.getIdPedido())};
                String[] id2 = {String.valueOf(pedidoRealizado.getIdUsuario())};
                Cursor cursor1 = db.query("Pedido", null, "idPedido = ?", id1, null,
                        null, null);
                Cursor cursor2 = db.query("Usuario", null, "idUsuario = ?", id2,
                        null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;

            }
            case 16:{
//verificar que al modificar o eliminar pedidoRealizado exista idPedido del Pedido, idUsuario y el idPedidoAsignado
                PedidoRealizado pedidoRealizado1 = (PedidoRealizado) dato;
                String[] ids = {String.valueOf(pedidoRealizado1.getIdPedidoRealizado()), String.valueOf(pedidoRealizado1.getIdPedido()),
                        String.valueOf(pedidoRealizado1.getIdUsuario())};
                Cursor c = db.query("PEDIDOREALIZADO", null, "IDPEDIDOREALIZADO= ? AND IDPEDIDO = ? AND IDUSUARIO= ?", ids, null, null, null);
                if(c.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;
            }
            case 17: {
                //verificar que al insertar AccesoUsuario exista idUsuario e idOpcion
                AccesoUsuario accesoUsuario = (AccesoUsuario) dato;
                String[] id1 = {String.valueOf(accesoUsuario.getIdUsuario())};
                String[] id2 = {accesoUsuario.getIdOpcion()};
                Cursor cursor1 = db.query("Usuario", null, "idUsuario = ?", id1, null,
                        null, null);
                Cursor cursor2 = db.query("OpcionCrud", null, "id_opcion = ?", id2,
                        null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;

            }
            case 18:{
//verificar que al modificar o eliminar AccesoUsuario exista idOpcion de OpcionCrud, idUsuario y el idAccesoUsuario
                AccesoUsuario accesoUsuario1 = (AccesoUsuario) dato;
                String[] ids = {String.valueOf(accesoUsuario1.getIdAccesoUsuario()), accesoUsuario1.getIdOpcion(),
                        String.valueOf(accesoUsuario1.getIdUsuario())};
                Cursor c = db.query("ACCESOUSUARIO", null, "IDACCESOUSUARIO= ? AND ID_OPCION = ? AND IDUSUARIO= ?", ids, null, null, null);
                if(c.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;
            }
            case 19: {
                //verificar que exista opcionCrud
                OpcionCrud opcionCrud = (OpcionCrud) dato;
                String[] opCrud = {opcionCrud.getIdOpcion()};
                Cursor c1 = db.query("OpcionCrud", null, "id_Opcion = ?", opCrud, null, null, null);
                if (c1.moveToFirst()) {
                    //Se encontro opcionCrud
                    return true;
                }
                return false;
            }
            case 20: {
                //verificar que idOpcion este en AccesoUsuario
                OpcionCrud opcionCrud1 = (OpcionCrud) dato;
                Cursor c1 = db.query(true, "AccesoUsuario", new String[]{"id_Opcion"}, "id_Opcion= '" + opcionCrud1.getIdOpcion() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;
            }
            default:
                return false;
        }
        //fin integridad

        //Aquí termina la parte de Vane

        //Aquí termina la parte de Vane


        //Integridad
        //fin integridad
    }
}
