package com.nishitalath.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6, b7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        b1 = (Button) findViewById(R.id.postLost);
        b2 = (Button) findViewById(R.id.postFound);
        b3 = (Button) findViewById(R.id.feedLost);
        b4 = (Button) findViewById(R.id.feedFound);
        b5 = (Button) findViewById(R.id.myPosts);
        b6 = (Button) findViewById(R.id.updtPass);
        b7 = (Button) findViewById(R.id.logout);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPostLost();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPostFound();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedLost();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedFound();
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyPosts();
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdatePassword();
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    public  void openPostLost() {
        Intent intent1= new Intent(this, PostLost.class);
        startActivity(intent1);
    }

    public  void openPostFound() {
        Intent intent2= new Intent(this, PostFound.class);
        startActivity(intent2);
    }

    public  void openFeedLost() {
        Intent intent3= new Intent(this, FeedLost.class);
        startActivity(intent3);
    }

    public  void openFeedFound() {
        Intent intent4= new Intent(this, FeedFound.class);
        startActivity(intent4);
    }

    public  void openMyPosts() {
        Intent intent5= new Intent(this, MyPosts.class);
        startActivity(intent5);
    }

    public  void openUpdatePassword() {
        Intent intent6= new Intent(this, UpdatePassword.class);
        startActivity(intent6);
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent7= new Intent(this, Login.class);
        startActivity(intent7);
    }
}