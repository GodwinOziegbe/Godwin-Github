package demo.codingbusiness.tripmizerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AddEditOneActivity extends AppCompatActivity {

    Button btn_ok, btn_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_one);

        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);


        btn_ok.setOnClickListener(view -> {
            Intent intent = new Intent(this, RouteCustomerListActivity.class);
            startActivity(intent);
        });


        btn_cancel.setOnClickListener(view -> {
            Intent myIntent = new Intent(AddEditOneActivity.this, RouteActivity.class);
            startActivity(myIntent);
        });
    }
}