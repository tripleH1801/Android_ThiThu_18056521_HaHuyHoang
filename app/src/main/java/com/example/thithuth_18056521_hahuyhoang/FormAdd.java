package com.example.thithuth_18056521_hahuyhoang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class FormAdd extends AppCompatActivity {

    private TextView tvName;
    private TextView tvAge;
    private TextView tvMoney;
    private Button btn;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add);

        tvName = findViewById(R.id.txtName);
        tvAge = findViewById(R.id.txtAge);
        tvMoney = findViewById(R.id.txtMoney);
        btn = findViewById(R.id.btnSaveNew);

        url="https://60ada21980a61f0017331577.mockapi.io/api/customers/customers123";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(FormAdd.this);
                    JSONObject jsonBody = new JSONObject();

                    jsonBody.put("name", tvName.getText());
                    jsonBody.put("age", Integer.parseInt(tvAge.getText().toString()));
                    jsonBody.put("money", Integer.parseInt(tvMoney.getText().toString()));

                    final String requestBody = jsonBody.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.toString());
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return requestBody == null ? null : requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                                return null;
                            }
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                            String responseString = "";
                            if (response != null) {
                                responseString = String.valueOf(response.statusCode);
                                // can get more details such as response.headers
                            }
                            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                        }
                    };

                    requestQueue.add(stringRequest);

                    startActivity(new Intent(FormAdd.this, ListCustomer.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}