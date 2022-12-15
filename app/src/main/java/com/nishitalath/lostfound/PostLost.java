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

public class PostLost extends AppCompatActivity {

    EditText jName, jPhone, jItemname, jWhereLost, jMsg, jPhotos;
    Button jButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_lost);

        jName=findViewById(R.id.name);
        jPhone=findViewById(R.id.phone);
        jItemname=findViewById(R.id.itemname);
        jWhereLost=findViewById(R.id.whereLost);
        jMsg=findViewById(R.id.msg);
        jPhotos=findViewById(R.id.photos);
        jButton=(Button) findViewById(R.id.submitbtn);

        jButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLost();
                Toast.makeText(PostLost.this, "Added Successfully.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PostLost.this, Menu.class));
            }
        });
    }

    private void AddLost() {

        String Name=jName.getText().toString();
        String Phone=jPhone.getText().toString();
        String ItemName=jItemname.getText().toString();
        String WhereLost=jWhereLost.getText().toString();
        String Msg=jMsg.getText().toString();
        String Photos=jPhotos.getText().toString();

        HashMap<String , Object> lost = new HashMap<>();
        lost.put("Name" , Name);
        lost.put("Phone" , Phone);
        lost.put("Item Name" , ItemName);
        lost.put("WhereLost" , WhereLost);
        lost.put("Message" , Msg);
        lost.put("Photos" , Photos);

        FirebaseDatabase.getInstance().getReference().child("Lost").push().setValue(lost);
    }
}