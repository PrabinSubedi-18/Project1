package com.example.firebasedatabsedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText fname,lname, buildingNo, email,flatNo,dob,contactNo,suburb,password,cnfPassword;
    private Button signup;
    private TextView login;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference("Strata");
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        buildingNo=(EditText)findViewById(R.id.BuildinNo);
        email=(EditText)findViewById(R.id.email);
        flatNo=(EditText)findViewById(R.id.flatNo);
        dob=(EditText)findViewById(R.id.dob);
        contactNo=(EditText)findViewById(R.id.contactNo);
        suburb=(EditText)findViewById(R.id.suburb);
        password=(EditText)findViewById(R.id.password);
        cnfPassword=(EditText)findViewById(R.id.cnfPassword);
        signup=(Button)findViewById(R.id.signup);
        login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginintent=new Intent(MainActivity.this,Login.class);
                startActivity(loginintent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsers();
            }
        });
    }
    public void addUsers(){
        String userFName=fname.getText().toString();
        String userLName=lname.getText().toString();
        String userBuildingNo=buildingNo.getText().toString();
        String useremail = email.getText().toString();
        String userFlatNo=flatNo.getText().toString();
        String userDob=dob.getText().toString();
        String userContactNo=contactNo.getText().toString();
        String userSuburb=suburb.getText().toString();
        String userPassword=password.getText().toString();
        String userCnfPassword=cnfPassword.getText().toString();
        if(!TextUtils.isEmpty(userFName) && !TextUtils.isEmpty(userLName) && !TextUtils.isEmpty(userBuildingNo) && !TextUtils.isEmpty(useremail)
                && !TextUtils.isEmpty(userFlatNo) && !TextUtils.isEmpty(userDob)&& !TextUtils.isEmpty(userContactNo) && !TextUtils.isEmpty(userSuburb)
                && !TextUtils.isEmpty(userPassword) && !TextUtils.isEmpty(userCnfPassword) && userPassword.equals(userCnfPassword)){
            String id= databaseReference.push().getKey();
            Users users=new Users(id,userFName,userLName,userBuildingNo,useremail,userFlatNo,userDob,userContactNo,userSuburb,userPassword,userCnfPassword);
            databaseReference.child(id).setValue(users);
            fname.setText("");
            lname.setText("");
            buildingNo.setText("");
            flatNo.setText("");
            dob.setText("");
            contactNo.setText("");
            suburb.setText("");
            password.setText("");
            cnfPassword.setText("");
            Toast.makeText(MainActivity.this,"Registration Success!!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Error: Check details.", Toast.LENGTH_SHORT).show();
        }
    }
}
