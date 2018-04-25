package com.example.csgroupg.bilvend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class loginPage extends AppCompatActivity {

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    // constants
    static final String CHAT_PREFS = "ChatPrefs";
    static final String DISPLAY_NAME_KEY = "username";

    // properties
    TextView signUp;
    EditText email;
    EditText password;
    FirebaseAuth mAuth;
    CardView login;
    ProgressDialog dialog;
    TextView noRegistration;
    DatabaseReference mDatabaseReference;

    // program code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.cardView2);
        signUp = findViewById(R.id.signup);
        email = findViewById(R.id.Email);
        noRegistration = findViewById(R.id.noRegistration);
        password = findViewById(R.id.password);
        dialog = new ProgressDialog(loginPage.this);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        noRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anonymousEntrance();
                startActivity(new Intent(getApplicationContext(),mainPage.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), registerPage.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                if(email.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter e-mail address", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                    dialog.cancel();
                    return;
                }
                if(password.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                    dialog.cancel();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    saveDisplayName(user);
                                    startActivity(new Intent(getApplicationContext(), mainPage.class));
                                }
                                else {
                                    dialog.cancel();
                                    Toast.makeText(loginPage.this, "Login failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void saveDisplayName(FirebaseUser user) {
        String current_user_id = user.getUid();
        String displayName = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user_id).child("Username").getKey();
        SharedPreferences prefs = getSharedPreferences(CHAT_PREFS, 0);
        prefs.edit().putString(DISPLAY_NAME_KEY, displayName).apply();
    }
    public void anonymousEntrance()
    {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
