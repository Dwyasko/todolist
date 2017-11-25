package com.yascode.todolist.data.local.todolist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by caksono21 on 25/11/17.
 */

@Dao
public interface TodolistDao {
    @Query("SELECT * FROM todolist")
    List<TodoList> getAll();

    @Insert
    void insertAll(TodoList... todoLists);
}
