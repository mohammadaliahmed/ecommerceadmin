package com.appsinventiv.freewatchadmin.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.appsinventiv.freewatchadmin.Models.ProductModel;
import com.appsinventiv.freewatchadmin.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by AliAh on 10/03/2018.
 */

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ViewHolder> {
    ArrayList<ProductModel> itemList;
    Context context;
    LayoutInflater layoutInflater;

    int cartCount;
    onItemClick onItemClick;

    public ProductsListAdapter(ArrayList<ProductModel> itemList, Context context, onItemClick onItemClick) {
        this.itemList = itemList;
        this.context = context;
        this.onItemClick = onItemClick;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cart_item_layout, parent, false);
        ProductsListAdapter.ViewHolder viewHolder = new ProductsListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ProductModel model = itemList.get(position);
        holder.productTitle.setText(model.getItemTitle());
        holder.productSubTitle.setText("SKU: "+model.getSku());
        holder.productPrice.setText("Rs " + model.getItemPrice());
        Glide.with(context).load(model.getThumbnailUrl()).into(holder.productImage);
//
        if (model.getIsActive().equals("true")) {
            holder.active.setChecked(true);
        } else if (model.getIsActive().equals("false")) {
            holder.active.setChecked(false);
        }

        holder.active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                if (onItemClick != null) {
                    onItemClick.onClick(compoundButton, model.getId(), b);
                    model.setIsActive(""+b);

//                    showAlertDialogButtonClicked(compoundButton, model.getId(), b);

                }

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void showAlertDialogButtonClicked(final View view, final String productId, final boolean value) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert");
        if (value) {
            builder.setMessage("Make this product active?");
        } else {
            builder.setMessage("Make this product inactive?");
        }
        // add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onItemClick.onClick(view, productId, value);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productTitle, productSubTitle, productPrice, quantity, product_count;
        public ImageView productImage;
        public Switch active;

        public ViewHolder(View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.product_title);
            productSubTitle = itemView.findViewById(R.id.product_subtitle);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
            active = itemView.findViewById(R.id.switch1);

        }
    }

    public interface onItemClick {
        public void onClick(View view, String productId, boolean value);
    }
}
