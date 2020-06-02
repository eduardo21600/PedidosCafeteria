package sv.edu.ues.fia.eisi.pedidoscafeteria;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ControladorBD extends SQLiteOpenHelper {

    private static final String nombreBD = "cafeteriaUES.s3db";


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
    public String insertar(Usuario usuario){

        String resultado="Usuario creado, bienvenido "+usuario;
        long  comprobador = 0;
        ContentValues usu = new ContentValues();



    return resultado;
    }





    //Aquí termina la ayuda de tu vecino el hombre araña

    //Preguntar a Vane si esta parte del código falla o hay dudas

    //Aquí termina la parte de Vane


    //Integridad
    //fin integridad
}
