package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private Button logout_btn;
    private TextView hello_lbl;
    private TextView showpass_lbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        Intent intent =getIntent();
        String recived_username=intent.getExtras().getString("username");
        String recived_password=intent.getExtras().getString("password");
        logout_btn=(Button) findViewById(R.id.Logout_btn);

        hello_lbl=findViewById(R.id.Hello_lbl);
        hello_lbl.setText("Hello, "+recived_username);
        showpass_lbl=findViewById(R.id.ShowPass_lbl);
        showpass_lbl.setText("Your password:"+ recived_password);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}