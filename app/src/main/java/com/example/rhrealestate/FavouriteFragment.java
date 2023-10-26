package com.example.rhrealestate;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {

    // defining variables
    private View view;
    private RecyclerView favRecyclerView;
    private List<Houses> favList = new ArrayList<>();
    private FavouritesListAdapter favouritesListAdapter;

    DatabaseHelper dbh;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
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
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        favRecyclerView = view.findViewById(R.id.favRecyclerView);

        // getting results here
        dbh = new DatabaseHelper(getContext());
        Cursor cursor = dbh.viewFavHouseData();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Houses houses = new Houses();
                    houses.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                    houses.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                    houses.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    houses.setImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("image"))));
                    favList.add(houses);
                } while (cursor.moveToNext());
            }
            cursor.close();
            dbh.close();
            bindAdapter();
        } else {
            Toast.makeText(getActivity(), "No data found.", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    // binding data to the recyclerview
    private void bindAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        favRecyclerView.setLayoutManager(layoutManager);
        favouritesListAdapter = new FavouritesListAdapter(favList, getContext());
        favRecyclerView.setAdapter(favouritesListAdapter);
        favouritesListAdapter.notifyDataSetChanged();
    }
}