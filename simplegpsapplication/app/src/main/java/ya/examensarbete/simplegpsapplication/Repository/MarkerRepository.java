package ya.examensarbete.simplegpsapplication.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import ya.examensarbete.simplegpsapplication.Models.MarkerModel;

public class MarkerRepository extends SQLiteOpenHelper {

    public static final String COLUMN_MARKER_TABLE = "MARKER_TABLE";
    public static final String COLUMN_MARKER_ID = "MARKER_ID";
    public static final String COLUMN_ADDRESS = "MARKER_ADDRESS";
    public static final String COLUMN_LOCATION = "MARKER_LOCATION";
    public static final String COLUMN_LATITUDE = "MARKER_LATITUDE";
    public static final String COLUMN_LONGITUDE = "MARKER_LONGITUDE";
    public static final String COLUMN_SNIPPET = "SNIPPET";
    public static final String COLUMN_LAST_UPDATED = "LAST_UPDATED";
    public static final String COLUMN_CREATED = "CREATED";
    public static final String COLUMN_IS_ACTIVE = "IS_ACTIVE";

    public MarkerRepository(@Nullable Context context) {
        super(context, "marker.db", null, 1);
    }

    private List<MarkerModel> markerModelList;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = " CREATE TABLE " + COLUMN_MARKER_TABLE + " ( " + COLUMN_MARKER_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "  + COLUMN_ADDRESS + " TEXT, "  + COLUMN_LOCATION +
                " TEXT, " + COLUMN_LATITUDE + " TEXT, " + COLUMN_LONGITUDE + " TEXT," + COLUMN_SNIPPET + " TEXT, " + COLUMN_CREATED + " TEXT,"
                 + COLUMN_LAST_UPDATED + " TEXT, " + COLUMN_IS_ACTIVE + " BOOL ) ";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //adding marker
    public boolean addMarker(MarkerModel markerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_ADDRESS, markerModel.getMarkerAddress());
        cv.put(COLUMN_LOCATION, markerModel.getMarkerLocation());
        cv.put(COLUMN_LATITUDE, markerModel.getMarkerLatitude());
        cv.put(COLUMN_LONGITUDE, markerModel.getMarkerLongitude());
        cv.put(COLUMN_SNIPPET, markerModel.getSnippetTag());
        cv.put(COLUMN_CREATED, markerModel.getCreated());
        cv.put(COLUMN_LAST_UPDATED, markerModel.getLastUpdated());
        cv.put(COLUMN_IS_ACTIVE, markerModel.isActive());

        long insert = db.insert(COLUMN_MARKER_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<MarkerModel> getMarkers() {
        markerModelList = new ArrayList<>();
        String queryString = " SELECT * FROM " + COLUMN_MARKER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int markerID = cursor.getInt(0);
                String markerAddress = cursor.getString(1);
                String markerLocation = cursor.getString(2);
                String markerLatitude = cursor.getString(3);
                String markerLongitude = cursor.getString(4);
                String snippetTag = cursor.getString(5);
                String createdDateTime = cursor.getString(6);
                String lastUpdated = cursor.getString(7);
                boolean markerActive = cursor.getInt(8) == 1 ? true : false;

                MarkerModel markerModel = new MarkerModel(markerID,markerAddress,markerLocation,markerLatitude,markerLongitude,
                        snippetTag,createdDateTime,lastUpdated,markerActive);
                markerModelList.add(markerModel);
            } while (cursor.moveToNext());
        } else {
            //failure do not add anything

        }
        //close db and cursor
        cursor.close();
        db.close();
        return markerModelList;
    }


    public boolean deleteMarker(MarkerModel markerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + COLUMN_MARKER_TABLE + " WHERE " + COLUMN_MARKER_ID + " = " + markerModel.getMarkerID();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return true;
        }
    }

}