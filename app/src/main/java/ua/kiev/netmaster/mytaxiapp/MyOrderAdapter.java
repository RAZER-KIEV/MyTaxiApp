package ua.kiev.netmaster.mytaxiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ua.kiev.netmaster.mytaxiapp.domain.Order;

/**
 * Created by ПК on 12.08.2015.
 */
public class MyOrderAdapter extends BaseAdapter {


    Context ctx;
    LayoutInflater lInflater;
    List<Order> objects;



    MyOrderAdapter(Context context, List<Order> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = lInflater.inflate(R.layout.item, viewGroup, false);
        }

        Order order = getOrder(position);

        ((TextView) view.findViewById(R.id.adressFrom)).setText("From: "+order.getDepartureAddress());
        ((TextView) view.findViewById(R.id.adressTo)).setText("To: "+order.getDestinationAddress());
        ((TextView) view.findViewById(R.id.orderCoast)).setText("Sum:\n"+String.valueOf(order.getOrderSum())+" UAH");
        ((TextView) view.findViewById(R.id.clientPhone)).setText("Phone:\n "+order.getClient().getPhone());

        return view;
    }

    private Order getOrder(int position) {
        return ((Order) getItem(position));
    }
}
