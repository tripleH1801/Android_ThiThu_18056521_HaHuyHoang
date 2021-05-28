package com.example.thithuth_18056521_hahuyhoang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class ListCustomer extends AppCompatActivity {

    private Button btnNew;
    private Recycle_Adapter adt;
    private RecyclerView rec;
    private List<Customer> list;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_customer);

        url="https://60ada21980a61f0017331577.mockapi.io/api/customers/customers123";
        rec = findViewById(R.id.recListCustomer);
        btnNew = findViewById(R.id.btnNew);

        list = new ArrayList<>();
        GetArrayJson(url);

        adt = new Recycle_Adapter(this, list);
        System.out.println("===================listsize: "+list.size());
        rec.setAdapter(adt);
        rec.setLayoutManager(new LinearLayoutManager(this));
    }

    private void GetArrayJson(String url){
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                rec.setVisibility(View.VISIBLE);
                                for(int i=0; i<response.length(); i++){
                                    try {
                                        JSONObject object = (JSONObject) response.get(i);

                                        String name = object.getString("name");
                                        int money = object.getInt("money");
                                        int age = object.getInt("age");
                                        Customer customer = new Customer(name, money, age);
                                        list.add(customer);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                adt = new Recycle_Adapter(ListCustomer.this, list);
                                System.out.println("===================listsize: "+list.size());
                                rec.setLayoutManager(new LinearLayoutManager(ListCustomer.this));
                                rec.setAdapter(adt);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListCustomer.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}