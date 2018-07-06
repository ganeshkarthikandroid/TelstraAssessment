package com.telstra.assessment.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.telstra.assessment.presenter.AlertDialogCallback;


public class AlertUtils {

    public static void ShowAlert(final Activity activity, String alertMsg, final AlertDialogCallback callback) {
        AlertDialog alertDialog = new AlertDialog.Builder(
                activity).create();
        alertDialog.setCanceledOnTouchOutside(false);
        // Setting Dialog Message
        alertDialog.setMessage(alertMsg);

        // Setting OK Button
        alertDialog.setButton("Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                callback.retry();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

}
