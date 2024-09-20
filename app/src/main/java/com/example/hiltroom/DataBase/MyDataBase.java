package com.example.hiltroom.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hiltroom.Helper.RoomHelper;
import com.example.hiltroom.Model.Student;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    public abstract RoomHelper roomDao();


    public static MyDataBase getDatabase(Context context){
        return Room.databaseBuilder(context,MyDataBase.class,"StudentInfo")
                .allowMainThreadQueries()
                .build();

    }

}
