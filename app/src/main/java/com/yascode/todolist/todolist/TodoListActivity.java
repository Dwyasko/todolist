package com.yascode.todolist.todolist;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yascode.todolist.R;
import com.yascode.todolist.data.local.AppDatabase;
import com.yascode.todolist.data.local.todolist.TodoList;

import java.util.List;

public class TodoListActivity extends AppCompatActivity implements TodoContract.view {

    RecyclerView recTodo;
    TodoContract.presenter TodoPresenter;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        recTodo = findViewById(R.id.rec_todo);
        pd = new ProgressDialog(this);
        AppDatabase appDatabase = Room.databaseBuilder(this, AppDatabase.class, "tododb").build();
        TodoPresenter = new TodoPresenter(this, appDatabase);

        TodoPresenter.retreiveTodoLocally();
    }

    @Override
    public void updateList(List<TodoList> todoLists) {
        recTodo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        TodoAdapter todoAdapter = new TodoAdapter(todoLists);
        recTodo.setAdapter(todoAdapter);
    }

    @Override
    public void showLoader() {
        pd.setMessage("Getting Data");
        pd.show();
    }

    @Override
    public void hideLoader() {
        pd.dismiss();
    }
}
