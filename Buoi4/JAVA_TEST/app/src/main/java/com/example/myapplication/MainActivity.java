package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_CLASS = 1;
    private static final int REQUEST_CODE_DELETE_CLASS = 2;
    private ArrayList<Class> list_classroom = new ArrayList<>();
    private ListView lv_classroom;
    private ClassListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lv_classroom =findViewById(R.id.listView);
        Class class1 = new Class("ATTT","An toan");
        class1.addStudent( new Student(1,"Huy",2004));
        class1.addStudent( new Student(2,"A",2004));
        class1.addStudent( new Student(3,"B",2004));
        class1.addStudent( new Student(4,"C",2004));
        class1.addStudent( new Student(5,"D",2004));
        class1.addStudent( new Student(6,"E",2004));

        Class class2 = new Class("HTTT","He Thong");
        class2.addStudent( new Student(1,"A",2004));
        class2.addStudent( new Student(2,"B",2004));
        class2.addStudent( new Student(3,"C",2004));
        class2.addStudent( new Student(4,"D",2004));
        class2.addStudent( new Student(5,"E",2004));

        Class class3 = new Class("CNTT","Cong nghe");
        class3.addStudent( new Student(1,"K",2004));
        class3.addStudent( new Student(2,"F",2004));
        class3.addStudent( new Student(3,"G",2004));
        class3.addStudent( new Student(4,"H",2004));
        class3.addStudent( new Student(5,"I",2004));
        class3.addStudent( new Student(6,"J",2004));
        list_classroom.add(class1);
        list_classroom.add(class2);
        list_classroom.add(class3);
        adapter = new ClassListAdapter(this, R.layout.class_item, list_classroom);
        lv_classroom.setAdapter(adapter);

        lv_classroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class selectedClass = (Class) parent.getItemAtPosition(position);

                if (selectedClass != null) {
                    Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                    intent.putExtra("classId", selectedClass.getClassId());
                    intent.putExtra("className", selectedClass.getClassName());
                    intent.putExtra("memberCount", selectedClass.getTotalStudents());

                    ArrayList<Student> students = new ArrayList<>(selectedClass.getStudents());
                    intent.putExtra("students", students);

                    startActivity(intent);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.class_menu, menu);
        return true;
    }

    // Xử lý sự kiện khi chọn item trong Options Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_class){
            Intent intent = new Intent(MainActivity.this, NewClassActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_CLASS); // Sử dụng startActivityForResult
            return true;
        } else if (item.getItemId() == R.id.delete_class) {
            Intent intent = new Intent(MainActivity.this, DeleteClassActivity.class);
            intent.putExtra("ClassList",(Serializable) list_classroom);
            startActivityForResult(intent, REQUEST_CODE_DELETE_CLASS); // Sử dụng startActivityForResult
            return true;
        }
        return super.onOptionsItemSelected(item); // Thêm dòng này để trả về kết quả xử lý mặc định
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_CLASS && resultCode == RESULT_OK && data != null) {
            String classId = data.getStringExtra("classId");
            String className = data.getStringExtra("className");
            Class newClass = new Class(classId, className);
            list_classroom.add(newClass);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã thêm lớp mới: " + className, Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_DELETE_CLASS && resultCode == RESULT_OK && data != null) {
            list_classroom = (ArrayList<Class>) data.getSerializableExtra("updatedClassList");

            // Cập nhật dữ liệu trực tiếp trong adapter
            adapter.updateClassList(list_classroom);
            adapter.notifyDataSetChanged();
        }
    }
}