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

public class DeleteClassAdapter extends ArrayAdapter<Class> {
    private Context context;
    private int resource;
    private ArrayList<Class> classList;
    private ArrayList<Boolean> checkedStates;

    public DeleteClassAdapter(Context context, int resource, ArrayList<Class> classList) {
        super(context, resource, classList);
        this.context = context;
        this.resource = resource;
        this.classList = classList;
        this.checkedStates = new ArrayList<>();
        for (int i = 0; i < classList.size(); i++) {
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

        TextView classId = convertView.findViewById(R.id.class_id);
        TextView className = convertView.findViewById(R.id.class_name);
        CheckBox classCheckbox = convertView.findViewById(R.id.class_checkbox);

        Class classItem = classList.get(position);
        classId.setText(classItem.getClassId());
        className.setText(classItem.getClassName());

        classCheckbox.setChecked(checkedStates.get(position));

        classCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedStates.set(position, isChecked);
        });

        return convertView;
    }

    public ArrayList<Class> getSelectedClasses() {
        ArrayList<Class> selectedClasses = new ArrayList<>();
        for (int i = 0; i < classList.size(); i++) {
            if (checkedStates.get(i)) {
                selectedClasses.add(classList.get(i));
            }
        }
        return selectedClasses;
    }

}
