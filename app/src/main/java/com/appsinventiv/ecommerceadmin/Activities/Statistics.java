package com.appsinventiv.ecommerceadmin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appsinventiv.ecommerceadmin.Models.OrderModel;
import com.appsinventiv.ecommerceadmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {
    TextView revenue,orders;
    DatabaseReference mDatabase;
    int totalRevenue=0;
    ArrayList<OrderModel> numOfOrders=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        revenue=findViewById(R.id.revenue);
        orders=findViewById(R.id.number_of_orders);

        mDatabase= FirebaseDatabase.getInstance().getReference();

        mDatabase.child("orders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot!=null){
                    OrderModel model=dataSnapshot.getValue(OrderModel.class);
                    if(model!=null){
                        if (model.getOrderStatus().equals("Order completed")){
                            numOfOrders.add(model);
//                            orders.setText(""+dataSnapshot.getChildrenCount());
                            totalRevenue+=model.getTotalPrice();
                            revenue.setText("Revenue: Rs "+totalRevenue);
                            calculateTotal();

                        }
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
    private  void calculateTotal(){
        orders.setText("Orders completed: "+numOfOrders.size());
    }
}
