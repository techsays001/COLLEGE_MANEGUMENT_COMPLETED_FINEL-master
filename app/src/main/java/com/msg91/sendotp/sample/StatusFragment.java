package com.msg91.sendotp.sample;







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
import com.msg91.sendotp.sample.Chequeadapter;
import com.msg91.sendotp.sample.Chequeadapter1;
import com.msg91.sendotp.sample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class StatusFragment extends Fragment {

    View root;
    List<Cheque1> productList1;
    SwipeRefreshLayout s;
    //the recyclerview
    RecyclerView recyclerView1;
    SwipeRefreshLayout swipe;
    SharedPreferences sh;
    Chequeadapter1 adapter1;
    EditText edt;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        root = inflater.inflate(R.layout.fragment_status, container, false);
        loadProducts();


        recyclerView1 = root.findViewById(R.id.recylcerViewcq);
        edt = root.findViewById(R.id.editTextsearchq);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter1(editable.toString());
            }
        });
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipe = root.findViewById(R.id.swiperefreshq);
        sh = getActivity().getSharedPreferences("Official", MODE_PRIVATE);
        productList1 = new ArrayList<>();
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productList1.clear();

                loadProducts();


            }
        });
        return root;
    }

    private void loadProducts() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/College_communication_app/srudent_info.php",
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
                                productList1.add(new Cheque1(
                                        product.getString("name"),
                                        product.getString("phone"),
                                        product.getString("image"),
                                        product.getString("address"),
                                        product.getString("department"),
                                        product.getString("gender"),
                                        product.getString("email"),
                                        product.getString("password")


                                ));
                            }

                            adapter1 = new Chequeadapter1(getActivity(), productList1);
                            // adapter.notifyDataSetChanged();
                            recyclerView1.setAdapter(adapter1);


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

    private void filter1(String text) {
        ArrayList<Cheque1> filteredList1 = new ArrayList<>();

        for (Cheque1 item : productList1) {
            if (item.getUser3().toLowerCase().contains(text.toLowerCase())) {
                filteredList1.add(item);
            }
        }

        adapter1.filterList1(filteredList1);
    }
}