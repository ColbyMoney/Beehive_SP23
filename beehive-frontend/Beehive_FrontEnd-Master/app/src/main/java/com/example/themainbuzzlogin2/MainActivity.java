package com.example.themainbuzzlogin2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    DBHelper DB;
    EditText etUsername;
    EditText etPassword;
    //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Get rid of Action bar and title, to my knowledge, this is the only way to do so and you must do it in every activity - JG
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);

        // Initialize etUsername and etPassword
        etUsername = (EditText) findViewById(R.id.etUsername1);
        etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bLogin = (Button) findViewById(R.id.bLogin);

        final TextView registerLink = (TextView) findViewById(R.id.tRegister1);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkUsernamePassword(user,pass);
                    if(checkuserpass == true){
                        // Execute the login task
                        new LoginTask().execute(user, pass);

                        Intent intent = new Intent(getApplicationContext(), UserAreaActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);

            }
        });
    }

    public class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://153.91.238.178:8081/user/login");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");

                con.setDoOutput(true);
                JSONObject loginJson = new JSONObject();
                loginJson.put("usernameOrEmail", params[0]);
                loginJson.put("password", params[1]);

                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(loginJson.toString());
                out.flush();
                out.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                return content.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                System.out.println(result);
                try {
                    JSONObject responseJson = new JSONObject(result);
                    String token = responseJson.getString("accessToken");
                    String username = etUsername.getText().toString();
                    boolean isTokenUpdated = DB.updateToken(username, token);
                    if (isTokenUpdated) {
                        Toast.makeText(MainActivity.this, "Token saved successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to save token", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

}