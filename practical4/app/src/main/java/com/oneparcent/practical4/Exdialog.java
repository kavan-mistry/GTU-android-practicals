package com.oneparcent.practical4;

import android.app.*;
import android.content.*;
import android.os.*;
import androidx.appcompat.app.*;
import androidx.appcompat.app.AlertDialog;

public class Exdialog extends AppCompatDialogFragment{

    public Dialog onCreateDialog(Bundle bundle){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information").setMessage("En. No. : 191260116023").setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }

}
