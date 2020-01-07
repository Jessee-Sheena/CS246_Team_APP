package com.team10.lifeorientedlist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Register User
 * <p> handles the registraiotn of the user, stores data in shared preferences and database</p>
 * @author Sheena Jessee
 * @version 1.0
 */

public class Register extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "REGISTER";
    Button register;
    EditText userPassword;
    EditText name;
    EditText userEmail;
    ManageData manageData;
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.edit_name);
        userEmail = (EditText) findViewById(R.id.edit_email2);
        userPassword = (EditText) findViewById(R.id.edit_password2);
        register = (Button) findViewById(R.id.edit_register);
        register.setOnClickListener(this);
        manageData = new ManageData(this);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    /**
     * Register User
     * <p>retrieves the data from the view and sets them to the database and shared preferences</p>
     */
    public void registerUser () {
        //get the values from the user
        final String new_name = name.getText().toString();
        final String new_email = userEmail.getText().toString();
        final String new_password = userPassword.getText().toString();

        auth.createUserWithEmailAndPassword(new_email, new_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //create a user
                            User user = new User(new_name, new_email);
                            //create a map to put the values in the database
                            Map<String, Object> data = new HashMap<>();
                            data.put("name", new_name);
                            data.put("email", new_email);


                            db.collection("user").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .set(data)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error writing document", e);
                                        }
                                    });
                            manageData.storeUser(user);
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_register:

                registerUser();
                startActivity(new Intent(this, Login.class));
                break;


        }
    }
}
