package com.example.stark.elections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreference = MainActivity.this
                .getSharedPreferences("authentication",Context.MODE_PRIVATE);
        if (sharedPreference.getBoolean("voter",false)){
            Intent i = new Intent(this, user.class);
            i.putExtra("Name","Welcome "+sharedPreference.getString("name","NA"));
            startActivity(i);
            finish();
        }
        else if (sharedPreference.getBoolean("official",false)){
            startActivity(new Intent(this, official.class));
            finish();}

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
