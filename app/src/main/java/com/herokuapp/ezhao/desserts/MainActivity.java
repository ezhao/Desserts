package com.herokuapp.ezhao.desserts;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {
    List<Dessert> desserts;
    @InjectView(R.id.vpFragments) ViewPager vpFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        FragmentManager fm = getFragmentManager();
        desserts = Dessert.getAll(this);
        DessertPagerAdapter dessertPagerAdapter = new DessertPagerAdapter(fm, desserts);
        vpFragments.setAdapter(dessertPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
