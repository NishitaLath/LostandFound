package com.nishitalath.lostfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button submitbtn, signup, jforgotpass;
    String emailPattern="[a-zA-Z0-9._-]+@iitp+\\.+ac+\\.+in";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        submitbtn = (Button) findViewById(R.id.loginbtn);
        signup = (Button) findViewById(R.id.signupbtn);
        jforgotpass = (Button) findViewById(R.id.forgotpass);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup();
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        jforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetPass= new EditText(v.getContext());
                final AlertDialog.Builder forgotpassDialog= new AlertDialog.Builder(v.getContext());
                forgotpassDialog.setTitle("Forgot Password?");
                forgotpassDialog.setMessage("Enter your Institute Email to receive Reset Link. ");
                forgotpassDialog.setView(resetPass);

                forgotpassDialog.setPositiveButton("Yes", (dialog, which) -> {
                    String email= resetPass.getText().toString();
                    mAuth.sendPasswordResetEmail(email).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
                        Toast.makeText(Login.this, "Reset Link sent to your Email. ", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener((e) -> {
                        Toast.makeText(Login.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                });

                forgotpassDialog.setNegativeButton("No", (dialog, which) -> {

                });
                forgotpassDialog.create().show();
            }
        });
    }
    public  void openSignup() {
        Intent intent1= new Intent(this, Signup.class);
        startActivity(intent1);
    }

    public  void login() {
        String email=username.getText().toString();
        String pass=password.getText().toString();
        if (!email.matches(emailPattern))
        {
            username.setError("Enter Institute Email");
        }
        else if(pass.isEmpty() || pass.length()<8)
        {
            password.setError("Enter valid password");
        }
        else {
            progressDialog.setMessage("Please wait");
            progressDialog.setTitle("Logging In");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful() && mUser.isEmailVerified())
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent2=new Intent(Login.this, Menu.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}