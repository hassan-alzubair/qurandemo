package com.hassan.qurandemo;

import android.app.Application;

public class App extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession =  new DaoMaster(new DaoMaster.DevOpenHelper(this, "prayer_times.db").getWritableDb()).newSession();
    }

    public DaoSession getDaoSession(){
        return  daoSession;
    }

}
