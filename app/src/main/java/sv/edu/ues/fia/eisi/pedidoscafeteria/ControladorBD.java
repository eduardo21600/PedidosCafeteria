package sv.edu.ues.fia.eisi.pedidoscafeteria;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
    private static final String[] camposTabla = {"","","","","",""};
    private static final String[] camposDetallePedido = {"","","","","",""};
    private static final String[] camposMenu = {"","","","","",""};
    private static final String[] camposProducto = {"","","","","",""};
    private static final String[] camposUbicacion = {"","","","","",""};

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
               db.update("Usuario",usuact,"idUsuario=?",id);

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
            ubic.put("", ubicacion.getIdUbicacion());
            ubic.put("", ubicacion.getIdFacultad());
            ubic.put("", ubicacion.getIdPedido());
            ubic.put("", ubicacion.getDirecUbicacion());
            ubic.put("", ubicacion.getNomUbicacion());
            ubic.put("", ubicacion.getPuntoRefUbicacion());
            contador=db.insert("Ubicacion", null, ubic);
        }
        else {
            resultado="Facultad y Pedido no existen";
        }
        return resultado;
    }

    public Ubicacion consultarUbicacion(String idubicacion){
        String [] id = {idubicacion};
        Cursor cursor = db.query("Ubicacion", camposUbicacion,"idUbicacion= ?", id,null,null,null);
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
                cv.put("",ubicacion.getIdUbicacion());
                cv.put("",ubicacion.getIdPedido());
                cv.put("",ubicacion.getIdFacultad());
                cv.put("",ubicacion.getNomUbicacion());
                cv.put("",ubicacion.getDirecUbicacion());
                cv.put("",ubicacion.getPuntoRefUbicacion());
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
            //verificar que exista facultad y pedido para eliminar en cascada
            if(verificarIntegridad(ubicacion,2)){
                contadorF+=db.delete("Facultad","idFacultad= '"+ubicacion.getIdFacultad()+"'",null);
                resultado+= resultado + " Se elimino el/los " + contadorF + " registros de Facultad";
            }
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
            default:
                return false;
        }
        //fin integridad
    }

}
