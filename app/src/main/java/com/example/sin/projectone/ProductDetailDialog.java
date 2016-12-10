package com.example.sin.projectone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by nanth on 12/10/2016.
 */

public class ProductDetailDialog extends DialogFragment {
    private String message = "";
    private String textBtnOk = "OK";
    private String textBtnCancel = "Cancel";
    private String title ="Product Detail";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Bundle b = getArguments();
        if(b!=null){
            message = b.getString(Constant.KEY_BUNDLE_MESSAGE_DIALOG, message);
            title = b.getString(Constant.KEY_BUNDLE_TITLE_DIALOG, title);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message)
                .setPositiveButton(textBtnOk, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(textBtnCancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
