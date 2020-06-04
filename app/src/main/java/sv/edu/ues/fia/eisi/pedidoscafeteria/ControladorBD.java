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

public class ControladorBD extends SQLiteOpenHelper {

    private static final String nombreBD = "cafeteriaUES.s3db";
    private static final int VERSION = 1;
    private SQLiteDatabase db;



    public ControladorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreBD,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
    public String actualizarUsuario(Usuario usuario)
    {
        String resultado = "datos actualizados";
        String[] id = {String.valueOf(usuario.getIdUsuario())};
        Cursor cur = db.query("Usuario",null,"idUsuario = ?",id,null,null,null);
        if(cur.moveToFirst())
        {
            String[] idTU = {String.valueOf(usuario.getIdTipoUsuario())};
            Cursor k = db.query("TipoUsuario",null,"idTipoUsuario = ?", idTU,null,null,null);
           if(k.moveToFirst())
           {
               ContentValues usuact = new ContentValues();
               usuact.put("idUsuario",usuario.getIdUsuario());
               usuact.put("idTipoUsuario",usuario.getIdTipoUsuario());
               usuact.put("contrasena",usuario.getContrasena());
               usuact.put("nombreUsuario",usuario.getNombreUsuario());
               usuact.put("teleUsuario",usuario.getTeleUsuario());
               usuact.put("apellidoUsuario",usuario.getApellidoUsuario());
               db.update("Usuario",usuact,"idUsiario=?",id);

           }else
           {
               resultado= "el tipo de usuario no existe, pruebe con uno existente";
           }

        }
        else {
            resultado= "no hay registros de usuario con el código "+id;
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

             */
    {   String resultado = "se ha eliminado el usuario " + usuario.getIdUsuario();
        String[] id = {String.valueOf(usuario.getIdTipoUsuario())};
        Cursor cur = db.query("Usuario",null,"idUsuario = ?",id,null,null,null);
        if(cur.moveToFirst())
        {
            Cursor k = db.query("PedidoRealizado",null,"idUsuario = ?",id,null,null,null);
            Cursor m = db.query("Local",null,"idUsuario = ?",id,null,null,null);
            if (k.moveToFirst())
            {
            int cantPR = db.delete("PedidoRealizado","idUsuario="+ usuario.getIdUsuario(),null);
                resultado= resultado+ " y el/los " +cantPR +" pedidos de este usuario ";
            }
            if (m.moveToFirst())
            {
                int cantL = db.delete("Local","idUsuario ="+usuario.getIdUsuario(),null);
                resultado = resultado + " y el/los "+ cantL  +" locales de los que era propietario  ";
            }
            int cantU = db.delete("Usuario","idUsuario ="+usuario.getIdUsuario(),null);

        }else
        {
            resultado= "el usuario no existe";
        }
        return null;

    }


        //Aquí termina la ayuda de tu vecino el hombre araña

        //Preguntar a Vane si esta parte del código falla o hay dudas
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
                    //Se encontro pedido
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
