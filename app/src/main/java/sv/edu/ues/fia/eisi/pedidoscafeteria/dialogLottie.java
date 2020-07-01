package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class dialogLottie {
    Activity activity;
    AlertDialog alertDialog;

    dialogLottie (Activity mActivity){
        activity = mActivity;
    }
    void starLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_dialog,null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();
    }

    void dismissDialog(){
        alertDialog.dismiss();
    }
}
