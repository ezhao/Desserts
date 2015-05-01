package com.herokuapp.ezhao.desserts;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DessertFragment extends Fragment {
    Dessert dessert;
    @InjectView(R.id.tvDessertName) TextView tvDessertName;
    @InjectView(R.id.tvRestaurantName) TextView tvRestaurantName;
    @InjectView(R.id.tvRestaurantAddress) TextView tvRestaurantAddress;
    @InjectView(R.id.btnComplete) Button btnComplete;

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

            if (dessert.isVisited()) {
                setDessertCompleted();
            } else {
                setDessertNotCompleted();
            }
        }

        return view;
    }

    @OnClick(R.id.btnComplete)
    public void onCompleteDessert() {
        if (dessert == null) {
            return;
        }

        if (dessert.isVisited()) {
            setDessertNotCompleted();
        } else {
            setDessertCompleted();
        }
    }

    private void setDessertNotCompleted() {
        btnComplete.setText(getResources().getString(R.string.complete));
        btnComplete.setBackground(getResources().getDrawable(R.drawable.button_not_completed_bg));
        dessert.setVisited(false);
        dessert.save();
    }

    private void setDessertCompleted() {
        btnComplete.setText(getResources().getString(R.string.completed));
        btnComplete.setBackground(getResources().getDrawable(R.drawable.button_completed_bg));
        dessert.setVisited(true);
        dessert.save();
    }
}
