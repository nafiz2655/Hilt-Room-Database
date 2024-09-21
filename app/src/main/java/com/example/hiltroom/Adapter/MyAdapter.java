package com.example.hiltroom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiltroom.Helper.RoomHelper;
import com.example.hiltroom.Model.Student;
import com.example.hiltroom.R;
import com.example.hiltroom.VIew.UpdateData;
import com.example.hiltroom.VIew.ViewData;

import java.util.ArrayList;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<Student> arrayList = new ArrayList<>();
    Context  context;



    RoomHelper roomHelper;

    public MyAdapter() {
    }

    public MyAdapter(ArrayList<Student> arrayList, Context context) {
    }

    public MyAdapter(ArrayList<Student> arrayList, Context context, RoomHelper roomHelper) {
        this.arrayList = arrayList;
        this.context = context;
        this.roomHelper = roomHelper;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name,tv_roll,tv_reg,tv_subject,tv_phone,tv_address,tv_uptade,tv_delete;
        CircleImageView profile_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.name);
            tv_roll = itemView.findViewById(R.id.roll);
            tv_reg = itemView.findViewById(R.id.reg);
            tv_subject = itemView.findViewById(R.id.subject);
            tv_phone = itemView.findViewById(R.id.phone);
            tv_address = itemView.findViewById(R.id.address);
            tv_uptade = itemView.findViewById(R.id.tv_uptade);
            tv_delete = itemView.findViewById(R.id.tv_delete);
            profile_image = itemView.findViewById(R.id.profile_image);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Student student = arrayList.get(position);

        int sId = student.getId();
        String sName = student.getName();
        String sPhone = student.getPhone();
        String sAddress = student.getAddress();
        String sreg = student.getReg();
        String sRoll = student.getRoll();
        String sSubcect = student.getSubject();
        String sImage = student.getImage();


        holder.tv_name.setText(sName);
        holder.tv_phone.setText(sPhone);
        holder.tv_address.setText(sAddress);
        holder.tv_reg.setText(sreg);
        holder.tv_roll.setText(sRoll);
        holder.tv_subject.setText(sSubcect);



        holder.tv_uptade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData.id = sId;
                UpdateData.NAME = sName;
                UpdateData.PHONE = sPhone;
                UpdateData.ADDRESS = sAddress;
                UpdateData.REG = sreg;
                UpdateData.ROLL = sRoll;
                UpdateData.SUB = sSubcect;
                UpdateData.encodedImageString= sImage;
                context.startActivity(new Intent(context,UpdateData.class));


            }
        });

        holder.tv_delete.setOnClickListener(view -> {


            // Remove the item from the database
            roomHelper.deleteData(student);

            // Remove the item from the ArrayList
            arrayList.remove(position);

            // Notify the adapter about the removed item
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, arrayList.size());

        });

        // Decode Base64 string to byte array
        byte[] decodedString = Base64.decode(sImage, Base64.DEFAULT);

        // Convert byte array to Bitmap
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        // Set the Bitmap to the ImageView
        holder.profile_image.setImageBitmap(decodedBitmap);




    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }


}
