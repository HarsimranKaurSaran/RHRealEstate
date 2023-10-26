package com.example.rhrealestate;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class HomeDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    // defining all the variables
    private int houseId, image;
    private String address, description, price;
    private TextView addressTv, priceTv, descriptionTv;
    private ImageView houseImageView;
    private GoogleMap googleMap;
    private double lat = 0;
    private double lng = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedetail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // getting data passed to intent
        Intent intent = getIntent();
        houseId = intent.getIntExtra("id", 0);
        address = intent.getStringExtra("address");
        price = intent.getStringExtra("price");
        description = intent.getStringExtra("description");
        image = intent.getIntExtra("image", R.drawable.ic_navhome);

        addressTv = (TextView) findViewById(R.id.houseaddress_tv);
        priceTv = (TextView) findViewById(R.id.houseprice_tv);
        descriptionTv = (TextView) findViewById(R.id.housedescription_tv);
        houseImageView = (ImageView) findViewById(R.id.house_imageView);

        // setting data to the textviews and imageview
        addressTv.setText(address);
        priceTv.setText("CAD $" + price);
        descriptionTv.setText(description);
        houseImageView.setImageResource(image);
    }

    // showing the address on the google map
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocationName(address, 1);
            lat = addresses.get(0).getLatitude();
            lng = addresses.get(0).getLongitude();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LatLng location = new LatLng(lat, lng);
        float zoom = 16.0f;
        googleMap.addMarker(new MarkerOptions().position(location).title(address));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoom));
    }
}
