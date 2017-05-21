package com.example.laksh.neutroapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.AdapterView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Calculate extends AppCompatActivity implements View.OnClickListener{
    Spinner spinner;
    ArrayAdapter<CharSequence>adapter;
    DatabaseReference databasecal1,databasecal2,databaseconsumption;
    private Button btnCalOne , btnCalTwo ,btnSavedata,btnOk;
    private FirebaseAuth firebaseAuthentication;
    private TextView txtUserEmail,txtcalories1,txtcalories2,txtcaloriesconsumed,txtcarbohydrates1,txtcarbohydrates2,txtcarbohydratesconsumed,
    txtprotien1,txtprotien2,txtprotienconsumed,txtcalcium1,txtcalcium2,txtcalciumconsumed;

    private ProgressDialog progressDialog;
    private String currentDateandTime;
    private FirebaseUser user;
    float calories1, calories2, carbohydrates1, carbohydrates2, calcium1, calcium2, protien1, protien2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        firebaseAuthentication = FirebaseAuth.getInstance();

        databasecal1 = FirebaseDatabase.getInstance().getReference("Calculation 1");
        databasecal2 = FirebaseDatabase.getInstance().getReference("Calculation 2");
        databaseconsumption = FirebaseDatabase.getInstance().getReference("Consumption");


        btnCalOne = (Button)findViewById(R.id.btnCalOne);
        btnCalTwo = (Button)findViewById(R.id.btnCalculateSecond);
        btnSavedata = (Button)findViewById(R.id.btnSaveData);
        btnOk = (Button)findViewById(R.id.btnOkay);

        txtcalories1 = (TextView)findViewById(R.id.txtViewcalories1);
        txtcalories2 = (TextView)findViewById(R.id.txtViewcalories2);
        txtcaloriesconsumed = (TextView)findViewById(R.id.txtViewcaloriesConsumed);

        txtcarbohydrates1 = (TextView)findViewById(R.id.txtViewCarbohydrates1);
        txtcarbohydrates2 = (TextView)findViewById(R.id.txtViewCarbohydrates2);
        txtcarbohydratesconsumed = (TextView)findViewById(R.id.txtViewCarbohydratesConsumed);

        txtprotien1 = (TextView)findViewById(R.id.txtxViewProtein1);
        txtprotien2 = (TextView)findViewById(R.id.txtViewProtien2);
        txtprotienconsumed = (TextView)findViewById(R.id.txtViewProtienConsumed);

        txtcalcium1 = (TextView)findViewById(R.id.TxtViewCalcium1);
        txtcalcium2 = (TextView)findViewById(R.id.TxtViewCalcium2);
        txtcalciumconsumed = (TextView)findViewById(R.id.TxtViewCalciumConsumed);


        FirebaseUser user = firebaseAuthentication.getCurrentUser();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        currentDateandTime = sdf.format(new Date());


        txtUserEmail = (TextView) findViewById(R.id.txtUserEmail);
        txtUserEmail.setText(" Hello " +user.getEmail());


        btnOk.setOnClickListener(this);
        btnSavedata.setOnClickListener(this);
        btnCalOne.setOnClickListener(this);
        btnCalTwo.setOnClickListener(this);
        spinner();


    }
    public void spinner() {
        spinner = (Spinner) findViewById(R.id.JuciceSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.juiceTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position> 0) {
//                    calc.calcAverage();
//                    showNutrients(nutrients);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void calculateConsumed(){

        getData();

        //benu fill this
        //make sure to set the text views after calculating.
        // for now work with calories,carbohydrates , calcium and protien


    }


    public void getData(){
        //lakshan retreive the data
        // put everything in an array.
    }


    @Override
    public void onClick(View v) {
        FirebaseUser user = firebaseAuthentication.getCurrentUser();
        Random rand = new Random();

        if ( v == btnCalOne){
            //progressDialog.setMessage("Calculating...");
            //progressDialog.show();

            calories1 = rand.nextFloat()*(60 - 40 + 1) + 40 ;//Bluetooth received data
            carbohydrates1 = rand.nextFloat()*(15 - 6 + 1) + 6; //Bluetooth Received data
            protien1 = rand.nextFloat()*(2 - 1 + 1) + 1; //Bluetooth Received Data
            calcium1 = rand.nextFloat()*(2 - 1 + 1) + 1; //Bluetooth received Data

            txtcalories1.setText(Float.toString(calories1));
            txtcarbohydrates1.setText(Float.toString(carbohydrates1));
            txtprotien1.setText(Float.toString(protien1));
            txtcalcium1.setText(Float.toString(calcium1));


            //progressDialog.dismiss();

            calc A = new calc(calories1,carbohydrates1,protien1,calcium1);

            databasecal1.child(user.getUid()).setValue(A);

            Toast.makeText(Calculate.this, " Calculated ! ", Toast.LENGTH_SHORT).show();


        }

        if(v == btnCalTwo){
            //progressDialog.setMessage("Calculating...");
            //progressDialog.show();

            calories2 = rand.nextFloat()*(21) + 15 ;//Bluetooth received data
            carbohydrates2 = rand.nextFloat()*(5 - 0 + 1) + 0; //Bluetooth Received data
            protien2 = rand.nextFloat()*(0.9f - 0 + 1) + 0; //Bluetooth Received Data
            calcium2 = rand.nextFloat()*(0.9f - 0 + 1) + 0; //Bluetooth received Data

            txtcalories2.setText(Float.toString(calories2));
            txtcarbohydrates2.setText(Float.toString(carbohydrates2));
            txtprotien2.setText(Float.toString(protien2));
            txtcalcium2.setText(Float.toString(calcium2));
            //progressDialog.dismiss();

            calc B = new calc(calories2,carbohydrates2,protien2,calcium2);

            databasecal2.child(user.getUid()).setValue(B);

            calculateConsumed();

            Toast.makeText(Calculate.this, " Calculated ! ", Toast.LENGTH_SHORT).show();

        }

        if(v == btnOk){
            startActivity(new Intent(this, HomeActivity.class));
        }

        if(v == btnSavedata){

            //progressDialog.setMessage("Saving...");
            //progressDialog.show();

            float calories = calories1 - calories2;//from calculated consumption
            float carbohydrates = carbohydrates1 - carbohydrates2 ; //from calculated consumption
            float protien = protien1 - protien2; //from calculated consumption
            float calcium = calcium1 - calcium2; //from calculated consumption

            txtcaloriesconsumed.setText(Float.toString(calories));
            txtcarbohydratesconsumed.setText(Float.toString(carbohydrates));
            txtprotienconsumed.setText(Float.toString(protien));
            txtcalciumconsumed.setText(Float.toString(calcium));
            //progressDialog.dismiss();

            SaveConsumption con = new SaveConsumption(calories,carbohydrates,protien,calcium);

            databaseconsumption.child(user.getUid()).child(currentDateandTime).setValue(con);

            Toast.makeText(Calculate.this, " Data Has been Saved ! ", Toast.LENGTH_SHORT).show();

        }

    }

}
