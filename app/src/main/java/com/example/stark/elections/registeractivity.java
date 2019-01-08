package com.example.stark.elections;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    int duration = Toast.LENGTH_LONG;
    CharSequence txt = "All the fields are compulsory";

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

        setupFloatingLabelError();

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

        if(s1.isEmpty()||s2.isEmpty()||s3.isEmpty()||s.isEmpty()||s5.isEmpty()||s6.isEmpty()||s7.isEmpty())
        {
            Toast t = Toast.makeText(getApplicationContext(), txt, duration);
            t.show();
        }
        else {
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


            FirebaseMessaging.getInstance().subscribeToTopic("Dates");

            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (task.isSuccessful()) {
                                String token = task.getResult().getToken();
                                savetoken(token);
                            }
                        }
                    });
        }

    }

    public void doit(View vie) {
        Intent i = new Intent(this,voterloginactivity.class);
        startActivity(i);
    }

    public void savetoken(String s) {
        String s1 = edt1.getText().toString();

        myRef.child(s1).child("Token").setValue(s);
    }

    private void setupFloatingLabelError() {
        final TextInputLayout floatingUsernameLabel =  findViewById(R.id.username_text_input_layout);
        floatingUsernameLabel.getEditText().addTextChangedListener(new TextWatcher() {
            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() != 12) {
                    floatingUsernameLabel.setError(getString(R.string.username_required));
                    floatingUsernameLabel.setErrorEnabled(true);
                } else {
                    floatingUsernameLabel.setErrorEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
