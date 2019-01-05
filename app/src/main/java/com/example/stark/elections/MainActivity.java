package com.example.stark.elections;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PushNotifications.start(getApplicationContext(), "d81f88c6-6275-4373-8577-8739273f1fca");
        //PushNotifications.subscribe("hello");
    }


    public void officialauth(View view) {
        Intent i1 = new Intent(this,officialloginactivity.class);
        startActivity(i1);

    }

    public void voterauth(View view) {
        Intent i2 = new Intent(this,voterloginactivity.class);
        startActivity(i2);

    }
}
