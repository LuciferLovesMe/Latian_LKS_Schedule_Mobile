package com.example.abim.lks_jadwal_pake_api;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity  {
    EditText et_username, et_password;
    Button btn;
    Context ctx;
    Session s;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        ctx =this;
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_pass);
        btn = findViewById(R.id.btn_login);
        queue = Volley.newRequestQueue(ctx);

        s = new Session(ctx);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_username.getText().toString().equalsIgnoreCase("") || et_password.getText().toString().equalsIgnoreCase("")){
                    AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                    dialog.setTitle("Error");
                    dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.setMessage("All Fields Must be Filled");
                    dialog.show();
                }
                else{
//                    Toast.makeText(ctx, "Login Login", Toast.LENGTH_SHORT).show();
                    doLogin();
//                    Intent intent = new Intent(HomeActivity.this, DashboardActivity.class);
//                    startActivity(intent);
//                    finish();
                }

//                login();
            }
        });
    }

    private void doLogin(){
        try {
            String username = et_username.getText().toString();
            String pass = et_password.getText().toString();

            try {
                StringRequest request = new StringRequest(Request.Method.POST, MyRequest.getLoginURL(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        if (response == null) {
                            try {
                                Log.d("Message", "MEssage nih");
                                JSONObject obj = new JSONObject(response);
                                s.setUser(obj.getInt("id"), obj.getString("name"));

                                Intent intent = new Intent(HomeActivity.this, DashboardActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            catch (Exception exception){
                                AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                                dialog.setTitle("Error");
                                dialog.setMessage("Can't Find User");
                                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                            }
//                        }
//                        else {
//                            AlertDialog dialog = new AlertDialog.Builder(ctx).create();
//                            dialog.setTitle("Error");
//                            dialog.setMessage("Can't Find User");
//                            dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                            dialog.show();
//                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", username);
                        params.put("password", pass);

                        return params;
                    }
                };

                queue.add(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}