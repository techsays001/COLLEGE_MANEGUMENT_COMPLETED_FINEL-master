package com.msg91.sendotp.sample;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter extends RecyclerView.Adapter<Chequeadapter.ProductViewHolder> {
    Intent i;
SharedPreferences sh;

    private Context mCtx;
    private List<Cheque> productList;

    public Chequeadapter(Context mCtx, List<Cheque> productList) {
        sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
        this.mCtx = mCtx;
        this.productList = productList;
       // sh=mCtx.getSharedPreferences("Official1",MODE_PRIVATE);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Cheque cheque = productList.get(position);


        holder.name.setText(cheque.getImage());
        holder.detalis.setText(cheque.getStatus());
        Picasso.get().load(cheque.getUser1()).into(holder.image);
        holder.ph.setText(cheque.getUser2());
        holder.la.setText(cheque.getUser3());
        holder.lo.setText(cheque.getUser4());
       holder.pass.setText(cheque.getUser6());
        holder.emails.setText(cheque.getUser5());

        sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);



//        holder.map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+cheque.getUser3()+","+cheque.getUser4()));
//                mCtx.startActivity(intent);
//
//
//
//
//
//
//
//            }
//        });

//        holder.share.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//
//                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                sharingIntent.setType("text/plain");
//                String shareBody = cheque.getUser1()+"\t\t\t\t"+"phone number :"+"\t\t\t"+cheque.getStatus()+"\t\t\t\t"+"farm name :"+"\t\t\t"+cheque.getUser2()+"\t\t\t\t"+"oner name :"+"\t\t\t"+cheque.getImage();
//                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
//                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//                mCtx.startActivity(sharingIntent);
//
//            }
//
//        });




        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/College_communication_app/Student_registration_confirm.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//If we are getting success from server
                              //  Toast.makeText(mCtx,cheque.getUser1(),Toast.LENGTH_LONG).show();
                                if(response.equals("Registration Successful"))
                                {

                                    new SweetAlertDialog(mCtx, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Success")
                                            .setContentText("Login to Dashboard!")
                                            .setConfirmText("Yes,Login")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    sDialog
                                                            .setTitleText("Logining...!")

                                                            .setConfirmText("OK")
                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
//
                                                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/College_communication_app/delete.php",
                                                                            new Response.Listener<String>() {
                                                                                @Override
                                                                                public void onResponse(String response) {
                                                                                 //   Toast.makeText(mCtx,response,Toast.LENGTH_LONG).show();
                                                                              if(response.equals("success")){

                                                                                  Intent iin=new Intent(mCtx,MainActivityhome.class);
                                                                    mCtx.startActivity(iin);


                                                                              }
                                                                              else{


                                                                                  new SweetAlertDialog(mCtx, SweetAlertDialog.WARNING_TYPE)
                                                                                          .setTitleText("Updated")
                                                                                          .setContentText("Back To Home!")
                                                                                          .setConfirmText("Yes")

                                                                                          .show();



                                                                              }
                                                                                    try {
                                                                                        JSONArray jsonArray = new JSONArray(response);
                                                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                                                            JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");



                                                                                        }

                                                                                    } catch (JSONException e) {
                                                                                        e.printStackTrace();
                                                                                    }


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
//Adding parameters to request
                                                                            params.put("ph", cheque.getStatus());


//returning parameter
                                                                            return params;
                                                                        }

                                                                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                                                                    RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                                                                    requestQueue.add(stringRequest);
                                                                }
                                                            })
                                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                }
                                            })
                                            .show();




//
                                }



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



                      params.put("sname",cheque.getImage());
                        params.put("semail", cheque.getUser5());
                        params.put("phone",cheque.getStatus());
                        params.put("add",cheque.getUser2());
                        params.put("f",cheque.getUser3());
                        params.put("da",cheque.getUser4());
                        params.put("passw",cheque.getUser6());;
                        params.put("img",cheque.getUser1());



                        return params;
                    }

                };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(stringRequest);

            }
        });



    }


    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView name, price, detalis, a, ph,app,call,msg,lo,la, map,share,emails,pass;
        ImageView image;
        Button accept;
        Button buy, del;


        public ProductViewHolder(View itemView) {
            super(itemView);

            sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
            name = itemView.findViewById(R.id.t_discription1);
            ph = itemView.findViewById(R.id.pph2);
            detalis = itemView.findViewById(R.id.pph1);
            image = itemView.findViewById(R.id.t_name1);



           accept= itemView.findViewById(R.id.smstxt);
            lo= itemView.findViewById(R.id.lo);
            la= itemView.findViewById(R.id.la);

           emails= itemView.findViewById(R.id.email12345);
           pass= itemView.findViewById(R.id.pass123);
        }


    }
//
//    public void filterList(ArrayList<Cheque> filteredList) {
//        productList = filteredList;
//        notifyDataSetChanged();
//    }

}
