package ya.examensarbete.simplegpsapplication.MarkerServices;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ya.examensarbete.simplegpsapplication.Models.MarkerModel;
import ya.examensarbete.simplegpsapplication.UserInterface.MapsActivity;
import ya.examensarbete.simplegpsapplication.Repository.MarkerRepository;
import ya.examensarbete.simplegpsapplication.Utilities.ArithmeticCalculations;

public  class GetMarkers extends MapsActivity {
//


//GetMarkers getMarkers=new GetMarkers();

    MarkerOptions markerOptions;
    public void getAllMarkers(GoogleMap mMap,MarkerRepository markerRepository,Location location) {
       ArithmeticCalculations arithmeticCalculations = new ArithmeticCalculations();
         LatLng latLng;

        for (MarkerModel markerModel : markerRepository.getMarkers()) {
            //Here we will like to skip the current iteration if it is the current location because there is another function to handle current location ( the getCurrentPosition() function)
            if (markerModel.getMarkerLatitude().equals(String.valueOf(arithmeticCalculations.roundToFourDigits(location.getLatitude())))) {
                continue;
            } else {


                Double difference_in_longitude =
                        arithmeticCalculations.roundToFourDigits(Math.abs((Double.parseDouble(markerModel.getMarkerLongitude())) - arithmeticCalculations.roundToFourDigits(location.getLongitude())) * 10000);


                if (difference_in_longitude <= 400) {
                 // Toast.makeText(GetMarkers.this, " marker close, approx.:" + difference_in_longitude + " meters", Toast.LENGTH_LONG).show();
                   markerOptions = new MarkerOptions();

                  latLng = new LatLng(Double.parseDouble(markerModel.getMarkerLatitude()), Double.parseDouble(markerModel.getMarkerLongitude()));
                   markerOptions.position(latLng);
                   markerOptions.title(markerModel.getMarkerAddress());
                    markerOptions.snippet(markerModel.getSnippetTag().toUpperCase());
                    mMap.addMarker(markerOptions);//makes the saved marker red when clicked.
                    mMap.addMarker(markerOptions).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                }



            }


        }

    }

    //************Function to retrieve all saved markers ****************
    //Gets all markers from database
//    private void getAllMarkers(Location location) {
//        // todo add the markers just like how you did the first time
//        arithmeticCalculations = new ArithmeticCalculations();
//        MarkerRepository markerRepository = new MarkerRepository(MapsActivity.this);
//        for (MarkerModel markerModel : markerRepository.getMarkers()) {
//            //Here we will like to skip the current iteration if it is the current location because there is another function to handle current location ( the getCurrentPosition() function)
//            if (markerModel.getMarkerLatitude().equals(String.valueOf(arithmeticCalculations.roundToFourDigits(location.getLatitude())))) {
//                continue;
//            } else {
//
//
//                Double difference_in_longitude =
//                        arithmeticCalculations.roundToFourDigits(Math.abs((Double.parseDouble(markerModel.getMarkerLongitude())) - arithmeticCalculations.roundToFourDigits(location.getLongitude())) * 10000);
//
//
//                if (difference_in_longitude <= 400) {
//                    Toast.makeText(MapsActivity.this, " marker close, approx.:" + difference_in_longitude + " meters", Toast.LENGTH_LONG).show();
//                    markerOptions = new MarkerOptions();
//                    latLng = new LatLng(Double.parseDouble(markerModel.getMarkerLatitude()), Double.parseDouble(markerModel.getMarkerLongitude()));
//                    markerOptions.position(latLng);
//                    String address = getAddressInfo().get(0).getAddressLine(0); //0 to obtain first possible address
//                    String title = address;
//                    markerOptions.title(title);
//                    markerOptions.snippet(markerModel.getSnippetTag().toUpperCase());
//                    mMap.addMarker(markerOptions);//makes the saved marker red when clicked.
//                    mMap.addMarker(markerOptions).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
//                }
//
//            }
//
//
//        }
//
//    }//end of getAllmarkers()
}
