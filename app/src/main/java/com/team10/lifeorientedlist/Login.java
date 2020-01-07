package com.team10.lifeorientedlist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;

/**
 * Allows the User to login
 * <p> Set the user experience in shared preferences and the database</p>
 * @author Sheena Jessee
 * @version 1.0
 */

public class Login extends AppCompatActivity implements View.OnClickListener{
    private String TAG = "LOGIN";
    Button login;
    EditText userEmail;
    EditText userPassword;
    TextView registerLink;
    ManageData manageData;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail = (EditText) findViewById(R.id.edit_email);
        userPassword = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.edit_login);
        login.setOnClickListener(this);
        registerLink = (TextView) findViewById(R.id.register_now);
        registerLink.setOnClickListener(this);
        manageData = new ManageData(this);
        auth = FirebaseAuth.getInstance();
    }

    /**
     * Validates User
     * <p>Checks the database for the users email and password If there is a valid user the MainActivity is started</p>
     */
    public void validateUser() {
        String user_email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if (user_email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Login failed! Please try again!", Toast.LENGTH_LONG).show();
            return;
        }

        auth.signInWithEmailAndPassword(user_email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            manageData.setLoggedIn(true);
                            String mUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            manageData.setUserID(mUid);

                            startActivity(new Intent(Login.this, MainActivity.class));
                        } else {

                            Toast.makeText(getApplicationContext(), "Login failed! Please try again!", Toast.LENGTH_LONG).show();
                        }

                    }
                });



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.edit_login:
                validateUser();
                break;
            case R.id.register_now:
                startActivity(new Intent(this, Register.class));
                break;
        }

    }
}
