package com.nishitalath.lostfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

    EditText name, rollno, username, password, conpassword, phone, wano, pp;
    Button signupbtn;
    String emailPattern="[a-zA-Z0-9._-]+@iitp+\\.+ac+\\.+in";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name=findViewById(R.id.name);
        rollno=findViewById(R.id.rollno);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        conpassword=findViewById(R.id.conpassword);
        phone=findViewById(R.id.phone);
        wano=findViewById(R.id.wano);
        pp=findViewById(R.id.pp);
        signupbtn=(Button) findViewById(R.id.signupbtn);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
                startActivity(new Intent(Signup.this, Login.class));
            }
        });

    }

    private void PerforAuth() {
        String Name=name.getText().toString();
        String Rollno=rollno.getText().toString();
        String Phone=phone.getText().toString();
        String Wano=wano.getText().toString();
        String PP=pp.getText().toString();
        String email=username.getText().toString();
        String pass=password.getText().toString();
        String conPassword=conpassword.getText().toString();

        HashMap<String , Object> user = new HashMap<>();
        user.put("Email" , email);
        user.put("Name" , Name);
        user.put("Roll Number" , Rollno);
        user.put("Phone" , Phone);
        user.put("WhatsApp Number" , Wano);
        user.put("Profile Picture" , PP);

        FirebaseDatabase.getInstance().getReference().child("Users").push().setValue(user);

        if (!email.matches(emailPattern))
        {
            username.setError("Enter Institute Email");
        }
        else if(pass.isEmpty() || pass.length()<8)
        {
            password.setError("Enter valid password");
        }
        else if(!pass.equals(conPassword))
        {
            conpassword.setError("Password does not match");
        }
        else
        {
            progressDialog.setMessage("Creating Account");
            progressDialog.setTitle("Account Created");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Signup.this, "Account Created Successfully, Activation email sent.", Toast.LENGTH_SHORT).show();
                        mUser.sendEmailVerification();
                        sendUserToNextActivity();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Signup.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }

                private void sendUserToNextActivity() {
                    Intent intent=new Intent(Signup.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

        }
    }
}