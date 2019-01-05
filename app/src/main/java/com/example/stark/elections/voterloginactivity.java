package com.example.stark.elections;

import android.content.Context;
import android.content.Intent;
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

import java.util.Objects;

public class voterloginactivity extends AppCompatActivity {

    EditText ed1, ed2;
    CharSequence text = "You are not registered. Kindly register first.";
    int duration = Toast.LENGTH_LONG;
    CharSequence txt = "Wrong password. Try again";
    CharSequence t = "wow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voterloginactivity);

        ed1 = findViewById(R.id.password);
        ed2 = findViewById(R.id.aadhar);

    }

    public void perform_action(View view) {
        Intent i3 = new Intent(this, registeractivity.class);
        startActivity(i3);

    }

    public void verification(final View vi) {

        final String st1 = ed1.getText().toString();
        String st2 = ed2.getText().toString();

        FirebaseDatabase base = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = base.getReference("Users/"+st2+"/Password");


        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                String x = snapshot.getValue().toString();
                    if(st1.equals(x)) {

                        usingintent(vi);

                    }

                    else {
                        Toast t = Toast.makeText(getApplicationContext(), txt, duration);
                    t.show(); }

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }

        });
    }

    public void usingintent(View v1) {

        String st2 = ed2.getText().toString();

        FirebaseDatabase base = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = base.getReference("Users/" + st2 + "/Name");

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String q = snapshot.getValue().toString();
                blah(q);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }

        });
    }
    public void blah(String d) {

        Intent i = new Intent(this, user.class);
        i.putExtra("Name","Welcome "+d);
        startActivity(i);
    }
}