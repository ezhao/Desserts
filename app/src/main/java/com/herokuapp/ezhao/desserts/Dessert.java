package com.herokuapp.ezhao.desserts;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dessert {
    private String dessertName;
    private String restaurantName;
    private String restaurantAddress;
    private boolean restaurantAddressMappable;
    private String imageUrl;

    public static ArrayList<Dessert> fromCsvFile(Activity activity) {
        ArrayList<Dessert> desserts = new ArrayList<>();
        try {
            // Get raw file from assets
            InputStream inputStream = activity.getAssets().open("desserts.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            // Turn into dessert objects
            String line = bufferedReader.readLine();
            while (line != null) {
                Dessert dessert = new Dessert();
                int firstBreak = line.indexOf("\t");
                dessert.setDessertName(line.substring(0, firstBreak));

                int secondBreak = line.indexOf("\t", firstBreak+1);
                dessert.setRestaurantName(line.substring(firstBreak+1, secondBreak));

                String restaurantAddress = line.substring(secondBreak + 1);
                if (restaurantAddress.equals("Closed") || restaurantAddress.equals("Local farmers markets")) {
                    dessert.setRestaurantAddressMappable(false);
                } else {
                    dessert.setRestaurantAddressMappable(true);
                }
                dessert.setRestaurantAddress(restaurantAddress);

                desserts.add(dessert);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return desserts;
    }

    public Dessert() {
        super();
    }

    public void setDessertName(String dessertName) {
        this.dessertName = dessertName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public void setRestaurantAddressMappable(boolean restaurantAddressMappable) {
        this.restaurantAddressMappable = restaurantAddressMappable;
    }
}
