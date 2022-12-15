package com.nishitalath.lostfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdatePassword extends AppCompatActivity {
    EditText password, newpassword, conpassword;
    Button submit;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        password = findViewById(R.id.pass);
        newpassword = findViewById(R.id.newpass);
        conpassword = findViewById(R.id.conpass);
        submit = (Button) findViewById(R.id.submitbtn);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        String userEmail = mUser.getEmail();

        String spass = password.getText().toString();
        String snewpass = newpassword.getText().toString();
        String sconpass = conpassword.getText().toString();

        mAuth.signInWithEmailAndPassword(userEmail, spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && mUser.isEmailVerified()) {
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (snewpass.equals(sconpass)) {
                                mUser.updatePassword(snewpass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(UpdatePassword.this, "Password Updated Successfully. ", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(UpdatePassword.this, "Password Update Failed. ", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Toast.makeText(UpdatePassword.this, "Password Update Failed. ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(UpdatePassword.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}