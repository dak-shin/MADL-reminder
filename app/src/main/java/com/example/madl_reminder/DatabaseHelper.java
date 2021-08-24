package com.example.madl_reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int RED = -65536;
    public static final int BLUE = -16776961;
    public static final int GREEN = -3355444;
    public static final int WHITE = -1;
    public static final int YELLOW = -256;
    public static final int CYAN =  -16711681;

    public static final String TT = "tasks_table";
    public static final String TN = "TASK_NAME";
    public static final String TP = "TASK_PRIORITY";
    public static final String ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "tasks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "Create table " + TT + " (" + ID + " integer primary key autoincrement, " +
                TN + " text, " + TP + " int )";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addTask(TaskModel task_obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(TN, task_obj.getTask_name());
        cv.put(TP, task_obj.getTask_priority());

        long status = db.insert(TT,null ,cv);

        if (status==-1) return false;
        else return true;
    }

    public boolean deleteTaskByTaskID( String prio)
    {

        SQLiteDatabase db = getWritableDatabase();
        String del_query = "Delete from "+TT+ " where " + ID + " = '" + prio+"'";
        Cursor cursor = db.rawQuery(del_query, null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        else{
            return false;
        }
    }



    public List<TaskModel> getAll(){
        List<TaskModel> req_list = new ArrayList<>();
        String get_query = " Select * from "+TT+" order by "+TP;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(get_query, null);

        if (cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0);
                String t_name = cursor.getString(1);
                int t_priority = cursor.getInt(2);

                TaskModel task_obj = new TaskModel(id, t_name, t_priority);
                req_list.add(task_obj);

            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return req_list;
    }
}
