package com.example.rhrealestate;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // defining variables
    private View view;
    private RecyclerView homeRecyclerView;
    private List<Houses> list = new ArrayList<>();
    private HousesListAdapter housesListAdapter;
    Houses houses;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        homeRecyclerView = view.findViewById(R.id.homeRecyclerView);

        addHouses();
        bindAdapter();

        return view;
    }

    // method to add houses
    private void addHouses(){
        houses = new Houses();
        houses.setHouseId(1);
        houses.setAddress("27 Colton Circle, Kitchener, Ontario");
        houses.setPrice("1,250,000");
        houses.setDescription("5 Bedrooms, 4 Baths, one open kitchen, full finished basement, dining room, living room, attached garage and surfaced inside entry, parking space 2.");
        houses.setImage(R.drawable.houseone);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(2);
        houses.setAddress("2 Pebblecreek Drive, Kitchener, Ontario");
        houses.setPrice("2,050,000");
        houses.setDescription("5 Bedrooms, 4 Baths, one open kitchen, full finished basement, dining room, living room, swimming pool, gym area, attached garage and surfaced inside entry, parking space 4.");
        houses.setImage(R.drawable.housetwo);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(3);
        houses.setAddress("34 Amelia Street, Kitchener, Ontario");
        houses.setPrice("1,360,000");
        houses.setDescription("6 Bedrooms, 4.5 Baths, one open kitchen, full partially finished basement, dining room, living room, attached garage, parking space 3.");
        houses.setImage(R.drawable.housethree);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(4);
        houses.setAddress("11 Zeller Drive, Kitchener, Ontario");
        houses.setPrice("1,589,000");
        houses.setDescription("6 Bedrooms, 4 Baths, kitchen, full finished basement, dining room, living room, detached garage and surfaced inside entry, parking space 2.");
        houses.setImage(R.drawable.housefour);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(5);
        houses.setAddress("15 Zeller Crescent, Kitchener, Ontario");
        houses.setPrice("465,000");
        houses.setDescription("2 Bedrooms, 1.5 Baths, kitchen, full partially finished basement, dining room, living room, attached garage, parking space 1.");
        houses.setImage(R.drawable.housefive);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(6);
        houses.setAddress("35 Old Zeller Drive, Kitchener, Ontario");
        houses.setPrice("1,275,000");
        houses.setDescription("5 Bedrooms, 4.5 Baths, kitchen, full finished basement, dining room, living room, attached garage and surfaced inside entry, parking space 2.");
        houses.setImage(R.drawable.housesix);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(7);
        houses.setAddress("64 Bridgemill Crescent, Kitchener, Ontario");
        houses.setPrice("755,000");
        houses.setDescription("4 Bedrooms, 2.5 Baths, kitchen, full partially finished basement, dining room, living room, attached garage and surfaced inside entry, parking space 2.");
        houses.setImage(R.drawable.houseseven);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(8);
        houses.setAddress("46 Country Clair Street, Kitchener, Ontario");
        houses.setPrice("785,000");
        houses.setDescription("3 Bedrooms, 2.5 Baths, kitchen, full partially finished basement, dining room, living room, detached garage, parking space 2.");
        houses.setImage(R.drawable.houseeight);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(9);
        houses.setAddress("38 Landgren Ct, Kitchener, Ontario");
        houses.setPrice("1,168,000");
        houses.setDescription("5 Bedrooms, 4 Baths, kitchen, full finished basement, dining room, living room, attached garage and surfaced inside entry, parking space 2.");
        houses.setImage(R.drawable.housenine);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(10);
        houses.setAddress("35 Colton Circle, Kitchener, Ontario");
        houses.setPrice("955,000");
        houses.setDescription("5 Bedrooms, 4.5 Baths, kitchen, full finished basement, dining room, living room, detached garage, parking space 3.");
        houses.setImage(R.drawable.houseten);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(11);
        houses.setAddress("11 Feenstra Street, Kitchener, Ontario");
        houses.setPrice("1,039,000");
        houses.setDescription("6 Bedrooms, 4 Baths, kitchen, full finished basement, dining room, living room, attached garage, parking space 4.");
        houses.setImage(R.drawable.houseeleven);
        list.add(houses);

        houses = new Houses();
        houses.setHouseId(12);
        houses.setAddress("15 Feenstra Street, Kitchener, Ontario");
        houses.setPrice("949,000");
        houses.setDescription("3 Bedrooms, 2.5 Baths, one open kitchen, full finished basement, dining room, living room, attached garage and surfaced inside entry, parking space 2.");
        houses.setImage(R.drawable.housetwelve);
        list.add(houses);
    }

    // binding data to the recyclerview
    private void bindAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        homeRecyclerView.setLayoutManager(layoutManager);
        housesListAdapter = new HousesListAdapter(list, getContext());
        homeRecyclerView.setAdapter(housesListAdapter);
        housesListAdapter.notifyDataSetChanged();
    }
}