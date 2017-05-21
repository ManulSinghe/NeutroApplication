package com.example.laksh.neutroapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText txtEmail, txtPassword;
    private TextView viewLogin;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuthentication = FirebaseAuth.getInstance();
        if (firebaseAuthentication.getCurrentUser() != null) {
            //profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        progressDialog = new ProgressDialog(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        txtEmail = (EditText) findViewById(R.id.editEmail);
        txtPassword = (EditText) findViewById(R.id.editPassword);
        viewLogin = (TextView) findViewById(R.id.txtViewLogin);

        btnRegister.setOnClickListener(this);
        viewLogin.setOnClickListener(this);
    }

    private void registerUser() {
        String uEmail = txtEmail.getText().toString().trim();
        String uPassword = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(uEmail)) {
            Toast.makeText(this, " Please enter Email ", Toast.LENGTH_LONG).show();
            //stop the function
            return;
        }
        if (TextUtils.isEmpty(uPassword)) {
            Toast.makeText(this, " Please enter Password ", Toast.LENGTH_LONG).show();
            //stop the function
            return;
        }
        progressDialog.setMessage(" Registering User....");
        progressDialog.show();
        firebaseAuthentication.createUserWithEmailAndPassword(uEmail, uPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user successfully registerd
                            //profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), profileActivity.class));
//                           Toast.makeText(MainActivity.this, " Registration Successful ! ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, " Failed ! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            registerUser();

        }
        if (v == viewLogin) {
            //sign in
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
