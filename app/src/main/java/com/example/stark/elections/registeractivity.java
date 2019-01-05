package com.example.stark.elections;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class registeractivity extends AppCompatActivity {

    EditText edt1,edt2,edt3,edt4,edt5,edt6,edt7;
    private static final String TAG = "register";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);


        edt1 = findViewById(R.id.aadhar);
        edt2 = findViewById(R.id.name);
        edt3 = findViewById(R.id.contact);
        edt4 = findViewById(R.id.age);
        edt5 = findViewById(R.id.city);
        edt6 = findViewById(R.id.state);
        edt7 = findViewById(R.id.password);

    }



    public void collectdata(View v) {

        String s1 = edt1.getText().toString();
        String s2 = edt2.getText().toString();
        String s3 = edt3.getText().toString();
        String s = edt4.getText().toString();
        long s4 = Long.parseLong(s);
        String s5 = edt5.getText().toString();
        String s6 = edt6.getText().toString();
        String s7 = edt7.getText().toString();

//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "getInstanceId failed", task.getException());
//                            return;
//                        }
//
//                        // Get new Instance ID token
//                        String token = task.getResult().getToken();
//
//                            myRef.child(s1).child("Token").setValue(token);
//
//                    }
//                });



        myRef.child(s1).child("Name").setValue(s2);
        myRef.child(s1).child("Contact").setValue(s3);
        myRef.child(s1).child("Age").setValue(s4);
        myRef.child(s1).child("District").setValue(s5);
        myRef.child(s1).child("State").setValue(s6);
        myRef.child(s1).child("Password").setValue(s7);


        Snackbar.make(findViewById(R.id.myCoordinatorLayout), "You have been registered.",
                Snackbar.LENGTH_LONG)
                .setAction(" LOGIN ", new View.OnClickListener() {
                    @Override
                    public void onClick(View vi) {
                         doit(vi);
                    }
                })
                .show();



        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                           msg = "Subscribe_Failed";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(registeractivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });



    }

    public void doit(View vie) {
        Intent i = new Intent(this,voterloginactivity.class);
        startActivity(i);
    }
}
