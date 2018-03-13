package com.appsinventiv.headphonesadmin.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appsinventiv.headphonesadmin.Adapters.OrdersAdapter;
import com.appsinventiv.headphonesadmin.Models.OrderModel;
import com.appsinventiv.headphonesadmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class PendingOrders extends Fragment {

    DatabaseReference mDatabase;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OrdersAdapter adapter;
    ArrayList<OrderModel> orderModelArrayList = new ArrayList<>();

    Context context;
    SharedPreferences userPref;
    String fcmKey;


    public PendingOrders() {
    }

    private void setUserFcm(String fcm) {
        mDatabase.child("admin").child("fcmKey").setValue(fcm);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toast.makeText(context, "sdfsd", Toast.LENGTH_SHORT).show();
        userPref = context.getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        fcmKey = userPref.getString("fcmKey", "");
//        Toast.makeText(context, ""+fcmKey, Toast.LENGTH_SHORT).show();


        mDatabase = FirebaseDatabase.getInstance().getReference();
        setUserFcm(fcmKey);

        loadPendingOrders();

    }

    private void loadPendingOrders() {
        mDatabase.child("orders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null) {
                    OrderModel orderModel = dataSnapshot.getValue(OrderModel.class);
                    if (orderModel != null) {
                        if (orderModel.getOrderStatus().equals("Pending")) {

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
                        }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_pending_orders, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_pending_orders);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrdersAdapter(orderModelArrayList, context);
        recyclerView.setAdapter(adapter);

        return rootView;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
