package ua.kiev.netmaster.mytaxiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ua.kiev.netmaster.mytaxiapp.domain.Order;


public class ActivityListView extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private String result;

    private Gson gson;

    private Order order;

    private List<Order> orderList;

    private MyOrderAdapter myOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list_view);

        gson = new Gson();

        //ListView listView = (ListView) findViewById(R.id.listViewOrders);
        Button getOrders = (Button)findViewById(R.id.getOrdersBtn);
        getOrders.setOnClickListener(this);
        onClick(null);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_list_view, menu);
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

        try {
            result = new MyDownTask("findInCompleteOrders").execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        TypeToken<List<Order>> token = new TypeToken<List<Order>>() {};

        orderList = gson.fromJson(result, token.getType());

        myOrderAdapter = new MyOrderAdapter(this, orderList);

        final ListView orderListView = (ListView)findViewById(R.id.listViewOrders);
        orderListView.setAdapter(myOrderAdapter);

        orderListView.setOnItemClickListener(this);





    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        order = orderList.get(i);

        Intent intent = new Intent(this,OrderAcceptinActivity.class);

        TypeToken<Order> token2 = new TypeToken<Order>() {};

        String orderGson = gson.toJson(order, token2.getType());

        System.out.println(orderGson);

        intent.putExtra("Order", orderGson);

        startActivity(intent);
    }
}

