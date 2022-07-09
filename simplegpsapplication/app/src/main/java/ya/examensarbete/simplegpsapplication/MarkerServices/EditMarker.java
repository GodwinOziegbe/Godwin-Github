package ya.examensarbete.simplegpsapplication.MarkerServices;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.Marker;

import androidx.fragment.app.DialogFragment;
import ya.examensarbete.simplegpsapplication.R;
import ya.examensarbete.simplegpsapplication.UserInterface.MapsActivity;

public class EditMarker extends DialogFragment {
    private static final String MARKER = "title";
 String message1;
   // private static final int MARKER1 = 1;
Marker marker;
    public static EditMarker newInstance(String title) {
        EditMarker frag = new EditMarker();
        Bundle args = new Bundle();
        args.putString(MARKER, title);
        frag.setArguments(args);
        return frag;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_marks, null);
      //  final String markerTitle= getArguments().getString(title);
        final EditText etTitle= dialogView.findViewById(R.id.title);
        final Button accidentButton= dialogView.findViewById(R.id.accident);
        final Button roadClosedButton= dialogView.findViewById(R.id.roadClosed);
        final Button roadConstructionButton= dialogView.findViewById(R.id.roadCon);
        final Button heavyTrafficButton= dialogView.findViewById(R.id.heavyTraff);

        final AlertDialog dialogAlert= new AlertDialog.Builder(getActivity())
                .setTitle("Change marker's title")
                .setView(dialogView)
                .setPositiveButton("Ok",null)
                .setNegativeButton("Cancel",null)
                .create();

        dialogAlert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positive=dialogAlert.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negative=dialogAlert.getButton(AlertDialog.BUTTON_NEGATIVE);

                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ((MapsActivity)getActivity()).addMarker(etTitle.getText(),message1);

                        dialogAlert.dismiss();
                    }
                });
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogAlert.dismiss();
                    }
                });

                accidentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        message1 ="Accident!";
                    }
                });
                roadClosedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        message1 ="Road Closed!";
                    }
                });
                roadConstructionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        message1 ="Road Under Construction!";
                    }
                });
                heavyTrafficButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        message1 ="Heavy Traffic!";
                    }
                });

            }
        });
        return dialogAlert;
    }
}
