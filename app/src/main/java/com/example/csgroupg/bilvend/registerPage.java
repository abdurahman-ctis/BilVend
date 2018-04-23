package com.example.csgroupg.bilvend;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.re_enter_password);
        register = findViewById(R.id.cardView);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        user = new User(name.getText().toString(), surname.getText().toString(), email.toString().substring(0, 14));
        for(EditText e : list)
        {
            if(e.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Pleas fully enter required information", Toast.LENGTH_SHORT).show();
                e.requestFocus();
                return;
            }
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {
            Toast.makeText(getApplicationContext(), "Please enter a valid e-mail", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return;
        }
//        if ( !email.toString().substring(email.toString().length() - 14, email.toString().length()).equals("bilkent.edu.tr"))
//        {
//            Toast.makeText(getApplicationContext(), "Please enter your Bilkent e-mail address", Toast.LENGTH_SHORT).show();
//            email.requestFocus();
//            return;
//        }
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
        mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            String current_user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user_id);
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            Map newPost = new HashMap();
                            newPost.put("Name", user.getName());
                            newPost.put("Surname", user.getSurname());
                            newPost.put("Username", user.getUsername());
                            databaseReference.setValue(newPost);
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
}
