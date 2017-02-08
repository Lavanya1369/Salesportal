package com.example.enchanter.salesportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    Button button;
    EditText name,mail,phone,pass;
    String s1,s2,s3,s4;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=(EditText)findViewById(R.id.name);
        mail=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        pass=(EditText)findViewById(R.id.password);

        button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s1=name.getText().toString();
                s2=mail.getText().toString();
                s3=phone.getText().toString();
                s4=pass.getText().toString();

                if(s1.equals("")&& s2.equals("")&&s3.equals("")&& s4.equals("")) {
                    Toast.makeText(Registration.this, "please enter all fields", Toast.LENGTH_LONG).show();
                }
                else {
                    insertme(s1, s2, s3, s4);
                    Intent i = new Intent(Registration.this,MainActivity.class);

                    startActivity(i);

                }
            }
        });

    }


    public void insertme(final String s1,final String s2,final String s3,final String s4)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://lavanya1369.tk/registration.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<String, String>();
                params.put("na",s1);
                params.put("xx",s2);
                params.put("dd",s3);
                params.put("pas",s4);

                return params;

            }
        };
        Secondclass.getInstance().addToRequestQueue(stringRequest);

    }



}
