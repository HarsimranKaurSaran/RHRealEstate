package com.example.rhrealestate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouritesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // defining all the variables
    private List<Houses> favHousesList;

    public FavouritesListAdapter(List<Houses> list, Context context) {
        super();
        favHousesList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView address, price;
        public ImageView houseImage, favBtn, shareBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address = (TextView) itemView.findViewById(R.id.address_tv);
            price = (TextView) itemView.findViewById(R.id.price_tv);
            houseImage = (ImageView) itemView.findViewById(R.id.home_imageView);
            favBtn = (ImageView) itemView.findViewById(R.id.fav_imageView);
            shareBtn = (ImageView) itemView.findViewById(R.id.share_imageView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Houses itemAdapter = favHousesList.get(position);

        ((ViewHolder) holder).address.setText("Address: " + itemAdapter.getAddress());
        ((ViewHolder) holder).price.setText("Price: $ " + itemAdapter.getPrice());
        ((ViewHolder) holder).houseImage.setImageResource(Integer.parseInt(String.valueOf(itemAdapter.getImage())));

        // if the item is selected, put the data in intent and pass the data to the HomeDetailActivity
        ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HomeDetailActivity.class);
                intent.putExtra("id", itemAdapter.getHouseId());
                intent.putExtra("address", itemAdapter.getAddress());
                intent.putExtra("price", itemAdapter.getPrice());
                intent.putExtra("description", itemAdapter.getDescription());
                intent.putExtra("image", itemAdapter.getImage());
                v.getContext().startActivity(intent);
            }
        });

        ((ViewHolder) holder).favBtn.setVisibility(View.INVISIBLE);
        ((ViewHolder) holder).shareBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return favHousesList.size();
    }
}
