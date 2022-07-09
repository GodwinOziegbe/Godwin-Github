package ya.examensarbete.simplegpsapplication.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import ya.examensarbete.simplegpsapplication.MarkerServices.MySavedLocations;
import ya.examensarbete.simplegpsapplication.R;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int FAST_UPDATE_INTERVAL = 5;
    public static final int PERMISSIONS_FINE_LOCATION = 84;
    private static Location mylocationInstance;
    //declare the UI elements
    TextView txtview_lat,  txtview_lon,  txtview_altitude,  txtview_accuracy,
            txtview_speed,  txtview_address,  txtview_updates,  txtview_network ;
    Switch sw_location_updates, sw_gps;
    Button btn_showMap;

    boolean updateOn = false;

    //Google's API for location services. The majority of the app functions using this class.
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest = LocationRequest.create();
    LocationCallback locationCallBack;

    Location currentLocation;

    //List of savedlocations(shown by markers)
    List<Location> savedLocations;


    public Location getMylocationInstance(){
        return mylocationInstance;
    }

    //start of onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define each UI variable
        txtview_lat = findViewById(R.id.txtview_lat);
        txtview_lon = findViewById(R.id.txtview_lon);
        txtview_altitude = findViewById(R.id.txtview_altitude);
        txtview_accuracy = findViewById(R.id.txtview_accuracy);
        txtview_speed = findViewById(R.id.txtview_speed);
        txtview_address = findViewById(R.id.txtview_address);
        txtview_updates = findViewById(R.id.txtview_updates);
        txtview_network = findViewById(R.id.txtview_network);
        sw_gps = findViewById(R.id.sw_gps);
        sw_location_updates = findViewById(R.id.sw_location_updates);
        btn_showMap = findViewById(R.id.btn_showMap);



        // set all properties of locationRequest

        locationRequest

                //how often does the default location check occur?
                .setInterval(1000 * DEFAULT_UPDATE_INTERVAL)//set to every 30seconds
                .setFastestInterval(1000 * FAST_UPDATE_INTERVAL)//set to every 5 seconds
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        ;

        //event triggers whenever the update interval is met
        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                //Save location
                updateUIValues(locationResult.getLastLocation());
            }
        };
    //listens if network switch is turned on
        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw_gps.isChecked()) {
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    txtview_network.setText("Using GPS signals");
                } else {
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    txtview_network.setText("Using Towers + WIFI");
                }
            }
        });

        btn_showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);

                MySavedLocations mySavedLocations = (MySavedLocations) getApplicationContext();
                savedLocations = mySavedLocations.getMyLocations();
                savedLocations.add(currentLocation);
                mySavedLocations.setCurrentLocation(currentLocation);

            }
        });

        //listens if location updates switch is turned on
        sw_location_updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw_location_updates.isChecked()) {
                    //turn on location tracking
                    startTrackingLocation();
                } else {
                    //turn off location tracking
                    stopTrackingLocation();
                }
            }
        });

        updateGPS();
    }//end of onCreate methods
   // Timer timer = new Timer("Timer");

    int interval = 1000;
    //timer to add saved locations
    public void getsavedLoc() {
    new Timer().scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            savedLocations.add(currentLocation);
//            Log.i("tag", "A Kiss every 5 seconds");
        }
    }, 0, 30000);

//    TimerTask task = new TimerTask() {
//        public void run() {
//            Toast.makeText(MainActivity.this, "Task performed on: " + new Date() + "Thread's name:" + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();

//            System.out.println("Task performed on: " + new Date() + "n" +
//                    "Thread's name: " + Thread.currentThread().getName());
//        }
//    };
//    Timer timer = new Timer("Timer");
//
//    int delay = 1000;
//    timer.schedule(task, delay);

}
    private void updateSavedLocation(List<Location> savedLocations) {
        savedLocations.add(currentLocation);

    }

    //overrides method from AppCompatActivity
    //this method listens for permission requests
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch ((requestCode)){
            case PERMISSIONS_FINE_LOCATION:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    updateGPS();
                } else {
                    Toast.makeText(this, "This new app requires permission to be granted in order to work properly",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }


    void updateGPS() {
        //get permissions from the user to track GPS
        //get the current location from the fused client
        //update the UI - i.e. set all properties in their associated text view items.

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            //user provided the permission
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener( this,
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            currentLocation = location;

                            updateUIValues(location);
                        }
                    });
        } else {
            //permission not granted yet
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }

        }
    }


    void startTrackingLocation() {
        //get permissions from the user to track GPS
        txtview_updates.setText("Location tracking started");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            } else {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Toast.makeText(this, "This app requires permission to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
     }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();
    }

    private void stopTrackingLocation() {
        txtview_updates.setText("Location tracking is switched Off");
        txtview_lat.setText("Latitude tracking is turned Off");
        txtview_lon.setText("Longitude tracking is turned Off");
        txtview_speed.setText("Speed tracking is turned Off");
        txtview_altitude.setText("Altitude is turned Off");
        txtview_accuracy.setText("Accuracy tracking is turned Off");
        txtview_address.setText("Address tracking is turned Off");


        fusedLocationProviderClient.removeLocationUpdates((locationCallBack));
    }
    private void updateUIValues(Location location) {
        //update the UI with current values
        txtview_lat.setText(String.valueOf(location.getLatitude()));
        txtview_lon.setText(String.valueOf(location.getLongitude()));
        txtview_accuracy.setText(String.valueOf(location.getAccuracy()));

        if(location.hasAltitude()){
            txtview_altitude.setText(Double.toString( location.getAltitude()));
        } else {
            txtview_altitude.setText("Not Available");
        }

        if(location.hasSpeed()){
            txtview_speed.setText(String.valueOf(location.getSpeed()));
        } else {
            txtview_speed.setText("Not Available");
        }

        Geocoder geocoder = new Geocoder(MainActivity.this);

        try{
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
            txtview_address.setText(addressList.get(0).getAddressLine(0));
        }
        catch (Exception e){
            txtview_address.setText("Unable to get street address");
        }


    }
}