package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;

public class DeleteStudentActivity extends AppCompatActivity {

    private ArrayList<Student> list_student = new ArrayList<>();
    private ListView lv_student;
    private DeleteStudentAdapter adapter;
    private Button deleteSelectedButton;
    private ArrayList<Boolean> selectedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_student);
        lv_student =findViewById(R.id.listView);
        deleteSelectedButton =findViewById(R.id.deleteButton);
        list_student = (ArrayList<Student>) getIntent().getSerializableExtra("StudentList");
        if (list_student == null) {
            list_student = new ArrayList<>();
        }
        selectedItems = new ArrayList<>();
        for (int i = 0; i < list_student.size(); i++) {
            selectedItems.add(false);
        }

        // Khởi tạo adapter và gán vào ListView
        adapter = new DeleteStudentAdapter(this, R.layout.delete_student_item, list_student);
        lv_student.setAdapter(adapter);

        // Xử lý sự kiện khi bấm nút "Xóa lớp đã chọn"
        deleteSelectedButton.setOnClickListener(v -> {
            ArrayList<Student> selectedStudents = adapter.getSelectedStudents(); // Lấy danh sách lớp được chọn từ adapter

            // Xóa các lớp đã chọn khỏi list_classroom
            list_student.removeAll(selectedStudents);

            // Thông báo cho adapter về thay đổi dữ liệu
            adapter.notifyDataSetChanged();

            // Trả lại danh sách lớp đã cập nhật cho MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedStudentList", (Serializable) list_student);
            setResult(RESULT_OK, resultIntent);
            finish(); // Đóng DeleteClassActivity
        });
    }
}