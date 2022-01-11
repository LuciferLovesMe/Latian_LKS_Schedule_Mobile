package com.example.abim.lks_jadwal_pake_api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences preferences;

    public Session(Context ctx) {
        preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public void setUser(int id, String name){
        preferences.edit().putInt("ID", id).commit();
        preferences.edit().putString("name", name).commit();
    }

    public int getID(){
        return preferences.getInt("ID", 0);
    }

    public String getName(){
        return preferences.getString("name", "");
    }
}
