package ua.kiev.netmaster.mytaxiapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ПК on 06.08.2015.
 */
public class MyDownTask extends AsyncTask<Void, Void, String>{

    private Gson gson;

    private String login, password, urlStr = "http://185.11.82.233:8087/";

    private String result;

    private Long orderId;

    public MyDownTask(String urlTeil, String login, String password) {
       urlStr += urlTeil;
       this.login = login;
       this.password = password;

        gson = new Gson();

    }

    public MyDownTask(String urlStr) {
        this.urlStr+= urlStr;
        gson = new Gson();
    }


    public MyDownTask(String urlStr, Long orderId) {
        this.urlStr+= urlStr;
        this.orderId = orderId;
        gson = new Gson();
    }


    protected void onPreExecute() {}

    @Override
    protected String doInBackground(Void... params) {
        Log.d("LOGTAG", "doInBackground");
        return connect();
    }

    protected void onPostExecute(String result) {
        this.result = result;
    }

    public String connect() {
        StringBuffer responses= new StringBuffer();
        URL url = null;
        HttpURLConnection con = null;
        String inputLine;
        try {
            System.out.println(urlStr);
            url = new URL(urlStr);

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);

           if(login!=null&&password!=null||orderId!=null){
               Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("login",login )
                    .appendQueryParameter("password", password)
                    .appendQueryParameter("orderId", String.valueOf(orderId));

            String query = builder.build().getEncodedQuery();

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
           }



            con.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                responses.append(inputLine);
                Log.d("Loger", inputLine);
                System.out.println(inputLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return responses.toString();
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public String getResalt() {
        return result;
    }

    public void setResalt(String resalt) {
        this.result = resalt;
    }
}

