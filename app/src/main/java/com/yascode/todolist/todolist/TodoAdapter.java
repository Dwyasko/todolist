package com.yascode.todolist.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yascode.todolist.R;
import com.yascode.todolist.data.local.todolist.TodoList;

import java.util.List;

/**
 * Created by caksono21 on 25/11/17.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    List<TodoList> todoLists;

    public TodoAdapter(List<TodoList> todoLists) {
        this.todoLists = todoLists;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_layout, parent, false);

        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        bindHolder(holder, position);
    }

    private void bindHolder(TodoViewHolder holder, int position) {
        if (todoLists.get(position).isCompleted()) {
            holder.imgCheck.setVisibility(View.VISIBLE);
        } else {
            holder.imgCheck.setVisibility(View.INVISIBLE);
        }

        holder.txtTodo.setText(todoLists.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return todoLists.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView txtTodo;
        ImageView imgCheck;

        public TodoViewHolder(View itemView) {
            super(itemView);

            txtTodo = itemView.findViewById(R.id.txt_todo);
            imgCheck = itemView.findViewById(R.id.img_check);
        }
    }
}
