package com.example.ermile.android_sample_01;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class DeviceInfo extends AppCompatActivity {
    // show device info
    public static final int REQUEST_CODE_PHONE_STATE_READ = 100;
    private int checkedPermission = PackageManager.PERMISSION_DENIED;
    //end device info

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_info);








        // show device info
        checkedPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (Build.VERSION.SDK_INT >= 23 && checkedPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else
            checkedPermission = PackageManager.PERMISSION_GRANTED;
        TOST();
        // end show device info




    }

    /////////////////////////////////////////////////////////////
    private void requestPermission() {
        Toast.makeText(DeviceInfo.this, "Requesting permission", Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    REQUEST_CODE_PHONE_STATE_READ);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE_PHONE_STATE_READ:
                if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED ) {
                    checkedPermission = PackageManager.PERMISSION_GRANTED;
                }
                break;

        }
    }
    public void TOST(){
        SwipeRefreshLayout swipe = findViewById(R.id.Refresh_deviceinfo);
        swipe.setRefreshing(true);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                startActivity(getIntent());
            }
        });

        TextView textView = findViewById(R.id.txtview_deviceinfo);
        if (textView != null){
            swipe.setRefreshing(false);
        }

        TelephonyManager manager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        textView.setText(
                "IMEI : " + manager.getDeviceId() + "\n"+"\n"+"\n"+
                "Board : " + Build.BOARD + "\n"+"\n"+
                "Brand : " + Build.BRAND + "\n"+"\n"+
                "DEVICE : " + Build.DEVICE + "\n"+"\n"+
                "Display : " + Build.DISPLAY + "\n"+"\n"+
                "FINGERPRINT : " + Build.FINGERPRINT + "\n"+"\n"+
                "HARDWARE : " + Build.HARDWARE + "\n"+"\n"+
                "ID : " + Build.ID + "\n"+"\n"+
                "Manufacturer : " + Build.MANUFACTURER + "\n"+"\n"+
                "MODEL : " + Build.MODEL + "\n"+"\n"+
                "SERIAL : " + Build.SERIAL + "\n"+"\n"+
                "VERSION : " + Build.VERSION.SDK_INT + "\n"+"\n"+
                "Line 1 : " + manager.getLine1Number() + "\n"+"\n"+
                "IMSI : " + manager.getSubscriberId()
        );
    }
}
