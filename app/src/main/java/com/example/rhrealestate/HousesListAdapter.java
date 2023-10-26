package com.example.rhrealestate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HousesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // defining all the variables
    private List<Houses> housesList;
    private boolean status;
    private boolean insertstatus;

    DatabaseHelper dbhelper;

    public HousesListAdapter(List<Houses> list, Context context) {
        super();
        housesList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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
        int houseId, image;
        String address, price, description;

        Houses itemAdapter = housesList.get(position);
        houseId = itemAdapter.getHouseId();
        image = itemAdapter.getImage();
        address = itemAdapter.getAddress();
        price = itemAdapter.getPrice();
        description = itemAdapter.getDescription();

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

        ((ViewHolder) holder).favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbhelper = new DatabaseHelper(v.getContext());
                status = dbhelper.checkFavHouses(houseId);
                if (status == false) {
                    Houses houses = new Houses(houseId, address, price, description, image);
                    insertstatus = dbhelper.insertFavHouse(houses);
                    if (insertstatus == true){
                        Toast.makeText(v.getContext(), "Added to favourites.", Toast.LENGTH_LONG).show();
                        ((ViewHolder) holder).favBtn.setImageResource(R.drawable.ic_favfilled);
                    } else {
                        Toast.makeText(v.getContext(), "Cannot add to favourites.", Toast.LENGTH_LONG).show();
                    }

                } else if (status == true) {
                    Houses houses = new Houses(itemAdapter.getHouseId(), itemAdapter.getAddress(), itemAdapter.getPrice(), itemAdapter.getDescription(), itemAdapter.getImage());
                    dbhelper.deleteFavHouses(houses);
                    if (insertstatus == true) {
                        Toast.makeText(v.getContext(), "Removed from favourites.", Toast.LENGTH_LONG).show();
                        ((ViewHolder) holder).favBtn.setImageResource(R.drawable.ic_fav);
                    } else {
                        Toast.makeText(v.getContext(), "Cannot remove from favourites.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // if share button is clicked, get the details of the house like address, price and description and start the intent
        ((ViewHolder) holder).shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                housesList.get(position);
                String text = "Check the details of this house:"
                        + "\n Address: " + itemAdapter.getAddress()
                        + "\n Price: " + itemAdapter.getPrice()
                        + "\n Description: " + itemAdapter.getDescription();

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                intent.putExtra(Intent.EXTRA_TEXT, text);
                intent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(intent, "Share to");
                v.getContext().startActivity(shareIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return housesList.size();
    }
}
