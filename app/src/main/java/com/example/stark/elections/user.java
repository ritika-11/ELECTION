package com.example.stark.elections;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class user extends AppCompatActivity {

    private DatabaseReference rootref1,rootref2,rootref3;
    TextView x;
    private ListView z1,z2,z3;

    private ArrayList<String> important1 = new ArrayList<>();
    private ArrayList<String> important2 = new ArrayList<>();
    private ArrayList<String> important3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("Name");

        x = findViewById(R.id.tt);
        x.setText(message);

        z1 = findViewById(R.id.tt1);
        z2 = findViewById(R.id.tt2);
        z3 = findViewById(R.id.tt3);

        rootref1 = FirebaseDatabase.getInstance().getReference("Dates");
        final ArrayAdapter aa1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, important1);

        z1.setAdapter(aa1);

        ChildEventListener childEventListener1 = rootref1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String key = dataSnapshot.getKey();
                String val = dataSnapshot.getValue(String.class);
                String net = key+" : "+val;
                important1.add(net);
                aa1.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        rootref3 = FirebaseDatabase.getInstance().getReference("Results");
        final ArrayAdapter aa3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, important3);

        z3.setAdapter(aa3);

        ChildEventListener childEventListener = rootref3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String key = dataSnapshot.getKey();
                String val = dataSnapshot.getValue(String.class);
                String net = key+" : "+val;
                important3.add(net);
                aa3.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rootref2 = FirebaseDatabase.getInstance().getReference("Venues");
        final ArrayAdapter aa2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, important2);

        z2.setAdapter(aa2);

        ChildEventListener childEventListener2 = rootref2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String val = dataSnapshot.getValue(String.class);
                important2.add(val);
                aa2.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    public void logout() {
        Intent i = new Intent(this,voterloginactivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action2:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
