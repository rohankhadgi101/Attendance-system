package com.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private RequestQueue requestQueue;
    //  private static final String API_URL = "http://demo1909133.mockable.io/login";
    private static final String API_URL = "http://localhost/attendanceapp/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.passw);
        loginButton = findViewById(R.id.loginbtn);

        getSupportActionBar().hide();

        requestQueue = Volley.newRequestQueue(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                loginUser(username, password);
            }
        });
    }

    private void loginUser(String username, String password) {
/*        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", username);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

/*

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API_URL, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");

                            if (success) {
                                // Login successful, navigate to the Dashboard activity
                                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                                startActivity(intent);
                                finish(); // Close the login activity
                            } else {
                                // Login failed, display an error message
                                Toast.makeText(MainActivity.this, "username or password is invalid!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                           // Log.d("eta pugyo",e.getLocalizedMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                   //     Log.d("statusCode", String.valueOf(error.networkResponse.statusCode));
                     //   Log.d("statusCode", error.networkResponse.data.toString());
                        Toast.makeText(MainActivity.this, "Error occurred: " + error.networkResponse, Toast.LENGTH_SHORT).show();
                    }
                });*/

        //     requestQueue.add(jsonObjectRequest);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (success){
                            Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                                startActivity(intent);
                                finish();
                        }
                        else {
                                 //Login failed, display an error message
                                Toast.makeText(MainActivity.this, "Username or password is invalid!", Toast.LENGTH_SHORT).show();
                        }
                        // Handle the response from the server
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//
//                            if (success) {
//                                // Login successful, navigate to the Dashboard activity
//                                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(MainActivity.this, Dashboard.class);
//                                startActivity(intent);
//                                finish(); // Close the login activity
//                            } else {
//                                // Login failed, display an error message
//                                Toast.makeText(MainActivity.this, "Username or password is invalid!", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error response
                        Toast.makeText(MainActivity.this, "Error occurred: " + error.networkResponse, Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", username);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
