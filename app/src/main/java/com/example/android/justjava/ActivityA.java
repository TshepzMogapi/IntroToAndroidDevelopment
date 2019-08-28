package com.example.android.justjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class ActivityA  extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }

    public void prepareOrder(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }
}
