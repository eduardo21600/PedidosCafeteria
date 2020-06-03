package sv.edu.ues.fia.eisi.pedidoscafeteria;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ControladorBD extends SQLiteOpenHelper {

    private static final String nombreBD = "cafeteriaUES.s3db";
    private SQLiteDatabase db;
    //campos de las bases
    private static final String[] camposTabla = {"","","","","",""};
    private static final String[] camposUsuario = {"","","","","",""};
    private static final String[] camposDetallePedido = {"","","","","",""};
    private static final String[] camposMenu = {"","","","","",""};
    private static final String[] camposProducto = {"","","","","",""};


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
        Cursor cur = db.query("Usuario",null,"idTipoUsuario = ?",tipoU,null,null,null);

        if(cur.moveToFirst())
        {
            resultado="El tipo de usuario no existe";
        }
        else{
            comprobador = db.insert("usuario", null, usu);

            if (comprobador == -1 || comprobador == 0)
            {
                resultado="Oh, oh, parece que ese id de usuario ya existe, prueba con otro";

            }

        }

            return resultado;
    }
    public Usuario ConsultaUsuario(String idUsuario)
    {
        String[] id = {idUsuario};
        Cursor cur = db.rawQuery("select * from Usuario where idUsuario ="+idUsuario,null);
        if(cur.moveToFirst())
        {
            Usuario usu1 = new Usuario();
            usu1.setIdTipoUsuario(Integer.parseInt(cur.getString(0)));


        }





        return null;
    }







    //Aquí termina la ayuda de tu vecino el hombre araña

    //Preguntar a Vane si esta parte del código falla o hay dudas

    //Aquí termina la parte de Vane


    //Integridad
    //fin integridad
}
