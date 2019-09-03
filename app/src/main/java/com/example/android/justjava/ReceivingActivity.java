package com.example.android.justjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceivingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Intent intent = getIntent();

        String stringData = intent.getExtras().getString("stringData");

        TextView textView = findViewById(R.id.result);

        textView.setText(stringData);
    }
}
