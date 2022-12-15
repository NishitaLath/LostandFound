package com.nishitalath.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PostFound extends AppCompatActivity {
    EditText jName, jPhone, jItemname, jWhereFound, jMsg, jPhotos;
    Button jButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_found);

        jName=findViewById(R.id.name);
        jPhone=findViewById(R.id.phone);
        jItemname=findViewById(R.id.itemname);
        jWhereFound=findViewById(R.id.whereFound);
        jMsg=findViewById(R.id.msg);
        jPhotos=findViewById(R.id.photos);
        jButton=(Button) findViewById(R.id.submitbtn);

        jButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFound();
                Toast.makeText(PostFound.this, "Added Successfully.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PostFound.this, Menu.class));
            }
        });
    }

    private void AddFound() {
        String Name=jName.getText().toString();
        String Phone=jPhone.getText().toString();
        String ItemName=jItemname.getText().toString();
        String WhereFound=jWhereFound.getText().toString();
        String Msg=jMsg.getText().toString();
        String Photos=jPhotos.getText().toString();

        HashMap<String , Object> found = new HashMap<>();
        found.put("Name" , Name);
        found.put("Phone" , Phone);
        found.put("Item Name" , ItemName);
        found.put("WhereLost" , WhereFound);
        found.put("Message" , Msg);
        found.put("Photos" , Photos);

        FirebaseDatabase.getInstance().getReference().child("Found").push().setValue(found);
    }
}