package com.msg91.sendotp.sample.ui.gallery;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.msg91.sendotp.sample.Cheque;
import com.msg91.sendotp.sample.Cheque1;
import com.msg91.sendotp.sample.Cheque4;
import com.msg91.sendotp.sample.Chequeadapter;
import com.msg91.sendotp.sample.Chequeadapter1;
import com.msg91.sendotp.sample.Chequeadapter2;
import com.msg91.sendotp.sample.Chequeadapter4;
import com.msg91.sendotp.sample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class NurseFragment extends Fragment {

    View root;
    List<Cheque4> productList2;
    SwipeRefreshLayout s;
    //the recyclerview
    RecyclerView recyclerView2;
    SwipeRefreshLayout swipe;
    SharedPreferences sh;
    Chequeadapter4 adapter2;
    EditText edt;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        root = inflater.inflate(R.layout.fragment_nurse, container, false);
        loadProducts();


        recyclerView2 = root.findViewById(R.id.recylcerViewct);
        edt = root.findViewById(R.id.editTextsearcht);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter2(editable.toString());
            }
        });
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipe = root.findViewById(R.id.swiperefresht);
        sh = getActivity().getSharedPreferences("Official", MODE_PRIVATE);
        productList2 = new ArrayList<>();
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productList2.clear();

                loadProducts();


            }
        });
        return root;
    }

    private void loadProducts() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/College_communication_app/timetabel_view.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_LONG).show();

                        swipe.setRefreshing(false);
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = array.length() - 1; i >= 0; i--) {


                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                //adding the product to product list
                                productList2.add(new Cheque4(
                                        product.getString("department"),
                                        product.getString("year"),
                                        product.getString("Subject"),
                                        product.getString("date"),
                                        product.getString("stime"),
                                        product.getString("etime")



                                ));
                            }

                            adapter2 = new Chequeadapter4(getActivity(), productList2);
                            // adapter.notifyDataSetChanged();
                            recyclerView2.setAdapter(adapter2);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }


                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //  params.put("d",dis.getSelectedItem().toString().toLowerCase());
                // params.put("ph",shh.getString("phone",null));


                return params;
            }

        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    private void filter2(String text) {
        ArrayList<Cheque4> filteredList2 = new ArrayList<>();

        for (Cheque4 item : productList2) {
            if (item.getImage().toLowerCase().contains(text.toLowerCase())) {
                filteredList2.add(item);
            }
        }

        adapter2.filterList2(filteredList2);
    }

}