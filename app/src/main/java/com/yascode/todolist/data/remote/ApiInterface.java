package com.yascode.todolist.data.remote;

import com.yascode.todolist.data.local.todolist.TodoList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by caksono21 on 25/11/17.
 */
public interface ApiInterface {
    //login
    @Headers("Content-Type: application/json")
    @GET("todos")
    Call<List<TodoList>> retreiveTodo();
}