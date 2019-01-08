package com.example.stark.elections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class officialloginactivity extends AppCompatActivity {

    EditText e;
    int duration = Toast.LENGTH_LONG;
    CharSequence txt = "Wrong password. Try again";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officialloginactivity);

        e = findViewById(R.id.password);
    }

    public void dosmthg(View v) {
        SharedPreferences sharedPreference = officialloginactivity.this
                .getSharedPreferences("authentication",Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreference.edit();
        editor.putBoolean("official",true);
        editor.apply();
        Intent in = new Intent(this,official.class);
        startActivity(in);
        finishAffinity();
        finish();
    }

    public void checkin(final View view) {

        final String x = e.getText().toString();

        FirebaseDatabase base = FirebaseDatabase.getInstance();
        DatabaseReference root = base.getReference("officialpassword");


        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                String y = datasnapshot.getValue().toString();

                if(x.equals(y)) {
                    dosmthg(view);
                }
                else {
                    Toast t = Toast.makeText(getApplicationContext(), txt, duration);
                    t.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }

        });
    }
}
