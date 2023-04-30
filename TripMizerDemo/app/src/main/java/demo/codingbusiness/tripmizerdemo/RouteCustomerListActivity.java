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

public class RouteCustomerListActivity extends AppCompatActivity {
    Button btn_addOne;
    private static final String TAG = "TripMizer App";
    MyApplication myApplication = (MyApplication) this.getApplication();
    private static List<Customer> customerList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_customer_list);

        btn_addOne = findViewById(R.id.btn_add_customer);

        customerList= myApplication.getCustomerList();

        Log.d(TAG, "onCreate: " + customerList.toString());

        Toast.makeText(this,"Customer count = "+ customerList.size(), Toast.LENGTH_SHORT).show();


        btn_addOne.setOnClickListener(view -> {
            Intent intent = new Intent(RouteCustomerListActivity.this, AddEditOneActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.rv_route_customer_list);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RouteCustomerListViewAdapter(customerList, RouteCustomerListActivity.this);
        recyclerView.setAdapter(mAdapter);

    }


    private static int nextItem=0;

}