package ya.examensarbete.simplegpsapplication.UserInterface;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import ya.examensarbete.simplegpsapplication.MarkerServices.EditMarker;
import ya.examensarbete.simplegpsapplication.Models.MarkerModel;
import ya.examensarbete.simplegpsapplication.Repository.MarkerRepository;
import ya.examensarbete.simplegpsapplication.MarkerServices.GetMarkers;
import ya.examensarbete.simplegpsapplication.MarkerServices.MySavedLocations;
import ya.examensarbete.simplegpsapplication.R;
import ya.examensarbete.simplegpsapplication.Utilities.ArithmeticCalculations;
import ya.examensarbete.simplegpsapplication.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private boolean marker_close;
    GoogleMap mMap;
    private ActivityMapsBinding binding;
    private boolean message_added = false;
    private double message_location_lat;
    private boolean location_refreshed = true;
    MarkerRepository markerRepository;
    List<Location> savedLocations;
    Location currentLocation;
    Location oldLocation;
    double theoldlatitude;
    double l1;
    int initiateMapCount;
    LatLng latLng;
    LatLng lastLocationPlaced;
    MarkerOptions markerOptions;
    ArithmeticCalculations arithmeticCalculations;
    String mySnippet = "Click me to add info.";
    protected MySavedLocations mySavedLocations;
    // GetMarkers getMarkers;
    int count = 1;
    int check;
    public static final int PERMISSIONS_FINE_LOCATION = 8;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest = LocationRequest.create();
    LocationCallback locationCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mySavedLocations = (MySavedLocations) getApplicationContext();
        //getMarkers=(GetMarkers) getApplicationContext();
        currentLocation = null;
        currentLocation = mySavedLocations.getCurrentLocation();
        theoldlatitude = 0.0;
        initiateMapCount = -1;//a counter that allows us to display snippet the first time the map is launched
        marker_close = false;
        MarkerRepository markerRepository1 = new MarkerRepository(MapsActivity.this);
        check = 0;
        if (!markerRepository1.getMarkers().isEmpty()) {
            check = markerRepository1.getMarkers().size();
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        markerRepository = new MarkerRepository(MapsActivity.this);

        locationRequest

                //how often does the default location check occur?
                .setInterval(1000 * 15)//set to every 30seconds
                .setFastestInterval(1000 * 10)//set to every 5 seconds
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        ;

        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                GetMarkers getMarkers = new GetMarkers();
                super.onLocationResult(locationResult);
                mMap.clear();//clears location markers from screen
                getMarkers.getAllMarkers(mMap,markerRepository,locationResult.getLastLocation());//loads old markers and new ones if any
               // getMarkerTitle(locationResult.getLastLocation());
                //getPosition.getCurrentPosition( latLng, lastLocationPlaced, initiateMapCount,  mySnippet, markerOptions,  message_added,  location_refreshed,theoldlatitude, message_location_lat,  mMap,  markerRepository,locationResult.getLastLocation());
                getCurrentPosition(locationResult.getLastLocation());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocationPlaced, 12));//positions the camera at specified zoom point


            }
        };

        updateGPS();
        startTrackingLocation();
        //marker's on click listener
//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(@NonNull Marker marker) {
//                if (markerOptions.getPosition().latitude == currentLocation.getLatitude()) {
//                   mMap.addMarker(markerOptions).showInfoWindow();
//                    mMap.addMarker(markerOptions).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//                }else {
//                    mMap.addMarker(markerOptions).showInfoWindow();
//                }
//                    return false;
//                }
//
//        });

        //marker's info window's on click listener
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                EditMarker editMarker = EditMarker.newInstance(marker.getTitle());
                editMarker.show(getSupportFragmentManager(), "dialog");
            }
        });

    }//end of onMapReady

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch ((requestCode)) {
            case PERMISSIONS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                } else {
                    Toast.makeText(this, "This new app requires permission to be granted in order to work properly",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @SuppressLint("MissingPermission")
    void updateGPS() {
        //GetMarkers getMarkers = new GetMarkers();
        // GetMarkers getMarkers1;
        //get permissions from the user to track GPS
        //get the current location from the fused client
        //get saved markers if any
        //MarkerRepository markerRepository = new MarkerRepository(MapsActivity.this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            //user provided the permission
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this,
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            currentLocation = location;
                             GetMarkers getMarkers = new GetMarkers();
                             if (!markerRepository.getMarkers().isEmpty()) {
                                getMarkers.getAllMarkers(mMap,markerRepository,location);
                               }
                            getCurrentPosition(location);
                           // getPosition.getCurrentPosition( latLng, lastLocationPlaced, initiateMapCount,  mySnippet, markerOptions,  message_added,  location_refreshed,theoldlatitude, message_location_lat,  mMap,  markerRepository,location);

                            oldLocation = null;
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocationPlaced, 12));

                        }
                    });
        } else {
            //permission not granted yet
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }

        }
    }

    @SuppressLint("MissingPermission")
    void startTrackingLocation() {
        //get permissions from the user to track GPS
        //Begins to track the user locations

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            } else {
                Toast.makeText(this, "This app requires permission to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
    }

    private void stopTrackingLocation() {//not yet used
        fusedLocationProviderClient.removeLocationUpdates((locationCallBack));
    }
//
    //************Function to get current marker's position and positions the gps marker on that position**********
    private void getCurrentPosition(Location thiscurrentLocation) {
        arithmeticCalculations = new ArithmeticCalculations();
        //GetAddress getAddress = new GetAddress();
        MarkerRepository markerRepository = new MarkerRepository(MapsActivity.this);
//        markerOptions = new MarkerOptions();
//        latLng = new LatLng(thiscurrentLocation.getLatitude(), thiscurrentLocation.getLongitude());
//        markerOptions.position(latLng);
        getMarkerTitle(thiscurrentLocation);
//        String address = getAddressInfo().get(0).getAddressLine(0); //0 to obtain first possible address
//        String title = address;
//        markerOptions.title(title);

        if (message_added) {//a flag to check if new message has been added
            if (message_location_lat != thiscurrentLocation.getLatitude()) {//if location changed refresh app
                Intent i = new Intent(MapsActivity.this, MapsActivity.class);
                startActivity(i);
                message_added = false;
                location_refreshed = true;
            } else if (!location_refreshed) {
                Intent i = new Intent(MapsActivity.this, MapsActivity.class);
                startActivity(i);
                location_refreshed = true;
            }
        }
        //todo check to see if check can be decremented once after admarker() is called and/or after a new location is detected
        if (markerRepository.getMarkers().isEmpty()) {
            markerOptions.snippet(mySnippet);
        } else {
            for (MarkerModel value : markerRepository.getMarkers()) {//The saved snippet will appear instead of an empty snippet
                if (value.getMarkerLatitude().equals(String.valueOf(arithmeticCalculations.roundToFourDigits(thiscurrentLocation.getLatitude())))) {//if marker is in current location
                    markerOptions.snippet(value.getSnippetTag().toUpperCase());
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    mMap.addMarker(markerOptions).showInfoWindow();
                    break;
                } else {//marker not in current location
                    markerOptions.snippet(mySnippet);
                }

            }
        }

        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);

        if ((theoldlatitude != (thiscurrentLocation.getLatitude())) || initiateMapCount < 1) {//show snippet if location is new or first time map is launched

            mMap.addMarker(markerOptions).showInfoWindow();
            theoldlatitude = thiscurrentLocation.getLatitude();
            initiateMapCount++;
        }
        lastLocationPlaced = latLng;
    }//end of getCurrentPosition method

    //************Function to add a marker object****************
    public void addMarker(Editable title, String message) {
        arithmeticCalculations = new ArithmeticCalculations();
        //GetAddress getAddress = new GetAddress();
        MarkerRepository markerRepository = new MarkerRepository(MapsActivity.this);
        Date created = new Date();
        Date lastUpdated = new Date();
        boolean success;
        double lat_double = currentLocation.getLatitude();
        double lon_double = currentLocation.getLongitude();
        String lat_string = String.valueOf(arithmeticCalculations.roundToFourDigits(lat_double));//rounds latitude to four digits for data storage use
        String lon_string = String.valueOf(arithmeticCalculations.roundToFourDigits(lon_double));//rounds longitude to four digits for data storage use
        String address = getAddressInfo().get(0).getAddressLine(0); //0 to obtain first possible address
        int count = 0;
        for (MarkerModel value : markerRepository.getMarkers()) {
            //checks if saved marker position is same as current location and deletes the marker since the getcurrentlocation() function will handle it
            if (value.getMarkerLatitude().equals(String.valueOf(arithmeticCalculations.roundToFourDigits(currentLocation.getLatitude()))) &&
                    value.getMarkerLongitude().equals(String.valueOf(arithmeticCalculations.roundToFourDigits(currentLocation.getLongitude())))) {

                // Todo add a warning before you replace snippet, check to see if snippet is same as message;

                //removes saved marker
                try {
                    markerRepository.deleteMarker(value);

                } catch (Exception e) {

                    Toast.makeText(MapsActivity.this, " error in deleting saved marker :" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }
        try {
            MarkerModel markerModel = new MarkerModel(address, currentLocation.toString(), lat_string,
                    lon_string, message, created.toString(), lastUpdated.toString(), false);

            markerRepository.addMarker(markerModel);
            Toast.makeText(MapsActivity.this, "new marker inserted=", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(MapsActivity.this, " error in creating marker :" + e, Toast.LENGTH_SHORT).show();
        }

        message_added = true;
        location_refreshed = false;
        message_location_lat = currentLocation.getLatitude();//to check if location has changed


    }//end of addmarker


    public void getMarkerTitle(Location location) {
        markerOptions = new MarkerOptions();
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        markerOptions.position(latLng);
        markerOptions.title(getAddressInfo().get(0).getAddressLine(0));

    }

    public List<Address>getAddressInfo(){
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> addresses = null; //1 num of possible location returne
        try {
            addresses = geocoder.getFromLocation(markerOptions.getPosition().latitude,
                    markerOptions.getPosition().longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    //   public List<Address> getAddressInfo(MarkerOptions markerOptions) {
//       Geocoder geocoder = new Geocoder(GetAddress.this);
//       // Geocoder geocoder= clone();
//        List<Address> addresses =null; //1 num of possible location returne
//        try {
//            addresses = geocoder.getFromLocation(markerOptions.getPosition().latitude,
//                    markerOptions.getPosition().longitude, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//       return addresses;
//   }

}



