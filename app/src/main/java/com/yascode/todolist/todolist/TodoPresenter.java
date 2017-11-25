package com.yascode.todolist.todolist;

import android.content.Context;
import android.os.AsyncTask;

import com.yascode.todolist.data.local.AppDatabase;
import com.yascode.todolist.data.local.todolist.TodoList;

import java.util.List;

/**
 * Created by caksono21 on 25/11/17.
 */

public class TodoPresenter implements TodoContract.presenter {

    TodoContract.view TodoView;
    AppDatabase appDatabase;

    public TodoPresenter(TodoContract.view todoView, AppDatabase appDatabase) {
        TodoView = todoView;
        this.appDatabase = appDatabase;
    }


    @Override
    public void retreiveTodoLocally() {

        new GetTodo().execute();
    }

    class GetTodo extends AsyncTask<Void, Void, List<TodoList>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            TodoView.showLoader();
        }

        @Override
        protected List<TodoList> doInBackground(Void... voids) {
            List<TodoList> todoLists = appDatabase.todolistDao().getAll();

            return todoLists;
        }

        @Override
        protected void onPostExecute(List<TodoList> s) {
            super.onPostExecute(s);
            TodoView.hideLoader();
            TodoView.updateList(s);
        }
    }
}
