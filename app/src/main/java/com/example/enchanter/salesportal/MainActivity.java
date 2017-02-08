package com.example.enchanter.salesportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText phone,pass;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView=(TextView)findViewById(R.id.t2);
        Intent intent = getIntent();
        String s1= getIntent().getStringExtra("getData");
        textView.setText(s1);
        phone=(EditText)findViewById(R.id.phone);
        pass=(EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.but);
        b2=(Button)findViewById(R.id.bu2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,Registration.class);
                startActivity(intent1);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t1=phone.getText().toString();
                String t2=pass.getText().toString();
                if(t1.equals("")&& t2.equals("")) {
                    Toast.makeText(MainActivity.this, "please enter all fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    getallme(t1,t2);
                }
            }
        });


    }
    public void getallme(final String t1, final String t2)
    {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, MyUrl_controller.loginform ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObj = new JSONObject(response);
                    boolean success = responseObj.getBoolean("success");
                    //Toast.makeText(MainActivity.this,"jikx",Toast.LENGTH_LONG).show();
                    if (success)
                    {
                        String name = responseObj.getString("name");
                        String number = responseObj.getString("phoneno");
                        String email = responseObj.getString("email");

                        Intent intent=new Intent(MainActivity.this,Adminlist.class);
                        intent.putExtra("name",name);
                        intent.putExtra("num",number);
                        intent.putExtra("email",email);

                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(MainActivity.this,"something went wrong please try again!!!",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("na1",t1);
                params.put("na2",t2);
                return params;
            }
        };
        Secondclass.getInstance().addToRequestQueue(stringRequest);
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
