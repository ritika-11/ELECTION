package com.example.stark.elections;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class official extends AppCompatActivity {


    EditText edt1,edt2,edt3,edt6,edt7,edt8;

    DatabaseReference myRef1,myRef2,myRef3 ;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official);


        Toolbar myToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing....");
        progressDialog.setCanceledOnTouchOutside(false);

        edt1 = findViewById(R.id.ed);
        edt2 = findViewById(R.id.rd);
        edt3 = findViewById(R.id.ad);
        edt6 = findViewById(R.id.pp);
        edt7 = findViewById(R.id.pm);
        edt8 = findViewById(R.id.num);

    }

    public void update(View v) {

        myRef1 = FirebaseDatabase.getInstance().getReference("Dates");

        String s1 = edt1.getText().toString();
        String s2 = edt2.getText().toString();

        myRef1.child("Election date").setValue(s1);
        myRef1.child("Result date").setValue(s2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(official.this,"Successfully Updated",Toast.LENGTH_LONG).show();
                edt1.setText("");
                edt2.setText("");
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(official.this,"Updated Failed",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });



    }

    public void addvenue(View vi) {

        myRef2 = FirebaseDatabase.getInstance().getReference("Venues");

        String s3 = edt3.getText().toString();

        myRef2.push().setValue(s3);

    }

    public void upload(View vie) {

        myRef3 = FirebaseDatabase.getInstance().getReference("Results");

        String s6 = edt6.getText().toString();
        String s7 = edt7.getText().toString();
        String s8 = edt8.getText().toString();

        myRef3.child("Political party won").setValue(s6);
        myRef3.child("Prime minister").setValue(s7);
        myRef3.child("Constituencies won").setValue(s8);
    }

    public void logoutt() {
        SharedPreferences sharedPreference = official.this
                .getSharedPreferences("authentication",Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreference.edit();
        editor.putBoolean("official",false);
        editor.apply();
        Intent i = new Intent(this,officialloginactivity.class);
        startActivity(i);
        finish();
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
                logoutt();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

