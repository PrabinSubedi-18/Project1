package com.example.firebasedatabsedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private TextView info;
    private Button Login;
    private Button mTextViewRegister;
    private TextView mTextViewForgotPassword;
    private int counter=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.edittext_username);
        password=(EditText) findViewById(R.id.edittext_password);
        info=(TextView) findViewById(R.id.textview_info);
        Login=(Button)findViewById(R.id.button_Login);
        mTextViewForgotPassword=(TextView) findViewById(R.id.textview_forgot_password);
        info.setText("Number of attempts: 5");
        mTextViewRegister = (Button) findViewById(R.id.button_signup);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent=new Intent(Login.this,Signup.class);
                startActivity(registerIntent);
            }
        });
        mTextViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotpasswordintent=new Intent(Login.this,forgotpass.class);
                startActivity(forgotpasswordintent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* loginUser();*/
                validate(username.getText().toString(),password.getText().toString());
            }
        });

      /*  Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Animation animation= AnimationUtils.loadAnimation(Login.this,R.anim.blink_anim);
               Login.startAnimation(animation);
                validate(username.getText().toString(),password.getText().toString());
            }
        });*/
    }

   /* private void loginUser( ) {
        if(!validateUsername() | !validatePasword()){
            return;
        }
        else{
            isUser();
        }
    }

    private void isUser() {
        final String userEnteredUsername=username.getText().toString().trim();
        final String userEnteredPassword=password.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Strata");
        Query checkUser=reference.orderByChild("userContactNo").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String passwordFromDB=dataSnapshot.child(userEnteredUsername).child("userPassword").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredPassword)){
                        String dobFromDB=dataSnapshot.child(userEnteredUsername).child("userDob").getValue(String.class);
                        String fNameFromDB=dataSnapshot.child(userEnteredUsername).child("userFName").getValue(String.class);
                        String lNameFromDB=dataSnapshot.child(userEnteredUsername).child("userLName").getValue(String.class);
                        String contactNoFromDB=dataSnapshot.child(userEnteredUsername).child("userContactNo").getValue(String.class);
                        String buildingNoFromDB=dataSnapshot.child(userEnteredUsername).child("userBuildingNo").getValue(String.class);
                        String flatNoFromDB=dataSnapshot.child(userEnteredUsername).child("userFlatNo").getValue(String.class);
                        String suburbFromDB=dataSnapshot.child(userEnteredUsername).child("userSuburb").getValue(String.class);
                        String emailFromDB=dataSnapshot.child(userEnteredUsername).child("userEmail").getValue(String.class);
                        Intent intent=new Intent(getApplicationContext(),Homescree.class);
                        intent.putExtra("userDob",dobFromDB);
                        intent.putExtra("userFname",fNameFromDB);
                        intent.putExtra("userLName",lNameFromDB);
                        intent.putExtra("userContactNo",contactNoFromDB);
                        intent.putExtra("userBuildingNo",buildingNoFromDB);
                        intent.putExtra("userFlatNo",flatNoFromDB);
                        intent.putExtra("userSuburb",suburbFromDB);
                        intent.putExtra("userEmail",emailFromDB);
                        startActivity(intent);
                    }
                    else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }

                }
                else{
                    username.setError("No such user exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private Boolean validateUsername(){
        String val=username.getText().toString();
        if(val.isEmpty()){
            username.setError("Field is Empty");
            return false;
        }else{
            username.setError(null);
           return true;
        }

    }

    private  Boolean validatePasword(){
        String val=password.getText().toString();
        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }
*/

    private void validate(String user, String pwd) {
        if ((user.equals("Admin")) && (pwd.equals("Admin") )) {
            Intent intent = new Intent(Login.this, Homescree.class);
            startActivity(intent);
        } else {
            info.setText("Remaining Attempts:" +String.valueOf(counter));
            counter--;
            if(counter==-1){

                Login.setEnabled(false);
                Toast.makeText(Login.this, "Login Blocked!!", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
