package com.example.hiltroom.ModuleHilt;

import android.content.Context;

import com.example.hiltroom.DataBase.MyDataBase;
import com.example.hiltroom.Helper.RoomHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class SingletonModule {

    @Provides
    @Singleton
    public MyDataBase myDataBase(@ApplicationContext Context context) {
        return MyDataBase.getDatabase(context);
    }

    @Provides
    @Singleton
    public RoomHelper roomHelper(MyDataBase myDataBase) {
        return myDataBase.roomDao();
    }
}
