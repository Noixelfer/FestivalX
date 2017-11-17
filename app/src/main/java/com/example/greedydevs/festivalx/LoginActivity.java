package com.example.greedydevs.festivalx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailField = (EditText) findViewById(R.id.email);
        final EditText passwordField = (EditText) findViewById(R.id.password);
        final Button bLogin = (Button) findViewById(R.id.email_sign_in_button);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startSignIn(emailField.getText().toString(), passwordField.getText().toString());
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }

    private void startSignIn(final String email, final String password)
    {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful())
                {
                    mAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<ProviderQueryResult> task1) {
                            if (!task1.getResult().getProviders().isEmpty())
                                Toast.makeText(LoginActivity.this, "Username and password do not match", Toast.LENGTH_LONG).show();
                            else
                                createAcc(email, password);
                        }
                    });
                }
                else
                {
                   // Intent i = new Intent(LoginActivity.this, MainActivity.class);
                   // i.putExtra("username", email);
                   // startActivity(i);
                    Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createAcc(final String email, final String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful())
            {
                Toast.makeText(LoginActivity.this, "A new account was created", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(LoginActivity.this, "The account can not be created", Toast.LENGTH_LONG).show();
            }
        }
    });
    }

}




