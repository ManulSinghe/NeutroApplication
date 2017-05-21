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

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText txtEmail, txtPassword;
    private TextView txtView;
    private ProgressDialog progressDialog;
     private FirebaseAuth firebaseAuthentication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuthentication = FirebaseAuth.getInstance();
        txtEmail = (EditText)findViewById(R.id.editEmail);
        txtPassword = (EditText)findViewById(R.id.editPassword);
        buttonSignIn = (Button)findViewById(R.id.btnSignin);
        txtView = (TextView)findViewById(R.id.txtViewSignin);
        progressDialog = new ProgressDialog(this);


        buttonSignIn.setOnClickListener(this);
        txtView.setOnClickListener(this);

        if(firebaseAuthentication.getCurrentUser()!=null){
            //profile activity
            finish();
            startActivity(new Intent(getApplicationContext(),profileActivity.class));
        }

    }
    private void userLogin(){
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, " Please enter Email ", Toast.LENGTH_LONG).show();
            //stop the function
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, " Please enter Password ", Toast.LENGTH_LONG).show();
            //stop the function
            return;
        }
        progressDialog.setMessage(" Sigining....");
        progressDialog.show();
        firebaseAuthentication.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                             progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //start profile
                            finish();
                            startActivity(new Intent(getApplicationContext(),profileActivity.class));

                        }
                    }
                });

    }



    @Override
    public void onClick(View v) {
        if (v == buttonSignIn){
            userLogin();

        }
        if (v == txtView){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
