package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewStudentActivity extends AppCompatActivity {

    private EditText editTextStudentId, editTextStudentName, editTextStudentYearBrith;
    private Button buttonAddClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_student);
        editTextStudentId = findViewById(R.id.editTextStudentId);
        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentYearBrith = findViewById(R.id.editTextStudentYearBrith);
        buttonAddClass = findViewById(R.id.buttonAddStudent);

        buttonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int StudentId = Integer.parseInt(editTextStudentId.getText().toString());
                String StudentName = editTextStudentName.getText().toString();
                int StudentYearBrith = Integer.parseInt(editTextStudentYearBrith.getText().toString());
                if ((StudentId == 0) || StudentName.isEmpty() || (StudentYearBrith == 0)) {
                    Toast.makeText(NewStudentActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("studentId", StudentId);
                    resultIntent.putExtra("studentName", StudentName);
                    resultIntent.putExtra("studentYearBrith", StudentYearBrith);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}