package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends ArrayAdapter<Student> {
    private int resource;
    private List<Student> students;

    public StudentListAdapter(Context context, int resource, List<Student> students) {
        super(context, resource, students);
        this.students = students;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(this.getContext());
            v = vi.inflate(this.resource, null);
        }

        Student student = getItem(position);

        if (student != null) {
            TextView classNameTextView = v.findViewById(R.id.student_name);
            TextView classIdTextView = v.findViewById(R.id.student_id);
            TextView classTotalStudent = v.findViewById(R.id.student_year_brith);
            if (classNameTextView != null)
                classNameTextView.setText(student.getName());
            if (classIdTextView != null)
                classIdTextView.setText(student.getID()+"");
            if (classTotalStudent != null)
                classTotalStudent.setText(student.getYearBrith()+"");
        }
        return v;
    }
    public void updateStudentList(ArrayList<Student> updatedList) {
        this.students.clear();
        this.students.addAll(updatedList);
    }

}

