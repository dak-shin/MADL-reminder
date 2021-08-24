package com.example.madl_reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskModel> {

    public static final int RED = -65536;
    public static final int BLUE = -16776961;
    public static final int GREEN = -3355444;
    public static final int WHITE = -1;
    public static final int YELLOW = -256;
    public static final int CYAN =  -16711681;

    private Context mContext;
    private List<TaskModel> taskList = new ArrayList<>();
    Button btn;

    public TaskAdapter(@NonNull Context context,  @NonNull List<TaskModel> task_list) {
        super(context, 0, task_list);
        mContext = context;
        taskList = task_list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        TaskModel currentTask = taskList.get(position);

        TextView name = (TextView)listItem.findViewById(R.id.task_name);
        TextView id = (TextView)listItem.findViewById(R.id.priority);
        name.setText(currentTask.getTask_name());

        int color;
        int p = currentTask.getTask_priority();
        switch (p) {
            case 1:
                color = RED;
                break;

            case 2:
                color = YELLOW;
                break;

            case 3:
                color = CYAN;
                break;

            default:
                color = WHITE;
                break;
        }
        name.setTextColor(color);
        id.setText(Integer.toString(currentTask.getId()));
        return listItem;
    }

}
