package com.appsinventiv.ecommerceadmin.Activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appsinventiv.ecommerceadmin.Adapters.OrdersAdapter;
import com.appsinventiv.ecommerceadmin.Models.OrderModel;
import com.appsinventiv.ecommerceadmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    String userId;
    ArrayList<String> ordersList = new ArrayList<>();
    DatabaseReference mDatabase;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OrdersAdapter adapter;
    ArrayList<OrderModel> orderModelArrayList=new ArrayList<>();
    ArrayList<String> productModelArrayList=new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_orders);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new OrdersAdapter(orderModelArrayList,MainActivity.this);
        recyclerView.setAdapter(adapter);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        loadOrdersList();



    }



    private void loadOrdersList() {
        mDatabase.child("orders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null) {
                    OrderModel orderModel = dataSnapshot.getValue(OrderModel.class);
                    if (orderModel != null) {
                        orderModelArrayList.add(orderModel);

                        Collections.sort(orderModelArrayList, new Comparator<OrderModel>() {
                            @Override
                            public int compare(OrderModel listData, OrderModel t1) {
                                Long ob1 = listData.getTime();
                                Long ob2 = t1.getTime();

                                return ob2.compareTo(ob1);

                            }
                        });
                        adapter.notifyDataSetChanged();
//                        for (DataSnapshot childSnapshot : dataSnapshot.child("productIds").getChildren()) {
//
//                            productModelArrayList.add("" + childSnapshot.getValue());
////                            Toast.makeText(MyOrders.this, "" + childSnapshot.getValue(), Toast.LENGTH_SHORT).show();
//
//                        }
                    }
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_product) {
            Intent i = new Intent(MainActivity.this, AddProduct.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
