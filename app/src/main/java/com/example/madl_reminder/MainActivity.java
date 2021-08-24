package com.example.madl_reminder;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    private TaskAdapter taskAd;
    ArrayAdapter taskArr;
    DatabaseHelper databaseHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView task_list = findViewById(R.id.taskList);
        ShowTasks(task_list);
    }

    private void ShowTasks(ListView task_list) {
        databaseHelper = new DatabaseHelper(MainActivity.this);
        List<TaskModel> all_tasks = databaseHelper.getAll();
        taskAd = new TaskAdapter(this,all_tasks);
        task_list.setAdapter(taskAd);
    }

    public boolean DeleteTaskWithBtn(View view)
    {

        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_name);
        TextView prioTextView = (TextView) parent.findViewById(R.id.priority);
        String task = (String) taskTextView.getText();
        String prio = (String) prioTextView.getText();
        databaseHelper.deleteTaskByTaskID(prio);
        ListView task_list = findViewById(R.id.taskList);
        ShowTasks(task_list);
        Toast.makeText(MainActivity.this, "Deleted successfully",Toast.LENGTH_LONG ).show();
        return false;

    }

    public void handleTask(View v)
    {
        RadioGroup priority_grp = findViewById(R.id.priority);
        Button btn = findViewById(R.id.add_button);

        EditText task = findViewById(R.id.task);
        String temp = task.getText().toString();
        if(!temp.trim().equals(""))
        {
            int color;
            int priority = priority_grp.getCheckedRadioButtonId();


            if (priority!=-1) {


                switch (priority) {
                    case R.id.high:
                        color = getResources().getColor(R.color.red);
                        add(temp, 1);
                        break;

                    case R.id.medium:
                        color = getResources().getColor(R.color.orange);
                        add(temp, 2);
                        break;

                    case R.id.low:
                        color = getResources().getColor(R.color.blue);
                        add(temp, 3);
                        break;

                    default:
                        color = getResources().getColor(R.color.white);
                        break;
                }
            }
            else
                task.setError("Please select a Priority");
            TaskModel task_obj = null;
            databaseHelper = new DatabaseHelper(MainActivity.this);

            ListView task_list = findViewById(R.id.taskList);
            ShowTasks(task_list);
            task.setText("");
            priority_grp.clearCheck();

        }
        else{
            task.setError("Task cannot be empty!!");
        }
    }

    private void add(String temp, int priority) {
        TaskModel task_obj;
        try{
            task_obj = new TaskModel(-1, temp, priority);
//                Toast.makeText(MainActivity.this, task_obj.toString(),Toast.LENGTH_LONG ).show();
            boolean b = databaseHelper.addTask(task_obj);
            Toast.makeText(MainActivity.this, "Added Successfully",Toast.LENGTH_LONG ).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Error creating task",Toast.LENGTH_LONG ).show();
        }
    }


}