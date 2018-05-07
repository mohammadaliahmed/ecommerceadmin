package com.appsinventiv.shoesadmin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsinventiv.shoesadmin.Models.SelectedAdImages;
import com.appsinventiv.shoesadmin.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by AliAh on 26/02/2018.
 */

public class SelectedImagesAdapter extends RecyclerView.Adapter<SelectedImagesAdapter.ViewHolder> {
    List<SelectedAdImages> mobileAds;
    Context context;

    private LayoutInflater mInflater;

    // data is passed into the constructor
    public SelectedImagesAdapter(Context context, List<SelectedAdImages> mobileAds) {
        this.mInflater = LayoutInflater.from(context);
        this.mobileAds = mobileAds;
        this.context = context;
    }

    @Override
    public SelectedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.picked_images, parent, false);
        SelectedImagesAdapter.ViewHolder viewHolder = new SelectedImagesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SelectedAdImages model = mobileAds.get(position);

        Glide.with(context).load(model.getUrl()).into(holder.adImageView);
        position = position + 1;
        holder.picCount.setText("" + position);


    }

    @Override
    public int getItemCount() {

        return mobileAds.size();

    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View myView;

        public ImageView adImageView;
        public TextView picCount;

        public ViewHolder(View itemView) {
            super(itemView);

            adImageView = itemView.findViewById(R.id.imageview);
            picCount = itemView.findViewById(R.id.pic_count);


        }


    }
}

