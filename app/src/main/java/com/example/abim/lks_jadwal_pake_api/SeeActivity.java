package com.example.abim.lks_jadwal_pake_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SeeActivity extends AppCompatActivity {
    RecyclerView rv;
    Context ctx;
    Adapter adapter;
    List<Students> students;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);
        getSupportActionBar().setTitle("See Students");

        ctx = this;
        rv = findViewById(R.id.rv);
        students = new ArrayList<>();
        queue = Volley.newRequestQueue(ctx);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, MyRequest.getStudentsURL(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length() ; i++){
                        JSONObject obj = response.getJSONObject(i);
                        students.add(new Students(obj.getInt("id"), obj.getString("name"), obj.getString("address"), obj.getString("date"), obj.getString("hp"), obj.getString("classname"), obj.getString("username")));
                    }

                    LinearLayoutManager manager = new LinearLayoutManager(ctx);
                    manager.setOrientation(RecyclerView.VERTICAL);
                    rv.setLayoutManager(manager);
                    adapter = new Adapter(students, ctx);
                    rv.setAdapter(adapter);
                }
                catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}