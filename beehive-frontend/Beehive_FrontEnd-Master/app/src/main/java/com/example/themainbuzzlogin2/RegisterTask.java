package com.example.themainbuzzlogin2;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class RegisterTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        String user = params[0];
        String pass = params[1];
        String name = params[2];
        String DoB = params[3];

        // Convert the date string to the "yyyy-MM-dd" format
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDoB = "";
        try {
            formattedDoB = outputFormat.format(inputFormat.parse(DoB));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        try {
            URL url = new URL("http://153.91.238.178:8081/user/register");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            // Correctly format the JSON string
            String postData = "{\"firstName\":\"" + name + "\",\"lastName\":\"noLastName\",\"username\":\"" + user + "\",\"email\":\"noEmail\",\"password\":\"" + pass + "\",\"role\":\"USER\",\"birthday\":\"" + formattedDoB + "\"}";

            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(postData);
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
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
        if (result != null) {
            System.out.println(result);
        } else {
            // Handle error
        }
    }
}

