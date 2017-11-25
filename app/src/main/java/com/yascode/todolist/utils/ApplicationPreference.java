package com.yascode.todolist.utils;

import android.content.Context;

/**
 * Created by caksono21 on 25/11/17.
 */

public class ApplicationPreference {

    public static final String IS_FIRST_TIME = "isfirstime";
    public final String SP_NAME = "todo_pref";

    public boolean isFirstTime(Context context, String key) {
        return context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE)
                .getBoolean(key, true);
    }

    public void setNotFirst(Context context, String key) {
        context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE)
                .edit().putBoolean(key, false).apply();
    }
}
