package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mBtnSend;
    private TextView tvtext;
    private LocalBroadcastManager localBroadcastManager;
    private AirplaneModeReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvtext=findViewById(R.id.tvreeta);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        mBtnSend=findViewById(R.id.btnSend);
        registerLocalReceiver();
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent("com.broadcastreceiver.broadcast");
               intent.putExtra("key","Hello Masai School");
               localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    private void registerLocalReceiver(){
        localReceiver=new AirplaneModeReceiver();
        IntentFilter intentFilter=new IntentFilter("com.broadcastreceiver.broadcast");
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
    private class AirplaneModeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                String data=intent.getStringExtra("key");
                tvtext.setText(data);
            }

        }
    }

}