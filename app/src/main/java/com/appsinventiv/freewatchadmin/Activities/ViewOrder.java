package com.appsinventiv.freewatchadmin.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appsinventiv.freewatchadmin.Adapters.ProductsAdapter;
import com.appsinventiv.freewatchadmin.Models.OrderModel;
import com.appsinventiv.freewatchadmin.R;
import com.appsinventiv.freewatchadmin.Utils.NotificationAsync;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewOrder extends AppCompatActivity {
    TextView orderId, orderTime, quantity, price, username, phone, address, city;
    String orderIdFromIntent;
    DatabaseReference mDatabase;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ProductsAdapter adapter;
    ArrayList<String> list = new ArrayList<>();
    Button orderCompleted, orderShipped;

    String s_orderId, s_orderTime, s_quantity, s_price, s_username, s_phone, s_address, s_city;
    String userFcmKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent = getIntent();
        orderIdFromIntent = intent.getStringExtra("orderId");

        orderId = findViewById(R.id.order_id);
        orderTime = findViewById(R.id.order_time);
        quantity = findViewById(R.id.order_quantity);
        price = findViewById(R.id.order_price);


        username = findViewById(R.id.ship_username);
        phone = findViewById(R.id.ship_phone);
        address = findViewById(R.id.ship_address);
        city = findViewById(R.id.ship_city);


        orderCompleted = findViewById(R.id.completed);
        orderShipped = findViewById(R.id.shipped);

        orderShipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                markOrderAsShipped();
            }
        });

        orderCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markOrderAsComplete();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("orders").child(orderIdFromIntent).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    OrderModel model = dataSnapshot.getValue(OrderModel.class);
                    if (model != null) {
                        orderId.setText("" + model.getOrderId());
                        orderTime.setText("" + getFormattedDate(model.getTime()));
                        quantity.setText("" + model.getItemCount());
                        price.setText("Rs " + model.getTotalPrice());
                        username.setText("" + model.getFullName());
                        phone.setText("" + model.getPhone());
                        address.setText(model.getAddress());
                        city.setText(model.getCity());
                        list = model.getProductIdList();
                        adapter = new ProductsAdapter(list, ViewOrder.this);
                        recyclerView.setAdapter(adapter);

                        if(model.getOrderStatus().equals("Pending")){
                            orderCompleted.setVisibility(View.GONE);
                            orderShipped.setVisibility(View.VISIBLE);

                        }
                        else if(model.getOrderStatus().equals("Order shipped")){
                            orderShipped.setVisibility(View.GONE);
                            orderCompleted.setVisibility(View.VISIBLE);
                        }
                        else{
                            orderShipped.setVisibility(View.GONE);
                            orderCompleted.setVisibility(View.GONE);
                        }
//                        Toast.makeText(ViewOrder.this, ""+list, Toast.LENGTH_SHORT).show();
//                        adapter.notifyDataSetChanged();

                        s_orderId = model.getOrderId();
                        s_quantity = "" + model.getItemCount();
                        s_price = "" + model.getTotalPrice();
                        s_username = model.getUserId();

                        getUserFcmKey();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void getUserFcmKey() {
        mDatabase.child("customers").child(s_username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userFcmKey = dataSnapshot.child("fcmKey").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void markOrderAsComplete() {
        showAlertDialogButtonClicked("completed");
    }

    private void markOrderAsShipped() {
        showAlertDialogButtonClicked("shipped");
    }

    public void showAlertDialogButtonClicked(final String message) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewOrder.this);
        builder.setTitle("Alert");
        builder.setMessage("Do you want to mark this order as " + message + "?");

        // add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDatabase.child("orders").child(orderIdFromIntent).child("orderStatus").setValue("Order " + message).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ViewOrder.this, "Order marked as "+message, Toast.LENGTH_SHORT).show();
                        NotificationAsync notificationAsync = new NotificationAsync(ViewOrder.this);
                        String notification_title = "You order has been " + message;
                        String notification_message = "Order Id:  " + s_orderId + "    Item Count:  " + s_quantity + "    Total:  Rs " + s_price;
                        notificationAsync.execute("ali", userFcmKey, notification_title, notification_message);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public String getFormattedDate(long smsTimeInMilis) {
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(smsTimeInMilis);

        Calendar now = Calendar.getInstance();

        final String timeFormatString = "h:mm aa";
        final String dateTimeFormatString = "dd MMM ";
        final long HOURS = 60 * 60 * 60;
        if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
            return "" + DateFormat.format(timeFormatString, smsTime);
        } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1) {
            return "Yesterday ";
        } else if (now.get(Calendar.YEAR) == smsTime.get(Calendar.YEAR)) {
            return DateFormat.format(dateTimeFormatString, smsTime).toString();
        } else {
            return DateFormat.format("dd MMM , h:mm aa", smsTime).toString();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i =new Intent(ViewOrder.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            Intent i =new Intent(ViewOrder.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
