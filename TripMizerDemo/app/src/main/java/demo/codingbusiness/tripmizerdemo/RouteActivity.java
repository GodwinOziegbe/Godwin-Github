package demo.codingbusiness.tripmizerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class RouteActivity extends AppCompatActivity {

    Button btn_route_page;
    private static final String TAG = "TripMizer App-Routes";
    MyApplication myApplication = (MyApplication) this.getApplication();
    private static List<Route> routeList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        btn_route_page = findViewById(R.id.btn_route_cust_list);

       routeList= myApplication.getRouteList();

        Log.d(TAG, "onCreate: " + routeList.toString());

        Toast.makeText(this,"Route count = "+ routeList.size(), Toast.LENGTH_SHORT).show();


        btn_route_page.setOnClickListener(view -> {
            Intent intent = new Intent(this, RouteCustomerListActivity.class);
            startActivity(intent);

        });

        recyclerView = findViewById(R.id.rv_route_list);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RouteViewAdapter(routeList, RouteActivity.this);
        recyclerView.setAdapter(mAdapter);
    }
}