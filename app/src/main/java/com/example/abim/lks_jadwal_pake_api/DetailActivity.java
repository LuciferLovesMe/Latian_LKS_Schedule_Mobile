package com.example.abim.lks_jadwal_pake_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    Context ctx;
    Intent intent;
    TextView name, address, date, hp, classname, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("Detail");

        intent = getIntent();
        ctx = this;
        name = findViewById(R.id.named);
        address = findViewById(R.id.addressd);
        date = findViewById(R.id.dobd);
        hp = findViewById(R.id.hpd);
        classname = findViewById(R.id.classd);
        user = findViewById(R.id.userd);

        name.setText(intent.getStringExtra("name"));
        address.setText(intent.getStringExtra("address"));
        date.setText(intent.getStringExtra("date"));
        hp.setText(intent.getStringExtra("hp"));
        classname.setText(intent.getStringExtra("classname"));
        user.setText(intent.getStringExtra("username"));
    }
}