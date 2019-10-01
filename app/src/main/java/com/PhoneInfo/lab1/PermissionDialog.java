package com.PhoneInfo.lab1;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

public class PermissionDialog extends DialogFragment {
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    private Activity context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Permission necessary");
        builder.setMessage("This app requires access to Phone State");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ActivityCompat.requestPermissions(context , new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
            }
        });
        return builder.create();
    }
}