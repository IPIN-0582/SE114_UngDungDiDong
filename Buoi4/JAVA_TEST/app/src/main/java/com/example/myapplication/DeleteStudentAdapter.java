package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DeleteStudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private ArrayList<Student> studentList;
    private ArrayList<Boolean> checkedStates;

    public DeleteStudentAdapter(Context context, int resource, ArrayList<Student> studentList) {
        super(context, resource, studentList);
        this.context = context;
        this.resource = resource;
        this.studentList = studentList;
        this.checkedStates = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            checkedStates.add(false); // Khởi tạo tất cả checkbox chưa được chọn
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);
        }

        TextView studentId = convertView.findViewById(R.id.student_id);
        TextView studentName = convertView.findViewById(R.id.student_name);
        CheckBox studentCheckbox = convertView.findViewById(R.id.student_checkbox);

        Student studentItem = studentList.get(position);
        studentId.setText(studentItem.getID()+"");
        studentName.setText(studentItem.getName());

        studentCheckbox.setChecked(checkedStates.get(position));

        studentCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedStates.set(position, isChecked);
        });

        return convertView;
    }

    public ArrayList<Student> getSelectedStudents() {
        ArrayList<Student> selectedStudents = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            if (checkedStates.get(i)) {
                selectedStudents.add(studentList.get(i));
            }
        }
        return selectedStudents;
    }
}

