package com.PhoneInfo.lab1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    String device_unique_id;
    PermissionDialog dialog;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        dialog = new PermissionDialog();
        button.setOnClickListener(this);
        if (savedInstanceState != null) {
            device_unique_id = savedInstanceState.getString("id");
            textView.setText(BuildConfig.VERSION_NAME + "\n" + device_unique_id);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("id", device_unique_id);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button)
            deviceId();
    }

    private void deviceId() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {
                dialog.show(getSupportFragmentManager(), "PermissionDialogTag");
            } else {
                dialog.show(getSupportFragmentManager(), "PermissionDialogTag");
            }
        } else {
            //TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            device_unique_id = Settings.Secure.getString(this.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            String version = BuildConfig.VERSION_NAME;
            textView.setText(version + "\n" + device_unique_id);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_PHONE_STATE) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"Access granted",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No access granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}