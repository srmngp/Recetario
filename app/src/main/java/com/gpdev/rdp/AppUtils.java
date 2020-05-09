package com.gpdev.rdp;

public class AppUtils {

    public static String extractString(int resourceId){
       return App.getContext().getString(resourceId);
    }
}
