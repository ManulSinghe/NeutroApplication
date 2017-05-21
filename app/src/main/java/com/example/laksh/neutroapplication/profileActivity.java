package com.example.laksh.neutroapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuthentication;
    private TextView txtUserEmail;
    private EditText Height,Weight,Age;
    private Button btnLogout, btnSave, btnNext;
    public ProgressDialog progressDialog;

    DatabaseReference databasereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuthentication = FirebaseAuth.getInstance();
        databasereference = FirebaseDatabase.getInstance().getReference("Details");

        if (firebaseAuthentication.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuthentication.getCurrentUser();

        txtUserEmail = (TextView) findViewById(R.id.textViewuseremail);
        txtUserEmail.setText(" WELCOME ! " +user.getEmail());

        Height= (EditText)findViewById(R.id.Height);
        Weight = (EditText)findViewById(R.id.weight);
        Age = (EditText)findViewById(R.id.Age);




        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnNext = (Button)findViewById(R.id.btnNext);

        btnLogout.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {



        if ( v == btnLogout){

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Signing out ....");
            progressDialog.show();
            firebaseAuthentication.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }

        if(v == btnSave){
            savedata();
            Toast.makeText(profileActivity.this, " Data Has been Saved ! ", Toast.LENGTH_SHORT).show();

        }

        if(v == btnNext){
            startActivity(new Intent(this, Calculate.class));
    }

    }

    public void savedata(){

        String Hi8=Height.getText().toString();
        String Wei8=Weight.getText().toString();
        String Ag=Age.getText().toString();

        double height = Double.parseDouble(Hi8);
        double weight = Double.parseDouble(Wei8);
        double age = Double.parseDouble(Ag);

        FirebaseUser id = firebaseAuthentication.getCurrentUser();

      SaveDetails details =new SaveDetails(height,weight,age);

        databasereference.child(id.getUid()).setValue(details);

    }


}
