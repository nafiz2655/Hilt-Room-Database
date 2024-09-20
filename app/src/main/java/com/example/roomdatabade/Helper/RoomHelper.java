package com.example.roomdatabade.Helper;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabade.Model.Student;

import java.util.List;

@Dao
public interface RoomHelper {

    @Insert
    void insertData(Student student);

    @Update
    void updateData(Student student);

    @Delete
    void deleteData(Student student);

    @Query("select * from student where id=:id")
    Student readdata(int id);

    @Query("select * from student order by id DESC")
    List<Student> readAllData();
}
