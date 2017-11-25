package com.yascode.todolist.todolist;

import com.yascode.todolist.data.local.todolist.TodoList;

import java.util.List;

/**
 * Created by caksono21 on 25/11/17.
 */

public interface TodoContract {

    interface view {
        void updateList(List<TodoList> todoLists);

        void showLoader();

        void hideLoader();
    }

    interface presenter {
        void retreiveTodoLocally();
    }
}
