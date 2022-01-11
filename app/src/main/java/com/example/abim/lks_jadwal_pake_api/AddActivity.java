package com.example.abim.lks_jadwal_pake_api;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    Context ctx;
    EditText et_name, et_address, et_phone;
    Button btn_pick, btn_sv;
    RadioButton r_male, r_female;
    Spinner spinner;
    List<Integer> ids;
    List<String> names;
    RequestQueue queue;
    TextView date;
    int classid;
    String name;
    String address;
    String gender;
    Date dob;
    String hp;
    String d;
    DatePickerDialog datepick;
    SimpleDateFormat format;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Add Student");

        ctx = this;
        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        et_phone = findViewById(R.id.et_hp);
        btn_pick = findViewById(R.id.btn_pick);
        btn_sv = findViewById(R.id.btn_sv);
        r_male = findViewById(R.id.r_male);
        r_female = findViewById(R.id.r_female);
        spinner = findViewById(R.id.spin);
        date = findViewById(R.id.tv_tgl);
        rg = findViewById(R.id.radioGroup);

        format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        queue = Volley.newRequestQueue(ctx);
        ids = new ArrayList<>();
        ids.clear();
        names = new ArrayList<>();
        names.clear();
//        dob = Date.valueOf(date.getText().toString());

        loadclass();

        btn_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdate();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classid = ids.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().equalsIgnoreCase("") || et_address.getText().toString().equalsIgnoreCase("") || et_phone.getText().toString().equalsIgnoreCase("") || !r_female.isChecked() && !r_male.isChecked() || date.getText().toString().equalsIgnoreCase("") || classid == 0){
                    Toast.makeText(ctx, "All Field Must be Filled", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                int gid = rg.getCheckedRadioButtonId();
                                switch (gid){
                                    case R.id.r_male:
                                        gender = "Male";
                                        break;

                                    case R.id.r_female:
                                        gender = "Female";
                                        break;

                                    default:
                                        gender = "-";
                                        break;
                                }

                                name = et_name.getText().toString();
                                address = et_address.getText().toString();
                                hp = et_phone.getText().toString();

                                JSONObject obj = new JSONObject();
                                obj.put("name", name);
                                obj.put("address", address);
                                obj.put("gender", gender);
                                obj.put("dob", dt());
                                obj.put("hp", hp);
                                obj.put("classid", classid);

                                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, MyRequest.getPostURL(), obj, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        if (response != null) {
                                            AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                                            dialog.setTitle("Info");
                                            dialog.setMessage("Success");
                                            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            dialog.show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                                        dialog.setTitle("Error");
                                        dialog.setMessage(""+error);
                                        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialog.dismiss();
                                            }
                                        });
                                        dialog.show();
                                    }
                                });

                                queue.add(request);
                            } catch (JSONException e) {
                                Toast.makeText(ctx, ""+e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.setMessage("Are you sure to insert ?");
                    dialog.setTitle("Confirmation");
                    dialog.show();

                }
            }
        });
    }

    void loadclass(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, MyRequest.getClassURL(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject obj = response.getJSONObject(i);
                        ids.add(obj.getInt("id"));
                        names.add(obj.getString("name"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, names);
                spinner.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }

    void getdate(){
        Calendar calendar = Calendar.getInstance();

        datepick = new DatePickerDialog(ctx, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar cal = Calendar.getInstance();
                cal.set(i, i1, i2);
                date.setText(format.format(cal.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datepick.show();
//        setDate(date.getText().toString());
//        dob = Date.valueOf(date.getText().toString());

    }

    void setDate(String d){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            dob = (Date) df.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    String dt(){
        String s = date.getText().toString();
        String[] st = s.split("-");
        return st[1] + "-" + st[0] + "-" + st[2];
    }
}