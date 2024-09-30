package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClassListAdapter extends ArrayAdapter<Class> {
    private Context context;
    private int resource;
    private ArrayList<Class> classList;

    public ClassListAdapter(Context context, int resource, ArrayList<Class> classList) {
        super(context, resource, classList);
        this.context = context;
        this.resource = resource;
        this.classList = classList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);
        }

        TextView classId = convertView.findViewById(R.id.classroom_id);
        TextView className = convertView.findViewById(R.id.classroom_name);
        TextView classTotalStudents = convertView.findViewById(R.id.classroom_total_students);

        Class classItem = classList.get(position);
        classId.setText(classItem.getClassId());
        className.setText(classItem.getClassName());
        classTotalStudents.setText(String.valueOf(classItem.getTotalStudents()));

        return convertView;
    }

    public void updateClassList(ArrayList<Class> updatedList) {
        this.classList.clear();
        this.classList.addAll(updatedList);
    }
}
