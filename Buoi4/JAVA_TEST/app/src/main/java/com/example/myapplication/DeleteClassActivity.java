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

public class DeleteClassActivity extends AppCompatActivity {

    private ArrayList<Class> list_classroom = new ArrayList<>();
    private ListView lv_classroom;
    private DeleteClassAdapter adapter;
    private Button deleteSelectedButton;
    private ArrayList<Boolean> selectedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_class);
        lv_classroom =findViewById(R.id.deleteClassListView);
        deleteSelectedButton =findViewById(R.id.deleteSelectedButton);
        list_classroom = (ArrayList<Class>) getIntent().getSerializableExtra("ClassList");
        if (list_classroom == null) {
            list_classroom = new ArrayList<>();
        }
        selectedItems = new ArrayList<>();
        for (int i = 0; i < list_classroom.size(); i++) {
            selectedItems.add(false);
        }

        // Khởi tạo adapter và gán vào ListView
        adapter = new DeleteClassAdapter(this, R.layout.delete_class_item, list_classroom);
        lv_classroom.setAdapter(adapter);

        // Xử lý sự kiện khi bấm nút "Xóa lớp đã chọn"
        deleteSelectedButton.setOnClickListener(v -> {
            ArrayList<Class> selectedClasses = adapter.getSelectedClasses(); // Lấy danh sách lớp được chọn từ adapter

            // Xóa các lớp đã chọn khỏi list_classroom
            list_classroom.removeAll(selectedClasses);

            // Thông báo cho adapter về thay đổi dữ liệu
            adapter.notifyDataSetChanged();

            // Trả lại danh sách lớp đã cập nhật cho MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedClassList", (Serializable) list_classroom);
            setResult(RESULT_OK, resultIntent);
            finish(); // Đóng DeleteClassActivity
        });
    }

}