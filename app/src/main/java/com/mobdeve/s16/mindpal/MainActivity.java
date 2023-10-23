package com.mobdeve.s16.mindpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button LogInButton;
    EditText UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogInButton = (Button)findViewById(R.id.login_button);
        UserName = (EditText) findViewById(R.id.enter_username);

        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();

            }
        });
    }

    public void openHomeActivity(){
        Intent intent = new Intent (this, HomeActivity.class);
        intent.putExtra("KeyUsername" , UserName.getText().toString());
        startActivity(intent);
    }
}

