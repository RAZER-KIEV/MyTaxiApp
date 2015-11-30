package ua.kiev.netmaster.mytaxiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class MainActivity extends Activity implements View.OnClickListener {



    private String result;
    static EditText login, password;
    static Button logInBt;
    static TextView info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Locale.setDefault(Locale.ENGLISH);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        info = (TextView) findViewById(R.id.info);

        login = (EditText) findViewById(R.id.loginET);
        password = (EditText) findViewById(R.id.passwordEt);

        logInBt = (Button) findViewById(R.id.LogInBTN);
        logInBt.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Please Wait...", Toast.LENGTH_SHORT).show();
        Log.d("LOGER", "ONCLICK");
        System.out.println("Login is: " + login.getText() + ", Password is: " + password.getText());
        try {
            result = new MyDownTask("authAndroid", String.valueOf(login.getText()), String.valueOf(password.getText())).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("onClick Executed! Must bee seen! RESULT IS: "+result);

        //int resInt = Integer.parseInt(result);

        result=result.trim();

        if (result.equals("Auth_Error")) {
            info.setText(result);
            info.setVisibility(View.VISIBLE);

        } else if (result.equals("Auth_Success")) {
            System.out.println("trying start ActivityListView");
            Intent intentSuccess = new Intent(this, ActivityListView.class);
            startActivity(intentSuccess);
        }
    }
}
