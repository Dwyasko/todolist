package com.yascode.todolist.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yascode.todolist.R;
import com.yascode.todolist.todolist.TodoListActivity;

public class MainActivity extends AppCompatActivity implements MainContract.view {

    Button btnTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTodo = findViewById(R.id.btn_todo);

        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoIntent();
            }
        });
    }

    @Override
    public void gotoIntent() {
        Intent todo = new Intent(MainActivity.this, TodoListActivity.class);
        startActivity(todo);
    }
}
