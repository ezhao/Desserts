package com.herokuapp.ezhao.desserts;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import java.util.List;

public class DessertPagerAdapter extends FragmentStatePagerAdapter {
    private List<Dessert> desserts;

    public DessertPagerAdapter(FragmentManager fm, List<Dessert> desserts) {
        super(fm);
        this.desserts = desserts;
    }

    @Override
    public Fragment getItem(int position) {
        Dessert dessert = desserts.get(position);
        return DessertFragment.newInstance(dessert);
    }

    @Override
    public int getCount() {
        return desserts.size();
    }

    @Override
    public float getPageWidth(int position) {
        return 0.93f;
    }
}
