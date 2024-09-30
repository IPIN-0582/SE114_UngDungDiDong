package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewClassActivity extends AppCompatActivity {

    private EditText editTextClassId, editTextClassName;
    private Button buttonAddClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class);

        editTextClassId = findViewById(R.id.editTextClassId);
        editTextClassName = findViewById(R.id.editTextClassName);
        buttonAddClass = findViewById(R.id.buttonAddClass);

        buttonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classId = editTextClassId.getText().toString();
                String className = editTextClassName.getText().toString();

                if (classId.isEmpty() || className.isEmpty()) {
                    Toast.makeText(NewClassActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("classId", classId);
                    resultIntent.putExtra("className", className);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
