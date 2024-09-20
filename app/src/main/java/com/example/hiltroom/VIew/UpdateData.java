package com.example.hiltroom.VIew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hiltroom.DataBase.MyDataBase;
import com.example.hiltroom.Helper.RoomHelper;
import com.example.hiltroom.InsertData;
import com.example.hiltroom.Model.Student;
import com.example.hiltroom.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.hdodenhof.circleimageview.CircleImageView;

@AndroidEntryPoint
public class UpdateData extends AppCompatActivity {

    EditText tv_name,tv_roll,tv_reg,tv_subject,tv_phone,tv_address;

    TextView update;

    public static int id ;
    public static String NAME = "";
    public static String ROLL = "";
    public static String REG = "";
    public static String SUB = "";
    public static String PHONE = "";
    public static String ADDRESS = "";
    Bitmap bitmap;
    Uri mainUri;
    public static String encodedImageString;
    CircleImageView profile_image;


    @Inject
    RoomHelper roomHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_name = findViewById(R.id.tv_name);
        tv_roll = findViewById(R.id.tv_roll);
        tv_reg = findViewById(R.id.tv_reg);
        tv_subject = findViewById(R.id.tv_subject);
        tv_phone = findViewById(R.id.tv_phone);
        tv_address = findViewById(R.id.tv_address);
        update = findViewById(R.id.update);
        profile_image = findViewById(R.id.profile_image);

        tv_name.setText(NAME);
        tv_roll.setText(ROLL);
        tv_reg.setText(REG);
        tv_subject.setText(SUB);
        tv_phone.setText(PHONE);
        tv_address.setText(ADDRESS);


        byte[] decodedBytes = Base64.decode(encodedImageString, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        profile_image.setImageBitmap(decodedBitmap);


        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = tv_name.getText().toString();
                String roll = tv_roll.getText().toString();
                String reg = tv_reg.getText().toString();
                String subject = tv_subject.getText().toString();
                String phone = tv_phone.getText().toString();
                String address = tv_address.getText().toString();

                try {
                    roomHelper.updateData(new Student(id,name,roll,reg,subject,phone,address,encodedImageString));

                    Toast.makeText(UpdateData.this, "Update Successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateData.this,ViewData.class));
                }catch (Exception e){
                    Toast.makeText(UpdateData.this, "Can Not Update", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    //profile_image.setImageURI(uri);
                    mainUri = uri;

                    try {

                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        profile_image.setImageBitmap(bitmap);
                        encodeImagetobitmap(bitmap);

                    }catch (Exception e){
                        Toast.makeText(UpdateData.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    private void encodeImagetobitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        encodedImageString = Base64.encodeToString(bytes, Base64.DEFAULT);



    }





}