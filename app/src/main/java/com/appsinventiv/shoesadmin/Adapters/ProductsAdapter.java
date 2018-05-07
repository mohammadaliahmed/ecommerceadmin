package com.appsinventiv.shoesadmin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsinventiv.shoesadmin.Models.ProductItemCount;
import com.appsinventiv.shoesadmin.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by AliAh on 27/02/2018.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.Viewholder> {
    ArrayList<ProductItemCount> itemList;
    Context context;
    LayoutInflater layoutInflater;
    DatabaseReference mDatabase;

    public ProductsAdapter(ArrayList<ProductItemCount> itemList, Context context) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        this.itemList = itemList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ProductsAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.product_layout, parent, false);
        ProductsAdapter.Viewholder viewHolder = new ProductsAdapter.Viewholder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductsAdapter.Viewholder holder, int position) {
        final ProductItemCount model = itemList.get(position);
//        Toast.makeText(context, "" + id, Toast.LENGTH_SHORT).show();
//        mDatabase.child("products").child(id).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null) {
//                    final ProductModel model = dataSnapshot.getValue(ProductModel.class);
//                    if (model != null) {

                        holder.product_title.setText(model.getProductTitle());
                        holder.product_price.setText("Rs " + model.getPrice());
                        holder.product_sku.setText("QTY  "+model.getQuantity());


                        Glide.with(context).load(model.getThumbnailUrl()).into(holder.product_image);

//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {

        return itemList.size();


    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView product_title, product_sku, product_price;
        public ImageView product_image;

        public Viewholder(View itemView) {
            super(itemView);
            product_title = itemView.findViewById(R.id.product_title);
            product_sku = itemView.findViewById(R.id.product_sku);
            product_price = itemView.findViewById(R.id.product_price);
            product_image = itemView.findViewById(R.id.product_image);
        }
    }
}
