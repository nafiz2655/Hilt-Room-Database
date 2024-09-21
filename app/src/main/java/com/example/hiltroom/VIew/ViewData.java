package com.example.hiltroom.VIew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiltroom.Adapter.MyAdapter;
import com.example.hiltroom.DataBase.MyDataBase;
import com.example.hiltroom.Helper.RoomHelper;
import com.example.hiltroom.InsertData;
import com.example.hiltroom.Model.Student;
import com.example.hiltroom.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ViewData extends AppCompatActivity {

    MyAdapter myAdapter;
    RecyclerView recyclerView;
    ArrayList<Student> arrayList = new ArrayList<>();
    ImageView add;

    @Inject
    RoomHelper roomHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerView);
        add = findViewById(R.id.add);





        try {
            ArrayList<Student> students = (ArrayList<Student>) roomHelper.readAllData();  // Fetches a list of students
            arrayList.addAll(students);


            myAdapter = new MyAdapter(arrayList,this,roomHelper);
            recyclerView.setAdapter(myAdapter);

        } catch (Exception e) {
            Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show();
        }


        add.setOnClickListener( view -> {
            startActivity(new Intent(ViewData.this, InsertData.class));
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        try {
            arrayList = new ArrayList<>();

            ArrayList<Student> students = (ArrayList<Student>) roomHelper.readAllData();  // Fetches a list of students
            arrayList.addAll(students);


            myAdapter = new MyAdapter(arrayList,this);
            recyclerView.setAdapter(myAdapter);

        } catch (Exception e) {
            Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show();
        }
    }
}