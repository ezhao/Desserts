package com.herokuapp.ezhao.desserts;

import android.app.Activity;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Desserts")
public class Dessert extends Model implements Serializable {
    @Column(name = "dessertName") private String dessertName;
    @Column(name = "restaurantName") private String restaurantName;
    @Column(name = "restaurantAddress") private String restaurantAddress;
    @Column(name = "restaurantAddressMappable") private boolean restaurantAddressMappable;
    @Column(name = "imageUrl") private String imageUrl;
    @Column(name = "visited") private boolean visited;

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

                dessert.save();
                desserts.add(dessert);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return desserts;
    }

    public static List<Dessert> getAll(Activity activity) {
        List<Dessert> desserts = new Select().from(Dessert.class).execute();
        if (desserts.size() == 0) {
            desserts = Dessert.fromCsvFile(activity);
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

    public String getDessertName() {
        return dessertName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }
}
