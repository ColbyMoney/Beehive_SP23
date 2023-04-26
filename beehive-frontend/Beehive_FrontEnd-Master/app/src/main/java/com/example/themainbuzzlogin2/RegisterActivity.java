package com.example.themainbuzzlogin2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Get rid of Action bar and title, to my knowledge, this is the only way to do so and you must do it in every activity - JG
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DB = new DBHelper(this);


        final EditText etDate = (EditText) findViewById(R.id.etDate);

        etDate.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog picker;
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);


                picker = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText( (month + 1) + "/" +dayOfMonth  + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        final EditText etName = (EditText) findViewById(R.id.etName);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString();
                String name = etName.getText().toString();
                String pass = etPassword.getText().toString();
                String DoB = etDate.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(name) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(DoB)) {
                    Toast.makeText(RegisterActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuser = DB.checkUsername(user);
                    if (checkuser == false) {
                        Boolean insert = DB.insertData(user, name, pass, DoB);
                        if (insert) {
                            // Start of new code
                            new RegisterTask().execute(user, pass, name, DoB);
                            // End of new code

                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } // This is the missing closing brace
        });

//        bRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String name = etName.getText().toString();
//                final String username = etUsername.getText().toString();
//                final String password = etPassword.getText().toString();
//                final int age = Integer.parseInt(etAge.getText().toString());
//
//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//
//                            boolean success = jsonResponse.getBoolean("success");
//
//                            if (success){
//                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                                RegisterActivity.this.startActivity(intent);
//                            }
//                            else{
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//                                builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
//                            }
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                };
//
//                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
//                queue.add(registerRequest);
//            }
//        });
            }

            private void registerUser(String firstName, String lastName, String username, String email, String password, Date birthday) {
                String url = "http://localhost:8081/user/register";
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);

                JSONObject registerData = new JSONObject();
                try {
                    registerData.put("firstName", firstName);
                    registerData.put("lastName", lastName);
                    registerData.put("username", username);
                    registerData.put("email", email);
                    registerData.put("password", password);
                    registerData.put("role", "USER");
                    registerData.put("birthday", birthday.getTime()); // Convert the Date object to milliseconds since epoch
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, registerData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response from the server, e.g., navigate to another activity, show a success message, etc.
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error, e.g., show an error message
                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        }