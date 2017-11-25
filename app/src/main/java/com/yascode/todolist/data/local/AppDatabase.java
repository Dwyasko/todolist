package com.yascode.todolist.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.yascode.todolist.data.local.todolist.TodoList;
import com.yascode.todolist.data.local.todolist.TodolistDao;

/**
 * Created by caksono21 on 25/11/17.
 */

@Database(entities = {TodoList.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodolistDao todolistDao();
}
