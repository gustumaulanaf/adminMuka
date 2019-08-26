package com.gustu.adminmuka.sharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.gustu.adminmuka.BuildConfig;
public class SharedPrefUtil {

    public static SharedPreferences pref() {
        return App.getContext().getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor edit(){
        return pref().edit();
    }
    public static void saveString(String key, String value){
        edit().putString(key, value).apply();
    }

    public static String getString(String key){
        return pref().getString(key, null);
    }

    public static void saveInt(String key, int value){ edit().putInt(key, value).apply(); }

    public static int getInt(String key) { return pref().getInt(key, 0); }

    public static void saveBoolean(String key, boolean value) {
        pref().edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return pref().getBoolean(key, false);
    }


    public static void saveToken(String key, String value){
        edit().putString(key, value).apply();
    }

    public static String getToken(String key){ return pref().getString(key, null); }

    public static void saveExpired(String key, String value) { edit().putString(key, value).apply(); }

    public static String getExpired(String key) { return pref().getString(key, null); }



//    public static void isLogin(String key, boolean value){
//        edit().putBoolean(key, value).apply();
//    }
//
//    public static boolean getLogin(String key){
//        return pref().getBoolean(key, false);
//    }

}
