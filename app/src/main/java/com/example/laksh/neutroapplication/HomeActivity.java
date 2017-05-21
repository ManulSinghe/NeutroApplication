package com.example.laksh.neutroapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import static com.example.laksh.neutroapplication.R.id.txtUserEmail;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuthentication;
    private TextView txtViewUserName;
    private Button btncalc,btnstats,btnexit,btnprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        txtViewUserName = (TextView)findViewById(R.id.textViewUsername);
        btncalc = (Button)findViewById(R.id.btnToCalculatePage);
        btnprofile  = (Button)findViewById(R.id.btnToProfilePage);
        btnstats = (Button)findViewById(R.id.btnToStatisticsPage);
        btnexit = (Button)findViewById(R.id.btnExit);

        firebaseAuthentication = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuthentication.getCurrentUser();

        if (firebaseAuthentication.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        txtViewUserName.setText(user.getEmail());

        btnexit.setOnClickListener(this);
        btnstats.setOnClickListener(this);
        btnprofile.setOnClickListener(this);
        btncalc.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if ( v == btncalc){

            startActivity(new Intent(this, Calculate.class));

        }

        if ( v == btnprofile){

            startActivity(new Intent(this, profileActivity.class));

        }

        if ( v == btnstats){

            startActivity(new Intent(this, stats.class));

        }

        if ( v == btnexit){
           System.exit(0);

        }

    }
}
