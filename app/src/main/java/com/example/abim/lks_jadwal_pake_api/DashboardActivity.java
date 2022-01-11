package com.example.abim.lks_jadwal_pake_api;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    Session s;
    Context ctx;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ctx = this;
        s = new Session(ctx);
        textView = findViewById(R.id.tv_name);
        textView.setText(s.getName());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.card_see){
            Intent intent = new Intent(DashboardActivity.this, SeeActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.card_add){
            Intent intent = new Intent(DashboardActivity.this, AddActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.card_info){
            Intent intent = new Intent(DashboardActivity.this, InfoActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.card_logout){
            AlertDialog dialog = new AlertDialog.Builder(ctx).create();
            dialog.setTitle("Confirmation");
            dialog.setMessage("Are you sure to logout ?");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(DashboardActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}