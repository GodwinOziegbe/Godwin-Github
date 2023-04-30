package demo.codingbusiness.tripmizerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_route;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_route = findViewById(R.id.btn_route);




        btn_route.setOnClickListener(view -> {
           Intent intent = new Intent(this, RouteActivity.class);
           startActivity(intent);

        });
    }
}