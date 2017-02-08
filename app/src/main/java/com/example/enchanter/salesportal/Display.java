package com.example.enchanter.salesportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Display extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4;
    Button b2,b3;
    int count,count1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String ing=getIntent().getStringExtra("ids");
        String method=getIntent().getStringExtra("amount");
        String topic_name=getIntent().getStringExtra("discount");
        textView1=(TextView)findViewById(R.id.t1);
        textView2=(TextView)findViewById(R.id.t2);
        textView3=(TextView)findViewById(R.id.t3);
        textView4=(TextView)findViewById(R.id.t4);
        b2=(Button)findViewById(R.id.button2);

        textView1.setText(ing);
        textView2.setText(method);
        textView3.setText(topic_name);
        int o= Integer.parseInt(method);
        int n=Integer.parseInt(topic_name);
        if(o!=0&&n!=0){
            count=(n*o)/100;
            count1=o-count;

        }
        textView4.setText(String.valueOf(count1));
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t1=textView1.getText().toString();
                String t2=textView2.getText().toString();
                String t3=textView3.getText().toString();
                String t4=textView4.getText().toString();
                Intent intent1=new Intent(Display.this,Adminlist.class);
                startActivity(intent1);
                getallme(t1,t2,t3,t4);
            }
        });


    }


    public void getallme(final String t1, final String t2,final String t3, final String t4)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://lavanya1369.tk/fulilist.php", new Response.Listener<String>() {

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

                params.put("xx",t1);
                params.put("dd",t2);
                params.put("pas",t3);
                params.put("tot",t4);

                return params;

            }
        };
        Secondclass.getInstance().addToRequestQueue(stringRequest);

    }




}
