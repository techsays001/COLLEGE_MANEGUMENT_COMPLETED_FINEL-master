package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Addrequset extends AppCompatActivity {
TextView homeaddress,la,lo,mapp,nm,phh,calll;
Intent abc;
String a,b,c,d,e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrequset);
   homeaddress=findViewById(R.id.homeaddress);
        la=findViewById(R.id.la7);
      lo=findViewById(R.id.lo7);
        nm=findViewById(R.id.name1230);
        phh=findViewById(R.id.ph1230);
       mapp=findViewById(R.id.map7);
    calll=findViewById(R.id.ph7);
abc=getIntent();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/College_communication_app/lacation_flrch.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
                    //     Toast.makeText(Addrequset.this,abc.getStringExtra("ph")+response,Toast.LENGTH_LONG).show();
if(!response.equals("ok")){

    homeaddress.setText("No Data Found");



}
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");
                                a=json_obj.getString("address");
                                b=json_obj.getString("la");
                                c=json_obj.getString("lo");
                                d=json_obj.getString("name");
                                e=json_obj.getString("parent_phone");


                               homeaddress.setText(a);
                               la.setText(b);
                                lo.setText(c);
                               nm.setText(d);
                               phh.setText(e);
                            }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //   Toast.makeText(Signin.this, "success", Toast.LENGTH_LONG).show();

                    }



                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("ph",abc.getStringExtra("ph"));

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Addrequset.this);
        requestQueue.add(stringRequest);


       mapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+la.getText().toString()+","+lo.getText().toString()));
             startActivity(intent);







            }
        });
     calll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:"+phh.getText().toString()));
              startActivity(intent1);




            }
        });

    }

}
