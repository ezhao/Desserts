package com.herokuapp.ezhao.desserts;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class DessertFragment extends Fragment {
    Dessert dessert;
    @InjectView(R.id.tvDessertName) TextView tvDessertName;
    @InjectView(R.id.tvRestaurantName) TextView tvRestaurantName;
    @InjectView(R.id.tvRestaurantAddress) TextView tvRestaurantAddress;

    public static DessertFragment newInstance(Dessert dessert) {
        DessertFragment dessertFragment = new DessertFragment();
        Bundle args = new Bundle();
        args.putSerializable("dessert", dessert);
        dessertFragment.setArguments(args);
        return dessertFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dessert = (Dessert) getArguments().getSerializable("dessert");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dessert, container, false);
        ButterKnife.inject(this, view);

        if (dessert != null) {
            tvDessertName.setText(dessert.getDessertName());
            tvRestaurantName.setText(dessert.getRestaurantName());
            tvRestaurantAddress.setText(dessert.getRestaurantAddress());
        }

        return view;
    }
}
