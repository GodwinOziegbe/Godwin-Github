package ya.examensarbete.simplegpsapplication.MarkerServices;

import android.app.Application;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class MySavedLocations extends Application {
    private static MySavedLocations instance;
    private List<Location> myLocations;
    private Location currentLocation;

    public List<Location> getMyLocations()
    {
        return myLocations;
    }

    public void setMyLocations(List<Location> myLocations)
    {
        this.myLocations = myLocations;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public MySavedLocations getInstance()
    {
        return instance;
    }


    public void onCreate() {

        super.onCreate();
        instance= this;
        myLocations = new ArrayList<>();

    }


}
