package com.yascode.todolist.splash;

import android.arch.persistence.room.Transaction;
import android.os.AsyncTask;
import android.util.Log;

import com.yascode.todolist.data.local.AppDatabase;
import com.yascode.todolist.data.local.todolist.TodoList;
import com.yascode.todolist.data.remote.ApiInterface;
import com.yascode.todolist.utils.ApplicationPreference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by caksono21 on 25/11/17.
 */

public class SplashPresenter implements SplashContract.presenter {

    SplashContract.view splashView;
    ApiInterface apiInterface;
    AppDatabase appDatabase;

    public String TAG = getClass().getName();

    public SplashPresenter(SplashContract.view view, AppDatabase appDatabase, ApiInterface apiInterface) {
        splashView = view;

        this.appDatabase = appDatabase;
        this.apiInterface = apiInterface;
    }

    @Override
    public void retreiveTodoList() {
        splashView.showLoader();

        Call<List<TodoList>> transactionCall = apiInterface.retreiveTodo();
        transactionCall.enqueue(new Callback<List<TodoList>>() {
            @Override
            public void onResponse(Call<List<TodoList>> call, Response<List<TodoList>> response) {
                if (response.isSuccessful()) {
                    List<TodoList> todoLists = response.body();

                    if (todoLists != null) {
                        TodoList[] todos = new TodoList[todoLists.size()];
                        todoLists.toArray(todos);

                        Log.d(TAG, todos[0].getTitle() + "-" + todos[0].getUserId());
                        new InsertToDb().execute(todos);
                    }
                } else {
                    Log.e(TAG, "response isUnsuccesful");
                }
            }

            @Override
            public void onFailure(Call<List<TodoList>> call, Throwable t) {
                Log.e(TAG, "onFailure invoked");
            }
        });
    }

    class InsertToDb extends AsyncTask<TodoList[], Void, String> {

        @Override
        protected String doInBackground(TodoList[]... todoLists) {
            TodoList[] todos = todoLists[0];

            appDatabase.todolistDao().insertAll(todos);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            splashView.hideLoader();
        }
    }
}
