package sv.edu.ues.fia.eisi.pedidoscafeteria;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.analytics.ecommerce.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.AsignarProducto;

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
                db.execSQL("create table ACCESOUSUARIO\n" +
                        "(\n" +
                        "   IDUSUARIO            varchar(30) not null,\n" +
                        "   ID_OPCION            char(3) not null,\n" +
                        "   primary key (IDUSUARIO, ID_OPCION)\n" +
                        ");");
                db.execSQL("create table ASIGNAPRODUCTO\n" +
                        "(\n" +
                        "   IDMENU               int not null,\n" +
                        "   IDPRODUCTO           int not null\n" +
                        "  \n" +
                        ");");
                db.execSQL("create table DETALLEPEDIDO\n" +
                        "(\n" +
                        "   CANTIDAD             smallint not null,\n" +
                        "   SUBTOTAL             real not null,\n" +
                        "   IDDETALLEPEDIDO      INTEGER not null,\n" +
                        "   IDMENU               int not null,\n" +
                        "   primary key (IDDETALLEPEDIDO)\n" +
                        ");");
                db.execSQL("create table ESTADOPEDIDO\n" +
                        "(\n" +
                        "   IDESTADOPEDIDO       int not null,\n" +
                        "   DESCESTADOPEDIDO     varchar(30) not null,\n" +
                        "   primary key (IDESTADOPEDIDO)\n" +
                        ");");
                db.execSQL("create table FACULTAD\n" +
                        "(\n" +
                        "   IDFACULTAD           INTEGER not null,\n" +
                        "   NOMFACULTAD          varchar(30) not null,\n" +
                        "   primary key (IDFACULTAD)\n" +
                        ");");
                db.execSQL("create table LOCAL\n" +
                        "(\n" +
                        "   IDLOCAL              INTEGER not null,\n" +
                        "   IDUSUARIO            varchar(30),\n" +
                        "   NOMBRELOCAL          varchar(50) not null,\n" +
                        "   primary key (IDLOCAL)\n" +
                        ");");
                db.execSQL("create table MENU\n" +
                        "(\n" +
                        "   IDMENU               INTEGER not null,\n" +
                        "   IDLOCAL              Integer not null,\n" +
                        "   PRECIOMENU           real not null,\n" +
                        "   FECHADESDEMENU       varchar(50) not null,\n" +
                        "   FECHAHASTAMENU       varchar(50) not null,\n" +
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
                        "   IDPEDIDO             INTEGER not null,\n" +
                        "   IDDETALLEPEDIDO      int,\n" +
                        "   IDESTADOPEDIDO       int,\n" +
                        "   IDLOCAL              int,\n" +
                        "   IDUBICACION          int not null,\n" +
                        "   FECHAPEDIDO          varchar(10) not null,\n" +
                        "   TOTALPEDIDO          real not null,\n" +
                        "   primary key (IDPEDIDO)\n" +
                        ");");
                db.execSQL("create table PEDIDOREALIZADO\n" +
                        "(\n" +
                        "   IDPEDIDO             int not null,\n" +
                        "   IDUSUARIO            varchar(30) not null,\n" +
                        "   TIPOPEDREALIZADO     varchar(30) not null,\n" +
                        "   primary key (IDPEDIDO, IDUSUARIO)\n" +
                        ");");
                db.execSQL("create table PEDIDOASIGNADO\n" +
                        "(\n" +
                        "   IDPEDIDO             int not null,\n" +
                        "   IDUSUARIO            varchar(30) not null,\n" +
                        "   primary key (IDPEDIDO, IDUSUARIO)\n" +
                        ");");
                db.execSQL("create table PRODUCTO\n" +
                        "(\n" +
                        "   IDPRODUCTO           INTEGER not null,\n" +
                        "   NOMBREPRODUCTO       varchar(50) not null,\n" +
                        "   PRECIOUNITARIO       real not null,\n" +
                        "   DESCPRODUCTO         varchar(50),\n" +
                        "   primary key (IDPRODUCTO)\n" +
                        ");");
                db.execSQL("create table TIPOUSUARIO\n" +
                        "(\n" +
                        "   IDTIPOUSUARIO        int not null,\n" +
                        "   NOMTIPOUSUARIO       varchar(30) not null,\n" +
                        "   primary key (IDTIPOUSUARIO)\n" +
                        ");");
                db.execSQL("create table UBICACION\n" +
                        "(\n" +
                        "   IDUBICACION          INTEGER not null,\n" +
                        "   IDFACULTAD           INTEGER,\n" +
                        "   DIRECUBICACION       varchar(100) not null,\n" +
                        "   NOMUBICACION         varchar(30) not null,\n" +
                        "   PUNTOREFUBICACION    varchar(50) not null,\n" +
                        "   IDUSUARIO    varchar(50) not null,\n" +
                        "   primary key (IDUBICACION)\n" +
                        ");");
                db.execSQL("create table USUARIO\n" +
                        "(\n" +
                        "   IDUSUARIO            varchar(30) not null,\n" +
                        "   IDTIPOUSUARIO        int,\n" +
                        "   CONTRASENA           varchar(10) not null,\n" +
                        "   NOMBREUSUARIO        varchar(30) not null,\n" +
                        "   TELEUSUARIO          varchar(9),\n" +
                        "   APELLIDOUSUARIO      varchar(30) not null,\n" +
                        "   ESTDISPONREPARTIDOR  varchar(20),\n" +
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

    //Agregare un metodo para llenar algunas partes de la base y hacer pruebas
    public String llenarUsuario(){
        final String [] nomUsuario =new String[]{"Laura","Pepito","Carlos","Juanjo","Amber","Camila"};
        final String [] apeUsuario =new String[]{"Coto","Perez","Guzman","Herrera","Rose","Gomez"};
        final String [] contras =new String[]{"Lau1","Pepi1","Car1","Juan1","amber","Camila"};
        final String [] telefono =new String[]{"78156920","65258710","77458123","71458931","22457812","65247812"};
        final String [] idUsus =new String[]{"1","2","3","4","5","6"};
        final int [] idTipoUsus =new int[] {1,2,3,3,1,2}; //1 indica que son clientes
        final String [] estadoUsuario =new String[] {"0","1","0","0","0","1"}; //1 indica que son clientes

        final int [] idTipos =new int[] {1,2,3};
        final String [] nomTipos =new String[]{"Cliente","Repartidor","Encargado"};

        final int [] cantidadDetP = new int[] {1,5,6,7,8};
        final double[] subtotal = new double[] {1.5,2.8,10,8.2,6.5};
        final int[] idMenu = new int[] {1,2,3,4,5};
        final int[] idLocalMenu = new int[] {1,2,1,2,1};
        final double[] precioMenu = new double[] {1.5,2.8,2.4,8.2,6.5};
        final String[] fechaDesde = new String[] {"2019-06-17","2020-03-04","2020-04-10","2020-06-02","2020-06-14"};
        final String[] fechaHasta = new String[] {"2020-09-17","2020-10-10","2020-11-10","2020-08-02","2020-09-14"};
        String [] nomMenu =new String[]{"Hamburguesa","Pollo frito","Carne Asada","Papa rellena","Café"};

        final int[] idProducto = new int[] {1,2,3,4,5};
        String [] nomProducto =new String[]{"Papas","Soda","Ensalada","Chimol","Limonada"};
        final double[] precioUnitarioProducto = new double[]{1.00,0.80,0.50,0.75,0.90};
        String [] descProducto =new String[]{"Papas Fritas","Coca-Cola, Pepsi, entre otras","Fresca, coditos, etc","Bien picado","De limón"};

        final int[] idMenuAsig = new int[] {1,1,2,2,2,3,3,4,4};
        final int[] idProductoAsig = new int[] {1,2,1,2,3,2,4,2,3};

        final int[] idDetalleP = new int[] {1,2,3,4,5};
        final int[] idEstadoPed = new int[] {1,2};
        final String[] descEstP = new String[] {"Pendiente","Entregado"};

        final String[] nombreLocal = new String[] {"Don Carlos","Comedor Juan"};
        final String [] idUsuarioLocal =new String[]{"Car1","Juan1"};

        final int[] idPedido = new int[] {1,2,3,4,5};
        final int[] idEstadoPedPed = new int[] {1,2,1,1,2};
        final int[] idLocalPed = new int[] {1,2,1,1,2};
        final int[] idUbicPed = new int[] {3,3,3,2,2};
        final String[] fechaP = new String[] {"2020-06-17","2020-05-17","2020-04-10","2020-06-02","2020-06-14"};
        final double[] total = new double[] {5,2.8,10.2,4.2,12.5};

        final String [] idUsuarioPed =new String[]{"Lau1","Lau1","amber","amber","amber"};
        final String [] tipoPedReal =new String[]{"Local","Llevar","Local","Local","Llevar"};

        String res="No paso nada";

        final int [] idFacultad =new int[]{1,2,3,4,5};
        final String [] nomFacultad =new String[]{"FIA","Derecho","Economía","Psicología","Agronomía"};

        final String[] direcUbicacion = new String[]{"Sierra Morena", "Soyapango", "Mejicanos","Ciudad Delgado", "Ayutuxtepeque"};
        final String[] nomUbicacion = new String[]{"Casa de Lidia", "Casa de Pablo", "Casa de Carlos","Casa de Vane", "Casa de Fer"};
        final String[] puntoUbicacion = new String[]{"Delichely", "Iglesia", "Tienda","Parroquia", "Bajadas"};
        final String[] ubiUsuarios = new String[]{"Lau1", "Lau1", "Lau1","amber", "amber"};




        abrir();
        db.execSQL("DELETE FROM USUARIO");
        db.execSQL("DELETE FROM TIPOUSUARIO");
        db.execSQL("DELETE FROM FACULTAD");
        db.execSQL("DELETE FROM UBICACION");
        db.execSQL("DELETE FROM LOCAL");
        db.execSQL("DELETE FROM ESTADOPEDIDO");
        db.execSQL("DELETE FROM DETALLEPEDIDO");
        db.execSQL("DELETE FROM PEDIDO");
        db.execSQL("DELETE FROM PRODUCTO");
        db.execSQL("DELETE FROM ASIGNAPRODUCTO");
        db.execSQL("DELETE FROM MENU");

        TipoUsuario t = new TipoUsuario();
        for (int i = 0; i <3 ; i++) {
            t.setIdTipoUsuario(idTipos[i]);
            t.setNomTipoUsuario(nomTipos[i]);
            CrearTipoUsuario(t);
        }


        Usuario u = new Usuario();
        for (int i = 0; i <6 ; i++) {
            u.setIdUsuario(contras[i]);
            u.setNombreUsuario(nomUsuario[i]);
            u.setApellidoUsuario(apeUsuario[i]);
            u.setContrasena(idUsus[i]);
            u.setTeleUsuario(telefono[i]);
            u.setIdTipoUsuario(idTipoUsus[i]);
            u.setEstado(estadoUsuario[i]);
            res = insertar(u);
        }

        Facultad f = new Facultad();
        for (int i = 0; i < 5; i++)
        {
            f.setNomFacultad(nomFacultad[i]);
            insertar(f);
        }

        Ubicacion ubi = new Ubicacion();
        for (int i = 0; i < 5; i++)
        {
            ubi.setIdFacultad(idFacultad[i]);
            ubi.setDirecUbicacion(direcUbicacion[i]);
            ubi.setNomUbicacion(nomUbicacion[i]);
            ubi.setPuntoRefUbicacion(puntoUbicacion[i]);
            ubi.setIdUsuario(ubiUsuarios[i]);
            insertar(ubi);
        }

        Menu menu = new Menu();
        for (int i = 0; i <5 ; i++) {
            menu.setNomMenu(nomMenu[i]);
            menu.setFechaDesdeMenu(fechaDesde[i]);
            menu.setFechaHastaMenu(fechaHasta[i]);
            menu.setIdLocal(idLocalMenu[i]);
            menu.setPrecioMenu(precioMenu[i]);
            CrearMenu(menu);
        }

        DetallePedido dp = new DetallePedido();
        for (int i = 0; i <5 ; i++) {
            dp.setCantidad(cantidadDetP[i]);
            dp.setIdMenu(idMenu[i]);
            dp.setSubtotal(subtotal[i]);
            dp.setIdDetallePedido(idDetalleP[i]);
            Crear(dp);
        }

        EstadoPedido ep =new EstadoPedido();
        for (int i = 0; i <2 ; i++) {
            ep.setDescEstadoPedido(descEstP[i]);
            ep.setIdEstadoPedido(idEstadoPed[i]);
            CrearEstadoPedido(ep);
        }

        Local local = new Local();
        for (int i = 0; i <2 ; i++) {
            local.setIdUsuario(idUsuarioLocal[i]);
            local.setNombreLocal(nombreLocal[i]);
            CrearLocal(local);
        }

        Pedido pedido = new Pedido();
        for (int i = 0; i <5 ; i++) {
            pedido.setIdDetalleP(idDetalleP[i]);
            pedido.setFechaPedido(fechaP[i]);
            pedido.setIdEstadoPedido(idEstadoPedPed[i]);
            pedido.setIdUbicacion(idUbicPed[i]);
            pedido.setTotalPedido(total[i]);
            pedido.setIdPedido(idPedido[i]);
            pedido.setIdLocal(idLocalPed[i]);
            insertar(pedido);
        }

        PedidoRealizado pedidoRealizado= new PedidoRealizado();
        for (int i = 0; i <5 ; i++) {
            pedidoRealizado.setIdPedido(idPedido[i]);
            pedidoRealizado.setIdUsuario(idUsuarioPed[i]);
            pedidoRealizado.setTipo(tipoPedReal[i]);
            insertar(pedidoRealizado);
        }


        Producto producto = new Producto();
        for (int i = 0; i <5 ; i++) {
            producto.setNombreProducto(nomProducto[i]);
            producto.setPrecioUnitario(precioUnitarioProducto[i]);
            producto.setDescProducto(descProducto[i]);
            CrearProducto(producto);
        }

        ProductoAsignar productoAsignar = new ProductoAsignar();
        for (int i = 0; i <9 ; i++) {
            productoAsignar.setIdmenu(idMenuAsig[i]);
            productoAsignar.setIdProducto(idProductoAsig[i]);
            AsignarProtoMenu(productoAsignar);
        }

        cerrar();
        return res;
    }


    //Metodos para obtener el ultimo id de una tabla
    public DetallePedido ultimoIdDetallePedido()
    {
        Cursor cur = db.rawQuery("select * from DETALLEPEDIDO where IDDETALLEPEDIDO = (select max(IDDETALLEPEDIDO) from DETALLEPEDIDO);", null);
        if (cur.moveToFirst()) {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setCantidad(cur.getInt(0));
            detallePedido.setSubtotal(cur.getDouble(1));
            detallePedido.setIdDetallePedido(cur.getInt(2));
            detallePedido.setIdMenu(cur.getInt(3));
            return detallePedido;

        } else {
            return null;
        }
    }

    public int ultimoIdPedido()
    {
        Cursor cur = db.rawQuery("select * from PEDIDO where IDPEDIDO = (select max(IDPEDIDO) from PEDIDO);", null);
        if (cur.moveToFirst()) {
            return cur.getInt(0);

        } else {
            return 0;
        }
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
        usu.put("ESTDISPONREPARTIDOR",usuario.getEstado());


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
        Cursor cur = db.rawQuery("select * from Usuario where idUsuario = '" +idUsuario+"'", null);
        if (cur.moveToFirst()) {
            Usuario usu1 = new Usuario();
            usu1.setIdUsuario((cur.getString(0)));
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
                        usus.add(new Usuario(cur.getString(0)
                        , cur.getInt(1)
                        , cur.getString(2)
                        , cur.getString(3)
                        , cur.getString(4)
                        , cur.getString(5)
                        , cur.getString(6)));
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
                usuact.put("ESTDISPONREPARTIDOR",usuario.getEstado());
                db.update("Usuario", usuact, "idUsuario=?", id);

            } else {
                resultado = "el tipo de usuario no existe, pruebe con uno existente";
            }

        } else {
            resultado = "no hay registros de usuario con el código " + id;
        }
        return resultado;
    }

    public String actualizarUsuario2(Usuario usuario,String idUsuario) {
        String resultado = "datos actualizados";
        String[] id = {idUsuario};
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
                usuact.put("ESTDISPONREPARTIDOR",usuario.getEstado());
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
        String[] id = {usuario.getIdUsuario()};
        Cursor cur = db.query("Usuario", null, "idUsuario = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            Cursor k = db.query("PedidoRealizado", null, "idUsuario = ?", id, null, null, null);
            Cursor m = db.query("Local", null, "idUsuario = ?", id, null, null, null);
            if (k.moveToFirst()) {
                int cantPR = db.delete("PedidoRealizado", "idUsuario= '" + usuario.getIdUsuario()+"'", null);
                resultado = resultado + " y el/los " + cantPR + " pedidos de este usuario ";
            }
            if (m.moveToFirst()) {
                int cantL = db.delete("Local", "idUsuario = '" + usuario.getIdUsuario() + "'", null);
                resultado = resultado + " y el/los " + cantL + " locales de los que era propietario  ";
            }
            int cantU = db.delete("Usuario", "idUsuario = '" + usuario.getIdUsuario()+"'", null);

        } else {
            resultado = "el usuario no existe";
        }
        return null;

    }
    //CRUD tipoUsuario

    public String CrearTipoUsuario(TipoUsuario tipousuario) {
        String resultado = "Tipo de usuario creado ";
        ContentValues tusu = new ContentValues();
        tusu.put("idTipoUsuario", tipousuario.getIdTipoUsuario());
        tusu.put("nomTipoUsuario", tipousuario.getNomTipoUsuario());

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
        //pro777.put("idProducto", producto.getIdProduto());
        pro777.put("NOMBREPRODUCTO", producto.getNombreProducto());
        pro777.put("PRECIOUNITARIO", producto.getPrecioUnitario());
        pro777.put("DESCPRODUCTO", producto.getDescProducto());



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
                pro777.add(new Producto(cur.getInt(0), cur.getString(1),cur.getDouble(2),cur.getString(3)));
            } while (cur.moveToNext());


        }
        return pro777;
    }

    public List<Producto> ConsultaProductosMenu(int IDMENU) {
        Cursor cur = db.rawQuery("SELECT PRODUCTO.* FROM \n" +
                "(PRODUCTO INNER JOIN ASIGNAPRODUCTO ON PRODUCTO.IDPRODUCTO = ASIGNAPRODUCTO.IDPRODUCTO)\n" +
                "INNER JOIN MENU ON ASIGNAPRODUCTO.IDMENU = MENU.IDMENU WHERE MENU.IDMENU = " + IDMENU, null);
        List<Producto> pro777 = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                pro777.add(new Producto(cur.getInt(0), cur.getString(1),cur.getDouble(2),cur.getString(3)));
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

        Cursor cur = db.query("Producto", null, "IDPRODUCTO = ?", id, null, null, null);

        if (cur.moveToFirst()) {
            Cursor m = db.query("ASIGNAPRODUCTO", null, "IDPRODUCTO = ?", id, null, null, null);

            if (m.moveToFirst()) {
                cont = db.delete("ASIGNAPRODUCTO", "IDPRODUCTO = ?" + producto.getIdProduto(), null);
                resultado = resultado + ", " + cont + " Productos eliminados de los menús";
            }
            else
            {
                resultado = "Este Producto no se encuentra en ningun menu";
            }
            comprobador = db.delete("Producto", "idProducto =" + producto.getIdProduto(), null);
        }
        else {
            resultado = "Ese Producto no existe";
        }
        return resultado;
    }

    //CRUD MENU
    public String CrearMenu(Menu menu) {
        String resultado = "Menu creado ";

        ContentValues uwu = new ContentValues();
        ContentValues proMenu = new ContentValues();

        //uwu.put("idMenu", menu.getIdMenu());
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
       /* List<Producto> Productos =  menu.getProductos();
        for (int i=0;i<Productos.size();i++)
        {
            proMenu.put("IDMENU",menu.getIdMenu());
            proMenu.put("IDPRODUCTO", Productos.get(i).getIdProduto());
            comprobador = db.insert("ASIGNARPRODUCTO",null,proMenu);
            if (comprobador ==-1 || comprobador ==0)
            {
                resultado =resultado + "no se pudo insertar el producto del id "+Productos.get(i).getIdProduto();
            }
        }*/
        return resultado;
    }

    public Menu ConsultaMenu(String idMenu) {
        String[] id = {idMenu};
        ProductoAsignar pA;
        List<ProductoAsignar> productosMenu = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        Cursor cur = db.rawQuery("select * from Menu where idMenu =" + idMenu, null);
        if (cur.moveToFirst()) {
            Cursor k = db.rawQuery("select * from ASIGNAPRODUCTO where idMenu =" + idMenu, null);
            if(k.moveToFirst())
            {
                do {
                        pA = new ProductoAsignar(k.getInt(0),k.getInt(1));
                        productosMenu.add(pA);
                }while(k.moveToNext());
                for(int i=0;i<productosMenu.size();i++)
                {Cursor m = db.rawQuery("select * from PRODUCTO where idProducto = " + String.valueOf(productosMenu.get(i).getIdProducto()), null);
                    if(m.moveToFirst()){
                        productos.add(new Producto(m.getInt(0),m.getString(1),m.getDouble(2),m.getString(3)));
                    }
                }

            }

            Menu uwu = new Menu();
            uwu.setIdMenu((cur.getInt(0)));
            uwu.setIdLocal((cur.getInt(1)));
            uwu.setPrecioMenu((cur.getInt(2)));
            uwu.setFechaDesdeMenu((cur.getString(3)));
            uwu.setFechaHastaMenu((cur.getString(4)));
            uwu.setNomMenu((cur.getString(5)));
            uwu.setProductos(productos);
            return uwu;
        } else {
            return null;
        }


    }

    public List<Menu> ConsultaMenus() {
        Cursor cur = db.rawQuery("SELECT * FROM MENU", null);
        List<Menu> uwu = new ArrayList<>();
        List<AsignarProducto> proMenu = new ArrayList<>();
        List<Producto> producto = new ArrayList<>();

        if (cur.moveToFirst()) {

            do {
                uwu.add(new Menu(cur.getInt(0), cur.getInt(1), cur.getDouble(2), cur.getString(3), cur.getString(4), cur.getString(5)));
            } while (cur.moveToNext());
        }
       /* if (cur.moveToFirst()) {

            do {
                String[] idMenu = {String.valueOf(cur.getInt(0))};
                Cursor m = db.rawQuery("SELECT * FROM PRODUCTOASIGNADO WHERE =?"+idMenu,null);
                if(m.moveToFirst())
                {
                    proMenu.add(new AsignarProducto(m.getInt(0),m.getInt(1)));
                }
                for(int i=0;i<proMenu.size();i++)
                {
                    Cursor k = db.rawQuery("SELECT * FROM PRODUCTO WHERE =?"+proMenu.get(i).getIDPRODUCTO(),null);
                    if(k.moveToFirst())
                    {
                        producto.add(new Producto(k.getInt(0),k.getString(1),k.getInt(2),k.getString(3)));
                    }
                }
                uwu.add(new Menu(cur.getInt(0),
                        producto,
                        cur.getInt(2),
                        cur.getInt(3),
                        cur.getString(4),
                        cur.getString(5),
                        cur.getString(6)));
            } while (cur.moveToNext());
        }*/
        return uwu;
    }

    public List<Menu> ConsultaMenusLocal(int idlocal) {
        Cursor cur = db.rawQuery("SELECT * FROM MENU WHERE IDLOCAL=" + idlocal, null);
        List<Menu> uwu = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                uwu.add(new Menu(cur.getInt(0), cur.getInt(1), cur.getDouble(2), cur.getString(3), cur.getString(4), cur.getString(5)));
            } while (cur.moveToNext());
        }
        return uwu;
    }

    public List<Menu> ConsultaMenusRealizados(String idUsuario) {
        Cursor cur = db.rawQuery("SELECT MENU.* FROM ((DETALLEPEDIDO INNER JOIN PEDIDO ON DETALLEPEDIDO.IDDETALLEPEDIDO\n" +
                "= PEDIDO.IDDETALLEPEDIDO) INNER JOIN PEDIDOREALIZADO ON PEDIDO.IDPEDIDO = PEDIDOREALIZADO.IDPEDIDO)\n" +
                "INNER JOIN MENU ON MENU.IDMENU = DETALLEPEDIDO.IDMENU  WHERE IDUSUARIO=" + idUsuario, null);
        List<Menu> uwu = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                uwu.add(new Menu(cur.getInt(0), cur.getInt(1), cur.getDouble(2), cur.getString(3), cur.getString(4), cur.getString(5)));
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
        int cont1 =0;
        String resultado = comprobador + " Menu eliminados ";
        String[] id = {String.valueOf(menu.getIdMenu())};
        Cursor cur = db.query("Menu", null, "idMenu = ?", id, null, null, null);
        if (cur.moveToFirst()) {
            Cursor k = db.query("DetallePedido", null, "idMenu", id, null, null, null);
            if (k.moveToFirst()) {
                cont = db.delete("DetallePedido", "idMenu =" + menu.getIdMenu(), null);

                resultado = resultado + ", " + cont + " Detalles eliminados con este Menu";
            }
            Cursor m = db.query("ASIGNARPRODUCTO", null, "IDMENU", id, null, null, null);
            if (m.moveToFirst()) {
                cont = db.delete("ASIGNARPRODUCTO", "IDMENU =" + menu.getIdMenu(), null);

                resultado = resultado + ", " + cont1 + " Productos eliminados del menú";
            }
            comprobador = db.delete("Menu", "idMenu =" + menu.getIdMenu(), null);
        } else {
            resultado = "Ese Menu no existe";
        }
        return resultado;

}


    //CD de una asignación de productos a menú

    public String AsignarProtoMenu(ProductoAsignar asigP) {
        String resultado = "producto agregado a menu";
        ContentValues prodM = new ContentValues();
        prodM.put("IDMENU", asigP.getIdmenu());
        prodM.put("IDPRODUCTO", asigP.getIdProducto());

        long comprobador = 0;
        comprobador = db.insert("ASIGNAPRODUCTO", null, prodM);
        if (comprobador == -1 || comprobador == 0) {
            resultado = "El producto no ha sigo asignado al menu ";
        }
        return resultado;
    }
    public  String QuitarProducto(ProductoAsignar asigP)
    {
        int cont = 0;
        String resultado = "Producto retirado del menú";
        String[]datos = {String.valueOf(asigP.getIdmenu()),String.valueOf(asigP.getIdProducto())};
        Cursor cursor = db.rawQuery("delete from ASIGNAPRODUCTO where idMenu =" +  asigP.getIdmenu() + " and idProducto="+asigP.getIdProducto(), null);
        if(cursor.moveToFirst()) { cont=0; }
        else {cont=1;}
        if(cont == -1 || cont == 0)
        {
            resultado= " Este Producto no está en este menu";
        }
        return resultado;
    }

    //CRUD DETALLE PEDIDO
    public String Crear(DetallePedido detallepedido) {
        String resultado = "detalle de pedido creado ";
        ContentValues depe = new ContentValues();
        depe.put("cantidad", detallepedido.getCantidad());
        depe.put("subtotal", detallepedido.getSubtotal());
        //depe.put("idDetallePedido", detallepedido.getIdDetallePedido());
        depe.put("idMenu", detallepedido.getIdMenu());

        long comprobador = 0;
        comprobador = db.insert("DetallePedido", null, depe);
        if (comprobador == -1 || comprobador == 0) {
            resultado = "oh, oh ya existe un DetallePedido con ese codigo ): ";
        }
        return resultado;
    }

    public DetallePedido ConsultaDetallePedido(int iddetallePedido) {
        String[] id = {String.valueOf(iddetallePedido)};
        Cursor cur = db.rawQuery("select * from DetallePedido where idDetallePedido =" + iddetallePedido, null);
        if (cur.moveToFirst()) {
            DetallePedido depe = new DetallePedido();
            depe.setCantidad((cur.getInt(0)));
            depe.setSubtotal((cur.getInt(1)));
            depe.setIdDetallePedido((cur.getInt(2)));
            depe.setIdMenu(cur.getInt(3));
            return depe;

        } else {
            return null;
        }


    }

    public List<DetallePedido> ConsultaDetallePedidoRealizado(String idUsuario) {
        //String[] id = {String.valueOf(idUsuario)};
        Cursor cur = db.rawQuery("SELECT DETALLEPEDIDO.* FROM ((DETALLEPEDIDO INNER JOIN PEDIDO ON DETALLEPEDIDO.IDDETALLEPEDIDO\n" +
                "= PEDIDO.IDDETALLEPEDIDO) INNER JOIN PEDIDOREALIZADO ON PEDIDO.IDPEDIDO = PEDIDOREALIZADO.IDPEDIDO)\n" +
                " WHERE IDUSUARIO= '" + idUsuario+"'", null);
        List<DetallePedido> depe = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                depe.add(new DetallePedido(cur.getInt(0),
                        cur.getInt(1),
                        cur.getInt(2),
                        cur.getInt(3)
                ));
            } while (cur.moveToNext());


        }
        return depe;


    }

    public List<DetallePedido> ConsultaDetallePedidos() {
        Cursor cur = db.rawQuery("SELECT * FROM DetallePedido", null);
        List<DetallePedido> depe = new ArrayList<>();
        if (cur.moveToFirst()) {

            do {
                depe.add(new DetallePedido(cur.getInt(0),
                        cur.getInt(1),
                        cur.getInt(2),
                        cur.getInt(3)
                        ));
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
        //lokxti.put("idLocal", local.getIdLocal());
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
            lokxti.setIdUsuario((cur.getString(1)));
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
                lokxti.add(new Local(cur.getInt(0),cur.getString(1), cur.getString(2)));
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
        if(verificarIntegridad(ubicacion,2) && verificarIntegridad(ubicacion,21)){ //verificando que exista idPedido en pedido, idFacultad en facultad e idUsuario en Usuario para insertar en ubicacion
            ContentValues ubic = new ContentValues();
            ubic.put("idFacultad", ubicacion.getIdFacultad());
            //ubic.put("idPedido", ubicacion.getIdPedido());
            ubic.put("direcUbicacion", ubicacion.getDirecUbicacion());
            ubic.put("nomUbicacion", ubicacion.getNomUbicacion());
            ubic.put("puntoRefUbicacion", ubicacion.getPuntoRefUbicacion());
            ubic.put("idUsuario", ubicacion.getIdUsuario());
            contador=db.insert("Ubicacion", null, ubic);
        }
        else {
            resultado="Facultad, Pedido y Usuario no existen";
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
            ubicacion.setIdFacultad(cursor.getInt(1));
            //ubicacion.setIdPedido(1);
            ubicacion.setDirecUbicacion(cursor.getString(2));
            ubicacion.setNomUbicacion(cursor.getString(3));
            ubicacion.setPuntoRefUbicacion(cursor.getString(4));
            ubicacion.setIdUsuario(cursor.getString(5));
            return ubicacion;
        }
        else {
            return null;
        }
    }

    public List<Ubicacion> consultarUbicacionUsuario(String idUsuario){
        Cursor cur = db.rawQuery("SELECT * FROM Ubicacion WHERE IDUSUARIO = '" + idUsuario+"'", null);
        List<Ubicacion> ubic = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                ubic.add(new Ubicacion(cur.getInt(0),
                        cur.getInt(1),
                        cur.getString(2),
                        cur.getString(3),
                        cur.getString(4),
                        cur.getString(5)));
            } while (cur.moveToNext());
        }
        return ubic;
    }

    public List<Ubicacion> ConsultaUbicaciones() {
        Cursor cur = db.rawQuery("SELECT * FROM Ubicacion", null);
        List<Ubicacion> ubic = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                ubic.add(new Ubicacion(cur.getInt(0),
                        cur.getInt(1),
                        cur.getString(2),
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
            if(verificarIntegridad(ubicacion,2) && verificarIntegridad(ubicacion,3) && verificarIntegridad(ubicacion,21)){
                String[] idU = {String.valueOf(ubicacion.getIdUbicacion())};
                ContentValues cv = new ContentValues();
                cv.put("idUbicacion",ubicacion.getIdUbicacion());
                //cv.put("idPedido",ubicacion.getIdPedido());
                cv.put("idFacultad",ubicacion.getIdFacultad());
                cv.put("nomUbicacion",ubicacion.getNomUbicacion());
                cv.put("direcUbicacion",ubicacion.getDirecUbicacion());
                cv.put("puntoRefUbicacion",ubicacion.getPuntoRefUbicacion());
                cv.put("idUsuario",ubicacion.getIdUsuario());
                db.update("Ubicacion", cv, "idUbicacion = ?", idU);
                return "Registro de Ubicacion Actualizado Correctamente";
            }
            else{
                return "El codigo de facultad, usuario o pedido no existe";
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
            /*if(verificarIntegridad(ubicacion,3)){
                contadorP+=db.delete("Pedido", "idPedido= '"+ubicacion.getIdPedido()+"'",null);
                resultado+= resultado + " Se elimino el/los "+ contadorP+" registros de Pedido";
            }*/
            contadorU+=db.delete("Ubicacion","idUbicacion= '"+ubicacion.getIdUbicacion()+"'",null);
        }else {
            resultado= "La ubicacion no existe";
        }
        return resultado;
    }

    //CRUD Pedido
    public String insertar(Pedido pedido){
        String resultado="Se guardó correctamente nuevo pedido ";
        long contador=0;
        long cont = 0;
        ArrayList <DetallePedido> detallePedido = pedido.getDetallePedidos();
        DetallePedido det = new DetallePedido();
        //verificando Integridad
        // verificar que existe Pedido
        if (verificarIntegridad(pedido, 4)) {
            //se encontro pedido
            resultado="Este pedido ya existe. Registro Duplicado, ERROR";
        } else if(verificarIntegridad(pedido,6) && verificarIntegridad(pedido,7) && verificarIntegridad(pedido,8)){ //verificando que exista idEstadoPedido en EstadPedido e idLocal en Local e idUbicacion en Ubicacion para insertar en pedido
            ContentValues pedi = new ContentValues();
            pedi.put("idEstadoPedido",pedido.getIdEstadoPedido());
            pedi.put("idLocal",pedido.getIdLocal());
            pedi.put("idUbicacion",pedido.getIdUbicacion());
            pedi.put("fechaPedido",pedido.getFechaPedido());
            pedi.put("totalPedido",pedido.getTotalPedido());
            pedi.put("idDetallePedido",pedido.getIdDetalleP());
            contador=db.insert("Pedido", null, pedi);

           /* for(int i= 0; i < detallePedido.size(); i++){
                det.setIdDetallePedido(detallePedido.get(i).getIdDetallePedido());
                det.setCantidad(detallePedido.get(i).getCantidad());
                det.setSubtotal(detallePedido.get(i).getSubtotal());
                Crear(det);
                pedi.put("idDetallePedido", detallePedido.get(i).getIdDetallePedido());
                cont = db.insert("Pedido", null, pedi);
                if(cont == -1 || cont == 0)
                {
                   resultado = resultado + "no se pudo insertar el detallePedido con el id "+ detallePedido.get(i).getIdDetallePedido();
                }
            }*/
        }
        else {
            resultado="EstadoPedido,Local o Ubicacion no existen";
        }
        return resultado;
    }

    public Pedido consultarPedido(String idPedido){
        ArrayList<DetallePedido> detallePedidos = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Pedido where idPedido =" + idPedido, null);
        if (cursor.moveToFirst()) {
            Cursor k = db.rawQuery("select * from DETALLEPEDIDO where idDetallePedido =" + cursor.getString(1), null);
            if(k.moveToFirst())
            {
                do {
                    detallePedidos.add(new DetallePedido(k.getInt(1),k.getInt(2),k.getInt(3),k.getInt(4)));
                }while(k.moveToNext());
            }
            Pedido pedido = new Pedido();
            pedido.setIdPedido(cursor.getInt(0));
            pedido.setDetallePedidos(detallePedidos);
            pedido.setIdEstadoPedido(cursor.getInt(2));
            pedido.setIdLocal(cursor.getInt(3));
            pedido.setIdUbicacion(cursor.getInt(4));
            pedido.setFechaPedido(cursor.getString(5));
            pedido.setTotalPedido(cursor.getDouble(6));

            return pedido;
        } else {
            return null;
        }
    }

    public List<Pedido> consultarPedidoDetalle(int  idDetallePedido){
        Cursor cur = db.rawQuery("SELECT * FROM Pedido WHERE IDDETALLEPEDIDO=" + idDetallePedido, null);
        List<Pedido> pedido = new ArrayList<>();
        ArrayList<DetallePedido> detallePedidos = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                /*Cursor m = db.rawQuery("SELECT * FROM DETALLEPEDIDO WHERE IDDETALLEPEDIDO=?" + cur.getString(1), null);
                if (m.moveToFirst()) {
                    detallePedidos.add(new DetallePedido(m.getInt(1), m.getInt(2), m.getInt(3),m.getInt(4)));
                }while(m.moveToNext());*/
                pedido.add(new Pedido(cur.getInt(0),
                        cur.getInt(1),
                        cur.getInt(2),
                        cur.getInt(3),
                        cur.getInt(4),
                        cur.getString(5),
                        cur.getDouble(6)));
            } while (cur.moveToNext());
        }
        return pedido;
    }


    public List<Pedido> ConsultaPedidos() {
        Cursor cur = db.rawQuery("SELECT * FROM Pedido", null);
        List<Pedido> pedido = new ArrayList<>();
        ArrayList<DetallePedido> detallePedidos = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                Cursor m = db.rawQuery("SELECT * FROM DETALLEPEDIDO WHERE IDDETALLEPEDIDO=?" + cur.getString(1), null);
                if (m.moveToFirst()) {
                    detallePedidos.add(new DetallePedido(m.getInt(1), m.getInt(2), m.getInt(3),m.getInt(4)));
                }while(m.moveToNext());
                pedido.add(new Pedido(cur.getInt(0),
                        detallePedidos,
                        cur.getInt(2),
                        cur.getInt(3),
                        cur.getInt(4),
                        cur.getString(5),
                        cur.getDouble(6)));
            } while (cur.moveToNext());
        }
        return pedido;
    }

    public List<Pedido> ConsultaPedidosLocal(int idLocal) {
        Cursor cur = db.rawQuery("SELECT * FROM Pedido WHERE IDLOCAL=" + idLocal, null);
        List<Pedido> pedido = new ArrayList<>();
        ArrayList<DetallePedido> detallePedidos = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                /*Cursor m = db.rawQuery("SELECT * FROM DETALLEPEDIDO WHERE IDDETALLEPEDIDO=?" + cur.getString(1), null);
                if (m.moveToFirst()) {
                    detallePedidos.add(new DetallePedido(m.getInt(1), m.getInt(2), m.getInt(3),m.getInt(4)));
                }while(m.moveToNext());*/
                pedido.add(new Pedido(cur.getInt(0),
                        cur.getInt(1),
                        cur.getInt(2),
                        cur.getInt(3),
                        cur.getInt(4),
                        cur.getString(5),
                        cur.getDouble(6)));
            } while (cur.moveToNext());
        }
        return pedido;
    }

    public String actualizar(Pedido pedido){
        //verificando que exista pedido
        if(verificarIntegridad(pedido, 4)){
            if(verificarIntegridad(pedido,6) && verificarIntegridad(pedido,7)&& verificarIntegridad(pedido,8)){
                String[] idP = {String.valueOf(pedido.getIdPedido())};
                ContentValues cv = new ContentValues();
                cv.put("idPedido",pedido.getIdPedido());
                cv.put("idEstadoPedido",pedido.getIdEstadoPedido());
                cv.put("idLocal",pedido.getIdLocal());
                cv.put("idUbicacion",pedido.getIdUbicacion());
                cv.put("fechaPedido",pedido.getFechaPedido());
                cv.put("totalPedido",pedido.getTotalPedido());
                db.update("Pedido", cv, "idPedido = ?", idP);
                return "Registro de Pedido Actualizado Correctamente";
            }
            else{
                return "El codigo de EstadPedido o Local o Ubicacion no existe";
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
        int cont1 = 0;
        ArrayList<DetallePedido> detallePedidos = pedido.getDetallePedidos();
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
            /*if(verificarIntegridad(pedido,8)){
                contadorU+=db.delete("Ubicacion", "idUbicacion= '"+pedido.getIdUbicacion()+"'",null);
                resultado+= resultado + " Se elimino el/los "+ contadorU+" registros de Ubicacion";
            }*/
            /*for (int i = 0; i < detallePedidos.size();i++)
            {
                Cursor m = db.query(true, "DetallePedido", new String[]{"idDetallePedido"}, "idDetallePedido= '" + detallePedidos.get(i).getIdDetallePedido()+ "'", null, null, null, null, null);
                if (m.moveToFirst()) {
                    cont1 = db.delete("DETALLEPEDIDO", "IDDETALLEPEDIDO =" + detallePedidos.get(i).getIdDetallePedido(), null);

                    resultado = resultado + ", " + cont1 + " Detalles de pedido eliminados de pedido";
                }
            }*/
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
            facu.put("nomFacultad",facultad.getNomFacultad());
            contador=db.insert("Facultad", null, facu);
        }
        return resultado;
    }

    public Facultad consultarFacultad(String idFacultad){
        Cursor cursor = db.rawQuery("select * from Facultad where idFacultad =?" + idFacultad, null);
        //si existe facultad
        if(cursor.moveToFirst())
        {
            Facultad facultad = new Facultad();
            facultad.setIdFacultad(cursor.getInt(0));
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
                facu.add(new Facultad(cur.getInt(0),
                        cur.getString(1)));
            } while (cur.moveToNext());

        }
        return facu;
    }

    public String actualizar(Facultad facultad){
        //verificando que exista facultad
        if(verificarIntegridad(facultad, 9)){
            String[] idF = {String.valueOf(facultad.getIdFacultad())};
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

    public PedidoAsignado consultarPedAsig(int idPedido, String idUsuario){
        String[] id = {String.valueOf(idPedido),idUsuario};
        Cursor cursor = db.rawQuery("select * from PedidoAsignado where idPedido = ? and idUsuario = ?" + id, null);
        //si existe pedidoAsignado
        if(cursor.moveToFirst())
        {
            PedidoAsignado pedidoAsignado = new PedidoAsignado();
            pedidoAsignado.setIdPedido(cursor.getInt(0));
            pedidoAsignado.setIdUsuario(cursor.getString(1));
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
                        cur.getString(1)));
            } while (cur.moveToNext());

        }
        return peasig;
    }

    public String actualizar(PedidoAsignado pedidoAsignado){
        //verificando integridad
        if(verificarIntegridad(pedidoAsignado, 14)){
            String[] idPA = {String.valueOf(pedidoAsignado.getIdPedido()),pedidoAsignado.getIdUsuario()};
            ContentValues cv = new ContentValues();
            cv.put("idPedido",pedidoAsignado.getIdPedido());
            cv.put("idUsuario",pedidoAsignado.getIdUsuario());
            db.update("PedidoAsignado", cv, "idPedidoAsignado = ? and idPedido= ? and idUsuario= ?", idPA);
            return "Registro de PedidoAsignado Actualizado Correctamente";
        }else{
            return "Registro con codigo no existe";
        }
    }
    public String eliminar(PedidoAsignado pedidoAsignado){
        String resultado = "Se elimino pedidoAsignado";
        String[] idPA = {String.valueOf(pedidoAsignado.getIdPedido()),pedidoAsignado.getIdUsuario()};
        int contadorPA=0;
        //verificar que exista pedidoAsignado
        if(verificarIntegridad(pedidoAsignado,14)){
            contadorPA+=db.delete("PedidoAsignado","idPedido = ? and idUsuario = ? '"+ idPA +"'",null);
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
            preal.put("idPedido", pedidoRealizado.getIdPedido());
            preal.put("idUsuario", pedidoRealizado.getIdUsuario());
            preal.put("TIPOPEDREALIZADO", pedidoRealizado.getTipo());
            contador=db.insert("PedidoRealizado", null, preal);
        }
        if(contador==-1 || contador==0)
        {
            resultado= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {

        }
        return resultado;
    }

    public PedidoRealizado consultarPedReal(int idPedido, String idUsuario){
        String[] id = {String.valueOf(idPedido),idUsuario};
        Cursor cursor = db.rawQuery("select * from PedidoRealizado where idPedido = ? and idUsuario = ?" + id, null);
        //si existe pedidoRealizado
        if(cursor.moveToFirst())
        {
            PedidoRealizado pedidoRealizado = new PedidoRealizado();
            pedidoRealizado.setIdPedido(cursor.getInt(1));
            pedidoRealizado.setIdUsuario(cursor.getString(2));
            pedidoRealizado.setTipo(cursor.getString(3));
            return pedidoRealizado;
        }
        else {
            return null;
        }
    }

    public PedidoRealizado consultarPedRealIdP(String idPedido){
        String[] id = {idPedido};
        Cursor cursor = db.rawQuery("select * from PedidoRealizado where idPedido =" + idPedido, null);
        //si existe pedidoRealizado
        if(cursor.moveToFirst())
        {
            PedidoRealizado pedidoRealizado = new PedidoRealizado();
            pedidoRealizado.setIdPedido(cursor.getInt(0));
            pedidoRealizado.setIdUsuario(cursor.getString(1));
            pedidoRealizado.setTipo(cursor.getString(2));
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
                        cur.getString(1),
                        cur.getString(2)));
            } while (cur.moveToNext());

        }
        return pedreal;
    }

    public String actualizar(PedidoRealizado pedidoRealizado){
        //verificando integridad
        if(verificarIntegridad(pedidoRealizado, 16)){
            String[] idPR = {String.valueOf(String.valueOf(pedidoRealizado.getIdPedido())),pedidoRealizado.getIdUsuario()};
            ContentValues cv = new ContentValues();
            cv.put("idPedido",pedidoRealizado.getIdPedido());
            cv.put("idUsuario",pedidoRealizado.getIdUsuario());
            cv.put("tipoPedRealizado",pedidoRealizado.getTipo());
            db.update("PedidoRealizado", cv, "idPedido= ? and idUsuario= ?", idPR);
            return "Registro de PedidoRealizado Actualizado Correctamente";
        }else{
            return "Registro con codigo no existe";
        }
    }
    public String eliminar(PedidoRealizado pedidoRealizado){
        String resultado = "Se elimino pedidoRealizado";
        String[] idPR = {String.valueOf(pedidoRealizado.getIdPedido()),pedidoRealizado.getIdUsuario()};
        int contadorPR=0;
        //verificar que exista pedidoRealizado
        if(verificarIntegridad(pedidoRealizado,16)){
            contadorPR+=db.delete("PedidoRealizado","idPedido = ? and idUsuario = ?",idPR);
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

    public AccesoUsuario consultarAccesoUsuario(String idOpcion, String idUsuario){
        String[] id = {idOpcion,idUsuario};
        Cursor cursor = db.rawQuery("select * from AccesoUsuario where id_Opcion = ? and idUsuario = ?" + id, null);
        //si existe AccesoUsuario
        if(cursor.moveToFirst())
        {
            AccesoUsuario accesoUsuario = new AccesoUsuario();
            accesoUsuario.setIdOpcion(cursor.getString(0));
            accesoUsuario.setIdUsuario(cursor.getString(1));
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
                accusu.add(new AccesoUsuario(cur.getString(0),
                        cur.getString(1)));
            } while (cur.moveToNext());

        }
        return accusu;
    }

    public String actualizar(AccesoUsuario accesoUsuario){
        //verificando integridad
        if(verificarIntegridad(accesoUsuario, 18)){
            String[] idAU = {accesoUsuario.getIdOpcion(),accesoUsuario.getIdUsuario()};
            ContentValues cv = new ContentValues();
            cv.put("id_Opcion",accesoUsuario.getIdOpcion());
            cv.put("idUsuario",accesoUsuario.getIdUsuario());
            db.update("AccesoUsuario", cv, "id_Opcion= ? and idUsuario= ?", idAU);
            return "Registro de AccesoUsuario Actualizado Correctamente";
        }else{
            return "Registro no existe";
        }
    }
    public String eliminar(AccesoUsuario accesoUsuario){
        String resultado = "Se elimino accesoUsuario ";
        String[] idAU = {accesoUsuario.getIdOpcion(),accesoUsuario.getIdUsuario()};
        int contadorAU=0;
        //verificar que exista accesoUsuario
        if(verificarIntegridad(accesoUsuario,18)){
            contadorAU+=db.delete("AccesoUsuario","id_Opcion = ? and idUsuario = ? '"+ idAU +"'",null);
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
                /*verificar que en ubicacion exista pedido
                Ubicacion ubicacion2 = (Ubicacion) dato;
                Cursor c1 = db.query(true, "Pedido", new String[]{"idPedido"}, "idPedido= '" + ubicacion2.getIdPedido() + "'", null, null, null, null, null);
                if (c1.moveToFirst())
                    return true;
                else
                    return false;*/

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
                //verificar que en DetallePedido exista idDetallePedido
                Pedido pedido2 = (Pedido) dato;
                List<DetallePedido> detallePedidos = pedido2.getDetallePedidos();
                for (int i=0; i< detallePedidos.size();i++){
                    Cursor c1 = db.query(true, "DetallePedido", new String[]{"idDetallePedido"}, "idDetallePedido= '" + detallePedidos.get(i).getIdDetallePedido()+ "'", null, null, null, null, null);
                    if (c1.moveToFirst())
                        return true;
                    else
                        return false;
                }
            }
            case 6: {
                //verificar que en EstadPedido exista idEstadoPedido
                Pedido pedido3 = (Pedido) dato;
                Cursor c1 = db.query(true, "EstadoPedido", new String[]{"idEstadoPedido"}, "idEstadoPedido= '" + pedido3.getIdEstadoPedido() + "'", null, null, null, null, null);
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
                String[] fac = {String.valueOf(facultad.getIdFacultad())};
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
                String[] id2 = {pedidoAsignado.getIdUsuario()};
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
                String[] ids = {String.valueOf(pedidoAsignado1.getIdPedido()),
                            pedidoAsignado1.getIdUsuario()};
                Cursor c = db.query("PEDIDOASIGNADO", null, "IDPEDIDO = ? AND IDUSUARIO= ?", ids, null, null, null);
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
                String[] id2 = {pedidoRealizado.getIdUsuario()};
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
                String[] ids = {String.valueOf(pedidoRealizado1.getIdPedido()),
                        pedidoRealizado1.getIdUsuario()};
                Cursor c = db.query("PEDIDOREALIZADO", null, "IDPEDIDO = ? AND IDUSUARIO= ?", ids, null, null, null);
                if(c.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;
            }
            case 17: {
                //verificar que al insertar AccesoUsuario exista idUsuario e idOpcion
                AccesoUsuario accesoUsuario = (AccesoUsuario) dato;
                String[] id1 = {accesoUsuario.getIdUsuario()};
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
                String[] ids = {accesoUsuario1.getIdOpcion(),
                        accesoUsuario1.getIdUsuario()};
                Cursor c = db.query("ACCESOUSUARIO", null, "ID_OPCION = ? AND IDUSUARIO= ?", ids, null, null, null);
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
            case 21: {
                //verificar que en ubicacion exista usuario
                Ubicacion ubicacionU = (Ubicacion) dato;
                Cursor c1 = db.query(true, "Usuario", new String[]{"idUsuario"}, "idUsuario= '" + ubicacionU.getIdUsuario() + "'", null, null, null, null, null);
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
