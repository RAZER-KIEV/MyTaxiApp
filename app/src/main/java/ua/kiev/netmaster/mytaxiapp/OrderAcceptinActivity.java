package ua.kiev.netmaster.mytaxiapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import ua.kiev.netmaster.mytaxiapp.domain.Order;

public class OrderAcceptinActivity extends Activity implements View.OnClickListener {

    private String orderString, result, acceptOrder = "acceptOrderAndroid";

    private Gson gson;

    private Order order;

    private MyDownTask myDownTask;

    private TextView phoneTV;


    //private MyOrderAdapter myOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_acceptin);

        gson = new Gson();

        TextView orderInfo = (TextView) findViewById(R.id.orderInfoTV);
        TextView fromTV = (TextView) findViewById(R.id.fromTV);
        TextView toTV = (TextView) findViewById(R.id.toTV);
        TextView orderDate = (TextView) findViewById(R.id.orderDate);
        TextView orderSum = (TextView) findViewById(R.id.orderSum);
        TextView clientInfoTV = (TextView) findViewById(R.id.clientInfoTV);
        TextView fnTV = (TextView) findViewById(R.id.fnTV);
        phoneTV = (TextView) findViewById(R.id.phoneTV);
                 phoneTV.setOnClickListener(this);
        TextView totalSumClient = (TextView) findViewById(R.id.totalSumClient);

        Button acceptOrderBT = (Button)findViewById(R.id.acceptOrderBT);
                acceptOrderBT.setOnClickListener(this);
        Button backButtonBT = (Button)findViewById(R.id.backButtonBT);
                backButtonBT.setOnClickListener(this);

        Intent intent = getIntent();
        orderString = intent.getStringExtra("Order");
        System.out.println("orderString: "+orderString);

        order = gson.fromJson(orderString, Order.class);
        //System.out.println(order);

        orderInfo.append(String.valueOf(order.getId()));

        fromTV.append(order.getDepartureAddress());

        toTV.append(order.getDestinationAddress());

        orderDate.append(String.valueOf(order.getOrderDay()));

        orderSum.append(String.valueOf(order.getOrderSum()));

        clientInfoTV.append(String.valueOf(order.getClient().getId()));

        fnTV.append(order.getClient().getName()+" "+order.getClient().getSecondName());

        phoneTV.append(String.valueOf(order.getClient().getPhone()));

        totalSumClient.append(String.valueOf(order.getClient().getSumm()));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_acceptin, menu);
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




        switch (view.getId()){
            case R.id.acceptOrderBT:
                try {
                    result = new MyDownTask(acceptOrder, order.getId()).execute().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                if(result.equals("OrderACCEPTED")){
                    Toast.makeText(this,"Order ACCEPTED, now - move!",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Some error! It is time to panic!", Toast.LENGTH_LONG).show();
                }
                System.out.println("ACCEPT PRESSED!");
                break;
            case R.id.backButtonBT:
                Intent intent = new Intent(this, ActivityListView.class);
                startActivity(intent);

                System.out.println("BACK Pressed!!");
                break;
            case R.id.phoneTV:
                Intent intent3 = new Intent(Intent.ACTION_DIAL);
                Uri uri  = Uri.parse("tel:+3"+phoneTV.getText());
                intent3.setData(uri);
                startActivity(intent3);

        }

    }
}
