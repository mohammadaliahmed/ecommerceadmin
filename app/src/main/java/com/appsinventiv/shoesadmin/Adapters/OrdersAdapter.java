package com.appsinventiv.shoesadmin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsinventiv.shoesadmin.Activities.ViewOrder;
import com.appsinventiv.shoesadmin.Models.OrderModel;
import com.appsinventiv.shoesadmin.Models.ProductItemCount;
import com.appsinventiv.shoesadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by AliAh on 26/02/2018.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder>  {

    ArrayList<OrderModel> itemList;
    Context context;
    LayoutInflater layoutInflater;
    OrderedProductsAdapter adapter;
    DatabaseReference mDatabase;
    ArrayList<ProductItemCount> productModelsList=new ArrayList<>();


    public OrdersAdapter(ArrayList<OrderModel> itemList, Context context) {
        mDatabase= FirebaseDatabase.getInstance().getReference();
        this.layoutInflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.order_item_layout, parent, false);
        OrdersAdapter.ViewHolder viewHolder = new OrdersAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.ViewHolder holder, int position) {
        final OrderModel model=itemList.get(position);
        holder.orderId.setText("Order Id: "+model.getOrderId());
        holder.username.setText("Order By     Name: "+model.getFullName()+"\n"+"                     Phone: "+model.getPhone()+"\n                     City: "+model.getCity());
        holder.orderTime.setText("Order Time: "+getFormattedDate(model.getTime()));
        holder.orderStatus.setText("Order Status: "+model.getOrderStatus());
        holder.quantity.setText("Quantity: "+model.getItemCount());
        holder.totalAmount.setText("Total: Rs "+model.getTotalPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(context, ViewOrder.class);
                i.putExtra("orderId",model.getOrderId());
                context.startActivity(i);

            }
        });

        ArrayList<ProductItemCount> list=model.getProductIdList();
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(horizontalLayoutManagaer);
        adapter = new OrderedProductsAdapter(context, list);
        holder.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        mDatabase.child("orders").child(model.getOrderId()).child("productIdList").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                if(dataSnapshot!=null){
//                    ProductItemCount ordrId=dataSnapshot.getValue(ProductItemCount.class);
//                    list.add(ordrId);
//                    adapter.notifyDataSetChanged();
////                    loadProducts(ordrId);
//                }
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }

    private void loadProducts(String id) {
        mDatabase.child("products").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    ProductItemCount model=dataSnapshot.getValue(ProductItemCount.class);
                    productModelsList.add(model);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String getFormattedDate(long smsTimeInMilis) {
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(smsTimeInMilis);

        Calendar now = Calendar.getInstance();

        final String timeFormatString = "h:mm aa";
        final String dateTimeFormatString = "dd MMM ";
        final long HOURS = 60 * 60 * 60;
        if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE) ) {
            return "" + DateFormat.format(timeFormatString, smsTime);
        } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1  ){
            return "Yesterday ";
        } else if (now.get(Calendar.YEAR) == smsTime.get(Calendar.YEAR)) {
            return DateFormat.format(dateTimeFormatString, smsTime).toString();
        } else {
            return DateFormat.format("dd MMM , h:mm aa", smsTime).toString();
        }
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView orderId,orderTime,orderStatus,quantity,totalAmount,username;
        public  RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            orderId=itemView.findViewById(R.id.orderid);
            orderTime=itemView.findViewById(R.id.orderTime);
            orderStatus=itemView.findViewById(R.id.orderStatus);
            quantity=itemView.findViewById(R.id.quantity);
            totalAmount=itemView.findViewById(R.id.total);
            username=itemView.findViewById(R.id.order_id);



            recyclerView=itemView.findViewById(R.id.recycler_order_prodcuts);


        }
    }
}
