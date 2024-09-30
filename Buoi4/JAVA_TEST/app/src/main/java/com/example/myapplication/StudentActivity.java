package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    private TextView classNameTextView, classIdTextView, memberCountTextView;
    private ListView studentListView;
    private static final int REQUEST_CODE_ADD_STUDENT = 1;
    private static final int REQUEST_CODE_DELETE_STUDENT = 2;
    private StudentListAdapter adapter;
    private ArrayList<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        classNameTextView = findViewById(R.id.className);
        classIdTextView = findViewById(R.id.classId);
        memberCountTextView = findViewById(R.id.memberCount);
        studentListView = findViewById(R.id.studentListView);

        // Lấy dữ liệu từ Intent
        String classId = getIntent().getStringExtra("classId");
        String className = getIntent().getStringExtra("className");
        int memberCount = getIntent().getIntExtra("memberCount", 0);
        students = (ArrayList<Student>) getIntent().getSerializableExtra("students");

        // Hiển thị thông tin lớp
        classIdTextView.setText("Class ID: " + classId);
        classNameTextView.setText("Class Name: " + className);
        memberCountTextView.setText("Members: " + memberCount);

        // Hiển thị danh sách học sinh
        adapter = new StudentListAdapter(this,R.layout.student_item,students);
        studentListView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_menu, menu);
        return true;
    }

    // Xử lý sự kiện khi chọn item trong Options Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_student){
            Intent intent = new Intent(StudentActivity.this, NewStudentActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT); // Sử dụng startActivityForResult
            return true;
        } else if (item.getItemId() == R.id.delete_student) {
            Intent intent = new Intent(StudentActivity.this, DeleteStudentActivity.class);
            intent.putExtra("StudentList",(Serializable) students);
            startActivityForResult(intent, REQUEST_CODE_DELETE_STUDENT); // Sử dụng startActivityForResult
            return true;
        }
        return super.onOptionsItemSelected(item); // Thêm dòng này để trả về kết quả xử lý mặc định
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == RESULT_OK && data != null) {
            int studentId = data.getIntExtra("studentId",0);
            String studentName = data.getStringExtra("studentName");
            int studentYearBrith = data.getIntExtra("studentYearBrith",0);
            Student newStudent = new Student(studentId, studentName, studentYearBrith);
            students.add(newStudent);
            memberCountTextView.setText("Members: " + students.size());
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã thêm học sinh mới: " + studentName, Toast.LENGTH_SHORT).show();

        } else if (requestCode == REQUEST_CODE_DELETE_STUDENT && resultCode == RESULT_OK && data != null) {
            students = (ArrayList<Student>) data.getSerializableExtra("updatedStudentList");

            // Cập nhật dữ liệu trực tiếp trong adapter
            adapter.updateStudentList(students);
            memberCountTextView.setText("Members: " + students.size());
            adapter.notifyDataSetChanged();
        }
    }
}