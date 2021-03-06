package com.example.csgroupg.bilvend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class registerPage extends AppCompatActivity {
    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText password;
    private  EditText repassword;
    private CardView register;
    private FirebaseAuth mAuth;
    private User user;
    private ProgressDialog dialog;
    private long tStart;
    private long tEnd;
    private boolean verified;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.title);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.re_enter_password);
        register = findViewById(R.id.cardView);
        dialog = new ProgressDialog(registerPage.this);
        verified = true;
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                createAccount();
            }
        });
    }
    public void createAccount()
    {
        ArrayList<EditText> list = new ArrayList<EditText>();
        list.add(name);
        list.add(surname);
        list.add(email);
        list.add(password);
        list.add(repassword);
        while (!checkFilled(list))
        {
            register.setEnabled(true);
            return;
        }
        user = new User(name.getText().toString(), surname.getText().toString(), email.getText().toString());
        mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            user.saveToFirebase(mAuth.getCurrentUser());
                            Toast.makeText(getApplicationContext(), "Registered Succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), mainPage.class));
                        }
                        else
                        {
                            Toast.makeText(registerPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

    }
    public boolean checkFilled( ArrayList<EditText> list)
    {
        for(EditText e : list)
        {
            if(e.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Please fully enter required information", Toast.LENGTH_SHORT).show();
                e.requestFocus();
                return false;
            }
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {
            Toast.makeText(getApplicationContext(), "Please enter a valid e-mail", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return false;
        }
        if ( !email.getText().toString().substring(email.getText().toString().length() - 14, email.getText().toString().length()).equals("bilkent.edu.tr"))
        {
            Toast.makeText(getApplicationContext(), "Please enter your Bilkent e-mail address", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return false;
        }
        mAuth.fetchProvidersForEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                        boolean check = task.getResult().getProviders().isEmpty();
                        if (!check)
                        {
                            Toast.makeText(getApplicationContext(), "Already registered with this e-mail", Toast.LENGTH_SHORT).show();
                            email.requestFocus();
                            return;
                        }
                    }
                });
        return true;
    }
}
