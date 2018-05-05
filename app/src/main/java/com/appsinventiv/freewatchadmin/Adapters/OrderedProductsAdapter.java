package com.appsinventiv.freewatchadmin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appsinventiv.freewatchadmin.Models.ProductItemCount;
import com.appsinventiv.freewatchadmin.Models.ProductModel;
import com.appsinventiv.freewatchadmin.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by AliAh on 22/02/2018.
 */

public class OrderedProductsAdapter extends RecyclerView.Adapter<OrderedProductsAdapter.ViewHolder> {
    ArrayList<ProductItemCount> itemList;
    Context context;
    LayoutInflater layoutInflater;
    DatabaseReference mDatabase;

    public OrderedProductsAdapter(Context context, ArrayList<ProductItemCount> itemList) {
//        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.layoutInflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.ordered_products_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
         ProductItemCount model = itemList.get(position);
//        mDatabase.child("products").child(id).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null) {
//                    final ProductModel model = dataSnapshot.getValue(ProductModel.class);
//                    if (model != null) {
//        Toast.makeText(context, ""+model.getQuantity(), Toast.LENGTH_SHORT).show();
        holder.productTitle.setText(model.getProductTitle());
        holder.productPrice.setText("Rs " + model.getPrice());


        Glide.with(context).load(model.getThumbnailUrl()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                                Intent i = new Intent(context, ProductDescription.class);
//                                i.putExtra("productId", model.getId());
//                                context.startActivity(i);

            }
        });
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


//        holder.productTitle.setText(model.getItemTitle());
//        holder.productPrice.setText("Rs " + model.getItemPrice());
//
//
//        Glide.with(context).load(model.getThumbnailUrl()).into(holder.image);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(context, ProductDescription.class);
//                i.putExtra("productId",model.getId());
//                context.startActivity(i);
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
//        Toast.makeText(context, "Size: "+itemList.size(), Toast.LENGTH_SHORT).show();
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView productTitle, productPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);


        }
    }
}
