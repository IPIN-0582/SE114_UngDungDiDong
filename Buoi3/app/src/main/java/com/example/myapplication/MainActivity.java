package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button login_btn;
    private EditText user_txt;
    private EditText pass_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        List<User> userList = new ArrayList<>();
        userList.add(new User("Huy", "123"));
        userList.add(new User("An", "abc"));
        userList.add(new User("Minh", "xyz"));
        userList.add(new User("Loc", "efg"));
        userList.add(new User("Thoi", "789"));
        userList.add(new User("Nguyen", "jkl"));
        userList.add(new User("Tai", "456"));

        login_btn=(Button) findViewById(R.id.Login_btn);
        user_txt=(EditText) findViewById(R.id.User_txt);
        pass_txt=(EditText) findViewById(R.id.Pass_txt);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = user_txt.getText().toString();
                String passInput = pass_txt.getText().toString();

                for (User user : userList) {
                    if (user.getUsername().equals(userInput) && user.getPassword().equals(passInput)) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("username", user.getUsername());
                        intent.putExtra("password", user.getPassword());
                        startActivity(intent);
                        break;
                    }
                }
            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
